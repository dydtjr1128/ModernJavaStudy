class Service {
    public void printThreadLocal() {
        StoreData storeData = Context.threadLocal.get();
        System.out.println(this.getClass().toString() + storeData.toString());
    }
}