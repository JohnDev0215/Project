package b.f.b;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: LinearSystem.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f2186a = 1000;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static e f2187b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f2188c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static long f2189d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static long f2190e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a f2193h;
    public b.f.b.b[] k;
    public final c r;
    public a u;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2191f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public HashMap<String, SolverVariable> f2192g = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2194i = 32;
    public int j = 32;
    public boolean l = false;
    public boolean m = false;
    public boolean[] n = new boolean[32];
    public int o = 1;
    public int p = 0;
    public int q = 32;
    public SolverVariable[] s = new SolverVariable[f2186a];
    public int t = 0;

    /* JADX INFO: compiled from: LinearSystem.java */
    public interface a {
        void a(a aVar);

        void b(SolverVariable solverVariable);

        SolverVariable c(d dVar, boolean[] zArr);

        void clear();

        SolverVariable getKey();
    }

    /* JADX INFO: compiled from: LinearSystem.java */
    public class b extends b.f.b.b {
        public b(c cVar) {
            this.f2180e = new i(this, cVar);
        }
    }

    public d() {
        this.k = null;
        this.k = new b.f.b.b[32];
        C();
        c cVar = new c();
        this.r = cVar;
        this.f2193h = new h(cVar);
        if (f2188c) {
            this.u = new b(cVar);
        } else {
            this.u = new b.f.b.b(cVar);
        }
    }

    public static b.f.b.b s(d dVar, SolverVariable solverVariable, SolverVariable solverVariable2, float f2) {
        return dVar.r().j(solverVariable, solverVariable2, f2);
    }

    public static e w() {
        return f2187b;
    }

    public void A(a aVar) throws Exception {
        if (f2187b != null) {
            throw null;
        }
        u(aVar);
        B(aVar, false);
        n();
    }

    public final int B(a aVar, boolean z) {
        if (f2187b != null) {
            throw null;
        }
        for (int i2 = 0; i2 < this.o; i2++) {
            this.n[i2] = false;
        }
        boolean z2 = false;
        int i3 = 0;
        while (!z2) {
            if (f2187b != null) {
                throw null;
            }
            i3++;
            if (i3 >= this.o * 2) {
                return i3;
            }
            if (aVar.getKey() != null) {
                this.n[aVar.getKey().f611d] = true;
            }
            SolverVariable solverVariableC = aVar.c(this, this.n);
            if (solverVariableC != null) {
                boolean[] zArr = this.n;
                int i4 = solverVariableC.f611d;
                if (zArr[i4]) {
                    return i3;
                }
                zArr[i4] = true;
            }
            if (solverVariableC != null) {
                float f2 = Float.MAX_VALUE;
                int i5 = -1;
                for (int i6 = 0; i6 < this.p; i6++) {
                    b.f.b.b bVar = this.k[i6];
                    if (bVar.f2176a.k != SolverVariable.Type.UNRESTRICTED && !bVar.f2181f && bVar.t(solverVariableC)) {
                        float fG = bVar.f2180e.g(solverVariableC);
                        if (fG < 0.0f) {
                            float f3 = (-bVar.f2177b) / fG;
                            if (f3 < f2) {
                                i5 = i6;
                                f2 = f3;
                            }
                        }
                    }
                }
                if (i5 > -1) {
                    b.f.b.b bVar2 = this.k[i5];
                    bVar2.f2176a.f612e = -1;
                    if (f2187b != null) {
                        throw null;
                    }
                    bVar2.y(solverVariableC);
                    SolverVariable solverVariable = bVar2.f2176a;
                    solverVariable.f612e = i5;
                    solverVariable.g(bVar2);
                } else {
                    continue;
                }
            } else {
                z2 = true;
            }
        }
        return i3;
    }

    public final void C() {
        int i2 = 0;
        if (f2188c) {
            while (true) {
                b.f.b.b[] bVarArr = this.k;
                if (i2 >= bVarArr.length) {
                    return;
                }
                b.f.b.b bVar = bVarArr[i2];
                if (bVar != null) {
                    this.r.f2182a.a(bVar);
                }
                this.k[i2] = null;
                i2++;
            }
        } else {
            while (true) {
                b.f.b.b[] bVarArr2 = this.k;
                if (i2 >= bVarArr2.length) {
                    return;
                }
                b.f.b.b bVar2 = bVarArr2[i2];
                if (bVar2 != null) {
                    this.r.f2183b.a(bVar2);
                }
                this.k[i2] = null;
                i2++;
            }
        }
    }

    public void D() {
        c cVar;
        int i2 = 0;
        while (true) {
            cVar = this.r;
            SolverVariable[] solverVariableArr = cVar.f2185d;
            if (i2 >= solverVariableArr.length) {
                break;
            }
            SolverVariable solverVariable = solverVariableArr[i2];
            if (solverVariable != null) {
                solverVariable.d();
            }
            i2++;
        }
        cVar.f2184c.c(this.s, this.t);
        this.t = 0;
        Arrays.fill(this.r.f2185d, (Object) null);
        HashMap<String, SolverVariable> map = this.f2192g;
        if (map != null) {
            map.clear();
        }
        this.f2191f = 0;
        this.f2193h.clear();
        this.o = 1;
        for (int i3 = 0; i3 < this.p; i3++) {
            this.k[i3].f2178c = false;
        }
        C();
        this.p = 0;
        if (f2188c) {
            this.u = new b(this.r);
        } else {
            this.u = new b.f.b.b(this.r);
        }
    }

    public final SolverVariable a(SolverVariable.Type type, String str) {
        SolverVariable solverVariableB = this.r.f2184c.b();
        if (solverVariableB == null) {
            solverVariableB = new SolverVariable(type, str);
            solverVariableB.f(type, str);
        } else {
            solverVariableB.d();
            solverVariableB.f(type, str);
        }
        int i2 = this.t;
        int i3 = f2186a;
        if (i2 >= i3) {
            int i4 = i3 * 2;
            f2186a = i4;
            this.s = (SolverVariable[]) Arrays.copyOf(this.s, i4);
        }
        SolverVariable[] solverVariableArr = this.s;
        int i5 = this.t;
        this.t = i5 + 1;
        solverVariableArr[i5] = solverVariableB;
        return solverVariableB;
    }

    public void b(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f2, int i2) {
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
        b.f.b.b bVarR = r();
        double d2 = f2;
        double d3 = i2;
        bVarR.q(solverVariableQ2, solverVariableQ4, solverVariableQ6, solverVariableQ8, (float) (Math.sin(d2) * d3));
        d(bVarR);
        b.f.b.b bVarR2 = r();
        bVarR2.q(solverVariableQ, solverVariableQ3, solverVariableQ5, solverVariableQ7, (float) (Math.cos(d2) * d3));
        d(bVarR2);
    }

    public void c(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, float f2, SolverVariable solverVariable3, SolverVariable solverVariable4, int i3, int i4) {
        b.f.b.b bVarR = r();
        bVarR.h(solverVariable, solverVariable2, i2, f2, solverVariable3, solverVariable4, i3);
        if (i4 != 8) {
            bVarR.d(this, i4);
        }
        d(bVarR);
    }

    public void d(b.f.b.b bVar) {
        SolverVariable solverVariableW;
        if (bVar == null) {
            return;
        }
        if (f2187b != null) {
            throw null;
        }
        boolean z = true;
        if (this.p + 1 >= this.q || this.o + 1 >= this.j) {
            y();
        }
        boolean z2 = false;
        if (!bVar.f2181f) {
            bVar.D(this);
            if (bVar.u()) {
                return;
            }
            bVar.r();
            if (bVar.f(this)) {
                SolverVariable solverVariableP = p();
                bVar.f2176a = solverVariableP;
                l(bVar);
                this.u.a(bVar);
                B(this.u, true);
                if (solverVariableP.f612e == -1) {
                    if (bVar.f2176a == solverVariableP && (solverVariableW = bVar.w(solverVariableP)) != null) {
                        if (f2187b != null) {
                            throw null;
                        }
                        bVar.y(solverVariableW);
                    }
                    if (!bVar.f2181f) {
                        bVar.f2176a.g(bVar);
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

    public b.f.b.b e(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        if (i3 == 8 && solverVariable2.f615h && solverVariable.f612e == -1) {
            solverVariable.e(this, solverVariable2.f614g + i2);
            return null;
        }
        b.f.b.b bVarR = r();
        bVarR.n(solverVariable, solverVariable2, i2);
        if (i3 != 8) {
            bVarR.d(this, i3);
        }
        d(bVarR);
        return bVarR;
    }

    public void f(SolverVariable solverVariable, int i2) {
        int i3 = solverVariable.f612e;
        if (i3 == -1) {
            solverVariable.e(this, i2);
            return;
        }
        if (i3 == -1) {
            b.f.b.b bVarR = r();
            bVarR.i(solverVariable, i2);
            d(bVarR);
            return;
        }
        b.f.b.b bVar = this.k[i3];
        if (bVar.f2181f) {
            bVar.f2177b = i2;
            return;
        }
        if (bVar.f2180e.k() == 0) {
            bVar.f2181f = true;
            bVar.f2177b = i2;
        } else {
            b.f.b.b bVarR2 = r();
            bVarR2.m(solverVariable, i2);
            d(bVarR2);
        }
    }

    public void g(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, boolean z) {
        b.f.b.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f613f = 0;
        bVarR.o(solverVariable, solverVariable2, solverVariableT, i2);
        d(bVarR);
    }

    public void h(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        b.f.b.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f613f = 0;
        bVarR.o(solverVariable, solverVariable2, solverVariableT, i2);
        if (i3 != 8) {
            m(bVarR, (int) (bVarR.f2180e.g(solverVariableT) * (-1.0f)), i3);
        }
        d(bVarR);
    }

    public void i(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, boolean z) {
        b.f.b.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f613f = 0;
        bVarR.p(solverVariable, solverVariable2, solverVariableT, i2);
        d(bVarR);
    }

    public void j(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        b.f.b.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f613f = 0;
        bVarR.p(solverVariable, solverVariable2, solverVariableT, i2);
        if (i3 != 8) {
            m(bVarR, (int) (bVarR.f2180e.g(solverVariableT) * (-1.0f)), i3);
        }
        d(bVarR);
    }

    public void k(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f2, int i2) {
        b.f.b.b bVarR = r();
        bVarR.k(solverVariable, solverVariable2, solverVariable3, solverVariable4, f2);
        if (i2 != 8) {
            bVarR.d(this, i2);
        }
        d(bVarR);
    }

    public final void l(b.f.b.b bVar) {
        if (f2188c) {
            b.f.b.b[] bVarArr = this.k;
            int i2 = this.p;
            if (bVarArr[i2] != null) {
                this.r.f2182a.a(bVarArr[i2]);
            }
        } else {
            b.f.b.b[] bVarArr2 = this.k;
            int i3 = this.p;
            if (bVarArr2[i3] != null) {
                this.r.f2183b.a(bVarArr2[i3]);
            }
        }
        b.f.b.b[] bVarArr3 = this.k;
        int i4 = this.p;
        bVarArr3[i4] = bVar;
        SolverVariable solverVariable = bVar.f2176a;
        solverVariable.f612e = i4;
        this.p = i4 + 1;
        solverVariable.g(bVar);
    }

    public void m(b.f.b.b bVar, int i2, int i3) {
        bVar.e(o(i3, null), i2);
    }

    public final void n() {
        for (int i2 = 0; i2 < this.p; i2++) {
            b.f.b.b bVar = this.k[i2];
            bVar.f2176a.f614g = bVar.f2177b;
        }
    }

    public SolverVariable o(int i2, String str) {
        if (f2187b != null) {
            throw null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.ERROR, str);
        int i3 = this.f2191f + 1;
        this.f2191f = i3;
        this.o++;
        solverVariableA.f611d = i3;
        solverVariableA.f613f = i2;
        this.r.f2185d[i3] = solverVariableA;
        this.f2193h.b(solverVariableA);
        return solverVariableA;
    }

    public SolverVariable p() {
        if (f2187b != null) {
            throw null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.SLACK, null);
        int i2 = this.f2191f + 1;
        this.f2191f = i2;
        this.o++;
        solverVariableA.f611d = i2;
        this.r.f2185d[i2] = solverVariableA;
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
            int i2 = solverVariableF.f611d;
            if (i2 == -1 || i2 > this.f2191f || this.r.f2185d[i2] == null) {
                if (i2 != -1) {
                    solverVariableF.d();
                }
                int i3 = this.f2191f + 1;
                this.f2191f = i3;
                this.o++;
                solverVariableF.f611d = i3;
                solverVariableF.k = SolverVariable.Type.UNRESTRICTED;
                this.r.f2185d[i3] = solverVariableF;
            }
        }
        return solverVariableF;
    }

    public b.f.b.b r() {
        b.f.b.b bVarB;
        if (f2188c) {
            bVarB = this.r.f2182a.b();
            if (bVarB == null) {
                bVarB = new b(this.r);
                f2190e++;
            } else {
                bVarB.z();
            }
        } else {
            bVarB = this.r.f2183b.b();
            if (bVarB == null) {
                bVarB = new b.f.b.b(this.r);
                f2189d++;
            } else {
                bVarB.z();
            }
        }
        SolverVariable.b();
        return bVarB;
    }

    public SolverVariable t() {
        if (f2187b != null) {
            throw null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.SLACK, null);
        int i2 = this.f2191f + 1;
        this.f2191f = i2;
        this.o++;
        solverVariableA.f611d = i2;
        this.r.f2185d[i2] = solverVariableA;
        return solverVariableA;
    }

    public final int u(a aVar) throws Exception {
        float f2;
        boolean z;
        int i2 = 0;
        while (true) {
            f2 = 0.0f;
            if (i2 >= this.p) {
                z = false;
                break;
            }
            b.f.b.b[] bVarArr = this.k;
            if (bVarArr[i2].f2176a.k != SolverVariable.Type.UNRESTRICTED && bVarArr[i2].f2177b < 0.0f) {
                z = true;
                break;
            }
            i2++;
        }
        if (!z) {
            return 0;
        }
        boolean z2 = false;
        int i3 = 0;
        while (!z2) {
            if (f2187b != null) {
                throw null;
            }
            i3++;
            float f3 = Float.MAX_VALUE;
            int i4 = -1;
            int i5 = -1;
            int i6 = 0;
            int i7 = 0;
            while (i6 < this.p) {
                b.f.b.b bVar = this.k[i6];
                if (bVar.f2176a.k != SolverVariable.Type.UNRESTRICTED && !bVar.f2181f && bVar.f2177b < f2) {
                    int i8 = 1;
                    while (i8 < this.o) {
                        SolverVariable solverVariable = this.r.f2185d[i8];
                        float fG = bVar.f2180e.g(solverVariable);
                        if (fG > f2) {
                            for (int i9 = 0; i9 < 9; i9++) {
                                float f4 = solverVariable.f616i[i9] / fG;
                                if ((f4 < f3 && i9 == i7) || i9 > i7) {
                                    i7 = i9;
                                    f3 = f4;
                                    i4 = i6;
                                    i5 = i8;
                                }
                            }
                        }
                        i8++;
                        f2 = 0.0f;
                    }
                }
                i6++;
                f2 = 0.0f;
            }
            if (i4 != -1) {
                b.f.b.b bVar2 = this.k[i4];
                bVar2.f2176a.f612e = -1;
                if (f2187b != null) {
                    throw null;
                }
                bVar2.y(this.r.f2185d[i5]);
                SolverVariable solverVariable2 = bVar2.f2176a;
                solverVariable2.f612e = i4;
                solverVariable2.g(bVar2);
            } else {
                z2 = true;
            }
            if (i3 > this.o / 2) {
                z2 = true;
            }
            f2 = 0.0f;
        }
        return i3;
    }

    public c v() {
        return this.r;
    }

    public int x(Object obj) {
        SolverVariable solverVariableF = ((ConstraintAnchor) obj).f();
        if (solverVariableF != null) {
            return (int) (solverVariableF.f614g + 0.5f);
        }
        return 0;
    }

    public final void y() {
        int i2 = this.f2194i * 2;
        this.f2194i = i2;
        this.k = (b.f.b.b[]) Arrays.copyOf(this.k, i2);
        c cVar = this.r;
        cVar.f2185d = (SolverVariable[]) Arrays.copyOf(cVar.f2185d, this.f2194i);
        int i3 = this.f2194i;
        this.n = new boolean[i3];
        this.j = i3;
        this.q = i3;
        if (f2187b != null) {
            throw null;
        }
    }

    public void z() throws Exception {
        e eVar = f2187b;
        if (eVar != null) {
            throw null;
        }
        if (!this.l && !this.m) {
            A(this.f2193h);
            return;
        }
        if (eVar != null) {
            throw null;
        }
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= this.p) {
                z = true;
                break;
            } else if (!this.k[i2].f2181f) {
                break;
            } else {
                i2++;
            }
        }
        if (!z) {
            A(this.f2193h);
        } else {
            if (f2187b != null) {
                throw null;
            }
            n();
        }
    }
}
