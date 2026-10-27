package androidx.constraintlayout.core;

import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.d;
import java.util.ArrayList;

/* JADX INFO: compiled from: ArrayRow.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements d.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f714e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    SolverVariable f710a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    float f711b = 0.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f712c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ArrayList<SolverVariable> f713d = new ArrayList<>();
    boolean f = false;

    /* JADX INFO: compiled from: ArrayRow.java */
    public interface a {
        float a(int i);

        float b(SolverVariable solverVariable);

        float c(SolverVariable solverVariable, boolean z);

        void clear();

        boolean d(SolverVariable solverVariable);

        float e(b bVar, boolean z);

        void f(SolverVariable solverVariable, float f);

        SolverVariable g(int i);

        void h(SolverVariable solverVariable, float f, boolean z);

        void i(float f);

        void j();

        int k();
    }

    public b() {
    }

    private boolean u(SolverVariable solverVariable, d dVar) {
        return solverVariable.o <= 1;
    }

    private SolverVariable w(boolean[] zArr, SolverVariable solverVariable) {
        SolverVariable.Type type;
        int iK = this.f714e.k();
        SolverVariable solverVariable2 = null;
        float f = 0.0f;
        for (int i = 0; i < iK; i++) {
            float fA = this.f714e.a(i);
            if (fA < 0.0f) {
                SolverVariable solverVariableG = this.f714e.g(i);
                if ((zArr == null || !zArr[solverVariableG.f703e]) && solverVariableG != solverVariable && (((type = solverVariableG.l) == SolverVariable.Type.SLACK || type == SolverVariable.Type.ERROR) && fA < f)) {
                    f = fA;
                    solverVariable2 = solverVariableG;
                }
            }
        }
        return solverVariable2;
    }

    public void A(d dVar, SolverVariable solverVariable, boolean z) {
        if (solverVariable == null || !solverVariable.i) {
            return;
        }
        this.f711b += solverVariable.h * this.f714e.b(solverVariable);
        this.f714e.c(solverVariable, z);
        if (z) {
            solverVariable.g(this);
        }
        if (d.f721c && this.f714e.k() == 0) {
            this.f = true;
            dVar.j = true;
        }
    }

    public void B(d dVar, b bVar, boolean z) {
        this.f711b += bVar.f711b * this.f714e.e(bVar, z);
        if (z) {
            bVar.f710a.g(this);
        }
        if (d.f721c && this.f710a != null && this.f714e.k() == 0) {
            this.f = true;
            dVar.j = true;
        }
    }

    public void C(d dVar, SolverVariable solverVariable, boolean z) {
        if (solverVariable == null || !solverVariable.p) {
            return;
        }
        float fB = this.f714e.b(solverVariable);
        this.f711b += solverVariable.r * fB;
        this.f714e.c(solverVariable, z);
        if (z) {
            solverVariable.g(this);
        }
        this.f714e.h(dVar.w.f718d[solverVariable.q], fB, z);
        if (d.f721c && this.f714e.k() == 0) {
            this.f = true;
            dVar.j = true;
        }
    }

    public void D(d dVar) {
        if (dVar.p.length == 0) {
            return;
        }
        boolean z = false;
        while (!z) {
            int iK = this.f714e.k();
            for (int i = 0; i < iK; i++) {
                SolverVariable solverVariableG = this.f714e.g(i);
                if (solverVariableG.f != -1 || solverVariableG.i || solverVariableG.p) {
                    this.f713d.add(solverVariableG);
                }
            }
            int size = this.f713d.size();
            if (size > 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    SolverVariable solverVariable = this.f713d.get(i2);
                    if (solverVariable.i) {
                        A(dVar, solverVariable, true);
                    } else if (solverVariable.p) {
                        C(dVar, solverVariable, true);
                    } else {
                        B(dVar, dVar.p[solverVariable.f], true);
                    }
                }
                this.f713d.clear();
            } else {
                z = true;
            }
        }
        if (d.f721c && this.f710a != null && this.f714e.k() == 0) {
            this.f = true;
            dVar.j = true;
        }
    }

    @Override // androidx.constraintlayout.core.d.a
    public SolverVariable a(d dVar, boolean[] zArr) {
        return w(zArr, null);
    }

    @Override // androidx.constraintlayout.core.d.a
    public void b(SolverVariable solverVariable) {
        int i = solverVariable.g;
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
        this.f714e.f(solverVariable, f);
    }

    @Override // androidx.constraintlayout.core.d.a
    public void c(d.a aVar) {
        if (aVar instanceof b) {
            b bVar = (b) aVar;
            this.f710a = null;
            this.f714e.clear();
            for (int i = 0; i < bVar.f714e.k(); i++) {
                this.f714e.h(bVar.f714e.g(i), bVar.f714e.a(i), true);
            }
        }
    }

    @Override // androidx.constraintlayout.core.d.a
    public void clear() {
        this.f714e.clear();
        this.f710a = null;
        this.f711b = 0.0f;
    }

    public b d(d dVar, int i) {
        this.f714e.f(dVar.o(i, "ep"), 1.0f);
        this.f714e.f(dVar.o(i, "em"), -1.0f);
        return this;
    }

    b e(SolverVariable solverVariable, int i) {
        this.f714e.f(solverVariable, i);
        return this;
    }

    boolean f(d dVar) {
        boolean z;
        SolverVariable solverVariableG = g(dVar);
        if (solverVariableG == null) {
            z = true;
        } else {
            x(solverVariableG);
            z = false;
        }
        if (this.f714e.k() == 0) {
            this.f = true;
        }
        return z;
    }

    SolverVariable g(d dVar) {
        int iK = this.f714e.k();
        SolverVariable solverVariable = null;
        boolean z = false;
        boolean z2 = false;
        float f = 0.0f;
        float f2 = 0.0f;
        SolverVariable solverVariable2 = null;
        for (int i = 0; i < iK; i++) {
            float fA = this.f714e.a(i);
            SolverVariable solverVariableG = this.f714e.g(i);
            if (solverVariableG.l == SolverVariable.Type.UNRESTRICTED) {
                if (solverVariable == null || f > fA) {
                    boolean zU = u(solverVariableG, dVar);
                    z = zU;
                    f = fA;
                    solverVariable = solverVariableG;
                } else if (!z && u(solverVariableG, dVar)) {
                    f = fA;
                    solverVariable = solverVariableG;
                    z = true;
                }
            } else if (solverVariable == null && fA < 0.0f) {
                if (solverVariable2 == null || f2 > fA) {
                    boolean zU2 = u(solverVariableG, dVar);
                    z2 = zU2;
                    f2 = fA;
                    solverVariable2 = solverVariableG;
                } else if (!z2 && u(solverVariableG, dVar)) {
                    f2 = fA;
                    solverVariable2 = solverVariableG;
                    z2 = true;
                }
            }
        }
        return solverVariable != null ? solverVariable : solverVariable2;
    }

    @Override // androidx.constraintlayout.core.d.a
    public SolverVariable getKey() {
        return this.f710a;
    }

    b h(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2) {
        if (solverVariable2 == solverVariable3) {
            this.f714e.f(solverVariable, 1.0f);
            this.f714e.f(solverVariable4, 1.0f);
            this.f714e.f(solverVariable2, -2.0f);
            return this;
        }
        if (f == 0.5f) {
            this.f714e.f(solverVariable, 1.0f);
            this.f714e.f(solverVariable2, -1.0f);
            this.f714e.f(solverVariable3, -1.0f);
            this.f714e.f(solverVariable4, 1.0f);
            if (i > 0 || i2 > 0) {
                this.f711b = (-i) + i2;
            }
        } else if (f <= 0.0f) {
            this.f714e.f(solverVariable, -1.0f);
            this.f714e.f(solverVariable2, 1.0f);
            this.f711b = i;
        } else if (f >= 1.0f) {
            this.f714e.f(solverVariable4, -1.0f);
            this.f714e.f(solverVariable3, 1.0f);
            this.f711b = -i2;
        } else {
            float f2 = 1.0f - f;
            this.f714e.f(solverVariable, f2 * 1.0f);
            this.f714e.f(solverVariable2, f2 * (-1.0f));
            this.f714e.f(solverVariable3, (-1.0f) * f);
            this.f714e.f(solverVariable4, 1.0f * f);
            if (i > 0 || i2 > 0) {
                this.f711b = ((-i) * f2) + (i2 * f);
            }
        }
        return this;
    }

    b i(SolverVariable solverVariable, int i) {
        this.f710a = solverVariable;
        float f = i;
        solverVariable.h = f;
        this.f711b = f;
        this.f = true;
        return this;
    }

    @Override // androidx.constraintlayout.core.d.a
    public boolean isEmpty() {
        return this.f710a == null && this.f711b == 0.0f && this.f714e.k() == 0;
    }

    b j(SolverVariable solverVariable, SolverVariable solverVariable2, float f) {
        this.f714e.f(solverVariable, -1.0f);
        this.f714e.f(solverVariable2, f);
        return this;
    }

    public b k(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.f714e.f(solverVariable, -1.0f);
        this.f714e.f(solverVariable2, 1.0f);
        this.f714e.f(solverVariable3, f);
        this.f714e.f(solverVariable4, -f);
        return this;
    }

    public b l(float f, float f2, float f3, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4) {
        this.f711b = 0.0f;
        if (f2 == 0.0f || f == f3) {
            this.f714e.f(solverVariable, 1.0f);
            this.f714e.f(solverVariable2, -1.0f);
            this.f714e.f(solverVariable4, 1.0f);
            this.f714e.f(solverVariable3, -1.0f);
        } else if (f == 0.0f) {
            this.f714e.f(solverVariable, 1.0f);
            this.f714e.f(solverVariable2, -1.0f);
        } else if (f3 == 0.0f) {
            this.f714e.f(solverVariable3, 1.0f);
            this.f714e.f(solverVariable4, -1.0f);
        } else {
            float f4 = (f / f2) / (f3 / f2);
            this.f714e.f(solverVariable, 1.0f);
            this.f714e.f(solverVariable2, -1.0f);
            this.f714e.f(solverVariable4, f4);
            this.f714e.f(solverVariable3, -f4);
        }
        return this;
    }

    public b m(SolverVariable solverVariable, int i) {
        if (i < 0) {
            this.f711b = i * (-1);
            this.f714e.f(solverVariable, 1.0f);
        } else {
            this.f711b = i;
            this.f714e.f(solverVariable, -1.0f);
        }
        return this;
    }

    public b n(SolverVariable solverVariable, SolverVariable solverVariable2, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.f711b = i;
        }
        if (z) {
            this.f714e.f(solverVariable, 1.0f);
            this.f714e.f(solverVariable2, -1.0f);
        } else {
            this.f714e.f(solverVariable, -1.0f);
            this.f714e.f(solverVariable2, 1.0f);
        }
        return this;
    }

    public b o(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.f711b = i;
        }
        if (z) {
            this.f714e.f(solverVariable, 1.0f);
            this.f714e.f(solverVariable2, -1.0f);
            this.f714e.f(solverVariable3, -1.0f);
        } else {
            this.f714e.f(solverVariable, -1.0f);
            this.f714e.f(solverVariable2, 1.0f);
            this.f714e.f(solverVariable3, 1.0f);
        }
        return this;
    }

    public b p(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.f711b = i;
        }
        if (z) {
            this.f714e.f(solverVariable, 1.0f);
            this.f714e.f(solverVariable2, -1.0f);
            this.f714e.f(solverVariable3, 1.0f);
        } else {
            this.f714e.f(solverVariable, -1.0f);
            this.f714e.f(solverVariable2, 1.0f);
            this.f714e.f(solverVariable3, -1.0f);
        }
        return this;
    }

    public b q(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.f714e.f(solverVariable3, 0.5f);
        this.f714e.f(solverVariable4, 0.5f);
        this.f714e.f(solverVariable, -0.5f);
        this.f714e.f(solverVariable2, -0.5f);
        this.f711b = -f;
        return this;
    }

    void r() {
        float f = this.f711b;
        if (f < 0.0f) {
            this.f711b = f * (-1.0f);
            this.f714e.j();
        }
    }

    boolean s() {
        SolverVariable solverVariable = this.f710a;
        return solverVariable != null && (solverVariable.l == SolverVariable.Type.UNRESTRICTED || this.f711b >= 0.0f);
    }

    boolean t(SolverVariable solverVariable) {
        return this.f714e.d(solverVariable);
    }

    public String toString() {
        return z();
    }

    public SolverVariable v(SolverVariable solverVariable) {
        return w(null, solverVariable);
    }

    void x(SolverVariable solverVariable) {
        SolverVariable solverVariable2 = this.f710a;
        if (solverVariable2 != null) {
            this.f714e.f(solverVariable2, -1.0f);
            this.f710a.f = -1;
            this.f710a = null;
        }
        float fC = this.f714e.c(solverVariable, true) * (-1.0f);
        this.f710a = solverVariable;
        if (fC == 1.0f) {
            return;
        }
        this.f711b /= fC;
        this.f714e.i(fC);
    }

    public void y() {
        this.f710a = null;
        this.f714e.clear();
        this.f711b = 0.0f;
        this.f = false;
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.b.z():java.lang.String");
    }

    public b(c cVar) {
        this.f714e = new androidx.constraintlayout.core.a(this, cVar);
    }
}
