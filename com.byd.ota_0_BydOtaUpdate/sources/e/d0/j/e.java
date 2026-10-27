package e.d0.j;

import e.w;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;

/* JADX INFO: compiled from: Platform.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f3546a = g();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Logger f3547b = Logger.getLogger(w.class.getName());

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
        f.c cVar = new f.c();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Protocol protocol = list.get(i2);
            if (protocol != Protocol.HTTP_1_0) {
                cVar.A(protocol.toString().length());
                cVar.w(protocol.toString());
            }
        }
        return cVar.Q();
    }

    public static e g() {
        e eVarP = a.p();
        if (eVarP != null) {
            return eVarP;
        }
        b bVarP = b.p();
        if (bVarP != null) {
            return bVarP;
        }
        e eVarP2 = c.p();
        return eVarP2 != null ? eVarP2 : new e();
    }

    public static e h() {
        return f3546a;
    }

    public static <T> T n(Object obj, Class<T> cls, String str) {
        Object objN;
        for (Class<?> superclass = obj.getClass(); superclass != Object.class; superclass = superclass.getSuperclass()) {
            try {
                Field declaredField = superclass.getDeclaredField(str);
                declaredField.setAccessible(true);
                Object obj2 = declaredField.get(obj);
                if (obj2 != null && cls.isInstance(obj2)) {
                    return cls.cast(obj2);
                }
                return null;
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (NoSuchFieldException unused2) {
            }
        }
        if (str.equals("delegate") || (objN = n(obj, Object.class, "delegate")) == null) {
            return null;
        }
        return (T) n(objN, cls, str);
    }

    public void a(SSLSocket sSLSocket) {
    }

    public e.d0.l.b c(X509TrustManager x509TrustManager) {
        return new e.d0.l.a(e.d0.l.e.b(x509TrustManager));
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
        if (f3547b.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    public boolean k(String str) {
        return true;
    }

    public void l(int i2, String str, Throwable th) {
        f3547b.log(i2 == 5 ? Level.WARNING : Level.INFO, str, th);
    }

    public void m(String str, Object obj) {
        if (obj == null) {
            str = str + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }
        l(5, str, (Throwable) obj);
    }

    public X509TrustManager o(SSLSocketFactory sSLSocketFactory) {
        try {
            Object objN = n(sSLSocketFactory, Class.forName("sun.security.ssl.SSLContextImpl"), "context");
            if (objN == null) {
                return null;
            }
            return (X509TrustManager) n(objN, X509TrustManager.class, "trustManager");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
