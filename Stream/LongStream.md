
### Stream을 이용해서 병렬 연산을 해보자.

`Java 8` 부터는 병렬 연산을 지원하는 Stream API를 지원한다. 많은 사람들이 성능이 좋은것만으로 알고 있지만 그렇지 않은 경우도 상당히 많다.



### Stream을 사용하지 않는 것이 좋은 경우

#### 1. 전체 탐색이 아닌 중간에 연산을 그만둬야 하는경우

이와 같은 경우에 Stream API를 사용하는것은 매우 비효율적이다. Stream은 filter를 걸던 걸지 않던 전체를 탐색하기 때문에 일반 조건에서 break,return 과 달리 모든 경우를 탐색한다.
이러한 문제는 성능을 크게 떨어뜨리므로 조심해서 사용해야 한다.

#### 2. 소량의 데이터 사용

적은 양의 데이터를 병렬로 탐색하는것은 일반 연산에 비해 많이 느리다. 스레드를 생성해서 연산을 해야 하는 오버헤드가 존재하기 때문에 이를 고려해서 사용해야 한다.

#### 3. 동기화 문제가 발생할 여지가 있는 경우

더하는 연산이 필요한 경우 Stream의 병렬연산을 이용해서 더하는것은 문제가 발생한다. 멀티스레드에서 동기화 되지 않은 변수를 더하는 경우 올바르지 않은 결과를 만들어 낸다.

```java
import java.util.stream.LongStream;

public class Add {
    public static long num;

    private static void add(long l) {
        num += l;
    }

    public static void main(String[] args) {
        int range = Integer.MAX_VALUE;

        long st = System.currentTimeMillis();
        num = 0;
        LongStream.range(0, range).forEach(Add::add);
        System.out.println(System.currentTimeMillis() - st + " " + num);

        st = System.currentTimeMillis();
        num = 0;
        for (int i = 0; i < range; i++) {
            num += i;
        }
        System.out.println(System.currentTimeMillis() - st + " " + num);

        st = System.currentTimeMillis();
        num = 0;
        LongStream.range(0, range).parallel().forEach(Add::add);
        System.out.println(System.currentTimeMillis() - st + " " + num);
    }
}
``` 

위의 코드의 경우 결과는 어떻게 될까?
```result
822 2305843005992468481
563 2305843005992468481
3306 418333989419706937
```

일반 Stream과 for-loop 연산은 올바른 결과를 내지만 `parallel`을 이용한 연산을 더 느릴 뿐만 아니라 올바르지 않은 결과를 보여준다.
이처럼 동기화의 문제가 발생하는 경우에는 병렬 연산을 해서는 안된다.


