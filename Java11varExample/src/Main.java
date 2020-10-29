import java.util.ArrayList;

public class Main {

    // c++ 과 달리 리턴타입은 추론 안됨
    public static ArrayList<Integer> getArrayList(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(2);
        return arrayList;
    }

    public static void main(String[] args) {
        var a = getArrayList();
        var b = new ArrayList<Integer>();
        b.add(1);

        a.forEach(System.out::println);
        b.forEach(System.out::println);
    }
}
