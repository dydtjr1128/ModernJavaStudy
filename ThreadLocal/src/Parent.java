class Parent extends Thread {
    private int id;

    Parent(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println(this.getClass().toString() + Context.threadLocal.get().toString());
        Service service = new Service();
        service.printThreadLocal();
        Context.threadLocal.remove();
        //System.out.println(this.getClass().toString() + Context.threadLocal.get().toString());// NullPointerException
    }
}