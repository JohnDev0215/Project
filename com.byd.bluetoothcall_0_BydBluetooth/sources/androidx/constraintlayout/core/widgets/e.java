package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: Flow.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends k {
    private ConstraintWidget[] G1;
    private int j1 = -1;
    private int k1 = -1;
    private int l1 = -1;
    private int m1 = -1;
    private int n1 = -1;
    private int o1 = -1;
    private float p1 = 0.5f;
    private float q1 = 0.5f;
    private float r1 = 0.5f;
    private float s1 = 0.5f;
    private float t1 = 0.5f;
    private float u1 = 0.5f;
    private int v1 = 0;
    private int w1 = 0;
    private int x1 = 2;
    private int y1 = 2;
    private int z1 = 0;
    private int A1 = -1;
    private int B1 = 0;
    private ArrayList<a> C1 = new ArrayList<>();
    private ConstraintWidget[] D1 = null;
    private ConstraintWidget[] E1 = null;
    private int[] F1 = null;
    private int H1 = 0;

    /* JADX INFO: compiled from: Flow.java */
    private class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f881a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private ConstraintAnchor f884d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private ConstraintAnchor f885e;
        private ConstraintAnchor f;
        private ConstraintAnchor g;
        private int h;
        private int i;
        private int j;
        private int k;
        private int q;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private ConstraintWidget f882b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f883c = 0;
        private int l = 0;
        private int m = 0;
        private int n = 0;
        private int o = 0;
        private int p = 0;

        public a(int i, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i2) {
            this.f881a = 0;
            this.h = 0;
            this.i = 0;
            this.j = 0;
            this.k = 0;
            this.q = 0;
            this.f881a = i;
            this.f884d = constraintAnchor;
            this.f885e = constraintAnchor2;
            this.f = constraintAnchor3;
            this.g = constraintAnchor4;
            this.h = e.this.C1();
            this.i = e.this.E1();
            this.j = e.this.D1();
            this.k = e.this.B1();
            this.q = i2;
        }

        private void h() {
            this.l = 0;
            this.m = 0;
            this.f882b = null;
            this.f883c = 0;
            int i = this.o;
            for (int i2 = 0; i2 < i && this.n + i2 < e.this.H1; i2++) {
                ConstraintWidget constraintWidget = e.this.G1[this.n + i2];
                if (this.f881a == 0) {
                    int iY = constraintWidget.Y();
                    int i3 = e.this.v1;
                    if (constraintWidget.X() == 8) {
                        i3 = 0;
                    }
                    this.l += iY + i3;
                    int iN2 = e.this.n2(constraintWidget, this.q);
                    if (this.f882b == null || this.f883c < iN2) {
                        this.f882b = constraintWidget;
                        this.f883c = iN2;
                        this.m = iN2;
                    }
                } else {
                    int iO2 = e.this.o2(constraintWidget, this.q);
                    int iN22 = e.this.n2(constraintWidget, this.q);
                    int i4 = e.this.w1;
                    if (constraintWidget.X() == 8) {
                        i4 = 0;
                    }
                    this.m += iN22 + i4;
                    if (this.f882b == null || this.f883c < iO2) {
                        this.f882b = constraintWidget;
                        this.f883c = iO2;
                        this.l = iO2;
                    }
                }
            }
        }

        public void b(ConstraintWidget constraintWidget) {
            if (this.f881a == 0) {
                int iO2 = e.this.o2(constraintWidget, this.q);
                if (constraintWidget.C() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.p++;
                    iO2 = 0;
                }
                this.l += iO2 + (constraintWidget.X() != 8 ? e.this.v1 : 0);
                int iN2 = e.this.n2(constraintWidget, this.q);
                if (this.f882b == null || this.f883c < iN2) {
                    this.f882b = constraintWidget;
                    this.f883c = iN2;
                    this.m = iN2;
                }
            } else {
                int iO22 = e.this.o2(constraintWidget, this.q);
                int iN22 = e.this.n2(constraintWidget, this.q);
                if (constraintWidget.V() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.p++;
                    iN22 = 0;
                }
                this.m += iN22 + (constraintWidget.X() != 8 ? e.this.w1 : 0);
                if (this.f882b == null || this.f883c < iO22) {
                    this.f882b = constraintWidget;
                    this.f883c = iO22;
                    this.l = iO22;
                }
            }
            this.o++;
        }

        public void c() {
            this.f883c = 0;
            this.f882b = null;
            this.l = 0;
            this.m = 0;
            this.n = 0;
            this.o = 0;
            this.p = 0;
        }

        public void d(boolean z, int i, boolean z2) {
            ConstraintWidget constraintWidget;
            char c2;
            float f;
            float f2;
            int i2 = this.o;
            for (int i3 = 0; i3 < i2 && this.n + i3 < e.this.H1; i3++) {
                ConstraintWidget constraintWidget2 = e.this.G1[this.n + i3];
                if (constraintWidget2 != null) {
                    constraintWidget2.x0();
                }
            }
            if (i2 == 0 || this.f882b == null) {
                return;
            }
            boolean z3 = z2 && i == 0;
            int i4 = -1;
            int i5 = -1;
            for (int i6 = 0; i6 < i2; i6++) {
                int i7 = z ? (i2 - 1) - i6 : i6;
                if (this.n + i7 >= e.this.H1) {
                    break;
                }
                ConstraintWidget constraintWidget3 = e.this.G1[this.n + i7];
                if (constraintWidget3 != null && constraintWidget3.X() == 0) {
                    if (i4 == -1) {
                        i4 = i6;
                    }
                    i5 = i6;
                }
            }
            ConstraintWidget constraintWidget4 = null;
            if (this.f881a != 0) {
                ConstraintWidget constraintWidget5 = this.f882b;
                constraintWidget5.Q0(e.this.j1);
                int i8 = this.h;
                if (i > 0) {
                    i8 += e.this.v1;
                }
                if (z) {
                    constraintWidget5.T.a(this.f, i8);
                    if (z2) {
                        constraintWidget5.R.a(this.f884d, this.j);
                    }
                    if (i > 0) {
                        this.f.f818d.R.a(constraintWidget5.T, 0);
                    }
                } else {
                    constraintWidget5.R.a(this.f884d, i8);
                    if (z2) {
                        constraintWidget5.T.a(this.f, this.j);
                    }
                    if (i > 0) {
                        this.f884d.f818d.T.a(constraintWidget5.R, 0);
                    }
                }
                for (int i9 = 0; i9 < i2 && this.n + i9 < e.this.H1; i9++) {
                    ConstraintWidget constraintWidget6 = e.this.G1[this.n + i9];
                    if (constraintWidget6 != null) {
                        if (i9 == 0) {
                            constraintWidget6.l(constraintWidget6.S, this.f885e, this.i);
                            int i10 = e.this.k1;
                            float f3 = e.this.q1;
                            if (this.n == 0 && e.this.m1 != -1) {
                                i10 = e.this.m1;
                                f3 = e.this.s1;
                            } else if (z2 && e.this.o1 != -1) {
                                i10 = e.this.o1;
                                f3 = e.this.u1;
                            }
                            constraintWidget6.h1(i10);
                            constraintWidget6.g1(f3);
                        }
                        if (i9 == i2 - 1) {
                            constraintWidget6.l(constraintWidget6.U, this.g, this.k);
                        }
                        if (constraintWidget4 != null) {
                            constraintWidget6.S.a(constraintWidget4.U, e.this.w1);
                            if (i9 == i4) {
                                constraintWidget6.S.u(this.i);
                            }
                            constraintWidget4.U.a(constraintWidget6.S, 0);
                            if (i9 == i5 + 1) {
                                constraintWidget4.U.u(this.k);
                            }
                        }
                        if (constraintWidget6 == constraintWidget5) {
                            constraintWidget4 = constraintWidget6;
                        } else if (z) {
                            int i11 = e.this.x1;
                            if (i11 == 0) {
                                constraintWidget6.T.a(constraintWidget5.T, 0);
                            } else if (i11 == 1) {
                                constraintWidget6.R.a(constraintWidget5.R, 0);
                            } else if (i11 == 2) {
                                constraintWidget6.R.a(constraintWidget5.R, 0);
                                constraintWidget6.T.a(constraintWidget5.T, 0);
                            }
                            constraintWidget4 = constraintWidget6;
                        } else {
                            int i12 = e.this.x1;
                            if (i12 == 0) {
                                constraintWidget6.R.a(constraintWidget5.R, 0);
                            } else if (i12 == 1) {
                                constraintWidget6.T.a(constraintWidget5.T, 0);
                            } else if (i12 == 2) {
                                if (z3) {
                                    constraintWidget6.R.a(this.f884d, this.h);
                                    constraintWidget6.T.a(this.f, this.j);
                                } else {
                                    constraintWidget6.R.a(constraintWidget5.R, 0);
                                    constraintWidget6.T.a(constraintWidget5.T, 0);
                                }
                            }
                            constraintWidget4 = constraintWidget6;
                        }
                    }
                }
                return;
            }
            ConstraintWidget constraintWidget7 = this.f882b;
            constraintWidget7.h1(e.this.k1);
            int i13 = this.i;
            if (i > 0) {
                i13 += e.this.w1;
            }
            constraintWidget7.S.a(this.f885e, i13);
            if (z2) {
                constraintWidget7.U.a(this.g, this.k);
            }
            if (i > 0) {
                this.f885e.f818d.U.a(constraintWidget7.S, 0);
            }
            char c3 = 3;
            if (e.this.y1 != 3 || constraintWidget7.b0()) {
                constraintWidget = constraintWidget7;
            } else {
                for (int i14 = 0; i14 < i2; i14++) {
                    int i15 = z ? (i2 - 1) - i14 : i14;
                    if (this.n + i15 >= e.this.H1) {
                        break;
                    }
                    constraintWidget = e.this.G1[this.n + i15];
                    if (constraintWidget.b0()) {
                        break;
                    }
                }
                constraintWidget = constraintWidget7;
            }
            int i16 = 0;
            while (i16 < i2) {
                int i17 = z ? (i2 - 1) - i16 : i16;
                if (this.n + i17 >= e.this.H1) {
                    return;
                }
                ConstraintWidget constraintWidget8 = e.this.G1[this.n + i17];
                if (constraintWidget8 == null) {
                    constraintWidget8 = constraintWidget4;
                    c2 = c3;
                } else {
                    if (i16 == 0) {
                        constraintWidget8.l(constraintWidget8.R, this.f884d, this.h);
                    }
                    if (i17 == 0) {
                        int i18 = e.this.j1;
                        float f4 = e.this.p1;
                        if (z) {
                            f4 = 1.0f - f4;
                        }
                        if (this.n != 0 || e.this.l1 == -1) {
                            if (z2 && e.this.n1 != -1) {
                                i18 = e.this.n1;
                                if (z) {
                                    f2 = e.this.t1;
                                    f = 1.0f - f2;
                                    f4 = f;
                                } else {
                                    f = e.this.t1;
                                    f4 = f;
                                }
                            }
                            constraintWidget8.Q0(i18);
                            constraintWidget8.P0(f4);
                        } else {
                            i18 = e.this.l1;
                            if (z) {
                                f2 = e.this.r1;
                                f = 1.0f - f2;
                                f4 = f;
                                constraintWidget8.Q0(i18);
                                constraintWidget8.P0(f4);
                            } else {
                                f = e.this.r1;
                                f4 = f;
                                constraintWidget8.Q0(i18);
                                constraintWidget8.P0(f4);
                            }
                        }
                    }
                    if (i16 == i2 - 1) {
                        constraintWidget8.l(constraintWidget8.T, this.f, this.j);
                    }
                    if (constraintWidget4 != null) {
                        constraintWidget8.R.a(constraintWidget4.T, e.this.v1);
                        if (i16 == i4) {
                            constraintWidget8.R.u(this.h);
                        }
                        constraintWidget4.T.a(constraintWidget8.R, 0);
                        if (i16 == i5 + 1) {
                            constraintWidget4.T.u(this.j);
                        }
                    }
                    if (constraintWidget8 != constraintWidget7) {
                        c2 = 3;
                        if (e.this.y1 == 3 && constraintWidget.b0() && constraintWidget8 != constraintWidget && constraintWidget8.b0()) {
                            constraintWidget8.V.a(constraintWidget.V, 0);
                        } else {
                            int i19 = e.this.y1;
                            if (i19 == 0) {
                                constraintWidget8.S.a(constraintWidget7.S, 0);
                            } else if (i19 == 1) {
                                constraintWidget8.U.a(constraintWidget7.U, 0);
                            } else if (z3) {
                                constraintWidget8.S.a(this.f885e, this.i);
                                constraintWidget8.U.a(this.g, this.k);
                            } else {
                                constraintWidget8.S.a(constraintWidget7.S, 0);
                                constraintWidget8.U.a(constraintWidget7.U, 0);
                            }
                        }
                    } else {
                        c2 = 3;
                    }
                }
                i16++;
                c3 = c2;
                constraintWidget4 = constraintWidget8;
            }
        }

        public int e() {
            return this.f881a == 1 ? this.m - e.this.w1 : this.m;
        }

        public int f() {
            return this.f881a == 0 ? this.l - e.this.v1 : this.l;
        }

        public void g(int i) {
            int i2 = this.p;
            if (i2 == 0) {
                return;
            }
            int i3 = this.o;
            int i4 = i / i2;
            for (int i5 = 0; i5 < i3 && this.n + i5 < e.this.H1; i5++) {
                ConstraintWidget constraintWidget = e.this.G1[this.n + i5];
                if (this.f881a == 0) {
                    if (constraintWidget != null && constraintWidget.C() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.x == 0) {
                        e.this.G1(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i4, constraintWidget.V(), constraintWidget.z());
                    }
                } else if (constraintWidget != null && constraintWidget.V() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.y == 0) {
                    e.this.G1(constraintWidget, constraintWidget.C(), constraintWidget.Y(), ConstraintWidget.DimensionBehaviour.FIXED, i4);
                }
            }
            h();
        }

        public void i(int i) {
            this.n = i;
        }

        public void j(int i, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i2, int i3, int i4, int i5, int i6) {
            this.f881a = i;
            this.f884d = constraintAnchor;
            this.f885e = constraintAnchor2;
            this.f = constraintAnchor3;
            this.g = constraintAnchor4;
            this.h = i2;
            this.i = i3;
            this.j = i4;
            this.k = i5;
            this.q = i6;
        }
    }

    private void m2(boolean z) {
        ConstraintWidget constraintWidget;
        float f;
        int i;
        if (this.F1 == null || this.E1 == null || this.D1 == null) {
            return;
        }
        for (int i2 = 0; i2 < this.H1; i2++) {
            this.G1[i2].x0();
        }
        int[] iArr = this.F1;
        int i3 = iArr[0];
        int i4 = iArr[1];
        ConstraintWidget constraintWidget2 = null;
        float f2 = this.p1;
        int i5 = 0;
        while (i5 < i3) {
            if (z) {
                i = (i3 - i5) - 1;
                f = 1.0f - this.p1;
            } else {
                f = f2;
                i = i5;
            }
            ConstraintWidget constraintWidget3 = this.E1[i];
            if (constraintWidget3 != null && constraintWidget3.X() != 8) {
                if (i5 == 0) {
                    constraintWidget3.l(constraintWidget3.R, this.R, C1());
                    constraintWidget3.Q0(this.j1);
                    constraintWidget3.P0(f);
                }
                if (i5 == i3 - 1) {
                    constraintWidget3.l(constraintWidget3.T, this.T, D1());
                }
                if (i5 > 0 && constraintWidget2 != null) {
                    constraintWidget3.l(constraintWidget3.R, constraintWidget2.T, this.v1);
                    constraintWidget2.l(constraintWidget2.T, constraintWidget3.R, 0);
                }
                constraintWidget2 = constraintWidget3;
            }
            i5++;
            f2 = f;
        }
        for (int i6 = 0; i6 < i4; i6++) {
            ConstraintWidget constraintWidget4 = this.D1[i6];
            if (constraintWidget4 != null && constraintWidget4.X() != 8) {
                if (i6 == 0) {
                    constraintWidget4.l(constraintWidget4.S, this.S, E1());
                    constraintWidget4.h1(this.k1);
                    constraintWidget4.g1(this.q1);
                }
                if (i6 == i4 - 1) {
                    constraintWidget4.l(constraintWidget4.U, this.U, B1());
                }
                if (i6 > 0 && constraintWidget2 != null) {
                    constraintWidget4.l(constraintWidget4.S, constraintWidget2.U, this.w1);
                    constraintWidget2.l(constraintWidget2.U, constraintWidget4.S, 0);
                }
                constraintWidget2 = constraintWidget4;
            }
        }
        for (int i7 = 0; i7 < i3; i7++) {
            for (int i8 = 0; i8 < i4; i8++) {
                int i9 = (i8 * i3) + i7;
                if (this.B1 == 1) {
                    i9 = (i7 * i4) + i8;
                }
                ConstraintWidget[] constraintWidgetArr = this.G1;
                if (i9 < constraintWidgetArr.length && (constraintWidget = constraintWidgetArr[i9]) != null && constraintWidget.X() != 8) {
                    ConstraintWidget constraintWidget5 = this.E1[i7];
                    ConstraintWidget constraintWidget6 = this.D1[i8];
                    if (constraintWidget != constraintWidget5) {
                        constraintWidget.l(constraintWidget.R, constraintWidget5.R, 0);
                        constraintWidget.l(constraintWidget.T, constraintWidget5.T, 0);
                    }
                    if (constraintWidget != constraintWidget6) {
                        constraintWidget.l(constraintWidget.S, constraintWidget6.S, 0);
                        constraintWidget.l(constraintWidget.U, constraintWidget6.U, 0);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int n2(ConstraintWidget constraintWidget, int i) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.V() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i2 = constraintWidget.y;
            if (i2 == 0) {
                return 0;
            }
            if (i2 == 2) {
                int i3 = (int) (constraintWidget.F * i);
                if (i3 != constraintWidget.z()) {
                    constraintWidget.b1(true);
                    G1(constraintWidget, constraintWidget.C(), constraintWidget.Y(), ConstraintWidget.DimensionBehaviour.FIXED, i3);
                }
                return i3;
            }
            if (i2 == 1) {
                return constraintWidget.z();
            }
            if (i2 == 3) {
                return (int) ((constraintWidget.Y() * constraintWidget.g0) + 0.5f);
            }
        }
        return constraintWidget.z();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int o2(ConstraintWidget constraintWidget, int i) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.C() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i2 = constraintWidget.x;
            if (i2 == 0) {
                return 0;
            }
            if (i2 == 2) {
                int i3 = (int) (constraintWidget.C * i);
                if (i3 != constraintWidget.Y()) {
                    constraintWidget.b1(true);
                    G1(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i3, constraintWidget.V(), constraintWidget.z());
                }
                return i3;
            }
            if (i2 == 1) {
                return constraintWidget.Y();
            }
            if (i2 == 3) {
                return (int) ((constraintWidget.z() * constraintWidget.g0) + 0.5f);
            }
        }
        return constraintWidget.Y();
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
    private void p2(androidx.constraintlayout.core.widgets.ConstraintWidget[] r17, int r18, int r19, int r20, int[] r21) {
        /*
            Method dump skipped, instruction units count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.e.p2(androidx.constraintlayout.core.widgets.ConstraintWidget[], int, int, int, int[]):void");
    }

    private void q2(ConstraintWidget[] constraintWidgetArr, int i, int i2, int i3, int[] iArr) {
        int i4;
        int i5;
        int i6;
        ConstraintAnchor constraintAnchor;
        int iD1;
        ConstraintAnchor constraintAnchor2;
        int iB1;
        int i7;
        if (i == 0) {
            return;
        }
        this.C1.clear();
        a aVar = new a(i2, this.R, this.S, this.T, this.U, i3);
        this.C1.add(aVar);
        if (i2 == 0) {
            i4 = 0;
            int i8 = 0;
            int i9 = 0;
            while (i9 < i) {
                ConstraintWidget constraintWidget = constraintWidgetArr[i9];
                int iO2 = o2(constraintWidget, i3);
                if (constraintWidget.C() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i4++;
                }
                int i10 = i4;
                boolean z = (i8 == i3 || (this.v1 + i8) + iO2 > i3) && aVar.f882b != null;
                if (!z && i9 > 0 && (i7 = this.A1) > 0 && i9 % i7 == 0) {
                    z = true;
                }
                if (z) {
                    aVar = new a(i2, this.R, this.S, this.T, this.U, i3);
                    aVar.i(i9);
                    this.C1.add(aVar);
                } else {
                    if (i9 > 0) {
                        i8 += this.v1 + iO2;
                    }
                    aVar.b(constraintWidget);
                    i9++;
                    i4 = i10;
                }
                i8 = iO2;
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
                int iN2 = n2(constraintWidget2, i3);
                if (constraintWidget2.V() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i4++;
                }
                int i13 = i4;
                boolean z2 = (i11 == i3 || (this.w1 + i11) + iN2 > i3) && aVar.f882b != null;
                if (!z2 && i12 > 0 && (i5 = this.A1) > 0 && i12 % i5 == 0) {
                    z2 = true;
                }
                if (z2) {
                    aVar = new a(i2, this.R, this.S, this.T, this.U, i3);
                    aVar.i(i12);
                    this.C1.add(aVar);
                } else {
                    if (i12 > 0) {
                        i11 += this.w1 + iN2;
                    }
                    aVar.b(constraintWidget2);
                    i12++;
                    i4 = i13;
                }
                i11 = iN2;
                aVar.b(constraintWidget2);
                i12++;
                i4 = i13;
            }
        }
        int size = this.C1.size();
        ConstraintAnchor constraintAnchor3 = this.R;
        ConstraintAnchor constraintAnchor4 = this.S;
        ConstraintAnchor constraintAnchor5 = this.T;
        ConstraintAnchor constraintAnchor6 = this.U;
        int iC1 = C1();
        int iE1 = E1();
        int iD12 = D1();
        int iB12 = B1();
        ConstraintWidget.DimensionBehaviour dimensionBehaviourC = C();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        boolean z3 = dimensionBehaviourC == dimensionBehaviour || V() == dimensionBehaviour;
        if (i4 > 0 && z3) {
            for (int i14 = 0; i14 < size; i14++) {
                a aVar2 = this.C1.get(i14);
                if (i2 == 0) {
                    aVar2.g(i3 - aVar2.f());
                } else {
                    aVar2.g(i3 - aVar2.e());
                }
            }
        }
        int i15 = iE1;
        int i16 = iD12;
        int iE = 0;
        int iF = 0;
        int i17 = 0;
        int i18 = iC1;
        ConstraintAnchor constraintAnchor7 = constraintAnchor4;
        ConstraintAnchor constraintAnchor8 = constraintAnchor3;
        int i19 = iB12;
        while (i17 < size) {
            a aVar3 = this.C1.get(i17);
            if (i2 == 0) {
                if (i17 < size - 1) {
                    constraintAnchor2 = this.C1.get(i17 + 1).f882b.S;
                    iB1 = 0;
                } else {
                    constraintAnchor2 = this.U;
                    iB1 = B1();
                }
                ConstraintAnchor constraintAnchor9 = aVar3.f882b.U;
                ConstraintAnchor constraintAnchor10 = constraintAnchor8;
                ConstraintAnchor constraintAnchor11 = constraintAnchor8;
                int i20 = iE;
                ConstraintAnchor constraintAnchor12 = constraintAnchor7;
                int i21 = iF;
                ConstraintAnchor constraintAnchor13 = constraintAnchor5;
                ConstraintAnchor constraintAnchor14 = constraintAnchor5;
                i6 = i17;
                aVar3.j(i2, constraintAnchor10, constraintAnchor12, constraintAnchor13, constraintAnchor2, i18, i15, i16, iB1, i3);
                int iMax = Math.max(i21, aVar3.f());
                iE = i20 + aVar3.e();
                if (i6 > 0) {
                    iE += this.w1;
                }
                constraintAnchor8 = constraintAnchor11;
                iF = iMax;
                i15 = 0;
                constraintAnchor7 = constraintAnchor9;
                constraintAnchor = constraintAnchor14;
                int i22 = iB1;
                constraintAnchor6 = constraintAnchor2;
                i19 = i22;
            } else {
                ConstraintAnchor constraintAnchor15 = constraintAnchor8;
                int i23 = iE;
                int i24 = iF;
                i6 = i17;
                if (i6 < size - 1) {
                    constraintAnchor = this.C1.get(i6 + 1).f882b.R;
                    iD1 = 0;
                } else {
                    constraintAnchor = this.T;
                    iD1 = D1();
                }
                ConstraintAnchor constraintAnchor16 = aVar3.f882b.T;
                aVar3.j(i2, constraintAnchor15, constraintAnchor7, constraintAnchor, constraintAnchor6, i18, i15, iD1, i19, i3);
                iF = i24 + aVar3.f();
                int iMax2 = Math.max(i23, aVar3.e());
                if (i6 > 0) {
                    iF += this.v1;
                }
                iE = iMax2;
                i18 = 0;
                i16 = iD1;
                constraintAnchor8 = constraintAnchor16;
            }
            i17 = i6 + 1;
            constraintAnchor5 = constraintAnchor;
        }
        iArr[0] = iF;
        iArr[1] = iE;
    }

    private void r2(ConstraintWidget[] constraintWidgetArr, int i, int i2, int i3, int[] iArr) {
        int i4;
        int i5;
        int i6;
        ConstraintAnchor constraintAnchor;
        int iD1;
        ConstraintAnchor constraintAnchor2;
        int iB1;
        int i7;
        if (i == 0) {
            return;
        }
        this.C1.clear();
        a aVar = new a(i2, this.R, this.S, this.T, this.U, i3);
        this.C1.add(aVar);
        if (i2 == 0) {
            int i8 = 0;
            i4 = 0;
            int i9 = 0;
            int i10 = 0;
            while (i10 < i) {
                int i11 = i8 + 1;
                ConstraintWidget constraintWidget = constraintWidgetArr[i10];
                int iO2 = o2(constraintWidget, i3);
                if (constraintWidget.C() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i4++;
                }
                int i12 = i4;
                boolean z = (i9 == i3 || (this.v1 + i9) + iO2 > i3) && aVar.f882b != null;
                if (!z && i10 > 0 && (i7 = this.A1) > 0 && i11 > i7) {
                    z = true;
                }
                if (z) {
                    aVar = new a(i2, this.R, this.S, this.T, this.U, i3);
                    aVar.i(i10);
                    this.C1.add(aVar);
                    i8 = i11;
                    i9 = iO2;
                } else {
                    i9 = i10 > 0 ? i9 + this.v1 + iO2 : iO2;
                    i8 = 0;
                }
                aVar.b(constraintWidget);
                i10++;
                i4 = i12;
            }
        } else {
            int i13 = 0;
            i4 = 0;
            int i14 = 0;
            while (i14 < i) {
                ConstraintWidget constraintWidget2 = constraintWidgetArr[i14];
                int iN2 = n2(constraintWidget2, i3);
                if (constraintWidget2.V() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i4++;
                }
                int i15 = i4;
                boolean z2 = (i13 == i3 || (this.w1 + i13) + iN2 > i3) && aVar.f882b != null;
                if (!z2 && i14 > 0 && (i5 = this.A1) > 0 && i5 < 0) {
                    z2 = true;
                }
                if (z2) {
                    aVar = new a(i2, this.R, this.S, this.T, this.U, i3);
                    aVar.i(i14);
                    this.C1.add(aVar);
                } else {
                    if (i14 > 0) {
                        i13 += this.w1 + iN2;
                    }
                    aVar.b(constraintWidget2);
                    i14++;
                    i4 = i15;
                }
                i13 = iN2;
                aVar.b(constraintWidget2);
                i14++;
                i4 = i15;
            }
        }
        int size = this.C1.size();
        ConstraintAnchor constraintAnchor3 = this.R;
        ConstraintAnchor constraintAnchor4 = this.S;
        ConstraintAnchor constraintAnchor5 = this.T;
        ConstraintAnchor constraintAnchor6 = this.U;
        int iC1 = C1();
        int iE1 = E1();
        int iD12 = D1();
        int iB12 = B1();
        ConstraintWidget.DimensionBehaviour dimensionBehaviourC = C();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        boolean z3 = dimensionBehaviourC == dimensionBehaviour || V() == dimensionBehaviour;
        if (i4 > 0 && z3) {
            for (int i16 = 0; i16 < size; i16++) {
                a aVar2 = this.C1.get(i16);
                if (i2 == 0) {
                    aVar2.g(i3 - aVar2.f());
                } else {
                    aVar2.g(i3 - aVar2.e());
                }
            }
        }
        int i17 = iE1;
        int i18 = iD12;
        int iE = 0;
        int iF = 0;
        int i19 = 0;
        int i20 = iC1;
        ConstraintAnchor constraintAnchor7 = constraintAnchor4;
        ConstraintAnchor constraintAnchor8 = constraintAnchor3;
        int i21 = iB12;
        while (i19 < size) {
            a aVar3 = this.C1.get(i19);
            if (i2 == 0) {
                if (i19 < size - 1) {
                    constraintAnchor2 = this.C1.get(i19 + 1).f882b.S;
                    iB1 = 0;
                } else {
                    constraintAnchor2 = this.U;
                    iB1 = B1();
                }
                ConstraintAnchor constraintAnchor9 = aVar3.f882b.U;
                ConstraintAnchor constraintAnchor10 = constraintAnchor8;
                ConstraintAnchor constraintAnchor11 = constraintAnchor8;
                int i22 = iE;
                ConstraintAnchor constraintAnchor12 = constraintAnchor7;
                int i23 = iF;
                ConstraintAnchor constraintAnchor13 = constraintAnchor5;
                ConstraintAnchor constraintAnchor14 = constraintAnchor5;
                i6 = i19;
                aVar3.j(i2, constraintAnchor10, constraintAnchor12, constraintAnchor13, constraintAnchor2, i20, i17, i18, iB1, i3);
                int iMax = Math.max(i23, aVar3.f());
                iE = i22 + aVar3.e();
                if (i6 > 0) {
                    iE += this.w1;
                }
                constraintAnchor8 = constraintAnchor11;
                iF = iMax;
                i17 = 0;
                constraintAnchor7 = constraintAnchor9;
                constraintAnchor = constraintAnchor14;
                int i24 = iB1;
                constraintAnchor6 = constraintAnchor2;
                i21 = i24;
            } else {
                ConstraintAnchor constraintAnchor15 = constraintAnchor8;
                int i25 = iE;
                int i26 = iF;
                i6 = i19;
                if (i6 < size - 1) {
                    constraintAnchor = this.C1.get(i6 + 1).f882b.R;
                    iD1 = 0;
                } else {
                    constraintAnchor = this.T;
                    iD1 = D1();
                }
                ConstraintAnchor constraintAnchor16 = aVar3.f882b.T;
                aVar3.j(i2, constraintAnchor15, constraintAnchor7, constraintAnchor, constraintAnchor6, i20, i17, iD1, i21, i3);
                iF = i26 + aVar3.f();
                int iMax2 = Math.max(i25, aVar3.e());
                if (i6 > 0) {
                    iF += this.v1;
                }
                iE = iMax2;
                i20 = 0;
                i18 = iD1;
                constraintAnchor8 = constraintAnchor16;
            }
            i19 = i6 + 1;
            constraintAnchor5 = constraintAnchor;
        }
        iArr[0] = iF;
        iArr[1] = iE;
    }

    private void s2(ConstraintWidget[] constraintWidgetArr, int i, int i2, int i3, int[] iArr) {
        a aVar;
        if (i == 0) {
            return;
        }
        if (this.C1.size() == 0) {
            aVar = new a(i2, this.R, this.S, this.T, this.U, i3);
            this.C1.add(aVar);
        } else {
            a aVar2 = this.C1.get(0);
            aVar2.c();
            aVar = aVar2;
            aVar.j(i2, this.R, this.S, this.T, this.U, C1(), E1(), D1(), B1(), i3);
        }
        for (int i4 = 0; i4 < i; i4++) {
            aVar.b(constraintWidgetArr[i4]);
        }
        iArr[0] = aVar.f();
        iArr[1] = aVar.e();
    }

    public void A2(int i) {
        this.j1 = i;
    }

    public void B2(float f) {
        this.t1 = f;
    }

    public void C2(int i) {
        this.n1 = i;
    }

    public void D2(float f) {
        this.u1 = f;
    }

    public void E2(int i) {
        this.o1 = i;
    }

    @Override // androidx.constraintlayout.core.widgets.k
    public void F1(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int[] iArr;
        boolean z;
        if (this.V0 > 0 && !H1()) {
            K1(0, 0);
            J1(false);
            return;
        }
        int iC1 = C1();
        int iD1 = D1();
        int iE1 = E1();
        int iB1 = B1();
        int[] iArr2 = new int[2];
        int i7 = (i2 - iC1) - iD1;
        int i8 = this.B1;
        if (i8 == 1) {
            i7 = (i4 - iE1) - iB1;
        }
        int i9 = i7;
        if (i8 == 0) {
            if (this.j1 == -1) {
                this.j1 = 0;
            }
            if (this.k1 == -1) {
                this.k1 = 0;
            }
        } else {
            if (this.j1 == -1) {
                this.j1 = 0;
            }
            if (this.k1 == -1) {
                this.k1 = 0;
            }
        }
        ConstraintWidget[] constraintWidgetArr = this.U0;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            i5 = this.V0;
            if (i10 >= i5) {
                break;
            }
            if (this.U0[i10].X() == 8) {
                i11++;
            }
            i10++;
        }
        if (i11 > 0) {
            constraintWidgetArr = new ConstraintWidget[i5 - i11];
            int i12 = 0;
            for (int i13 = 0; i13 < this.V0; i13++) {
                ConstraintWidget constraintWidget = this.U0[i13];
                if (constraintWidget.X() != 8) {
                    constraintWidgetArr[i12] = constraintWidget;
                    i12++;
                }
            }
            i6 = i12;
        } else {
            i6 = i5;
        }
        this.G1 = constraintWidgetArr;
        this.H1 = i6;
        int i14 = this.z1;
        if (i14 == 0) {
            iArr = iArr2;
            z = true;
            s2(constraintWidgetArr, i6, this.B1, i9, iArr2);
        } else if (i14 == 1) {
            z = true;
            iArr = iArr2;
            q2(constraintWidgetArr, i6, this.B1, i9, iArr2);
        } else if (i14 == 2) {
            z = true;
            iArr = iArr2;
            p2(constraintWidgetArr, i6, this.B1, i9, iArr2);
        } else if (i14 != 3) {
            z = true;
            iArr = iArr2;
        } else {
            z = true;
            iArr = iArr2;
            r2(constraintWidgetArr, i6, this.B1, i9, iArr2);
        }
        int iMin = iArr[0] + iC1 + iD1;
        int iMin2 = iArr[z ? 1 : 0] + iE1 + iB1;
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
        K1(iMin, iMin2);
        n1(iMin);
        O0(iMin2);
        if (this.V0 <= 0) {
            z = false;
        }
        J1(z);
    }

    public void F2(int i) {
        this.A1 = i;
    }

    public void G2(int i) {
        this.B1 = i;
    }

    public void H2(int i) {
        this.y1 = i;
    }

    public void I2(float f) {
        this.q1 = f;
    }

    public void J2(int i) {
        this.w1 = i;
    }

    public void K2(int i) {
        this.k1 = i;
    }

    public void L2(int i) {
        this.z1 = i;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void g(androidx.constraintlayout.core.d dVar, boolean z) {
        super.g(dVar, z);
        boolean z2 = M() != null && ((d) M()).S1();
        int i = this.z1;
        if (i != 0) {
            if (i == 1) {
                int size = this.C1.size();
                int i2 = 0;
                while (i2 < size) {
                    this.C1.get(i2).d(z2, i2, i2 == size + (-1));
                    i2++;
                }
            } else if (i == 2) {
                m2(z2);
            } else if (i == 3) {
                int size2 = this.C1.size();
                int i3 = 0;
                while (i3 < size2) {
                    this.C1.get(i3).d(z2, i3, i3 == size2 + (-1));
                    i3++;
                }
            }
        } else if (this.C1.size() > 0) {
            this.C1.get(0).d(z2, 0, true);
        }
        J1(false);
    }

    @Override // androidx.constraintlayout.core.widgets.h, androidx.constraintlayout.core.widgets.ConstraintWidget
    public void n(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.n(constraintWidget, map);
        e eVar = (e) constraintWidget;
        this.j1 = eVar.j1;
        this.k1 = eVar.k1;
        this.l1 = eVar.l1;
        this.m1 = eVar.m1;
        this.n1 = eVar.n1;
        this.o1 = eVar.o1;
        this.p1 = eVar.p1;
        this.q1 = eVar.q1;
        this.r1 = eVar.r1;
        this.s1 = eVar.s1;
        this.t1 = eVar.t1;
        this.u1 = eVar.u1;
        this.v1 = eVar.v1;
        this.w1 = eVar.w1;
        this.x1 = eVar.x1;
        this.y1 = eVar.y1;
        this.z1 = eVar.z1;
        this.A1 = eVar.A1;
        this.B1 = eVar.B1;
    }

    public void t2(float f) {
        this.r1 = f;
    }

    public void u2(int i) {
        this.l1 = i;
    }

    public void v2(float f) {
        this.s1 = f;
    }

    public void w2(int i) {
        this.m1 = i;
    }

    public void x2(int i) {
        this.x1 = i;
    }

    public void y2(float f) {
        this.p1 = f;
    }

    public void z2(int i) {
        this.v1 = i;
    }
}
