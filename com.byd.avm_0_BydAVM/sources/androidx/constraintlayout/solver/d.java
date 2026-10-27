package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: LinearSystem.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f636a = 1000;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static e f637b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f638c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static long f639d;
    public static long e;
    private a h;
    androidx.constraintlayout.solver.b[] k;
    final c r;
    private a u;
    int f = 0;
    private HashMap<String, SolverVariable> g = null;
    private int i = 32;
    private int j = 32;
    public boolean l = false;
    public boolean m = false;
    private boolean[] n = new boolean[32];
    int o = 1;
    int p = 0;
    private int q = 32;
    private SolverVariable[] s = new SolverVariable[f636a];
    private int t = 0;

    /* JADX INFO: compiled from: LinearSystem.java */
    interface a {
        void a(a aVar);

        void b(SolverVariable solverVariable);

        SolverVariable c(d dVar, boolean[] zArr);

        void clear();

        SolverVariable getKey();
    }

    /* JADX INFO: compiled from: LinearSystem.java */
    class b extends androidx.constraintlayout.solver.b {
        public b(c cVar) {
            this.e = new i(this, cVar);
        }
    }

    public d() {
        this.k = null;
        this.k = new androidx.constraintlayout.solver.b[32];
        C();
        c cVar = new c();
        this.r = cVar;
        this.h = new h(cVar);
        if (f638c) {
            this.u = new b(cVar);
        } else {
            this.u = new androidx.constraintlayout.solver.b(cVar);
        }
    }

    private final int B(a aVar, boolean z) {
        if (f637b != null) {
            throw null;
        }
        for (int i = 0; i < this.o; i++) {
            this.n[i] = false;
        }
        boolean z2 = false;
        int i2 = 0;
        while (!z2) {
            if (f637b != null) {
                throw null;
            }
            i2++;
            if (i2 >= this.o * 2) {
                return i2;
            }
            if (aVar.getKey() != null) {
                this.n[aVar.getKey().f622d] = true;
            }
            SolverVariable solverVariableC = aVar.c(this, this.n);
            if (solverVariableC != null) {
                boolean[] zArr = this.n;
                int i3 = solverVariableC.f622d;
                if (zArr[i3]) {
                    return i2;
                }
                zArr[i3] = true;
            }
            if (solverVariableC != null) {
                float f = Float.MAX_VALUE;
                int i4 = -1;
                for (int i5 = 0; i5 < this.p; i5++) {
                    androidx.constraintlayout.solver.b bVar = this.k[i5];
                    if (bVar.f628a.k != SolverVariable.Type.UNRESTRICTED && !bVar.f && bVar.t(solverVariableC)) {
                        float fG = bVar.e.g(solverVariableC);
                        if (fG < 0.0f) {
                            float f2 = (-bVar.f629b) / fG;
                            if (f2 < f) {
                                i4 = i5;
                                f = f2;
                            }
                        }
                    }
                }
                if (i4 > -1) {
                    androidx.constraintlayout.solver.b bVar2 = this.k[i4];
                    bVar2.f628a.e = -1;
                    if (f637b != null) {
                        throw null;
                    }
                    bVar2.y(solverVariableC);
                    SolverVariable solverVariable = bVar2.f628a;
                    solverVariable.e = i4;
                    solverVariable.g(bVar2);
                } else {
                    continue;
                }
            } else {
                z2 = true;
            }
        }
        return i2;
    }

    private void C() {
        int i = 0;
        if (f638c) {
            while (true) {
                androidx.constraintlayout.solver.b[] bVarArr = this.k;
                if (i >= bVarArr.length) {
                    return;
                }
                androidx.constraintlayout.solver.b bVar = bVarArr[i];
                if (bVar != null) {
                    this.r.f632a.a(bVar);
                }
                this.k[i] = null;
                i++;
            }
        } else {
            while (true) {
                androidx.constraintlayout.solver.b[] bVarArr2 = this.k;
                if (i >= bVarArr2.length) {
                    return;
                }
                androidx.constraintlayout.solver.b bVar2 = bVarArr2[i];
                if (bVar2 != null) {
                    this.r.f633b.a(bVar2);
                }
                this.k[i] = null;
                i++;
            }
        }
    }

    private SolverVariable a(SolverVariable.Type type, String str) {
        SolverVariable solverVariableB = this.r.f634c.b();
        if (solverVariableB == null) {
            solverVariableB = new SolverVariable(type, str);
            solverVariableB.f(type, str);
        } else {
            solverVariableB.d();
            solverVariableB.f(type, str);
        }
        int i = this.t;
        int i2 = f636a;
        if (i >= i2) {
            int i3 = i2 * 2;
            f636a = i3;
            this.s = (SolverVariable[]) Arrays.copyOf(this.s, i3);
        }
        SolverVariable[] solverVariableArr = this.s;
        int i4 = this.t;
        this.t = i4 + 1;
        solverVariableArr[i4] = solverVariableB;
        return solverVariableB;
    }

    private final void l(androidx.constraintlayout.solver.b bVar) {
        if (f638c) {
            androidx.constraintlayout.solver.b[] bVarArr = this.k;
            int i = this.p;
            if (bVarArr[i] != null) {
                this.r.f632a.a(bVarArr[i]);
            }
        } else {
            androidx.constraintlayout.solver.b[] bVarArr2 = this.k;
            int i2 = this.p;
            if (bVarArr2[i2] != null) {
                this.r.f633b.a(bVarArr2[i2]);
            }
        }
        androidx.constraintlayout.solver.b[] bVarArr3 = this.k;
        int i3 = this.p;
        bVarArr3[i3] = bVar;
        SolverVariable solverVariable = bVar.f628a;
        solverVariable.e = i3;
        this.p = i3 + 1;
        solverVariable.g(bVar);
    }

    private void n() {
        for (int i = 0; i < this.p; i++) {
            androidx.constraintlayout.solver.b bVar = this.k[i];
            bVar.f628a.g = bVar.f629b;
        }
    }

    public static androidx.constraintlayout.solver.b s(d dVar, SolverVariable solverVariable, SolverVariable solverVariable2, float f) {
        return dVar.r().j(solverVariable, solverVariable2, f);
    }

    private int u(a aVar) throws Exception {
        float f;
        boolean z;
        int i = 0;
        while (true) {
            f = 0.0f;
            if (i >= this.p) {
                z = false;
                break;
            }
            androidx.constraintlayout.solver.b[] bVarArr = this.k;
            if (bVarArr[i].f628a.k != SolverVariable.Type.UNRESTRICTED && bVarArr[i].f629b < 0.0f) {
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
            if (f637b != null) {
                throw null;
            }
            i2++;
            float f2 = Float.MAX_VALUE;
            int i3 = -1;
            int i4 = -1;
            int i5 = 0;
            int i6 = 0;
            while (i5 < this.p) {
                androidx.constraintlayout.solver.b bVar = this.k[i5];
                if (bVar.f628a.k != SolverVariable.Type.UNRESTRICTED && !bVar.f && bVar.f629b < f) {
                    int i7 = 1;
                    while (i7 < this.o) {
                        SolverVariable solverVariable = this.r.f635d[i7];
                        float fG = bVar.e.g(solverVariable);
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
                androidx.constraintlayout.solver.b bVar2 = this.k[i3];
                bVar2.f628a.e = -1;
                if (f637b != null) {
                    throw null;
                }
                bVar2.y(this.r.f635d[i4]);
                SolverVariable solverVariable2 = bVar2.f628a;
                solverVariable2.e = i3;
                solverVariable2.g(bVar2);
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

    public static e w() {
        return f637b;
    }

    private void y() {
        int i = this.i * 2;
        this.i = i;
        this.k = (androidx.constraintlayout.solver.b[]) Arrays.copyOf(this.k, i);
        c cVar = this.r;
        cVar.f635d = (SolverVariable[]) Arrays.copyOf(cVar.f635d, this.i);
        int i2 = this.i;
        this.n = new boolean[i2];
        this.j = i2;
        this.q = i2;
        if (f637b != null) {
            throw null;
        }
    }

    void A(a aVar) throws Exception {
        if (f637b != null) {
            throw null;
        }
        u(aVar);
        B(aVar, false);
        n();
    }

    public void D() {
        c cVar;
        int i = 0;
        while (true) {
            cVar = this.r;
            SolverVariable[] solverVariableArr = cVar.f635d;
            if (i >= solverVariableArr.length) {
                break;
            }
            SolverVariable solverVariable = solverVariableArr[i];
            if (solverVariable != null) {
                solverVariable.d();
            }
            i++;
        }
        cVar.f634c.c(this.s, this.t);
        this.t = 0;
        Arrays.fill(this.r.f635d, (Object) null);
        HashMap<String, SolverVariable> map = this.g;
        if (map != null) {
            map.clear();
        }
        this.f = 0;
        this.h.clear();
        this.o = 1;
        for (int i2 = 0; i2 < this.p; i2++) {
            this.k[i2].f630c = false;
        }
        C();
        this.p = 0;
        if (f638c) {
            this.u = new b(this.r);
        } else {
            this.u = new androidx.constraintlayout.solver.b(this.r);
        }
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
        androidx.constraintlayout.solver.b bVarR = r();
        double d2 = f;
        double d3 = i;
        bVarR.q(solverVariableQ2, solverVariableQ4, solverVariableQ6, solverVariableQ8, (float) (Math.sin(d2) * d3));
        d(bVarR);
        androidx.constraintlayout.solver.b bVarR2 = r();
        bVarR2.q(solverVariableQ, solverVariableQ3, solverVariableQ5, solverVariableQ7, (float) (Math.cos(d2) * d3));
        d(bVarR2);
    }

    public void c(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2, int i3) {
        androidx.constraintlayout.solver.b bVarR = r();
        bVarR.h(solverVariable, solverVariable2, i, f, solverVariable3, solverVariable4, i2);
        if (i3 != 8) {
            bVarR.d(this, i3);
        }
        d(bVarR);
    }

    public void d(androidx.constraintlayout.solver.b bVar) {
        SolverVariable solverVariableW;
        if (bVar == null) {
            return;
        }
        if (f637b != null) {
            throw null;
        }
        boolean z = true;
        if (this.p + 1 >= this.q || this.o + 1 >= this.j) {
            y();
        }
        boolean z2 = false;
        if (!bVar.f) {
            bVar.D(this);
            if (bVar.u()) {
                return;
            }
            bVar.r();
            if (bVar.f(this)) {
                SolverVariable solverVariableP = p();
                bVar.f628a = solverVariableP;
                l(bVar);
                this.u.a(bVar);
                B(this.u, true);
                if (solverVariableP.e == -1) {
                    if (bVar.f628a == solverVariableP && (solverVariableW = bVar.w(solverVariableP)) != null) {
                        if (f637b != null) {
                            throw null;
                        }
                        bVar.y(solverVariableW);
                    }
                    if (!bVar.f) {
                        bVar.f628a.g(bVar);
                    }
                    this.p--;
                }
            } else {
                z = false;
            }
            if (!bVar.s()) {
                return;
            } else {
                z2 = z;
            }
        }
        if (z2) {
            return;
        }
        l(bVar);
    }

    public androidx.constraintlayout.solver.b e(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        if (i2 == 8 && solverVariable2.h && solverVariable.e == -1) {
            solverVariable.e(this, solverVariable2.g + i);
            return null;
        }
        androidx.constraintlayout.solver.b bVarR = r();
        bVarR.n(solverVariable, solverVariable2, i);
        if (i2 != 8) {
            bVarR.d(this, i2);
        }
        d(bVarR);
        return bVarR;
    }

    public void f(SolverVariable solverVariable, int i) {
        int i2 = solverVariable.e;
        if (i2 == -1) {
            solverVariable.e(this, i);
            return;
        }
        if (i2 == -1) {
            androidx.constraintlayout.solver.b bVarR = r();
            bVarR.i(solverVariable, i);
            d(bVarR);
            return;
        }
        androidx.constraintlayout.solver.b bVar = this.k[i2];
        if (bVar.f) {
            bVar.f629b = i;
            return;
        }
        if (bVar.e.k() == 0) {
            bVar.f = true;
            bVar.f629b = i;
        } else {
            androidx.constraintlayout.solver.b bVarR2 = r();
            bVarR2.m(solverVariable, i);
            d(bVarR2);
        }
    }

    public void g(SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        androidx.constraintlayout.solver.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f = 0;
        bVarR.o(solverVariable, solverVariable2, solverVariableT, i);
        d(bVarR);
    }

    public void h(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        androidx.constraintlayout.solver.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f = 0;
        bVarR.o(solverVariable, solverVariable2, solverVariableT, i);
        if (i2 != 8) {
            m(bVarR, (int) (bVarR.e.g(solverVariableT) * (-1.0f)), i2);
        }
        d(bVarR);
    }

    public void i(SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        androidx.constraintlayout.solver.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f = 0;
        bVarR.p(solverVariable, solverVariable2, solverVariableT, i);
        d(bVarR);
    }

    public void j(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        androidx.constraintlayout.solver.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f = 0;
        bVarR.p(solverVariable, solverVariable2, solverVariableT, i);
        if (i2 != 8) {
            m(bVarR, (int) (bVarR.e.g(solverVariableT) * (-1.0f)), i2);
        }
        d(bVarR);
    }

    public void k(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f, int i) {
        androidx.constraintlayout.solver.b bVarR = r();
        bVarR.k(solverVariable, solverVariable2, solverVariable3, solverVariable4, f);
        if (i != 8) {
            bVarR.d(this, i);
        }
        d(bVarR);
    }

    void m(androidx.constraintlayout.solver.b bVar, int i, int i2) {
        bVar.e(o(i2, null), i);
    }

    public SolverVariable o(int i, String str) {
        if (f637b != null) {
            throw null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.ERROR, str);
        int i2 = this.f + 1;
        this.f = i2;
        this.o++;
        solverVariableA.f622d = i2;
        solverVariableA.f = i;
        this.r.f635d[i2] = solverVariableA;
        this.h.b(solverVariableA);
        return solverVariableA;
    }

    public SolverVariable p() {
        if (f637b != null) {
            throw null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.SLACK, null);
        int i = this.f + 1;
        this.f = i;
        this.o++;
        solverVariableA.f622d = i;
        this.r.f635d[i] = solverVariableA;
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
            int i = solverVariableF.f622d;
            if (i == -1 || i > this.f || this.r.f635d[i] == null) {
                if (i != -1) {
                    solverVariableF.d();
                }
                int i2 = this.f + 1;
                this.f = i2;
                this.o++;
                solverVariableF.f622d = i2;
                solverVariableF.k = SolverVariable.Type.UNRESTRICTED;
                this.r.f635d[i2] = solverVariableF;
            }
        }
        return solverVariableF;
    }

    public androidx.constraintlayout.solver.b r() {
        androidx.constraintlayout.solver.b bVarB;
        if (f638c) {
            bVarB = this.r.f632a.b();
            if (bVarB == null) {
                bVarB = new b(this.r);
                e++;
            } else {
                bVarB.z();
            }
        } else {
            bVarB = this.r.f633b.b();
            if (bVarB == null) {
                bVarB = new androidx.constraintlayout.solver.b(this.r);
                f639d++;
            } else {
                bVarB.z();
            }
        }
        SolverVariable.b();
        return bVarB;
    }

    public SolverVariable t() {
        if (f637b != null) {
            throw null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.SLACK, null);
        int i = this.f + 1;
        this.f = i;
        this.o++;
        solverVariableA.f622d = i;
        this.r.f635d[i] = solverVariableA;
        return solverVariableA;
    }

    public c v() {
        return this.r;
    }

    public int x(Object obj) {
        SolverVariable solverVariableF = ((ConstraintAnchor) obj).f();
        if (solverVariableF != null) {
            return (int) (solverVariableF.g + 0.5f);
        }
        return 0;
    }

    public void z() throws Exception {
        e eVar = f637b;
        if (eVar != null) {
            throw null;
        }
        if (!this.l && !this.m) {
            A(this.h);
            return;
        }
        if (eVar != null) {
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
            if (f637b != null) {
                throw null;
            }
            n();
        }
    }
}
