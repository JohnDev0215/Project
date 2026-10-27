package b.e.b;

import b.e.b.d;
import b.e.b.i;
import java.util.ArrayList;

/* JADX INFO: compiled from: ArrayRow.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements d.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f2364e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    i f2360a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    float f2361b = 0.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f2362c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ArrayList<i> f2363d = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    boolean f2365f = false;

    /* JADX INFO: compiled from: ArrayRow.java */
    public interface a {
        float a(int i2);

        float b(b bVar, boolean z);

        void c(i iVar, float f2);

        void clear();

        float d(i iVar, boolean z);

        void e(i iVar, float f2, boolean z);

        i f(int i2);

        float g(i iVar);

        boolean h(i iVar);

        void i(float f2);

        void j();

        int k();
    }

    public b() {
    }

    private boolean v(i iVar, d dVar) {
        return iVar.n <= 1;
    }

    private i x(boolean[] zArr, i iVar) {
        i.a aVar;
        int iK = this.f2364e.k();
        i iVar2 = null;
        float f2 = 0.0f;
        for (int i2 = 0; i2 < iK; i2++) {
            float fA = this.f2364e.a(i2);
            if (fA < 0.0f) {
                i iVarF = this.f2364e.f(i2);
                if ((zArr == null || !zArr[iVarF.f2392d]) && iVarF != iVar && (((aVar = iVarF.k) == i.a.SLACK || aVar == i.a.ERROR) && fA < f2)) {
                    f2 = fA;
                    iVar2 = iVarF;
                }
            }
        }
        return iVar2;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00cf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    java.lang.String A() {
        /*
            Method dump skipped, instruction units count: 255
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.e.b.b.A():java.lang.String");
    }

    public void B(d dVar, i iVar, boolean z) {
        if (iVar.f2396h) {
            this.f2361b += iVar.f2395g * this.f2364e.g(iVar);
            this.f2364e.d(iVar, z);
            if (z) {
                iVar.c(this);
            }
        }
    }

    public void C(b bVar, boolean z) {
        this.f2361b += bVar.f2361b * this.f2364e.b(bVar, z);
        if (z) {
            bVar.f2360a.c(this);
        }
    }

    public void D(d dVar) {
        if (dVar.k.length == 0) {
            return;
        }
        boolean z = false;
        while (!z) {
            int iK = this.f2364e.k();
            for (int i2 = 0; i2 < iK; i2++) {
                i iVarF = this.f2364e.f(i2);
                if (iVarF.f2393e != -1 || iVarF.f2396h) {
                    this.f2363d.add(iVarF);
                }
            }
            if (this.f2363d.size() > 0) {
                for (i iVar : this.f2363d) {
                    if (iVar.f2396h) {
                        B(dVar, iVar, true);
                    } else {
                        C(dVar.k[iVar.f2393e], true);
                    }
                }
                this.f2363d.clear();
            } else {
                z = true;
            }
        }
    }

    @Override // b.e.b.d.a
    public void a(d.a aVar) {
        if (aVar instanceof b) {
            b bVar = (b) aVar;
            this.f2360a = null;
            this.f2364e.clear();
            for (int i2 = 0; i2 < bVar.f2364e.k(); i2++) {
                this.f2364e.e(bVar.f2364e.f(i2), bVar.f2364e.a(i2), true);
            }
        }
    }

    @Override // b.e.b.d.a
    public void b(i iVar) {
        int i2 = iVar.f2394f;
        float f2 = 1.0f;
        if (i2 != 1) {
            if (i2 == 2) {
                f2 = 1000.0f;
            } else if (i2 == 3) {
                f2 = 1000000.0f;
            } else if (i2 == 4) {
                f2 = 1.0E9f;
            } else if (i2 == 5) {
                f2 = 1.0E12f;
            }
        }
        this.f2364e.c(iVar, f2);
    }

    @Override // b.e.b.d.a
    public i c(d dVar, boolean[] zArr) {
        return x(zArr, null);
    }

    @Override // b.e.b.d.a
    public void clear() {
        this.f2364e.clear();
        this.f2360a = null;
        this.f2361b = 0.0f;
    }

    public b d(d dVar, int i2) {
        this.f2364e.c(dVar.o(i2, "ep"), 1.0f);
        this.f2364e.c(dVar.o(i2, "em"), -1.0f);
        return this;
    }

    b e(i iVar, int i2) {
        this.f2364e.c(iVar, i2);
        return this;
    }

    boolean f(d dVar) {
        boolean z;
        i iVarG = g(dVar);
        if (iVarG == null) {
            z = true;
        } else {
            y(iVarG);
            z = false;
        }
        if (this.f2364e.k() == 0) {
            this.f2365f = true;
        }
        return z;
    }

    i g(d dVar) {
        int iK = this.f2364e.k();
        i iVar = null;
        boolean z = false;
        boolean z2 = false;
        float f2 = 0.0f;
        float f3 = 0.0f;
        i iVar2 = null;
        for (int i2 = 0; i2 < iK; i2++) {
            float fA = this.f2364e.a(i2);
            i iVarF = this.f2364e.f(i2);
            if (iVarF.k == i.a.UNRESTRICTED) {
                if (iVar == null || f2 > fA) {
                    boolean zV = v(iVarF, dVar);
                    z = zV;
                    f2 = fA;
                    iVar = iVarF;
                } else if (!z && v(iVarF, dVar)) {
                    f2 = fA;
                    iVar = iVarF;
                    z = true;
                }
            } else if (iVar == null && fA < 0.0f) {
                if (iVar2 == null || f3 > fA) {
                    boolean zV2 = v(iVarF, dVar);
                    z2 = zV2;
                    f3 = fA;
                    iVar2 = iVarF;
                } else if (!z2 && v(iVarF, dVar)) {
                    f3 = fA;
                    iVar2 = iVarF;
                    z2 = true;
                }
            }
        }
        return iVar != null ? iVar : iVar2;
    }

    @Override // b.e.b.d.a
    public i getKey() {
        return this.f2360a;
    }

    b h(i iVar, i iVar2, int i2, float f2, i iVar3, i iVar4, int i3) {
        if (iVar2 == iVar3) {
            this.f2364e.c(iVar, 1.0f);
            this.f2364e.c(iVar4, 1.0f);
            this.f2364e.c(iVar2, -2.0f);
            return this;
        }
        if (f2 == 0.5f) {
            this.f2364e.c(iVar, 1.0f);
            this.f2364e.c(iVar2, -1.0f);
            this.f2364e.c(iVar3, -1.0f);
            this.f2364e.c(iVar4, 1.0f);
            if (i2 > 0 || i3 > 0) {
                this.f2361b = (-i2) + i3;
            }
        } else if (f2 <= 0.0f) {
            this.f2364e.c(iVar, -1.0f);
            this.f2364e.c(iVar2, 1.0f);
            this.f2361b = i2;
        } else if (f2 >= 1.0f) {
            this.f2364e.c(iVar4, -1.0f);
            this.f2364e.c(iVar3, 1.0f);
            this.f2361b = -i3;
        } else {
            float f3 = 1.0f - f2;
            this.f2364e.c(iVar, f3 * 1.0f);
            this.f2364e.c(iVar2, f3 * (-1.0f));
            this.f2364e.c(iVar3, (-1.0f) * f2);
            this.f2364e.c(iVar4, 1.0f * f2);
            if (i2 > 0 || i3 > 0) {
                this.f2361b = ((-i2) * f3) + (i3 * f2);
            }
        }
        return this;
    }

    b i(i iVar, int i2) {
        this.f2360a = iVar;
        float f2 = i2;
        iVar.f2395g = f2;
        this.f2361b = f2;
        this.f2365f = true;
        return this;
    }

    b j(i iVar, i iVar2, float f2) {
        this.f2364e.c(iVar, -1.0f);
        this.f2364e.c(iVar2, f2);
        return this;
    }

    public b k(i iVar, i iVar2, i iVar3, i iVar4, float f2) {
        this.f2364e.c(iVar, -1.0f);
        this.f2364e.c(iVar2, 1.0f);
        this.f2364e.c(iVar3, f2);
        this.f2364e.c(iVar4, -f2);
        return this;
    }

    public b l(float f2, float f3, float f4, i iVar, i iVar2, i iVar3, i iVar4) {
        this.f2361b = 0.0f;
        if (f3 == 0.0f || f2 == f4) {
            this.f2364e.c(iVar, 1.0f);
            this.f2364e.c(iVar2, -1.0f);
            this.f2364e.c(iVar4, 1.0f);
            this.f2364e.c(iVar3, -1.0f);
        } else if (f2 == 0.0f) {
            this.f2364e.c(iVar, 1.0f);
            this.f2364e.c(iVar2, -1.0f);
        } else if (f4 == 0.0f) {
            this.f2364e.c(iVar3, 1.0f);
            this.f2364e.c(iVar4, -1.0f);
        } else {
            float f5 = (f2 / f3) / (f4 / f3);
            this.f2364e.c(iVar, 1.0f);
            this.f2364e.c(iVar2, -1.0f);
            this.f2364e.c(iVar4, f5);
            this.f2364e.c(iVar3, -f5);
        }
        return this;
    }

    public b m(i iVar, int i2) {
        if (i2 < 0) {
            this.f2361b = i2 * (-1);
            this.f2364e.c(iVar, 1.0f);
        } else {
            this.f2361b = i2;
            this.f2364e.c(iVar, -1.0f);
        }
        return this;
    }

    public b n(i iVar, i iVar2, int i2) {
        boolean z = false;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z = true;
            }
            this.f2361b = i2;
        }
        if (z) {
            this.f2364e.c(iVar, 1.0f);
            this.f2364e.c(iVar2, -1.0f);
        } else {
            this.f2364e.c(iVar, -1.0f);
            this.f2364e.c(iVar2, 1.0f);
        }
        return this;
    }

    public b o(i iVar, i iVar2, i iVar3, int i2) {
        boolean z = false;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z = true;
            }
            this.f2361b = i2;
        }
        if (z) {
            this.f2364e.c(iVar, 1.0f);
            this.f2364e.c(iVar2, -1.0f);
            this.f2364e.c(iVar3, -1.0f);
        } else {
            this.f2364e.c(iVar, -1.0f);
            this.f2364e.c(iVar2, 1.0f);
            this.f2364e.c(iVar3, 1.0f);
        }
        return this;
    }

    public b p(i iVar, i iVar2, i iVar3, int i2) {
        boolean z = false;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z = true;
            }
            this.f2361b = i2;
        }
        if (z) {
            this.f2364e.c(iVar, 1.0f);
            this.f2364e.c(iVar2, -1.0f);
            this.f2364e.c(iVar3, 1.0f);
        } else {
            this.f2364e.c(iVar, -1.0f);
            this.f2364e.c(iVar2, 1.0f);
            this.f2364e.c(iVar3, -1.0f);
        }
        return this;
    }

    public b q(i iVar, i iVar2, i iVar3, i iVar4, float f2) {
        this.f2364e.c(iVar3, 0.5f);
        this.f2364e.c(iVar4, 0.5f);
        this.f2364e.c(iVar, -0.5f);
        this.f2364e.c(iVar2, -0.5f);
        this.f2361b = -f2;
        return this;
    }

    void r() {
        float f2 = this.f2361b;
        if (f2 < 0.0f) {
            this.f2361b = f2 * (-1.0f);
            this.f2364e.j();
        }
    }

    boolean s() {
        i iVar = this.f2360a;
        return iVar != null && (iVar.k == i.a.UNRESTRICTED || this.f2361b >= 0.0f);
    }

    boolean t(i iVar) {
        return this.f2364e.h(iVar);
    }

    public String toString() {
        return A();
    }

    public boolean u() {
        return this.f2360a == null && this.f2361b == 0.0f && this.f2364e.k() == 0;
    }

    public i w(i iVar) {
        return x(null, iVar);
    }

    void y(i iVar) {
        i iVar2 = this.f2360a;
        if (iVar2 != null) {
            this.f2364e.c(iVar2, -1.0f);
            this.f2360a = null;
        }
        float fD = this.f2364e.d(iVar, true) * (-1.0f);
        this.f2360a = iVar;
        if (fD == 1.0f) {
            return;
        }
        this.f2361b /= fD;
        this.f2364e.i(fD);
    }

    public void z() {
        this.f2360a = null;
        this.f2364e.clear();
        this.f2361b = 0.0f;
        this.f2365f = false;
    }

    public b(c cVar) {
        this.f2364e = new b.e.b.a(this, cVar);
    }
}
