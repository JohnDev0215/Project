package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.d;
import java.util.ArrayList;

/* JADX INFO: compiled from: ArrayRow.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements d.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f707e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    SolverVariable f703a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    float f704b = 0.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f705c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ArrayList<SolverVariable> f706d = new ArrayList<>();
    boolean f = false;

    /* JADX INFO: compiled from: ArrayRow.java */
    public interface a {
        float a(int i);

        float b(b bVar, boolean z);

        void c(SolverVariable solverVariable, float f);

        void clear();

        float d(SolverVariable solverVariable, boolean z);

        void e(SolverVariable solverVariable, float f, boolean z);

        SolverVariable f(int i);

        float g(SolverVariable solverVariable);

        boolean h(SolverVariable solverVariable);

        void i(float f);

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
        int iK = this.f707e.k();
        SolverVariable solverVariable2 = null;
        float f = 0.0f;
        for (int i = 0; i < iK; i++) {
            float fA = this.f707e.a(i);
            if (fA < 0.0f) {
                SolverVariable solverVariableF = this.f707e.f(i);
                if ((zArr == null || !zArr[solverVariableF.f695d]) && solverVariableF != solverVariable && (((type = solverVariableF.k) == SolverVariable.Type.SLACK || type == SolverVariable.Type.ERROR) && fA < f)) {
                    f = fA;
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
        if (solverVariable.h) {
            this.f704b += solverVariable.g * this.f707e.g(solverVariable);
            this.f707e.d(solverVariable, z);
            if (z) {
                solverVariable.c(this);
            }
        }
    }

    public void C(b bVar, boolean z) {
        this.f704b += bVar.f704b * this.f707e.b(bVar, z);
        if (z) {
            bVar.f703a.c(this);
        }
    }

    public void D(d dVar) {
        if (dVar.k.length == 0) {
            return;
        }
        boolean z = false;
        while (!z) {
            int iK = this.f707e.k();
            for (int i = 0; i < iK; i++) {
                SolverVariable solverVariableF = this.f707e.f(i);
                if (solverVariableF.f696e != -1 || solverVariableF.h) {
                    this.f706d.add(solverVariableF);
                }
            }
            if (this.f706d.size() > 0) {
                for (SolverVariable solverVariable : this.f706d) {
                    if (solverVariable.h) {
                        B(dVar, solverVariable, true);
                    } else {
                        C(dVar.k[solverVariable.f696e], true);
                    }
                }
                this.f706d.clear();
            } else {
                z = true;
            }
        }
    }

    @Override // androidx.constraintlayout.solver.d.a
    public void a(d.a aVar) {
        if (aVar instanceof b) {
            b bVar = (b) aVar;
            this.f703a = null;
            this.f707e.clear();
            for (int i = 0; i < bVar.f707e.k(); i++) {
                this.f707e.e(bVar.f707e.f(i), bVar.f707e.a(i), true);
            }
        }
    }

    @Override // androidx.constraintlayout.solver.d.a
    public void b(SolverVariable solverVariable) {
        int i = solverVariable.f;
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
        this.f707e.c(solverVariable, f);
    }

    @Override // androidx.constraintlayout.solver.d.a
    public SolverVariable c(d dVar, boolean[] zArr) {
        return x(zArr, null);
    }

    @Override // androidx.constraintlayout.solver.d.a
    public void clear() {
        this.f707e.clear();
        this.f703a = null;
        this.f704b = 0.0f;
    }

    public b d(d dVar, int i) {
        this.f707e.c(dVar.o(i, "ep"), 1.0f);
        this.f707e.c(dVar.o(i, "em"), -1.0f);
        return this;
    }

    b e(SolverVariable solverVariable, int i) {
        this.f707e.c(solverVariable, i);
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
        if (this.f707e.k() == 0) {
            this.f = true;
        }
        return z;
    }

    SolverVariable g(d dVar) {
        int iK = this.f707e.k();
        SolverVariable solverVariable = null;
        boolean z = false;
        boolean z2 = false;
        float f = 0.0f;
        float f2 = 0.0f;
        SolverVariable solverVariable2 = null;
        for (int i = 0; i < iK; i++) {
            float fA = this.f707e.a(i);
            SolverVariable solverVariableF = this.f707e.f(i);
            if (solverVariableF.k == SolverVariable.Type.UNRESTRICTED) {
                if (solverVariable == null || f > fA) {
                    boolean zV = v(solverVariableF, dVar);
                    z = zV;
                    f = fA;
                    solverVariable = solverVariableF;
                } else if (!z && v(solverVariableF, dVar)) {
                    f = fA;
                    solverVariable = solverVariableF;
                    z = true;
                }
            } else if (solverVariable == null && fA < 0.0f) {
                if (solverVariable2 == null || f2 > fA) {
                    boolean zV2 = v(solverVariableF, dVar);
                    z2 = zV2;
                    f2 = fA;
                    solverVariable2 = solverVariableF;
                } else if (!z2 && v(solverVariableF, dVar)) {
                    f2 = fA;
                    solverVariable2 = solverVariableF;
                    z2 = true;
                }
            }
        }
        return solverVariable != null ? solverVariable : solverVariable2;
    }

    @Override // androidx.constraintlayout.solver.d.a
    public SolverVariable getKey() {
        return this.f703a;
    }

    b h(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2) {
        if (solverVariable2 == solverVariable3) {
            this.f707e.c(solverVariable, 1.0f);
            this.f707e.c(solverVariable4, 1.0f);
            this.f707e.c(solverVariable2, -2.0f);
            return this;
        }
        if (f == 0.5f) {
            this.f707e.c(solverVariable, 1.0f);
            this.f707e.c(solverVariable2, -1.0f);
            this.f707e.c(solverVariable3, -1.0f);
            this.f707e.c(solverVariable4, 1.0f);
            if (i > 0 || i2 > 0) {
                this.f704b = (-i) + i2;
            }
        } else if (f <= 0.0f) {
            this.f707e.c(solverVariable, -1.0f);
            this.f707e.c(solverVariable2, 1.0f);
            this.f704b = i;
        } else if (f >= 1.0f) {
            this.f707e.c(solverVariable4, -1.0f);
            this.f707e.c(solverVariable3, 1.0f);
            this.f704b = -i2;
        } else {
            float f2 = 1.0f - f;
            this.f707e.c(solverVariable, f2 * 1.0f);
            this.f707e.c(solverVariable2, f2 * (-1.0f));
            this.f707e.c(solverVariable3, (-1.0f) * f);
            this.f707e.c(solverVariable4, 1.0f * f);
            if (i > 0 || i2 > 0) {
                this.f704b = ((-i) * f2) + (i2 * f);
            }
        }
        return this;
    }

    b i(SolverVariable solverVariable, int i) {
        this.f703a = solverVariable;
        float f = i;
        solverVariable.g = f;
        this.f704b = f;
        this.f = true;
        return this;
    }

    b j(SolverVariable solverVariable, SolverVariable solverVariable2, float f) {
        this.f707e.c(solverVariable, -1.0f);
        this.f707e.c(solverVariable2, f);
        return this;
    }

    public b k(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.f707e.c(solverVariable, -1.0f);
        this.f707e.c(solverVariable2, 1.0f);
        this.f707e.c(solverVariable3, f);
        this.f707e.c(solverVariable4, -f);
        return this;
    }

    public b l(float f, float f2, float f3, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4) {
        this.f704b = 0.0f;
        if (f2 == 0.0f || f == f3) {
            this.f707e.c(solverVariable, 1.0f);
            this.f707e.c(solverVariable2, -1.0f);
            this.f707e.c(solverVariable4, 1.0f);
            this.f707e.c(solverVariable3, -1.0f);
        } else if (f == 0.0f) {
            this.f707e.c(solverVariable, 1.0f);
            this.f707e.c(solverVariable2, -1.0f);
        } else if (f3 == 0.0f) {
            this.f707e.c(solverVariable3, 1.0f);
            this.f707e.c(solverVariable4, -1.0f);
        } else {
            float f4 = (f / f2) / (f3 / f2);
            this.f707e.c(solverVariable, 1.0f);
            this.f707e.c(solverVariable2, -1.0f);
            this.f707e.c(solverVariable4, f4);
            this.f707e.c(solverVariable3, -f4);
        }
        return this;
    }

    public b m(SolverVariable solverVariable, int i) {
        if (i < 0) {
            this.f704b = i * (-1);
            this.f707e.c(solverVariable, 1.0f);
        } else {
            this.f704b = i;
            this.f707e.c(solverVariable, -1.0f);
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
            this.f704b = i;
        }
        if (z) {
            this.f707e.c(solverVariable, 1.0f);
            this.f707e.c(solverVariable2, -1.0f);
        } else {
            this.f707e.c(solverVariable, -1.0f);
            this.f707e.c(solverVariable2, 1.0f);
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
            this.f704b = i;
        }
        if (z) {
            this.f707e.c(solverVariable, 1.0f);
            this.f707e.c(solverVariable2, -1.0f);
            this.f707e.c(solverVariable3, -1.0f);
        } else {
            this.f707e.c(solverVariable, -1.0f);
            this.f707e.c(solverVariable2, 1.0f);
            this.f707e.c(solverVariable3, 1.0f);
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
            this.f704b = i;
        }
        if (z) {
            this.f707e.c(solverVariable, 1.0f);
            this.f707e.c(solverVariable2, -1.0f);
            this.f707e.c(solverVariable3, 1.0f);
        } else {
            this.f707e.c(solverVariable, -1.0f);
            this.f707e.c(solverVariable2, 1.0f);
            this.f707e.c(solverVariable3, -1.0f);
        }
        return this;
    }

    public b q(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.f707e.c(solverVariable3, 0.5f);
        this.f707e.c(solverVariable4, 0.5f);
        this.f707e.c(solverVariable, -0.5f);
        this.f707e.c(solverVariable2, -0.5f);
        this.f704b = -f;
        return this;
    }

    void r() {
        float f = this.f704b;
        if (f < 0.0f) {
            this.f704b = f * (-1.0f);
            this.f707e.j();
        }
    }

    boolean s() {
        SolverVariable solverVariable = this.f703a;
        return solverVariable != null && (solverVariable.k == SolverVariable.Type.UNRESTRICTED || this.f704b >= 0.0f);
    }

    boolean t(SolverVariable solverVariable) {
        return this.f707e.h(solverVariable);
    }

    public String toString() {
        return A();
    }

    public boolean u() {
        return this.f703a == null && this.f704b == 0.0f && this.f707e.k() == 0;
    }

    public SolverVariable w(SolverVariable solverVariable) {
        return x(null, solverVariable);
    }

    void y(SolverVariable solverVariable) {
        SolverVariable solverVariable2 = this.f703a;
        if (solverVariable2 != null) {
            this.f707e.c(solverVariable2, -1.0f);
            this.f703a = null;
        }
        float fD = this.f707e.d(solverVariable, true) * (-1.0f);
        this.f703a = solverVariable;
        if (fD == 1.0f) {
            return;
        }
        this.f704b /= fD;
        this.f707e.i(fD);
    }

    public void z() {
        this.f703a = null;
        this.f707e.clear();
        this.f704b = 0.0f;
        this.f = false;
    }

    public b(c cVar) {
        this.f707e = new androidx.constraintlayout.solver.a(this, cVar);
    }
}
