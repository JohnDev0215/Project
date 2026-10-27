package b.e.b;

import b.e.b.i;
import b.e.b.k.d;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: LinearSystem.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a */
    private static int f2370a = 1000;

    /* JADX INFO: renamed from: b */
    public static e f2371b = null;

    /* JADX INFO: renamed from: c */
    public static boolean f2372c = true;

    /* JADX INFO: renamed from: d */
    public static long f2373d;

    /* JADX INFO: renamed from: e */
    public static long f2374e;

    /* JADX INFO: renamed from: h */
    private a f2377h;
    b.e.b.b[] k;
    final c r;
    private a u;

    /* JADX INFO: renamed from: f */
    int f2375f = 0;

    /* JADX INFO: renamed from: g */
    private HashMap<String, i> f2376g = null;

    /* JADX INFO: renamed from: i */
    private int f2378i = 32;
    private int j = 32;
    public boolean l = false;
    public boolean m = false;
    private boolean[] n = new boolean[32];
    int o = 1;
    int p = 0;
    private int q = 32;
    private i[] s = new i[f2370a];
    private int t = 0;

    /* JADX INFO: compiled from: LinearSystem.java */
    interface a {
        void a(a aVar);

        void b(i iVar);

        i c(d dVar, boolean[] zArr);

        void clear();

        i getKey();
    }

    /* JADX INFO: compiled from: LinearSystem.java */
    class b extends b.e.b.b {
        public b(c cVar) {
            this.f2364e = new j(this, cVar);
        }
    }

    public d() {
        this.k = null;
        this.k = new b.e.b.b[32];
        C();
        c cVar = new c();
        this.r = cVar;
        this.f2377h = new h(cVar);
        if (f2372c) {
            this.u = new b(cVar);
        } else {
            this.u = new b.e.b.b(cVar);
        }
    }

    private final int B(a aVar, boolean z) {
        if (f2371b != null) {
            throw null;
        }
        for (int i2 = 0; i2 < this.o; i2++) {
            this.n[i2] = false;
        }
        boolean z2 = false;
        int i3 = 0;
        while (!z2) {
            if (f2371b != null) {
                throw null;
            }
            i3++;
            if (i3 >= this.o * 2) {
                return i3;
            }
            if (aVar.getKey() != null) {
                this.n[aVar.getKey().f2392d] = true;
            }
            i iVarC = aVar.c(this, this.n);
            if (iVarC != null) {
                boolean[] zArr = this.n;
                int i4 = iVarC.f2392d;
                if (zArr[i4]) {
                    return i3;
                }
                zArr[i4] = true;
            }
            if (iVarC != null) {
                float f2 = Float.MAX_VALUE;
                int i5 = -1;
                for (int i6 = 0; i6 < this.p; i6++) {
                    b.e.b.b bVar = this.k[i6];
                    if (bVar.f2360a.k != i.a.UNRESTRICTED && !bVar.f2365f && bVar.t(iVarC)) {
                        float fG = bVar.f2364e.g(iVarC);
                        if (fG < 0.0f) {
                            float f3 = (-bVar.f2361b) / fG;
                            if (f3 < f2) {
                                i5 = i6;
                                f2 = f3;
                            }
                        }
                    }
                }
                if (i5 > -1) {
                    b.e.b.b bVar2 = this.k[i5];
                    bVar2.f2360a.f2393e = -1;
                    if (f2371b != null) {
                        throw null;
                    }
                    bVar2.y(iVarC);
                    i iVar = bVar2.f2360a;
                    iVar.f2393e = i5;
                    iVar.g(bVar2);
                } else {
                    continue;
                }
            } else {
                z2 = true;
            }
        }
        return i3;
    }

    private void C() {
        int i2 = 0;
        if (f2372c) {
            while (true) {
                b.e.b.b[] bVarArr = this.k;
                if (i2 >= bVarArr.length) {
                    return;
                }
                b.e.b.b bVar = bVarArr[i2];
                if (bVar != null) {
                    this.r.f2366a.a(bVar);
                }
                this.k[i2] = null;
                i2++;
            }
        } else {
            while (true) {
                b.e.b.b[] bVarArr2 = this.k;
                if (i2 >= bVarArr2.length) {
                    return;
                }
                b.e.b.b bVar2 = bVarArr2[i2];
                if (bVar2 != null) {
                    this.r.f2367b.a(bVar2);
                }
                this.k[i2] = null;
                i2++;
            }
        }
    }

    private i a(i.a aVar, String str) {
        i iVarB = this.r.f2368c.b();
        if (iVarB == null) {
            iVarB = new i(aVar, str);
            iVarB.f(aVar, str);
        } else {
            iVarB.d();
            iVarB.f(aVar, str);
        }
        int i2 = this.t;
        int i3 = f2370a;
        if (i2 >= i3) {
            int i4 = i3 * 2;
            f2370a = i4;
            this.s = (i[]) Arrays.copyOf(this.s, i4);
        }
        i[] iVarArr = this.s;
        int i5 = this.t;
        this.t = i5 + 1;
        iVarArr[i5] = iVarB;
        return iVarB;
    }

    private final void l(b.e.b.b bVar) {
        if (f2372c) {
            b.e.b.b[] bVarArr = this.k;
            int i2 = this.p;
            if (bVarArr[i2] != null) {
                this.r.f2366a.a(bVarArr[i2]);
            }
        } else {
            b.e.b.b[] bVarArr2 = this.k;
            int i3 = this.p;
            if (bVarArr2[i3] != null) {
                this.r.f2367b.a(bVarArr2[i3]);
            }
        }
        b.e.b.b[] bVarArr3 = this.k;
        int i4 = this.p;
        bVarArr3[i4] = bVar;
        i iVar = bVar.f2360a;
        iVar.f2393e = i4;
        this.p = i4 + 1;
        iVar.g(bVar);
    }

    private void n() {
        for (int i2 = 0; i2 < this.p; i2++) {
            b.e.b.b bVar = this.k[i2];
            bVar.f2360a.f2395g = bVar.f2361b;
        }
    }

    public static b.e.b.b s(d dVar, i iVar, i iVar2, float f2) {
        return dVar.r().j(iVar, iVar2, f2);
    }

    private int u(a aVar) {
        float f2;
        boolean z;
        int i2 = 0;
        while (true) {
            f2 = 0.0f;
            if (i2 >= this.p) {
                z = false;
                break;
            }
            b.e.b.b[] bVarArr = this.k;
            if (bVarArr[i2].f2360a.k != i.a.UNRESTRICTED && bVarArr[i2].f2361b < 0.0f) {
                z = true;
                break;
            }
            i2++;
        }
        if (!z) {
            return 0;
        }
        boolean z2 = false;
        int i3 = 0;
        while (!z2) {
            if (f2371b != null) {
                throw null;
            }
            i3++;
            float f3 = Float.MAX_VALUE;
            int i4 = -1;
            int i5 = -1;
            int i6 = 0;
            int i7 = 0;
            while (i6 < this.p) {
                b.e.b.b bVar = this.k[i6];
                if (bVar.f2360a.k != i.a.UNRESTRICTED && !bVar.f2365f && bVar.f2361b < f2) {
                    int i8 = 1;
                    while (i8 < this.o) {
                        i iVar = this.r.f2369d[i8];
                        float fG = bVar.f2364e.g(iVar);
                        if (fG > f2) {
                            for (int i9 = 0; i9 < 9; i9++) {
                                float f4 = iVar.f2397i[i9] / fG;
                                if ((f4 < f3 && i9 == i7) || i9 > i7) {
                                    i7 = i9;
                                    f3 = f4;
                                    i4 = i6;
                                    i5 = i8;
                                }
                            }
                        }
                        i8++;
                        f2 = 0.0f;
                    }
                }
                i6++;
                f2 = 0.0f;
            }
            if (i4 != -1) {
                b.e.b.b bVar2 = this.k[i4];
                bVar2.f2360a.f2393e = -1;
                if (f2371b != null) {
                    throw null;
                }
                bVar2.y(this.r.f2369d[i5]);
                i iVar2 = bVar2.f2360a;
                iVar2.f2393e = i4;
                iVar2.g(bVar2);
            } else {
                z2 = true;
            }
            if (i3 > this.o / 2) {
                z2 = true;
            }
            f2 = 0.0f;
        }
        return i3;
    }

    public static e w() {
        return f2371b;
    }

    private void y() {
        int i2 = this.f2378i * 2;
        this.f2378i = i2;
        this.k = (b.e.b.b[]) Arrays.copyOf(this.k, i2);
        c cVar = this.r;
        cVar.f2369d = (i[]) Arrays.copyOf(cVar.f2369d, this.f2378i);
        int i3 = this.f2378i;
        this.n = new boolean[i3];
        this.j = i3;
        this.q = i3;
        if (f2371b != null) {
            throw null;
        }
    }

    void A(a aVar) {
        if (f2371b != null) {
            throw null;
        }
        u(aVar);
        B(aVar, false);
        n();
    }

    public void D() {
        c cVar;
        int i2 = 0;
        while (true) {
            cVar = this.r;
            i[] iVarArr = cVar.f2369d;
            if (i2 >= iVarArr.length) {
                break;
            }
            i iVar = iVarArr[i2];
            if (iVar != null) {
                iVar.d();
            }
            i2++;
        }
        cVar.f2368c.c(this.s, this.t);
        this.t = 0;
        Arrays.fill(this.r.f2369d, (Object) null);
        HashMap<String, i> map = this.f2376g;
        if (map != null) {
            map.clear();
        }
        this.f2375f = 0;
        this.f2377h.clear();
        this.o = 1;
        for (int i3 = 0; i3 < this.p; i3++) {
            this.k[i3].f2362c = false;
        }
        C();
        this.p = 0;
        if (f2372c) {
            this.u = new b(this.r);
        } else {
            this.u = new b.e.b.b(this.r);
        }
    }

    public void b(b.e.b.k.e eVar, b.e.b.k.e eVar2, float f2, int i2) {
        d.b bVar = d.b.LEFT;
        i iVarQ = q(eVar.n(bVar));
        d.b bVar2 = d.b.TOP;
        i iVarQ2 = q(eVar.n(bVar2));
        d.b bVar3 = d.b.RIGHT;
        i iVarQ3 = q(eVar.n(bVar3));
        d.b bVar4 = d.b.BOTTOM;
        i iVarQ4 = q(eVar.n(bVar4));
        i iVarQ5 = q(eVar2.n(bVar));
        i iVarQ6 = q(eVar2.n(bVar2));
        i iVarQ7 = q(eVar2.n(bVar3));
        i iVarQ8 = q(eVar2.n(bVar4));
        b.e.b.b bVarR = r();
        double d2 = f2;
        double d3 = i2;
        bVarR.q(iVarQ2, iVarQ4, iVarQ6, iVarQ8, (float) (Math.sin(d2) * d3));
        d(bVarR);
        b.e.b.b bVarR2 = r();
        bVarR2.q(iVarQ, iVarQ3, iVarQ5, iVarQ7, (float) (Math.cos(d2) * d3));
        d(bVarR2);
    }

    public void c(i iVar, i iVar2, int i2, float f2, i iVar3, i iVar4, int i3, int i4) {
        b.e.b.b bVarR = r();
        bVarR.h(iVar, iVar2, i2, f2, iVar3, iVar4, i3);
        if (i4 != 8) {
            bVarR.d(this, i4);
        }
        d(bVarR);
    }

    public void d(b.e.b.b bVar) {
        i iVarW;
        if (bVar == null) {
            return;
        }
        if (f2371b != null) {
            throw null;
        }
        boolean z = true;
        if (this.p + 1 >= this.q || this.o + 1 >= this.j) {
            y();
        }
        boolean z2 = false;
        if (!bVar.f2365f) {
            bVar.D(this);
            if (bVar.u()) {
                return;
            }
            bVar.r();
            if (bVar.f(this)) {
                i iVarP = p();
                bVar.f2360a = iVarP;
                l(bVar);
                this.u.a(bVar);
                B(this.u, true);
                if (iVarP.f2393e == -1) {
                    if (bVar.f2360a == iVarP && (iVarW = bVar.w(iVarP)) != null) {
                        if (f2371b != null) {
                            throw null;
                        }
                        bVar.y(iVarW);
                    }
                    if (!bVar.f2365f) {
                        bVar.f2360a.g(bVar);
                    }
                    this.p--;
                }
            } else {
                z = false;
            }
            if (!bVar.s()) {
                return;
            } else {
                z2 = z;
            }
        }
        if (z2) {
            return;
        }
        l(bVar);
    }

    public b.e.b.b e(i iVar, i iVar2, int i2, int i3) {
        if (i3 == 8 && iVar2.f2396h && iVar.f2393e == -1) {
            iVar.e(this, iVar2.f2395g + i2);
            return null;
        }
        b.e.b.b bVarR = r();
        bVarR.n(iVar, iVar2, i2);
        if (i3 != 8) {
            bVarR.d(this, i3);
        }
        d(bVarR);
        return bVarR;
    }

    public void f(i iVar, int i2) {
        int i3 = iVar.f2393e;
        if (i3 == -1) {
            iVar.e(this, i2);
            return;
        }
        if (i3 == -1) {
            b.e.b.b bVarR = r();
            bVarR.i(iVar, i2);
            d(bVarR);
            return;
        }
        b.e.b.b bVar = this.k[i3];
        if (bVar.f2365f) {
            bVar.f2361b = i2;
            return;
        }
        if (bVar.f2364e.k() == 0) {
            bVar.f2365f = true;
            bVar.f2361b = i2;
        } else {
            b.e.b.b bVarR2 = r();
            bVarR2.m(iVar, i2);
            d(bVarR2);
        }
    }

    public void g(i iVar, i iVar2, int i2, boolean z) {
        b.e.b.b bVarR = r();
        i iVarT = t();
        iVarT.f2394f = 0;
        bVarR.o(iVar, iVar2, iVarT, i2);
        d(bVarR);
    }

    public void h(i iVar, i iVar2, int i2, int i3) {
        b.e.b.b bVarR = r();
        i iVarT = t();
        iVarT.f2394f = 0;
        bVarR.o(iVar, iVar2, iVarT, i2);
        if (i3 != 8) {
            m(bVarR, (int) (bVarR.f2364e.g(iVarT) * (-1.0f)), i3);
        }
        d(bVarR);
    }

    public void i(i iVar, i iVar2, int i2, boolean z) {
        b.e.b.b bVarR = r();
        i iVarT = t();
        iVarT.f2394f = 0;
        bVarR.p(iVar, iVar2, iVarT, i2);
        d(bVarR);
    }

    public void j(i iVar, i iVar2, int i2, int i3) {
        b.e.b.b bVarR = r();
        i iVarT = t();
        iVarT.f2394f = 0;
        bVarR.p(iVar, iVar2, iVarT, i2);
        if (i3 != 8) {
            m(bVarR, (int) (bVarR.f2364e.g(iVarT) * (-1.0f)), i3);
        }
        d(bVarR);
    }

    public void k(i iVar, i iVar2, i iVar3, i iVar4, float f2, int i2) {
        b.e.b.b bVarR = r();
        bVarR.k(iVar, iVar2, iVar3, iVar4, f2);
        if (i2 != 8) {
            bVarR.d(this, i2);
        }
        d(bVarR);
    }

    void m(b.e.b.b bVar, int i2, int i3) {
        bVar.e(o(i3, null), i2);
    }

    public i o(int i2, String str) {
        if (f2371b != null) {
            throw null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        i iVarA = a(i.a.ERROR, str);
        int i3 = this.f2375f + 1;
        this.f2375f = i3;
        this.o++;
        iVarA.f2392d = i3;
        iVarA.f2394f = i2;
        this.r.f2369d[i3] = iVarA;
        this.f2377h.b(iVarA);
        return iVarA;
    }

    public i p() {
        if (f2371b != null) {
            throw null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        i iVarA = a(i.a.SLACK, null);
        int i2 = this.f2375f + 1;
        this.f2375f = i2;
        this.o++;
        iVarA.f2392d = i2;
        this.r.f2369d[i2] = iVarA;
        return iVarA;
    }

    public i q(Object obj) {
        i iVarF = null;
        if (obj == null) {
            return null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        if (obj instanceof b.e.b.k.d) {
            b.e.b.k.d dVar = (b.e.b.k.d) obj;
            iVarF = dVar.f();
            if (iVarF == null) {
                dVar.m(this.r);
                iVarF = dVar.f();
            }
            int i2 = iVarF.f2392d;
            if (i2 == -1 || i2 > this.f2375f || this.r.f2369d[i2] == null) {
                if (i2 != -1) {
                    iVarF.d();
                }
                int i3 = this.f2375f + 1;
                this.f2375f = i3;
                this.o++;
                iVarF.f2392d = i3;
                iVarF.k = i.a.UNRESTRICTED;
                this.r.f2369d[i3] = iVarF;
            }
        }
        return iVarF;
    }

    public b.e.b.b r() {
        b.e.b.b bVarB;
        if (f2372c) {
            bVarB = this.r.f2366a.b();
            if (bVarB == null) {
                bVarB = new b(this.r);
                f2374e++;
            } else {
                bVarB.z();
            }
        } else {
            bVarB = this.r.f2367b.b();
            if (bVarB == null) {
                bVarB = new b.e.b.b(this.r);
                f2373d++;
            } else {
                bVarB.z();
            }
        }
        i.b();
        return bVarB;
    }

    public i t() {
        if (f2371b != null) {
            throw null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        i iVarA = a(i.a.SLACK, null);
        int i2 = this.f2375f + 1;
        this.f2375f = i2;
        this.o++;
        iVarA.f2392d = i2;
        this.r.f2369d[i2] = iVarA;
        return iVarA;
    }

    public c v() {
        return this.r;
    }

    public int x(Object obj) {
        i iVarF = ((b.e.b.k.d) obj).f();
        if (iVarF != null) {
            return (int) (iVarF.f2395g + 0.5f);
        }
        return 0;
    }

    public void z() {
        e eVar = f2371b;
        if (eVar != null) {
            throw null;
        }
        if (!this.l && !this.m) {
            A(this.f2377h);
            return;
        }
        if (eVar != null) {
            throw null;
        }
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= this.p) {
                z = true;
                break;
            } else if (!this.k[i2].f2365f) {
                break;
            } else {
                i2++;
            }
        }
        if (!z) {
            A(this.f2377h);
        } else {
            if (f2371b != null) {
                throw null;
            }
            n();
        }
    }
}
