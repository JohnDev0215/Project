package y;

import androidx.constraintlayout.solver.SolverVariable;
import java.util.ArrayList;
import y.d;

/* JADX INFO: compiled from: ArrayRow.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements d.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f9110e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SolverVariable f9106a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f9107b = 0.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f9108c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList<SolverVariable> f9109d = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f9111f = false;

    /* JADX INFO: compiled from: ArrayRow.java */
    public interface a {
        float a(int i8);

        void b(SolverVariable solverVariable, float f8, boolean z7);

        float c(SolverVariable solverVariable);

        void clear();

        boolean d(SolverVariable solverVariable);

        int e();

        float f(b bVar, boolean z7);

        void g(SolverVariable solverVariable, float f8);

        float h(SolverVariable solverVariable, boolean z7);

        SolverVariable i(int i8);

        void j(float f8);

        void k();
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
        throw new UnsupportedOperationException("Method not decompiled: y.b.A():java.lang.String");
    }

    public void B(d dVar, SolverVariable solverVariable, boolean z7) {
        if (solverVariable.f908h) {
            this.f9107b += solverVariable.f907g * this.f9110e.c(solverVariable);
            this.f9110e.h(solverVariable, z7);
            if (z7) {
                solverVariable.c(this);
            }
        }
    }

    public void C(b bVar, boolean z7) {
        this.f9107b += bVar.f9107b * this.f9110e.f(bVar, z7);
        if (z7) {
            bVar.f9106a.c(this);
        }
    }

    public void D(d dVar) {
        if (dVar.f9126k.length == 0) {
            return;
        }
        boolean z7 = false;
        while (!z7) {
            int iE = this.f9110e.e();
            for (int i8 = 0; i8 < iE; i8++) {
                SolverVariable solverVariableI = this.f9110e.i(i8);
                if (solverVariableI.f905e != -1 || solverVariableI.f908h) {
                    this.f9109d.add(solverVariableI);
                }
            }
            if (this.f9109d.size() > 0) {
                for (SolverVariable solverVariable : this.f9109d) {
                    if (solverVariable.f908h) {
                        B(dVar, solverVariable, true);
                    } else {
                        C(dVar.f9126k[solverVariable.f905e], true);
                    }
                }
                this.f9109d.clear();
            } else {
                z7 = true;
            }
        }
    }

    @Override // y.d.a
    public void a(d.a aVar) {
        if (aVar instanceof b) {
            b bVar = (b) aVar;
            this.f9106a = null;
            this.f9110e.clear();
            for (int i8 = 0; i8 < bVar.f9110e.e(); i8++) {
                this.f9110e.b(bVar.f9110e.i(i8), bVar.f9110e.a(i8), true);
            }
        }
    }

    @Override // y.d.a
    public void b(SolverVariable solverVariable) {
        int i8 = solverVariable.f906f;
        float f8 = 1.0f;
        if (i8 != 1) {
            if (i8 == 2) {
                f8 = 1000.0f;
            } else if (i8 == 3) {
                f8 = 1000000.0f;
            } else if (i8 == 4) {
                f8 = 1.0E9f;
            } else if (i8 == 5) {
                f8 = 1.0E12f;
            }
        }
        this.f9110e.g(solverVariable, f8);
    }

    @Override // y.d.a
    public SolverVariable c(d dVar, boolean[] zArr) {
        return x(zArr, null);
    }

    @Override // y.d.a
    public void clear() {
        this.f9110e.clear();
        this.f9106a = null;
        this.f9107b = 0.0f;
    }

    public b d(d dVar, int i8) {
        this.f9110e.g(dVar.o(i8, "ep"), 1.0f);
        this.f9110e.g(dVar.o(i8, "em"), -1.0f);
        return this;
    }

    public b e(SolverVariable solverVariable, int i8) {
        this.f9110e.g(solverVariable, i8);
        return this;
    }

    public boolean f(d dVar) {
        boolean z7;
        SolverVariable solverVariableG = g(dVar);
        if (solverVariableG == null) {
            z7 = true;
        } else {
            y(solverVariableG);
            z7 = false;
        }
        if (this.f9110e.e() == 0) {
            this.f9111f = true;
        }
        return z7;
    }

    public SolverVariable g(d dVar) {
        int iE = this.f9110e.e();
        SolverVariable solverVariable = null;
        boolean z7 = false;
        boolean z8 = false;
        float f8 = 0.0f;
        float f9 = 0.0f;
        SolverVariable solverVariable2 = null;
        for (int i8 = 0; i8 < iE; i8++) {
            float fA = this.f9110e.a(i8);
            SolverVariable solverVariableI = this.f9110e.i(i8);
            if (solverVariableI.f911k == SolverVariable.Type.UNRESTRICTED) {
                if (solverVariable == null || f8 > fA) {
                    boolean zV = v(solverVariableI, dVar);
                    z7 = zV;
                    f8 = fA;
                    solverVariable = solverVariableI;
                } else if (!z7 && v(solverVariableI, dVar)) {
                    f8 = fA;
                    solverVariable = solverVariableI;
                    z7 = true;
                }
            } else if (solverVariable == null && fA < 0.0f) {
                if (solverVariable2 == null || f9 > fA) {
                    boolean zV2 = v(solverVariableI, dVar);
                    z8 = zV2;
                    f9 = fA;
                    solverVariable2 = solverVariableI;
                } else if (!z8 && v(solverVariableI, dVar)) {
                    f9 = fA;
                    solverVariable2 = solverVariableI;
                    z8 = true;
                }
            }
        }
        return solverVariable != null ? solverVariable : solverVariable2;
    }

    @Override // y.d.a
    public SolverVariable getKey() {
        return this.f9106a;
    }

    public b h(SolverVariable solverVariable, SolverVariable solverVariable2, int i8, float f8, SolverVariable solverVariable3, SolverVariable solverVariable4, int i9) {
        if (solverVariable2 == solverVariable3) {
            this.f9110e.g(solverVariable, 1.0f);
            this.f9110e.g(solverVariable4, 1.0f);
            this.f9110e.g(solverVariable2, -2.0f);
            return this;
        }
        if (f8 == 0.5f) {
            this.f9110e.g(solverVariable, 1.0f);
            this.f9110e.g(solverVariable2, -1.0f);
            this.f9110e.g(solverVariable3, -1.0f);
            this.f9110e.g(solverVariable4, 1.0f);
            if (i8 > 0 || i9 > 0) {
                this.f9107b = (-i8) + i9;
            }
        } else if (f8 <= 0.0f) {
            this.f9110e.g(solverVariable, -1.0f);
            this.f9110e.g(solverVariable2, 1.0f);
            this.f9107b = i8;
        } else if (f8 >= 1.0f) {
            this.f9110e.g(solverVariable4, -1.0f);
            this.f9110e.g(solverVariable3, 1.0f);
            this.f9107b = -i9;
        } else {
            float f9 = 1.0f - f8;
            this.f9110e.g(solverVariable, f9 * 1.0f);
            this.f9110e.g(solverVariable2, f9 * (-1.0f));
            this.f9110e.g(solverVariable3, (-1.0f) * f8);
            this.f9110e.g(solverVariable4, 1.0f * f8);
            if (i8 > 0 || i9 > 0) {
                this.f9107b = ((-i8) * f9) + (i9 * f8);
            }
        }
        return this;
    }

    public b i(SolverVariable solverVariable, int i8) {
        this.f9106a = solverVariable;
        float f8 = i8;
        solverVariable.f907g = f8;
        this.f9107b = f8;
        this.f9111f = true;
        return this;
    }

    public b j(SolverVariable solverVariable, SolverVariable solverVariable2, float f8) {
        this.f9110e.g(solverVariable, -1.0f);
        this.f9110e.g(solverVariable2, f8);
        return this;
    }

    public b k(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f8) {
        this.f9110e.g(solverVariable, -1.0f);
        this.f9110e.g(solverVariable2, 1.0f);
        this.f9110e.g(solverVariable3, f8);
        this.f9110e.g(solverVariable4, -f8);
        return this;
    }

    public b l(float f8, float f9, float f10, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4) {
        this.f9107b = 0.0f;
        if (f9 == 0.0f || f8 == f10) {
            this.f9110e.g(solverVariable, 1.0f);
            this.f9110e.g(solverVariable2, -1.0f);
            this.f9110e.g(solverVariable4, 1.0f);
            this.f9110e.g(solverVariable3, -1.0f);
        } else if (f8 == 0.0f) {
            this.f9110e.g(solverVariable, 1.0f);
            this.f9110e.g(solverVariable2, -1.0f);
        } else if (f10 == 0.0f) {
            this.f9110e.g(solverVariable3, 1.0f);
            this.f9110e.g(solverVariable4, -1.0f);
        } else {
            float f11 = (f8 / f9) / (f10 / f9);
            this.f9110e.g(solverVariable, 1.0f);
            this.f9110e.g(solverVariable2, -1.0f);
            this.f9110e.g(solverVariable4, f11);
            this.f9110e.g(solverVariable3, -f11);
        }
        return this;
    }

    public b m(SolverVariable solverVariable, int i8) {
        if (i8 < 0) {
            this.f9107b = i8 * (-1);
            this.f9110e.g(solverVariable, 1.0f);
        } else {
            this.f9107b = i8;
            this.f9110e.g(solverVariable, -1.0f);
        }
        return this;
    }

    public b n(SolverVariable solverVariable, SolverVariable solverVariable2, int i8) {
        boolean z7 = false;
        if (i8 != 0) {
            if (i8 < 0) {
                i8 *= -1;
                z7 = true;
            }
            this.f9107b = i8;
        }
        if (z7) {
            this.f9110e.g(solverVariable, 1.0f);
            this.f9110e.g(solverVariable2, -1.0f);
        } else {
            this.f9110e.g(solverVariable, -1.0f);
            this.f9110e.g(solverVariable2, 1.0f);
        }
        return this;
    }

    public b o(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i8) {
        boolean z7 = false;
        if (i8 != 0) {
            if (i8 < 0) {
                i8 *= -1;
                z7 = true;
            }
            this.f9107b = i8;
        }
        if (z7) {
            this.f9110e.g(solverVariable, 1.0f);
            this.f9110e.g(solverVariable2, -1.0f);
            this.f9110e.g(solverVariable3, -1.0f);
        } else {
            this.f9110e.g(solverVariable, -1.0f);
            this.f9110e.g(solverVariable2, 1.0f);
            this.f9110e.g(solverVariable3, 1.0f);
        }
        return this;
    }

    public b p(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i8) {
        boolean z7 = false;
        if (i8 != 0) {
            if (i8 < 0) {
                i8 *= -1;
                z7 = true;
            }
            this.f9107b = i8;
        }
        if (z7) {
            this.f9110e.g(solverVariable, 1.0f);
            this.f9110e.g(solverVariable2, -1.0f);
            this.f9110e.g(solverVariable3, 1.0f);
        } else {
            this.f9110e.g(solverVariable, -1.0f);
            this.f9110e.g(solverVariable2, 1.0f);
            this.f9110e.g(solverVariable3, -1.0f);
        }
        return this;
    }

    public b q(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f8) {
        this.f9110e.g(solverVariable3, 0.5f);
        this.f9110e.g(solverVariable4, 0.5f);
        this.f9110e.g(solverVariable, -0.5f);
        this.f9110e.g(solverVariable2, -0.5f);
        this.f9107b = -f8;
        return this;
    }

    public void r() {
        float f8 = this.f9107b;
        if (f8 < 0.0f) {
            this.f9107b = f8 * (-1.0f);
            this.f9110e.k();
        }
    }

    public boolean s() {
        SolverVariable solverVariable = this.f9106a;
        return solverVariable != null && (solverVariable.f911k == SolverVariable.Type.UNRESTRICTED || this.f9107b >= 0.0f);
    }

    public boolean t(SolverVariable solverVariable) {
        return this.f9110e.d(solverVariable);
    }

    public String toString() {
        return A();
    }

    public boolean u() {
        return this.f9106a == null && this.f9107b == 0.0f && this.f9110e.e() == 0;
    }

    public final boolean v(SolverVariable solverVariable, d dVar) {
        return solverVariable.f914n <= 1;
    }

    public SolverVariable w(SolverVariable solverVariable) {
        return x(null, solverVariable);
    }

    public final SolverVariable x(boolean[] zArr, SolverVariable solverVariable) {
        SolverVariable.Type type;
        int iE = this.f9110e.e();
        SolverVariable solverVariable2 = null;
        float f8 = 0.0f;
        for (int i8 = 0; i8 < iE; i8++) {
            float fA = this.f9110e.a(i8);
            if (fA < 0.0f) {
                SolverVariable solverVariableI = this.f9110e.i(i8);
                if ((zArr == null || !zArr[solverVariableI.f904d]) && solverVariableI != solverVariable && (((type = solverVariableI.f911k) == SolverVariable.Type.SLACK || type == SolverVariable.Type.ERROR) && fA < f8)) {
                    f8 = fA;
                    solverVariable2 = solverVariableI;
                }
            }
        }
        return solverVariable2;
    }

    public void y(SolverVariable solverVariable) {
        SolverVariable solverVariable2 = this.f9106a;
        if (solverVariable2 != null) {
            this.f9110e.g(solverVariable2, -1.0f);
            this.f9106a = null;
        }
        float fH = this.f9110e.h(solverVariable, true) * (-1.0f);
        this.f9106a = solverVariable;
        if (fH == 1.0f) {
            return;
        }
        this.f9107b /= fH;
        this.f9110e.j(fH);
    }

    public void z() {
        this.f9106a = null;
        this.f9110e.clear();
        this.f9107b = 0.0f;
        this.f9111f = false;
    }

    public b(c cVar) {
        this.f9110e = new y.a(this, cVar);
    }
}
