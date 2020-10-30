package dydtjr1128.github.io.filewatcher;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Component
public class FileWatcherApplicationRunner implements ApplicationRunner {
    private static final String projPath = System.getProperty("user.dir");

    private WatchKey watchKey;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //watchService 생성
        WatchService watchService = FileSystems.getDefault().newWatchService();
        //경로 생성
        Path path = Paths.get("C:\\Users\\CYS\\Downloads\\일");
        //해당 디렉토리 경로에 와치서비스와 이벤트 등록
        path.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.OVERFLOW);

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    watchKey = watchService.take();//이벤트가 오길 대기(Blocking)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<WatchEvent<?>> events = watchKey.pollEvents();//이벤트들을 가져옴
                for (WatchEvent<?> event : events) {
                    //이벤트 종류
                    WatchEvent.Kind<?> kind = event.kind();
                    //경로
                    Path paths = (Path) event.context();
                    System.out.println(paths.toAbsolutePath());//C:\...\...\test.txt
                    if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
                        System.out.println("created something in directory");
                    } else if (kind.equals(StandardWatchEventKinds.ENTRY_DELETE)) {
                        System.out.println("delete something in directory");
                    } else if (kind.equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
                        System.out.println("modified something in directory");
                    } else if (kind.equals(StandardWatchEventKinds.OVERFLOW)) {
                        System.out.println("overflow");
                    } else {
                        System.out.println("hello world");
                    }
                }
                if (!watchKey.reset()) {
                    try {
                        watchService.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
}
