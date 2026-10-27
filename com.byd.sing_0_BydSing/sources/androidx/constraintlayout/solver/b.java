package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.d;
import java.util.ArrayList;

/* JADX INFO: compiled from: ArrayRow.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements d.a {
    public a e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    SolverVariable f1147a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    float f1148b = 0.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f1149c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ArrayList<SolverVariable> f1150d = new ArrayList<>();
    boolean f = false;

    /* JADX INFO: compiled from: ArrayRow.java */
    public interface a {
        float a(int i);

        void b(SolverVariable solverVariable, float f, boolean z);

        float c(SolverVariable solverVariable);

        void clear();

        boolean d(SolverVariable solverVariable);

        int e();

        float f(b bVar, boolean z);

        void g(SolverVariable solverVariable, float f);

        float h(SolverVariable solverVariable, boolean z);

        SolverVariable i(int i);

        void j(float f);

        void k();
    }

    public b() {
    }

    private boolean v(SolverVariable solverVariable, d dVar) {
        return solverVariable.n <= 1;
    }

    private SolverVariable x(boolean[] zArr, SolverVariable solverVariable) {
        SolverVariable.Type type;
        int iE = this.e.e();
        SolverVariable solverVariable2 = null;
        float f = 0.0f;
        for (int i = 0; i < iE; i++) {
            float fA = this.e.a(i);
            if (fA < 0.0f) {
                SolverVariable solverVariableI = this.e.i(i);
                if ((zArr == null || !zArr[solverVariableI.f1141d]) && solverVariableI != solverVariable && (((type = solverVariableI.k) == SolverVariable.Type.SLACK || type == SolverVariable.Type.ERROR) && fA < f)) {
                    f = fA;
                    solverVariable2 = solverVariableI;
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
            this.f1148b += solverVariable.g * this.e.c(solverVariable);
            this.e.h(solverVariable, z);
            if (z) {
                solverVariable.c(this);
            }
        }
    }

    public void C(b bVar, boolean z) {
        this.f1148b += bVar.f1148b * this.e.f(bVar, z);
        if (z) {
            bVar.f1147a.c(this);
        }
    }

    public void D(d dVar) {
        if (dVar.k.length == 0) {
            return;
        }
        boolean z = false;
        while (!z) {
            int iE = this.e.e();
            for (int i = 0; i < iE; i++) {
                SolverVariable solverVariableI = this.e.i(i);
                if (solverVariableI.e != -1 || solverVariableI.h) {
                    this.f1150d.add(solverVariableI);
                }
            }
            if (this.f1150d.size() > 0) {
                for (SolverVariable solverVariable : this.f1150d) {
                    if (solverVariable.h) {
                        B(dVar, solverVariable, true);
                    } else {
                        C(dVar.k[solverVariable.e], true);
                    }
                }
                this.f1150d.clear();
            } else {
                z = true;
            }
        }
    }

    @Override // androidx.constraintlayout.solver.d.a
    public void a(d.a aVar) {
        if (aVar instanceof b) {
            b bVar = (b) aVar;
            this.f1147a = null;
            this.e.clear();
            for (int i = 0; i < bVar.e.e(); i++) {
                this.e.b(bVar.e.i(i), bVar.e.a(i), true);
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
        this.e.g(solverVariable, f);
    }

    @Override // androidx.constraintlayout.solver.d.a
    public SolverVariable c(d dVar, boolean[] zArr) {
        return x(zArr, null);
    }

    @Override // androidx.constraintlayout.solver.d.a
    public void clear() {
        this.e.clear();
        this.f1147a = null;
        this.f1148b = 0.0f;
    }

    public b d(d dVar, int i) {
        this.e.g(dVar.o(i, "ep"), 1.0f);
        this.e.g(dVar.o(i, "em"), -1.0f);
        return this;
    }

    b e(SolverVariable solverVariable, int i) {
        this.e.g(solverVariable, i);
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
        if (this.e.e() == 0) {
            this.f = true;
        }
        return z;
    }

    SolverVariable g(d dVar) {
        int iE = this.e.e();
        SolverVariable solverVariable = null;
        boolean z = false;
        boolean z2 = false;
        float f = 0.0f;
        float f2 = 0.0f;
        SolverVariable solverVariable2 = null;
        for (int i = 0; i < iE; i++) {
            float fA = this.e.a(i);
            SolverVariable solverVariableI = this.e.i(i);
            if (solverVariableI.k == SolverVariable.Type.UNRESTRICTED) {
                if (solverVariable == null || f > fA) {
                    boolean zV = v(solverVariableI, dVar);
                    z = zV;
                    f = fA;
                    solverVariable = solverVariableI;
                } else if (!z && v(solverVariableI, dVar)) {
                    f = fA;
                    solverVariable = solverVariableI;
                    z = true;
                }
            } else if (solverVariable == null && fA < 0.0f) {
                if (solverVariable2 == null || f2 > fA) {
                    boolean zV2 = v(solverVariableI, dVar);
                    z2 = zV2;
                    f2 = fA;
                    solverVariable2 = solverVariableI;
                } else if (!z2 && v(solverVariableI, dVar)) {
                    f2 = fA;
                    solverVariable2 = solverVariableI;
                    z2 = true;
                }
            }
        }
        return solverVariable != null ? solverVariable : solverVariable2;
    }

    @Override // androidx.constraintlayout.solver.d.a
    public SolverVariable getKey() {
        return this.f1147a;
    }

    b h(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2) {
        if (solverVariable2 == solverVariable3) {
            this.e.g(solverVariable, 1.0f);
            this.e.g(solverVariable4, 1.0f);
            this.e.g(solverVariable2, -2.0f);
            return this;
        }
        if (f == 0.5f) {
            this.e.g(solverVariable, 1.0f);
            this.e.g(solverVariable2, -1.0f);
            this.e.g(solverVariable3, -1.0f);
            this.e.g(solverVariable4, 1.0f);
            if (i > 0 || i2 > 0) {
                this.f1148b = (-i) + i2;
            }
        } else if (f <= 0.0f) {
            this.e.g(solverVariable, -1.0f);
            this.e.g(solverVariable2, 1.0f);
            this.f1148b = i;
        } else if (f >= 1.0f) {
            this.e.g(solverVariable4, -1.0f);
            this.e.g(solverVariable3, 1.0f);
            this.f1148b = -i2;
        } else {
            float f2 = 1.0f - f;
            this.e.g(solverVariable, f2 * 1.0f);
            this.e.g(solverVariable2, f2 * (-1.0f));
            this.e.g(solverVariable3, (-1.0f) * f);
            this.e.g(solverVariable4, 1.0f * f);
            if (i > 0 || i2 > 0) {
                this.f1148b = ((-i) * f2) + (i2 * f);
            }
        }
        return this;
    }

    b i(SolverVariable solverVariable, int i) {
        this.f1147a = solverVariable;
        float f = i;
        solverVariable.g = f;
        this.f1148b = f;
        this.f = true;
        return this;
    }

    b j(SolverVariable solverVariable, SolverVariable solverVariable2, float f) {
        this.e.g(solverVariable, -1.0f);
        this.e.g(solverVariable2, f);
        return this;
    }

    public b k(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.e.g(solverVariable, -1.0f);
        this.e.g(solverVariable2, 1.0f);
        this.e.g(solverVariable3, f);
        this.e.g(solverVariable4, -f);
        return this;
    }

    public b l(float f, float f2, float f3, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4) {
        this.f1148b = 0.0f;
        if (f2 == 0.0f || f == f3) {
            this.e.g(solverVariable, 1.0f);
            this.e.g(solverVariable2, -1.0f);
            this.e.g(solverVariable4, 1.0f);
            this.e.g(solverVariable3, -1.0f);
        } else if (f == 0.0f) {
            this.e.g(solverVariable, 1.0f);
            this.e.g(solverVariable2, -1.0f);
        } else if (f3 == 0.0f) {
            this.e.g(solverVariable3, 1.0f);
            this.e.g(solverVariable4, -1.0f);
        } else {
            float f4 = (f / f2) / (f3 / f2);
            this.e.g(solverVariable, 1.0f);
            this.e.g(solverVariable2, -1.0f);
            this.e.g(solverVariable4, f4);
            this.e.g(solverVariable3, -f4);
        }
        return this;
    }

    public b m(SolverVariable solverVariable, int i) {
        if (i < 0) {
            this.f1148b = i * (-1);
            this.e.g(solverVariable, 1.0f);
        } else {
            this.f1148b = i;
            this.e.g(solverVariable, -1.0f);
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
            this.f1148b = i;
        }
        if (z) {
            this.e.g(solverVariable, 1.0f);
            this.e.g(solverVariable2, -1.0f);
        } else {
            this.e.g(solverVariable, -1.0f);
            this.e.g(solverVariable2, 1.0f);
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
            this.f1148b = i;
        }
        if (z) {
            this.e.g(solverVariable, 1.0f);
            this.e.g(solverVariable2, -1.0f);
            this.e.g(solverVariable3, -1.0f);
        } else {
            this.e.g(solverVariable, -1.0f);
            this.e.g(solverVariable2, 1.0f);
            this.e.g(solverVariable3, 1.0f);
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
            this.f1148b = i;
        }
        if (z) {
            this.e.g(solverVariable, 1.0f);
            this.e.g(solverVariable2, -1.0f);
            this.e.g(solverVariable3, 1.0f);
        } else {
            this.e.g(solverVariable, -1.0f);
            this.e.g(solverVariable2, 1.0f);
            this.e.g(solverVariable3, -1.0f);
        }
        return this;
    }

    public b q(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.e.g(solverVariable3, 0.5f);
        this.e.g(solverVariable4, 0.5f);
        this.e.g(solverVariable, -0.5f);
        this.e.g(solverVariable2, -0.5f);
        this.f1148b = -f;
        return this;
    }

    void r() {
        float f = this.f1148b;
        if (f < 0.0f) {
            this.f1148b = f * (-1.0f);
            this.e.k();
        }
    }

    boolean s() {
        SolverVariable solverVariable = this.f1147a;
        return solverVariable != null && (solverVariable.k == SolverVariable.Type.UNRESTRICTED || this.f1148b >= 0.0f);
    }

    boolean t(SolverVariable solverVariable) {
        return this.e.d(solverVariable);
    }

    public String toString() {
        return A();
    }

    public boolean u() {
        return this.f1147a == null && this.f1148b == 0.0f && this.e.e() == 0;
    }

    public SolverVariable w(SolverVariable solverVariable) {
        return x(null, solverVariable);
    }

    void y(SolverVariable solverVariable) {
        SolverVariable solverVariable2 = this.f1147a;
        if (solverVariable2 != null) {
            this.e.g(solverVariable2, -1.0f);
            this.f1147a = null;
        }
        float fH = this.e.h(solverVariable, true) * (-1.0f);
        this.f1147a = solverVariable;
        if (fH == 1.0f) {
            return;
        }
        this.f1148b /= fH;
        this.e.j(fH);
    }

    public void z() {
        this.f1147a = null;
        this.e.clear();
        this.f1148b = 0.0f;
        this.f = false;
    }

    public b(c cVar) {
        this.e = new androidx.constraintlayout.solver.a(this, cVar);
    }
}
