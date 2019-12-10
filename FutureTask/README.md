
### FutureTask

`FutureTask`는 `RunnableFuture`의 구현체 이며 `RunnableFuture`는 `Runnalble`과 `Future`의 결합체 이다. 말 그대로 `Future`와 `Task`를 합친 말로 Task를 수행하고 결과를 Future로 받겠다는 의미이다. 즉, 수행 Thread와 결과를 받는 Thread가 분리 되어 있는 구조를 가진다.
 
여기서 Timeout을 걸 수가 있는데, 이 Timeout의 원리를 생각해 보면 하나의 쓰레드가 작업하는동안 다른 쓰레드가 이 쓰레드를 얼마나 기다릴 지를 정하는 것이다. 즉 Fork-Join 모델의 형태로써 작업 내용은 fork 된 thread에게 맡기고, join하는 thread에서 얼마나 join을 하고 있을지 정하는 것이다.

```java
FutureTask<String> task = new FutureTask<>(
                new Callable<String>() {
                    public String call() throws Exception {
                        return "This is return message";
                    }
                });
FutureTask<String> task2 = new FutureTask<>(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("doSomething...");
                    }
                }, "This is return message");
```

`FutureTask`의 기본 형태는 위와 같다. 생성자의 인자로 `Callable` 혹은 `Runnable`을 받으며 Runnable의 경우 Result 값을 2번째 인자로 넣어 주어야 한다.
실행은 `run()` 메소드로 실행하며 결과를 받기 위해 `get()` 메소드를 사용한다. 
`FutureTask`의 멤버 함수인 run을 실행하면 Callable 혹은 Runnable 객체의 오버라이드 된 메소드가 실행되고, 결과를 내부에 가지고 있게 된다. `isDone()` 메소드로 실행이 완료가 되었는지 boolean 값으로 받아 볼 수 있으며 결과값을 get()메소드로 받아 올 수 있다.

이 상태에서 `run()` 메소드로 실행하는것은 멀티 스레드가 아닌 main thread에서 수행하고 비동기로 결과를 받아 오는 것이므로 쓰레드를 이용하여 실행 해 주어야 한다.

```java
    ExecutorService executorService = Executors.newFixedThreadPool(2);// make 2 thread pool
    try {
        String result = task.get(5 /* TIMEOUT */, TimeUnit.SECONDS);
    } catch (TimeoutException e) {
        System.out.println("time out!");}
    try {
        String result2l = task.get(5 /* TIMEOUT */, TimeUnit.SECONDS);
    } catch (TimeoutException e) {
        System.out.println("time out!");
    }
```

하나의 작업 정도는 thread로 만들어 실행 할 수 있겠지만 많은 작업이 있는 경우에는 비 효율 적이므로 ExecutorService를 이용하여 ThreadPool을 사용하는 것이 좋다. 또한 get을 이용해 작업의 결과를 받도록 만들고, timeout 시간을 설정해 일정 시간이 지난 뒤에 exception을 발생 시키도록 만들었다.
따라서 발생하는 TimeoutException을 적절하게 처리해주면 된다.
