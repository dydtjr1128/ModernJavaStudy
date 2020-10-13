import java.util.Queue;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Queue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        Queue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();

        ExecutorService executorService = Executors.newFixedThreadPool(10);


        executorService.execute(new Task(1, concurrentLinkedQueue));
        executorService.execute(new Task(2, concurrentLinkedQueue));
        executorService.execute(new Task(3, concurrentLinkedQueue));
        //executorService.execute(new Task(4, concurrentLinkedQueue));
        executorService.execute(() -> {
            while(true) {
                System.out.println("t1"+concurrentLinkedQueue.poll());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.execute(() -> {
            while(true) {
                System.out.println("t2"+concurrentLinkedQueue.poll());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        TimeUnit.SECONDS.sleep(1000);
        executorService.shutdown();
        do {
            System.out.println("Running.... {}" + executorService.isTerminated()); // 작업이 완료되었으면 즉시 정지 한다.
            if (executorService.isTerminated()) {
                System.out.println("Running....2 {}" + executorService.isTerminated());
                executorService.shutdownNow();
            } // 지정된 시간 별로(10초단위) 작업이 모든 작업이 중지되었는지 체크 // 작업이 완료되었으면 루프 해제
        } while (!executorService.awaitTermination(10, TimeUnit.SECONDS));


        executorService.execute(new Task(1, linkedBlockingQueue));
        executorService.execute(new Task(2, linkedBlockingQueue));
        executorService.execute(new Task(3, linkedBlockingQueue));
        executorService.execute(new Task(4, linkedBlockingQueue));
        executorService.execute(() -> {
            System.out.println(concurrentLinkedQueue.poll());
        });

    }
}
