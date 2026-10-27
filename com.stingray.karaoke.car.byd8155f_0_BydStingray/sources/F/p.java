package F;

import C0.V;
import G.N;
import H7.H;
import java.util.List;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class p implements N {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1927a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1928b;
    public final int c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a1.k f1929e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f1930f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f1931g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List f1932h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f1933i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Object f1934j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final androidx.compose.foundation.lazy.layout.a f1935k;
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f1936m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f1937n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f1938p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1939q = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1940r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1941s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final long f1942t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public long f1943u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1944v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f1945w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f1946x;

    public p(int i7, Object obj, int i10, int i11, boolean z6, a1.k kVar, int i12, int i13, List list, long j3, Object obj2, androidx.compose.foundation.lazy.layout.a aVar, long j10, int i14, int i15) {
        this.f1927a = i7;
        this.f1928b = obj;
        this.c = i10;
        this.d = z6;
        this.f1929e = kVar;
        this.f1930f = i12;
        this.f1931g = i13;
        this.f1932h = list;
        this.f1933i = j3;
        this.f1934j = obj2;
        this.f1935k = aVar;
        this.l = j10;
        this.f1936m = i14;
        this.f1937n = i15;
        int size = list.size();
        int iMax = 0;
        for (int i16 = 0; i16 < size; i16++) {
            iMax = Math.max(iMax, ((V) list.get(i16)).f871m);
        }
        this.o = iMax;
        int i17 = iMax + i11;
        this.f1938p = i17 >= 0 ? i17 : 0;
        this.f1942t = H.c(this.c, iMax);
        this.f1943u = 0L;
        this.f1944v = -1;
        this.f1945w = -1;
    }

    public final void a(int i7, int i10, int i11, int i12, int i13, int i14) {
        this.f1939q = i12;
        if (this.f1929e == a1.k.f11038m) {
            i10 = (i11 - i10) - this.c;
        }
        this.f1943u = AbstractC2673a.d(i10, i7);
        this.f1944v = i13;
        this.f1945w = i14;
        this.f1940r = -this.f1930f;
        this.f1941s = this.f1939q + this.f1931g;
    }

    @Override // G.N
    public final int b() {
        return this.f1937n;
    }

    @Override // G.N
    public final int c() {
        return this.f1932h.size();
    }

    @Override // G.N
    public final boolean d() {
        return true;
    }

    @Override // G.N
    public final void e() {
        this.f1946x = true;
    }

    @Override // G.N
    public final int f() {
        return this.f1938p;
    }

    @Override // G.N
    public final long g(int i7) {
        return this.f1943u;
    }

    @Override // G.N
    public final int getIndex() {
        return this.f1927a;
    }

    @Override // G.N
    public final Object getKey() {
        return this.f1928b;
    }

    @Override // G.N
    public final Object h(int i7) {
        return ((V) this.f1932h.get(i7)).i();
    }

    @Override // G.N
    public final int i() {
        return this.f1936m;
    }

    @Override // G.N
    public final long j() {
        return this.l;
    }

    @Override // G.N
    public final void k(int i7, int i10, int i11, int i12) {
        a(i7, i10, i11, i12, -1, -1);
    }
}
