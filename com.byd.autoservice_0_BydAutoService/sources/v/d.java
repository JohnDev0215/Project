package v;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: LinearSystem.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f5371a = 1000;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static e f5372b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f5373c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static long f5374d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static long f5375e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a f5378h;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public v.b[] f5381k;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final c f5388r;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public a f5391u;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5376f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public HashMap<String, SolverVariable> f5377g = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5379i = 32;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f5380j = 32;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f5382l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f5383m = false;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean[] f5384n = new boolean[32];

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f5385o = 1;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f5386p = 0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f5387q = 32;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public SolverVariable[] f5389s = new SolverVariable[f5371a];

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f5390t = 0;

    /* JADX INFO: compiled from: LinearSystem.java */
    public interface a {
        void a(a aVar);

        void b(SolverVariable solverVariable);

        SolverVariable c(d dVar, boolean[] zArr);

        void clear();

        SolverVariable getKey();
    }

    /* JADX INFO: compiled from: LinearSystem.java */
    public class b extends v.b {
        public b(c cVar) {
            this.f5365e = new i(this, cVar);
        }
    }

    public d() {
        this.f5381k = null;
        this.f5381k = new v.b[32];
        C();
        c cVar = new c();
        this.f5388r = cVar;
        this.f5378h = new h(cVar);
        if (f5373c) {
            this.f5391u = new b(cVar);
        } else {
            this.f5391u = new v.b(cVar);
        }
    }

    public static v.b s(d dVar, SolverVariable solverVariable, SolverVariable solverVariable2, float f3) {
        return dVar.r().j(solverVariable, solverVariable2, f3);
    }

    public static e w() {
        return f5372b;
    }

    public void A(a aVar) throws Exception {
        if (f5372b != null) {
            throw null;
        }
        u(aVar);
        B(aVar, false);
        n();
    }

    public final int B(a aVar, boolean z3) {
        if (f5372b != null) {
            throw null;
        }
        for (int i3 = 0; i3 < this.f5385o; i3++) {
            this.f5384n[i3] = false;
        }
        boolean z4 = false;
        int i4 = 0;
        while (!z4) {
            if (f5372b != null) {
                throw null;
            }
            i4++;
            if (i4 >= this.f5385o * 2) {
                return i4;
            }
            if (aVar.getKey() != null) {
                this.f5384n[aVar.getKey().f813d] = true;
            }
            SolverVariable solverVariableC = aVar.c(this, this.f5384n);
            if (solverVariableC != null) {
                boolean[] zArr = this.f5384n;
                int i5 = solverVariableC.f813d;
                if (zArr[i5]) {
                    return i4;
                }
                zArr[i5] = true;
            }
            if (solverVariableC != null) {
                float f3 = Float.MAX_VALUE;
                int i6 = -1;
                for (int i7 = 0; i7 < this.f5386p; i7++) {
                    v.b bVar = this.f5381k[i7];
                    if (bVar.f5361a.f820k != SolverVariable.Type.UNRESTRICTED && !bVar.f5366f && bVar.t(solverVariableC)) {
                        float fG = bVar.f5365e.g(solverVariableC);
                        if (fG < 0.0f) {
                            float f4 = (-bVar.f5362b) / fG;
                            if (f4 < f3) {
                                i6 = i7;
                                f3 = f4;
                            }
                        }
                    }
                }
                if (i6 > -1) {
                    v.b bVar2 = this.f5381k[i6];
                    bVar2.f5361a.f814e = -1;
                    if (f5372b != null) {
                        throw null;
                    }
                    bVar2.y(solverVariableC);
                    SolverVariable solverVariable = bVar2.f5361a;
                    solverVariable.f814e = i6;
                    solverVariable.g(bVar2);
                } else {
                    continue;
                }
            } else {
                z4 = true;
            }
        }
        return i4;
    }

    public final void C() {
        int i3 = 0;
        if (f5373c) {
            while (true) {
                v.b[] bVarArr = this.f5381k;
                if (i3 >= bVarArr.length) {
                    return;
                }
                v.b bVar = bVarArr[i3];
                if (bVar != null) {
                    this.f5388r.f5367a.a(bVar);
                }
                this.f5381k[i3] = null;
                i3++;
            }
        } else {
            while (true) {
                v.b[] bVarArr2 = this.f5381k;
                if (i3 >= bVarArr2.length) {
                    return;
                }
                v.b bVar2 = bVarArr2[i3];
                if (bVar2 != null) {
                    this.f5388r.f5368b.a(bVar2);
                }
                this.f5381k[i3] = null;
                i3++;
            }
        }
    }

    public void D() {
        c cVar;
        int i3 = 0;
        while (true) {
            cVar = this.f5388r;
            SolverVariable[] solverVariableArr = cVar.f5370d;
            if (i3 >= solverVariableArr.length) {
                break;
            }
            SolverVariable solverVariable = solverVariableArr[i3];
            if (solverVariable != null) {
                solverVariable.d();
            }
            i3++;
        }
        cVar.f5369c.c(this.f5389s, this.f5390t);
        this.f5390t = 0;
        Arrays.fill(this.f5388r.f5370d, (Object) null);
        HashMap<String, SolverVariable> map = this.f5377g;
        if (map != null) {
            map.clear();
        }
        this.f5376f = 0;
        this.f5378h.clear();
        this.f5385o = 1;
        for (int i4 = 0; i4 < this.f5386p; i4++) {
            this.f5381k[i4].f5363c = false;
        }
        C();
        this.f5386p = 0;
        if (f5373c) {
            this.f5391u = new b(this.f5388r);
        } else {
            this.f5391u = new v.b(this.f5388r);
        }
    }

    public final SolverVariable a(SolverVariable.Type type, String str) {
        SolverVariable solverVariableB = this.f5388r.f5369c.b();
        if (solverVariableB == null) {
            solverVariableB = new SolverVariable(type, str);
            solverVariableB.f(type, str);
        } else {
            solverVariableB.d();
            solverVariableB.f(type, str);
        }
        int i3 = this.f5390t;
        int i4 = f5371a;
        if (i3 >= i4) {
            int i5 = i4 * 2;
            f5371a = i5;
            this.f5389s = (SolverVariable[]) Arrays.copyOf(this.f5389s, i5);
        }
        SolverVariable[] solverVariableArr = this.f5389s;
        int i6 = this.f5390t;
        this.f5390t = i6 + 1;
        solverVariableArr[i6] = solverVariableB;
        return solverVariableB;
    }

    public void b(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f3, int i3) {
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
        v.b bVarR = r();
        double d3 = f3;
        double d4 = i3;
        bVarR.q(solverVariableQ2, solverVariableQ4, solverVariableQ6, solverVariableQ8, (float) (Math.sin(d3) * d4));
        d(bVarR);
        v.b bVarR2 = r();
        bVarR2.q(solverVariableQ, solverVariableQ3, solverVariableQ5, solverVariableQ7, (float) (Math.cos(d3) * d4));
        d(bVarR2);
    }

    public void c(SolverVariable solverVariable, SolverVariable solverVariable2, int i3, float f3, SolverVariable solverVariable3, SolverVariable solverVariable4, int i4, int i5) {
        v.b bVarR = r();
        bVarR.h(solverVariable, solverVariable2, i3, f3, solverVariable3, solverVariable4, i4);
        if (i5 != 8) {
            bVarR.d(this, i5);
        }
        d(bVarR);
    }

    public void d(v.b bVar) {
        SolverVariable solverVariableW;
        if (bVar == null) {
            return;
        }
        if (f5372b != null) {
            throw null;
        }
        boolean z3 = true;
        if (this.f5386p + 1 >= this.f5387q || this.f5385o + 1 >= this.f5380j) {
            y();
        }
        boolean z4 = false;
        if (!bVar.f5366f) {
            bVar.D(this);
            if (bVar.u()) {
                return;
            }
            bVar.r();
            if (bVar.f(this)) {
                SolverVariable solverVariableP = p();
                bVar.f5361a = solverVariableP;
                l(bVar);
                this.f5391u.a(bVar);
                B(this.f5391u, true);
                if (solverVariableP.f814e == -1) {
                    if (bVar.f5361a == solverVariableP && (solverVariableW = bVar.w(solverVariableP)) != null) {
                        if (f5372b != null) {
                            throw null;
                        }
                        bVar.y(solverVariableW);
                    }
                    if (!bVar.f5366f) {
                        bVar.f5361a.g(bVar);
                    }
                    this.f5386p--;
                }
            } else {
                z3 = false;
            }
            if (!bVar.s()) {
                return;
            } else {
                z4 = z3;
            }
        }
        if (z4) {
            return;
        }
        l(bVar);
    }

    public v.b e(SolverVariable solverVariable, SolverVariable solverVariable2, int i3, int i4) {
        if (i4 == 8 && solverVariable2.f817h && solverVariable.f814e == -1) {
            solverVariable.e(this, solverVariable2.f816g + i3);
            return null;
        }
        v.b bVarR = r();
        bVarR.n(solverVariable, solverVariable2, i3);
        if (i4 != 8) {
            bVarR.d(this, i4);
        }
        d(bVarR);
        return bVarR;
    }

    public void f(SolverVariable solverVariable, int i3) {
        int i4 = solverVariable.f814e;
        if (i4 == -1) {
            solverVariable.e(this, i3);
            return;
        }
        if (i4 == -1) {
            v.b bVarR = r();
            bVarR.i(solverVariable, i3);
            d(bVarR);
            return;
        }
        v.b bVar = this.f5381k[i4];
        if (bVar.f5366f) {
            bVar.f5362b = i3;
            return;
        }
        if (bVar.f5365e.k() == 0) {
            bVar.f5366f = true;
            bVar.f5362b = i3;
        } else {
            v.b bVarR2 = r();
            bVarR2.m(solverVariable, i3);
            d(bVarR2);
        }
    }

    public void g(SolverVariable solverVariable, SolverVariable solverVariable2, int i3, boolean z3) {
        v.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f815f = 0;
        bVarR.o(solverVariable, solverVariable2, solverVariableT, i3);
        d(bVarR);
    }

    public void h(SolverVariable solverVariable, SolverVariable solverVariable2, int i3, int i4) {
        v.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f815f = 0;
        bVarR.o(solverVariable, solverVariable2, solverVariableT, i3);
        if (i4 != 8) {
            m(bVarR, (int) (bVarR.f5365e.g(solverVariableT) * (-1.0f)), i4);
        }
        d(bVarR);
    }

    public void i(SolverVariable solverVariable, SolverVariable solverVariable2, int i3, boolean z3) {
        v.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f815f = 0;
        bVarR.p(solverVariable, solverVariable2, solverVariableT, i3);
        d(bVarR);
    }

    public void j(SolverVariable solverVariable, SolverVariable solverVariable2, int i3, int i4) {
        v.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f815f = 0;
        bVarR.p(solverVariable, solverVariable2, solverVariableT, i3);
        if (i4 != 8) {
            m(bVarR, (int) (bVarR.f5365e.g(solverVariableT) * (-1.0f)), i4);
        }
        d(bVarR);
    }

    public void k(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f3, int i3) {
        v.b bVarR = r();
        bVarR.k(solverVariable, solverVariable2, solverVariable3, solverVariable4, f3);
        if (i3 != 8) {
            bVarR.d(this, i3);
        }
        d(bVarR);
    }

    public final void l(v.b bVar) {
        if (f5373c) {
            v.b[] bVarArr = this.f5381k;
            int i3 = this.f5386p;
            if (bVarArr[i3] != null) {
                this.f5388r.f5367a.a(bVarArr[i3]);
            }
        } else {
            v.b[] bVarArr2 = this.f5381k;
            int i4 = this.f5386p;
            if (bVarArr2[i4] != null) {
                this.f5388r.f5368b.a(bVarArr2[i4]);
            }
        }
        v.b[] bVarArr3 = this.f5381k;
        int i5 = this.f5386p;
        bVarArr3[i5] = bVar;
        SolverVariable solverVariable = bVar.f5361a;
        solverVariable.f814e = i5;
        this.f5386p = i5 + 1;
        solverVariable.g(bVar);
    }

    public void m(v.b bVar, int i3, int i4) {
        bVar.e(o(i4, null), i3);
    }

    public final void n() {
        for (int i3 = 0; i3 < this.f5386p; i3++) {
            v.b bVar = this.f5381k[i3];
            bVar.f5361a.f816g = bVar.f5362b;
        }
    }

    public SolverVariable o(int i3, String str) {
        if (f5372b != null) {
            throw null;
        }
        if (this.f5385o + 1 >= this.f5380j) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.ERROR, str);
        int i4 = this.f5376f + 1;
        this.f5376f = i4;
        this.f5385o++;
        solverVariableA.f813d = i4;
        solverVariableA.f815f = i3;
        this.f5388r.f5370d[i4] = solverVariableA;
        this.f5378h.b(solverVariableA);
        return solverVariableA;
    }

    public SolverVariable p() {
        if (f5372b != null) {
            throw null;
        }
        if (this.f5385o + 1 >= this.f5380j) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.SLACK, null);
        int i3 = this.f5376f + 1;
        this.f5376f = i3;
        this.f5385o++;
        solverVariableA.f813d = i3;
        this.f5388r.f5370d[i3] = solverVariableA;
        return solverVariableA;
    }

    public SolverVariable q(Object obj) {
        SolverVariable solverVariableF = null;
        if (obj == null) {
            return null;
        }
        if (this.f5385o + 1 >= this.f5380j) {
            y();
        }
        if (obj instanceof ConstraintAnchor) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
            solverVariableF = constraintAnchor.f();
            if (solverVariableF == null) {
                constraintAnchor.m(this.f5388r);
                solverVariableF = constraintAnchor.f();
            }
            int i3 = solverVariableF.f813d;
            if (i3 == -1 || i3 > this.f5376f || this.f5388r.f5370d[i3] == null) {
                if (i3 != -1) {
                    solverVariableF.d();
                }
                int i4 = this.f5376f + 1;
                this.f5376f = i4;
                this.f5385o++;
                solverVariableF.f813d = i4;
                solverVariableF.f820k = SolverVariable.Type.UNRESTRICTED;
                this.f5388r.f5370d[i4] = solverVariableF;
            }
        }
        return solverVariableF;
    }

    public v.b r() {
        v.b bVarB;
        if (f5373c) {
            bVarB = this.f5388r.f5367a.b();
            if (bVarB == null) {
                bVarB = new b(this.f5388r);
                f5375e++;
            } else {
                bVarB.z();
            }
        } else {
            bVarB = this.f5388r.f5368b.b();
            if (bVarB == null) {
                bVarB = new v.b(this.f5388r);
                f5374d++;
            } else {
                bVarB.z();
            }
        }
        SolverVariable.b();
        return bVarB;
    }

    public SolverVariable t() {
        if (f5372b != null) {
            throw null;
        }
        if (this.f5385o + 1 >= this.f5380j) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.SLACK, null);
        int i3 = this.f5376f + 1;
        this.f5376f = i3;
        this.f5385o++;
        solverVariableA.f813d = i3;
        this.f5388r.f5370d[i3] = solverVariableA;
        return solverVariableA;
    }

    public final int u(a aVar) throws Exception {
        float f3;
        boolean z3;
        int i3 = 0;
        while (true) {
            f3 = 0.0f;
            if (i3 >= this.f5386p) {
                z3 = false;
                break;
            }
            v.b[] bVarArr = this.f5381k;
            if (bVarArr[i3].f5361a.f820k != SolverVariable.Type.UNRESTRICTED && bVarArr[i3].f5362b < 0.0f) {
                z3 = true;
                break;
            }
            i3++;
        }
        if (!z3) {
            return 0;
        }
        boolean z4 = false;
        int i4 = 0;
        while (!z4) {
            if (f5372b != null) {
                throw null;
            }
            i4++;
            float f4 = Float.MAX_VALUE;
            int i5 = -1;
            int i6 = -1;
            int i7 = 0;
            int i8 = 0;
            while (i7 < this.f5386p) {
                v.b bVar = this.f5381k[i7];
                if (bVar.f5361a.f820k != SolverVariable.Type.UNRESTRICTED && !bVar.f5366f && bVar.f5362b < f3) {
                    int i9 = 1;
                    while (i9 < this.f5385o) {
                        SolverVariable solverVariable = this.f5388r.f5370d[i9];
                        float fG = bVar.f5365e.g(solverVariable);
                        if (fG > f3) {
                            for (int i10 = 0; i10 < 9; i10++) {
                                float f5 = solverVariable.f818i[i10] / fG;
                                if ((f5 < f4 && i10 == i8) || i10 > i8) {
                                    i8 = i10;
                                    f4 = f5;
                                    i5 = i7;
                                    i6 = i9;
                                }
                            }
                        }
                        i9++;
                        f3 = 0.0f;
                    }
                }
                i7++;
                f3 = 0.0f;
            }
            if (i5 != -1) {
                v.b bVar2 = this.f5381k[i5];
                bVar2.f5361a.f814e = -1;
                if (f5372b != null) {
                    throw null;
                }
                bVar2.y(this.f5388r.f5370d[i6]);
                SolverVariable solverVariable2 = bVar2.f5361a;
                solverVariable2.f814e = i5;
                solverVariable2.g(bVar2);
            } else {
                z4 = true;
            }
            if (i4 > this.f5385o / 2) {
                z4 = true;
            }
            f3 = 0.0f;
        }
        return i4;
    }

    public c v() {
        return this.f5388r;
    }

    public int x(Object obj) {
        SolverVariable solverVariableF = ((ConstraintAnchor) obj).f();
        if (solverVariableF != null) {
            return (int) (solverVariableF.f816g + 0.5f);
        }
        return 0;
    }

    public final void y() {
        int i3 = this.f5379i * 2;
        this.f5379i = i3;
        this.f5381k = (v.b[]) Arrays.copyOf(this.f5381k, i3);
        c cVar = this.f5388r;
        cVar.f5370d = (SolverVariable[]) Arrays.copyOf(cVar.f5370d, this.f5379i);
        int i4 = this.f5379i;
        this.f5384n = new boolean[i4];
        this.f5380j = i4;
        this.f5387q = i4;
        if (f5372b != null) {
            throw null;
        }
    }

    public void z() throws Exception {
        e eVar = f5372b;
        if (eVar != null) {
            throw null;
        }
        if (!this.f5382l && !this.f5383m) {
            A(this.f5378h);
            return;
        }
        if (eVar != null) {
            throw null;
        }
        boolean z3 = false;
        int i3 = 0;
        while (true) {
            if (i3 >= this.f5386p) {
                z3 = true;
                break;
            } else if (!this.f5381k[i3].f5366f) {
                break;
            } else {
                i3++;
            }
        }
        if (!z3) {
            A(this.f5378h);
        } else {
            if (f5372b != null) {
                throw null;
            }
            n();
        }
    }
}
