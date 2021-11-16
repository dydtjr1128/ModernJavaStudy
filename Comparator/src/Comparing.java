public class Comparing {

    public static void main(String[] args) {

        //isGreater 메소드 작동확인용
        System.out.println((new Search()).isGreater(20, 3));
        System.out.println((new Search()).isGreater(3.0, 2.0));
        System.out.println((new Search()).isGreater("Hong", "Gildong"));

        //배열 선언
        int arrint[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        double arrdouble[] = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0,
                11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0, 20.0};

        //아래의 코드가 정상작동되어야함
        //System.out.println((new Search()).BSearch(arrint[], 15));
        //System.out.println((new search()).BSearch(arrdouble[], 14.5));


    }
}
