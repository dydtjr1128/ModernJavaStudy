public class Box<T extends Comparable<T>> implements Comparable<T> {
    private T t;

    public void add(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    @Override
    public int compareTo(T obj) {
        return t.compareTo(obj);
    }
}


