package f.k0.l;

import android.os.Build;
import android.util.Log;
import f.c0;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: compiled from: AndroidPlatform.java */
/* JADX INFO: loaded from: classes.dex */
class b extends f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Class<?> f5422c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Class<?> f5423d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Method f5424e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Method f5425f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Method f5426g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Method f5427h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final C0101b f5428i = C0101b.b();

    /* JADX INFO: compiled from: AndroidPlatform.java */
    static final class a extends f.k0.n.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Object f5429a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Method f5430b;

        a(Object obj, Method method) {
            this.f5429a = obj;
            this.f5430b = method;
        }

        @Override // f.k0.n.c
        public List<Certificate> a(List<Certificate> list, String str) throws SSLPeerUnverifiedException {
            try {
                return (List) this.f5430b.invoke(this.f5429a, (X509Certificate[]) list.toArray(new X509Certificate[list.size()]), "RSA", str);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (InvocationTargetException e3) {
                SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e3.getMessage());
                sSLPeerUnverifiedException.initCause(e3);
                throw sSLPeerUnverifiedException;
            }
        }

        public boolean equals(Object obj) {
            return obj instanceof a;
        }

        public int hashCode() {
            return 0;
        }
    }

    /* JADX INFO: renamed from: f.k0.l.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AndroidPlatform.java */
    static final class C0101b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f5431a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Method f5432b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Method f5433c;

        C0101b(Method method, Method method2, Method method3) {
            this.f5431a = method;
            this.f5432b = method2;
            this.f5433c = method3;
        }

        static C0101b b() throws NoSuchMethodException {
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
            return new C0101b(method3, method2, method);
        }

        Object a(String str) {
            Method method = this.f5431a;
            if (method != null) {
                try {
                    Object objInvoke = method.invoke(null, new Object[0]);
                    this.f5432b.invoke(objInvoke, str);
                    return objInvoke;
                } catch (Exception unused) {
                }
            }
            return null;
        }

        boolean c(Object obj) {
            if (obj == null) {
                return false;
            }
            try {
                this.f5433c.invoke(obj, new Object[0]);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: AndroidPlatform.java */
    static final class c implements f.k0.n.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final X509TrustManager f5434a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Method f5435b;

        c(X509TrustManager x509TrustManager, Method method) {
            this.f5435b = method;
            this.f5434a = x509TrustManager;
        }

        @Override // f.k0.n.e
        public X509Certificate a(X509Certificate x509Certificate) {
            try {
                TrustAnchor trustAnchor = (TrustAnchor) this.f5435b.invoke(this.f5434a, x509Certificate);
                if (trustAnchor != null) {
                    return trustAnchor.getTrustedCert();
                }
                return null;
            } catch (IllegalAccessException e2) {
                throw new AssertionError("unable to get issues and signature", e2);
            } catch (InvocationTargetException unused) {
                return null;
            }
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return this.f5434a.equals(cVar.f5434a) && this.f5435b.equals(cVar.f5435b);
        }

        public int hashCode() {
            return this.f5434a.hashCode() + (this.f5435b.hashCode() * 31);
        }
    }

    b(Class<?> cls, Class<?> cls2, Method method, Method method2, Method method3, Method method4) {
        this.f5422c = cls;
        this.f5423d = cls2;
        this.f5424e = method;
        this.f5425f = method2;
        this.f5426g = method3;
        this.f5427h = method4;
    }

    private boolean r(String str, Class<?> cls, Object obj) {
        try {
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", new Class[0]).invoke(obj, new Object[0])).booleanValue();
        } catch (NoSuchMethodException unused) {
            return super.n(str);
        }
    }

    private boolean s(String str, Class<?> cls, Object obj) {
        try {
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", String.class).invoke(obj, str)).booleanValue();
        } catch (NoSuchMethodException unused) {
            return r(str, cls, obj);
        }
    }

    @Nullable
    public static f t() {
        if (u() == 0) {
            return null;
        }
        try {
            Class<?> cls = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
            Class<?> cls2 = Class.forName("com.android.org.conscrypt.OpenSSLSocketImpl");
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    return new b(cls, cls2, cls2.getDeclaredMethod("setUseSessionTickets", Boolean.TYPE), cls2.getMethod("setHostname", String.class), cls2.getMethod("getAlpnSelectedProtocol", new Class[0]), cls2.getMethod("setAlpnProtocols", byte[].class));
                } catch (NoSuchMethodException unused) {
                }
            }
            throw new IllegalStateException("Expected Android API level 21+ but was " + Build.VERSION.SDK_INT);
        } catch (ClassNotFoundException unused2) {
            return null;
        }
    }

    static int u() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (NoClassDefFoundError unused) {
            return 0;
        }
    }

    @Override // f.k0.l.f
    public f.k0.n.c c(X509TrustManager x509TrustManager) {
        try {
            Class<?> cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new a(cls.getConstructor(X509TrustManager.class).newInstance(x509TrustManager), cls.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class));
        } catch (Exception unused) {
            return super.c(x509TrustManager);
        }
    }

    @Override // f.k0.l.f
    public f.k0.n.e d(X509TrustManager x509TrustManager) {
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            return new c(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            return super.d(x509TrustManager);
        }
    }

    @Override // f.k0.l.f
    public void g(SSLSocket sSLSocket, String str, List<c0> list) {
        if (this.f5423d.isInstance(sSLSocket)) {
            if (str != null) {
                try {
                    this.f5424e.invoke(sSLSocket, Boolean.TRUE);
                    this.f5425f.invoke(sSLSocket, str);
                } catch (IllegalAccessException | InvocationTargetException e2) {
                    throw new AssertionError(e2);
                }
            }
            this.f5427h.invoke(sSLSocket, f.e(list));
        }
    }

    @Override // f.k0.l.f
    public void h(Socket socket, InetSocketAddress inetSocketAddress, int i2) throws IOException {
        try {
            socket.connect(inetSocketAddress, i2);
        } catch (AssertionError e2) {
            if (!f.k0.e.z(e2)) {
                throw e2;
            }
            throw new IOException(e2);
        } catch (ClassCastException e3) {
            if (Build.VERSION.SDK_INT != 26) {
                throw e3;
            }
            throw new IOException("Exception in connect", e3);
        }
    }

    @Override // f.k0.l.f
    public SSLContext k() {
        boolean z = true;
        try {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 < 16 || i2 >= 22) {
                z = false;
            }
        } catch (NoClassDefFoundError unused) {
        }
        if (z) {
            try {
                return SSLContext.getInstance("TLSv1.2");
            } catch (NoSuchAlgorithmException unused2) {
            }
        }
        try {
            return SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException("No TLS provider", e2);
        }
    }

    @Override // f.k0.l.f
    @Nullable
    public String l(SSLSocket sSLSocket) {
        if (!this.f5423d.isInstance(sSLSocket)) {
            return null;
        }
        try {
            byte[] bArr = (byte[]) this.f5426g.invoke(sSLSocket, new Object[0]);
            if (bArr != null) {
                return new String(bArr, StandardCharsets.UTF_8);
            }
            return null;
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // f.k0.l.f
    @Nullable
    public Object m(String str) {
        return this.f5428i.a(str);
    }

    @Override // f.k0.l.f
    public boolean n(String str) {
        try {
            Class<?> cls = Class.forName("android.security.NetworkSecurityPolicy");
            return s(str, cls, cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]));
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return super.n(str);
        } catch (IllegalAccessException e2) {
            e = e2;
            throw new AssertionError("unable to determine cleartext support", e);
        } catch (IllegalArgumentException e3) {
            e = e3;
            throw new AssertionError("unable to determine cleartext support", e);
        } catch (InvocationTargetException e4) {
            e = e4;
            throw new AssertionError("unable to determine cleartext support", e);
        }
    }

    @Override // f.k0.l.f
    public void p(int i2, String str, @Nullable Throwable th) {
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

    @Override // f.k0.l.f
    public void q(String str, Object obj) {
        if (this.f5428i.c(obj)) {
            return;
        }
        p(5, str, null);
    }
}
