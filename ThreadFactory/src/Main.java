import executor.MyThreadFactory;
import executor.Task;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        MyThreadFactory myThreadFactory = new MyThreadFactory("Thread");
        Task task = new Task();
        
        for (int i = 0; i < 10; i++) {
            Thread thread = myThreadFactory.newThread(task);
            thread.start();
        }
        System.out.println("End");
        System.out.println(myThreadFactory.getThreadLog());
    }
}
