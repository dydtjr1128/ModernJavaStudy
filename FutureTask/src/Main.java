import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread());
        FutureTask<String> task = new FutureTask<>(
                new Callable<String>() {
                    public String call() throws Exception {
                        System.out.println(Thread.currentThread());
                        return "This is return message";
                    }
                });
        FutureTask<String> task2 = new FutureTask<>(
                new Runnable() {
                    @Override
                    public void run() {
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
        try {
            String result2l = task.get(5 /* TIMEOUT */, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("time out!");
        }

        System.out.println(task2.get());
    }
}
