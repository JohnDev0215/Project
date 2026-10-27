package c6;

import c6.t;
import java.io.Closeable;
import java.util.List;

/* JADX INFO: compiled from: Response.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b0 implements Closeable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private d f3648e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final z f3649g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final y f3650h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final String f3651i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final int f3652j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final s f3653k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final t f3654l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final c0 f3655m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final b0 f3656n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private final b0 f3657o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final b0 f3658p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private final long f3659q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private final long f3660r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private final h6.c f3661s;

    public b0(z request, y protocol, String message, int i7, s sVar, t headers, c0 c0Var, b0 b0Var, b0 b0Var2, b0 b0Var3, long j7, long j8, h6.c cVar) {
        kotlin.jvm.internal.l.e(request, "request");
        kotlin.jvm.internal.l.e(protocol, "protocol");
        kotlin.jvm.internal.l.e(message, "message");
        kotlin.jvm.internal.l.e(headers, "headers");
        this.f3649g = request;
        this.f3650h = protocol;
        this.f3651i = message;
        this.f3652j = i7;
        this.f3653k = sVar;
        this.f3654l = headers;
        this.f3655m = c0Var;
        this.f3656n = b0Var;
        this.f3657o = b0Var2;
        this.f3658p = b0Var3;
        this.f3659q = j7;
        this.f3660r = j8;
        this.f3661s = cVar;
    }

    public static /* synthetic */ String U(b0 b0Var, String str, String str2, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            str2 = null;
        }
        return b0Var.P(str, str2);
    }

    public final b0 A() {
        return this.f3657o;
    }

    public final List<h> G() {
        String str;
        t tVar = this.f3654l;
        int i7 = this.f3652j;
        if (i7 == 401) {
            str = "WWW-Authenticate";
        } else {
            if (i7 != 407) {
                return h5.p.h();
            }
            str = "Proxy-Authenticate";
        }
        return i6.e.a(tVar, str);
    }

    public final int I() {
        return this.f3652j;
    }

    public final h6.c K() {
        return this.f3661s;
    }

    public final s N() {
        return this.f3653k;
    }

    public final String P(String name, String str) {
        kotlin.jvm.internal.l.e(name, "name");
        String strC = this.f3654l.c(name);
        return strC != null ? strC : str;
    }

    public final t V() {
        return this.f3654l;
    }

    public final boolean Z() {
        int i7 = this.f3652j;
        return 200 <= i7 && 299 >= i7;
    }

    public final String a0() {
        return this.f3651i;
    }

    public final b0 b0() {
        return this.f3656n;
    }

    public final a c0() {
        return new a(this);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        c0 c0Var = this.f3655m;
        if (c0Var == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed".toString());
        }
        c0Var.close();
    }

    public final b0 d0() {
        return this.f3658p;
    }

    public final y e0() {
        return this.f3650h;
    }

    public final long f0() {
        return this.f3660r;
    }

    public final z g0() {
        return this.f3649g;
    }

    public final long h0() {
        return this.f3659q;
    }

    public final c0 m() {
        return this.f3655m;
    }

    public String toString() {
        return "Response{protocol=" + this.f3650h + ", code=" + this.f3652j + ", message=" + this.f3651i + ", url=" + this.f3649g.i() + '}';
    }

    public final d z() {
        d dVar = this.f3648e;
        if (dVar != null) {
            return dVar;
        }
        d dVarB = d.f3681p.b(this.f3654l);
        this.f3648e = dVarB;
        return dVarB;
    }

    /* JADX INFO: compiled from: Response.kt */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private z f3662a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private y f3663b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f3664c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f3665d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private s f3666e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private t.a f3667f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private c0 f3668g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private b0 f3669h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private b0 f3670i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private b0 f3671j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private long f3672k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private long f3673l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private h6.c f3674m;

        public a() {
            this.f3664c = -1;
            this.f3667f = new t.a();
        }

        private final void e(b0 b0Var) {
            if (b0Var != null) {
                if (!(b0Var.m() == null)) {
                    throw new IllegalArgumentException("priorResponse.body != null".toString());
                }
            }
        }

        private final void f(String str, b0 b0Var) {
            if (b0Var != null) {
                if (!(b0Var.m() == null)) {
                    throw new IllegalArgumentException((str + ".body != null").toString());
                }
                if (!(b0Var.b0() == null)) {
                    throw new IllegalArgumentException((str + ".networkResponse != null").toString());
                }
                if (!(b0Var.A() == null)) {
                    throw new IllegalArgumentException((str + ".cacheResponse != null").toString());
                }
                if (b0Var.d0() == null) {
                    return;
                }
                throw new IllegalArgumentException((str + ".priorResponse != null").toString());
            }
        }

        public a a(String name, String value) {
            kotlin.jvm.internal.l.e(name, "name");
            kotlin.jvm.internal.l.e(value, "value");
            this.f3667f.a(name, value);
            return this;
        }

        public a b(c0 c0Var) {
            this.f3668g = c0Var;
            return this;
        }

        public b0 c() {
            int i7 = this.f3664c;
            if (!(i7 >= 0)) {
                throw new IllegalStateException(("code < 0: " + this.f3664c).toString());
            }
            z zVar = this.f3662a;
            if (zVar == null) {
                throw new IllegalStateException("request == null".toString());
            }
            y yVar = this.f3663b;
            if (yVar == null) {
                throw new IllegalStateException("protocol == null".toString());
            }
            String str = this.f3665d;
            if (str != null) {
                return new b0(zVar, yVar, str, i7, this.f3666e, this.f3667f.d(), this.f3668g, this.f3669h, this.f3670i, this.f3671j, this.f3672k, this.f3673l, this.f3674m);
            }
            throw new IllegalStateException("message == null".toString());
        }

        public a d(b0 b0Var) {
            f("cacheResponse", b0Var);
            this.f3670i = b0Var;
            return this;
        }

        public a g(int i7) {
            this.f3664c = i7;
            return this;
        }

        public final int h() {
            return this.f3664c;
        }

        public a i(s sVar) {
            this.f3666e = sVar;
            return this;
        }

        public a j(String name, String value) {
            kotlin.jvm.internal.l.e(name, "name");
            kotlin.jvm.internal.l.e(value, "value");
            this.f3667f.g(name, value);
            return this;
        }

        public a k(t headers) {
            kotlin.jvm.internal.l.e(headers, "headers");
            this.f3667f = headers.e();
            return this;
        }

        public final void l(h6.c deferredTrailers) {
            kotlin.jvm.internal.l.e(deferredTrailers, "deferredTrailers");
            this.f3674m = deferredTrailers;
        }

        public a m(String message) {
            kotlin.jvm.internal.l.e(message, "message");
            this.f3665d = message;
            return this;
        }

        public a n(b0 b0Var) {
            f("networkResponse", b0Var);
            this.f3669h = b0Var;
            return this;
        }

        public a o(b0 b0Var) {
            e(b0Var);
            this.f3671j = b0Var;
            return this;
        }

        public a p(y protocol) {
            kotlin.jvm.internal.l.e(protocol, "protocol");
            this.f3663b = protocol;
            return this;
        }

        public a q(long j7) {
            this.f3673l = j7;
            return this;
        }

        public a r(z request) {
            kotlin.jvm.internal.l.e(request, "request");
            this.f3662a = request;
            return this;
        }

        public a s(long j7) {
            this.f3672k = j7;
            return this;
        }

        public a(b0 response) {
            kotlin.jvm.internal.l.e(response, "response");
            this.f3664c = -1;
            this.f3662a = response.g0();
            this.f3663b = response.e0();
            this.f3664c = response.I();
            this.f3665d = response.a0();
            this.f3666e = response.N();
            this.f3667f = response.V().e();
            this.f3668g = response.m();
            this.f3669h = response.b0();
            this.f3670i = response.A();
            this.f3671j = response.d0();
            this.f3672k = response.h0();
            this.f3673l = response.f0();
            this.f3674m = response.K();
        }
    }
}
