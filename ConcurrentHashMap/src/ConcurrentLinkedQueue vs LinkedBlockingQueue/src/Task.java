package ConcurrentLinkedQueue

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
    private final int taskId;
    private final Queue<String> queue;

    public Task(int id, Queue<String> queue) {
        this.taskId = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        int i=0;
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            queue.offer("Task id : " + taskId + " Loop : " + i);
        }
    }
}
