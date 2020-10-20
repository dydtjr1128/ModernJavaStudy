import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Before create p1");
        Product p1 = new Product("p1");
        System.out.println("After create p1");

        System.out.println("Before create p2");
        Product p2 = new Product("p2");
        System.out.println("After create p2");

        try (Product p3 = new Product("p3")) {

        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }

}
