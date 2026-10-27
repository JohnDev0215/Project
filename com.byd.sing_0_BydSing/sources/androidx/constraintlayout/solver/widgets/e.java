package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: Flow.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends j {
    private ConstraintWidget[] s1;
    private int V0 = -1;
    private int W0 = -1;
    private int X0 = -1;
    private int Y0 = -1;
    private int Z0 = -1;
    private int a1 = -1;
    private float b1 = 0.5f;
    private float c1 = 0.5f;
    private float d1 = 0.5f;
    private float e1 = 0.5f;
    private float f1 = 0.5f;
    private float g1 = 0.5f;
    private int h1 = 0;
    private int i1 = 0;
    private int j1 = 2;
    private int k1 = 2;
    private int l1 = 0;
    private int m1 = -1;
    private int n1 = 0;
    private ArrayList<a> o1 = new ArrayList<>();
    private ConstraintWidget[] p1 = null;
    private ConstraintWidget[] q1 = null;
    private int[] r1 = null;
    private int t1 = 0;

    /* JADX INFO: compiled from: Flow.java */
    private class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f1214a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private ConstraintAnchor f1217d;
        private ConstraintAnchor e;
        private ConstraintAnchor f;
        private ConstraintAnchor g;
        private int h;
        private int i;
        private int j;
        private int k;
        private int q;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private ConstraintWidget f1215b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1216c = 0;
        private int l = 0;
        private int m = 0;
        private int n = 0;
        private int o = 0;
        private int p = 0;

        public a(int i, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i2) {
            this.f1214a = 0;
            this.h = 0;
            this.i = 0;
            this.j = 0;
            this.k = 0;
            this.q = 0;
            this.f1214a = i;
            this.f1217d = constraintAnchor;
            this.e = constraintAnchor2;
            this.f = constraintAnchor3;
            this.g = constraintAnchor4;
            this.h = e.this.Q0();
            this.i = e.this.S0();
            this.j = e.this.R0();
            this.k = e.this.P0();
            this.q = i2;
        }

        private void h() {
            this.l = 0;
            this.m = 0;
            this.f1215b = null;
            this.f1216c = 0;
            int i = this.o;
            for (int i2 = 0; i2 < i && this.n + i2 < e.this.t1; i2++) {
                ConstraintWidget constraintWidget = e.this.s1[this.n + i2];
                if (this.f1214a == 0) {
                    int iQ = constraintWidget.Q();
                    int i3 = e.this.h1;
                    if (constraintWidget.P() == 8) {
                        i3 = 0;
                    }
                    this.l += iQ + i3;
                    int iB1 = e.this.B1(constraintWidget, this.q);
                    if (this.f1215b == null || this.f1216c < iB1) {
                        this.f1215b = constraintWidget;
                        this.f1216c = iB1;
                        this.m = iB1;
                    }
                } else {
                    int iC1 = e.this.C1(constraintWidget, this.q);
                    int iB12 = e.this.B1(constraintWidget, this.q);
                    int i4 = e.this.i1;
                    if (constraintWidget.P() == 8) {
                        i4 = 0;
                    }
                    this.m += iB12 + i4;
                    if (this.f1215b == null || this.f1216c < iC1) {
                        this.f1215b = constraintWidget;
                        this.f1216c = iC1;
                        this.l = iC1;
                    }
                }
            }
        }

        public void b(ConstraintWidget constraintWidget) {
            if (this.f1214a == 0) {
                int iC1 = e.this.C1(constraintWidget, this.q);
                if (constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.p++;
                    iC1 = 0;
                }
                this.l += iC1 + (constraintWidget.P() != 8 ? e.this.h1 : 0);
                int iB1 = e.this.B1(constraintWidget, this.q);
                if (this.f1215b == null || this.f1216c < iB1) {
                    this.f1215b = constraintWidget;
                    this.f1216c = iB1;
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
                if (this.f1215b == null || this.f1216c < iC12) {
                    this.f1215b = constraintWidget;
                    this.f1216c = iC12;
                    this.l = iC12;
                }
            }
            this.o++;
        }

        public void c() {
            this.f1216c = 0;
            this.f1215b = null;
            this.l = 0;
            this.m = 0;
            this.n = 0;
            this.o = 0;
            this.p = 0;
        }

        public void d(boolean z, int i, boolean z2) {
            ConstraintWidget constraintWidget;
            char c2;
            int i2 = this.o;
            for (int i3 = 0; i3 < i2 && this.n + i3 < e.this.t1; i3++) {
                ConstraintWidget constraintWidget2 = e.this.s1[this.n + i3];
                if (constraintWidget2 != null) {
                    constraintWidget2.a0();
                }
            }
            if (i2 == 0 || this.f1215b == null) {
                return;
            }
            boolean z3 = z2 && i == 0;
            int i4 = -1;
            int i5 = -1;
            for (int i6 = 0; i6 < i2; i6++) {
                int i7 = z ? (i2 - 1) - i6 : i6;
                if (this.n + i7 >= e.this.t1) {
                    break;
                }
                if (e.this.s1[this.n + i7].P() == 0) {
                    if (i4 == -1) {
                        i4 = i6;
                    }
                    i5 = i6;
                }
            }
            ConstraintWidget constraintWidget3 = null;
            if (this.f1214a != 0) {
                ConstraintWidget constraintWidget4 = this.f1215b;
                constraintWidget4.k0(e.this.V0);
                int i8 = this.h;
                if (i > 0) {
                    i8 += e.this.h1;
                }
                if (z) {
                    constraintWidget4.G.a(this.f, i8);
                    if (z2) {
                        constraintWidget4.E.a(this.f1217d, this.j);
                    }
                    if (i > 0) {
                        this.f.f1170b.E.a(constraintWidget4.G, 0);
                    }
                } else {
                    constraintWidget4.E.a(this.f1217d, i8);
                    if (z2) {
                        constraintWidget4.G.a(this.f, this.j);
                    }
                    if (i > 0) {
                        this.f1217d.f1170b.G.a(constraintWidget4.E, 0);
                    }
                }
                int i9 = 0;
                while (i9 < i2 && this.n + i9 < e.this.t1) {
                    ConstraintWidget constraintWidget5 = e.this.s1[this.n + i9];
                    if (i9 == 0) {
                        constraintWidget5.j(constraintWidget5.F, this.e, this.i);
                        int i10 = e.this.W0;
                        float f = e.this.c1;
                        if (this.n == 0 && e.this.Y0 != -1) {
                            i10 = e.this.Y0;
                            f = e.this.e1;
                        } else if (z2 && e.this.a1 != -1) {
                            i10 = e.this.a1;
                            f = e.this.g1;
                        }
                        constraintWidget5.z0(i10);
                        constraintWidget5.y0(f);
                    }
                    if (i9 == i2 - 1) {
                        constraintWidget5.j(constraintWidget5.H, this.g, this.k);
                    }
                    if (constraintWidget3 != null) {
                        constraintWidget5.F.a(constraintWidget3.H, e.this.i1);
                        if (i9 == i4) {
                            constraintWidget5.F.n(this.i);
                        }
                        constraintWidget3.H.a(constraintWidget5.F, 0);
                        if (i9 == i5 + 1) {
                            constraintWidget3.H.n(this.k);
                        }
                    }
                    if (constraintWidget5 != constraintWidget4) {
                        if (z) {
                            int i11 = e.this.j1;
                            if (i11 == 0) {
                                constraintWidget5.G.a(constraintWidget4.G, 0);
                            } else if (i11 == 1) {
                                constraintWidget5.E.a(constraintWidget4.E, 0);
                            } else if (i11 == 2) {
                                constraintWidget5.E.a(constraintWidget4.E, 0);
                                constraintWidget5.G.a(constraintWidget4.G, 0);
                            }
                        } else {
                            int i12 = e.this.j1;
                            if (i12 == 0) {
                                constraintWidget5.E.a(constraintWidget4.E, 0);
                            } else if (i12 == 1) {
                                constraintWidget5.G.a(constraintWidget4.G, 0);
                            } else if (i12 == 2) {
                                if (z3) {
                                    constraintWidget5.E.a(this.f1217d, this.h);
                                    constraintWidget5.G.a(this.f, this.j);
                                } else {
                                    constraintWidget5.E.a(constraintWidget4.E, 0);
                                    constraintWidget5.G.a(constraintWidget4.G, 0);
                                }
                            }
                        }
                    }
                    i9++;
                    constraintWidget3 = constraintWidget5;
                }
                return;
            }
            ConstraintWidget constraintWidget6 = this.f1215b;
            constraintWidget6.z0(e.this.W0);
            int i13 = this.i;
            if (i > 0) {
                i13 += e.this.i1;
            }
            constraintWidget6.F.a(this.e, i13);
            if (z2) {
                constraintWidget6.H.a(this.g, this.k);
            }
            if (i > 0) {
                this.e.f1170b.H.a(constraintWidget6.F, 0);
            }
            if (e.this.k1 != 3 || constraintWidget6.T()) {
                constraintWidget = constraintWidget6;
            } else {
                for (int i14 = 0; i14 < i2; i14++) {
                    int i15 = z ? (i2 - 1) - i14 : i14;
                    if (this.n + i15 >= e.this.t1) {
                        break;
                    }
                    constraintWidget = e.this.s1[this.n + i15];
                    if (constraintWidget.T()) {
                        break;
                    }
                }
                constraintWidget = constraintWidget6;
            }
            int i16 = 0;
            while (i16 < i2) {
                int i17 = z ? (i2 - 1) - i16 : i16;
                if (this.n + i17 >= e.this.t1) {
                    return;
                }
                ConstraintWidget constraintWidget7 = e.this.s1[this.n + i17];
                if (i16 == 0) {
                    constraintWidget7.j(constraintWidget7.E, this.f1217d, this.h);
                }
                if (i17 == 0) {
                    int i18 = e.this.V0;
                    float f2 = e.this.b1;
                    if (this.n == 0 && e.this.X0 != -1) {
                        i18 = e.this.X0;
                        f2 = e.this.d1;
                    } else if (z2 && e.this.Z0 != -1) {
                        i18 = e.this.Z0;
                        f2 = e.this.f1;
                    }
                    constraintWidget7.k0(i18);
                    constraintWidget7.j0(f2);
                }
                if (i16 == i2 - 1) {
                    constraintWidget7.j(constraintWidget7.G, this.f, this.j);
                }
                if (constraintWidget3 != null) {
                    constraintWidget7.E.a(constraintWidget3.G, e.this.h1);
                    if (i16 == i4) {
                        constraintWidget7.E.n(this.h);
                    }
                    constraintWidget3.G.a(constraintWidget7.E, 0);
                    if (i16 == i5 + 1) {
                        constraintWidget3.G.n(this.j);
                    }
                }
                if (constraintWidget7 != constraintWidget6) {
                    c2 = 3;
                    if (e.this.k1 == 3 && constraintWidget.T() && constraintWidget7 != constraintWidget && constraintWidget7.T()) {
                        constraintWidget7.I.a(constraintWidget.I, 0);
                    } else {
                        int i19 = e.this.k1;
                        if (i19 == 0) {
                            constraintWidget7.F.a(constraintWidget6.F, 0);
                        } else if (i19 == 1) {
                            constraintWidget7.H.a(constraintWidget6.H, 0);
                        } else if (z3) {
                            constraintWidget7.F.a(this.e, this.i);
                            constraintWidget7.H.a(this.g, this.k);
                        } else {
                            constraintWidget7.F.a(constraintWidget6.F, 0);
                            constraintWidget7.H.a(constraintWidget6.H, 0);
                        }
                    }
                } else {
                    c2 = 3;
                }
                i16++;
                constraintWidget3 = constraintWidget7;
            }
        }

        public int e() {
            return this.f1214a == 1 ? this.m - e.this.i1 : this.m;
        }

        public int f() {
            return this.f1214a == 0 ? this.l - e.this.h1 : this.l;
        }

        public void g(int i) {
            int i2 = this.p;
            if (i2 == 0) {
                return;
            }
            int i3 = this.o;
            int i4 = i / i2;
            for (int i5 = 0; i5 < i3 && this.n + i5 < e.this.t1; i5++) {
                ConstraintWidget constraintWidget = e.this.s1[this.n + i5];
                if (this.f1214a == 0) {
                    if (constraintWidget != null && constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.m == 0) {
                        e.this.U0(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i4, constraintWidget.N(), constraintWidget.w());
                    }
                } else if (constraintWidget != null && constraintWidget.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.n == 0) {
                    e.this.U0(constraintWidget, constraintWidget.z(), constraintWidget.Q(), ConstraintWidget.DimensionBehaviour.FIXED, i4);
                }
            }
            h();
        }

        public void i(int i) {
            this.n = i;
        }

        public void j(int i, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i2, int i3, int i4, int i5, int i6) {
            this.f1214a = i;
            this.f1217d = constraintAnchor;
            this.e = constraintAnchor2;
            this.f = constraintAnchor3;
            this.g = constraintAnchor4;
            this.h = i2;
            this.i = i3;
            this.j = i4;
            this.k = i5;
            this.q = i6;
        }
    }

    private void A1(boolean z) {
        ConstraintWidget constraintWidget;
        if (this.r1 == null || this.q1 == null || this.p1 == null) {
            return;
        }
        for (int i = 0; i < this.t1; i++) {
            this.s1[i].a0();
        }
        int[] iArr = this.r1;
        int i2 = iArr[0];
        int i3 = iArr[1];
        ConstraintWidget constraintWidget2 = null;
        for (int i4 = 0; i4 < i2; i4++) {
            ConstraintWidget constraintWidget3 = this.q1[z ? (i2 - i4) - 1 : i4];
            if (constraintWidget3 != null && constraintWidget3.P() != 8) {
                if (i4 == 0) {
                    constraintWidget3.j(constraintWidget3.E, this.E, Q0());
                    constraintWidget3.k0(this.V0);
                    constraintWidget3.j0(this.b1);
                }
                if (i4 == i2 - 1) {
                    constraintWidget3.j(constraintWidget3.G, this.G, R0());
                }
                if (i4 > 0) {
                    constraintWidget3.j(constraintWidget3.E, constraintWidget2.G, this.h1);
                    constraintWidget2.j(constraintWidget2.G, constraintWidget3.E, 0);
                }
                constraintWidget2 = constraintWidget3;
            }
        }
        for (int i5 = 0; i5 < i3; i5++) {
            ConstraintWidget constraintWidget4 = this.p1[i5];
            if (constraintWidget4 != null && constraintWidget4.P() != 8) {
                if (i5 == 0) {
                    constraintWidget4.j(constraintWidget4.F, this.F, S0());
                    constraintWidget4.z0(this.W0);
                    constraintWidget4.y0(this.c1);
                }
                if (i5 == i3 - 1) {
                    constraintWidget4.j(constraintWidget4.H, this.H, P0());
                }
                if (i5 > 0) {
                    constraintWidget4.j(constraintWidget4.F, constraintWidget2.H, this.i1);
                    constraintWidget2.j(constraintWidget2.H, constraintWidget4.F, 0);
                }
                constraintWidget2 = constraintWidget4;
            }
        }
        for (int i6 = 0; i6 < i2; i6++) {
            for (int i7 = 0; i7 < i3; i7++) {
                int i8 = (i7 * i2) + i6;
                if (this.n1 == 1) {
                    i8 = (i6 * i3) + i7;
                }
                ConstraintWidget[] constraintWidgetArr = this.s1;
                if (i8 < constraintWidgetArr.length && (constraintWidget = constraintWidgetArr[i8]) != null && constraintWidget.P() != 8) {
                    ConstraintWidget constraintWidget5 = this.q1[i6];
                    ConstraintWidget constraintWidget6 = this.p1[i7];
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

    /* JADX INFO: Access modifiers changed from: private */
    public final int B1(ConstraintWidget constraintWidget, int i) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i2 = constraintWidget.n;
            if (i2 == 0) {
                return 0;
            }
            if (i2 == 2) {
                int i3 = (int) (constraintWidget.u * i);
                if (i3 != constraintWidget.w()) {
                    U0(constraintWidget, constraintWidget.z(), constraintWidget.Q(), ConstraintWidget.DimensionBehaviour.FIXED, i3);
                }
                return i3;
            }
            if (i2 == 1) {
                return constraintWidget.w();
            }
            if (i2 == 3) {
                return (int) ((constraintWidget.Q() * constraintWidget.T) + 0.5f);
            }
        }
        return constraintWidget.w();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int C1(ConstraintWidget constraintWidget, int i) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i2 = constraintWidget.m;
            if (i2 == 0) {
                return 0;
            }
            if (i2 == 2) {
                int i3 = (int) (constraintWidget.r * i);
                if (i3 != constraintWidget.Q()) {
                    U0(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i3, constraintWidget.N(), constraintWidget.w());
                }
                return i3;
            }
            if (i2 == 1) {
                return constraintWidget.Q();
            }
            if (i2 == 3) {
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
    private void D1(androidx.constraintlayout.solver.widgets.ConstraintWidget[] r17, int r18, int r19, int r20, int[] r21) {
        /*
            Method dump skipped, instruction units count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.e.D1(androidx.constraintlayout.solver.widgets.ConstraintWidget[], int, int, int, int[]):void");
    }

    private void E1(ConstraintWidget[] constraintWidgetArr, int i, int i2, int i3, int[] iArr) {
        int i4;
        int i5;
        int i6;
        ConstraintAnchor constraintAnchor;
        int iR0;
        ConstraintAnchor constraintAnchor2;
        int iP0;
        int i7;
        if (i == 0) {
            return;
        }
        this.o1.clear();
        a aVar = new a(i2, this.E, this.F, this.G, this.H, i3);
        this.o1.add(aVar);
        if (i2 == 0) {
            i4 = 0;
            int i8 = 0;
            int i9 = 0;
            while (i9 < i) {
                ConstraintWidget constraintWidget = constraintWidgetArr[i9];
                int iC1 = C1(constraintWidget, i3);
                if (constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i4++;
                }
                int i10 = i4;
                boolean z = (i8 == i3 || (this.h1 + i8) + iC1 > i3) && aVar.f1215b != null;
                if (!z && i9 > 0 && (i7 = this.m1) > 0 && i9 % i7 == 0) {
                    z = true;
                }
                if (z) {
                    aVar = new a(i2, this.E, this.F, this.G, this.H, i3);
                    aVar.i(i9);
                    this.o1.add(aVar);
                } else {
                    if (i9 > 0) {
                        i8 += this.h1 + iC1;
                    }
                    aVar.b(constraintWidget);
                    i9++;
                    i4 = i10;
                }
                i8 = iC1;
                aVar.b(constraintWidget);
                i9++;
                i4 = i10;
            }
        } else {
            i4 = 0;
            int i11 = 0;
            int i12 = 0;
            while (i12 < i) {
                ConstraintWidget constraintWidget2 = constraintWidgetArr[i12];
                int iB1 = B1(constraintWidget2, i3);
                if (constraintWidget2.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i4++;
                }
                int i13 = i4;
                boolean z2 = (i11 == i3 || (this.i1 + i11) + iB1 > i3) && aVar.f1215b != null;
                if (!z2 && i12 > 0 && (i5 = this.m1) > 0 && i12 % i5 == 0) {
                    z2 = true;
                }
                if (z2) {
                    aVar = new a(i2, this.E, this.F, this.G, this.H, i3);
                    aVar.i(i12);
                    this.o1.add(aVar);
                } else {
                    if (i12 > 0) {
                        i11 += this.i1 + iB1;
                    }
                    aVar.b(constraintWidget2);
                    i12++;
                    i4 = i13;
                }
                i11 = iB1;
                aVar.b(constraintWidget2);
                i12++;
                i4 = i13;
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
        if (i4 > 0 && z3) {
            for (int i14 = 0; i14 < size; i14++) {
                a aVar2 = this.o1.get(i14);
                if (i2 == 0) {
                    aVar2.g(i3 - aVar2.f());
                } else {
                    aVar2.g(i3 - aVar2.e());
                }
            }
        }
        int i15 = iS0;
        int i16 = iR02;
        int iE = 0;
        int iF = 0;
        int i17 = 0;
        int i18 = iQ0;
        ConstraintAnchor constraintAnchor7 = constraintAnchor4;
        ConstraintAnchor constraintAnchor8 = constraintAnchor3;
        int i19 = iP02;
        while (i17 < size) {
            a aVar3 = this.o1.get(i17);
            if (i2 == 0) {
                if (i17 < size - 1) {
                    constraintAnchor2 = this.o1.get(i17 + 1).f1215b.F;
                    iP0 = 0;
                } else {
                    constraintAnchor2 = this.H;
                    iP0 = P0();
                }
                ConstraintAnchor constraintAnchor9 = aVar3.f1215b.H;
                ConstraintAnchor constraintAnchor10 = constraintAnchor8;
                ConstraintAnchor constraintAnchor11 = constraintAnchor8;
                int i20 = iE;
                ConstraintAnchor constraintAnchor12 = constraintAnchor7;
                int i21 = iF;
                ConstraintAnchor constraintAnchor13 = constraintAnchor5;
                ConstraintAnchor constraintAnchor14 = constraintAnchor5;
                i6 = i17;
                aVar3.j(i2, constraintAnchor10, constraintAnchor12, constraintAnchor13, constraintAnchor2, i18, i15, i16, iP0, i3);
                int iMax = Math.max(i21, aVar3.f());
                iE = i20 + aVar3.e();
                if (i6 > 0) {
                    iE += this.i1;
                }
                constraintAnchor8 = constraintAnchor11;
                iF = iMax;
                i15 = 0;
                constraintAnchor7 = constraintAnchor9;
                constraintAnchor = constraintAnchor14;
                int i22 = iP0;
                constraintAnchor6 = constraintAnchor2;
                i19 = i22;
            } else {
                ConstraintAnchor constraintAnchor15 = constraintAnchor8;
                int i23 = iE;
                int i24 = iF;
                i6 = i17;
                if (i6 < size - 1) {
                    constraintAnchor = this.o1.get(i6 + 1).f1215b.E;
                    iR0 = 0;
                } else {
                    constraintAnchor = this.G;
                    iR0 = R0();
                }
                ConstraintAnchor constraintAnchor16 = aVar3.f1215b.G;
                aVar3.j(i2, constraintAnchor15, constraintAnchor7, constraintAnchor, constraintAnchor6, i18, i15, iR0, i19, i3);
                iF = i24 + aVar3.f();
                int iMax2 = Math.max(i23, aVar3.e());
                if (i6 > 0) {
                    iF += this.h1;
                }
                iE = iMax2;
                i18 = 0;
                i16 = iR0;
                constraintAnchor8 = constraintAnchor16;
            }
            i17 = i6 + 1;
            constraintAnchor5 = constraintAnchor;
        }
        iArr[0] = iF;
        iArr[1] = iE;
    }

    private void F1(ConstraintWidget[] constraintWidgetArr, int i, int i2, int i3, int[] iArr) {
        a aVar;
        if (i == 0) {
            return;
        }
        if (this.o1.size() == 0) {
            aVar = new a(i2, this.E, this.F, this.G, this.H, i3);
            this.o1.add(aVar);
        } else {
            a aVar2 = this.o1.get(0);
            aVar2.c();
            aVar = aVar2;
            aVar.j(i2, this.E, this.F, this.G, this.H, Q0(), S0(), R0(), P0(), i3);
        }
        for (int i4 = 0; i4 < i; i4++) {
            aVar.b(constraintWidgetArr[i4]);
        }
        iArr[0] = aVar.f();
        iArr[1] = aVar.e();
    }

    public void G1(float f) {
        this.d1 = f;
    }

    public void H1(int i) {
        this.X0 = i;
    }

    public void I1(float f) {
        this.e1 = f;
    }

    public void J1(int i) {
        this.Y0 = i;
    }

    public void K1(int i) {
        this.j1 = i;
    }

    public void L1(float f) {
        this.b1 = f;
    }

    public void M1(int i) {
        this.h1 = i;
    }

    public void N1(int i) {
        this.V0 = i;
    }

    public void O1(float f) {
        this.f1 = f;
    }

    public void P1(int i) {
        this.Z0 = i;
    }

    public void Q1(float f) {
        this.g1 = f;
    }

    public void R1(int i) {
        this.a1 = i;
    }

    public void S1(int i) {
        this.m1 = i;
    }

    @Override // androidx.constraintlayout.solver.widgets.j
    public void T0(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
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
        int i7 = (i2 - iQ0) - iR0;
        int i8 = this.n1;
        if (i8 == 1) {
            i7 = (i4 - iS0) - iP0;
        }
        int i9 = i7;
        if (i8 == 0) {
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
        int i10 = 0;
        int i11 = 0;
        while (true) {
            i5 = this.H0;
            if (i10 >= i5) {
                break;
            }
            if (this.G0[i10].P() == 8) {
                i11++;
            }
            i10++;
        }
        if (i11 > 0) {
            constraintWidgetArr = new ConstraintWidget[i5 - i11];
            int i12 = 0;
            for (int i13 = 0; i13 < this.H0; i13++) {
                ConstraintWidget constraintWidget = this.G0[i13];
                if (constraintWidget.P() != 8) {
                    constraintWidgetArr[i12] = constraintWidget;
                    i12++;
                }
            }
            i6 = i12;
        } else {
            i6 = i5;
        }
        this.s1 = constraintWidgetArr;
        this.t1 = i6;
        int i14 = this.l1;
        if (i14 == 0) {
            iArr = iArr2;
            z = true;
            F1(constraintWidgetArr, i6, this.n1, i9, iArr2);
        } else if (i14 == 1) {
            z = true;
            iArr = iArr2;
            E1(constraintWidgetArr, i6, this.n1, i9, iArr2);
        } else if (i14 != 2) {
            z = true;
            iArr = iArr2;
        } else {
            z = true;
            iArr = iArr2;
            D1(constraintWidgetArr, i6, this.n1, i9, iArr2);
        }
        int iMin = iArr[0] + iQ0 + iR0;
        int iMin2 = iArr[z ? 1 : 0] + iS0 + iP0;
        if (i == 1073741824) {
            iMin = i2;
        } else if (i == Integer.MIN_VALUE) {
            iMin = Math.min(iMin, i2);
        } else if (i != 0) {
            iMin = 0;
        }
        if (i3 == 1073741824) {
            iMin2 = i4;
        } else if (i3 == Integer.MIN_VALUE) {
            iMin2 = Math.min(iMin2, i4);
        } else if (i3 != 0) {
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

    public void T1(int i) {
        this.n1 = i;
    }

    public void U1(int i) {
        this.k1 = i;
    }

    public void V1(float f) {
        this.c1 = f;
    }

    public void W1(int i) {
        this.i1 = i;
    }

    public void X1(int i) {
        this.W0 = i;
    }

    public void Y1(int i) {
        this.l1 = i;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void f(androidx.constraintlayout.solver.d dVar) {
        super.f(dVar);
        boolean zC1 = H() != null ? ((d) H()).c1() : false;
        int i = this.l1;
        if (i != 0) {
            if (i == 1) {
                int size = this.o1.size();
                int i2 = 0;
                while (i2 < size) {
                    this.o1.get(i2).d(zC1, i2, i2 == size + (-1));
                    i2++;
                }
            } else if (i == 2) {
                A1(zC1);
            }
        } else if (this.o1.size() > 0) {
            this.o1.get(0).d(zC1, 0, true);
        }
        X0(false);
    }

    @Override // androidx.constraintlayout.solver.widgets.h, androidx.constraintlayout.solver.widgets.ConstraintWidget
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
