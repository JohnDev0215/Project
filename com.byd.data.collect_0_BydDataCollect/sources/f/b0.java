package f;

import f.g0;
import f.i;
import f.t;
import f.w;
import java.net.Proxy;
import java.net.ProxySelector;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
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
import javax.net.ssl.X509TrustManager;

/* JADX INFO: compiled from: OkHttpClient.java */
/* JADX INFO: loaded from: classes.dex */
public class b0 implements Cloneable, i.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final List<c0> f5011b = f.k0.e.t(c0.HTTP_2, c0.HTTP_1_1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final List<o> f5012c = f.k0.e.t(o.f5473d, o.f5475f);
    final int A;
    final int B;
    final int C;
    final int D;
    final int E;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final r f5013d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @Nullable
    final Proxy f5014e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final List<c0> f5015f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final List<o> f5016g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final List<y> f5017h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final List<y> f5018i;
    final t.b j;
    final ProxySelector k;
    final q l;

    @Nullable
    final g m;

    @Nullable
    final f.k0.g.d n;
    final SocketFactory o;
    final SSLSocketFactory p;
    final f.k0.n.c q;
    final HostnameVerifier r;
    final k s;
    final f t;
    final f u;
    final n v;
    final s w;
    final boolean x;
    final boolean y;
    final boolean z;

    /* JADX INFO: compiled from: OkHttpClient.java */
    class a extends f.k0.c {
        a() {
        }

        @Override // f.k0.c
        public void a(w.a aVar, String str) {
            aVar.c(str);
        }

        @Override // f.k0.c
        public void b(w.a aVar, String str, String str2) {
            aVar.d(str, str2);
        }

        @Override // f.k0.c
        public void c(o oVar, SSLSocket sSLSocket, boolean z) {
            oVar.a(sSLSocket, z);
        }

        @Override // f.k0.c
        public int d(g0.a aVar) {
            return aVar.f5084c;
        }

        @Override // f.k0.c
        public boolean e(e eVar, e eVar2) {
            return eVar.d(eVar2);
        }

        @Override // f.k0.c
        @Nullable
        public f.k0.h.d f(g0 g0Var) {
            return g0Var.n;
        }

        @Override // f.k0.c
        public void g(g0.a aVar, f.k0.h.d dVar) {
            aVar.k(dVar);
        }

        @Override // f.k0.c
        public f.k0.h.g h(n nVar) {
            return nVar.f5469a;
        }
    }

    /* JADX INFO: compiled from: OkHttpClient.java */
    public static final class b {
        int A;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @Nullable
        Proxy f5020b;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        ProxySelector f5026h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        q f5027i;

        @Nullable
        f.k0.g.d j;
        SocketFactory k;

        @Nullable
        SSLSocketFactory l;

        @Nullable
        f.k0.n.c m;
        HostnameVerifier n;
        k o;
        f p;
        f q;
        n r;
        s s;
        boolean t;
        boolean u;
        boolean v;
        int w;
        int x;
        int y;
        int z;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final List<y> f5023e = new ArrayList();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final List<y> f5024f = new ArrayList();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        r f5019a = new r();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        List<c0> f5021c = b0.f5011b;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        List<o> f5022d = b0.f5012c;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        t.b f5025g = t.k(t.f5501a);

        public b() {
            ProxySelector proxySelector = ProxySelector.getDefault();
            this.f5026h = proxySelector;
            if (proxySelector == null) {
                this.f5026h = new f.k0.m.a();
            }
            this.f5027i = q.f5492a;
            this.k = SocketFactory.getDefault();
            this.n = f.k0.n.d.f5450a;
            this.o = k.f5120a;
            f fVar = f.f5065a;
            this.p = fVar;
            this.q = fVar;
            this.r = new n();
            this.s = s.f5500a;
            this.t = true;
            this.u = true;
            this.v = true;
            this.w = 0;
            this.x = 10000;
            this.y = 10000;
            this.z = 10000;
            this.A = 0;
        }

        public b a(y yVar) {
            if (yVar == null) {
                throw new IllegalArgumentException("interceptor == null");
            }
            this.f5023e.add(yVar);
            return this;
        }

        public b b(y yVar) {
            if (yVar == null) {
                throw new IllegalArgumentException("interceptor == null");
            }
            this.f5024f.add(yVar);
            return this;
        }

        public b0 c() {
            return new b0(this);
        }

        public b d(long j, TimeUnit timeUnit) {
            this.x = f.k0.e.d("timeout", j, timeUnit);
            return this;
        }

        public b e(s sVar) {
            Objects.requireNonNull(sVar, "dns == null");
            this.s = sVar;
            return this;
        }

        public b f(long j, TimeUnit timeUnit) {
            this.y = f.k0.e.d("timeout", j, timeUnit);
            return this;
        }

        public b g(boolean z) {
            this.v = z;
            return this;
        }

        public b h(long j, TimeUnit timeUnit) {
            this.z = f.k0.e.d("timeout", j, timeUnit);
            return this;
        }
    }

    static {
        f.k0.c.f5131a = new a();
    }

    public b0() {
        this(new b());
    }

    private static SSLSocketFactory u(X509TrustManager x509TrustManager) {
        try {
            SSLContext sSLContextK = f.k0.l.f.j().k();
            sSLContextK.init(null, new TrustManager[]{x509TrustManager}, null);
            return sSLContextK.getSocketFactory();
        } catch (GeneralSecurityException e2) {
            throw new AssertionError("No System TLS", e2);
        }
    }

    public ProxySelector A() {
        return this.k;
    }

    public int B() {
        return this.C;
    }

    public boolean D() {
        return this.z;
    }

    public SocketFactory E() {
        return this.o;
    }

    public SSLSocketFactory F() {
        return this.p;
    }

    public int G() {
        return this.D;
    }

    @Override // f.i.a
    public i b(e0 e0Var) {
        return d0.f(this, e0Var, false);
    }

    public f c() {
        return this.u;
    }

    public int e() {
        return this.A;
    }

    public k f() {
        return this.s;
    }

    public int g() {
        return this.B;
    }

    public n h() {
        return this.v;
    }

    public List<o> i() {
        return this.f5016g;
    }

    public q k() {
        return this.l;
    }

    public r l() {
        return this.f5013d;
    }

    public s m() {
        return this.w;
    }

    public t.b n() {
        return this.j;
    }

    public boolean o() {
        return this.y;
    }

    public boolean p() {
        return this.x;
    }

    public HostnameVerifier q() {
        return this.r;
    }

    public List<y> r() {
        return this.f5017h;
    }

    @Nullable
    f.k0.g.d s() {
        if (this.m == null) {
            return this.n;
        }
        throw null;
    }

    public List<y> t() {
        return this.f5018i;
    }

    public int v() {
        return this.E;
    }

    public List<c0> w() {
        return this.f5015f;
    }

    @Nullable
    public Proxy x() {
        return this.f5014e;
    }

    public f z() {
        return this.t;
    }

    b0(b bVar) {
        boolean z;
        this.f5013d = bVar.f5019a;
        this.f5014e = bVar.f5020b;
        this.f5015f = bVar.f5021c;
        List<o> list = bVar.f5022d;
        this.f5016g = list;
        this.f5017h = f.k0.e.s(bVar.f5023e);
        this.f5018i = f.k0.e.s(bVar.f5024f);
        this.j = bVar.f5025g;
        this.k = bVar.f5026h;
        this.l = bVar.f5027i;
        this.n = bVar.j;
        this.o = bVar.k;
        Iterator<o> it = list.iterator();
        loop0: while (true) {
            z = false;
            while (it.hasNext()) {
                z = (z || it.next().d()) ? true : z;
            }
        }
        SSLSocketFactory sSLSocketFactory = bVar.l;
        if (sSLSocketFactory == null && z) {
            X509TrustManager x509TrustManagerC = f.k0.e.C();
            this.p = u(x509TrustManagerC);
            this.q = f.k0.n.c.b(x509TrustManagerC);
        } else {
            this.p = sSLSocketFactory;
            this.q = bVar.m;
        }
        if (this.p != null) {
            f.k0.l.f.j().f(this.p);
        }
        this.r = bVar.n;
        this.s = bVar.o.f(this.q);
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
        this.D = bVar.z;
        this.E = bVar.A;
        if (this.f5017h.contains(null)) {
            throw new IllegalStateException("Null interceptor: " + this.f5017h);
        }
        if (this.f5018i.contains(null)) {
            throw new IllegalStateException("Null network interceptor: " + this.f5018i);
        }
    }
}
