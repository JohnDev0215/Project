package f;

import f.q;
import java.io.Closeable;
import okhttp3.Protocol;

/* JADX INFO: compiled from: Response.java */
/* JADX INFO: loaded from: classes.dex */
public final class y implements Closeable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final w f5438b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Protocol f5439c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5440d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f5441e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final p f5442f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final q f5443g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final z f5444h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final y f5445i;
    public final y j;
    public final y k;
    public final long l;
    public final long m;
    public volatile d n;

    public y(a aVar) {
        this.f5438b = aVar.f5446a;
        this.f5439c = aVar.f5447b;
        this.f5440d = aVar.f5448c;
        this.f5441e = aVar.f5449d;
        this.f5442f = aVar.f5450e;
        this.f5443g = aVar.f5451f.d();
        this.f5444h = aVar.f5452g;
        this.f5445i = aVar.f5453h;
        this.j = aVar.f5454i;
        this.k = aVar.j;
        this.l = aVar.k;
        this.m = aVar.l;
    }

    public int O() {
        return this.f5440d;
    }

    public p P() {
        return this.f5442f;
    }

    public String Q(String str) {
        return R(str, null);
    }

    public String R(String str, String str2) {
        String strA = this.f5443g.a(str);
        return strA != null ? strA : str2;
    }

    public q S() {
        return this.f5443g;
    }

    public boolean T() {
        int i2 = this.f5440d;
        return i2 >= 200 && i2 < 300;
    }

    public a U() {
        return new a(this);
    }

    public long V() {
        return this.m;
    }

    public w W() {
        return this.f5438b;
    }

    public long X() {
        return this.l;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f5444h.close();
    }

    public String toString() {
        return "Response{protocol=" + this.f5439c + ", code=" + this.f5440d + ", message=" + this.f5441e + ", url=" + this.f5438b.h() + '}';
    }

    public z w() {
        return this.f5444h;
    }

    public d z() {
        d dVar = this.n;
        if (dVar != null) {
            return dVar;
        }
        d dVarK = d.k(this.f5443g);
        this.n = dVarK;
        return dVarK;
    }

    /* JADX INFO: compiled from: Response.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public w f5446a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Protocol f5447b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5448c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f5449d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public p f5450e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public q.a f5451f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public z f5452g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public y f5453h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public y f5454i;
        public y j;
        public long k;
        public long l;

        public a() {
            this.f5448c = -1;
            this.f5451f = new q.a();
        }

        public a a(String str, String str2) {
            this.f5451f.a(str, str2);
            return this;
        }

        public a b(z zVar) {
            this.f5452g = zVar;
            return this;
        }

        public y c() {
            if (this.f5446a == null) {
                throw new IllegalStateException("request == null");
            }
            if (this.f5447b == null) {
                throw new IllegalStateException("protocol == null");
            }
            if (this.f5448c >= 0) {
                return new y(this);
            }
            throw new IllegalStateException("code < 0: " + this.f5448c);
        }

        public a d(y yVar) {
            if (yVar != null) {
                f("cacheResponse", yVar);
            }
            this.f5454i = yVar;
            return this;
        }

        public final void e(y yVar) {
            if (yVar.f5444h != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        public final void f(String str, y yVar) {
            if (yVar.f5444h != null) {
                throw new IllegalArgumentException(str + ".body != null");
            }
            if (yVar.f5445i != null) {
                throw new IllegalArgumentException(str + ".networkResponse != null");
            }
            if (yVar.j != null) {
                throw new IllegalArgumentException(str + ".cacheResponse != null");
            }
            if (yVar.k == null) {
                return;
            }
            throw new IllegalArgumentException(str + ".priorResponse != null");
        }

        public a g(int i2) {
            this.f5448c = i2;
            return this;
        }

        public a h(p pVar) {
            this.f5450e = pVar;
            return this;
        }

        public a i(q qVar) {
            this.f5451f = qVar.d();
            return this;
        }

        public a j(String str) {
            this.f5449d = str;
            return this;
        }

        public a k(y yVar) {
            if (yVar != null) {
                f("networkResponse", yVar);
            }
            this.f5453h = yVar;
            return this;
        }

        public a l(y yVar) {
            if (yVar != null) {
                e(yVar);
            }
            this.j = yVar;
            return this;
        }

        public a m(Protocol protocol) {
            this.f5447b = protocol;
            return this;
        }

        public a n(long j) {
            this.l = j;
            return this;
        }

        public a o(w wVar) {
            this.f5446a = wVar;
            return this;
        }

        public a p(long j) {
            this.k = j;
            return this;
        }

        public a(y yVar) {
            this.f5448c = -1;
            this.f5446a = yVar.f5438b;
            this.f5447b = yVar.f5439c;
            this.f5448c = yVar.f5440d;
            this.f5449d = yVar.f5441e;
            this.f5450e = yVar.f5442f;
            this.f5451f = yVar.f5443g.d();
            this.f5452g = yVar.f5444h;
            this.f5453h = yVar.f5445i;
            this.f5454i = yVar.j;
            this.j = yVar.k;
            this.k = yVar.l;
            this.l = yVar.m;
        }
    }
}
