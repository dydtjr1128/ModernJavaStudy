/**
 * @author : dydtjr1128
 * @project : Study
 * @github : https://github.com/dydtjr1128
 * @since : 2020-06-16
 */
public class Main {
    public static void main(String[] args) {
        String texts[] =
                {
                        "안녕하세요 만나서 반가워요.", // 한국어
                        "Hi, it's nice to meet you.", // 영어
                        "こんにちは。お会いできて嬉しいです。", // 일본어
                        "你好,很高兴见到你。", // 중국어
                        "Encantado de conocerte.", // 스페인어
                        "Hallo, schön, Sie kennenzulernen.", // 독일어
                        "Привет всем, рад встрече.", // 러시아어
                        "Xin chào, rất vui được gặp bạn.", // 베트남어
                        "สวัสดีค่ะ ยินดีที่ได้พบนะคะ", // 태국어
                        "हाय, यह आप को पूरा करने के लिए अच्छा है।", // 힌디어
                        "Oi, é um prazer conhecê-lo." // 포르투갈어
                };

        for(String text : texts) {
            System.out.println(text);
        }
    }
}
