import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stream {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        
        // 기존 연산
        long count = 0;
        for (int n : list) {
            if (n > 2)
                count++;
        }
        System.out.println(count);

        // Stream 연산
        count = list.stream().filter(n -> n > 2).count();
        System.out.println(count);
    }
}
