package defpackage;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: LinearSystem.java */
/* JADX INFO: loaded from: classes.dex */
public class n5 {
    public static int a = 1000;
    public static o5 b = null;
    public static boolean c = true;
    public static long d;
    public static long e;
    public a h;
    public l5[] k;
    public final m5 r;
    public a u;
    public int f = 0;
    public HashMap<String, SolverVariable> g = null;
    public int i = 32;
    public int j = 32;
    public boolean l = false;
    public boolean m = false;
    public boolean[] n = new boolean[32];
    public int o = 1;
    public int p = 0;
    public int q = 32;
    public SolverVariable[] s = new SolverVariable[a];
    public int t = 0;

    /* JADX INFO: compiled from: LinearSystem.java */
    public interface a {
        void a(a aVar);

        void b(SolverVariable solverVariable);

        SolverVariable c(n5 n5Var, boolean[] zArr);

        void clear();

        SolverVariable getKey();
    }

    /* JADX INFO: compiled from: LinearSystem.java */
    public class b extends l5 {
        public b(m5 m5Var) {
            this.e = new s5(this, m5Var);
        }
    }

    public n5() {
        this.k = null;
        this.k = new l5[32];
        C();
        m5 m5Var = new m5();
        this.r = m5Var;
        this.h = new r5(m5Var);
        if (c) {
            this.u = new b(m5Var);
        } else {
            this.u = new l5(m5Var);
        }
    }

    public static l5 s(n5 n5Var, SolverVariable solverVariable, SolverVariable solverVariable2, float f) {
        return n5Var.r().j(solverVariable, solverVariable2, f);
    }

    public static o5 w() {
        return b;
    }

    public void A(a aVar) throws Exception {
        if (b != null) {
            throw null;
        }
        u(aVar);
        B(aVar, false);
        n();
    }

    public final int B(a aVar, boolean z) {
        if (b != null) {
            throw null;
        }
        for (int i = 0; i < this.o; i++) {
            this.n[i] = false;
        }
        boolean z2 = false;
        int i2 = 0;
        while (!z2) {
            if (b != null) {
                throw null;
            }
            i2++;
            if (i2 >= this.o * 2) {
                return i2;
            }
            if (aVar.getKey() != null) {
                this.n[aVar.getKey().d] = true;
            }
            SolverVariable solverVariableC = aVar.c(this, this.n);
            if (solverVariableC != null) {
                boolean[] zArr = this.n;
                int i3 = solverVariableC.d;
                if (zArr[i3]) {
                    return i2;
                }
                zArr[i3] = true;
            }
            if (solverVariableC != null) {
                float f = Float.MAX_VALUE;
                int i4 = -1;
                for (int i5 = 0; i5 < this.p; i5++) {
                    l5 l5Var = this.k[i5];
                    if (l5Var.a.k != SolverVariable.Type.UNRESTRICTED && !l5Var.f && l5Var.t(solverVariableC)) {
                        float fG = l5Var.e.g(solverVariableC);
                        if (fG < 0.0f) {
                            float f2 = (-l5Var.b) / fG;
                            if (f2 < f) {
                                i4 = i5;
                                f = f2;
                            }
                        }
                    }
                }
                if (i4 > -1) {
                    l5 l5Var2 = this.k[i4];
                    l5Var2.a.e = -1;
                    if (b != null) {
                        throw null;
                    }
                    l5Var2.y(solverVariableC);
                    SolverVariable solverVariable = l5Var2.a;
                    solverVariable.e = i4;
                    solverVariable.g(l5Var2);
                } else {
                    continue;
                }
            } else {
                z2 = true;
            }
        }
        return i2;
    }

    public final void C() {
        int i = 0;
        if (c) {
            while (true) {
                l5[] l5VarArr = this.k;
                if (i >= l5VarArr.length) {
                    return;
                }
                l5 l5Var = l5VarArr[i];
                if (l5Var != null) {
                    this.r.a.a(l5Var);
                }
                this.k[i] = null;
                i++;
            }
        } else {
            while (true) {
                l5[] l5VarArr2 = this.k;
                if (i >= l5VarArr2.length) {
                    return;
                }
                l5 l5Var2 = l5VarArr2[i];
                if (l5Var2 != null) {
                    this.r.b.a(l5Var2);
                }
                this.k[i] = null;
                i++;
            }
        }
    }

    public void D() {
        m5 m5Var;
        int i = 0;
        while (true) {
            m5Var = this.r;
            SolverVariable[] solverVariableArr = m5Var.d;
            if (i >= solverVariableArr.length) {
                break;
            }
            SolverVariable solverVariable = solverVariableArr[i];
            if (solverVariable != null) {
                solverVariable.d();
            }
            i++;
        }
        m5Var.c.c(this.s, this.t);
        this.t = 0;
        Arrays.fill(this.r.d, (Object) null);
        HashMap<String, SolverVariable> map = this.g;
        if (map != null) {
            map.clear();
        }
        this.f = 0;
        this.h.clear();
        this.o = 1;
        for (int i2 = 0; i2 < this.p; i2++) {
            this.k[i2].c = false;
        }
        C();
        this.p = 0;
        if (c) {
            this.u = new b(this.r);
        } else {
            this.u = new l5(this.r);
        }
    }

    public final SolverVariable a(SolverVariable.Type type, String str) {
        SolverVariable solverVariableB = this.r.c.b();
        if (solverVariableB == null) {
            solverVariableB = new SolverVariable(type, str);
            solverVariableB.f(type, str);
        } else {
            solverVariableB.d();
            solverVariableB.f(type, str);
        }
        int i = this.t;
        int i2 = a;
        if (i >= i2) {
            int i3 = i2 * 2;
            a = i3;
            this.s = (SolverVariable[]) Arrays.copyOf(this.s, i3);
        }
        SolverVariable[] solverVariableArr = this.s;
        int i4 = this.t;
        this.t = i4 + 1;
        solverVariableArr[i4] = solverVariableB;
        return solverVariableB;
    }

    public void b(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f, int i) {
        ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
        SolverVariable solverVariableQ = q(constraintWidget.n(type));
        ConstraintAnchor.Type type2 = ConstraintAnchor.Type.TOP;
        SolverVariable solverVariableQ2 = q(constraintWidget.n(type2));
        ConstraintAnchor.Type type3 = ConstraintAnchor.Type.RIGHT;
        SolverVariable solverVariableQ3 = q(constraintWidget.n(type3));
        ConstraintAnchor.Type type4 = ConstraintAnchor.Type.BOTTOM;
        SolverVariable solverVariableQ4 = q(constraintWidget.n(type4));
        SolverVariable solverVariableQ5 = q(constraintWidget2.n(type));
        SolverVariable solverVariableQ6 = q(constraintWidget2.n(type2));
        SolverVariable solverVariableQ7 = q(constraintWidget2.n(type3));
        SolverVariable solverVariableQ8 = q(constraintWidget2.n(type4));
        l5 l5VarR = r();
        double d2 = f;
        double d3 = i;
        l5VarR.q(solverVariableQ2, solverVariableQ4, solverVariableQ6, solverVariableQ8, (float) (Math.sin(d2) * d3));
        d(l5VarR);
        l5 l5VarR2 = r();
        l5VarR2.q(solverVariableQ, solverVariableQ3, solverVariableQ5, solverVariableQ7, (float) (Math.cos(d2) * d3));
        d(l5VarR2);
    }

    public void c(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2, int i3) {
        l5 l5VarR = r();
        l5VarR.h(solverVariable, solverVariable2, i, f, solverVariable3, solverVariable4, i2);
        if (i3 != 8) {
            l5VarR.d(this, i3);
        }
        d(l5VarR);
    }

    public void d(l5 l5Var) {
        SolverVariable solverVariableW;
        if (l5Var == null) {
            return;
        }
        if (b != null) {
            throw null;
        }
        boolean z = true;
        if (this.p + 1 >= this.q || this.o + 1 >= this.j) {
            y();
        }
        boolean z2 = false;
        if (!l5Var.f) {
            l5Var.D(this);
            if (l5Var.u()) {
                return;
            }
            l5Var.r();
            if (l5Var.f(this)) {
                SolverVariable solverVariableP = p();
                l5Var.a = solverVariableP;
                l(l5Var);
                this.u.a(l5Var);
                B(this.u, true);
                if (solverVariableP.e == -1) {
                    if (l5Var.a == solverVariableP && (solverVariableW = l5Var.w(solverVariableP)) != null) {
                        if (b != null) {
                            throw null;
                        }
                        l5Var.y(solverVariableW);
                    }
                    if (!l5Var.f) {
                        l5Var.a.g(l5Var);
                    }
                    this.p--;
                }
            } else {
                z = false;
            }
            if (!l5Var.s()) {
                return;
            } else {
                z2 = z;
            }
        }
        if (z2) {
            return;
        }
        l(l5Var);
    }

    public l5 e(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        if (i2 == 8 && solverVariable2.h && solverVariable.e == -1) {
            solverVariable.e(this, solverVariable2.g + i);
            return null;
        }
        l5 l5VarR = r();
        l5VarR.n(solverVariable, solverVariable2, i);
        if (i2 != 8) {
            l5VarR.d(this, i2);
        }
        d(l5VarR);
        return l5VarR;
    }

    public void f(SolverVariable solverVariable, int i) {
        int i2 = solverVariable.e;
        if (i2 == -1) {
            solverVariable.e(this, i);
            return;
        }
        if (i2 == -1) {
            l5 l5VarR = r();
            l5VarR.i(solverVariable, i);
            d(l5VarR);
            return;
        }
        l5 l5Var = this.k[i2];
        if (l5Var.f) {
            l5Var.b = i;
            return;
        }
        if (l5Var.e.k() == 0) {
            l5Var.f = true;
            l5Var.b = i;
        } else {
            l5 l5VarR2 = r();
            l5VarR2.m(solverVariable, i);
            d(l5VarR2);
        }
    }

    public void g(SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        l5 l5VarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f = 0;
        l5VarR.o(solverVariable, solverVariable2, solverVariableT, i);
        d(l5VarR);
    }

    public void h(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        l5 l5VarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f = 0;
        l5VarR.o(solverVariable, solverVariable2, solverVariableT, i);
        if (i2 != 8) {
            m(l5VarR, (int) (l5VarR.e.g(solverVariableT) * (-1.0f)), i2);
        }
        d(l5VarR);
    }

    public void i(SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        l5 l5VarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f = 0;
        l5VarR.p(solverVariable, solverVariable2, solverVariableT, i);
        d(l5VarR);
    }

    public void j(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        l5 l5VarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f = 0;
        l5VarR.p(solverVariable, solverVariable2, solverVariableT, i);
        if (i2 != 8) {
            m(l5VarR, (int) (l5VarR.e.g(solverVariableT) * (-1.0f)), i2);
        }
        d(l5VarR);
    }

    public void k(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f, int i) {
        l5 l5VarR = r();
        l5VarR.k(solverVariable, solverVariable2, solverVariable3, solverVariable4, f);
        if (i != 8) {
            l5VarR.d(this, i);
        }
        d(l5VarR);
    }

    public final void l(l5 l5Var) {
        if (c) {
            l5[] l5VarArr = this.k;
            int i = this.p;
            if (l5VarArr[i] != null) {
                this.r.a.a(l5VarArr[i]);
            }
        } else {
            l5[] l5VarArr2 = this.k;
            int i2 = this.p;
            if (l5VarArr2[i2] != null) {
                this.r.b.a(l5VarArr2[i2]);
            }
        }
        l5[] l5VarArr3 = this.k;
        int i3 = this.p;
        l5VarArr3[i3] = l5Var;
        SolverVariable solverVariable = l5Var.a;
        solverVariable.e = i3;
        this.p = i3 + 1;
        solverVariable.g(l5Var);
    }

    public void m(l5 l5Var, int i, int i2) {
        l5Var.e(o(i2, null), i);
    }

    public final void n() {
        for (int i = 0; i < this.p; i++) {
            l5 l5Var = this.k[i];
            l5Var.a.g = l5Var.b;
        }
    }

    public SolverVariable o(int i, String str) {
        if (b != null) {
            throw null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.ERROR, str);
        int i2 = this.f + 1;
        this.f = i2;
        this.o++;
        solverVariableA.d = i2;
        solverVariableA.f = i;
        this.r.d[i2] = solverVariableA;
        this.h.b(solverVariableA);
        return solverVariableA;
    }

    public SolverVariable p() {
        if (b != null) {
            throw null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.SLACK, null);
        int i = this.f + 1;
        this.f = i;
        this.o++;
        solverVariableA.d = i;
        this.r.d[i] = solverVariableA;
        return solverVariableA;
    }

    public SolverVariable q(Object obj) {
        SolverVariable solverVariableF = null;
        if (obj == null) {
            return null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        if (obj instanceof ConstraintAnchor) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
            solverVariableF = constraintAnchor.f();
            if (solverVariableF == null) {
                constraintAnchor.m(this.r);
                solverVariableF = constraintAnchor.f();
            }
            int i = solverVariableF.d;
            if (i == -1 || i > this.f || this.r.d[i] == null) {
                if (i != -1) {
                    solverVariableF.d();
                }
                int i2 = this.f + 1;
                this.f = i2;
                this.o++;
                solverVariableF.d = i2;
                solverVariableF.k = SolverVariable.Type.UNRESTRICTED;
                this.r.d[i2] = solverVariableF;
            }
        }
        return solverVariableF;
    }

    public l5 r() {
        l5 l5VarB;
        if (c) {
            l5VarB = this.r.a.b();
            if (l5VarB == null) {
                l5VarB = new b(this.r);
                e++;
            } else {
                l5VarB.z();
            }
        } else {
            l5VarB = this.r.b.b();
            if (l5VarB == null) {
                l5VarB = new l5(this.r);
                d++;
            } else {
                l5VarB.z();
            }
        }
        SolverVariable.b();
        return l5VarB;
    }

    public SolverVariable t() {
        if (b != null) {
            throw null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.SLACK, null);
        int i = this.f + 1;
        this.f = i;
        this.o++;
        solverVariableA.d = i;
        this.r.d[i] = solverVariableA;
        return solverVariableA;
    }

    public final int u(a aVar) throws Exception {
        float f;
        boolean z;
        int i = 0;
        while (true) {
            f = 0.0f;
            if (i >= this.p) {
                z = false;
                break;
            }
            l5[] l5VarArr = this.k;
            if (l5VarArr[i].a.k != SolverVariable.Type.UNRESTRICTED && l5VarArr[i].b < 0.0f) {
                z = true;
                break;
            }
            i++;
        }
        if (!z) {
            return 0;
        }
        boolean z2 = false;
        int i2 = 0;
        while (!z2) {
            if (b != null) {
                throw null;
            }
            i2++;
            float f2 = Float.MAX_VALUE;
            int i3 = -1;
            int i4 = -1;
            int i5 = 0;
            int i6 = 0;
            while (i5 < this.p) {
                l5 l5Var = this.k[i5];
                if (l5Var.a.k != SolverVariable.Type.UNRESTRICTED && !l5Var.f && l5Var.b < f) {
                    int i7 = 1;
                    while (i7 < this.o) {
                        SolverVariable solverVariable = this.r.d[i7];
                        float fG = l5Var.e.g(solverVariable);
                        if (fG > f) {
                            for (int i8 = 0; i8 < 9; i8++) {
                                float f3 = solverVariable.i[i8] / fG;
                                if ((f3 < f2 && i8 == i6) || i8 > i6) {
                                    i6 = i8;
                                    f2 = f3;
                                    i3 = i5;
                                    i4 = i7;
                                }
                            }
                        }
                        i7++;
                        f = 0.0f;
                    }
                }
                i5++;
                f = 0.0f;
            }
            if (i3 != -1) {
                l5 l5Var2 = this.k[i3];
                l5Var2.a.e = -1;
                if (b != null) {
                    throw null;
                }
                l5Var2.y(this.r.d[i4]);
                SolverVariable solverVariable2 = l5Var2.a;
                solverVariable2.e = i3;
                solverVariable2.g(l5Var2);
            } else {
                z2 = true;
            }
            if (i2 > this.o / 2) {
                z2 = true;
            }
            f = 0.0f;
        }
        return i2;
    }

    public m5 v() {
        return this.r;
    }

    public int x(Object obj) {
        SolverVariable solverVariableF = ((ConstraintAnchor) obj).f();
        if (solverVariableF != null) {
            return (int) (solverVariableF.g + 0.5f);
        }
        return 0;
    }

    public final void y() {
        int i = this.i * 2;
        this.i = i;
        this.k = (l5[]) Arrays.copyOf(this.k, i);
        m5 m5Var = this.r;
        m5Var.d = (SolverVariable[]) Arrays.copyOf(m5Var.d, this.i);
        int i2 = this.i;
        this.n = new boolean[i2];
        this.j = i2;
        this.q = i2;
        if (b != null) {
            throw null;
        }
    }

    public void z() throws Exception {
        o5 o5Var = b;
        if (o5Var != null) {
            throw null;
        }
        if (!this.l && !this.m) {
            A(this.h);
            return;
        }
        if (o5Var != null) {
            throw null;
        }
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= this.p) {
                z = true;
                break;
            } else if (!this.k[i].f) {
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            A(this.h);
        } else {
            if (b != null) {
                throw null;
            }
            n();
        }
    }
}
