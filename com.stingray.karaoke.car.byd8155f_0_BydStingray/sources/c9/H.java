package c9;

import java.io.Closeable;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class H implements Closeable {
    public final H0.b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final D f12514m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f12515n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final t f12516p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final u f12517q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final B3.m f12518r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final H f12519s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final H f12520t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final H f12521u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final long f12522v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final long f12523w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final g9.e f12524x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public C1046g f12525y;

    public H(H0.b bVar, D d, String str, int i7, t tVar, u uVar, B3.m mVar, H h10, H h11, H h12, long j3, long j10, g9.e eVar) {
        A7.m.f(bVar, "request");
        A7.m.f(d, "protocol");
        A7.m.f(str, "message");
        this.l = bVar;
        this.f12514m = d;
        this.f12515n = str;
        this.o = i7;
        this.f12516p = tVar;
        this.f12517q = uVar;
        this.f12518r = mVar;
        this.f12519s = h10;
        this.f12520t = h11;
        this.f12521u = h12;
        this.f12522v = j3;
        this.f12523w = j10;
        this.f12524x = eVar;
    }

    public static String g(H h10, String str) {
        h10.getClass();
        String strD = h10.f12517q.d(str);
        if (strD == null) {
            return null;
        }
        return strD;
    }

    public final C1046g b() {
        C1046g c1046g = this.f12525y;
        if (c1046g != null) {
            return c1046g;
        }
        C1046g c1046g2 = C1046g.f12558n;
        C1046g c1046gR = AbstractC2673a.R(this.f12517q);
        this.f12525y = c1046gR;
        return c1046gR;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        B3.m mVar = this.f12518r;
        if (mVar == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed");
        }
        mVar.close();
    }

    public final boolean i() {
        int i7 = this.o;
        if (i7 != 307 && i7 != 308) {
            switch (i7) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public final boolean j() {
        int i7 = this.o;
        return 200 <= i7 && i7 < 300;
    }

    public final G k() {
        G g7 = new G();
        g7.f12504a = this.l;
        g7.f12505b = this.f12514m;
        g7.c = this.o;
        g7.d = this.f12515n;
        g7.f12506e = this.f12516p;
        g7.f12507f = this.f12517q.q();
        g7.f12508g = this.f12518r;
        g7.f12509h = this.f12519s;
        g7.f12510i = this.f12520t;
        g7.f12511j = this.f12521u;
        g7.f12512k = this.f12522v;
        g7.l = this.f12523w;
        g7.f12513m = this.f12524x;
        return g7;
    }

    public final String toString() {
        return "Response{protocol=" + this.f12514m + ", code=" + this.o + ", message=" + this.f12515n + ", url=" + ((w) this.l.f2765m) + '}';
    }
}
