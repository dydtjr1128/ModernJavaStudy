import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        List<Integer> temp1 = new ArrayList<>();
        List<Integer> temp2 = new ArrayList<>();
        int max = 100000;
        long st;
        HashSet<Integer> h1 = new HashSet<>();
        HashSet<Integer> h2 = new HashSet<>();
        for (int i = 0; i <max ; i++) {
            h1.add((int)(Math.random()*max));
            h2.add((int)(Math.random()*max));
        }
        List<Integer> list = new ArrayList<>(h1);
        List<Integer> list2 = new ArrayList<>(h2);

        System.out.println("===== start =====");

        temp1.addAll(list);
        temp2.addAll(list2);
        st = System.currentTimeMillis();
        for(int a : temp2){
            if(!temp1.contains(a))
                temp1.add(a);
        }
        System.out.println("contains : "+ temp1.size() + " " + (System.currentTimeMillis()-st) + "ms");

        temp1.clear();
        temp2.clear();
        temp1.addAll(list);
        temp2.addAll(list2);
        st = System.currentTimeMillis();
        HashSet<Integer> hashSet = new HashSet<>(temp1);
        hashSet.addAll(temp2);
        List<Integer> finalFoo = new ArrayList<>(hashSet);
        System.out.println("hashset : "+ finalFoo.size() + " " + (System.currentTimeMillis()-st) + "ms");

        temp1.clear();
        temp2.clear();
        temp1.addAll(list);
        temp2.addAll(list2);
        st = System.currentTimeMillis();
        List<Integer> combinedList = Stream.of(temp1, temp2)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("flatMap : "+ combinedList.size() + " " + (System.currentTimeMillis()-st) + "ms");


        temp1.clear();
        temp2.clear();
        temp1.addAll(list);
        temp2.addAll(list2);
        st = System.currentTimeMillis();
        List<Integer> listTwoCopy = new ArrayList<>(temp1);
        listTwoCopy.removeAll(temp2);
        temp2.addAll(listTwoCopy);
        System.out.println("remove : "+ temp2.size() + " " + (System.currentTimeMillis()-st) + "ms");

        System.out.println("===== end =====");

    }
}
