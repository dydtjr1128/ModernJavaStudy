import org.w3c.dom.Document;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author : dydtjr1128
 * @project : Study
 * @github : https://github.com/dydtjr1128
 * @since : 2020-05-07
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Document xml = null;
        HttpURLConnection connection = null;

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            URL restUrl = new URL("https://121.170.135.242");
            String protocol = restUrl.getProtocol();

            /* 프로토콜이 HTTPS일 경우 서버쪽 인증서를 신뢰할 수 있도록 처리 */
            if (protocol.toLowerCase().equals("https")) {
                TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
                        System.out.println("@@@" + authType);
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
                        System.out.println("!!!" + authType);
                    }

                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        System.out.println("Ttttttt");
                        return null;
                    }
                }};

                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            }

            /*connection 정보 설정*/
            connection = (HttpURLConnection) restUrl.openConnection();
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            connection.setRequestProperty("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.5,en;q=0.3");
            connection.setRequestProperty("Connection", "keep-alive");
/*            connection.setConnectTimeout(Configure.HTTP_CONN_TIME_OUT);
            if(timeOut > 0){
                connection.setReadTimeout(timeOut);
            }else{
                connection.setReadTimeout(Configure.HTTP_READ_TIME_OUT);
            }*/

            /* 호출한 결과를 XML문서로 저장 */
            xml = documentBuilder.parse(connection.getInputStream());

        } catch (MalformedURLException me) {
            me.printStackTrace();
            throw new MalformedURLException("MalformedURLException");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            /* resource */
            connection.disconnect();
        }

    }
}
