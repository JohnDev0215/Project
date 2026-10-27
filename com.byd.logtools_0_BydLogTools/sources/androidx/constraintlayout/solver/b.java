package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.d;
import java.util.ArrayList;

/* JADX INFO: compiled from: ArrayRow.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements d.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f926e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    SolverVariable f922a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    float f923b = 0.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f924c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ArrayList<SolverVariable> f925d = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    boolean f927f = false;

    /* JADX INFO: compiled from: ArrayRow.java */
    public interface a {
        float a(int i);

        float b(b bVar, boolean z);

        void c(SolverVariable solverVariable, float f2);

        void clear();

        float d(SolverVariable solverVariable, boolean z);

        void e(SolverVariable solverVariable, float f2, boolean z);

        SolverVariable f(int i);

        float g(SolverVariable solverVariable);

        boolean h(SolverVariable solverVariable);

        void i(float f2);

        void j();

        int k();
    }

    public b() {
    }

    private boolean v(SolverVariable solverVariable, d dVar) {
        return solverVariable.n <= 1;
    }

    private SolverVariable x(boolean[] zArr, SolverVariable solverVariable) {
        SolverVariable.Type type;
        int iK = this.f926e.k();
        SolverVariable solverVariable2 = null;
        float f2 = 0.0f;
        for (int i = 0; i < iK; i++) {
            float fA = this.f926e.a(i);
            if (fA < 0.0f) {
                SolverVariable solverVariableF = this.f926e.f(i);
                if ((zArr == null || !zArr[solverVariableF.f908d]) && solverVariableF != solverVariable && (((type = solverVariableF.k) == SolverVariable.Type.SLACK || type == SolverVariable.Type.ERROR) && fA < f2)) {
                    f2 = fA;
                    solverVariable2 = solverVariableF;
                }
            }
        }
        return solverVariable2;
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.b.A():java.lang.String");
    }

    public void B(d dVar, SolverVariable solverVariable, boolean z) {
        if (solverVariable.f912h) {
            this.f923b += solverVariable.f911g * this.f926e.g(solverVariable);
            this.f926e.d(solverVariable, z);
            if (z) {
                solverVariable.c(this);
            }
        }
    }

    public void C(b bVar, boolean z) {
        this.f923b += bVar.f923b * this.f926e.b(bVar, z);
        if (z) {
            bVar.f922a.c(this);
        }
    }

    public void D(d dVar) {
        if (dVar.k.length == 0) {
            return;
        }
        boolean z = false;
        while (!z) {
            int iK = this.f926e.k();
            for (int i = 0; i < iK; i++) {
                SolverVariable solverVariableF = this.f926e.f(i);
                if (solverVariableF.f909e != -1 || solverVariableF.f912h) {
                    this.f925d.add(solverVariableF);
                }
            }
            if (this.f925d.size() > 0) {
                for (SolverVariable solverVariable : this.f925d) {
                    if (solverVariable.f912h) {
                        B(dVar, solverVariable, true);
                    } else {
                        C(dVar.k[solverVariable.f909e], true);
                    }
                }
                this.f925d.clear();
            } else {
                z = true;
            }
        }
    }

    @Override // androidx.constraintlayout.solver.d.a
    public void a(d.a aVar) {
        if (aVar instanceof b) {
            b bVar = (b) aVar;
            this.f922a = null;
            this.f926e.clear();
            for (int i = 0; i < bVar.f926e.k(); i++) {
                this.f926e.e(bVar.f926e.f(i), bVar.f926e.a(i), true);
            }
        }
    }

    @Override // androidx.constraintlayout.solver.d.a
    public void b(SolverVariable solverVariable) {
        int i = solverVariable.f910f;
        float f2 = 1.0f;
        if (i != 1) {
            if (i == 2) {
                f2 = 1000.0f;
            } else if (i == 3) {
                f2 = 1000000.0f;
            } else if (i == 4) {
                f2 = 1.0E9f;
            } else if (i == 5) {
                f2 = 1.0E12f;
            }
        }
        this.f926e.c(solverVariable, f2);
    }

    @Override // androidx.constraintlayout.solver.d.a
    public SolverVariable c(d dVar, boolean[] zArr) {
        return x(zArr, null);
    }

    @Override // androidx.constraintlayout.solver.d.a
    public void clear() {
        this.f926e.clear();
        this.f922a = null;
        this.f923b = 0.0f;
    }

    public b d(d dVar, int i) {
        this.f926e.c(dVar.o(i, "ep"), 1.0f);
        this.f926e.c(dVar.o(i, "em"), -1.0f);
        return this;
    }

    b e(SolverVariable solverVariable, int i) {
        this.f926e.c(solverVariable, i);
        return this;
    }

    boolean f(d dVar) {
        boolean z;
        SolverVariable solverVariableG = g(dVar);
        if (solverVariableG == null) {
            z = true;
        } else {
            y(solverVariableG);
            z = false;
        }
        if (this.f926e.k() == 0) {
            this.f927f = true;
        }
        return z;
    }

    SolverVariable g(d dVar) {
        int iK = this.f926e.k();
        SolverVariable solverVariable = null;
        boolean z = false;
        boolean z2 = false;
        float f2 = 0.0f;
        float f3 = 0.0f;
        SolverVariable solverVariable2 = null;
        for (int i = 0; i < iK; i++) {
            float fA = this.f926e.a(i);
            SolverVariable solverVariableF = this.f926e.f(i);
            if (solverVariableF.k == SolverVariable.Type.UNRESTRICTED) {
                if (solverVariable == null || f2 > fA) {
                    boolean zV = v(solverVariableF, dVar);
                    z = zV;
                    f2 = fA;
                    solverVariable = solverVariableF;
                } else if (!z && v(solverVariableF, dVar)) {
                    f2 = fA;
                    solverVariable = solverVariableF;
                    z = true;
                }
            } else if (solverVariable == null && fA < 0.0f) {
                if (solverVariable2 == null || f3 > fA) {
                    boolean zV2 = v(solverVariableF, dVar);
                    z2 = zV2;
                    f3 = fA;
                    solverVariable2 = solverVariableF;
                } else if (!z2 && v(solverVariableF, dVar)) {
                    f3 = fA;
                    solverVariable2 = solverVariableF;
                    z2 = true;
                }
            }
        }
        return solverVariable != null ? solverVariable : solverVariable2;
    }

    @Override // androidx.constraintlayout.solver.d.a
    public SolverVariable getKey() {
        return this.f922a;
    }

    b h(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f2, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2) {
        if (solverVariable2 == solverVariable3) {
            this.f926e.c(solverVariable, 1.0f);
            this.f926e.c(solverVariable4, 1.0f);
            this.f926e.c(solverVariable2, -2.0f);
            return this;
        }
        if (f2 == 0.5f) {
            this.f926e.c(solverVariable, 1.0f);
            this.f926e.c(solverVariable2, -1.0f);
            this.f926e.c(solverVariable3, -1.0f);
            this.f926e.c(solverVariable4, 1.0f);
            if (i > 0 || i2 > 0) {
                this.f923b = (-i) + i2;
            }
        } else if (f2 <= 0.0f) {
            this.f926e.c(solverVariable, -1.0f);
            this.f926e.c(solverVariable2, 1.0f);
            this.f923b = i;
        } else if (f2 >= 1.0f) {
            this.f926e.c(solverVariable4, -1.0f);
            this.f926e.c(solverVariable3, 1.0f);
            this.f923b = -i2;
        } else {
            float f3 = 1.0f - f2;
            this.f926e.c(solverVariable, f3 * 1.0f);
            this.f926e.c(solverVariable2, f3 * (-1.0f));
            this.f926e.c(solverVariable3, (-1.0f) * f2);
            this.f926e.c(solverVariable4, 1.0f * f2);
            if (i > 0 || i2 > 0) {
                this.f923b = ((-i) * f3) + (i2 * f2);
            }
        }
        return this;
    }

    b i(SolverVariable solverVariable, int i) {
        this.f922a = solverVariable;
        float f2 = i;
        solverVariable.f911g = f2;
        this.f923b = f2;
        this.f927f = true;
        return this;
    }

    b j(SolverVariable solverVariable, SolverVariable solverVariable2, float f2) {
        this.f926e.c(solverVariable, -1.0f);
        this.f926e.c(solverVariable2, f2);
        return this;
    }

    public b k(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f2) {
        this.f926e.c(solverVariable, -1.0f);
        this.f926e.c(solverVariable2, 1.0f);
        this.f926e.c(solverVariable3, f2);
        this.f926e.c(solverVariable4, -f2);
        return this;
    }

    public b l(float f2, float f3, float f4, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4) {
        this.f923b = 0.0f;
        if (f3 == 0.0f || f2 == f4) {
            this.f926e.c(solverVariable, 1.0f);
            this.f926e.c(solverVariable2, -1.0f);
            this.f926e.c(solverVariable4, 1.0f);
            this.f926e.c(solverVariable3, -1.0f);
        } else if (f2 == 0.0f) {
            this.f926e.c(solverVariable, 1.0f);
            this.f926e.c(solverVariable2, -1.0f);
        } else if (f4 == 0.0f) {
            this.f926e.c(solverVariable3, 1.0f);
            this.f926e.c(solverVariable4, -1.0f);
        } else {
            float f5 = (f2 / f3) / (f4 / f3);
            this.f926e.c(solverVariable, 1.0f);
            this.f926e.c(solverVariable2, -1.0f);
            this.f926e.c(solverVariable4, f5);
            this.f926e.c(solverVariable3, -f5);
        }
        return this;
    }

    public b m(SolverVariable solverVariable, int i) {
        if (i < 0) {
            this.f923b = i * (-1);
            this.f926e.c(solverVariable, 1.0f);
        } else {
            this.f923b = i;
            this.f926e.c(solverVariable, -1.0f);
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
            this.f923b = i;
        }
        if (z) {
            this.f926e.c(solverVariable, 1.0f);
            this.f926e.c(solverVariable2, -1.0f);
        } else {
            this.f926e.c(solverVariable, -1.0f);
            this.f926e.c(solverVariable2, 1.0f);
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
            this.f923b = i;
        }
        if (z) {
            this.f926e.c(solverVariable, 1.0f);
            this.f926e.c(solverVariable2, -1.0f);
            this.f926e.c(solverVariable3, -1.0f);
        } else {
            this.f926e.c(solverVariable, -1.0f);
            this.f926e.c(solverVariable2, 1.0f);
            this.f926e.c(solverVariable3, 1.0f);
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
            this.f923b = i;
        }
        if (z) {
            this.f926e.c(solverVariable, 1.0f);
            this.f926e.c(solverVariable2, -1.0f);
            this.f926e.c(solverVariable3, 1.0f);
        } else {
            this.f926e.c(solverVariable, -1.0f);
            this.f926e.c(solverVariable2, 1.0f);
            this.f926e.c(solverVariable3, -1.0f);
        }
        return this;
    }

    public b q(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f2) {
        this.f926e.c(solverVariable3, 0.5f);
        this.f926e.c(solverVariable4, 0.5f);
        this.f926e.c(solverVariable, -0.5f);
        this.f926e.c(solverVariable2, -0.5f);
        this.f923b = -f2;
        return this;
    }

    void r() {
        float f2 = this.f923b;
        if (f2 < 0.0f) {
            this.f923b = f2 * (-1.0f);
            this.f926e.j();
        }
    }

    boolean s() {
        SolverVariable solverVariable = this.f922a;
        return solverVariable != null && (solverVariable.k == SolverVariable.Type.UNRESTRICTED || this.f923b >= 0.0f);
    }

    boolean t(SolverVariable solverVariable) {
        return this.f926e.h(solverVariable);
    }

    public String toString() {
        return A();
    }

    public boolean u() {
        return this.f922a == null && this.f923b == 0.0f && this.f926e.k() == 0;
    }

    public SolverVariable w(SolverVariable solverVariable) {
        return x(null, solverVariable);
    }

    void y(SolverVariable solverVariable) {
        SolverVariable solverVariable2 = this.f922a;
        if (solverVariable2 != null) {
            this.f926e.c(solverVariable2, -1.0f);
            this.f922a = null;
        }
        float fD = this.f926e.d(solverVariable, true) * (-1.0f);
        this.f922a = solverVariable;
        if (fD == 1.0f) {
            return;
        }
        this.f923b /= fD;
        this.f926e.i(fD);
    }

    public void z() {
        this.f922a = null;
        this.f926e.clear();
        this.f923b = 0.0f;
        this.f927f = false;
    }

    public b(c cVar) {
        this.f926e = new androidx.constraintlayout.solver.a(this, cVar);
    }
}
