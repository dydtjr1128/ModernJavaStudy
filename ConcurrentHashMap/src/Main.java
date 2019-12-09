import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final int MAP_SIZE = 5;
        Map<String, Integer>[] maps = new Map[MAP_SIZE];
        maps[0] = new HashMap<>();
        maps[1] = new LinkedHashMap<>();
        maps[2] = Collections.synchronizedMap(new HashMap<>());
        maps[3] = new ConcurrentHashMap<>();
        maps[4] = new TreeMap<>();
        String ukey = null;
        for (int i = 0; i < 1000000; i++) {
            String uid = UUID.randomUUID().toString();
            int val = (int) ((Math.random() * Integer.MAX_VALUE) + 1);
            for (int j = 0; j < MAP_SIZE; j++) {
                maps[j].put(uid, val);
            }
            if (i == 1000)
                ukey = uid;
        }

        System.out.println("============= containsKey time =============");
        for (int j = 0; j < MAP_SIZE; j++) {
            long st = System.currentTimeMillis();
            for (int i = 0; i < 1000000; i++) {
                maps[j].containsKey(ukey);
                maps[j].containsKey(UUID.randomUUID().toString());
            }
            System.out.println(maps[j].getClass().toString() + " " + (System.currentTimeMillis() - st));
        }
        System.out.println("============= containsValue time =============");
        for (int j = 0; j < MAP_SIZE; j++) {
            long st = System.currentTimeMillis();

            for (int i = 0; i < 30; i++) {
                int val = (int) ((Math.random() * Integer.MAX_VALUE) + 1);
                maps[j].containsValue(val);
            }
            System.out.println(maps[j].getClass().toString() + " " + (System.currentTimeMillis() - st));
        }

        System.out.println("============= containsKey time in multi thread =============");
        AtomicInteger[] atomicIntegers = new AtomicInteger[2];
        atomicIntegers[0] = new AtomicInteger(0);
        atomicIntegers[1] = new AtomicInteger(0);
        ExecutorService service = Executors.newFixedThreadPool(4);
        Future<?>[] future = new Future[4];
        for (int i = 0; i < 4; i++) {
            future[i] = service.submit(() -> {
                for (int j = 2; j <= 3; j++) {
                    long st = System.currentTimeMillis();
                    for (int i1 = 0; i1 < 1000000; i1++) {
                        maps[j].containsKey(UUID.randomUUID().toString());
                    }
                    atomicIntegers[j - 2].addAndGet((int) (System.currentTimeMillis() - st));
                }
            });
        }
        for (int i = 0; i < 4; i++) {
            future[i].get();
        }
        System.out.println("SynchronizedMap : " + atomicIntegers[0].get() / 4);
        System.out.println("ConcurrentHashMap : " + atomicIntegers[1].get() / 4);
    }
}
