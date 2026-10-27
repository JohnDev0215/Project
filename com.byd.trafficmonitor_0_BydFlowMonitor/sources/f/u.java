package f;

import f.q;
import f.y;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;

/* JADX INFO: compiled from: OkHttpClient.java */
/* JADX INFO: loaded from: classes.dex */
public class u implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final List<Protocol> f5395b = f.b0.c.o(Protocol.HTTP_2, Protocol.HTTP_1_1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final List<k> f5396c = f.b0.c.o(k.f5340b, k.f5341c, k.f5342d);
    public final int A;
    public final int B;
    public final int C;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final n f5397d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Proxy f5398e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List<Protocol> f5399f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final List<k> f5400g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List<r> f5401h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List<r> f5402i;
    public final ProxySelector j;
    public final m k;
    public final c l;
    public final f.b0.e.d m;
    public final SocketFactory n;
    public final SSLSocketFactory o;
    public final f.b0.k.b p;
    public final HostnameVerifier q;
    public final g r;
    public final f.b s;
    public final f.b t;
    public final j u;
    public final o v;
    public final boolean w;
    public final boolean x;
    public final boolean y;
    public final int z;

    /* JADX INFO: compiled from: OkHttpClient.java */
    public static class a extends f.b0.a {
        @Override // f.b0.a
        public void a(q.a aVar, String str) {
            aVar.b(str);
        }

        @Override // f.b0.a
        public void b(q.a aVar, String str, String str2) {
            aVar.c(str, str2);
        }

        @Override // f.b0.a
        public void c(k kVar, SSLSocket sSLSocket, boolean z) {
            kVar.a(sSLSocket, z);
        }

        @Override // f.b0.a
        public int d(y.a aVar) {
            return aVar.f5448c;
        }

        @Override // f.b0.a
        public boolean e(j jVar, f.b0.f.c cVar) {
            return jVar.b(cVar);
        }

        @Override // f.b0.a
        public Socket f(j jVar, f.a aVar, f.b0.f.f fVar) {
            return jVar.c(aVar, fVar);
        }

        @Override // f.b0.a
        public f.b0.f.c g(j jVar, f.a aVar, f.b0.f.f fVar) {
            return jVar.d(aVar, fVar);
        }

        @Override // f.b0.a
        public void h(j jVar, f.b0.f.c cVar) {
            jVar.f(cVar);
        }

        @Override // f.b0.a
        public f.b0.f.d i(j jVar) {
            return jVar.f5336f;
        }
    }

    /* JADX INFO: compiled from: OkHttpClient.java */
    public static final class b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Proxy f5404b;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public f.b0.e.d f5411i;
        public SSLSocketFactory k;
        public f.b0.k.b l;
        public f.b o;
        public f.b p;
        public j q;
        public o r;
        public boolean s;
        public boolean t;
        public boolean u;
        public int v;
        public int w;
        public int x;
        public int y;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final List<r> f5407e = new ArrayList();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final List<r> f5408f = new ArrayList();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public n f5403a = new n();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public List<Protocol> f5405c = u.f5395b;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public List<k> f5406d = u.f5396c;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ProxySelector f5409g = ProxySelector.getDefault();

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public m f5410h = m.f5360a;
        public SocketFactory j = SocketFactory.getDefault();
        public HostnameVerifier m = f.b0.k.d.f5294a;
        public g n = g.f5314a;

        public b() {
            f.b bVar = f.b.f5009a;
            this.o = bVar;
            this.p = bVar;
            this.q = new j();
            this.r = o.f5368a;
            this.s = true;
            this.t = true;
            this.u = true;
            this.v = 10000;
            this.w = 10000;
            this.x = 10000;
            this.y = 0;
        }

        public static int b(String str, long j, TimeUnit timeUnit) {
            if (j < 0) {
                throw new IllegalArgumentException(str + " < 0");
            }
            Objects.requireNonNull(timeUnit, "unit == null");
            long millis = timeUnit.toMillis(j);
            if (millis > 2147483647L) {
                throw new IllegalArgumentException(str + " too large.");
            }
            if (millis != 0 || j <= 0) {
                return (int) millis;
            }
            throw new IllegalArgumentException(str + " too small.");
        }

        public u a() {
            return new u(this);
        }

        public b c(long j, TimeUnit timeUnit) {
            this.v = b("timeout", j, timeUnit);
            return this;
        }

        public b d(boolean z) {
            this.t = z;
            return this;
        }

        public b e(HostnameVerifier hostnameVerifier) {
            Objects.requireNonNull(hostnameVerifier, "hostnameVerifier == null");
            this.m = hostnameVerifier;
            return this;
        }

        public List<r> f() {
            return this.f5408f;
        }

        public b g(long j, TimeUnit timeUnit) {
            this.w = b("timeout", j, timeUnit);
            return this;
        }

        public b h(long j, TimeUnit timeUnit) {
            this.x = b("timeout", j, timeUnit);
            return this;
        }
    }

    static {
        f.b0.a.f5010a = new a();
    }

    public u() {
        this(new b());
    }

    public final X509TrustManager A() {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
                return (X509TrustManager) trustManagers[0];
            }
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        } catch (GeneralSecurityException unused) {
            throw new AssertionError();
        }
    }

    public int B() {
        return this.B;
    }

    public f.b a() {
        return this.t;
    }

    public g b() {
        return this.r;
    }

    public int c() {
        return this.z;
    }

    public j d() {
        return this.u;
    }

    public List<k> e() {
        return this.f5400g;
    }

    public m f() {
        return this.k;
    }

    public n g() {
        return this.f5397d;
    }

    public o h() {
        return this.v;
    }

    public boolean i() {
        return this.x;
    }

    public boolean j() {
        return this.w;
    }

    public HostnameVerifier k() {
        return this.q;
    }

    public List<r> l() {
        return this.f5401h;
    }

    public f.b0.e.d m() {
        if (this.l == null) {
            return this.m;
        }
        throw null;
    }

    public List<r> n() {
        return this.f5402i;
    }

    public e o(w wVar) {
        return new v(this, wVar, false);
    }

    public List<Protocol> p() {
        return this.f5399f;
    }

    public Proxy q() {
        return this.f5398e;
    }

    public f.b r() {
        return this.s;
    }

    public ProxySelector s() {
        return this.j;
    }

    public int t() {
        return this.A;
    }

    public boolean u() {
        return this.y;
    }

    public SocketFactory v() {
        return this.n;
    }

    public SSLSocketFactory x() {
        return this.o;
    }

    public final SSLSocketFactory y(X509TrustManager x509TrustManager) {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, new TrustManager[]{x509TrustManager}, null);
            return sSLContext.getSocketFactory();
        } catch (GeneralSecurityException unused) {
            throw new AssertionError();
        }
    }

    public u(b bVar) {
        boolean z;
        this.f5397d = bVar.f5403a;
        this.f5398e = bVar.f5404b;
        this.f5399f = bVar.f5405c;
        List<k> list = bVar.f5406d;
        this.f5400g = list;
        this.f5401h = f.b0.c.n(bVar.f5407e);
        this.f5402i = f.b0.c.n(bVar.f5408f);
        this.j = bVar.f5409g;
        this.k = bVar.f5410h;
        this.m = bVar.f5411i;
        this.n = bVar.j;
        Iterator<k> it = list.iterator();
        loop0: while (true) {
            z = false;
            while (it.hasNext()) {
                z = (z || it.next().d()) ? true : z;
            }
        }
        SSLSocketFactory sSLSocketFactory = bVar.k;
        if (sSLSocketFactory == null && z) {
            X509TrustManager x509TrustManagerA = A();
            this.o = y(x509TrustManagerA);
            this.p = f.b0.k.b.b(x509TrustManagerA);
        } else {
            this.o = sSLSocketFactory;
            this.p = bVar.l;
        }
        this.q = bVar.m;
        this.r = bVar.n.f(this.p);
        this.s = bVar.o;
        this.t = bVar.p;
        this.u = bVar.q;
        this.v = bVar.r;
        this.w = bVar.s;
        this.x = bVar.t;
        this.y = bVar.u;
        this.z = bVar.v;
        this.A = bVar.w;
        this.B = bVar.x;
        this.C = bVar.y;
    }
}
