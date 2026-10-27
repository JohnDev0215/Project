package e.d0.j;

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
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;

/* JADX INFO: compiled from: AndroidPlatform.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Class<?> f3522c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d<Socket> f3523d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d<Socket> f3524e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final d<Socket> f3525f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final d<Socket> f3526g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final b f3527h = b.b();

    /* JADX INFO: renamed from: e.d0.j.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AndroidPlatform.java */
    public static final class C0084a extends e.d0.l.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f3528a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Method f3529b;

        public C0084a(Object obj, Method method) {
            this.f3528a = obj;
            this.f3529b = method;
        }

        @Override // e.d0.l.b
        public List<Certificate> a(List<Certificate> list, String str) throws SSLPeerUnverifiedException {
            try {
                return (List) this.f3529b.invoke(this.f3528a, (X509Certificate[]) list.toArray(new X509Certificate[list.size()]), "RSA", str);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (InvocationTargetException e3) {
                SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e3.getMessage());
                sSLPeerUnverifiedException.initCause(e3);
                throw sSLPeerUnverifiedException;
            }
        }

        public boolean equals(Object obj) {
            return obj instanceof C0084a;
        }

        public int hashCode() {
            return 0;
        }
    }

    /* JADX INFO: compiled from: AndroidPlatform.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Method f3530a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Method f3531b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Method f3532c;

        public b(Method method, Method method2, Method method3) {
            this.f3530a = method;
            this.f3531b = method2;
            this.f3532c = method3;
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
            Method method = this.f3530a;
            if (method != null) {
                try {
                    Object objInvoke = method.invoke(null, new Object[0]);
                    this.f3531b.invoke(objInvoke, str);
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
                this.f3532c.invoke(obj, new Object[0]);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }
    }

    public a(Class<?> cls, d<Socket> dVar, d<Socket> dVar2, d<Socket> dVar3, d<Socket> dVar4) {
        this.f3522c = cls;
        this.f3523d = dVar;
        this.f3524e = dVar2;
        this.f3525f = dVar3;
        this.f3526g = dVar4;
    }

    public static e p() {
        Class<?> cls;
        d dVar;
        d dVar2;
        try {
            try {
                cls = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
            } catch (ClassNotFoundException unused) {
                return null;
            }
        } catch (ClassNotFoundException unused2) {
            cls = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
        }
        Class<?> cls2 = cls;
        d dVar3 = new d(null, "setUseSessionTickets", Boolean.TYPE);
        d dVar4 = new d(null, "setHostname", String.class);
        try {
            Class.forName("android.net.Network");
            dVar = new d(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
        } catch (ClassNotFoundException unused3) {
            dVar = null;
        }
        try {
            dVar2 = new d(null, "setAlpnProtocols", byte[].class);
        } catch (ClassNotFoundException unused4) {
            dVar2 = null;
        }
        return new a(cls2, dVar3, dVar4, dVar, dVar2);
    }

    @Override // e.d0.j.e
    public e.d0.l.b c(X509TrustManager x509TrustManager) {
        try {
            Class<?> cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new C0084a(cls.getConstructor(X509TrustManager.class).newInstance(x509TrustManager), cls.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class));
        } catch (Exception unused) {
            return super.c(x509TrustManager);
        }
    }

    @Override // e.d0.j.e
    public void e(SSLSocket sSLSocket, String str, List<Protocol> list) {
        if (str != null) {
            this.f3523d.e(sSLSocket, Boolean.TRUE);
            this.f3524e.e(sSLSocket, str);
        }
        d<Socket> dVar = this.f3526g;
        if (dVar == null || !dVar.g(sSLSocket)) {
            return;
        }
        this.f3526g.f(sSLSocket, e.d(list));
    }

    @Override // e.d0.j.e
    public void f(Socket socket, InetSocketAddress inetSocketAddress, int i2) throws IOException {
        try {
            socket.connect(inetSocketAddress, i2);
        } catch (AssertionError e2) {
            if (!e.d0.c.s(e2)) {
                throw e2;
            }
            throw new IOException(e2);
        } catch (SecurityException e3) {
            IOException iOException = new IOException("Exception in connect");
            iOException.initCause(e3);
            throw iOException;
        }
    }

    @Override // e.d0.j.e
    public String i(SSLSocket sSLSocket) {
        byte[] bArr;
        d<Socket> dVar = this.f3525f;
        if (dVar == null || !dVar.g(sSLSocket) || (bArr = (byte[]) this.f3525f.f(sSLSocket, new Object[0])) == null) {
            return null;
        }
        return new String(bArr, e.d0.c.j);
    }

    @Override // e.d0.j.e
    public Object j(String str) {
        return this.f3527h.a(str);
    }

    @Override // e.d0.j.e
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

    @Override // e.d0.j.e
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

    @Override // e.d0.j.e
    public void m(String str, Object obj) {
        if (this.f3527h.c(obj)) {
            return;
        }
        l(5, str, null);
    }

    @Override // e.d0.j.e
    public X509TrustManager o(SSLSocketFactory sSLSocketFactory) {
        Object objN = e.n(sSLSocketFactory, this.f3522c, "sslParameters");
        if (objN == null) {
            try {
                objN = e.n(sSLSocketFactory, Class.forName("com.google.android.gms.org.conscrypt.SSLParametersImpl", false, sSLSocketFactory.getClass().getClassLoader()), "sslParameters");
            } catch (ClassNotFoundException unused) {
                return super.o(sSLSocketFactory);
            }
        }
        X509TrustManager x509TrustManager = (X509TrustManager) e.n(objN, X509TrustManager.class, "x509TrustManager");
        return x509TrustManager != null ? x509TrustManager : (X509TrustManager) e.n(objN, X509TrustManager.class, "trustManager");
    }
}
