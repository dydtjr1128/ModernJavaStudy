import java.util.stream.LongStream;

public class Add {//멀티 스레딩에서의 병렬 연산을 통해 동기화 되지 않은 공유자원에 접근
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
            add(i);
        }
        System.out.println(System.currentTimeMillis() - st + " " + num);

        st = System.currentTimeMillis();
        num = 0;
        LongStream.range(0, range).parallel().forEach(Add::add);
        System.out.println(System.currentTimeMillis() - st + " " + num);
    }
}
