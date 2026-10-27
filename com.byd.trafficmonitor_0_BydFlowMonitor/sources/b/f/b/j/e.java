package b.f.b.j;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: Flow.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends j {
    public ConstraintWidget[] s1;
    public int V0 = -1;
    public int W0 = -1;
    public int X0 = -1;
    public int Y0 = -1;
    public int Z0 = -1;
    public int a1 = -1;
    public float b1 = 0.5f;
    public float c1 = 0.5f;
    public float d1 = 0.5f;
    public float e1 = 0.5f;
    public float f1 = 0.5f;
    public float g1 = 0.5f;
    public int h1 = 0;
    public int i1 = 0;
    public int j1 = 2;
    public int k1 = 2;
    public int l1 = 0;
    public int m1 = -1;
    public int n1 = 0;
    public ArrayList<a> o1 = new ArrayList<>();
    public ConstraintWidget[] p1 = null;
    public ConstraintWidget[] q1 = null;
    public int[] r1 = null;
    public int t1 = 0;

    /* JADX INFO: compiled from: Flow.java */
    public class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2223a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ConstraintAnchor f2226d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ConstraintAnchor f2227e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public ConstraintAnchor f2228f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ConstraintAnchor f2229g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f2230h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f2231i;
        public int j;
        public int k;
        public int q;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ConstraintWidget f2224b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2225c = 0;
        public int l = 0;
        public int m = 0;
        public int n = 0;
        public int o = 0;
        public int p = 0;

        public a(int i2, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i3) {
            this.f2223a = 0;
            this.f2230h = 0;
            this.f2231i = 0;
            this.j = 0;
            this.k = 0;
            this.q = 0;
            this.f2223a = i2;
            this.f2226d = constraintAnchor;
            this.f2227e = constraintAnchor2;
            this.f2228f = constraintAnchor3;
            this.f2229g = constraintAnchor4;
            this.f2230h = e.this.Q0();
            this.f2231i = e.this.S0();
            this.j = e.this.R0();
            this.k = e.this.P0();
            this.q = i3;
        }

        public void b(ConstraintWidget constraintWidget) {
            if (this.f2223a == 0) {
                int iC1 = e.this.C1(constraintWidget, this.q);
                if (constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.p++;
                    iC1 = 0;
                }
                this.l += iC1 + (constraintWidget.P() != 8 ? e.this.h1 : 0);
                int iB1 = e.this.B1(constraintWidget, this.q);
                if (this.f2224b == null || this.f2225c < iB1) {
                    this.f2224b = constraintWidget;
                    this.f2225c = iB1;
                    this.m = iB1;
                }
            } else {
                int iC12 = e.this.C1(constraintWidget, this.q);
                int iB12 = e.this.B1(constraintWidget, this.q);
                if (constraintWidget.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.p++;
                    iB12 = 0;
                }
                this.m += iB12 + (constraintWidget.P() != 8 ? e.this.i1 : 0);
                if (this.f2224b == null || this.f2225c < iC12) {
                    this.f2224b = constraintWidget;
                    this.f2225c = iC12;
                    this.l = iC12;
                }
            }
            this.o++;
        }

        public void c() {
            this.f2225c = 0;
            this.f2224b = null;
            this.l = 0;
            this.m = 0;
            this.n = 0;
            this.o = 0;
            this.p = 0;
        }

        public void d(boolean z, int i2, boolean z2) {
            ConstraintWidget constraintWidget;
            char c2;
            int i3 = this.o;
            for (int i4 = 0; i4 < i3 && this.n + i4 < e.this.t1; i4++) {
                ConstraintWidget constraintWidget2 = e.this.s1[this.n + i4];
                if (constraintWidget2 != null) {
                    constraintWidget2.a0();
                }
            }
            if (i3 == 0 || this.f2224b == null) {
                return;
            }
            boolean z3 = z2 && i2 == 0;
            int i5 = -1;
            int i6 = -1;
            for (int i7 = 0; i7 < i3; i7++) {
                int i8 = z ? (i3 - 1) - i7 : i7;
                if (this.n + i8 >= e.this.t1) {
                    break;
                }
                if (e.this.s1[this.n + i8].P() == 0) {
                    if (i5 == -1) {
                        i5 = i7;
                    }
                    i6 = i7;
                }
            }
            ConstraintWidget constraintWidget3 = null;
            if (this.f2223a != 0) {
                ConstraintWidget constraintWidget4 = this.f2224b;
                constraintWidget4.k0(e.this.V0);
                int i9 = this.f2230h;
                if (i2 > 0) {
                    i9 += e.this.h1;
                }
                if (z) {
                    constraintWidget4.G.a(this.f2228f, i9);
                    if (z2) {
                        constraintWidget4.E.a(this.f2226d, this.j);
                    }
                    if (i2 > 0) {
                        this.f2228f.f619b.E.a(constraintWidget4.G, 0);
                    }
                } else {
                    constraintWidget4.E.a(this.f2226d, i9);
                    if (z2) {
                        constraintWidget4.G.a(this.f2228f, this.j);
                    }
                    if (i2 > 0) {
                        this.f2226d.f619b.G.a(constraintWidget4.E, 0);
                    }
                }
                int i10 = 0;
                while (i10 < i3 && this.n + i10 < e.this.t1) {
                    ConstraintWidget constraintWidget5 = e.this.s1[this.n + i10];
                    if (i10 == 0) {
                        constraintWidget5.j(constraintWidget5.F, this.f2227e, this.f2231i);
                        int i11 = e.this.W0;
                        float f2 = e.this.c1;
                        if (this.n == 0 && e.this.Y0 != -1) {
                            i11 = e.this.Y0;
                            f2 = e.this.e1;
                        } else if (z2 && e.this.a1 != -1) {
                            i11 = e.this.a1;
                            f2 = e.this.g1;
                        }
                        constraintWidget5.z0(i11);
                        constraintWidget5.y0(f2);
                    }
                    if (i10 == i3 - 1) {
                        constraintWidget5.j(constraintWidget5.H, this.f2229g, this.k);
                    }
                    if (constraintWidget3 != null) {
                        constraintWidget5.F.a(constraintWidget3.H, e.this.i1);
                        if (i10 == i5) {
                            constraintWidget5.F.n(this.f2231i);
                        }
                        constraintWidget3.H.a(constraintWidget5.F, 0);
                        if (i10 == i6 + 1) {
                            constraintWidget3.H.n(this.k);
                        }
                    }
                    if (constraintWidget5 != constraintWidget4) {
                        if (z) {
                            int i12 = e.this.j1;
                            if (i12 == 0) {
                                constraintWidget5.G.a(constraintWidget4.G, 0);
                            } else if (i12 == 1) {
                                constraintWidget5.E.a(constraintWidget4.E, 0);
                            } else if (i12 == 2) {
                                constraintWidget5.E.a(constraintWidget4.E, 0);
                                constraintWidget5.G.a(constraintWidget4.G, 0);
                            }
                        } else {
                            int i13 = e.this.j1;
                            if (i13 == 0) {
                                constraintWidget5.E.a(constraintWidget4.E, 0);
                            } else if (i13 == 1) {
                                constraintWidget5.G.a(constraintWidget4.G, 0);
                            } else if (i13 == 2) {
                                if (z3) {
                                    constraintWidget5.E.a(this.f2226d, this.f2230h);
                                    constraintWidget5.G.a(this.f2228f, this.j);
                                } else {
                                    constraintWidget5.E.a(constraintWidget4.E, 0);
                                    constraintWidget5.G.a(constraintWidget4.G, 0);
                                }
                            }
                        }
                    }
                    i10++;
                    constraintWidget3 = constraintWidget5;
                }
                return;
            }
            ConstraintWidget constraintWidget6 = this.f2224b;
            constraintWidget6.z0(e.this.W0);
            int i14 = this.f2231i;
            if (i2 > 0) {
                i14 += e.this.i1;
            }
            constraintWidget6.F.a(this.f2227e, i14);
            if (z2) {
                constraintWidget6.H.a(this.f2229g, this.k);
            }
            if (i2 > 0) {
                this.f2227e.f619b.H.a(constraintWidget6.F, 0);
            }
            if (e.this.k1 != 3 || constraintWidget6.T()) {
                constraintWidget = constraintWidget6;
            } else {
                for (int i15 = 0; i15 < i3; i15++) {
                    int i16 = z ? (i3 - 1) - i15 : i15;
                    if (this.n + i16 >= e.this.t1) {
                        break;
                    }
                    constraintWidget = e.this.s1[this.n + i16];
                    if (constraintWidget.T()) {
                        break;
                    }
                }
                constraintWidget = constraintWidget6;
            }
            int i17 = 0;
            while (i17 < i3) {
                int i18 = z ? (i3 - 1) - i17 : i17;
                if (this.n + i18 >= e.this.t1) {
                    return;
                }
                ConstraintWidget constraintWidget7 = e.this.s1[this.n + i18];
                if (i17 == 0) {
                    constraintWidget7.j(constraintWidget7.E, this.f2226d, this.f2230h);
                }
                if (i18 == 0) {
                    int i19 = e.this.V0;
                    float f3 = e.this.b1;
                    if (this.n == 0 && e.this.X0 != -1) {
                        i19 = e.this.X0;
                        f3 = e.this.d1;
                    } else if (z2 && e.this.Z0 != -1) {
                        i19 = e.this.Z0;
                        f3 = e.this.f1;
                    }
                    constraintWidget7.k0(i19);
                    constraintWidget7.j0(f3);
                }
                if (i17 == i3 - 1) {
                    constraintWidget7.j(constraintWidget7.G, this.f2228f, this.j);
                }
                if (constraintWidget3 != null) {
                    constraintWidget7.E.a(constraintWidget3.G, e.this.h1);
                    if (i17 == i5) {
                        constraintWidget7.E.n(this.f2230h);
                    }
                    constraintWidget3.G.a(constraintWidget7.E, 0);
                    if (i17 == i6 + 1) {
                        constraintWidget3.G.n(this.j);
                    }
                }
                if (constraintWidget7 != constraintWidget6) {
                    c2 = 3;
                    if (e.this.k1 == 3 && constraintWidget.T() && constraintWidget7 != constraintWidget && constraintWidget7.T()) {
                        constraintWidget7.I.a(constraintWidget.I, 0);
                    } else {
                        int i20 = e.this.k1;
                        if (i20 == 0) {
                            constraintWidget7.F.a(constraintWidget6.F, 0);
                        } else if (i20 == 1) {
                            constraintWidget7.H.a(constraintWidget6.H, 0);
                        } else if (z3) {
                            constraintWidget7.F.a(this.f2227e, this.f2231i);
                            constraintWidget7.H.a(this.f2229g, this.k);
                        } else {
                            constraintWidget7.F.a(constraintWidget6.F, 0);
                            constraintWidget7.H.a(constraintWidget6.H, 0);
                        }
                    }
                } else {
                    c2 = 3;
                }
                i17++;
                constraintWidget3 = constraintWidget7;
            }
        }

        public int e() {
            return this.f2223a == 1 ? this.m - e.this.i1 : this.m;
        }

        public int f() {
            return this.f2223a == 0 ? this.l - e.this.h1 : this.l;
        }

        public void g(int i2) {
            int i3 = this.p;
            if (i3 == 0) {
                return;
            }
            int i4 = this.o;
            int i5 = i2 / i3;
            for (int i6 = 0; i6 < i4 && this.n + i6 < e.this.t1; i6++) {
                ConstraintWidget constraintWidget = e.this.s1[this.n + i6];
                if (this.f2223a == 0) {
                    if (constraintWidget != null && constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.m == 0) {
                        e.this.U0(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i5, constraintWidget.N(), constraintWidget.w());
                    }
                } else if (constraintWidget != null && constraintWidget.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.n == 0) {
                    e.this.U0(constraintWidget, constraintWidget.z(), constraintWidget.Q(), ConstraintWidget.DimensionBehaviour.FIXED, i5);
                }
            }
            h();
        }

        public final void h() {
            this.l = 0;
            this.m = 0;
            this.f2224b = null;
            this.f2225c = 0;
            int i2 = this.o;
            for (int i3 = 0; i3 < i2 && this.n + i3 < e.this.t1; i3++) {
                ConstraintWidget constraintWidget = e.this.s1[this.n + i3];
                if (this.f2223a == 0) {
                    int iQ = constraintWidget.Q();
                    int i4 = e.this.h1;
                    if (constraintWidget.P() == 8) {
                        i4 = 0;
                    }
                    this.l += iQ + i4;
                    int iB1 = e.this.B1(constraintWidget, this.q);
                    if (this.f2224b == null || this.f2225c < iB1) {
                        this.f2224b = constraintWidget;
                        this.f2225c = iB1;
                        this.m = iB1;
                    }
                } else {
                    int iC1 = e.this.C1(constraintWidget, this.q);
                    int iB12 = e.this.B1(constraintWidget, this.q);
                    int i5 = e.this.i1;
                    if (constraintWidget.P() == 8) {
                        i5 = 0;
                    }
                    this.m += iB12 + i5;
                    if (this.f2224b == null || this.f2225c < iC1) {
                        this.f2224b = constraintWidget;
                        this.f2225c = iC1;
                        this.l = iC1;
                    }
                }
            }
        }

        public void i(int i2) {
            this.n = i2;
        }

        public void j(int i2, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i3, int i4, int i5, int i6, int i7) {
            this.f2223a = i2;
            this.f2226d = constraintAnchor;
            this.f2227e = constraintAnchor2;
            this.f2228f = constraintAnchor3;
            this.f2229g = constraintAnchor4;
            this.f2230h = i3;
            this.f2231i = i4;
            this.j = i5;
            this.k = i6;
            this.q = i7;
        }
    }

    public final void A1(boolean z) {
        ConstraintWidget constraintWidget;
        if (this.r1 == null || this.q1 == null || this.p1 == null) {
            return;
        }
        for (int i2 = 0; i2 < this.t1; i2++) {
            this.s1[i2].a0();
        }
        int[] iArr = this.r1;
        int i3 = iArr[0];
        int i4 = iArr[1];
        ConstraintWidget constraintWidget2 = null;
        for (int i5 = 0; i5 < i3; i5++) {
            ConstraintWidget constraintWidget3 = this.q1[z ? (i3 - i5) - 1 : i5];
            if (constraintWidget3 != null && constraintWidget3.P() != 8) {
                if (i5 == 0) {
                    constraintWidget3.j(constraintWidget3.E, this.E, Q0());
                    constraintWidget3.k0(this.V0);
                    constraintWidget3.j0(this.b1);
                }
                if (i5 == i3 - 1) {
                    constraintWidget3.j(constraintWidget3.G, this.G, R0());
                }
                if (i5 > 0) {
                    constraintWidget3.j(constraintWidget3.E, constraintWidget2.G, this.h1);
                    constraintWidget2.j(constraintWidget2.G, constraintWidget3.E, 0);
                }
                constraintWidget2 = constraintWidget3;
            }
        }
        for (int i6 = 0; i6 < i4; i6++) {
            ConstraintWidget constraintWidget4 = this.p1[i6];
            if (constraintWidget4 != null && constraintWidget4.P() != 8) {
                if (i6 == 0) {
                    constraintWidget4.j(constraintWidget4.F, this.F, S0());
                    constraintWidget4.z0(this.W0);
                    constraintWidget4.y0(this.c1);
                }
                if (i6 == i4 - 1) {
                    constraintWidget4.j(constraintWidget4.H, this.H, P0());
                }
                if (i6 > 0) {
                    constraintWidget4.j(constraintWidget4.F, constraintWidget2.H, this.i1);
                    constraintWidget2.j(constraintWidget2.H, constraintWidget4.F, 0);
                }
                constraintWidget2 = constraintWidget4;
            }
        }
        for (int i7 = 0; i7 < i3; i7++) {
            for (int i8 = 0; i8 < i4; i8++) {
                int i9 = (i8 * i3) + i7;
                if (this.n1 == 1) {
                    i9 = (i7 * i4) + i8;
                }
                ConstraintWidget[] constraintWidgetArr = this.s1;
                if (i9 < constraintWidgetArr.length && (constraintWidget = constraintWidgetArr[i9]) != null && constraintWidget.P() != 8) {
                    ConstraintWidget constraintWidget5 = this.q1[i7];
                    ConstraintWidget constraintWidget6 = this.p1[i8];
                    if (constraintWidget != constraintWidget5) {
                        constraintWidget.j(constraintWidget.E, constraintWidget5.E, 0);
                        constraintWidget.j(constraintWidget.G, constraintWidget5.G, 0);
                    }
                    if (constraintWidget != constraintWidget6) {
                        constraintWidget.j(constraintWidget.F, constraintWidget6.F, 0);
                        constraintWidget.j(constraintWidget.H, constraintWidget6.H, 0);
                    }
                }
            }
        }
    }

    public final int B1(ConstraintWidget constraintWidget, int i2) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i3 = constraintWidget.n;
            if (i3 == 0) {
                return 0;
            }
            if (i3 == 2) {
                int i4 = (int) (constraintWidget.u * i2);
                if (i4 != constraintWidget.w()) {
                    U0(constraintWidget, constraintWidget.z(), constraintWidget.Q(), ConstraintWidget.DimensionBehaviour.FIXED, i4);
                }
                return i4;
            }
            if (i3 == 1) {
                return constraintWidget.w();
            }
            if (i3 == 3) {
                return (int) ((constraintWidget.Q() * constraintWidget.T) + 0.5f);
            }
        }
        return constraintWidget.w();
    }

    public final int C1(ConstraintWidget constraintWidget, int i2) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i3 = constraintWidget.m;
            if (i3 == 0) {
                return 0;
            }
            if (i3 == 2) {
                int i4 = (int) (constraintWidget.r * i2);
                if (i4 != constraintWidget.Q()) {
                    U0(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i4, constraintWidget.N(), constraintWidget.w());
                }
                return i4;
            }
            if (i3 == 1) {
                return constraintWidget.Q();
            }
            if (i3 == 3) {
                return (int) ((constraintWidget.w() * constraintWidget.T) + 0.5f);
            }
        }
        return constraintWidget.Q();
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0068  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:105:0x011b -> B:42:0x0063). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:106:0x011d -> B:42:0x0063). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:108:0x0123 -> B:42:0x0063). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:109:0x0125 -> B:42:0x0063). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void D1(androidx.constraintlayout.solver.widgets.ConstraintWidget[] r17, int r18, int r19, int r20, int[] r21) {
        /*
            Method dump skipped, instruction units count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.f.b.j.e.D1(androidx.constraintlayout.solver.widgets.ConstraintWidget[], int, int, int, int[]):void");
    }

    public final void E1(ConstraintWidget[] constraintWidgetArr, int i2, int i3, int i4, int[] iArr) {
        int i5;
        int i6;
        int i7;
        ConstraintAnchor constraintAnchor;
        int iR0;
        ConstraintAnchor constraintAnchor2;
        int iP0;
        int i8;
        if (i2 == 0) {
            return;
        }
        this.o1.clear();
        a aVar = new a(i3, this.E, this.F, this.G, this.H, i4);
        this.o1.add(aVar);
        if (i3 == 0) {
            i5 = 0;
            int i9 = 0;
            int i10 = 0;
            while (i10 < i2) {
                ConstraintWidget constraintWidget = constraintWidgetArr[i10];
                int iC1 = C1(constraintWidget, i4);
                if (constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i5++;
                }
                int i11 = i5;
                boolean z = (i9 == i4 || (this.h1 + i9) + iC1 > i4) && aVar.f2224b != null;
                if (!z && i10 > 0 && (i8 = this.m1) > 0 && i10 % i8 == 0) {
                    z = true;
                }
                if (z) {
                    aVar = new a(i3, this.E, this.F, this.G, this.H, i4);
                    aVar.i(i10);
                    this.o1.add(aVar);
                } else {
                    if (i10 > 0) {
                        i9 += this.h1 + iC1;
                    }
                    aVar.b(constraintWidget);
                    i10++;
                    i5 = i11;
                }
                i9 = iC1;
                aVar.b(constraintWidget);
                i10++;
                i5 = i11;
            }
        } else {
            i5 = 0;
            int i12 = 0;
            int i13 = 0;
            while (i13 < i2) {
                ConstraintWidget constraintWidget2 = constraintWidgetArr[i13];
                int iB1 = B1(constraintWidget2, i4);
                if (constraintWidget2.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i5++;
                }
                int i14 = i5;
                boolean z2 = (i12 == i4 || (this.i1 + i12) + iB1 > i4) && aVar.f2224b != null;
                if (!z2 && i13 > 0 && (i6 = this.m1) > 0 && i13 % i6 == 0) {
                    z2 = true;
                }
                if (z2) {
                    aVar = new a(i3, this.E, this.F, this.G, this.H, i4);
                    aVar.i(i13);
                    this.o1.add(aVar);
                } else {
                    if (i13 > 0) {
                        i12 += this.i1 + iB1;
                    }
                    aVar.b(constraintWidget2);
                    i13++;
                    i5 = i14;
                }
                i12 = iB1;
                aVar.b(constraintWidget2);
                i13++;
                i5 = i14;
            }
        }
        int size = this.o1.size();
        ConstraintAnchor constraintAnchor3 = this.E;
        ConstraintAnchor constraintAnchor4 = this.F;
        ConstraintAnchor constraintAnchor5 = this.G;
        ConstraintAnchor constraintAnchor6 = this.H;
        int iQ0 = Q0();
        int iS0 = S0();
        int iR02 = R0();
        int iP02 = P0();
        ConstraintWidget.DimensionBehaviour dimensionBehaviourZ = z();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        boolean z3 = dimensionBehaviourZ == dimensionBehaviour || N() == dimensionBehaviour;
        if (i5 > 0 && z3) {
            for (int i15 = 0; i15 < size; i15++) {
                a aVar2 = this.o1.get(i15);
                if (i3 == 0) {
                    aVar2.g(i4 - aVar2.f());
                } else {
                    aVar2.g(i4 - aVar2.e());
                }
            }
        }
        int i16 = iS0;
        int i17 = iR02;
        int iE = 0;
        int iF = 0;
        int i18 = 0;
        int i19 = iQ0;
        ConstraintAnchor constraintAnchor7 = constraintAnchor4;
        ConstraintAnchor constraintAnchor8 = constraintAnchor3;
        int i20 = iP02;
        while (i18 < size) {
            a aVar3 = this.o1.get(i18);
            if (i3 == 0) {
                if (i18 < size - 1) {
                    constraintAnchor2 = this.o1.get(i18 + 1).f2224b.F;
                    iP0 = 0;
                } else {
                    constraintAnchor2 = this.H;
                    iP0 = P0();
                }
                ConstraintAnchor constraintAnchor9 = aVar3.f2224b.H;
                ConstraintAnchor constraintAnchor10 = constraintAnchor8;
                ConstraintAnchor constraintAnchor11 = constraintAnchor8;
                int i21 = iE;
                ConstraintAnchor constraintAnchor12 = constraintAnchor7;
                int i22 = iF;
                ConstraintAnchor constraintAnchor13 = constraintAnchor5;
                ConstraintAnchor constraintAnchor14 = constraintAnchor5;
                i7 = i18;
                aVar3.j(i3, constraintAnchor10, constraintAnchor12, constraintAnchor13, constraintAnchor2, i19, i16, i17, iP0, i4);
                int iMax = Math.max(i22, aVar3.f());
                iE = i21 + aVar3.e();
                if (i7 > 0) {
                    iE += this.i1;
                }
                constraintAnchor8 = constraintAnchor11;
                iF = iMax;
                i16 = 0;
                constraintAnchor7 = constraintAnchor9;
                constraintAnchor = constraintAnchor14;
                int i23 = iP0;
                constraintAnchor6 = constraintAnchor2;
                i20 = i23;
            } else {
                ConstraintAnchor constraintAnchor15 = constraintAnchor8;
                int i24 = iE;
                int i25 = iF;
                i7 = i18;
                if (i7 < size - 1) {
                    constraintAnchor = this.o1.get(i7 + 1).f2224b.E;
                    iR0 = 0;
                } else {
                    constraintAnchor = this.G;
                    iR0 = R0();
                }
                ConstraintAnchor constraintAnchor16 = aVar3.f2224b.G;
                aVar3.j(i3, constraintAnchor15, constraintAnchor7, constraintAnchor, constraintAnchor6, i19, i16, iR0, i20, i4);
                iF = i25 + aVar3.f();
                int iMax2 = Math.max(i24, aVar3.e());
                if (i7 > 0) {
                    iF += this.h1;
                }
                iE = iMax2;
                i19 = 0;
                i17 = iR0;
                constraintAnchor8 = constraintAnchor16;
            }
            i18 = i7 + 1;
            constraintAnchor5 = constraintAnchor;
        }
        iArr[0] = iF;
        iArr[1] = iE;
    }

    public final void F1(ConstraintWidget[] constraintWidgetArr, int i2, int i3, int i4, int[] iArr) {
        a aVar;
        if (i2 == 0) {
            return;
        }
        if (this.o1.size() == 0) {
            aVar = new a(i3, this.E, this.F, this.G, this.H, i4);
            this.o1.add(aVar);
        } else {
            a aVar2 = this.o1.get(0);
            aVar2.c();
            aVar = aVar2;
            aVar.j(i3, this.E, this.F, this.G, this.H, Q0(), S0(), R0(), P0(), i4);
        }
        for (int i5 = 0; i5 < i2; i5++) {
            aVar.b(constraintWidgetArr[i5]);
        }
        iArr[0] = aVar.f();
        iArr[1] = aVar.e();
    }

    public void G1(float f2) {
        this.d1 = f2;
    }

    public void H1(int i2) {
        this.X0 = i2;
    }

    public void I1(float f2) {
        this.e1 = f2;
    }

    public void J1(int i2) {
        this.Y0 = i2;
    }

    public void K1(int i2) {
        this.j1 = i2;
    }

    public void L1(float f2) {
        this.b1 = f2;
    }

    public void M1(int i2) {
        this.h1 = i2;
    }

    public void N1(int i2) {
        this.V0 = i2;
    }

    public void O1(float f2) {
        this.f1 = f2;
    }

    public void P1(int i2) {
        this.Z0 = i2;
    }

    public void Q1(float f2) {
        this.g1 = f2;
    }

    public void R1(int i2) {
        this.a1 = i2;
    }

    public void S1(int i2) {
        this.m1 = i2;
    }

    @Override // b.f.b.j.j
    public void T0(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int[] iArr;
        boolean z;
        if (this.H0 > 0 && !V0()) {
            Y0(0, 0);
            X0(false);
            return;
        }
        int iQ0 = Q0();
        int iR0 = R0();
        int iS0 = S0();
        int iP0 = P0();
        int[] iArr2 = new int[2];
        int i8 = (i3 - iQ0) - iR0;
        int i9 = this.n1;
        if (i9 == 1) {
            i8 = (i5 - iS0) - iP0;
        }
        int i10 = i8;
        if (i9 == 0) {
            if (this.V0 == -1) {
                this.V0 = 0;
            }
            if (this.W0 == -1) {
                this.W0 = 0;
            }
        } else {
            if (this.V0 == -1) {
                this.V0 = 0;
            }
            if (this.W0 == -1) {
                this.W0 = 0;
            }
        }
        ConstraintWidget[] constraintWidgetArr = this.G0;
        int i11 = 0;
        int i12 = 0;
        while (true) {
            i6 = this.H0;
            if (i11 >= i6) {
                break;
            }
            if (this.G0[i11].P() == 8) {
                i12++;
            }
            i11++;
        }
        if (i12 > 0) {
            constraintWidgetArr = new ConstraintWidget[i6 - i12];
            int i13 = 0;
            for (int i14 = 0; i14 < this.H0; i14++) {
                ConstraintWidget constraintWidget = this.G0[i14];
                if (constraintWidget.P() != 8) {
                    constraintWidgetArr[i13] = constraintWidget;
                    i13++;
                }
            }
            i7 = i13;
        } else {
            i7 = i6;
        }
        this.s1 = constraintWidgetArr;
        this.t1 = i7;
        int i15 = this.l1;
        if (i15 == 0) {
            iArr = iArr2;
            z = true;
            F1(constraintWidgetArr, i7, this.n1, i10, iArr2);
        } else if (i15 == 1) {
            z = true;
            iArr = iArr2;
            E1(constraintWidgetArr, i7, this.n1, i10, iArr2);
        } else if (i15 != 2) {
            z = true;
            iArr = iArr2;
        } else {
            z = true;
            iArr = iArr2;
            D1(constraintWidgetArr, i7, this.n1, i10, iArr2);
        }
        int iMin = iArr[0] + iQ0 + iR0;
        int iMin2 = iArr[z ? 1 : 0] + iS0 + iP0;
        if (i2 == 1073741824) {
            iMin = i3;
        } else if (i2 == Integer.MIN_VALUE) {
            iMin = Math.min(iMin, i3);
        } else if (i2 != 0) {
            iMin = 0;
        }
        if (i4 == 1073741824) {
            iMin2 = i5;
        } else if (i4 == Integer.MIN_VALUE) {
            iMin2 = Math.min(iMin2, i5);
        } else if (i4 != 0) {
            iMin2 = 0;
        }
        Y0(iMin, iMin2);
        F0(iMin);
        i0(iMin2);
        if (this.H0 <= 0) {
            z = false;
        }
        X0(z);
    }

    public void T1(int i2) {
        this.n1 = i2;
    }

    public void U1(int i2) {
        this.k1 = i2;
    }

    public void V1(float f2) {
        this.c1 = f2;
    }

    public void W1(int i2) {
        this.i1 = i2;
    }

    public void X1(int i2) {
        this.W0 = i2;
    }

    public void Y1(int i2) {
        this.l1 = i2;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void f(b.f.b.d dVar) {
        super.f(dVar);
        boolean zC1 = H() != null ? ((d) H()).c1() : false;
        int i2 = this.l1;
        if (i2 != 0) {
            if (i2 == 1) {
                int size = this.o1.size();
                int i3 = 0;
                while (i3 < size) {
                    this.o1.get(i3).d(zC1, i3, i3 == size + (-1));
                    i3++;
                }
            } else if (i2 == 2) {
                A1(zC1);
            }
        } else if (this.o1.size() > 0) {
            this.o1.get(0).d(zC1, 0, true);
        }
        X0(false);
    }

    @Override // b.f.b.j.h, androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void l(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.l(constraintWidget, map);
        e eVar = (e) constraintWidget;
        this.V0 = eVar.V0;
        this.W0 = eVar.W0;
        this.X0 = eVar.X0;
        this.Y0 = eVar.Y0;
        this.Z0 = eVar.Z0;
        this.a1 = eVar.a1;
        this.b1 = eVar.b1;
        this.c1 = eVar.c1;
        this.d1 = eVar.d1;
        this.e1 = eVar.e1;
        this.f1 = eVar.f1;
        this.g1 = eVar.g1;
        this.h1 = eVar.h1;
        this.i1 = eVar.i1;
        this.j1 = eVar.j1;
        this.k1 = eVar.k1;
        this.l1 = eVar.l1;
        this.m1 = eVar.m1;
        this.n1 = eVar.n1;
    }
}
