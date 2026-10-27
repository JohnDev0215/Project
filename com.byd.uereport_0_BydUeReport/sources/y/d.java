package y;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: LinearSystem.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f9116a = 1000;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static e f9117b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f9118c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static long f9119d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static long f9120e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a f9123h;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public y.b[] f9126k;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final c f9133r;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public a f9136u;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f9121f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public HashMap<String, SolverVariable> f9122g = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f9124i = 32;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f9125j = 32;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f9127l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f9128m = false;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean[] f9129n = new boolean[32];

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f9130o = 1;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f9131p = 0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f9132q = 32;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public SolverVariable[] f9134s = new SolverVariable[f9116a];

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f9135t = 0;

    /* JADX INFO: compiled from: LinearSystem.java */
    public interface a {
        void a(a aVar);

        void b(SolverVariable solverVariable);

        SolverVariable c(d dVar, boolean[] zArr);

        void clear();

        SolverVariable getKey();
    }

    /* JADX INFO: compiled from: LinearSystem.java */
    public class b extends y.b {
        public b(c cVar) {
            this.f9110e = new i(this, cVar);
        }
    }

    public d() {
        this.f9126k = null;
        this.f9126k = new y.b[32];
        C();
        c cVar = new c();
        this.f9133r = cVar;
        this.f9123h = new h(cVar);
        if (f9118c) {
            this.f9136u = new b(cVar);
        } else {
            this.f9136u = new y.b(cVar);
        }
    }

    public static y.b s(d dVar, SolverVariable solverVariable, SolverVariable solverVariable2, float f8) {
        return dVar.r().j(solverVariable, solverVariable2, f8);
    }

    public static e w() {
        return f9117b;
    }

    public void A(a aVar) throws Exception {
        if (f9117b != null) {
            throw null;
        }
        u(aVar);
        B(aVar, false);
        n();
    }

    public final int B(a aVar, boolean z7) {
        if (f9117b != null) {
            throw null;
        }
        for (int i8 = 0; i8 < this.f9130o; i8++) {
            this.f9129n[i8] = false;
        }
        boolean z8 = false;
        int i9 = 0;
        while (!z8) {
            if (f9117b != null) {
                throw null;
            }
            i9++;
            if (i9 >= this.f9130o * 2) {
                return i9;
            }
            if (aVar.getKey() != null) {
                this.f9129n[aVar.getKey().f904d] = true;
            }
            SolverVariable solverVariableC = aVar.c(this, this.f9129n);
            if (solverVariableC != null) {
                boolean[] zArr = this.f9129n;
                int i10 = solverVariableC.f904d;
                if (zArr[i10]) {
                    return i9;
                }
                zArr[i10] = true;
            }
            if (solverVariableC != null) {
                float f8 = Float.MAX_VALUE;
                int i11 = -1;
                for (int i12 = 0; i12 < this.f9131p; i12++) {
                    y.b bVar = this.f9126k[i12];
                    if (bVar.f9106a.f911k != SolverVariable.Type.UNRESTRICTED && !bVar.f9111f && bVar.t(solverVariableC)) {
                        float fC = bVar.f9110e.c(solverVariableC);
                        if (fC < 0.0f) {
                            float f9 = (-bVar.f9107b) / fC;
                            if (f9 < f8) {
                                i11 = i12;
                                f8 = f9;
                            }
                        }
                    }
                }
                if (i11 > -1) {
                    y.b bVar2 = this.f9126k[i11];
                    bVar2.f9106a.f905e = -1;
                    if (f9117b != null) {
                        throw null;
                    }
                    bVar2.y(solverVariableC);
                    SolverVariable solverVariable = bVar2.f9106a;
                    solverVariable.f905e = i11;
                    solverVariable.g(bVar2);
                } else {
                    continue;
                }
            } else {
                z8 = true;
            }
        }
        return i9;
    }

    public final void C() {
        int i8 = 0;
        if (f9118c) {
            while (true) {
                y.b[] bVarArr = this.f9126k;
                if (i8 >= bVarArr.length) {
                    return;
                }
                y.b bVar = bVarArr[i8];
                if (bVar != null) {
                    this.f9133r.f9112a.a(bVar);
                }
                this.f9126k[i8] = null;
                i8++;
            }
        } else {
            while (true) {
                y.b[] bVarArr2 = this.f9126k;
                if (i8 >= bVarArr2.length) {
                    return;
                }
                y.b bVar2 = bVarArr2[i8];
                if (bVar2 != null) {
                    this.f9133r.f9113b.a(bVar2);
                }
                this.f9126k[i8] = null;
                i8++;
            }
        }
    }

    public void D() {
        c cVar;
        int i8 = 0;
        while (true) {
            cVar = this.f9133r;
            SolverVariable[] solverVariableArr = cVar.f9115d;
            if (i8 >= solverVariableArr.length) {
                break;
            }
            SolverVariable solverVariable = solverVariableArr[i8];
            if (solverVariable != null) {
                solverVariable.d();
            }
            i8++;
        }
        cVar.f9114c.c(this.f9134s, this.f9135t);
        this.f9135t = 0;
        Arrays.fill(this.f9133r.f9115d, (Object) null);
        HashMap<String, SolverVariable> map = this.f9122g;
        if (map != null) {
            map.clear();
        }
        this.f9121f = 0;
        this.f9123h.clear();
        this.f9130o = 1;
        for (int i9 = 0; i9 < this.f9131p; i9++) {
            this.f9126k[i9].f9108c = false;
        }
        C();
        this.f9131p = 0;
        if (f9118c) {
            this.f9136u = new b(this.f9133r);
        } else {
            this.f9136u = new y.b(this.f9133r);
        }
    }

    public final SolverVariable a(SolverVariable.Type type, String str) {
        SolverVariable solverVariableB = this.f9133r.f9114c.b();
        if (solverVariableB == null) {
            solverVariableB = new SolverVariable(type, str);
            solverVariableB.f(type, str);
        } else {
            solverVariableB.d();
            solverVariableB.f(type, str);
        }
        int i8 = this.f9135t;
        int i9 = f9116a;
        if (i8 >= i9) {
            int i10 = i9 * 2;
            f9116a = i10;
            this.f9134s = (SolverVariable[]) Arrays.copyOf(this.f9134s, i10);
        }
        SolverVariable[] solverVariableArr = this.f9134s;
        int i11 = this.f9135t;
        this.f9135t = i11 + 1;
        solverVariableArr[i11] = solverVariableB;
        return solverVariableB;
    }

    public void b(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f8, int i8) {
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
        y.b bVarR = r();
        double d8 = f8;
        double d9 = i8;
        bVarR.q(solverVariableQ2, solverVariableQ4, solverVariableQ6, solverVariableQ8, (float) (Math.sin(d8) * d9));
        d(bVarR);
        y.b bVarR2 = r();
        bVarR2.q(solverVariableQ, solverVariableQ3, solverVariableQ5, solverVariableQ7, (float) (Math.cos(d8) * d9));
        d(bVarR2);
    }

    public void c(SolverVariable solverVariable, SolverVariable solverVariable2, int i8, float f8, SolverVariable solverVariable3, SolverVariable solverVariable4, int i9, int i10) {
        y.b bVarR = r();
        bVarR.h(solverVariable, solverVariable2, i8, f8, solverVariable3, solverVariable4, i9);
        if (i10 != 8) {
            bVarR.d(this, i10);
        }
        d(bVarR);
    }

    public void d(y.b bVar) {
        SolverVariable solverVariableW;
        if (bVar == null) {
            return;
        }
        if (f9117b != null) {
            throw null;
        }
        boolean z7 = true;
        if (this.f9131p + 1 >= this.f9132q || this.f9130o + 1 >= this.f9125j) {
            y();
        }
        boolean z8 = false;
        if (!bVar.f9111f) {
            bVar.D(this);
            if (bVar.u()) {
                return;
            }
            bVar.r();
            if (bVar.f(this)) {
                SolverVariable solverVariableP = p();
                bVar.f9106a = solverVariableP;
                l(bVar);
                this.f9136u.a(bVar);
                B(this.f9136u, true);
                if (solverVariableP.f905e == -1) {
                    if (bVar.f9106a == solverVariableP && (solverVariableW = bVar.w(solverVariableP)) != null) {
                        if (f9117b != null) {
                            throw null;
                        }
                        bVar.y(solverVariableW);
                    }
                    if (!bVar.f9111f) {
                        bVar.f9106a.g(bVar);
                    }
                    this.f9131p--;
                }
            } else {
                z7 = false;
            }
            if (!bVar.s()) {
                return;
            } else {
                z8 = z7;
            }
        }
        if (z8) {
            return;
        }
        l(bVar);
    }

    public y.b e(SolverVariable solverVariable, SolverVariable solverVariable2, int i8, int i9) {
        if (i9 == 8 && solverVariable2.f908h && solverVariable.f905e == -1) {
            solverVariable.e(this, solverVariable2.f907g + i8);
            return null;
        }
        y.b bVarR = r();
        bVarR.n(solverVariable, solverVariable2, i8);
        if (i9 != 8) {
            bVarR.d(this, i9);
        }
        d(bVarR);
        return bVarR;
    }

    public void f(SolverVariable solverVariable, int i8) {
        int i9 = solverVariable.f905e;
        if (i9 == -1) {
            solverVariable.e(this, i8);
            return;
        }
        if (i9 == -1) {
            y.b bVarR = r();
            bVarR.i(solverVariable, i8);
            d(bVarR);
            return;
        }
        y.b bVar = this.f9126k[i9];
        if (bVar.f9111f) {
            bVar.f9107b = i8;
            return;
        }
        if (bVar.f9110e.e() == 0) {
            bVar.f9111f = true;
            bVar.f9107b = i8;
        } else {
            y.b bVarR2 = r();
            bVarR2.m(solverVariable, i8);
            d(bVarR2);
        }
    }

    public void g(SolverVariable solverVariable, SolverVariable solverVariable2, int i8, boolean z7) {
        y.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f906f = 0;
        bVarR.o(solverVariable, solverVariable2, solverVariableT, i8);
        d(bVarR);
    }

    public void h(SolverVariable solverVariable, SolverVariable solverVariable2, int i8, int i9) {
        y.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f906f = 0;
        bVarR.o(solverVariable, solverVariable2, solverVariableT, i8);
        if (i9 != 8) {
            m(bVarR, (int) (bVarR.f9110e.c(solverVariableT) * (-1.0f)), i9);
        }
        d(bVarR);
    }

    public void i(SolverVariable solverVariable, SolverVariable solverVariable2, int i8, boolean z7) {
        y.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f906f = 0;
        bVarR.p(solverVariable, solverVariable2, solverVariableT, i8);
        d(bVarR);
    }

    public void j(SolverVariable solverVariable, SolverVariable solverVariable2, int i8, int i9) {
        y.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f906f = 0;
        bVarR.p(solverVariable, solverVariable2, solverVariableT, i8);
        if (i9 != 8) {
            m(bVarR, (int) (bVarR.f9110e.c(solverVariableT) * (-1.0f)), i9);
        }
        d(bVarR);
    }

    public void k(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f8, int i8) {
        y.b bVarR = r();
        bVarR.k(solverVariable, solverVariable2, solverVariable3, solverVariable4, f8);
        if (i8 != 8) {
            bVarR.d(this, i8);
        }
        d(bVarR);
    }

    public final void l(y.b bVar) {
        if (f9118c) {
            y.b[] bVarArr = this.f9126k;
            int i8 = this.f9131p;
            if (bVarArr[i8] != null) {
                this.f9133r.f9112a.a(bVarArr[i8]);
            }
        } else {
            y.b[] bVarArr2 = this.f9126k;
            int i9 = this.f9131p;
            if (bVarArr2[i9] != null) {
                this.f9133r.f9113b.a(bVarArr2[i9]);
            }
        }
        y.b[] bVarArr3 = this.f9126k;
        int i10 = this.f9131p;
        bVarArr3[i10] = bVar;
        SolverVariable solverVariable = bVar.f9106a;
        solverVariable.f905e = i10;
        this.f9131p = i10 + 1;
        solverVariable.g(bVar);
    }

    public void m(y.b bVar, int i8, int i9) {
        bVar.e(o(i9, null), i8);
    }

    public final void n() {
        for (int i8 = 0; i8 < this.f9131p; i8++) {
            y.b bVar = this.f9126k[i8];
            bVar.f9106a.f907g = bVar.f9107b;
        }
    }

    public SolverVariable o(int i8, String str) {
        if (f9117b != null) {
            throw null;
        }
        if (this.f9130o + 1 >= this.f9125j) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.ERROR, str);
        int i9 = this.f9121f + 1;
        this.f9121f = i9;
        this.f9130o++;
        solverVariableA.f904d = i9;
        solverVariableA.f906f = i8;
        this.f9133r.f9115d[i9] = solverVariableA;
        this.f9123h.b(solverVariableA);
        return solverVariableA;
    }

    public SolverVariable p() {
        if (f9117b != null) {
            throw null;
        }
        if (this.f9130o + 1 >= this.f9125j) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.SLACK, null);
        int i8 = this.f9121f + 1;
        this.f9121f = i8;
        this.f9130o++;
        solverVariableA.f904d = i8;
        this.f9133r.f9115d[i8] = solverVariableA;
        return solverVariableA;
    }

    public SolverVariable q(Object obj) {
        SolverVariable solverVariableF = null;
        if (obj == null) {
            return null;
        }
        if (this.f9130o + 1 >= this.f9125j) {
            y();
        }
        if (obj instanceof ConstraintAnchor) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
            solverVariableF = constraintAnchor.f();
            if (solverVariableF == null) {
                constraintAnchor.m(this.f9133r);
                solverVariableF = constraintAnchor.f();
            }
            int i8 = solverVariableF.f904d;
            if (i8 == -1 || i8 > this.f9121f || this.f9133r.f9115d[i8] == null) {
                if (i8 != -1) {
                    solverVariableF.d();
                }
                int i9 = this.f9121f + 1;
                this.f9121f = i9;
                this.f9130o++;
                solverVariableF.f904d = i9;
                solverVariableF.f911k = SolverVariable.Type.UNRESTRICTED;
                this.f9133r.f9115d[i9] = solverVariableF;
            }
        }
        return solverVariableF;
    }

    public y.b r() {
        y.b bVarB;
        if (f9118c) {
            bVarB = this.f9133r.f9112a.b();
            if (bVarB == null) {
                bVarB = new b(this.f9133r);
                f9120e++;
            } else {
                bVarB.z();
            }
        } else {
            bVarB = this.f9133r.f9113b.b();
            if (bVarB == null) {
                bVarB = new y.b(this.f9133r);
                f9119d++;
            } else {
                bVarB.z();
            }
        }
        SolverVariable.b();
        return bVarB;
    }

    public SolverVariable t() {
        if (f9117b != null) {
            throw null;
        }
        if (this.f9130o + 1 >= this.f9125j) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.SLACK, null);
        int i8 = this.f9121f + 1;
        this.f9121f = i8;
        this.f9130o++;
        solverVariableA.f904d = i8;
        this.f9133r.f9115d[i8] = solverVariableA;
        return solverVariableA;
    }

    public final int u(a aVar) throws Exception {
        float f8;
        boolean z7;
        int i8 = 0;
        while (true) {
            f8 = 0.0f;
            if (i8 >= this.f9131p) {
                z7 = false;
                break;
            }
            y.b[] bVarArr = this.f9126k;
            if (bVarArr[i8].f9106a.f911k != SolverVariable.Type.UNRESTRICTED && bVarArr[i8].f9107b < 0.0f) {
                z7 = true;
                break;
            }
            i8++;
        }
        if (!z7) {
            return 0;
        }
        boolean z8 = false;
        int i9 = 0;
        while (!z8) {
            if (f9117b != null) {
                throw null;
            }
            i9++;
            float f9 = Float.MAX_VALUE;
            int i10 = -1;
            int i11 = -1;
            int i12 = 0;
            int i13 = 0;
            while (i12 < this.f9131p) {
                y.b bVar = this.f9126k[i12];
                if (bVar.f9106a.f911k != SolverVariable.Type.UNRESTRICTED && !bVar.f9111f && bVar.f9107b < f8) {
                    int i14 = 1;
                    while (i14 < this.f9130o) {
                        SolverVariable solverVariable = this.f9133r.f9115d[i14];
                        float fC = bVar.f9110e.c(solverVariable);
                        if (fC > f8) {
                            for (int i15 = 0; i15 < 9; i15++) {
                                float f10 = solverVariable.f909i[i15] / fC;
                                if ((f10 < f9 && i15 == i13) || i15 > i13) {
                                    i13 = i15;
                                    f9 = f10;
                                    i10 = i12;
                                    i11 = i14;
                                }
                            }
                        }
                        i14++;
                        f8 = 0.0f;
                    }
                }
                i12++;
                f8 = 0.0f;
            }
            if (i10 != -1) {
                y.b bVar2 = this.f9126k[i10];
                bVar2.f9106a.f905e = -1;
                if (f9117b != null) {
                    throw null;
                }
                bVar2.y(this.f9133r.f9115d[i11]);
                SolverVariable solverVariable2 = bVar2.f9106a;
                solverVariable2.f905e = i10;
                solverVariable2.g(bVar2);
            } else {
                z8 = true;
            }
            if (i9 > this.f9130o / 2) {
                z8 = true;
            }
            f8 = 0.0f;
        }
        return i9;
    }

    public c v() {
        return this.f9133r;
    }

    public int x(Object obj) {
        SolverVariable solverVariableF = ((ConstraintAnchor) obj).f();
        if (solverVariableF != null) {
            return (int) (solverVariableF.f907g + 0.5f);
        }
        return 0;
    }

    public final void y() {
        int i8 = this.f9124i * 2;
        this.f9124i = i8;
        this.f9126k = (y.b[]) Arrays.copyOf(this.f9126k, i8);
        c cVar = this.f9133r;
        cVar.f9115d = (SolverVariable[]) Arrays.copyOf(cVar.f9115d, this.f9124i);
        int i9 = this.f9124i;
        this.f9129n = new boolean[i9];
        this.f9125j = i9;
        this.f9132q = i9;
        if (f9117b != null) {
            throw null;
        }
    }

    public void z() throws Exception {
        e eVar = f9117b;
        if (eVar != null) {
            throw null;
        }
        if (!this.f9127l && !this.f9128m) {
            A(this.f9123h);
            return;
        }
        if (eVar != null) {
            throw null;
        }
        boolean z7 = false;
        int i8 = 0;
        while (true) {
            if (i8 >= this.f9131p) {
                z7 = true;
                break;
            } else if (!this.f9126k[i8].f9111f) {
                break;
            } else {
                i8++;
            }
        }
        if (!z7) {
            A(this.f9123h);
        } else {
            if (f9117b != null) {
                throw null;
            }
            n();
        }
    }
}
