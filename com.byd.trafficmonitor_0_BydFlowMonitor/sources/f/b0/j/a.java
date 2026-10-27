package f.b0.j;

import android.util.Log;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;

/* JADX INFO: compiled from: AndroidPlatform.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Class<?> f5260c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d<Socket> f5261d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d<Socket> f5262e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final d<Socket> f5263f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final d<Socket> f5264g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final b f5265h = b.b();

    /* JADX INFO: renamed from: f.b0.j.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AndroidPlatform.java */
    public static final class C0104a extends f.b0.k.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f5266a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Method f5267b;

        public C0104a(Object obj, Method method) {
            this.f5266a = obj;
            this.f5267b = method;
        }

        @Override // f.b0.k.b
        public List<Certificate> a(List<Certificate> list, String str) throws SSLPeerUnverifiedException {
            try {
                return (List) this.f5267b.invoke(this.f5266a, (X509Certificate[]) list.toArray(new X509Certificate[list.size()]), "RSA", str);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (InvocationTargetException e3) {
                SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e3.getMessage());
                sSLPeerUnverifiedException.initCause(e3);
                throw sSLPeerUnverifiedException;
            }
        }

        public boolean equals(Object obj) {
            return obj instanceof C0104a;
        }

        public int hashCode() {
            return 0;
        }
    }

    /* JADX INFO: compiled from: AndroidPlatform.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Method f5268a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Method f5269b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Method f5270c;

        public b(Method method, Method method2, Method method3) {
            this.f5268a = method;
            this.f5269b = method2;
            this.f5270c = method3;
        }

        public static b b() throws NoSuchMethodException {
            Method method;
            Method method2;
            Method method3 = null;
            try {
                Class<?> cls = Class.forName("dalvik.system.CloseGuard");
                Method method4 = cls.getMethod("get", new Class[0]);
                method2 = cls.getMethod("open", String.class);
                method = cls.getMethod("warnIfOpen", new Class[0]);
                method3 = method4;
            } catch (Exception unused) {
                method = null;
                method2 = null;
            }
            return new b(method3, method2, method);
        }

        public Object a(String str) {
            Method method = this.f5268a;
            if (method != null) {
                try {
                    Object objInvoke = method.invoke(null, new Object[0]);
                    this.f5269b.invoke(objInvoke, str);
                    return objInvoke;
                } catch (Exception unused) {
                }
            }
            return null;
        }

        public boolean c(Object obj) {
            if (obj == null) {
                return false;
            }
            try {
                this.f5270c.invoke(obj, new Object[0]);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }
    }

    public a(Class<?> cls, d<Socket> dVar, d<Socket> dVar2, d<Socket> dVar3, d<Socket> dVar4) {
        this.f5260c = cls;
        this.f5261d = dVar;
        this.f5262e = dVar2;
        this.f5263f = dVar3;
        this.f5264g = dVar4;
    }

    public static e n() {
        Class<?> cls;
        d dVar;
        d dVar2;
        try {
            try {
                cls = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
            } catch (ClassNotFoundException unused) {
                cls = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
            }
            Class<?> cls2 = cls;
            d dVar3 = new d(null, "setUseSessionTickets", Boolean.TYPE);
            d dVar4 = new d(null, "setHostname", String.class);
            try {
                Class.forName("android.net.Network");
                dVar = new d(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
                try {
                    dVar2 = new d(null, "setAlpnProtocols", byte[].class);
                } catch (ClassNotFoundException unused2) {
                    dVar2 = null;
                }
            } catch (ClassNotFoundException unused3) {
                dVar = null;
            }
            return new a(cls2, dVar3, dVar4, dVar, dVar2);
        } catch (ClassNotFoundException unused4) {
            return null;
        }
    }

    @Override // f.b0.j.e
    public f.b0.k.b c(X509TrustManager x509TrustManager) {
        try {
            Class<?> cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new C0104a(cls.getConstructor(X509TrustManager.class).newInstance(x509TrustManager), cls.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class));
        } catch (Exception unused) {
            return super.c(x509TrustManager);
        }
    }

    @Override // f.b0.j.e
    public void e(SSLSocket sSLSocket, String str, List<Protocol> list) {
        if (str != null) {
            this.f5261d.e(sSLSocket, Boolean.TRUE);
            this.f5262e.e(sSLSocket, str);
        }
        d<Socket> dVar = this.f5264g;
        if (dVar == null || !dVar.g(sSLSocket)) {
            return;
        }
        this.f5264g.f(sSLSocket, e.d(list));
    }

    @Override // f.b0.j.e
    public void f(Socket socket, InetSocketAddress inetSocketAddress, int i2) throws IOException {
        try {
            socket.connect(inetSocketAddress, i2);
        } catch (AssertionError e2) {
            if (!f.b0.c.t(e2)) {
                throw e2;
            }
            throw new IOException(e2);
        } catch (SecurityException e3) {
            IOException iOException = new IOException("Exception in connect");
            iOException.initCause(e3);
            throw iOException;
        }
    }

    @Override // f.b0.j.e
    public String i(SSLSocket sSLSocket) {
        byte[] bArr;
        d<Socket> dVar = this.f5263f;
        if (dVar == null || !dVar.g(sSLSocket) || (bArr = (byte[]) this.f5263f.f(sSLSocket, new Object[0])) == null) {
            return null;
        }
        return new String(bArr, f.b0.c.j);
    }

    @Override // f.b0.j.e
    public Object j(String str) {
        return this.f5265h.a(str);
    }

    @Override // f.b0.j.e
    public boolean k(String str) {
        try {
            Class<?> cls = Class.forName("android.security.NetworkSecurityPolicy");
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", String.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), str)).booleanValue();
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return super.k(str);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused2) {
            throw new AssertionError();
        }
    }

    @Override // f.b0.j.e
    public void l(int i2, String str, Throwable th) {
        int iMin;
        int i3 = i2 != 5 ? 3 : 5;
        if (th != null) {
            str = str + '\n' + Log.getStackTraceString(th);
        }
        int i4 = 0;
        int length = str.length();
        while (i4 < length) {
            int iIndexOf = str.indexOf(10, i4);
            if (iIndexOf == -1) {
                iIndexOf = length;
            }
            while (true) {
                iMin = Math.min(iIndexOf, i4 + 4000);
                Log.println(i3, "OkHttp", str.substring(i4, iMin));
                if (iMin >= iIndexOf) {
                    break;
                } else {
                    i4 = iMin;
                }
            }
            i4 = iMin + 1;
        }
    }

    @Override // f.b0.j.e
    public void m(String str, Object obj) {
        if (this.f5265h.c(obj)) {
            return;
        }
        l(5, str, null);
    }
}
