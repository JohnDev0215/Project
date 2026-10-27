package l6;

import android.os.Build;
import android.security.NetworkSecurityPolicy;
import c6.y;
import h5.p;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import kotlin.jvm.internal.l;
import m6.i;
import m6.j;
import m6.k;
import m6.l;

/* JADX INFO: compiled from: AndroidPlatform.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b extends h {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final boolean f7975f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final a f7976g = new a(null);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<k> f7977d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final m6.h f7978e;

    /* JADX INFO: compiled from: AndroidPlatform.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final h a() {
            if (b()) {
                return new b();
            }
            return null;
        }

        public final boolean b() {
            return b.f7975f;
        }
    }

    /* JADX INFO: renamed from: l6.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AndroidPlatform.kt */
    public static final class C0119b implements o6.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final X509TrustManager f7979a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Method f7980b;

        public C0119b(X509TrustManager trustManager, Method findByIssuerAndSignatureMethod) {
            l.e(trustManager, "trustManager");
            l.e(findByIssuerAndSignatureMethod, "findByIssuerAndSignatureMethod");
            this.f7979a = trustManager;
            this.f7980b = findByIssuerAndSignatureMethod;
        }

        @Override // o6.e
        public X509Certificate a(X509Certificate cert) {
            l.e(cert, "cert");
            try {
                Object objInvoke = this.f7980b.invoke(this.f7979a, cert);
                if (objInvoke != null) {
                    return ((TrustAnchor) objInvoke).getTrustedCert();
                }
                throw new NullPointerException("null cannot be cast to non-null type java.security.cert.TrustAnchor");
            } catch (IllegalAccessException e7) {
                throw new AssertionError("unable to get issues and signature", e7);
            } catch (InvocationTargetException unused) {
                return null;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof C0119b)) {
                return false;
            }
            C0119b c0119b = (C0119b) obj;
            return l.a(this.f7979a, c0119b.f7979a) && l.a(this.f7980b, c0119b.f7980b);
        }

        public int hashCode() {
            X509TrustManager x509TrustManager = this.f7979a;
            int iHashCode = (x509TrustManager != null ? x509TrustManager.hashCode() : 0) * 31;
            Method method = this.f7980b;
            return iHashCode + (method != null ? method.hashCode() : 0);
        }

        public String toString() {
            return "CustomTrustRootIndex(trustManager=" + this.f7979a + ", findByIssuerAndSignatureMethod=" + this.f7980b + ")";
        }
    }

    static {
        boolean z6 = false;
        if (h.f8004c.h() && Build.VERSION.SDK_INT < 30) {
            z6 = true;
        }
        f7975f = z6;
    }

    public b() {
        List listK = p.k(l.a.b(m6.l.f8432j, null, 1, null), new j(m6.f.f8415g.d()), new j(i.f8429b.a()), new j(m6.g.f8423b.a()));
        ArrayList arrayList = new ArrayList();
        for (Object obj : listK) {
            if (((k) obj).c()) {
                arrayList.add(obj);
            }
        }
        this.f7977d = arrayList;
        this.f7978e = m6.h.f8424d.a();
    }

    @Override // l6.h
    public o6.c c(X509TrustManager trustManager) {
        kotlin.jvm.internal.l.e(trustManager, "trustManager");
        m6.b bVarA = m6.b.f8407d.a(trustManager);
        return bVarA != null ? bVarA : super.c(trustManager);
    }

    @Override // l6.h
    public o6.e d(X509TrustManager trustManager) {
        kotlin.jvm.internal.l.e(trustManager, "trustManager");
        try {
            Method method = trustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            kotlin.jvm.internal.l.d(method, "method");
            method.setAccessible(true);
            return new C0119b(trustManager, method);
        } catch (NoSuchMethodException unused) {
            return super.d(trustManager);
        }
    }

    @Override // l6.h
    public void e(SSLSocket sslSocket, String str, List<y> protocols) {
        Object next;
        kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
        kotlin.jvm.internal.l.e(protocols, "protocols");
        Iterator<T> it = this.f7977d.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((k) next).a(sslSocket)) {
                    break;
                }
            }
        }
        k kVar = (k) next;
        if (kVar != null) {
            kVar.d(sslSocket, str, protocols);
        }
    }

    @Override // l6.h
    public void f(Socket socket, InetSocketAddress address, int i7) throws IOException {
        kotlin.jvm.internal.l.e(socket, "socket");
        kotlin.jvm.internal.l.e(address, "address");
        try {
            socket.connect(address, i7);
        } catch (ClassCastException e7) {
            throw e7;
        }
    }

    @Override // l6.h
    public String g(SSLSocket sslSocket) {
        Object next;
        kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
        Iterator<T> it = this.f7977d.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((k) next).a(sslSocket)) {
                break;
            }
        }
        k kVar = (k) next;
        if (kVar != null) {
            return kVar.b(sslSocket);
        }
        return null;
    }

    @Override // l6.h
    public Object h(String closer) {
        kotlin.jvm.internal.l.e(closer, "closer");
        return this.f7978e.a(closer);
    }

    @Override // l6.h
    public boolean i(String hostname) {
        kotlin.jvm.internal.l.e(hostname, "hostname");
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(hostname);
    }

    @Override // l6.h
    public void l(String message, Object obj) {
        kotlin.jvm.internal.l.e(message, "message");
        if (this.f7978e.b(obj)) {
            return;
        }
        h.k(this, message, 5, null, 4, null);
    }
}
