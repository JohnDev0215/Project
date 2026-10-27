package f.k0.l;

import f.b0;
import f.c0;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okio.Buffer;

/* JADX INFO: compiled from: Platform.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a */
    private static final f f5446a = i();

    /* JADX INFO: renamed from: b */
    private static final Logger f5447b = Logger.getLogger(b0.class.getName());

    public static List<String> b(List<c0> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            c0 c0Var = list.get(i2);
            if (c0Var != c0.HTTP_1_0) {
                arrayList.add(c0Var.toString());
            }
        }
        return arrayList;
    }

    static byte[] e(List<c0> list) {
        Buffer buffer = new Buffer();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            c0 c0Var = list.get(i2);
            if (c0Var != c0.HTTP_1_0) {
                buffer.J(c0Var.toString().length());
                buffer.D(c0Var.toString());
            }
        }
        return buffer.A();
    }

    private static f i() {
        c cVarR;
        f fVarT = a.t();
        if (fVarT != null) {
            return fVarT;
        }
        f fVarT2 = b.t();
        if (fVarT2 != null) {
            return fVarT2;
        }
        if (o() && (cVarR = c.r()) != null) {
            return cVarR;
        }
        e eVarR = e.r();
        if (eVarR != null) {
            return eVarR;
        }
        f fVarR = d.r();
        return fVarR != null ? fVarR : new f();
    }

    public static f j() {
        return f5446a;
    }

    public static boolean o() {
        if ("conscrypt".equals(f.k0.e.q("okhttp.platform", null))) {
            return true;
        }
        return "Conscrypt".equals(Security.getProviders()[0].getName());
    }

    public void a(SSLSocket sSLSocket) {
    }

    public f.k0.n.c c(X509TrustManager x509TrustManager) {
        return new f.k0.n.a(d(x509TrustManager));
    }

    public f.k0.n.e d(X509TrustManager x509TrustManager) {
        return new f.k0.n.b(x509TrustManager.getAcceptedIssuers());
    }

    public void f(SSLSocketFactory sSLSocketFactory) {
    }

    public void g(SSLSocket sSLSocket, @Nullable String str, List<c0> list) {
    }

    public void h(Socket socket, InetSocketAddress inetSocketAddress, int i2) throws IOException {
        socket.connect(inetSocketAddress, i2);
    }

    public SSLContext k() {
        try {
            return SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException("No TLS provider", e2);
        }
    }

    @Nullable
    public String l(SSLSocket sSLSocket) {
        return null;
    }

    @Nullable
    public Object m(String str) {
        if (f5447b.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    public boolean n(String str) {
        return true;
    }

    public void p(int i2, String str, @Nullable Throwable th) {
        f5447b.log(i2 == 5 ? Level.WARNING : Level.INFO, str, th);
    }

    public void q(String str, Object obj) {
        if (obj == null) {
            str = str + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }
        p(5, str, (Throwable) obj);
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
