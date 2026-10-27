package o;

import java.util.ArrayList;
import o.d;
import o.i;

/* JADX INFO: compiled from: ArrayRow.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements d.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f8563e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    i f8559a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    float f8560b = 0.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f8561c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ArrayList<i> f8562d = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    boolean f8564f = false;

    /* JADX INFO: compiled from: ArrayRow.java */
    public interface a {
        float a(int i7);

        void b(i iVar, float f7, boolean z6);

        boolean c(i iVar);

        void clear();

        float d(b bVar, boolean z6);

        void e(i iVar, float f7);

        float f(i iVar);

        i g(int i7);

        void h(float f7);

        float i(i iVar, boolean z6);

        void j();

        int k();
    }

    public b() {
    }

    private boolean u(i iVar, d dVar) {
        return iVar.f8620r <= 1;
    }

    private i w(boolean[] zArr, i iVar) {
        i.a aVar;
        int iK = this.f8563e.k();
        i iVar2 = null;
        float f7 = 0.0f;
        for (int i7 = 0; i7 < iK; i7++) {
            float fA = this.f8563e.a(i7);
            if (fA < 0.0f) {
                i iVarG = this.f8563e.g(i7);
                if ((zArr == null || !zArr[iVarG.f8610h]) && iVarG != iVar && (((aVar = iVarG.f8617o) == i.a.SLACK || aVar == i.a.ERROR) && fA < f7)) {
                    f7 = fA;
                    iVar2 = iVarG;
                }
            }
        }
        return iVar2;
    }

    public void A(d dVar, i iVar, boolean z6) {
        if (iVar == null || !iVar.f8614l) {
            return;
        }
        this.f8560b += iVar.f8613k * this.f8563e.f(iVar);
        this.f8563e.i(iVar, z6);
        if (z6) {
            iVar.d(this);
        }
        if (d.f8571t && this.f8563e.k() == 0) {
            this.f8564f = true;
            dVar.f8577a = true;
        }
    }

    public void B(d dVar, b bVar, boolean z6) {
        this.f8560b += bVar.f8560b * this.f8563e.d(bVar, z6);
        if (z6) {
            bVar.f8559a.d(this);
        }
        if (d.f8571t && this.f8559a != null && this.f8563e.k() == 0) {
            this.f8564f = true;
            dVar.f8577a = true;
        }
    }

    public void C(d dVar, i iVar, boolean z6) {
        if (iVar == null || !iVar.f8621s) {
            return;
        }
        float f7 = this.f8563e.f(iVar);
        this.f8560b += iVar.f8623u * f7;
        this.f8563e.i(iVar, z6);
        if (z6) {
            iVar.d(this);
        }
        this.f8563e.b(dVar.f8590n.f8568d[iVar.f8622t], f7, z6);
        if (d.f8571t && this.f8563e.k() == 0) {
            this.f8564f = true;
            dVar.f8577a = true;
        }
    }

    public void D(d dVar) {
        if (dVar.f8583g.length == 0) {
            return;
        }
        boolean z6 = false;
        while (!z6) {
            int iK = this.f8563e.k();
            for (int i7 = 0; i7 < iK; i7++) {
                i iVarG = this.f8563e.g(i7);
                if (iVarG.f8611i != -1 || iVarG.f8614l || iVarG.f8621s) {
                    this.f8562d.add(iVarG);
                }
            }
            int size = this.f8562d.size();
            if (size > 0) {
                for (int i8 = 0; i8 < size; i8++) {
                    i iVar = this.f8562d.get(i8);
                    if (iVar.f8614l) {
                        A(dVar, iVar, true);
                    } else if (iVar.f8621s) {
                        C(dVar, iVar, true);
                    } else {
                        B(dVar, dVar.f8583g[iVar.f8611i], true);
                    }
                }
                this.f8562d.clear();
            } else {
                z6 = true;
            }
        }
        if (d.f8571t && this.f8559a != null && this.f8563e.k() == 0) {
            this.f8564f = true;
            dVar.f8577a = true;
        }
    }

    @Override // o.d.a
    public void a(i iVar) {
        int i7 = iVar.f8612j;
        float f7 = 1.0f;
        if (i7 != 1) {
            if (i7 == 2) {
                f7 = 1000.0f;
            } else if (i7 == 3) {
                f7 = 1000000.0f;
            } else if (i7 == 4) {
                f7 = 1.0E9f;
            } else if (i7 == 5) {
                f7 = 1.0E12f;
            }
        }
        this.f8563e.e(iVar, f7);
    }

    @Override // o.d.a
    public i b(d dVar, boolean[] zArr) {
        return w(zArr, null);
    }

    @Override // o.d.a
    public void c(d.a aVar) {
        if (aVar instanceof b) {
            b bVar = (b) aVar;
            this.f8559a = null;
            this.f8563e.clear();
            for (int i7 = 0; i7 < bVar.f8563e.k(); i7++) {
                this.f8563e.b(bVar.f8563e.g(i7), bVar.f8563e.a(i7), true);
            }
        }
    }

    @Override // o.d.a
    public void clear() {
        this.f8563e.clear();
        this.f8559a = null;
        this.f8560b = 0.0f;
    }

    public b d(d dVar, int i7) {
        this.f8563e.e(dVar.o(i7, "ep"), 1.0f);
        this.f8563e.e(dVar.o(i7, "em"), -1.0f);
        return this;
    }

    b e(i iVar, int i7) {
        this.f8563e.e(iVar, i7);
        return this;
    }

    boolean f(d dVar) {
        boolean z6;
        i iVarG = g(dVar);
        if (iVarG == null) {
            z6 = true;
        } else {
            x(iVarG);
            z6 = false;
        }
        if (this.f8563e.k() == 0) {
            this.f8564f = true;
        }
        return z6;
    }

    i g(d dVar) {
        int iK = this.f8563e.k();
        i iVar = null;
        boolean z6 = false;
        boolean z7 = false;
        float f7 = 0.0f;
        float f8 = 0.0f;
        i iVar2 = null;
        for (int i7 = 0; i7 < iK; i7++) {
            float fA = this.f8563e.a(i7);
            i iVarG = this.f8563e.g(i7);
            if (iVarG.f8617o == i.a.UNRESTRICTED) {
                if (iVar == null || f7 > fA) {
                    boolean zU = u(iVarG, dVar);
                    z6 = zU;
                    f7 = fA;
                    iVar = iVarG;
                } else if (!z6 && u(iVarG, dVar)) {
                    f7 = fA;
                    iVar = iVarG;
                    z6 = true;
                }
            } else if (iVar == null && fA < 0.0f) {
                if (iVar2 == null || f8 > fA) {
                    boolean zU2 = u(iVarG, dVar);
                    z7 = zU2;
                    f8 = fA;
                    iVar2 = iVarG;
                } else if (!z7 && u(iVarG, dVar)) {
                    f8 = fA;
                    iVar2 = iVarG;
                    z7 = true;
                }
            }
        }
        return iVar != null ? iVar : iVar2;
    }

    @Override // o.d.a
    public i getKey() {
        return this.f8559a;
    }

    b h(i iVar, i iVar2, int i7, float f7, i iVar3, i iVar4, int i8) {
        if (iVar2 == iVar3) {
            this.f8563e.e(iVar, 1.0f);
            this.f8563e.e(iVar4, 1.0f);
            this.f8563e.e(iVar2, -2.0f);
            return this;
        }
        if (f7 == 0.5f) {
            this.f8563e.e(iVar, 1.0f);
            this.f8563e.e(iVar2, -1.0f);
            this.f8563e.e(iVar3, -1.0f);
            this.f8563e.e(iVar4, 1.0f);
            if (i7 > 0 || i8 > 0) {
                this.f8560b = (-i7) + i8;
            }
        } else if (f7 <= 0.0f) {
            this.f8563e.e(iVar, -1.0f);
            this.f8563e.e(iVar2, 1.0f);
            this.f8560b = i7;
        } else if (f7 >= 1.0f) {
            this.f8563e.e(iVar4, -1.0f);
            this.f8563e.e(iVar3, 1.0f);
            this.f8560b = -i8;
        } else {
            float f8 = 1.0f - f7;
            this.f8563e.e(iVar, f8 * 1.0f);
            this.f8563e.e(iVar2, f8 * (-1.0f));
            this.f8563e.e(iVar3, (-1.0f) * f7);
            this.f8563e.e(iVar4, 1.0f * f7);
            if (i7 > 0 || i8 > 0) {
                this.f8560b = ((-i7) * f8) + (i8 * f7);
            }
        }
        return this;
    }

    b i(i iVar, int i7) {
        this.f8559a = iVar;
        float f7 = i7;
        iVar.f8613k = f7;
        this.f8560b = f7;
        this.f8564f = true;
        return this;
    }

    @Override // o.d.a
    public boolean isEmpty() {
        return this.f8559a == null && this.f8560b == 0.0f && this.f8563e.k() == 0;
    }

    b j(i iVar, i iVar2, float f7) {
        this.f8563e.e(iVar, -1.0f);
        this.f8563e.e(iVar2, f7);
        return this;
    }

    public b k(i iVar, i iVar2, i iVar3, i iVar4, float f7) {
        this.f8563e.e(iVar, -1.0f);
        this.f8563e.e(iVar2, 1.0f);
        this.f8563e.e(iVar3, f7);
        this.f8563e.e(iVar4, -f7);
        return this;
    }

    public b l(float f7, float f8, float f9, i iVar, i iVar2, i iVar3, i iVar4) {
        this.f8560b = 0.0f;
        if (f8 == 0.0f || f7 == f9) {
            this.f8563e.e(iVar, 1.0f);
            this.f8563e.e(iVar2, -1.0f);
            this.f8563e.e(iVar4, 1.0f);
            this.f8563e.e(iVar3, -1.0f);
        } else if (f7 == 0.0f) {
            this.f8563e.e(iVar, 1.0f);
            this.f8563e.e(iVar2, -1.0f);
        } else if (f9 == 0.0f) {
            this.f8563e.e(iVar3, 1.0f);
            this.f8563e.e(iVar4, -1.0f);
        } else {
            float f10 = (f7 / f8) / (f9 / f8);
            this.f8563e.e(iVar, 1.0f);
            this.f8563e.e(iVar2, -1.0f);
            this.f8563e.e(iVar4, f10);
            this.f8563e.e(iVar3, -f10);
        }
        return this;
    }

    public b m(i iVar, int i7) {
        if (i7 < 0) {
            this.f8560b = i7 * (-1);
            this.f8563e.e(iVar, 1.0f);
        } else {
            this.f8560b = i7;
            this.f8563e.e(iVar, -1.0f);
        }
        return this;
    }

    public b n(i iVar, i iVar2, int i7) {
        boolean z6 = false;
        if (i7 != 0) {
            if (i7 < 0) {
                i7 *= -1;
                z6 = true;
            }
            this.f8560b = i7;
        }
        if (z6) {
            this.f8563e.e(iVar, 1.0f);
            this.f8563e.e(iVar2, -1.0f);
        } else {
            this.f8563e.e(iVar, -1.0f);
            this.f8563e.e(iVar2, 1.0f);
        }
        return this;
    }

    public b o(i iVar, i iVar2, i iVar3, int i7) {
        boolean z6 = false;
        if (i7 != 0) {
            if (i7 < 0) {
                i7 *= -1;
                z6 = true;
            }
            this.f8560b = i7;
        }
        if (z6) {
            this.f8563e.e(iVar, 1.0f);
            this.f8563e.e(iVar2, -1.0f);
            this.f8563e.e(iVar3, -1.0f);
        } else {
            this.f8563e.e(iVar, -1.0f);
            this.f8563e.e(iVar2, 1.0f);
            this.f8563e.e(iVar3, 1.0f);
        }
        return this;
    }

    public b p(i iVar, i iVar2, i iVar3, int i7) {
        boolean z6 = false;
        if (i7 != 0) {
            if (i7 < 0) {
                i7 *= -1;
                z6 = true;
            }
            this.f8560b = i7;
        }
        if (z6) {
            this.f8563e.e(iVar, 1.0f);
            this.f8563e.e(iVar2, -1.0f);
            this.f8563e.e(iVar3, 1.0f);
        } else {
            this.f8563e.e(iVar, -1.0f);
            this.f8563e.e(iVar2, 1.0f);
            this.f8563e.e(iVar3, -1.0f);
        }
        return this;
    }

    public b q(i iVar, i iVar2, i iVar3, i iVar4, float f7) {
        this.f8563e.e(iVar3, 0.5f);
        this.f8563e.e(iVar4, 0.5f);
        this.f8563e.e(iVar, -0.5f);
        this.f8563e.e(iVar2, -0.5f);
        this.f8560b = -f7;
        return this;
    }

    void r() {
        float f7 = this.f8560b;
        if (f7 < 0.0f) {
            this.f8560b = f7 * (-1.0f);
            this.f8563e.j();
        }
    }

    boolean s() {
        i iVar = this.f8559a;
        return iVar != null && (iVar.f8617o == i.a.UNRESTRICTED || this.f8560b >= 0.0f);
    }

    boolean t(i iVar) {
        return this.f8563e.c(iVar);
    }

    public String toString() {
        return z();
    }

    public i v(i iVar) {
        return w(null, iVar);
    }

    void x(i iVar) {
        i iVar2 = this.f8559a;
        if (iVar2 != null) {
            this.f8563e.e(iVar2, -1.0f);
            this.f8559a.f8611i = -1;
            this.f8559a = null;
        }
        float fI = this.f8563e.i(iVar, true) * (-1.0f);
        this.f8559a = iVar;
        if (fI == 1.0f) {
            return;
        }
        this.f8560b /= fI;
        this.f8563e.h(fI);
    }

    public void y() {
        this.f8559a = null;
        this.f8563e.clear();
        this.f8560b = 0.0f;
        this.f8564f = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00cf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    java.lang.String z() {
        /*
            Method dump skipped, instruction units count: 255
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: o.b.z():java.lang.String");
    }

    public b(c cVar) {
        this.f8563e = new o.a(this, cVar);
    }
}
