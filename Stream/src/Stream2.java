import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : dydtjr1128
 * @project : Study
 * @github : https://github.com/dydtjr1128
 * @since : 2020-08-13
 */
public class Stream2 {
    public static void main(String[] args) {
        List<String> list =  Arrays.asList("hello", "hi", "good");

        list.stream().map(String::toUpperCase).forEach(System.out::println);

        List<String> list2 = list.stream().map(String::toUpperCase).collect(Collectors.toList());

        for(String a : list) {
            System.out.println(a);
        }

        for(String a : list2) {
            System.out.println(a);
        }
    }
}
