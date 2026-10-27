package defpackage;

import androidx.constraintlayout.solver.SolverVariable;
import defpackage.n5;
import java.util.ArrayList;

/* JADX INFO: compiled from: ArrayRow.java */
/* JADX INFO: loaded from: classes.dex */
public class l5 implements n5.a {
    public a e;
    public SolverVariable a = null;
    public float b = 0.0f;
    public boolean c = false;
    public ArrayList<SolverVariable> d = new ArrayList<>();
    public boolean f = false;

    /* JADX INFO: compiled from: ArrayRow.java */
    public interface a {
        float a(int i);

        float b(l5 l5Var, boolean z);

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

    public l5() {
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00cf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String A() {
        /*
            Method dump skipped, instruction units count: 255
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.l5.A():java.lang.String");
    }

    public void B(n5 n5Var, SolverVariable solverVariable, boolean z) {
        if (solverVariable.h) {
            this.b += solverVariable.g * this.e.g(solverVariable);
            this.e.d(solverVariable, z);
            if (z) {
                solverVariable.c(this);
            }
        }
    }

    public void C(l5 l5Var, boolean z) {
        this.b += l5Var.b * this.e.b(l5Var, z);
        if (z) {
            l5Var.a.c(this);
        }
    }

    public void D(n5 n5Var) {
        if (n5Var.k.length == 0) {
            return;
        }
        boolean z = false;
        while (!z) {
            int iK = this.e.k();
            for (int i = 0; i < iK; i++) {
                SolverVariable solverVariableF = this.e.f(i);
                if (solverVariableF.e != -1 || solverVariableF.h) {
                    this.d.add(solverVariableF);
                }
            }
            if (this.d.size() > 0) {
                for (SolverVariable solverVariable : this.d) {
                    if (solverVariable.h) {
                        B(n5Var, solverVariable, true);
                    } else {
                        C(n5Var.k[solverVariable.e], true);
                    }
                }
                this.d.clear();
            } else {
                z = true;
            }
        }
    }

    @Override // n5.a
    public void a(n5.a aVar) {
        if (aVar instanceof l5) {
            l5 l5Var = (l5) aVar;
            this.a = null;
            this.e.clear();
            for (int i = 0; i < l5Var.e.k(); i++) {
                this.e.e(l5Var.e.f(i), l5Var.e.a(i), true);
            }
        }
    }

    @Override // n5.a
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
        this.e.c(solverVariable, f);
    }

    @Override // n5.a
    public SolverVariable c(n5 n5Var, boolean[] zArr) {
        return x(zArr, null);
    }

    @Override // n5.a
    public void clear() {
        this.e.clear();
        this.a = null;
        this.b = 0.0f;
    }

    public l5 d(n5 n5Var, int i) {
        this.e.c(n5Var.o(i, "ep"), 1.0f);
        this.e.c(n5Var.o(i, "em"), -1.0f);
        return this;
    }

    public l5 e(SolverVariable solverVariable, int i) {
        this.e.c(solverVariable, i);
        return this;
    }

    public boolean f(n5 n5Var) {
        boolean z;
        SolverVariable solverVariableG = g(n5Var);
        if (solverVariableG == null) {
            z = true;
        } else {
            y(solverVariableG);
            z = false;
        }
        if (this.e.k() == 0) {
            this.f = true;
        }
        return z;
    }

    public SolverVariable g(n5 n5Var) {
        int iK = this.e.k();
        SolverVariable solverVariable = null;
        boolean z = false;
        boolean z2 = false;
        float f = 0.0f;
        float f2 = 0.0f;
        SolverVariable solverVariable2 = null;
        for (int i = 0; i < iK; i++) {
            float fA = this.e.a(i);
            SolverVariable solverVariableF = this.e.f(i);
            if (solverVariableF.k == SolverVariable.Type.UNRESTRICTED) {
                if (solverVariable == null || f > fA) {
                    boolean zV = v(solverVariableF, n5Var);
                    z = zV;
                    f = fA;
                    solverVariable = solverVariableF;
                } else if (!z && v(solverVariableF, n5Var)) {
                    f = fA;
                    solverVariable = solverVariableF;
                    z = true;
                }
            } else if (solverVariable == null && fA < 0.0f) {
                if (solverVariable2 == null || f2 > fA) {
                    boolean zV2 = v(solverVariableF, n5Var);
                    z2 = zV2;
                    f2 = fA;
                    solverVariable2 = solverVariableF;
                } else if (!z2 && v(solverVariableF, n5Var)) {
                    f2 = fA;
                    solverVariable2 = solverVariableF;
                    z2 = true;
                }
            }
        }
        return solverVariable != null ? solverVariable : solverVariable2;
    }

    @Override // n5.a
    public SolverVariable getKey() {
        return this.a;
    }

    public l5 h(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2) {
        if (solverVariable2 == solverVariable3) {
            this.e.c(solverVariable, 1.0f);
            this.e.c(solverVariable4, 1.0f);
            this.e.c(solverVariable2, -2.0f);
            return this;
        }
        if (f == 0.5f) {
            this.e.c(solverVariable, 1.0f);
            this.e.c(solverVariable2, -1.0f);
            this.e.c(solverVariable3, -1.0f);
            this.e.c(solverVariable4, 1.0f);
            if (i > 0 || i2 > 0) {
                this.b = (-i) + i2;
            }
        } else if (f <= 0.0f) {
            this.e.c(solverVariable, -1.0f);
            this.e.c(solverVariable2, 1.0f);
            this.b = i;
        } else if (f >= 1.0f) {
            this.e.c(solverVariable4, -1.0f);
            this.e.c(solverVariable3, 1.0f);
            this.b = -i2;
        } else {
            float f2 = 1.0f - f;
            this.e.c(solverVariable, f2 * 1.0f);
            this.e.c(solverVariable2, f2 * (-1.0f));
            this.e.c(solverVariable3, (-1.0f) * f);
            this.e.c(solverVariable4, 1.0f * f);
            if (i > 0 || i2 > 0) {
                this.b = ((-i) * f2) + (i2 * f);
            }
        }
        return this;
    }

    public l5 i(SolverVariable solverVariable, int i) {
        this.a = solverVariable;
        float f = i;
        solverVariable.g = f;
        this.b = f;
        this.f = true;
        return this;
    }

    public l5 j(SolverVariable solverVariable, SolverVariable solverVariable2, float f) {
        this.e.c(solverVariable, -1.0f);
        this.e.c(solverVariable2, f);
        return this;
    }

    public l5 k(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.e.c(solverVariable, -1.0f);
        this.e.c(solverVariable2, 1.0f);
        this.e.c(solverVariable3, f);
        this.e.c(solverVariable4, -f);
        return this;
    }

    public l5 l(float f, float f2, float f3, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4) {
        this.b = 0.0f;
        if (f2 == 0.0f || f == f3) {
            this.e.c(solverVariable, 1.0f);
            this.e.c(solverVariable2, -1.0f);
            this.e.c(solverVariable4, 1.0f);
            this.e.c(solverVariable3, -1.0f);
        } else if (f == 0.0f) {
            this.e.c(solverVariable, 1.0f);
            this.e.c(solverVariable2, -1.0f);
        } else if (f3 == 0.0f) {
            this.e.c(solverVariable3, 1.0f);
            this.e.c(solverVariable4, -1.0f);
        } else {
            float f4 = (f / f2) / (f3 / f2);
            this.e.c(solverVariable, 1.0f);
            this.e.c(solverVariable2, -1.0f);
            this.e.c(solverVariable4, f4);
            this.e.c(solverVariable3, -f4);
        }
        return this;
    }

    public l5 m(SolverVariable solverVariable, int i) {
        if (i < 0) {
            this.b = i * (-1);
            this.e.c(solverVariable, 1.0f);
        } else {
            this.b = i;
            this.e.c(solverVariable, -1.0f);
        }
        return this;
    }

    public l5 n(SolverVariable solverVariable, SolverVariable solverVariable2, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = i;
        }
        if (z) {
            this.e.c(solverVariable, 1.0f);
            this.e.c(solverVariable2, -1.0f);
        } else {
            this.e.c(solverVariable, -1.0f);
            this.e.c(solverVariable2, 1.0f);
        }
        return this;
    }

    public l5 o(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = i;
        }
        if (z) {
            this.e.c(solverVariable, 1.0f);
            this.e.c(solverVariable2, -1.0f);
            this.e.c(solverVariable3, -1.0f);
        } else {
            this.e.c(solverVariable, -1.0f);
            this.e.c(solverVariable2, 1.0f);
            this.e.c(solverVariable3, 1.0f);
        }
        return this;
    }

    public l5 p(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.b = i;
        }
        if (z) {
            this.e.c(solverVariable, 1.0f);
            this.e.c(solverVariable2, -1.0f);
            this.e.c(solverVariable3, 1.0f);
        } else {
            this.e.c(solverVariable, -1.0f);
            this.e.c(solverVariable2, 1.0f);
            this.e.c(solverVariable3, -1.0f);
        }
        return this;
    }

    public l5 q(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.e.c(solverVariable3, 0.5f);
        this.e.c(solverVariable4, 0.5f);
        this.e.c(solverVariable, -0.5f);
        this.e.c(solverVariable2, -0.5f);
        this.b = -f;
        return this;
    }

    public void r() {
        float f = this.b;
        if (f < 0.0f) {
            this.b = f * (-1.0f);
            this.e.j();
        }
    }

    public boolean s() {
        SolverVariable solverVariable = this.a;
        return solverVariable != null && (solverVariable.k == SolverVariable.Type.UNRESTRICTED || this.b >= 0.0f);
    }

    public boolean t(SolverVariable solverVariable) {
        return this.e.h(solverVariable);
    }

    public String toString() {
        return A();
    }

    public boolean u() {
        return this.a == null && this.b == 0.0f && this.e.k() == 0;
    }

    public final boolean v(SolverVariable solverVariable, n5 n5Var) {
        return solverVariable.n <= 1;
    }

    public SolverVariable w(SolverVariable solverVariable) {
        return x(null, solverVariable);
    }

    public final SolverVariable x(boolean[] zArr, SolverVariable solverVariable) {
        SolverVariable.Type type;
        int iK = this.e.k();
        SolverVariable solverVariable2 = null;
        float f = 0.0f;
        for (int i = 0; i < iK; i++) {
            float fA = this.e.a(i);
            if (fA < 0.0f) {
                SolverVariable solverVariableF = this.e.f(i);
                if ((zArr == null || !zArr[solverVariableF.d]) && solverVariableF != solverVariable && (((type = solverVariableF.k) == SolverVariable.Type.SLACK || type == SolverVariable.Type.ERROR) && fA < f)) {
                    f = fA;
                    solverVariable2 = solverVariableF;
                }
            }
        }
        return solverVariable2;
    }

    public void y(SolverVariable solverVariable) {
        SolverVariable solverVariable2 = this.a;
        if (solverVariable2 != null) {
            this.e.c(solverVariable2, -1.0f);
            this.a = null;
        }
        float fD = this.e.d(solverVariable, true) * (-1.0f);
        this.a = solverVariable;
        if (fD == 1.0f) {
            return;
        }
        this.b /= fD;
        this.e.i(fD);
    }

    public void z() {
        this.a = null;
        this.e.clear();
        this.b = 0.0f;
        this.f = false;
    }

    public l5(m5 m5Var) {
        this.e = new k5(this, m5Var);
    }
}
