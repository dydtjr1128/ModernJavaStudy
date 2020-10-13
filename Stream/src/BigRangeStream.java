import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

/**
 * @author : dydtjr1128
 * @project : Study
 * @github : https://github.com/dydtjr1128
 * @since : 2020-01-03
 */
public class BigRangeStream {
    public static void main(String[] args) {
        final long RANGE = 1000000000;
        final long FILTER = 900000000;


        // 기존 연산
        long st = System.currentTimeMillis();
        long count = 0;
        for (int i=0; i< RANGE; i++) {
            if (i < FILTER)
                count++;
        }
        System.out.println(count);
        System.out.println(System.currentTimeMillis() - st);

        st = System.currentTimeMillis();
        // Stream 연산
        count = LongStream.range(0,RANGE).filter(n -> n < FILTER).count();
        System.out.println(count);
        System.out.println(System.currentTimeMillis() - st);
    }
}
