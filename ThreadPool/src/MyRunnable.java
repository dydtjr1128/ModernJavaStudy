import java.util.concurrent.TimeUnit;

/**
 * @author : dydtjr1128
 * @project : Study
 * @github : https://github.com/dydtjr1128
 * @since : 2019-12-12
 */
public class MyRunnable implements Runnable {
    int id;

    MyRunnable(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        long st = 0;
        try {
            st = System.currentTimeMillis();
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread id : " + id + " " + Thread.currentThread() + Thread.activeCount() + " " + (System.currentTimeMillis() - st));
    }
}
