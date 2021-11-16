import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) throws Exception {
        // 0 in 0 out
        Runnable runnable = () -> System.out.println("runnable");
        runnable.run();

        // 0 in 1 out
        Callable<String> callable = () -> "callable";
        var result = callable.call();
        // Supplier와 다르게 예외를 던질 수 있음 파일 IO 등에 사용하기 좋음., 다른쓰레드에서 사용하기위해 디자인된
        System.out.println(result);

        // after java 8
        // 0 in 1 out
        Supplier<String> stringSupplier = () -> "stringSupplier";
        result = stringSupplier.get();
        System.out.println(result);

        // 1 in 0 out
        Consumer<Integer> consumer = (i) -> System.out.println("consumer1 : " + i);
        Consumer<Integer> consumer2 = (i) -> System.out.println("consumer2 : " + i);
        consumer.andThen(consumer2).accept(123);
        consumer.accept(11);

        // 2 in 0 out
        BiConsumer<Integer, String> biConsumer = (i, s) -> System.out.println("biConsumer : " + i + s);
        biConsumer.accept(10, " hello!");

        // 1 in 1 out
        Function<String, Integer> function = (s) -> {
            System.out.println("function : " + s.charAt(0));
            return Character.getNumericValue(s.charAt(0));
        };
        Function<Integer, String> intToString = (i) -> "abc" + i;
        Function<Integer, Integer> resultFunction = function.compose(intToString);
        System.out.println("resultFunction : " + resultFunction.apply(46));


        Map<Integer, Function<Integer, String>> hashMap = new HashMap<>();
        hashMap.put(1, (i) -> "apple" + i);
        hashMap.put(2, (i) -> "grape" + i);
        hashMap.put(3, (i) -> "strawberry" + i);

        System.out.println(hashMap.get(1).apply(15));
        System.out.println(hashMap.get(2).apply(15));
        System.out.println(hashMap.get(3).apply(15));
    }
}
