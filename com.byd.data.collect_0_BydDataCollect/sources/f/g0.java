package f;

import f.w;
import java.io.Closeable;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Response.java */
/* JADX INFO: loaded from: classes.dex */
public final class g0 implements Closeable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final e0 f5074b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c0 f5075c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f5076d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final String f5077e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @Nullable
    final v f5078f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final w f5079g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    @Nullable
    final h0 f5080h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    @Nullable
    final g0 f5081i;

    @Nullable
    final g0 j;

    @Nullable
    final g0 k;
    final long l;
    final long m;

    @Nullable
    final f.k0.h.d n;

    @Nullable
    private volatile h o;

    g0(a aVar) {
        this.f5074b = aVar.f5082a;
        this.f5075c = aVar.f5083b;
        this.f5076d = aVar.f5084c;
        this.f5077e = aVar.f5085d;
        this.f5078f = aVar.f5086e;
        this.f5079g = aVar.f5087f.f();
        this.f5080h = aVar.f5088g;
        this.f5081i = aVar.f5089h;
        this.j = aVar.f5090i;
        this.k = aVar.j;
        this.l = aVar.k;
        this.m = aVar.l;
        this.n = aVar.m;
    }

    @Nullable
    public String C(String str) {
        return R(str, null);
    }

    @Nullable
    public String R(String str, @Nullable String str2) {
        String strC = this.f5079g.c(str);
        return strC != null ? strC : str2;
    }

    public w S() {
        return this.f5079g;
    }

    public boolean T() {
        int i2 = this.f5076d;
        return i2 >= 200 && i2 < 300;
    }

    public String U() {
        return this.f5077e;
    }

    public a V() {
        return new a(this);
    }

    @Nullable
    public g0 W() {
        return this.k;
    }

    public long X() {
        return this.m;
    }

    public e0 Y() {
        return this.f5074b;
    }

    public long Z() {
        return this.l;
    }

    @Nullable
    public h0 a() {
        return this.f5080h;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        h0 h0Var = this.f5080h;
        if (h0Var == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed");
        }
        h0Var.close();
    }

    public h d() {
        h hVar = this.o;
        if (hVar != null) {
            return hVar;
        }
        h hVarK = h.k(this.f5079g);
        this.o = hVarK;
        return hVarK;
    }

    public int j() {
        return this.f5076d;
    }

    public String toString() {
        return "Response{protocol=" + this.f5075c + ", code=" + this.f5076d + ", message=" + this.f5077e + ", url=" + this.f5074b.i() + '}';
    }

    @Nullable
    public v y() {
        return this.f5078f;
    }

    /* JADX INFO: compiled from: Response.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @Nullable
        e0 f5082a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @Nullable
        c0 f5083b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f5084c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        String f5085d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        @Nullable
        v f5086e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        w.a f5087f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        @Nullable
        h0 f5088g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        @Nullable
        g0 f5089h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        @Nullable
        g0 f5090i;

        @Nullable
        g0 j;
        long k;
        long l;

        @Nullable
        f.k0.h.d m;

        public a() {
            this.f5084c = -1;
            this.f5087f = new w.a();
        }

        private void e(g0 g0Var) {
            if (g0Var.f5080h != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        private void f(String str, g0 g0Var) {
            if (g0Var.f5080h != null) {
                throw new IllegalArgumentException(str + ".body != null");
            }
            if (g0Var.f5081i != null) {
                throw new IllegalArgumentException(str + ".networkResponse != null");
            }
            if (g0Var.j != null) {
                throw new IllegalArgumentException(str + ".cacheResponse != null");
            }
            if (g0Var.k == null) {
                return;
            }
            throw new IllegalArgumentException(str + ".priorResponse != null");
        }

        public a a(String str, String str2) {
            this.f5087f.a(str, str2);
            return this;
        }

        public a b(@Nullable h0 h0Var) {
            this.f5088g = h0Var;
            return this;
        }

        public g0 c() {
            if (this.f5082a == null) {
                throw new IllegalStateException("request == null");
            }
            if (this.f5083b == null) {
                throw new IllegalStateException("protocol == null");
            }
            if (this.f5084c >= 0) {
                if (this.f5085d != null) {
                    return new g0(this);
                }
                throw new IllegalStateException("message == null");
            }
            throw new IllegalStateException("code < 0: " + this.f5084c);
        }

        public a d(@Nullable g0 g0Var) {
            if (g0Var != null) {
                f("cacheResponse", g0Var);
            }
            this.f5090i = g0Var;
            return this;
        }

        public a g(int i2) {
            this.f5084c = i2;
            return this;
        }

        public a h(@Nullable v vVar) {
            this.f5086e = vVar;
            return this;
        }

        public a i(String str, String str2) {
            this.f5087f.h(str, str2);
            return this;
        }

        public a j(w wVar) {
            this.f5087f = wVar.f();
            return this;
        }

        void k(f.k0.h.d dVar) {
            this.m = dVar;
        }

        public a l(String str) {
            this.f5085d = str;
            return this;
        }

        public a m(@Nullable g0 g0Var) {
            if (g0Var != null) {
                f("networkResponse", g0Var);
            }
            this.f5089h = g0Var;
            return this;
        }

        public a n(@Nullable g0 g0Var) {
            if (g0Var != null) {
                e(g0Var);
            }
            this.j = g0Var;
            return this;
        }

        public a o(c0 c0Var) {
            this.f5083b = c0Var;
            return this;
        }

        public a p(long j) {
            this.l = j;
            return this;
        }

        public a q(e0 e0Var) {
            this.f5082a = e0Var;
            return this;
        }

        public a r(long j) {
            this.k = j;
            return this;
        }

        a(g0 g0Var) {
            this.f5084c = -1;
            this.f5082a = g0Var.f5074b;
            this.f5083b = g0Var.f5075c;
            this.f5084c = g0Var.f5076d;
            this.f5085d = g0Var.f5077e;
            this.f5086e = g0Var.f5078f;
            this.f5087f = g0Var.f5079g.f();
            this.f5088g = g0Var.f5080h;
            this.f5089h = g0Var.f5081i;
            this.f5090i = g0Var.j;
            this.j = g0Var.k;
            this.k = g0Var.l;
            this.l = g0Var.m;
            this.m = g0Var.n;
        }
    }
}
