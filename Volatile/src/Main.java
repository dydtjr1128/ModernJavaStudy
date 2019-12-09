import java.util.concurrent.atomic.AtomicLong;

public class Main {
    static volatile long value = 0;

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                for (int j = 0; j <= 1000000; j++) {
                    value += j;
                }
            }).start();
        }
        new Thread(() -> {
            while (true) {
                System.out.println("r" + value);
            }
        }).start();
    }
}
