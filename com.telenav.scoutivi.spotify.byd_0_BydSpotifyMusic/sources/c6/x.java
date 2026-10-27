package c6;

import c6.e;
import c6.r;
import java.net.Proxy;
import java.net.ProxySelector;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import l6.h;
import o6.c;

/* JADX INFO: compiled from: OkHttpClient.kt */
/* JADX INFO: loaded from: classes.dex */
public class x implements Cloneable, e.a {
    private final o6.c A;
    private final int B;
    private final int C;
    private final int D;
    private final int E;
    private final int F;
    private final long G;
    private final h6.i H;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final p f3874e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final k f3875g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final List<v> f3876h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final List<v> f3877i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final r.c f3878j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final boolean f3879k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final c6.b f3880l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final boolean f3881m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final boolean f3882n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private final n f3883o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final q f3884p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private final Proxy f3885q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private final ProxySelector f3886r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private final c6.b f3887s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private final SocketFactory f3888t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private final SSLSocketFactory f3889u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private final X509TrustManager f3890v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private final List<l> f3891w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private final List<y> f3892x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private final HostnameVerifier f3893y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private final g f3894z;
    public static final b K = new b(null);
    private static final List<y> I = d6.b.s(y.HTTP_2, y.HTTP_1_1);
    private static final List<l> J = d6.b.s(l.f3796h, l.f3798j);

    /* JADX INFO: compiled from: OkHttpClient.kt */
    public static final class a {
        private int A;
        private long B;
        private h6.i C;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private p f3895a = new p();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private k f3896b = new k();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final List<v> f3897c = new ArrayList();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final List<v> f3898d = new ArrayList();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private r.c f3899e = d6.b.e(r.f3834a);

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f3900f = true;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private c6.b f3901g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private boolean f3902h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private boolean f3903i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private n f3904j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private q f3905k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private Proxy f3906l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private ProxySelector f3907m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        private c6.b f3908n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        private SocketFactory f3909o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        private SSLSocketFactory f3910p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        private X509TrustManager f3911q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        private List<l> f3912r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        private List<? extends y> f3913s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        private HostnameVerifier f3914t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        private g f3915u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        private o6.c f3916v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        private int f3917w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        private int f3918x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        private int f3919y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        private int f3920z;

        public a() {
            c6.b bVar = c6.b.f3645a;
            this.f3901g = bVar;
            this.f3902h = true;
            this.f3903i = true;
            this.f3904j = n.f3822a;
            this.f3905k = q.f3832a;
            this.f3908n = bVar;
            SocketFactory socketFactory = SocketFactory.getDefault();
            kotlin.jvm.internal.l.d(socketFactory, "SocketFactory.getDefault()");
            this.f3909o = socketFactory;
            b bVar2 = x.K;
            this.f3912r = bVar2.a();
            this.f3913s = bVar2.b();
            this.f3914t = o6.d.f8672a;
            this.f3915u = g.f3708c;
            this.f3918x = 10000;
            this.f3919y = 10000;
            this.f3920z = 10000;
            this.B = 1024L;
        }

        public final h6.i A() {
            return this.C;
        }

        public final SocketFactory B() {
            return this.f3909o;
        }

        public final SSLSocketFactory C() {
            return this.f3910p;
        }

        public final int D() {
            return this.f3920z;
        }

        public final X509TrustManager E() {
            return this.f3911q;
        }

        public final x a() {
            return new x(this);
        }

        public final c6.b b() {
            return this.f3901g;
        }

        public final c c() {
            return null;
        }

        public final int d() {
            return this.f3917w;
        }

        public final o6.c e() {
            return this.f3916v;
        }

        public final g f() {
            return this.f3915u;
        }

        public final int g() {
            return this.f3918x;
        }

        public final k h() {
            return this.f3896b;
        }

        public final List<l> i() {
            return this.f3912r;
        }

        public final n j() {
            return this.f3904j;
        }

        public final p k() {
            return this.f3895a;
        }

        public final q l() {
            return this.f3905k;
        }

        public final r.c m() {
            return this.f3899e;
        }

        public final boolean n() {
            return this.f3902h;
        }

        public final boolean o() {
            return this.f3903i;
        }

        public final HostnameVerifier p() {
            return this.f3914t;
        }

        public final List<v> q() {
            return this.f3897c;
        }

        public final long r() {
            return this.B;
        }

        public final List<v> s() {
            return this.f3898d;
        }

        public final int t() {
            return this.A;
        }

        public final List<y> u() {
            return this.f3913s;
        }

        public final Proxy v() {
            return this.f3906l;
        }

        public final c6.b w() {
            return this.f3908n;
        }

        public final ProxySelector x() {
            return this.f3907m;
        }

        public final int y() {
            return this.f3919y;
        }

        public final boolean z() {
            return this.f3900f;
        }
    }

    /* JADX INFO: compiled from: OkHttpClient.kt */
    public static final class b {
        private b() {
        }

        public /* synthetic */ b(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final List<l> a() {
            return x.J;
        }

        public final List<y> b() {
            return x.I;
        }
    }

    public x(a builder) throws NoSuchAlgorithmException, KeyStoreException {
        ProxySelector proxySelectorX;
        kotlin.jvm.internal.l.e(builder, "builder");
        this.f3874e = builder.k();
        this.f3875g = builder.h();
        this.f3876h = d6.b.M(builder.q());
        this.f3877i = d6.b.M(builder.s());
        this.f3878j = builder.m();
        this.f3879k = builder.z();
        this.f3880l = builder.b();
        this.f3881m = builder.n();
        this.f3882n = builder.o();
        this.f3883o = builder.j();
        builder.c();
        this.f3884p = builder.l();
        this.f3885q = builder.v();
        if (builder.v() != null) {
            proxySelectorX = n6.a.f8546a;
        } else {
            proxySelectorX = builder.x();
            proxySelectorX = proxySelectorX == null ? ProxySelector.getDefault() : proxySelectorX;
            if (proxySelectorX == null) {
                proxySelectorX = n6.a.f8546a;
            }
        }
        this.f3886r = proxySelectorX;
        this.f3887s = builder.w();
        this.f3888t = builder.B();
        List<l> listI = builder.i();
        this.f3891w = listI;
        this.f3892x = builder.u();
        this.f3893y = builder.p();
        this.B = builder.d();
        this.C = builder.g();
        this.D = builder.y();
        this.E = builder.D();
        this.F = builder.t();
        this.G = builder.r();
        h6.i iVarA = builder.A();
        this.H = iVarA == null ? new h6.i() : iVarA;
        boolean z6 = true;
        if (!(listI instanceof Collection) || !listI.isEmpty()) {
            Iterator<T> it = listI.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((l) it.next()).f()) {
                    z6 = false;
                    break;
                }
            }
        }
        if (z6) {
            this.f3889u = null;
            this.A = null;
            this.f3890v = null;
            this.f3894z = g.f3708c;
        } else if (builder.C() != null) {
            this.f3889u = builder.C();
            o6.c cVarE = builder.e();
            kotlin.jvm.internal.l.c(cVarE);
            this.A = cVarE;
            X509TrustManager x509TrustManagerE = builder.E();
            kotlin.jvm.internal.l.c(x509TrustManagerE);
            this.f3890v = x509TrustManagerE;
            g gVarF = builder.f();
            kotlin.jvm.internal.l.c(cVarE);
            this.f3894z = gVarF.e(cVarE);
        } else {
            h.a aVar = l6.h.f8004c;
            X509TrustManager x509TrustManagerO = aVar.g().o();
            this.f3890v = x509TrustManagerO;
            l6.h hVarG = aVar.g();
            kotlin.jvm.internal.l.c(x509TrustManagerO);
            this.f3889u = hVarG.n(x509TrustManagerO);
            c.a aVar2 = o6.c.f8671a;
            kotlin.jvm.internal.l.c(x509TrustManagerO);
            o6.c cVarA = aVar2.a(x509TrustManagerO);
            this.A = cVarA;
            g gVarF2 = builder.f();
            kotlin.jvm.internal.l.c(cVarA);
            this.f3894z = gVarF2.e(cVarA);
        }
        E();
    }

    private final void E() {
        boolean z6;
        Objects.requireNonNull(this.f3876h, "null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
        if (!(!r0.contains(null))) {
            throw new IllegalStateException(("Null interceptor: " + this.f3876h).toString());
        }
        Objects.requireNonNull(this.f3877i, "null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
        if (!(!r0.contains(null))) {
            throw new IllegalStateException(("Null network interceptor: " + this.f3877i).toString());
        }
        List<l> list = this.f3891w;
        if ((list instanceof Collection) && list.isEmpty()) {
            z6 = true;
        } else {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (((l) it.next()).f()) {
                    z6 = false;
                    break;
                }
            }
            z6 = true;
        }
        if (!z6) {
            if (this.f3889u == null) {
                throw new IllegalStateException("sslSocketFactory == null".toString());
            }
            if (this.A == null) {
                throw new IllegalStateException("certificateChainCleaner == null".toString());
            }
            if (this.f3890v == null) {
                throw new IllegalStateException("x509TrustManager == null".toString());
            }
            return;
        }
        if (!(this.f3889u == null)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (!(this.A == null)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (!(this.f3890v == null)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (!kotlin.jvm.internal.l.a(this.f3894z, g.f3708c)) {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    public final int A() {
        return this.D;
    }

    public final boolean B() {
        return this.f3879k;
    }

    public final SocketFactory C() {
        return this.f3888t;
    }

    public final SSLSocketFactory D() {
        SSLSocketFactory sSLSocketFactory = this.f3889u;
        if (sSLSocketFactory != null) {
            return sSLSocketFactory;
        }
        throw new IllegalStateException("CLEARTEXT-only client");
    }

    public final int F() {
        return this.E;
    }

    @Override // c6.e.a
    public e a(z request) {
        kotlin.jvm.internal.l.e(request, "request");
        return new h6.e(this, request, false);
    }

    public Object clone() {
        return super.clone();
    }

    public final c6.b d() {
        return this.f3880l;
    }

    public final c e() {
        return null;
    }

    public final int f() {
        return this.B;
    }

    public final g g() {
        return this.f3894z;
    }

    public final int h() {
        return this.C;
    }

    public final k i() {
        return this.f3875g;
    }

    public final List<l> j() {
        return this.f3891w;
    }

    public final n k() {
        return this.f3883o;
    }

    public final p l() {
        return this.f3874e;
    }

    public final q n() {
        return this.f3884p;
    }

    public final r.c o() {
        return this.f3878j;
    }

    public final boolean p() {
        return this.f3881m;
    }

    public final boolean q() {
        return this.f3882n;
    }

    public final h6.i r() {
        return this.H;
    }

    public final HostnameVerifier s() {
        return this.f3893y;
    }

    public final List<v> t() {
        return this.f3876h;
    }

    public final List<v> u() {
        return this.f3877i;
    }

    public final int v() {
        return this.F;
    }

    public final List<y> w() {
        return this.f3892x;
    }

    public final Proxy x() {
        return this.f3885q;
    }

    public final c6.b y() {
        return this.f3887s;
    }

    public final ProxySelector z() {
        return this.f3886r;
    }

    public x() {
        this(new a());
    }
}
