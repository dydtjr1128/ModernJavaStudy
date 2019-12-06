package executor;

import java.util.Date;
import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
    private final String threadNamePrefix;
    private long threadCounter;
    private StringBuilder builder;

    public MyThreadFactory(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
        this.threadCounter = 0;
        this.builder = new StringBuilder();
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, threadNamePrefix + "-" + threadCounter++);
        builder.append(String.format("Created thread %d with name %s on %s, Active thread %d %n", thread.getId(), thread.getName(), new Date(), Thread.activeCount()));
        return thread;
    }

    public String getThreadLog() {
        String s = builder.toString();
        builder.setLength(0);
        return s;
    }
}
