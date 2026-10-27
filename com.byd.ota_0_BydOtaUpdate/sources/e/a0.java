package e;

import e.s;
import java.io.Closeable;
import javax.annotation.Nullable;
import okhttp3.Protocol;

/* JADX INFO: compiled from: Response.java */
/* JADX INFO: loaded from: classes.dex */
public final class a0 implements Closeable {
    public final s E3;

    @Nullable
    public final b0 F3;

    @Nullable
    public final a0 G3;

    @Nullable
    public final a0 H3;

    @Nullable
    public final a0 I3;
    public final long J3;
    public final long K3;
    public volatile d L3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final y f3298c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Protocol f3299d;
    public final int s;
    public final String x;

    @Nullable
    public final r y;

    public a0(a aVar) {
        this.f3298c = aVar.f3300a;
        this.f3299d = aVar.f3301b;
        this.s = aVar.f3302c;
        this.x = aVar.f3303d;
        this.y = aVar.f3304e;
        this.E3 = aVar.f3305f.d();
        this.F3 = aVar.f3306g;
        this.G3 = aVar.f3307h;
        this.H3 = aVar.f3308i;
        this.I3 = aVar.j;
        this.J3 = aVar.k;
        this.K3 = aVar.l;
    }

    @Nullable
    public String H(String str) {
        return I(str, null);
    }

    @Nullable
    public String I(String str, @Nullable String str2) {
        String strA = this.E3.a(str);
        return strA != null ? strA : str2;
    }

    public s J() {
        return this.E3;
    }

    public boolean K() {
        int i2 = this.s;
        return i2 >= 200 && i2 < 300;
    }

    public String L() {
        return this.x;
    }

    public a M() {
        return new a(this);
    }

    public long N() {
        return this.K3;
    }

    public y O() {
        return this.f3298c;
    }

    public long P() {
        return this.J3;
    }

    @Nullable
    public b0 c() {
        return this.F3;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.F3.close();
    }

    public d d() {
        d dVar = this.L3;
        if (dVar != null) {
            return dVar;
        }
        d dVarK = d.k(this.E3);
        this.L3 = dVarK;
        return dVarK;
    }

    public int s() {
        return this.s;
    }

    public String toString() {
        return "Response{protocol=" + this.f3299d + ", code=" + this.s + ", message=" + this.x + ", url=" + this.f3298c.h() + '}';
    }

    public r v() {
        return this.y;
    }

    /* JADX INFO: compiled from: Response.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public y f3300a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Protocol f3301b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3302c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f3303d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        @Nullable
        public r f3304e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public s.a f3305f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public b0 f3306g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public a0 f3307h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public a0 f3308i;
        public a0 j;
        public long k;
        public long l;

        public a() {
            this.f3302c = -1;
            this.f3305f = new s.a();
        }

        public a a(String str, String str2) {
            this.f3305f.a(str, str2);
            return this;
        }

        public a b(@Nullable b0 b0Var) {
            this.f3306g = b0Var;
            return this;
        }

        public a0 c() {
            if (this.f3300a == null) {
                throw new IllegalStateException("request == null");
            }
            if (this.f3301b == null) {
                throw new IllegalStateException("protocol == null");
            }
            if (this.f3302c >= 0) {
                if (this.f3303d != null) {
                    return new a0(this);
                }
                throw new IllegalStateException("message == null");
            }
            throw new IllegalStateException("code < 0: " + this.f3302c);
        }

        public a d(@Nullable a0 a0Var) {
            if (a0Var != null) {
                f("cacheResponse", a0Var);
            }
            this.f3308i = a0Var;
            return this;
        }

        public final void e(a0 a0Var) {
            if (a0Var.F3 != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        public final void f(String str, a0 a0Var) {
            if (a0Var.F3 != null) {
                throw new IllegalArgumentException(str + ".body != null");
            }
            if (a0Var.G3 != null) {
                throw new IllegalArgumentException(str + ".networkResponse != null");
            }
            if (a0Var.H3 != null) {
                throw new IllegalArgumentException(str + ".cacheResponse != null");
            }
            if (a0Var.I3 == null) {
                return;
            }
            throw new IllegalArgumentException(str + ".priorResponse != null");
        }

        public a g(int i2) {
            this.f3302c = i2;
            return this;
        }

        public a h(@Nullable r rVar) {
            this.f3304e = rVar;
            return this;
        }

        public a i(s sVar) {
            this.f3305f = sVar.d();
            return this;
        }

        public a j(String str) {
            this.f3303d = str;
            return this;
        }

        public a k(@Nullable a0 a0Var) {
            if (a0Var != null) {
                f("networkResponse", a0Var);
            }
            this.f3307h = a0Var;
            return this;
        }

        public a l(@Nullable a0 a0Var) {
            if (a0Var != null) {
                e(a0Var);
            }
            this.j = a0Var;
            return this;
        }

        public a m(Protocol protocol) {
            this.f3301b = protocol;
            return this;
        }

        public a n(long j) {
            this.l = j;
            return this;
        }

        public a o(y yVar) {
            this.f3300a = yVar;
            return this;
        }

        public a p(long j) {
            this.k = j;
            return this;
        }

        public a(a0 a0Var) {
            this.f3302c = -1;
            this.f3300a = a0Var.f3298c;
            this.f3301b = a0Var.f3299d;
            this.f3302c = a0Var.s;
            this.f3303d = a0Var.x;
            this.f3304e = a0Var.y;
            this.f3305f = a0Var.E3.d();
            this.f3306g = a0Var.F3;
            this.f3307h = a0Var.G3;
            this.f3308i = a0Var.H3;
            this.j = a0Var.I3;
            this.k = a0Var.J3;
            this.l = a0Var.K3;
        }
    }
}
