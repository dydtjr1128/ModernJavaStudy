import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.LongStream;

public class DefaultSum {
    public static void main(String[] args) {
        int range = Integer.MAX_VALUE;

        long st = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger();
        long t = LongStream.range(0, range).reduce(0, Long::sum);
        System.out.println(System.currentTimeMillis() - st + " " + t);

        st = System.currentTimeMillis();
        long t2 = 0;
        for (int i = 0; i < range; i++) {
            t2 += i;
        }
        System.out.println(System.currentTimeMillis() - st + " " + t2);

        st = System.currentTimeMillis();
        long t3 = LongStream.range(0, range).parallel().reduce(0, Long::sum);
        System.out.println(System.currentTimeMillis() - st + " " + t3);
    }
}
