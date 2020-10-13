import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : dydtjr1128
 * @project : Study
 * @github : https://github.com/dydtjr1128
 * @since : 2020-01-22
 */
public class Main {
    public static void main(String[] args) throws URISyntaxException, MalformedURLException, InterruptedException {
        URI uri = new URI(String.format("%s/%s/%s/%s/","http://www.naver.com","abc","ttt","rr"));
        uri = uri.resolve("patch/");
        uri = uri.resolve("patch.dat2");
        uri = uri.resolve("patch.dat2");
        System.out.println(uri.toString());

        System.out.println(Thread.activeCount());
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.submit(() -> {
            System.out.println("hello");
        });
        System.out.println(service.toString());
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.activeCount());
        service.shutdownNow();
        System.out.println(Thread.activeCount());
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.activeCount());
        System.out.println(service.toString());
        service.submit(() -> {
            System.out.println("hello");
        });
    }
}
