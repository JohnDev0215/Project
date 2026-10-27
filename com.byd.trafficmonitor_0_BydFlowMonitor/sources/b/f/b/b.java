package b.f.b;

import androidx.constraintlayout.solver.SolverVariable;
import b.f.b.d;
import java.util.ArrayList;

/* JADX INFO: compiled from: ArrayRow.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements d.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f2180e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SolverVariable f2176a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f2177b = 0.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2178c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList<SolverVariable> f2179d = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2181f = false;

    /* JADX INFO: compiled from: ArrayRow.java */
    public interface a {
        float a(int i2);

        float b(b bVar, boolean z);

        void c(SolverVariable solverVariable, float f2);

        void clear();

        float d(SolverVariable solverVariable, boolean z);

        void e(SolverVariable solverVariable, float f2, boolean z);

        SolverVariable f(int i2);

        float g(SolverVariable solverVariable);

        boolean h(SolverVariable solverVariable);

        void i(float f2);

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
        throw new UnsupportedOperationException("Method not decompiled: b.f.b.b.A():java.lang.String");
    }

    public void B(d dVar, SolverVariable solverVariable, boolean z) {
        if (solverVariable.f615h) {
            this.f2177b += solverVariable.f614g * this.f2180e.g(solverVariable);
            this.f2180e.d(solverVariable, z);
            if (z) {
                solverVariable.c(this);
            }
        }
    }

    public void C(b bVar, boolean z) {
        this.f2177b += bVar.f2177b * this.f2180e.b(bVar, z);
        if (z) {
            bVar.f2176a.c(this);
        }
    }

    public void D(d dVar) {
        if (dVar.k.length == 0) {
            return;
        }
        boolean z = false;
        while (!z) {
            int iK = this.f2180e.k();
            for (int i2 = 0; i2 < iK; i2++) {
                SolverVariable solverVariableF = this.f2180e.f(i2);
                if (solverVariableF.f612e != -1 || solverVariableF.f615h) {
                    this.f2179d.add(solverVariableF);
                }
            }
            if (this.f2179d.size() > 0) {
                for (SolverVariable solverVariable : this.f2179d) {
                    if (solverVariable.f615h) {
                        B(dVar, solverVariable, true);
                    } else {
                        C(dVar.k[solverVariable.f612e], true);
                    }
                }
                this.f2179d.clear();
            } else {
                z = true;
            }
        }
    }

    @Override // b.f.b.d.a
    public void a(d.a aVar) {
        if (aVar instanceof b) {
            b bVar = (b) aVar;
            this.f2176a = null;
            this.f2180e.clear();
            for (int i2 = 0; i2 < bVar.f2180e.k(); i2++) {
                this.f2180e.e(bVar.f2180e.f(i2), bVar.f2180e.a(i2), true);
            }
        }
    }

    @Override // b.f.b.d.a
    public void b(SolverVariable solverVariable) {
        int i2 = solverVariable.f613f;
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
        this.f2180e.c(solverVariable, f2);
    }

    @Override // b.f.b.d.a
    public SolverVariable c(d dVar, boolean[] zArr) {
        return x(zArr, null);
    }

    @Override // b.f.b.d.a
    public void clear() {
        this.f2180e.clear();
        this.f2176a = null;
        this.f2177b = 0.0f;
    }

    public b d(d dVar, int i2) {
        this.f2180e.c(dVar.o(i2, "ep"), 1.0f);
        this.f2180e.c(dVar.o(i2, "em"), -1.0f);
        return this;
    }

    public b e(SolverVariable solverVariable, int i2) {
        this.f2180e.c(solverVariable, i2);
        return this;
    }

    public boolean f(d dVar) {
        boolean z;
        SolverVariable solverVariableG = g(dVar);
        if (solverVariableG == null) {
            z = true;
        } else {
            y(solverVariableG);
            z = false;
        }
        if (this.f2180e.k() == 0) {
            this.f2181f = true;
        }
        return z;
    }

    public SolverVariable g(d dVar) {
        int iK = this.f2180e.k();
        SolverVariable solverVariable = null;
        boolean z = false;
        boolean z2 = false;
        float f2 = 0.0f;
        float f3 = 0.0f;
        SolverVariable solverVariable2 = null;
        for (int i2 = 0; i2 < iK; i2++) {
            float fA = this.f2180e.a(i2);
            SolverVariable solverVariableF = this.f2180e.f(i2);
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

    @Override // b.f.b.d.a
    public SolverVariable getKey() {
        return this.f2176a;
    }

    public b h(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, float f2, SolverVariable solverVariable3, SolverVariable solverVariable4, int i3) {
        if (solverVariable2 == solverVariable3) {
            this.f2180e.c(solverVariable, 1.0f);
            this.f2180e.c(solverVariable4, 1.0f);
            this.f2180e.c(solverVariable2, -2.0f);
            return this;
        }
        if (f2 == 0.5f) {
            this.f2180e.c(solverVariable, 1.0f);
            this.f2180e.c(solverVariable2, -1.0f);
            this.f2180e.c(solverVariable3, -1.0f);
            this.f2180e.c(solverVariable4, 1.0f);
            if (i2 > 0 || i3 > 0) {
                this.f2177b = (-i2) + i3;
            }
        } else if (f2 <= 0.0f) {
            this.f2180e.c(solverVariable, -1.0f);
            this.f2180e.c(solverVariable2, 1.0f);
            this.f2177b = i2;
        } else if (f2 >= 1.0f) {
            this.f2180e.c(solverVariable4, -1.0f);
            this.f2180e.c(solverVariable3, 1.0f);
            this.f2177b = -i3;
        } else {
            float f3 = 1.0f - f2;
            this.f2180e.c(solverVariable, f3 * 1.0f);
            this.f2180e.c(solverVariable2, f3 * (-1.0f));
            this.f2180e.c(solverVariable3, (-1.0f) * f2);
            this.f2180e.c(solverVariable4, 1.0f * f2);
            if (i2 > 0 || i3 > 0) {
                this.f2177b = ((-i2) * f3) + (i3 * f2);
            }
        }
        return this;
    }

    public b i(SolverVariable solverVariable, int i2) {
        this.f2176a = solverVariable;
        float f2 = i2;
        solverVariable.f614g = f2;
        this.f2177b = f2;
        this.f2181f = true;
        return this;
    }

    public b j(SolverVariable solverVariable, SolverVariable solverVariable2, float f2) {
        this.f2180e.c(solverVariable, -1.0f);
        this.f2180e.c(solverVariable2, f2);
        return this;
    }

    public b k(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f2) {
        this.f2180e.c(solverVariable, -1.0f);
        this.f2180e.c(solverVariable2, 1.0f);
        this.f2180e.c(solverVariable3, f2);
        this.f2180e.c(solverVariable4, -f2);
        return this;
    }

    public b l(float f2, float f3, float f4, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4) {
        this.f2177b = 0.0f;
        if (f3 == 0.0f || f2 == f4) {
            this.f2180e.c(solverVariable, 1.0f);
            this.f2180e.c(solverVariable2, -1.0f);
            this.f2180e.c(solverVariable4, 1.0f);
            this.f2180e.c(solverVariable3, -1.0f);
        } else if (f2 == 0.0f) {
            this.f2180e.c(solverVariable, 1.0f);
            this.f2180e.c(solverVariable2, -1.0f);
        } else if (f4 == 0.0f) {
            this.f2180e.c(solverVariable3, 1.0f);
            this.f2180e.c(solverVariable4, -1.0f);
        } else {
            float f5 = (f2 / f3) / (f4 / f3);
            this.f2180e.c(solverVariable, 1.0f);
            this.f2180e.c(solverVariable2, -1.0f);
            this.f2180e.c(solverVariable4, f5);
            this.f2180e.c(solverVariable3, -f5);
        }
        return this;
    }

    public b m(SolverVariable solverVariable, int i2) {
        if (i2 < 0) {
            this.f2177b = i2 * (-1);
            this.f2180e.c(solverVariable, 1.0f);
        } else {
            this.f2177b = i2;
            this.f2180e.c(solverVariable, -1.0f);
        }
        return this;
    }

    public b n(SolverVariable solverVariable, SolverVariable solverVariable2, int i2) {
        boolean z = false;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z = true;
            }
            this.f2177b = i2;
        }
        if (z) {
            this.f2180e.c(solverVariable, 1.0f);
            this.f2180e.c(solverVariable2, -1.0f);
        } else {
            this.f2180e.c(solverVariable, -1.0f);
            this.f2180e.c(solverVariable2, 1.0f);
        }
        return this;
    }

    public b o(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i2) {
        boolean z = false;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z = true;
            }
            this.f2177b = i2;
        }
        if (z) {
            this.f2180e.c(solverVariable, 1.0f);
            this.f2180e.c(solverVariable2, -1.0f);
            this.f2180e.c(solverVariable3, -1.0f);
        } else {
            this.f2180e.c(solverVariable, -1.0f);
            this.f2180e.c(solverVariable2, 1.0f);
            this.f2180e.c(solverVariable3, 1.0f);
        }
        return this;
    }

    public b p(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i2) {
        boolean z = false;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z = true;
            }
            this.f2177b = i2;
        }
        if (z) {
            this.f2180e.c(solverVariable, 1.0f);
            this.f2180e.c(solverVariable2, -1.0f);
            this.f2180e.c(solverVariable3, 1.0f);
        } else {
            this.f2180e.c(solverVariable, -1.0f);
            this.f2180e.c(solverVariable2, 1.0f);
            this.f2180e.c(solverVariable3, -1.0f);
        }
        return this;
    }

    public b q(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f2) {
        this.f2180e.c(solverVariable3, 0.5f);
        this.f2180e.c(solverVariable4, 0.5f);
        this.f2180e.c(solverVariable, -0.5f);
        this.f2180e.c(solverVariable2, -0.5f);
        this.f2177b = -f2;
        return this;
    }

    public void r() {
        float f2 = this.f2177b;
        if (f2 < 0.0f) {
            this.f2177b = f2 * (-1.0f);
            this.f2180e.j();
        }
    }

    public boolean s() {
        SolverVariable solverVariable = this.f2176a;
        return solverVariable != null && (solverVariable.k == SolverVariable.Type.UNRESTRICTED || this.f2177b >= 0.0f);
    }

    public boolean t(SolverVariable solverVariable) {
        return this.f2180e.h(solverVariable);
    }

    public String toString() {
        return A();
    }

    public boolean u() {
        return this.f2176a == null && this.f2177b == 0.0f && this.f2180e.k() == 0;
    }

    public final boolean v(SolverVariable solverVariable, d dVar) {
        return solverVariable.n <= 1;
    }

    public SolverVariable w(SolverVariable solverVariable) {
        return x(null, solverVariable);
    }

    public final SolverVariable x(boolean[] zArr, SolverVariable solverVariable) {
        SolverVariable.Type type;
        int iK = this.f2180e.k();
        SolverVariable solverVariable2 = null;
        float f2 = 0.0f;
        for (int i2 = 0; i2 < iK; i2++) {
            float fA = this.f2180e.a(i2);
            if (fA < 0.0f) {
                SolverVariable solverVariableF = this.f2180e.f(i2);
                if ((zArr == null || !zArr[solverVariableF.f611d]) && solverVariableF != solverVariable && (((type = solverVariableF.k) == SolverVariable.Type.SLACK || type == SolverVariable.Type.ERROR) && fA < f2)) {
                    f2 = fA;
                    solverVariable2 = solverVariableF;
                }
            }
        }
        return solverVariable2;
    }

    public void y(SolverVariable solverVariable) {
        SolverVariable solverVariable2 = this.f2176a;
        if (solverVariable2 != null) {
            this.f2180e.c(solverVariable2, -1.0f);
            this.f2176a = null;
        }
        float fD = this.f2180e.d(solverVariable, true) * (-1.0f);
        this.f2176a = solverVariable;
        if (fD == 1.0f) {
            return;
        }
        this.f2177b /= fD;
        this.f2180e.i(fD);
    }

    public void z() {
        this.f2176a = null;
        this.f2180e.clear();
        this.f2177b = 0.0f;
        this.f2181f = false;
    }

    public b(c cVar) {
        this.f2180e = new b.f.b.a(this, cVar);
    }
}
