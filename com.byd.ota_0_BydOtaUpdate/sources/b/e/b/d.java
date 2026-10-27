package b.e.b;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: LinearSystem.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f1375a = 1000;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static e f1376b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f1377c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static long f1378d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static long f1379e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a f1382h;
    public b.e.b.b[] k;
    public final c r;
    public a u;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1380f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public HashMap<String, SolverVariable> f1381g = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1383i = 32;
    public int j = 32;
    public boolean l = false;
    public boolean m = false;
    public boolean[] n = new boolean[32];
    public int o = 1;
    public int p = 0;
    public int q = 32;
    public SolverVariable[] s = new SolverVariable[f1375a];
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
    public class b extends b.e.b.b {
        public b(c cVar) {
            this.f1369e = new i(this, cVar);
        }
    }

    public d() {
        this.k = null;
        this.k = new b.e.b.b[32];
        C();
        c cVar = new c();
        this.r = cVar;
        this.f1382h = new h(cVar);
        if (f1377c) {
            this.u = new b(cVar);
        } else {
            this.u = new b.e.b.b(cVar);
        }
    }

    public static b.e.b.b s(d dVar, SolverVariable solverVariable, SolverVariable solverVariable2, float f2) {
        return dVar.r().j(solverVariable, solverVariable2, f2);
    }

    public static e w() {
        return f1376b;
    }

    public void A(a aVar) throws Exception {
        if (f1376b != null) {
            throw null;
        }
        u(aVar);
        B(aVar, false);
        n();
    }

    public final int B(a aVar, boolean z) {
        if (f1376b != null) {
            throw null;
        }
        for (int i2 = 0; i2 < this.o; i2++) {
            this.n[i2] = false;
        }
        boolean z2 = false;
        int i3 = 0;
        while (!z2) {
            if (f1376b != null) {
                throw null;
            }
            i3++;
            if (i3 >= this.o * 2) {
                return i3;
            }
            if (aVar.getKey() != null) {
                this.n[aVar.getKey().f322d] = true;
            }
            SolverVariable solverVariableC = aVar.c(this, this.n);
            if (solverVariableC != null) {
                boolean[] zArr = this.n;
                int i4 = solverVariableC.f322d;
                if (zArr[i4]) {
                    return i3;
                }
                zArr[i4] = true;
            }
            if (solverVariableC != null) {
                float f2 = Float.MAX_VALUE;
                int i5 = -1;
                for (int i6 = 0; i6 < this.p; i6++) {
                    b.e.b.b bVar = this.k[i6];
                    if (bVar.f1365a.k != SolverVariable.Type.UNRESTRICTED && !bVar.f1370f && bVar.t(solverVariableC)) {
                        float fG = bVar.f1369e.g(solverVariableC);
                        if (fG < 0.0f) {
                            float f3 = (-bVar.f1366b) / fG;
                            if (f3 < f2) {
                                i5 = i6;
                                f2 = f3;
                            }
                        }
                    }
                }
                if (i5 > -1) {
                    b.e.b.b bVar2 = this.k[i5];
                    bVar2.f1365a.f323e = -1;
                    if (f1376b != null) {
                        throw null;
                    }
                    bVar2.y(solverVariableC);
                    SolverVariable solverVariable = bVar2.f1365a;
                    solverVariable.f323e = i5;
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
        if (f1377c) {
            while (true) {
                b.e.b.b[] bVarArr = this.k;
                if (i2 >= bVarArr.length) {
                    return;
                }
                b.e.b.b bVar = bVarArr[i2];
                if (bVar != null) {
                    this.r.f1371a.a(bVar);
                }
                this.k[i2] = null;
                i2++;
            }
        } else {
            while (true) {
                b.e.b.b[] bVarArr2 = this.k;
                if (i2 >= bVarArr2.length) {
                    return;
                }
                b.e.b.b bVar2 = bVarArr2[i2];
                if (bVar2 != null) {
                    this.r.f1372b.a(bVar2);
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
            SolverVariable[] solverVariableArr = cVar.f1374d;
            if (i2 >= solverVariableArr.length) {
                break;
            }
            SolverVariable solverVariable = solverVariableArr[i2];
            if (solverVariable != null) {
                solverVariable.d();
            }
            i2++;
        }
        cVar.f1373c.c(this.s, this.t);
        this.t = 0;
        Arrays.fill(this.r.f1374d, (Object) null);
        HashMap<String, SolverVariable> map = this.f1381g;
        if (map != null) {
            map.clear();
        }
        this.f1380f = 0;
        this.f1382h.clear();
        this.o = 1;
        for (int i3 = 0; i3 < this.p; i3++) {
            this.k[i3].f1367c = false;
        }
        C();
        this.p = 0;
        if (f1377c) {
            this.u = new b(this.r);
        } else {
            this.u = new b.e.b.b(this.r);
        }
    }

    public final SolverVariable a(SolverVariable.Type type, String str) {
        SolverVariable solverVariableB = this.r.f1373c.b();
        if (solverVariableB == null) {
            solverVariableB = new SolverVariable(type, str);
            solverVariableB.f(type, str);
        } else {
            solverVariableB.d();
            solverVariableB.f(type, str);
        }
        int i2 = this.t;
        int i3 = f1375a;
        if (i2 >= i3) {
            int i4 = i3 * 2;
            f1375a = i4;
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
        b.e.b.b bVarR = r();
        double d2 = f2;
        double d3 = i2;
        bVarR.q(solverVariableQ2, solverVariableQ4, solverVariableQ6, solverVariableQ8, (float) (Math.sin(d2) * d3));
        d(bVarR);
        b.e.b.b bVarR2 = r();
        bVarR2.q(solverVariableQ, solverVariableQ3, solverVariableQ5, solverVariableQ7, (float) (Math.cos(d2) * d3));
        d(bVarR2);
    }

    public void c(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, float f2, SolverVariable solverVariable3, SolverVariable solverVariable4, int i3, int i4) {
        b.e.b.b bVarR = r();
        bVarR.h(solverVariable, solverVariable2, i2, f2, solverVariable3, solverVariable4, i3);
        if (i4 != 8) {
            bVarR.d(this, i4);
        }
        d(bVarR);
    }

    public void d(b.e.b.b bVar) {
        SolverVariable solverVariableW;
        if (bVar == null) {
            return;
        }
        if (f1376b != null) {
            throw null;
        }
        boolean z = true;
        if (this.p + 1 >= this.q || this.o + 1 >= this.j) {
            y();
        }
        boolean z2 = false;
        if (!bVar.f1370f) {
            bVar.D(this);
            if (bVar.u()) {
                return;
            }
            bVar.r();
            if (bVar.f(this)) {
                SolverVariable solverVariableP = p();
                bVar.f1365a = solverVariableP;
                l(bVar);
                this.u.a(bVar);
                B(this.u, true);
                if (solverVariableP.f323e == -1) {
                    if (bVar.f1365a == solverVariableP && (solverVariableW = bVar.w(solverVariableP)) != null) {
                        if (f1376b != null) {
                            throw null;
                        }
                        bVar.y(solverVariableW);
                    }
                    if (!bVar.f1370f) {
                        bVar.f1365a.g(bVar);
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

    public b.e.b.b e(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        if (i3 == 8 && solverVariable2.f326h && solverVariable.f323e == -1) {
            solverVariable.e(this, solverVariable2.f325g + i2);
            return null;
        }
        b.e.b.b bVarR = r();
        bVarR.n(solverVariable, solverVariable2, i2);
        if (i3 != 8) {
            bVarR.d(this, i3);
        }
        d(bVarR);
        return bVarR;
    }

    public void f(SolverVariable solverVariable, int i2) {
        int i3 = solverVariable.f323e;
        if (i3 == -1) {
            solverVariable.e(this, i2);
            return;
        }
        if (i3 == -1) {
            b.e.b.b bVarR = r();
            bVarR.i(solverVariable, i2);
            d(bVarR);
            return;
        }
        b.e.b.b bVar = this.k[i3];
        if (bVar.f1370f) {
            bVar.f1366b = i2;
            return;
        }
        if (bVar.f1369e.k() == 0) {
            bVar.f1370f = true;
            bVar.f1366b = i2;
        } else {
            b.e.b.b bVarR2 = r();
            bVarR2.m(solverVariable, i2);
            d(bVarR2);
        }
    }

    public void g(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, boolean z) {
        b.e.b.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f324f = 0;
        bVarR.o(solverVariable, solverVariable2, solverVariableT, i2);
        d(bVarR);
    }

    public void h(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        b.e.b.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f324f = 0;
        bVarR.o(solverVariable, solverVariable2, solverVariableT, i2);
        if (i3 != 8) {
            m(bVarR, (int) (bVarR.f1369e.g(solverVariableT) * (-1.0f)), i3);
        }
        d(bVarR);
    }

    public void i(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, boolean z) {
        b.e.b.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f324f = 0;
        bVarR.p(solverVariable, solverVariable2, solverVariableT, i2);
        d(bVarR);
    }

    public void j(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        b.e.b.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.f324f = 0;
        bVarR.p(solverVariable, solverVariable2, solverVariableT, i2);
        if (i3 != 8) {
            m(bVarR, (int) (bVarR.f1369e.g(solverVariableT) * (-1.0f)), i3);
        }
        d(bVarR);
    }

    public void k(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f2, int i2) {
        b.e.b.b bVarR = r();
        bVarR.k(solverVariable, solverVariable2, solverVariable3, solverVariable4, f2);
        if (i2 != 8) {
            bVarR.d(this, i2);
        }
        d(bVarR);
    }

    public final void l(b.e.b.b bVar) {
        if (f1377c) {
            b.e.b.b[] bVarArr = this.k;
            int i2 = this.p;
            if (bVarArr[i2] != null) {
                this.r.f1371a.a(bVarArr[i2]);
            }
        } else {
            b.e.b.b[] bVarArr2 = this.k;
            int i3 = this.p;
            if (bVarArr2[i3] != null) {
                this.r.f1372b.a(bVarArr2[i3]);
            }
        }
        b.e.b.b[] bVarArr3 = this.k;
        int i4 = this.p;
        bVarArr3[i4] = bVar;
        SolverVariable solverVariable = bVar.f1365a;
        solverVariable.f323e = i4;
        this.p = i4 + 1;
        solverVariable.g(bVar);
    }

    public void m(b.e.b.b bVar, int i2, int i3) {
        bVar.e(o(i3, null), i2);
    }

    public final void n() {
        for (int i2 = 0; i2 < this.p; i2++) {
            b.e.b.b bVar = this.k[i2];
            bVar.f1365a.f325g = bVar.f1366b;
        }
    }

    public SolverVariable o(int i2, String str) {
        if (f1376b != null) {
            throw null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.ERROR, str);
        int i3 = this.f1380f + 1;
        this.f1380f = i3;
        this.o++;
        solverVariableA.f322d = i3;
        solverVariableA.f324f = i2;
        this.r.f1374d[i3] = solverVariableA;
        this.f1382h.b(solverVariableA);
        return solverVariableA;
    }

    public SolverVariable p() {
        if (f1376b != null) {
            throw null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.SLACK, null);
        int i2 = this.f1380f + 1;
        this.f1380f = i2;
        this.o++;
        solverVariableA.f322d = i2;
        this.r.f1374d[i2] = solverVariableA;
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
            int i2 = solverVariableF.f322d;
            if (i2 == -1 || i2 > this.f1380f || this.r.f1374d[i2] == null) {
                if (i2 != -1) {
                    solverVariableF.d();
                }
                int i3 = this.f1380f + 1;
                this.f1380f = i3;
                this.o++;
                solverVariableF.f322d = i3;
                solverVariableF.k = SolverVariable.Type.UNRESTRICTED;
                this.r.f1374d[i3] = solverVariableF;
            }
        }
        return solverVariableF;
    }

    public b.e.b.b r() {
        b.e.b.b bVarB;
        if (f1377c) {
            bVarB = this.r.f1371a.b();
            if (bVarB == null) {
                bVarB = new b(this.r);
                f1379e++;
            } else {
                bVarB.z();
            }
        } else {
            bVarB = this.r.f1372b.b();
            if (bVarB == null) {
                bVarB = new b.e.b.b(this.r);
                f1378d++;
            } else {
                bVarB.z();
            }
        }
        SolverVariable.b();
        return bVarB;
    }

    public SolverVariable t() {
        if (f1376b != null) {
            throw null;
        }
        if (this.o + 1 >= this.j) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.SLACK, null);
        int i2 = this.f1380f + 1;
        this.f1380f = i2;
        this.o++;
        solverVariableA.f322d = i2;
        this.r.f1374d[i2] = solverVariableA;
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
            b.e.b.b[] bVarArr = this.k;
            if (bVarArr[i2].f1365a.k != SolverVariable.Type.UNRESTRICTED && bVarArr[i2].f1366b < 0.0f) {
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
            if (f1376b != null) {
                throw null;
            }
            i3++;
            float f3 = Float.MAX_VALUE;
            int i4 = -1;
            int i5 = -1;
            int i6 = 0;
            int i7 = 0;
            while (i6 < this.p) {
                b.e.b.b bVar = this.k[i6];
                if (bVar.f1365a.k != SolverVariable.Type.UNRESTRICTED && !bVar.f1370f && bVar.f1366b < f2) {
                    int i8 = 1;
                    while (i8 < this.o) {
                        SolverVariable solverVariable = this.r.f1374d[i8];
                        float fG = bVar.f1369e.g(solverVariable);
                        if (fG > f2) {
                            for (int i9 = 0; i9 < 9; i9++) {
                                float f4 = solverVariable.f327i[i9] / fG;
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
                b.e.b.b bVar2 = this.k[i4];
                bVar2.f1365a.f323e = -1;
                if (f1376b != null) {
                    throw null;
                }
                bVar2.y(this.r.f1374d[i5]);
                SolverVariable solverVariable2 = bVar2.f1365a;
                solverVariable2.f323e = i4;
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
            return (int) (solverVariableF.f325g + 0.5f);
        }
        return 0;
    }

    public final void y() {
        int i2 = this.f1383i * 2;
        this.f1383i = i2;
        this.k = (b.e.b.b[]) Arrays.copyOf(this.k, i2);
        c cVar = this.r;
        cVar.f1374d = (SolverVariable[]) Arrays.copyOf(cVar.f1374d, this.f1383i);
        int i3 = this.f1383i;
        this.n = new boolean[i3];
        this.j = i3;
        this.q = i3;
        if (f1376b != null) {
            throw null;
        }
    }

    public void z() throws Exception {
        e eVar = f1376b;
        if (eVar != null) {
            throw null;
        }
        if (!this.l && !this.m) {
            A(this.f1382h);
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
            } else if (!this.k[i2].f1370f) {
                break;
            } else {
                i2++;
            }
        }
        if (!z) {
            A(this.f1382h);
        } else {
            if (f1376b != null) {
                throw null;
            }
            n();
        }
    }
}
