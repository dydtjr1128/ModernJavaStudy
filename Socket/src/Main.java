import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * @author : dydtjr1128
 * @project : Study
 * @github : https://github.com/dydtjr1128
 * @since : 2020-04-28
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, KeyManagementException, NoSuchAlgorithmException {

        SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslSocket = (SSLSocket) socketFactory.createSocket("121.170.135.242", 3435);
        sslSocket.setEnabledProtocols(sslSocket.getEnabledProtocols());
        sslSocket.setEnabledCipherSuites(sslSocket.getEnabledCipherSuites());
        SSLSession session = sslSocket.getSession();
        System.out.println(session.getPeerHost() + " " + session.getProtocol() + " " + session.getCreationTime() + " " + Arrays.toString(session.getValueNames()));

        DataOutputStream outputStream = new DataOutputStream(sslSocket.getOutputStream());
        DataInputStream inputStream = new DataInputStream(sslSocket.getInputStream());

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String s = null;
                    try {
                        Thread.sleep(100);
                        int r = inputStream.read();
                        System.out.println(r);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //System.out.println(s);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String s = null;
                    try {
                        Thread.sleep(5000);
                        outputStream.writeUTF("hello");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //System.out.println(s);
                }
            }
        }).start();
        while (true) {
            Thread.sleep(5000);
        }

    }
}
