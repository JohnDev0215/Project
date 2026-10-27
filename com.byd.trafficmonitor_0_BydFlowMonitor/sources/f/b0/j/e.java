package f.b0.j;

import f.u;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;

/* JADX INFO: compiled from: Platform.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f5284a = g();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Logger f5285b = Logger.getLogger(u.class.getName());

    public static List<String> b(List<Protocol> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Protocol protocol = list.get(i2);
            if (protocol != Protocol.HTTP_1_0) {
                arrayList.add(protocol.toString());
            }
        }
        return arrayList;
    }

    public static byte[] d(List<Protocol> list) {
        g.c cVar = new g.c();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Protocol protocol = list.get(i2);
            if (protocol != Protocol.HTTP_1_0) {
                cVar.E(protocol.toString().length());
                cVar.A(protocol.toString());
            }
        }
        return cVar.X();
    }

    public static e g() {
        e eVarN = a.n();
        if (eVarN != null) {
            return eVarN;
        }
        b bVarN = b.n();
        if (bVarN != null) {
            return bVarN;
        }
        e eVarN2 = c.n();
        return eVarN2 != null ? eVarN2 : new e();
    }

    public static e h() {
        return f5284a;
    }

    public void a(SSLSocket sSLSocket) {
    }

    public f.b0.k.b c(X509TrustManager x509TrustManager) {
        return new f.b0.k.a(f.b0.k.e.b(x509TrustManager));
    }

    public void e(SSLSocket sSLSocket, String str, List<Protocol> list) {
    }

    public void f(Socket socket, InetSocketAddress inetSocketAddress, int i2) throws IOException {
        socket.connect(inetSocketAddress, i2);
    }

    public String i(SSLSocket sSLSocket) {
        return null;
    }

    public Object j(String str) {
        if (f5285b.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    public boolean k(String str) {
        return true;
    }

    public void l(int i2, String str, Throwable th) {
        f5285b.log(i2 == 5 ? Level.WARNING : Level.INFO, str, th);
    }

    public void m(String str, Object obj) {
        if (obj == null) {
            str = str + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }
        l(5, str, (Throwable) obj);
    }
}
