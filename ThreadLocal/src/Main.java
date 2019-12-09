public class Main {
    public static void main(String[] args) throws InterruptedException {
        Context.threadLocal.set(new StoreData(300, "This is message."));
        Parent parent = new Parent(100);
        Parent parent2 = new Parent(200);
        parent.start();
        parent2.start();

        parent.join();
        parent2.join();
    }
}
