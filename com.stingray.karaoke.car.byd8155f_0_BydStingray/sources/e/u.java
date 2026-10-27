package E;

import C0.C0113u;
import C0.U;
import C0.V;
import C0.W;
import G.C0310u;
import G.C0314y;
import G.N;
import S8.AbstractC0727y;
import java.util.List;
import q0.C2164c;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class u implements N {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1422a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f1423b;
    public final boolean c;
    public final g0.e d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g0.f f1424e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final a1.k f1425f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f1426g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f1427h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f1428i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f1429j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final long f1430k;
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f1431m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final androidx.compose.foundation.lazy.layout.a f1432n;
    public final long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f1433p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f1434q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f1435r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f1436s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f1437t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f1438u = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1439v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f1440w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int[] f1441x;

    public u(int i7, List list, boolean z6, g0.e eVar, g0.f fVar, a1.k kVar, boolean z10, int i10, int i11, int i12, long j3, Object obj, Object obj2, androidx.compose.foundation.lazy.layout.a aVar, long j10) {
        this.f1422a = i7;
        this.f1423b = list;
        this.c = z6;
        this.d = eVar;
        this.f1424e = fVar;
        this.f1425f = kVar;
        this.f1426g = z10;
        this.f1427h = i10;
        this.f1428i = i11;
        this.f1429j = i12;
        this.f1430k = j3;
        this.l = obj;
        this.f1431m = obj2;
        this.f1432n = aVar;
        this.o = j10;
        int size = list.size();
        int i13 = 0;
        int iMax = 0;
        for (int i14 = 0; i14 < size; i14++) {
            V v10 = (V) list.get(i14);
            boolean z11 = this.c;
            i13 += z11 ? v10.f871m : v10.l;
            iMax = Math.max(iMax, !z11 ? v10.f871m : v10.l);
        }
        this.f1434q = i13;
        int i15 = i13 + this.f1429j;
        this.f1435r = i15 >= 0 ? i15 : 0;
        this.f1436s = iMax;
        this.f1441x = new int[this.f1423b.size() * 2];
    }

    public final int a(long j3) {
        return (int) (this.c ? j3 & 4294967295L : j3 >> 32);
    }

    @Override // G.N
    public final int b() {
        return 1;
    }

    @Override // G.N
    public final int c() {
        return this.f1423b.size();
    }

    @Override // G.N
    public final boolean d() {
        return this.c;
    }

    @Override // G.N
    public final void e() {
        this.f1437t = true;
    }

    @Override // G.N
    public final int f() {
        return this.f1435r;
    }

    @Override // G.N
    public final long g(int i7) {
        int i10 = i7 * 2;
        int[] iArr = this.f1441x;
        return AbstractC2673a.d(iArr[i10], iArr[i10 + 1]);
    }

    @Override // G.N
    public final int getIndex() {
        return this.f1422a;
    }

    @Override // G.N
    public final Object getKey() {
        return this.l;
    }

    @Override // G.N
    public final Object h(int i7) {
        return ((V) this.f1423b.get(i7)).i();
    }

    @Override // G.N
    public final int i() {
        return 0;
    }

    @Override // G.N
    public final long j() {
        return this.o;
    }

    @Override // G.N
    public final void k(int i7, int i10, int i11, int i12) {
        m(i7, i11, i12);
    }

    public final void l(U u4, boolean z6) {
        List list;
        C2164c c2164c;
        int i7;
        int i10;
        a1.k kVar = a1.k.l;
        if (this.f1438u == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("position() should be called first");
        }
        List list2 = this.f1423b;
        int size = list2.size();
        int i11 = 0;
        while (i11 < size) {
            V v10 = (V) list2.get(i11);
            int i12 = this.f1439v;
            boolean z10 = this.c;
            int i13 = i12 - (z10 ? v10.f871m : v10.l);
            int i14 = this.f1440w;
            long jG = g(i11);
            C0314y c0314yA = this.f1432n.a(i11, this.l);
            if (c0314yA != null) {
                if (z6) {
                    c0314yA.f2630p = jG;
                    list = list2;
                } else {
                    list = list2;
                    if (!a1.h.b(c0314yA.f2630p, C0314y.f2617q)) {
                        jG = c0314yA.f2630p;
                    }
                    long jD = a1.h.d(jG, ((a1.h) c0314yA.o.getValue()).f11033a);
                    if (((a(jG) <= i13 && a(jD) <= i13) || (a(jG) >= i14 && a(jD) >= i14)) && ((Boolean) c0314yA.f2622f.getValue()).booleanValue()) {
                        AbstractC0727y.w(c0314yA.f2619a, null, null, new C0310u(c0314yA, null), 3);
                    }
                    jG = jD;
                }
                c2164c = c0314yA.l;
            } else {
                list = list2;
                c2164c = null;
            }
            if (this.f1426g) {
                if (z10) {
                    i7 = (int) (jG >> 32);
                } else {
                    i7 = (this.f1438u - ((int) (jG >> 32))) - (z10 ? v10.f871m : v10.l);
                }
                if (z10) {
                    i10 = (this.f1438u - ((int) (jG & 4294967295L))) - (z10 ? v10.f871m : v10.l);
                } else {
                    i10 = (int) (jG & 4294967295L);
                }
                jG = AbstractC2673a.d(i7, i10);
            }
            long jD2 = a1.h.d(jG, this.f1430k);
            if (!z6 && c0314yA != null) {
                c0314yA.f2627k = jD2;
            }
            if (z10) {
                if (c2164c != null) {
                    u4.getClass();
                    U.a(u4, v10);
                    v10.k0(a1.h.d(jD2, v10.f873p), 0.0f, c2164c);
                } else {
                    U.i(u4, v10, jD2);
                }
            } else if (c2164c == null) {
                int i15 = W.f875b;
                C0113u c0113u = C0113u.o;
                if (u4.b() == kVar || u4.c() == 0) {
                    U.a(u4, v10);
                    v10.l0(a1.h.d(jD2, v10.f873p), 0.0f, c0113u);
                } else {
                    long jD3 = AbstractC2673a.d((u4.c() - v10.l) - ((int) (jD2 >> 32)), (int) (jD2 & 4294967295L));
                    U.a(u4, v10);
                    v10.l0(a1.h.d(jD3, v10.f873p), 0.0f, c0113u);
                }
                i11++;
                list2 = list;
            } else if (u4.b() == kVar || u4.c() == 0) {
                U.a(u4, v10);
                v10.k0(a1.h.d(jD2, v10.f873p), 0.0f, c2164c);
            } else {
                long jD4 = AbstractC2673a.d((u4.c() - v10.l) - ((int) (jD2 >> 32)), (int) (jD2 & 4294967295L));
                U.a(u4, v10);
                v10.k0(a1.h.d(jD4, v10.f873p), 0.0f, c2164c);
            }
            i11++;
            list2 = list;
        }
    }

    public final void m(int i7, int i10, int i11) {
        int i12;
        this.f1433p = i7;
        boolean z6 = this.c;
        this.f1438u = z6 ? i11 : i10;
        List list = this.f1423b;
        int size = list.size();
        for (int i13 = 0; i13 < size; i13++) {
            V v10 = (V) list.get(i13);
            int i14 = i13 * 2;
            int[] iArr = this.f1441x;
            if (z6) {
                g0.e eVar = this.d;
                if (eVar == null) {
                    throw new IllegalArgumentException("null horizontalAlignment when isVertical == true");
                }
                iArr[i14] = eVar.a(v10.l, i10, this.f1425f);
                iArr[i14 + 1] = i7;
                i12 = v10.f871m;
            } else {
                iArr[i14] = i7;
                int i15 = i14 + 1;
                g0.f fVar = this.f1424e;
                if (fVar == null) {
                    throw new IllegalArgumentException("null verticalAlignment when isVertical == false");
                }
                iArr[i15] = fVar.a(v10.f871m, i11);
                i12 = v10.l;
            }
            i7 += i12;
        }
        this.f1439v = -this.f1427h;
        this.f1440w = this.f1438u + this.f1428i;
    }
}
