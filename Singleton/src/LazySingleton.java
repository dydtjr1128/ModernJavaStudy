/**
 * Created by dydtjr1994@gmail.com on 2019-12-11
 * Blog : https://dydtjr1128.github.io/
 * Github : https://github.com/dydtjr1128
 */
public class LazySingleton {
    private LazySingleton() {
        System.out.println("LazySingleton Constructor");
    }

    public static LazySingleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        static {
            System.out.println("LazyHolder");
        }
        private static final LazySingleton INSTANCE = new LazySingleton();
    }

    public static void print() {
        System.out.println("LazySingleton.print()");
    }
}