
### Stream 을 알아보자

`Java 8` 부터 추가된 Stream 은 다양한 기능을 지원한다. Stream의 큰 기능은 "Collection, Array 등의 데이터 요소를 함수형 인터페이스(람다식)을 이용하여 반복 처리해 주는 기능" 이다.
파일에서 사용하는 Stream 과는 다른 개념이며 데이터 소스를 추상화 시켜 놓았기 때문에 어떠한 형태를 가진 데이터도 동일하게 처리가 가능하다. 
             
```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

// 기존 연산
long count = 0;
for (int n : list) {
    if (n > 2)
        count++;
}
System.out.println(count);

// Stream 연산
count = list.stream().filter(n -> n > 2).count();
System.out.println(count);
```

위의 코드 처럼 기존의 연산은 if와 반복문을 통해서 연산을 해야 했지만 아래의 Stream 을 이용한 코드는 코드를 좀 더 직관적이고 가독성 있게 만들 수 있다.
