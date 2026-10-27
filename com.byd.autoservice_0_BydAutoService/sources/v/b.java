package v;

import androidx.constraintlayout.solver.SolverVariable;
import java.util.ArrayList;
import v.d;

/* JADX INFO: compiled from: ArrayRow.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements d.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f5365e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SolverVariable f5361a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f5362b = 0.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f5363c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList<SolverVariable> f5364d = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5366f = false;

    /* JADX INFO: compiled from: ArrayRow.java */
    public interface a {
        float a(int i3);

        float b(b bVar, boolean z3);

        void c(SolverVariable solverVariable, float f3);

        void clear();

        float d(SolverVariable solverVariable, boolean z3);

        void e(SolverVariable solverVariable, float f3, boolean z3);

        SolverVariable f(int i3);

        float g(SolverVariable solverVariable);

        boolean h(SolverVariable solverVariable);

        void i(float f3);

        void j();

        int k();
    }

    public b() {
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
        throw new UnsupportedOperationException("Method not decompiled: v.b.A():java.lang.String");
    }

    public void B(d dVar, SolverVariable solverVariable, boolean z3) {
        if (solverVariable.f817h) {
            this.f5362b += solverVariable.f816g * this.f5365e.g(solverVariable);
            this.f5365e.d(solverVariable, z3);
            if (z3) {
                solverVariable.c(this);
            }
        }
    }

    public void C(b bVar, boolean z3) {
        this.f5362b += bVar.f5362b * this.f5365e.b(bVar, z3);
        if (z3) {
            bVar.f5361a.c(this);
        }
    }

    public void D(d dVar) {
        if (dVar.f5381k.length == 0) {
            return;
        }
        boolean z3 = false;
        while (!z3) {
            int iK = this.f5365e.k();
            for (int i3 = 0; i3 < iK; i3++) {
                SolverVariable solverVariableF = this.f5365e.f(i3);
                if (solverVariableF.f814e != -1 || solverVariableF.f817h) {
                    this.f5364d.add(solverVariableF);
                }
            }
            if (this.f5364d.size() > 0) {
                for (SolverVariable solverVariable : this.f5364d) {
                    if (solverVariable.f817h) {
                        B(dVar, solverVariable, true);
                    } else {
                        C(dVar.f5381k[solverVariable.f814e], true);
                    }
                }
                this.f5364d.clear();
            } else {
                z3 = true;
            }
        }
    }

    @Override // v.d.a
    public void a(d.a aVar) {
        if (aVar instanceof b) {
            b bVar = (b) aVar;
            this.f5361a = null;
            this.f5365e.clear();
            for (int i3 = 0; i3 < bVar.f5365e.k(); i3++) {
                this.f5365e.e(bVar.f5365e.f(i3), bVar.f5365e.a(i3), true);
            }
        }
    }

    @Override // v.d.a
    public void b(SolverVariable solverVariable) {
        int i3 = solverVariable.f815f;
        float f3 = 1.0f;
        if (i3 != 1) {
            if (i3 == 2) {
                f3 = 1000.0f;
            } else if (i3 == 3) {
                f3 = 1000000.0f;
            } else if (i3 == 4) {
                f3 = 1.0E9f;
            } else if (i3 == 5) {
                f3 = 1.0E12f;
            }
        }
        this.f5365e.c(solverVariable, f3);
    }

    @Override // v.d.a
    public SolverVariable c(d dVar, boolean[] zArr) {
        return x(zArr, null);
    }

    @Override // v.d.a
    public void clear() {
        this.f5365e.clear();
        this.f5361a = null;
        this.f5362b = 0.0f;
    }

    public b d(d dVar, int i3) {
        this.f5365e.c(dVar.o(i3, "ep"), 1.0f);
        this.f5365e.c(dVar.o(i3, "em"), -1.0f);
        return this;
    }

    public b e(SolverVariable solverVariable, int i3) {
        this.f5365e.c(solverVariable, i3);
        return this;
    }

    public boolean f(d dVar) {
        boolean z3;
        SolverVariable solverVariableG = g(dVar);
        if (solverVariableG == null) {
            z3 = true;
        } else {
            y(solverVariableG);
            z3 = false;
        }
        if (this.f5365e.k() == 0) {
            this.f5366f = true;
        }
        return z3;
    }

    public SolverVariable g(d dVar) {
        int iK = this.f5365e.k();
        SolverVariable solverVariable = null;
        boolean z3 = false;
        boolean z4 = false;
        float f3 = 0.0f;
        float f4 = 0.0f;
        SolverVariable solverVariable2 = null;
        for (int i3 = 0; i3 < iK; i3++) {
            float fA = this.f5365e.a(i3);
            SolverVariable solverVariableF = this.f5365e.f(i3);
            if (solverVariableF.f820k == SolverVariable.Type.UNRESTRICTED) {
                if (solverVariable == null || f3 > fA) {
                    boolean zV = v(solverVariableF, dVar);
                    z3 = zV;
                    f3 = fA;
                    solverVariable = solverVariableF;
                } else if (!z3 && v(solverVariableF, dVar)) {
                    f3 = fA;
                    solverVariable = solverVariableF;
                    z3 = true;
                }
            } else if (solverVariable == null && fA < 0.0f) {
                if (solverVariable2 == null || f4 > fA) {
                    boolean zV2 = v(solverVariableF, dVar);
                    z4 = zV2;
                    f4 = fA;
                    solverVariable2 = solverVariableF;
                } else if (!z4 && v(solverVariableF, dVar)) {
                    f4 = fA;
                    solverVariable2 = solverVariableF;
                    z4 = true;
                }
            }
        }
        return solverVariable != null ? solverVariable : solverVariable2;
    }

    @Override // v.d.a
    public SolverVariable getKey() {
        return this.f5361a;
    }

    public b h(SolverVariable solverVariable, SolverVariable solverVariable2, int i3, float f3, SolverVariable solverVariable3, SolverVariable solverVariable4, int i4) {
        if (solverVariable2 == solverVariable3) {
            this.f5365e.c(solverVariable, 1.0f);
            this.f5365e.c(solverVariable4, 1.0f);
            this.f5365e.c(solverVariable2, -2.0f);
            return this;
        }
        if (f3 == 0.5f) {
            this.f5365e.c(solverVariable, 1.0f);
            this.f5365e.c(solverVariable2, -1.0f);
            this.f5365e.c(solverVariable3, -1.0f);
            this.f5365e.c(solverVariable4, 1.0f);
            if (i3 > 0 || i4 > 0) {
                this.f5362b = (-i3) + i4;
            }
        } else if (f3 <= 0.0f) {
            this.f5365e.c(solverVariable, -1.0f);
            this.f5365e.c(solverVariable2, 1.0f);
            this.f5362b = i3;
        } else if (f3 >= 1.0f) {
            this.f5365e.c(solverVariable4, -1.0f);
            this.f5365e.c(solverVariable3, 1.0f);
            this.f5362b = -i4;
        } else {
            float f4 = 1.0f - f3;
            this.f5365e.c(solverVariable, f4 * 1.0f);
            this.f5365e.c(solverVariable2, f4 * (-1.0f));
            this.f5365e.c(solverVariable3, (-1.0f) * f3);
            this.f5365e.c(solverVariable4, 1.0f * f3);
            if (i3 > 0 || i4 > 0) {
                this.f5362b = ((-i3) * f4) + (i4 * f3);
            }
        }
        return this;
    }

    public b i(SolverVariable solverVariable, int i3) {
        this.f5361a = solverVariable;
        float f3 = i3;
        solverVariable.f816g = f3;
        this.f5362b = f3;
        this.f5366f = true;
        return this;
    }

    public b j(SolverVariable solverVariable, SolverVariable solverVariable2, float f3) {
        this.f5365e.c(solverVariable, -1.0f);
        this.f5365e.c(solverVariable2, f3);
        return this;
    }

    public b k(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f3) {
        this.f5365e.c(solverVariable, -1.0f);
        this.f5365e.c(solverVariable2, 1.0f);
        this.f5365e.c(solverVariable3, f3);
        this.f5365e.c(solverVariable4, -f3);
        return this;
    }

    public b l(float f3, float f4, float f5, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4) {
        this.f5362b = 0.0f;
        if (f4 == 0.0f || f3 == f5) {
            this.f5365e.c(solverVariable, 1.0f);
            this.f5365e.c(solverVariable2, -1.0f);
            this.f5365e.c(solverVariable4, 1.0f);
            this.f5365e.c(solverVariable3, -1.0f);
        } else if (f3 == 0.0f) {
            this.f5365e.c(solverVariable, 1.0f);
            this.f5365e.c(solverVariable2, -1.0f);
        } else if (f5 == 0.0f) {
            this.f5365e.c(solverVariable3, 1.0f);
            this.f5365e.c(solverVariable4, -1.0f);
        } else {
            float f6 = (f3 / f4) / (f5 / f4);
            this.f5365e.c(solverVariable, 1.0f);
            this.f5365e.c(solverVariable2, -1.0f);
            this.f5365e.c(solverVariable4, f6);
            this.f5365e.c(solverVariable3, -f6);
        }
        return this;
    }

    public b m(SolverVariable solverVariable, int i3) {
        if (i3 < 0) {
            this.f5362b = i3 * (-1);
            this.f5365e.c(solverVariable, 1.0f);
        } else {
            this.f5362b = i3;
            this.f5365e.c(solverVariable, -1.0f);
        }
        return this;
    }

    public b n(SolverVariable solverVariable, SolverVariable solverVariable2, int i3) {
        boolean z3 = false;
        if (i3 != 0) {
            if (i3 < 0) {
                i3 *= -1;
                z3 = true;
            }
            this.f5362b = i3;
        }
        if (z3) {
            this.f5365e.c(solverVariable, 1.0f);
            this.f5365e.c(solverVariable2, -1.0f);
        } else {
            this.f5365e.c(solverVariable, -1.0f);
            this.f5365e.c(solverVariable2, 1.0f);
        }
        return this;
    }

    public b o(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i3) {
        boolean z3 = false;
        if (i3 != 0) {
            if (i3 < 0) {
                i3 *= -1;
                z3 = true;
            }
            this.f5362b = i3;
        }
        if (z3) {
            this.f5365e.c(solverVariable, 1.0f);
            this.f5365e.c(solverVariable2, -1.0f);
            this.f5365e.c(solverVariable3, -1.0f);
        } else {
            this.f5365e.c(solverVariable, -1.0f);
            this.f5365e.c(solverVariable2, 1.0f);
            this.f5365e.c(solverVariable3, 1.0f);
        }
        return this;
    }

    public b p(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i3) {
        boolean z3 = false;
        if (i3 != 0) {
            if (i3 < 0) {
                i3 *= -1;
                z3 = true;
            }
            this.f5362b = i3;
        }
        if (z3) {
            this.f5365e.c(solverVariable, 1.0f);
            this.f5365e.c(solverVariable2, -1.0f);
            this.f5365e.c(solverVariable3, 1.0f);
        } else {
            this.f5365e.c(solverVariable, -1.0f);
            this.f5365e.c(solverVariable2, 1.0f);
            this.f5365e.c(solverVariable3, -1.0f);
        }
        return this;
    }

    public b q(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f3) {
        this.f5365e.c(solverVariable3, 0.5f);
        this.f5365e.c(solverVariable4, 0.5f);
        this.f5365e.c(solverVariable, -0.5f);
        this.f5365e.c(solverVariable2, -0.5f);
        this.f5362b = -f3;
        return this;
    }

    public void r() {
        float f3 = this.f5362b;
        if (f3 < 0.0f) {
            this.f5362b = f3 * (-1.0f);
            this.f5365e.j();
        }
    }

    public boolean s() {
        SolverVariable solverVariable = this.f5361a;
        return solverVariable != null && (solverVariable.f820k == SolverVariable.Type.UNRESTRICTED || this.f5362b >= 0.0f);
    }

    public boolean t(SolverVariable solverVariable) {
        return this.f5365e.h(solverVariable);
    }

    public String toString() {
        return A();
    }

    public boolean u() {
        return this.f5361a == null && this.f5362b == 0.0f && this.f5365e.k() == 0;
    }

    public final boolean v(SolverVariable solverVariable, d dVar) {
        return solverVariable.f823n <= 1;
    }

    public SolverVariable w(SolverVariable solverVariable) {
        return x(null, solverVariable);
    }

    public final SolverVariable x(boolean[] zArr, SolverVariable solverVariable) {
        SolverVariable.Type type;
        int iK = this.f5365e.k();
        SolverVariable solverVariable2 = null;
        float f3 = 0.0f;
        for (int i3 = 0; i3 < iK; i3++) {
            float fA = this.f5365e.a(i3);
            if (fA < 0.0f) {
                SolverVariable solverVariableF = this.f5365e.f(i3);
                if ((zArr == null || !zArr[solverVariableF.f813d]) && solverVariableF != solverVariable && (((type = solverVariableF.f820k) == SolverVariable.Type.SLACK || type == SolverVariable.Type.ERROR) && fA < f3)) {
                    f3 = fA;
                    solverVariable2 = solverVariableF;
                }
            }
        }
        return solverVariable2;
    }

    public void y(SolverVariable solverVariable) {
        SolverVariable solverVariable2 = this.f5361a;
        if (solverVariable2 != null) {
            this.f5365e.c(solverVariable2, -1.0f);
            this.f5361a = null;
        }
        float fD = this.f5365e.d(solverVariable, true) * (-1.0f);
        this.f5361a = solverVariable;
        if (fD == 1.0f) {
            return;
        }
        this.f5362b /= fD;
        this.f5365e.i(fD);
    }

    public void z() {
        this.f5361a = null;
        this.f5365e.clear();
        this.f5362b = 0.0f;
        this.f5366f = false;
    }

    public b(c cVar) {
        this.f5365e = new v.a(this, cVar);
    }
}
