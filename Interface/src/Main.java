public class Main {
    public interface A {
        public void print();
        void print2();
        default void print3() {
            System.out.println("why?");
        }
    }
    abstract class AA {
        abstract public void print();
        abstract protected void print2();
        abstract void print3();

        protected void print4() {

        }

        int a;
    }

    class B implements A {
        @Override
        public void print() {

        }

        @Override
        public void print2() {

        }
    }
    class C extends  AA {
        @Override
        public void print() {

        }

        @Override
        protected void print2() {

        }

        @Override
        void print3() {

        }

        @Override
        public void print4() {

        }
    }
}
