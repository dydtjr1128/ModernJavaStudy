class StoreData {
    private int id;
    private String message;

    StoreData(int id, String message) {
        this.id = id;
        this.message = message;
    }

    @Override
    public String toString() {
        return "StoreData{" + "id=" + id + ", message='" + message + '\'' + '}';
    }
}