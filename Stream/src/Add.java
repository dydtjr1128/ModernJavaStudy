import java.util.stream.LongStream;

public class Add {
    public static long num;

    private static void add(long l) {
        num += l;
    }

    public static void main(String[] args) {
        int range = Integer.MAX_VALUE;

        long st = System.currentTimeMillis();
        num = 0;
        LongStream.range(0, range).forEach(Add::add);
        System.out.println(System.currentTimeMillis() - st + " " + num);

        st = System.currentTimeMillis();
        num = 0;
        for (int i = 0; i < range; i++) {
            num += i;
        }
        System.out.println(System.currentTimeMillis() - st + " " + num);

        st = System.currentTimeMillis();
        num = 0;
        LongStream.range(0, range).parallel().forEach(Add::add);
        System.out.println(System.currentTimeMillis() - st + " " + num);

    }


}
