import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread());
        FutureTask<String> task = new FutureTask<>(
                new Callable<String>() {
                    public String call() throws Exception {
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println(Thread.currentThread());
                        return "This is return message";
                    }
                });
        FutureTask<String> task2 = new FutureTask<>(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread());
                    }
                }, "This is return message");

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(task);
        executorService.execute(task2);
        try {
            String result = task.get(5 /* TIMEOUT */, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("time out!");
        }
        System.out.println(task2.get());
    }
}
