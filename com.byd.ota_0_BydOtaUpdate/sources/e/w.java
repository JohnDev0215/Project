package e;

import e.a0;
import e.e;
import e.p;
import e.s;
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
import javax.annotation.Nullable;
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
public class w implements Cloneable, e.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final List<Protocol> f3656c = e.d0.c.o(Protocol.HTTP_2, Protocol.HTTP_1_1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final List<k> f3657d = e.d0.c.o(k.f3594b, k.f3596d);
    public final List<k> E3;
    public final List<t> F3;
    public final List<t> G3;
    public final p.c H3;
    public final ProxySelector I3;
    public final m J3;

    @Nullable
    public final c K3;

    @Nullable
    public final e.d0.e.d L3;
    public final SocketFactory M3;

    @Nullable
    public final SSLSocketFactory N3;

    @Nullable
    public final e.d0.l.b O3;
    public final HostnameVerifier P3;
    public final g Q3;
    public final e.b R3;
    public final e.b S3;
    public final j T3;
    public final o U3;
    public final boolean V3;
    public final boolean W3;
    public final boolean X3;
    public final int Y3;
    public final int Z3;
    public final int a4;
    public final int b4;
    public final n s;

    @Nullable
    public final Proxy x;
    public final List<Protocol> y;

    /* JADX INFO: compiled from: OkHttpClient.java */
    public class a extends e.d0.a {
        @Override // e.d0.a
        public void a(s.a aVar, String str) {
            aVar.b(str);
        }

        @Override // e.d0.a
        public void b(s.a aVar, String str, String str2) {
            aVar.c(str, str2);
        }

        @Override // e.d0.a
        public void c(k kVar, SSLSocket sSLSocket, boolean z) {
            kVar.a(sSLSocket, z);
        }

        @Override // e.d0.a
        public int d(a0.a aVar) {
            return aVar.f3302c;
        }

        @Override // e.d0.a
        public boolean e(j jVar, e.d0.f.c cVar) {
            return jVar.b(cVar);
        }

        @Override // e.d0.a
        public Socket f(j jVar, e.a aVar, e.d0.f.f fVar) {
            return jVar.c(aVar, fVar);
        }

        @Override // e.d0.a
        public boolean g(e.a aVar, e.a aVar2) {
            return aVar.d(aVar2);
        }

        @Override // e.d0.a
        public e.d0.f.c h(j jVar, e.a aVar, e.d0.f.f fVar, c0 c0Var) {
            return jVar.d(aVar, fVar, c0Var);
        }

        @Override // e.d0.a
        public void i(j jVar, e.d0.f.c cVar) {
            jVar.f(cVar);
        }

        @Override // e.d0.a
        public e.d0.f.d j(j jVar) {
            return jVar.f3590f;
        }
    }

    static {
        e.d0.a.f3334a = new a();
    }

    public w() {
        this(new b());
    }

    public boolean A() {
        return this.X3;
    }

    public SocketFactory B() {
        return this.M3;
    }

    public SSLSocketFactory C() {
        return this.N3;
    }

    public final SSLSocketFactory D(X509TrustManager x509TrustManager) {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, new TrustManager[]{x509TrustManager}, null);
            return sSLContext.getSocketFactory();
        } catch (GeneralSecurityException unused) {
            throw new AssertionError();
        }
    }

    public final X509TrustManager E() {
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

    public int F() {
        return this.a4;
    }

    @Override // e.e.a
    public e a(y yVar) {
        return new x(this, yVar, false);
    }

    public e.b b() {
        return this.S3;
    }

    public g e() {
        return this.Q3;
    }

    public int f() {
        return this.Y3;
    }

    public j g() {
        return this.T3;
    }

    public List<k> h() {
        return this.E3;
    }

    public m i() {
        return this.J3;
    }

    public n j() {
        return this.s;
    }

    public o k() {
        return this.U3;
    }

    public p.c l() {
        return this.H3;
    }

    public boolean m() {
        return this.W3;
    }

    public boolean n() {
        return this.V3;
    }

    public HostnameVerifier o() {
        return this.P3;
    }

    public List<t> p() {
        return this.F3;
    }

    public e.d0.e.d q() {
        if (this.K3 == null) {
            return this.L3;
        }
        throw null;
    }

    public List<t> r() {
        return this.G3;
    }

    public b t() {
        return new b(this);
    }

    public List<Protocol> u() {
        return this.y;
    }

    public Proxy w() {
        return this.x;
    }

    public e.b x() {
        return this.R3;
    }

    public ProxySelector y() {
        return this.I3;
    }

    public int z() {
        return this.Z3;
    }

    public w(b bVar) {
        boolean z;
        this.s = bVar.f3658a;
        this.x = bVar.f3659b;
        this.y = bVar.f3660c;
        List<k> list = bVar.f3661d;
        this.E3 = list;
        this.F3 = e.d0.c.n(bVar.f3662e);
        this.G3 = e.d0.c.n(bVar.f3663f);
        this.H3 = bVar.f3664g;
        this.I3 = bVar.f3665h;
        this.J3 = bVar.f3666i;
        this.L3 = bVar.j;
        this.M3 = bVar.k;
        Iterator<k> it = list.iterator();
        loop0: while (true) {
            z = false;
            while (it.hasNext()) {
                z = (z || it.next().d()) ? true : z;
            }
        }
        SSLSocketFactory sSLSocketFactory = bVar.l;
        if (sSLSocketFactory == null && z) {
            X509TrustManager x509TrustManagerE = E();
            this.N3 = D(x509TrustManagerE);
            this.O3 = e.d0.l.b.b(x509TrustManagerE);
        } else {
            this.N3 = sSLSocketFactory;
            this.O3 = bVar.m;
        }
        this.P3 = bVar.n;
        this.Q3 = bVar.o.f(this.O3);
        this.R3 = bVar.p;
        this.S3 = bVar.q;
        this.T3 = bVar.r;
        this.U3 = bVar.s;
        this.V3 = bVar.t;
        this.W3 = bVar.u;
        this.X3 = bVar.v;
        this.Y3 = bVar.w;
        this.Z3 = bVar.x;
        this.a4 = bVar.y;
        this.b4 = bVar.z;
    }

    /* JADX INFO: compiled from: OkHttpClient.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public n f3658a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @Nullable
        public Proxy f3659b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public List<Protocol> f3660c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public List<k> f3661d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final List<t> f3662e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final List<t> f3663f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public p.c f3664g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public ProxySelector f3665h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public m f3666i;

        @Nullable
        public e.d0.e.d j;
        public SocketFactory k;

        @Nullable
        public SSLSocketFactory l;

        @Nullable
        public e.d0.l.b m;
        public HostnameVerifier n;
        public g o;
        public e.b p;
        public e.b q;
        public j r;
        public o s;
        public boolean t;
        public boolean u;
        public boolean v;
        public int w;
        public int x;
        public int y;
        public int z;

        public b() {
            this.f3662e = new ArrayList();
            this.f3663f = new ArrayList();
            this.f3658a = new n();
            this.f3660c = w.f3656c;
            this.f3661d = w.f3657d;
            this.f3664g = p.a(p.f3623a);
            this.f3665h = ProxySelector.getDefault();
            this.f3666i = m.f3614a;
            this.k = SocketFactory.getDefault();
            this.n = e.d0.l.d.f3564a;
            this.o = g.f3568a;
            e.b bVar = e.b.f3309a;
            this.p = bVar;
            this.q = bVar;
            this.r = new j();
            this.s = o.f3622a;
            this.t = true;
            this.u = true;
            this.v = true;
            this.w = 10000;
            this.x = 10000;
            this.y = 10000;
            this.z = 0;
        }

        public static int d(String str, long j, TimeUnit timeUnit) {
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

        public b a(t tVar) {
            this.f3662e.add(tVar);
            return this;
        }

        public b b(t tVar) {
            this.f3663f.add(tVar);
            return this;
        }

        public w c() {
            return new w(this);
        }

        public b e(long j, TimeUnit timeUnit) {
            this.w = d("timeout", j, timeUnit);
            return this;
        }

        public b f(List<k> list) {
            this.f3661d = e.d0.c.n(list);
            return this;
        }

        public b g(HostnameVerifier hostnameVerifier) {
            Objects.requireNonNull(hostnameVerifier, "hostnameVerifier == null");
            this.n = hostnameVerifier;
            return this;
        }

        public b h(long j, TimeUnit timeUnit) {
            this.x = d("timeout", j, timeUnit);
            return this;
        }

        public b i(SSLSocketFactory sSLSocketFactory) {
            Objects.requireNonNull(sSLSocketFactory, "sslSocketFactory == null");
            X509TrustManager x509TrustManagerO = e.d0.j.e.h().o(sSLSocketFactory);
            if (x509TrustManagerO != null) {
                this.l = sSLSocketFactory;
                this.m = e.d0.l.b.b(x509TrustManagerO);
                return this;
            }
            throw new IllegalStateException("Unable to extract the trust manager on " + e.d0.j.e.h() + ", sslSocketFactory is " + sSLSocketFactory.getClass());
        }

        public b j(long j, TimeUnit timeUnit) {
            this.y = d("timeout", j, timeUnit);
            return this;
        }

        public b(w wVar) {
            ArrayList arrayList = new ArrayList();
            this.f3662e = arrayList;
            ArrayList arrayList2 = new ArrayList();
            this.f3663f = arrayList2;
            this.f3658a = wVar.s;
            this.f3659b = wVar.x;
            this.f3660c = wVar.y;
            this.f3661d = wVar.E3;
            arrayList.addAll(wVar.F3);
            arrayList2.addAll(wVar.G3);
            this.f3664g = wVar.H3;
            this.f3665h = wVar.I3;
            this.f3666i = wVar.J3;
            this.j = wVar.L3;
            this.k = wVar.M3;
            this.l = wVar.N3;
            this.m = wVar.O3;
            this.n = wVar.P3;
            this.o = wVar.Q3;
            this.p = wVar.R3;
            this.q = wVar.S3;
            this.r = wVar.T3;
            this.s = wVar.U3;
            this.t = wVar.V3;
            this.u = wVar.W3;
            this.v = wVar.X3;
            this.w = wVar.Y3;
            this.x = wVar.Z3;
            this.y = wVar.a4;
            this.z = wVar.b4;
        }
    }
}
