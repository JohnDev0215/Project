package androidx.constraintlayout.core;

import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: LinearSystem.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f719a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f720b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f721c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f722d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f723e = false;
    private static int f = 1000;
    public static e g;
    public static long h;
    public static long i;
    private a m;
    androidx.constraintlayout.core.b[] p;
    final c w;
    private a z;
    public boolean j = false;
    int k = 0;
    private HashMap<String, SolverVariable> l = null;
    private int n = 32;
    private int o = 32;
    public boolean q = false;
    public boolean r = false;
    private boolean[] s = new boolean[32];
    int t = 1;
    int u = 0;
    private int v = 32;
    private SolverVariable[] x = new SolverVariable[f];
    private int y = 0;

    /* JADX INFO: compiled from: LinearSystem.java */
    interface a {
        SolverVariable a(d dVar, boolean[] zArr);

        void b(SolverVariable solverVariable);

        void c(a aVar);

        void clear();

        SolverVariable getKey();

        boolean isEmpty();
    }

    /* JADX INFO: compiled from: LinearSystem.java */
    class b extends androidx.constraintlayout.core.b {
        public b(c cVar) {
            this.f714e = new i(this, cVar);
        }
    }

    public d() {
        this.p = null;
        this.p = new androidx.constraintlayout.core.b[32];
        C();
        c cVar = new c();
        this.w = cVar;
        this.m = new h(cVar);
        if (f723e) {
            this.z = new b(cVar);
        } else {
            this.z = new androidx.constraintlayout.core.b(cVar);
        }
    }

    private final int B(a aVar, boolean z) {
        if (g != null) {
            throw null;
        }
        for (int i2 = 0; i2 < this.t; i2++) {
            this.s[i2] = false;
        }
        boolean z2 = false;
        int i3 = 0;
        while (!z2) {
            if (g != null) {
                throw null;
            }
            i3++;
            if (i3 >= this.t * 2) {
                return i3;
            }
            if (aVar.getKey() != null) {
                this.s[aVar.getKey().f703e] = true;
            }
            SolverVariable solverVariableA = aVar.a(this, this.s);
            if (solverVariableA != null) {
                boolean[] zArr = this.s;
                int i4 = solverVariableA.f703e;
                if (zArr[i4]) {
                    return i3;
                }
                zArr[i4] = true;
            }
            if (solverVariableA != null) {
                float f2 = Float.MAX_VALUE;
                int i5 = -1;
                for (int i6 = 0; i6 < this.u; i6++) {
                    androidx.constraintlayout.core.b bVar = this.p[i6];
                    if (bVar.f710a.l != SolverVariable.Type.UNRESTRICTED && !bVar.f && bVar.t(solverVariableA)) {
                        float fB = bVar.f714e.b(solverVariableA);
                        if (fB < 0.0f) {
                            float f3 = (-bVar.f711b) / fB;
                            if (f3 < f2) {
                                i5 = i6;
                                f2 = f3;
                            }
                        }
                    }
                }
                if (i5 > -1) {
                    androidx.constraintlayout.core.b bVar2 = this.p[i5];
                    bVar2.f710a.f = -1;
                    if (g != null) {
                        throw null;
                    }
                    bVar2.x(solverVariableA);
                    SolverVariable solverVariable = bVar2.f710a;
                    solverVariable.f = i5;
                    solverVariable.k(this, bVar2);
                } else {
                    continue;
                }
            } else {
                z2 = true;
            }
        }
        return i3;
    }

    private void C() {
        int i2 = 0;
        if (f723e) {
            while (i2 < this.u) {
                androidx.constraintlayout.core.b bVar = this.p[i2];
                if (bVar != null) {
                    this.w.f715a.a(bVar);
                }
                this.p[i2] = null;
                i2++;
            }
            return;
        }
        while (i2 < this.u) {
            androidx.constraintlayout.core.b bVar2 = this.p[i2];
            if (bVar2 != null) {
                this.w.f716b.a(bVar2);
            }
            this.p[i2] = null;
            i2++;
        }
    }

    private SolverVariable a(SolverVariable.Type type, String str) {
        SolverVariable solverVariableB = this.w.f717c.b();
        if (solverVariableB == null) {
            solverVariableB = new SolverVariable(type, str);
            solverVariableB.j(type, str);
        } else {
            solverVariableB.h();
            solverVariableB.j(type, str);
        }
        int i2 = this.y;
        int i3 = f;
        if (i2 >= i3) {
            int i4 = i3 * 2;
            f = i4;
            this.x = (SolverVariable[]) Arrays.copyOf(this.x, i4);
        }
        SolverVariable[] solverVariableArr = this.x;
        int i5 = this.y;
        this.y = i5 + 1;
        solverVariableArr[i5] = solverVariableB;
        return solverVariableB;
    }

    private final void l(androidx.constraintlayout.core.b bVar) {
        int i2;
        if (f721c && bVar.f) {
            bVar.f710a.i(this, bVar.f711b);
        } else {
            androidx.constraintlayout.core.b[] bVarArr = this.p;
            int i3 = this.u;
            bVarArr[i3] = bVar;
            SolverVariable solverVariable = bVar.f710a;
            solverVariable.f = i3;
            this.u = i3 + 1;
            solverVariable.k(this, bVar);
        }
        if (f721c && this.j) {
            int i4 = 0;
            while (i4 < this.u) {
                if (this.p[i4] == null) {
                    System.out.println("WTF");
                }
                androidx.constraintlayout.core.b[] bVarArr2 = this.p;
                if (bVarArr2[i4] != null && bVarArr2[i4].f) {
                    androidx.constraintlayout.core.b bVar2 = bVarArr2[i4];
                    bVar2.f710a.i(this, bVar2.f711b);
                    if (f723e) {
                        this.w.f715a.a(bVar2);
                    } else {
                        this.w.f716b.a(bVar2);
                    }
                    this.p[i4] = null;
                    int i5 = i4 + 1;
                    int i6 = i5;
                    while (true) {
                        i2 = this.u;
                        if (i5 >= i2) {
                            break;
                        }
                        androidx.constraintlayout.core.b[] bVarArr3 = this.p;
                        int i7 = i5 - 1;
                        bVarArr3[i7] = bVarArr3[i5];
                        if (bVarArr3[i7].f710a.f == i5) {
                            bVarArr3[i7].f710a.f = i7;
                        }
                        i6 = i5;
                        i5++;
                    }
                    if (i6 < i2) {
                        this.p[i6] = null;
                    }
                    this.u = i2 - 1;
                    i4--;
                }
                i4++;
            }
            this.j = false;
        }
    }

    private void n() {
        for (int i2 = 0; i2 < this.u; i2++) {
            androidx.constraintlayout.core.b bVar = this.p[i2];
            bVar.f710a.h = bVar.f711b;
        }
    }

    public static androidx.constraintlayout.core.b s(d dVar, SolverVariable solverVariable, SolverVariable solverVariable2, float f2) {
        return dVar.r().j(solverVariable, solverVariable2, f2);
    }

    private int u(a aVar) throws Exception {
        boolean z;
        int i2 = 0;
        while (true) {
            if (i2 >= this.u) {
                z = false;
                break;
            }
            androidx.constraintlayout.core.b[] bVarArr = this.p;
            if (bVarArr[i2].f710a.l != SolverVariable.Type.UNRESTRICTED && bVarArr[i2].f711b < 0.0f) {
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
            if (g != null) {
                throw null;
            }
            i3++;
            float f2 = Float.MAX_VALUE;
            int i4 = -1;
            int i5 = -1;
            int i6 = 0;
            for (int i7 = 0; i7 < this.u; i7++) {
                androidx.constraintlayout.core.b bVar = this.p[i7];
                if (bVar.f710a.l != SolverVariable.Type.UNRESTRICTED && !bVar.f && bVar.f711b < 0.0f) {
                    int i8 = 9;
                    if (f722d) {
                        int iK = bVar.f714e.k();
                        int i9 = 0;
                        while (i9 < iK) {
                            SolverVariable solverVariableG = bVar.f714e.g(i9);
                            float fB = bVar.f714e.b(solverVariableG);
                            if (fB > 0.0f) {
                                int i10 = 0;
                                while (i10 < i8) {
                                    float f3 = solverVariableG.j[i10] / fB;
                                    if ((f3 < f2 && i10 == i6) || i10 > i6) {
                                        i5 = solverVariableG.f703e;
                                        i6 = i10;
                                        i4 = i7;
                                        f2 = f3;
                                    }
                                    i10++;
                                    i8 = 9;
                                }
                            }
                            i9++;
                            i8 = 9;
                        }
                    } else {
                        for (int i11 = 1; i11 < this.t; i11++) {
                            SolverVariable solverVariable = this.w.f718d[i11];
                            float fB2 = bVar.f714e.b(solverVariable);
                            if (fB2 > 0.0f) {
                                for (int i12 = 0; i12 < 9; i12++) {
                                    float f4 = solverVariable.j[i12] / fB2;
                                    if ((f4 < f2 && i12 == i6) || i12 > i6) {
                                        i5 = i11;
                                        i6 = i12;
                                        i4 = i7;
                                        f2 = f4;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (i4 != -1) {
                androidx.constraintlayout.core.b bVar2 = this.p[i4];
                bVar2.f710a.f = -1;
                if (g != null) {
                    throw null;
                }
                bVar2.x(this.w.f718d[i5]);
                SolverVariable solverVariable2 = bVar2.f710a;
                solverVariable2.f = i4;
                solverVariable2.k(this, bVar2);
            } else {
                z2 = true;
            }
            if (i3 > this.t / 2) {
                z2 = true;
            }
        }
        return i3;
    }

    public static e w() {
        return g;
    }

    private void y() {
        int i2 = this.n * 2;
        this.n = i2;
        this.p = (androidx.constraintlayout.core.b[]) Arrays.copyOf(this.p, i2);
        c cVar = this.w;
        cVar.f718d = (SolverVariable[]) Arrays.copyOf(cVar.f718d, this.n);
        int i3 = this.n;
        this.s = new boolean[i3];
        this.o = i3;
        this.v = i3;
        if (g != null) {
            throw null;
        }
    }

    void A(a aVar) throws Exception {
        if (g != null) {
            throw null;
        }
        u(aVar);
        B(aVar, false);
        n();
    }

    public void D() {
        c cVar;
        int i2 = 0;
        while (true) {
            cVar = this.w;
            SolverVariable[] solverVariableArr = cVar.f718d;
            if (i2 >= solverVariableArr.length) {
                break;
            }
            SolverVariable solverVariable = solverVariableArr[i2];
            if (solverVariable != null) {
                solverVariable.h();
            }
            i2++;
        }
        cVar.f717c.c(this.x, this.y);
        this.y = 0;
        Arrays.fill(this.w.f718d, (Object) null);
        HashMap<String, SolverVariable> map = this.l;
        if (map != null) {
            map.clear();
        }
        this.k = 0;
        this.m.clear();
        this.t = 1;
        for (int i3 = 0; i3 < this.u; i3++) {
            androidx.constraintlayout.core.b[] bVarArr = this.p;
            if (bVarArr[i3] != null) {
                bVarArr[i3].f712c = false;
            }
        }
        C();
        this.u = 0;
        if (f723e) {
            this.z = new b(this.w);
        } else {
            this.z = new androidx.constraintlayout.core.b(this.w);
        }
    }

    public void b(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f2, int i2) {
        ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
        SolverVariable solverVariableQ = q(constraintWidget.q(type));
        ConstraintAnchor.Type type2 = ConstraintAnchor.Type.TOP;
        SolverVariable solverVariableQ2 = q(constraintWidget.q(type2));
        ConstraintAnchor.Type type3 = ConstraintAnchor.Type.RIGHT;
        SolverVariable solverVariableQ3 = q(constraintWidget.q(type3));
        ConstraintAnchor.Type type4 = ConstraintAnchor.Type.BOTTOM;
        SolverVariable solverVariableQ4 = q(constraintWidget.q(type4));
        SolverVariable solverVariableQ5 = q(constraintWidget2.q(type));
        SolverVariable solverVariableQ6 = q(constraintWidget2.q(type2));
        SolverVariable solverVariableQ7 = q(constraintWidget2.q(type3));
        SolverVariable solverVariableQ8 = q(constraintWidget2.q(type4));
        androidx.constraintlayout.core.b bVarR = r();
        double d2 = f2;
        double d3 = i2;
        bVarR.q(solverVariableQ2, solverVariableQ4, solverVariableQ6, solverVariableQ8, (float) (Math.sin(d2) * d3));
        d(bVarR);
        androidx.constraintlayout.core.b bVarR2 = r();
        bVarR2.q(solverVariableQ, solverVariableQ3, solverVariableQ5, solverVariableQ7, (float) (Math.cos(d2) * d3));
        d(bVarR2);
    }

    public void c(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, float f2, SolverVariable solverVariable3, SolverVariable solverVariable4, int i3, int i4) {
        androidx.constraintlayout.core.b bVarR = r();
        bVarR.h(solverVariable, solverVariable2, i2, f2, solverVariable3, solverVariable4, i3);
        if (i4 != 8) {
            bVarR.d(this, i4);
        }
        d(bVarR);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d(androidx.constraintlayout.core.b r7) {
        /*
            r6 = this;
            if (r7 != 0) goto L3
            return
        L3:
            androidx.constraintlayout.core.e r0 = androidx.constraintlayout.core.d.g
            r1 = 0
            if (r0 != 0) goto L95
            int r0 = r6.u
            r2 = 1
            int r0 = r0 + r2
            int r3 = r6.v
            if (r0 >= r3) goto L17
            int r0 = r6.t
            int r0 = r0 + r2
            int r3 = r6.o
            if (r0 < r3) goto L1a
        L17:
            r6.y()
        L1a:
            r0 = 0
            boolean r3 = r7.f
            if (r3 != 0) goto L8f
            r7.D(r6)
            boolean r3 = r7.isEmpty()
            if (r3 == 0) goto L29
            return
        L29:
            r7.r()
            boolean r3 = r7.f(r6)
            if (r3 == 0) goto L86
            androidx.constraintlayout.core.SolverVariable r3 = r6.p()
            r7.f710a = r3
            int r4 = r6.u
            r6.l(r7)
            int r5 = r6.u
            int r4 = r4 + r2
            if (r5 != r4) goto L86
            androidx.constraintlayout.core.d$a r0 = r6.z
            r0.c(r7)
            androidx.constraintlayout.core.d$a r0 = r6.z
            r6.B(r0, r2)
            int r0 = r3.f
            r4 = -1
            if (r0 != r4) goto L87
            androidx.constraintlayout.core.SolverVariable r0 = r7.f710a
            if (r0 != r3) goto L64
            androidx.constraintlayout.core.SolverVariable r0 = r7.v(r3)
            if (r0 == 0) goto L64
            androidx.constraintlayout.core.e r3 = androidx.constraintlayout.core.d.g
            if (r3 != 0) goto L63
            r7.x(r0)
            goto L64
        L63:
            throw r1
        L64:
            boolean r0 = r7.f
            if (r0 != 0) goto L6d
            androidx.constraintlayout.core.SolverVariable r0 = r7.f710a
            r0.k(r6, r7)
        L6d:
            boolean r0 = androidx.constraintlayout.core.d.f723e
            if (r0 == 0) goto L79
            androidx.constraintlayout.core.c r0 = r6.w
            androidx.constraintlayout.core.f<androidx.constraintlayout.core.b> r0 = r0.f715a
            r0.a(r7)
            goto L80
        L79:
            androidx.constraintlayout.core.c r0 = r6.w
            androidx.constraintlayout.core.f<androidx.constraintlayout.core.b> r0 = r0.f716b
            r0.a(r7)
        L80:
            int r0 = r6.u
            int r0 = r0 - r2
            r6.u = r0
            goto L87
        L86:
            r2 = r0
        L87:
            boolean r0 = r7.s()
            if (r0 != 0) goto L8e
            return
        L8e:
            r0 = r2
        L8f:
            if (r0 != 0) goto L94
            r6.l(r7)
        L94:
            return
        L95:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.d.d(androidx.constraintlayout.core.b):void");
    }

    public androidx.constraintlayout.core.b e(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        if (f720b && i3 == 8 && solverVariable2.i && solverVariable.f == -1) {
            solverVariable.i(this, solverVariable2.h + i2);
            return null;
        }
        androidx.constraintlayout.core.b bVarR = r();
        bVarR.n(solverVariable, solverVariable2, i2);
        if (i3 != 8) {
            bVarR.d(this, i3);
        }
        d(bVarR);
        return bVarR;
    }

    public void f(SolverVariable solverVariable, int i2) {
        if (f720b && solverVariable.f == -1) {
            float f2 = i2;
            solverVariable.i(this, f2);
            for (int i3 = 0; i3 < this.k + 1; i3++) {
                SolverVariable solverVariable2 = this.w.f718d[i3];
                if (solverVariable2 != null && solverVariable2.p && solverVariable2.q == solverVariable.f703e) {
                    solverVariable2.i(this, solverVariable2.r + f2);
                }
            }
            return;
        }
        int i4 = solverVariable.f;
        if (i4 == -1) {
            androidx.constraintlayout.core.b bVarR = r();
            bVarR.i(solverVariable, i2);
            d(bVarR);
            return;
        }
        androidx.constraintlayout.core.b bVar = this.p[i4];
        if (bVar.f) {
            bVar.f711b = i2;
            return;
        }
        if (bVar.f714e.k() == 0) {
            bVar.f = true;
            bVar.f711b = i2;
        } else {
            androidx.constraintlayout.core.b bVarR2 = r();
            bVarR2.m(solverVariable, i2);
            d(bVarR2);
        }
    }

    public void g(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, boolean z) {
        androidx.constraintlayout.core.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.g = 0;
        bVarR.o(solverVariable, solverVariable2, solverVariableT, i2);
        d(bVarR);
    }

    public void h(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        androidx.constraintlayout.core.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.g = 0;
        bVarR.o(solverVariable, solverVariable2, solverVariableT, i2);
        if (i3 != 8) {
            m(bVarR, (int) (bVarR.f714e.b(solverVariableT) * (-1.0f)), i3);
        }
        d(bVarR);
    }

    public void i(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, boolean z) {
        androidx.constraintlayout.core.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.g = 0;
        bVarR.p(solverVariable, solverVariable2, solverVariableT, i2);
        d(bVarR);
    }

    public void j(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        androidx.constraintlayout.core.b bVarR = r();
        SolverVariable solverVariableT = t();
        solverVariableT.g = 0;
        bVarR.p(solverVariable, solverVariable2, solverVariableT, i2);
        if (i3 != 8) {
            m(bVarR, (int) (bVarR.f714e.b(solverVariableT) * (-1.0f)), i3);
        }
        d(bVarR);
    }

    public void k(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f2, int i2) {
        androidx.constraintlayout.core.b bVarR = r();
        bVarR.k(solverVariable, solverVariable2, solverVariable3, solverVariable4, f2);
        if (i2 != 8) {
            bVarR.d(this, i2);
        }
        d(bVarR);
    }

    void m(androidx.constraintlayout.core.b bVar, int i2, int i3) {
        bVar.e(o(i3, null), i2);
    }

    public SolverVariable o(int i2, String str) {
        if (g != null) {
            throw null;
        }
        if (this.t + 1 >= this.o) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.ERROR, str);
        int i3 = this.k + 1;
        this.k = i3;
        this.t++;
        solverVariableA.f703e = i3;
        solverVariableA.g = i2;
        this.w.f718d[i3] = solverVariableA;
        this.m.b(solverVariableA);
        return solverVariableA;
    }

    public SolverVariable p() {
        if (g != null) {
            throw null;
        }
        if (this.t + 1 >= this.o) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.SLACK, null);
        int i2 = this.k + 1;
        this.k = i2;
        this.t++;
        solverVariableA.f703e = i2;
        this.w.f718d[i2] = solverVariableA;
        return solverVariableA;
    }

    public SolverVariable q(Object obj) {
        SolverVariable solverVariableI = null;
        if (obj == null) {
            return null;
        }
        if (this.t + 1 >= this.o) {
            y();
        }
        if (obj instanceof ConstraintAnchor) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
            solverVariableI = constraintAnchor.i();
            if (solverVariableI == null) {
                constraintAnchor.s(this.w);
                solverVariableI = constraintAnchor.i();
            }
            int i2 = solverVariableI.f703e;
            if (i2 == -1 || i2 > this.k || this.w.f718d[i2] == null) {
                if (i2 != -1) {
                    solverVariableI.h();
                }
                int i3 = this.k + 1;
                this.k = i3;
                this.t++;
                solverVariableI.f703e = i3;
                solverVariableI.l = SolverVariable.Type.UNRESTRICTED;
                this.w.f718d[i3] = solverVariableI;
            }
        }
        return solverVariableI;
    }

    public androidx.constraintlayout.core.b r() {
        androidx.constraintlayout.core.b bVarB;
        if (f723e) {
            bVarB = this.w.f715a.b();
            if (bVarB == null) {
                bVarB = new b(this.w);
                i++;
            } else {
                bVarB.y();
            }
        } else {
            bVarB = this.w.f716b.b();
            if (bVarB == null) {
                bVarB = new androidx.constraintlayout.core.b(this.w);
                h++;
            } else {
                bVarB.y();
            }
        }
        SolverVariable.e();
        return bVarB;
    }

    public SolverVariable t() {
        if (g != null) {
            throw null;
        }
        if (this.t + 1 >= this.o) {
            y();
        }
        SolverVariable solverVariableA = a(SolverVariable.Type.SLACK, null);
        int i2 = this.k + 1;
        this.k = i2;
        this.t++;
        solverVariableA.f703e = i2;
        this.w.f718d[i2] = solverVariableA;
        return solverVariableA;
    }

    public c v() {
        return this.w;
    }

    public int x(Object obj) {
        SolverVariable solverVariableI = ((ConstraintAnchor) obj).i();
        if (solverVariableI != null) {
            return (int) (solverVariableI.h + 0.5f);
        }
        return 0;
    }

    public void z() throws Exception {
        if (g != null) {
            throw null;
        }
        if (this.m.isEmpty()) {
            n();
            return;
        }
        if (!this.q && !this.r) {
            A(this.m);
            return;
        }
        if (g != null) {
            throw null;
        }
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= this.u) {
                z = true;
                break;
            } else if (!this.p[i2].f) {
                break;
            } else {
                i2++;
            }
        }
        if (!z) {
            A(this.m);
        } else {
            if (g != null) {
                throw null;
            }
            n();
        }
    }
}
