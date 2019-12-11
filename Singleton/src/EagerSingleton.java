/**
 * Created by dydtjr1994@gmail.com on 2019-12-11
 * Blog : https://dydtjr1128.github.io/
 * Github : https://github.com/dydtjr1128
 */
public class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    private EagerSingleton() {
        System.out.println("EagerSingleton Constructor");
    }

    public static void print() {
        System.out.println("EagerSingleton.print()");
    }
}
