import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int threadCount = 10;

        System.out.println("===Executors.newFixedThreadPool===");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        //executorService.shutdown();
        MyRunnable runnable = new MyRunnable(1);
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(runnable);
        }
        while (true){
            TimeUnit.SECONDS.sleep(1500);
            System.out.println("!");
            break;
        }

        /*try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("===Executors.newCachedThreadPool===");
        executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(new MyRunnable(i));
        }


        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("===Executors.newSingleThreadExecutor===");
        executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(new MyRunnable(i));
        }


        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("===Executors.newScheduledThreadPool===");
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        for (int i = 0; i < threadCount; i++) {
            scheduledExecutorService.scheduleAtFixedRate(new MyRunnable(i), 0, 1, TimeUnit.SECONDS); // scheduleAtFixedRate 호출 당 큐 하나 생김. 여기 말고 MyRunnable 내부에 for문 거는것-> 큐하나로 돌려쓰기 때문에 대기함.
        }*/



    }
}
