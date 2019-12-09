import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class FilterSum {
    public static void main(String[] args) {
        int range = Integer.MAX_VALUE;
        int filter = 100000000;

        long st = System.currentTimeMillis();
        long t = LongStream.range(0, range).filter(i -> i < filter).reduce(0, Long::sum);
        System.out.println(System.currentTimeMillis() - st + " " + t);

        st = System.currentTimeMillis();
        long t2 = 0;
        for (int i = 0; i < range; i++) {
            t2 += i;
            if (i >= filter)
                break;
        }
        System.out.println(System.currentTimeMillis() - st + " " + t2);

        st = System.currentTimeMillis();
        long t3 = LongStream.range(0, range).parallel().filter(i -> i < filter).reduce(0, Long::sum);
        System.out.println(System.currentTimeMillis() - st + " " + t3);

    }
}
