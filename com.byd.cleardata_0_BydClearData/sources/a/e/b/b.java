package a.e.b;

import a.e.b.d;
import a.e.b.i;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class b implements d.a {
    public a e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    i f171a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    float f172b = 0.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f173c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ArrayList<i> f174d = new ArrayList<>();
    boolean f = false;

    public interface a {
        float a(int i);

        float b(b bVar, boolean z);

        void c(i iVar, float f);

        void clear();

        float d(i iVar, boolean z);

        void e(i iVar, float f, boolean z);

        i f(int i);

        float g(i iVar);

        boolean h(i iVar);

        void i(float f);

        void j();

        int k();
    }

    public b() {
    }

    public b(c cVar) {
        this.e = new a.e.b.a(this, cVar);
    }

    private boolean v(i iVar, d dVar) {
        return iVar.n <= 1;
    }

    private i x(boolean[] zArr, i iVar) {
        i.a aVar;
        int iK = this.e.k();
        i iVar2 = null;
        float f = 0.0f;
        for (int i = 0; i < iK; i++) {
            float fA = this.e.a(i);
            if (fA < 0.0f) {
                i iVarF = this.e.f(i);
                if ((zArr == null || !zArr[iVarF.f192d]) && iVarF != iVar && (((aVar = iVarF.k) == i.a.SLACK || aVar == i.a.ERROR) && fA < f)) {
                    f = fA;
                    iVar2 = iVarF;
                }
            }
        }
        return iVar2;
    }

    /*  JADX ERROR: ConcurrentModificationException in pass: ConstructorVisitor
        java.util.ConcurrentModificationException
        	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1095)
        	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1049)
        	at jadx.core.dex.visitors.ConstructorVisitor.insertPhiInsn(ConstructorVisitor.java:139)
        	at jadx.core.dex.visitors.ConstructorVisitor.processInvoke(ConstructorVisitor.java:91)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:56)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    java.lang.String A() {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.e.b.b.A():java.lang.String");
    }

    public void B(d dVar, i iVar, boolean z) {
        if (iVar.h) {
            this.f172b += iVar.g * this.e.g(iVar);
            this.e.d(iVar, z);
            if (z) {
                iVar.c(this);
            }
        }
    }

    public void C(b bVar, boolean z) {
        this.f172b += bVar.f172b * this.e.b(bVar, z);
        if (z) {
            bVar.f171a.c(this);
        }
    }

    public void D(d dVar) {
        if (dVar.k.length == 0) {
            return;
        }
        boolean z = false;
        while (!z) {
            int iK = this.e.k();
            for (int i = 0; i < iK; i++) {
                i iVarF = this.e.f(i);
                if (iVarF.e != -1 || iVarF.h) {
                    this.f174d.add(iVarF);
                }
            }
            if (this.f174d.size() > 0) {
                for (i iVar : this.f174d) {
                    if (iVar.h) {
                        B(dVar, iVar, true);
                    } else {
                        C(dVar.k[iVar.e], true);
                    }
                }
                this.f174d.clear();
            } else {
                z = true;
            }
        }
    }

    @Override // a.e.b.d.a
    public void a(d.a aVar) {
        if (aVar instanceof b) {
            b bVar = (b) aVar;
            this.f171a = null;
            this.e.clear();
            for (int i = 0; i < bVar.e.k(); i++) {
                this.e.e(bVar.e.f(i), bVar.e.a(i), true);
            }
        }
    }

    @Override // a.e.b.d.a
    public void b(i iVar) {
        int i = iVar.f;
        float f = 1.0f;
        if (i != 1) {
            if (i == 2) {
                f = 1000.0f;
            } else if (i == 3) {
                f = 1000000.0f;
            } else if (i == 4) {
                f = 1.0E9f;
            } else if (i == 5) {
                f = 1.0E12f;
            }
        }
        this.e.c(iVar, f);
    }

    @Override // a.e.b.d.a
    public i c(d dVar, boolean[] zArr) {
        return x(zArr, null);
    }

    @Override // a.e.b.d.a
    public void clear() {
        this.e.clear();
        this.f171a = null;
        this.f172b = 0.0f;
    }

    public b d(d dVar, int i) {
        this.e.c(dVar.o(i, "ep"), 1.0f);
        this.e.c(dVar.o(i, "em"), -1.0f);
        return this;
    }

    b e(i iVar, int i) {
        this.e.c(iVar, i);
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
        if (this.e.k() == 0) {
            this.f = true;
        }
        return z;
    }

    i g(d dVar) {
        int iK = this.e.k();
        i iVar = null;
        boolean zV = false;
        boolean zV2 = false;
        float f = 0.0f;
        float f2 = 0.0f;
        i iVar2 = null;
        for (int i = 0; i < iK; i++) {
            float fA = this.e.a(i);
            i iVarF = this.e.f(i);
            if (iVarF.k == i.a.UNRESTRICTED) {
                if (iVar == null || f > fA) {
                    zV = v(iVarF, dVar);
                    f = fA;
                    iVar = iVarF;
                } else if (!zV && v(iVarF, dVar)) {
                    f = fA;
                    iVar = iVarF;
                    zV = true;
                }
            } else if (iVar == null && fA < 0.0f) {
                if (iVar2 == null || f2 > fA) {
                    zV2 = v(iVarF, dVar);
                    f2 = fA;
                    iVar2 = iVarF;
                } else if (!zV2 && v(iVarF, dVar)) {
                    f2 = fA;
                    iVar2 = iVarF;
                    zV2 = true;
                }
            }
        }
        return iVar != null ? iVar : iVar2;
    }

    @Override // a.e.b.d.a
    public i getKey() {
        return this.f171a;
    }

    b h(i iVar, i iVar2, int i, float f, i iVar3, i iVar4, int i2) {
        float f2;
        int i3;
        if (iVar2 == iVar3) {
            this.e.c(iVar, 1.0f);
            this.e.c(iVar4, 1.0f);
            this.e.c(iVar2, -2.0f);
            return this;
        }
        if (f == 0.5f) {
            this.e.c(iVar, 1.0f);
            this.e.c(iVar2, -1.0f);
            this.e.c(iVar3, -1.0f);
            this.e.c(iVar4, 1.0f);
            if (i > 0 || i2 > 0) {
                i3 = (-i) + i2;
                f2 = i3;
            }
            return this;
        }
        if (f <= 0.0f) {
            this.e.c(iVar, -1.0f);
            this.e.c(iVar2, 1.0f);
            f2 = i;
        } else {
            if (f < 1.0f) {
                float f3 = 1.0f - f;
                this.e.c(iVar, f3 * 1.0f);
                this.e.c(iVar2, f3 * (-1.0f));
                this.e.c(iVar3, (-1.0f) * f);
                this.e.c(iVar4, 1.0f * f);
                if (i > 0 || i2 > 0) {
                    f2 = ((-i) * f3) + (i2 * f);
                }
                return this;
            }
            this.e.c(iVar4, -1.0f);
            this.e.c(iVar3, 1.0f);
            i3 = -i2;
            f2 = i3;
        }
        this.f172b = f2;
        return this;
    }

    b i(i iVar, int i) {
        this.f171a = iVar;
        float f = i;
        iVar.g = f;
        this.f172b = f;
        this.f = true;
        return this;
    }

    b j(i iVar, i iVar2, float f) {
        this.e.c(iVar, -1.0f);
        this.e.c(iVar2, f);
        return this;
    }

    public b k(i iVar, i iVar2, i iVar3, i iVar4, float f) {
        this.e.c(iVar, -1.0f);
        this.e.c(iVar2, 1.0f);
        this.e.c(iVar3, f);
        this.e.c(iVar4, -f);
        return this;
    }

    public b l(float f, float f2, float f3, i iVar, i iVar2, i iVar3, i iVar4) {
        this.f172b = 0.0f;
        if (f2 == 0.0f || f == f3) {
            this.e.c(iVar, 1.0f);
            this.e.c(iVar2, -1.0f);
            this.e.c(iVar4, 1.0f);
            this.e.c(iVar3, -1.0f);
        } else if (f == 0.0f) {
            this.e.c(iVar, 1.0f);
            this.e.c(iVar2, -1.0f);
        } else if (f3 == 0.0f) {
            this.e.c(iVar3, 1.0f);
            this.e.c(iVar4, -1.0f);
        } else {
            float f4 = (f / f2) / (f3 / f2);
            this.e.c(iVar, 1.0f);
            this.e.c(iVar2, -1.0f);
            this.e.c(iVar4, f4);
            this.e.c(iVar3, -f4);
        }
        return this;
    }

    public b m(i iVar, int i) {
        a aVar;
        float f;
        if (i < 0) {
            this.f172b = i * (-1);
            aVar = this.e;
            f = 1.0f;
        } else {
            this.f172b = i;
            aVar = this.e;
            f = -1.0f;
        }
        aVar.c(iVar, f);
        return this;
    }

    public b n(i iVar, i iVar2, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.f172b = i;
        }
        if (z) {
            this.e.c(iVar, 1.0f);
            this.e.c(iVar2, -1.0f);
        } else {
            this.e.c(iVar, -1.0f);
            this.e.c(iVar2, 1.0f);
        }
        return this;
    }

    public b o(i iVar, i iVar2, i iVar3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.f172b = i;
        }
        if (z) {
            this.e.c(iVar, 1.0f);
            this.e.c(iVar2, -1.0f);
            this.e.c(iVar3, -1.0f);
        } else {
            this.e.c(iVar, -1.0f);
            this.e.c(iVar2, 1.0f);
            this.e.c(iVar3, 1.0f);
        }
        return this;
    }

    public b p(i iVar, i iVar2, i iVar3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.f172b = i;
        }
        if (z) {
            this.e.c(iVar, 1.0f);
            this.e.c(iVar2, -1.0f);
            this.e.c(iVar3, 1.0f);
        } else {
            this.e.c(iVar, -1.0f);
            this.e.c(iVar2, 1.0f);
            this.e.c(iVar3, -1.0f);
        }
        return this;
    }

    public b q(i iVar, i iVar2, i iVar3, i iVar4, float f) {
        this.e.c(iVar3, 0.5f);
        this.e.c(iVar4, 0.5f);
        this.e.c(iVar, -0.5f);
        this.e.c(iVar2, -0.5f);
        this.f172b = -f;
        return this;
    }

    void r() {
        float f = this.f172b;
        if (f < 0.0f) {
            this.f172b = f * (-1.0f);
            this.e.j();
        }
    }

    boolean s() {
        i iVar = this.f171a;
        return iVar != null && (iVar.k == i.a.UNRESTRICTED || this.f172b >= 0.0f);
    }

    boolean t(i iVar) {
        return this.e.h(iVar);
    }

    public String toString() {
        return A();
    }

    public boolean u() {
        return this.f171a == null && this.f172b == 0.0f && this.e.k() == 0;
    }

    public i w(i iVar) {
        return x(null, iVar);
    }

    void y(i iVar) {
        i iVar2 = this.f171a;
        if (iVar2 != null) {
            this.e.c(iVar2, -1.0f);
            this.f171a = null;
        }
        float fD = this.e.d(iVar, true) * (-1.0f);
        this.f171a = iVar;
        if (fD == 1.0f) {
            return;
        }
        this.f172b /= fD;
        this.e.i(fD);
    }

    public void z() {
        this.f171a = null;
        this.e.clear();
        this.f172b = 0.0f;
        this.f = false;
    }
}
