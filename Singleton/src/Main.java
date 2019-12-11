public class Main {
    Main() {
        /*LazySingleton ss = LazySingleton.getInstance();
        EagerSingleton s = EagerSingleton.getInstance();*/
        LazySingleton.print();
        EagerSingleton.print();
    }

    public static void main(String[] args) {
        new Main();
    }
}
