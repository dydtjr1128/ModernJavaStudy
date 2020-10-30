package Closeable.src;

import java.io.Closeable;

public class Product implements Closeable {
    private final String name;

    public Product(String name) {
        this.name = name;
        System.out.println("Product(" + name + ")");
    }

    @Override
    public void close() {
        System.out.println("close(" + name + ")");
    }
}
