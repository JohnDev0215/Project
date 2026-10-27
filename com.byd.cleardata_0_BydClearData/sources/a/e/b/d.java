package a.e.b;

import a.e.b.i;
import a.e.b.k.d;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f179a = 1000;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static e f180b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f181c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static long f182d;
    public static long e;
    private a h;
    a.e.b.b[] k;
    final c r;
    private a u;
    int f = 0;
    private HashMap<String, i> g = null;
    private int i = 32;
    private int j = 32;
    public boolean l = false;
    public boolean m = false;
    private boolean[] n = new boolean[32];
    int o = 1;
    int p = 0;
    private int q = 32;
    private i[] s = new i[f179a];
    private int t = 0;

    interface a {
        void a(a aVar);

        void b(i iVar);

        i c(d dVar, boolean[] zArr);

        void clear();

        i getKey();
    }

    class b extends a.e.b.b {
        public b(c cVar) {
            this.e = new j(this, cVar);
        }
    }

    public d() {
        this.k = null;
        this.k = new a.e.b.b[32];
        C();
        c cVar = new c();
        this.r = cVar;
        this.h = new h(cVar);
        this.u = f181c ? new b(cVar) : new a.e.b.b(cVar);
    }

    private final int B(a aVar, boolean z) {
        if (f180b != null) {
            throw null;
        }
        for (int i = 0; i < this.o; i++) {
            this.n[i] = false;
        }
        boolean z2 = false;
        int i2 = 0;
        while (!z2) {
            if (f180b != null) {
                throw null;
            }
            i2++;
            if (i2 >= this.o * 2) {
                return i2;
            }
            if (aVar.getKey() != null) {
                this.n[aVar.getKey().f192d] = true;
            }
            i iVarC = aVar.c(this, this.n);
            if (iVarC != null) {
                boolean[] zArr = this.n;
                int i3 = iVarC.f192d;
                if (zArr[i3]) {
                    return i2;
                }
                zArr[i3] = true;
            }
            if (iVarC != null) {
                float f = Float.MAX_VALUE;
                int i4 = -1;
                for (int i5 = 0; i5 < this.p; i5++) {
                    a.e.b.b bVar = this.k[i5];
                    if (bVar.f171a.k != i.a.UNRESTRICTED && !bVar.f && bVar.t(iVarC)) {
                        float fG = bVar.e.g(iVarC);
                        if (fG < 0.0f) {
                            float f2 = (-bVar.f172b) / fG;
                            if (f2 < f) {
                                i4 = i5;
                                f = f2;
                            }
                        }
                    }
                }
                if (i4 > -1) {
                    a.e.b.b bVar2 = this.k[i4];
                    bVar2.f171a.e = -1;
                    if (f180b != null) {
                        throw null;
                    }
                    bVar2.y(iVarC);
                    i iVar = bVar2.f171a;
                    iVar.e = i4;
                    iVar.g(bVar2);
                } else {
                    continue;
                }
            } else {
                z2 = true;
            }
        }
        return i2;
    }

    private void C() {
        int i = 0;
        if (f181c) {
            while (true) {
                a.e.b.b[] bVarArr = this.k;
                if (i >= bVarArr.length) {
                    return;
                }
                a.e.b.b bVar = bVarArr[i];
                if (bVar != null) {
                    this.r.f175a.a(bVar);
                }
                this.k[i] = null;
                i++;
            }
        } else {
            while (true) {
                a.e.b.b[] bVarArr2 = this.k;
                if (i >= bVarArr2.length) {
                    return;
                }
                a.e.b.b bVar2 = bVarArr2[i];
                if (bVar2 != null) {
                    this.r.f176b.a(bVar2);
                }
                this.k[i] = null;
                i++;
            }
        }
    }

    private i a(i.a aVar, String str) {
        i iVarB = this.r.f177c.b();
        if (iVarB == null) {
            iVarB = new i(aVar, str);
        } else {
            iVarB.d();
        }
        iVarB.f(aVar, str);
        int i = this.t;
        int i2 = f179a;
        if (i >= i2) {
            int i3 = i2 * 2;
            f179a = i3;
            this.s = (i[]) Arrays.copyOf(this.s, i3);
        }
        i[] iVarArr = this.s;
        int i4 = this.t;
        this.t = i4 + 1;
        iVarArr[i4] = iVarB;
        return iVarB;
    }

    private final void l(a.e.b.b bVar) {
        f<a.e.b.b> fVar;
        a.e.b.b bVar2;
        if (f181c) {
            a.e.b.b[] bVarArr = this.k;
            int i = this.p;
            if (bVarArr[i] != null) {
                fVar = this.r.f175a;
                bVar2 = bVarArr[i];
                fVar.a(bVar2);
            }
        } else {
            a.e.b.b[] bVarArr2 = this.k;
            int i2 = this.p;
            if (bVarArr2[i2] != null) {
                fVar = this.r.f176b;
                bVar2 = bVarArr2[i2];
                fVar.a(bVar2);
            }
        }
        a.e.b.b[] bVarArr3 = this.k;
        int i3 = this.p;
        bVarArr3[i3] = bVar;
        i iVar = bVar.f171a;
        iVar.e = i3;
        this.p = i3 + 1;
        iVar.g(bVar);
    }

    private void n() {
        for (int i = 0; i < this.p; i++) {
            a.e.b.b bVar = this.k[i];
            bVar.f171a.g = bVar.f172b;
        }
    }

    public static a.e.b.b s(d dVar, i iVar, i iVar2, float f) {
        return dVar.r().j(iVar, iVar2, f);
    }

    private int u(a aVar) {
        float f;
        boolean z;
        int i = 0;
        while (true) {
            f = 0.0f;
            if (i >= this.p) {
                z = false;
                break;
            }
            a.e.b.b[] bVarArr = this.k;
            if (bVarArr[i].f171a.k != i.a.UNRESTRICTED && bVarArr[i].f172b < 0.0f) {
                z = true;
                break;
            }
            i++;
        }
        if (!z) {
            return 0;
        }
        boolean z2 = false;
        int i2 = 0;
        while (!z2) {
            if (f180b != null) {
                throw null;
            }
            i2++;
            float f2 = Float.MAX_VALUE;
            int i3 = -1;
            int i4 = -1;
            int i5 = 0;
            int i6 = 0;
            while (i5 < this.p) {
                a.e.b.b bVar = this.k[i5];
                if (bVar.f171a.k != i.a.UNRESTRICTED && !bVar.f && bVar.f172b < f) {
                    int i7 = 1;
                    while (i7 < this.o) {
                        i iVar = this.r.f178d[i7];
                        float fG = bVar.e.g(iVar);
                        if (fG > f) {
                            for (int i8 = 0; i8 < 9; i8++) {
                                float f3 = iVar.i[i8] / fG;
                                if ((f3 < f2 && i8 == i6) || i8 > i6) {
                                    i6 = i8;
                                    f2 = f3;
                                    i3 = i5;
                                    i4 = i7;
                                }
                            }
                        }
                        i7++;
                        f = 0.0f;
                    }
                }
                i5++;
                f = 0.0f;
            }
            if (i3 != -1) {
                a.e.b.b bVar2 = this.k[i3];
                bVar2.f171a.e = -1;
                if (f180b != null) {
                    throw null;
                }
                bVar2.y(this.r.f178d[i4]);
                i iVar2 = bVar2.f171a;
                iVar2.e = i3;
                iVar2.g(bVar2);
            } else {
                z2 = true;
            }
            if (i2 > this.o / 2) {
                z2 = true;
            }
            f = 0.0f;
        }
        return i2;
    }

    public static e w() {
        return f180b;
    }

    private void y() {
        int i = this.i * 2;
        this.i = i;
        this.k = (a.e.b.b[]) Arrays.copyOf(this.k, i);
        c cVar = this.r;
        cVar.f178d = (i[]) Arrays.copyOf(cVar.f178d, this.i);
        int i2 = this.i;
        this.n = new boolean[i2];
        this.j = i2;
        this.q = i2;
        if (f180b != null) {
            throw null;
        }
    }

    void A(a aVar) {
        if (f180b != null) {
            throw null;
        }
        u(aVar);
        B(aVar, false);
        n();
    }

    public void D() {
        c cVar;
        int i = 0;
        while (true) {
            cVar = this.r;
            i[] iVarArr = cVar.f178d;
            if (i >= iVarArr.length) {
                break;
            }
            i iVar = iVarArr[i];
            if (iVar != null) {
                iVar.d();
            }
            i++;
        }
        cVar.f177c.c(this.s, this.t);
        this.t = 0;
        Arrays.fill(this.r.f178d, (Object) null);
        HashMap<String, i> map = this.g;
        if (map != null) {
            map.clear();
        }
        this.f = 0;
        this.h.clear();
        this.o = 1;
        for (int i2 = 0; i2 < this.p; i2++) {
            this.k[i2].f173c = false;
        }
        C();
        this.p = 0;
        this.u = f181c ? new b(this.r) : new a.e.b.b(this.r);
    }

    public void b(a.e.b.k.e eVar, a.e.b.k.e eVar2, float f, int i) {
        d.b bVar = d.b.LEFT;
        i iVarQ = q(eVar.k(bVar));
        d.b bVar2 = d.b.TOP;
        i iVarQ2 = q(eVar.k(bVar2));
        d.b bVar3 = d.b.RIGHT;
        i iVarQ3 = q(eVar.k(bVar3));
        d.b bVar4 = d.b.BOTTOM;
        i iVarQ4 = q(eVar.k(bVar4));
        i iVarQ5 = q(eVar2.k(bVar));
        i iVarQ6 = q(eVar2.k(bVar2));
        i iVarQ7 = q(eVar2.k(bVar3));
        i iVarQ8 = q(eVar2.k(bVar4));
        a.e.b.b bVarR = r();
        double d2 = f;
        double d3 = i;
        bVarR.q(iVarQ2, iVarQ4, iVarQ6, iVarQ8, (float) (Math.sin(d2) * d3));
        d(bVarR);
        a.e.b.b bVarR2 = r();
        bVarR2.q(iVarQ, iVarQ3, iVarQ5, iVarQ7, (float) (Math.cos(d2) * d3));
        d(bVarR2);
    }

    public void c(i iVar, i iVar2, int i, float f, i iVar3, i iVar4, int i2, int i3) {
        a.e.b.b bVarR = r();
        bVarR.h(iVar, iVar2, i, f, iVar3, iVar4, i2);
        if (i3 != 8) {
            bVarR.d(this, i3);
        }
        d(bVarR);
    }

    public void d(a.e.b.b bVar) {
        i iVarW;
        if (bVar == null) {
            return;
        }
        if (f180b != null) {
            throw null;
        }
        boolean z = true;
        if (this.p + 1 >= this.q || this.o + 1 >= this.j) {
            y();
        }
        boolean z2 = false;
        if (!bVar.f) {
            bVar.D(this);
            if (bVar.u()) {
                return;
            }
            bVar.r();
            if (bVar.f(this)) {
                i iVarP = p();
                bVar.f171a = iVarP;
                l(bVar);
                this.u.a(bVar);
                B(this.u, true);
                if (iVarP.e == -1) {
                    if (bVar.f171a == iVarP && (iVarW = bVar.w(iVarP)) != null) {
                        if (f180b != null) {
                            throw null;
                        }
                        bVar.y(iVarW);
                    }
                    if (!bVar.f) {
                        bVar.f171a.g(bVar);
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

    public a.e.b.b e(i iVar, i iVar2, int i, int i2) {
        if (i2 == 8 && iVar2.h && iVar.e == -1) {
            iVar.e(this, iVar2.g + i);
            return null;
        }
        a.e.b.b bVarR = r();
        bVarR.n(iVar, iVar2, i);
        if (i2 != 8) {
            bVarR.d(this, i2);
        }
        d(bVarR);
        return bVarR;
    }

    public void f(i iVar, int i) {
        a.e.b.b bVarR;
        int i2 = iVar.e;
        if (i2 == -1) {
            iVar.e(this, i);
            return;
        }
        if (i2 != -1) {
            a.e.b.b bVar = this.k[i2];
            if (!bVar.f) {
                if (bVar.e.k() == 0) {
                    bVar.f = true;
                } else {
                    bVarR = r();
                    bVarR.m(iVar, i);
                }
            }
            bVar.f172b = i;
            return;
        }
        bVarR = r();
        bVarR.i(iVar, i);
        d(bVarR);
    }

    public void g(i iVar, i iVar2, int i, boolean z) {
        a.e.b.b bVarR = r();
        i iVarT = t();
        iVarT.f = 0;
        bVarR.o(iVar, iVar2, iVarT, i);
        d(bVarR);
    }

    public void h(i iVar, i iVar2, int i, int i2) {
        a.e.b.b bVarR = r();
        i iVarT = t();
        iVarT.f = 0;
        bVarR.o(iVar, iVar2, iVarT, i);
        if (i2 != 8) {
            m(bVarR, (int) (bVarR.e.g(iVarT) * (-1.0f)), i2);
        }
        d(bVarR);
    }

    public void i(i iVar, i iVar2, int i, boolean z) {
        a.e.b.b bVarR = r();
        i iVarT = t();
        iVarT.f = 0;
        bVarR.p(iVar, iVar2, iVarT, i);
        d(bVarR);
    }

    public void j(i iVar, i iVar2, int i, int i2) {
        a.e.b.b bVarR = r();
        i iVarT = t();
        iVarT.f = 0;
        bVarR.p(iVar, iVar2, iVarT, i);
        if (i2 != 8) {
            m(bVarR, (int) (bVarR.e.g(iVarT) * (-1.0f)), i2);
        }
        d(bVarR);
    }

    public void k(i iVar, i iVar2, i iVar3, i iVar4, float f, int i) {
        a.e.b.b bVarR = r();
        bVarR.k(iVar, iVar2, iVar3, iVar4, f);
        if (i != 8) {
            bVarR.d(this, i);
        }
        d(bVarR);
    }

    void m(a.e.b.b bVar, int i, int i2) {
        bVar.e(o(i2, null), i);
    }

    public i o(int i, String str) {
        if (f180b != null) {
            throw null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        i iVarA = a(i.a.ERROR, str);
        int i2 = this.f + 1;
        this.f = i2;
        this.o++;
        iVarA.f192d = i2;
        iVarA.f = i;
        this.r.f178d[i2] = iVarA;
        this.h.b(iVarA);
        return iVarA;
    }

    public i p() {
        if (f180b != null) {
            throw null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        i iVarA = a(i.a.SLACK, null);
        int i = this.f + 1;
        this.f = i;
        this.o++;
        iVarA.f192d = i;
        this.r.f178d[i] = iVarA;
        return iVarA;
    }

    public i q(Object obj) {
        i iVarE = null;
        if (obj == null) {
            return null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        if (obj instanceof a.e.b.k.d) {
            a.e.b.k.d dVar = (a.e.b.k.d) obj;
            iVarE = dVar.e();
            if (iVarE == null) {
                dVar.l(this.r);
                iVarE = dVar.e();
            }
            int i = iVarE.f192d;
            if (i == -1 || i > this.f || this.r.f178d[i] == null) {
                if (i != -1) {
                    iVarE.d();
                }
                int i2 = this.f + 1;
                this.f = i2;
                this.o++;
                iVarE.f192d = i2;
                iVarE.k = i.a.UNRESTRICTED;
                this.r.f178d[i2] = iVarE;
            }
        }
        return iVarE;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0038 A[PHI: r0
  0x0038: PHI (r0v6 a.e.b.b) = (r0v4 a.e.b.b), (r0v11 a.e.b.b) binds: [B:8:0x0029, B:5:0x0010] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public a.e.b.b r() {
        /*
            r5 = this;
            boolean r0 = a.e.b.d.f181c
            r1 = 1
            if (r0 == 0) goto L1f
            a.e.b.c r0 = r5.r
            a.e.b.f<a.e.b.b> r0 = r0.f175a
            java.lang.Object r0 = r0.b()
            a.e.b.b r0 = (a.e.b.b) r0
            if (r0 != 0) goto L38
            a.e.b.d$b r0 = new a.e.b.d$b
            a.e.b.c r3 = r5.r
            r0.<init>(r3)
            long r3 = a.e.b.d.e
            long r3 = r3 + r1
            a.e.b.d.e = r3
            goto L3b
        L1f:
            a.e.b.c r0 = r5.r
            a.e.b.f<a.e.b.b> r0 = r0.f176b
            java.lang.Object r0 = r0.b()
            a.e.b.b r0 = (a.e.b.b) r0
            if (r0 != 0) goto L38
            a.e.b.b r0 = new a.e.b.b
            a.e.b.c r3 = r5.r
            r0.<init>(r3)
            long r3 = a.e.b.d.f182d
            long r3 = r3 + r1
            a.e.b.d.f182d = r3
            goto L3b
        L38:
            r0.z()
        L3b:
            a.e.b.i.b()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: a.e.b.d.r():a.e.b.b");
    }

    public i t() {
        if (f180b != null) {
            throw null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        i iVarA = a(i.a.SLACK, null);
        int i = this.f + 1;
        this.f = i;
        this.o++;
        iVarA.f192d = i;
        this.r.f178d[i] = iVarA;
        return iVarA;
    }

    public c v() {
        return this.r;
    }

    public int x(Object obj) {
        i iVarE = ((a.e.b.k.d) obj).e();
        if (iVarE != null) {
            return (int) (iVarE.g + 0.5f);
        }
        return 0;
    }

    public void z() {
        e eVar = f180b;
        if (eVar != null) {
            throw null;
        }
        if (this.l || this.m) {
            if (eVar != null) {
                throw null;
            }
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= this.p) {
                    z = true;
                    break;
                } else if (!this.k[i].f) {
                    break;
                } else {
                    i++;
                }
            }
            if (z) {
                if (f180b != null) {
                    throw null;
                }
                n();
                return;
            }
        }
        A(this.h);
    }
}
