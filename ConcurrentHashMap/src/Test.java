import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int MAP_SIZE = 2;
        Map<String, Integer>[] maps = new Map[MAP_SIZE];
        maps[0] = Collections.synchronizedMap(new HashMap<>());
        maps[1] = new ConcurrentHashMap<>();
        List<String> arrayList = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            String uid = UUID.randomUUID().toString();
            arrayList.add(uid);
            int val = (int) ((Math.random() * Integer.MAX_VALUE) + 1);
            for (int j = 0; j < MAP_SIZE; j++) {
                maps[j].put(uid, val);
            }
        }

        System.out.println("============= containsKey time in multi thread =============");
        AtomicInteger[] atomicIntegers = new AtomicInteger[MAP_SIZE];
        atomicIntegers[0] = new AtomicInteger(0);
        atomicIntegers[1] = new AtomicInteger(0);
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(availableProcessors);
        Future<?>[] future = new Future[availableProcessors];
        for (int i = 0; i < availableProcessors; i++) {
            future[i] = service.submit(() -> {
                for (int j = 0; j < MAP_SIZE; j++) {
                    long st = System.currentTimeMillis();
                    for (int k = 0; k < arrayList.size(); k++) {
                        maps[j].get(arrayList.get(k));
                    }
                    atomicIntegers[j].addAndGet((int) (System.currentTimeMillis() - st));
                }
            });
        }
        for (int i = 0; i < availableProcessors; i++) {
            future[i].get();
        }
        for (int i = 0; i < MAP_SIZE; i++) {
            System.out.println(maps[i].getClass().toString() + " " + atomicIntegers[i].get() / availableProcessors);
        }

    }
}
