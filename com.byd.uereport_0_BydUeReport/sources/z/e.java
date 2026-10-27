package z;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: Flow.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends j {

    /* JADX INFO: renamed from: s1, reason: collision with root package name */
    public ConstraintWidget[] f9462s1;
    public int V0 = -1;
    public int W0 = -1;
    public int X0 = -1;
    public int Y0 = -1;
    public int Z0 = -1;

    /* JADX INFO: renamed from: a1, reason: collision with root package name */
    public int f9444a1 = -1;

    /* JADX INFO: renamed from: b1, reason: collision with root package name */
    public float f9445b1 = 0.5f;

    /* JADX INFO: renamed from: c1, reason: collision with root package name */
    public float f9446c1 = 0.5f;

    /* JADX INFO: renamed from: d1, reason: collision with root package name */
    public float f9447d1 = 0.5f;

    /* JADX INFO: renamed from: e1, reason: collision with root package name */
    public float f9448e1 = 0.5f;

    /* JADX INFO: renamed from: f1, reason: collision with root package name */
    public float f9449f1 = 0.5f;

    /* JADX INFO: renamed from: g1, reason: collision with root package name */
    public float f9450g1 = 0.5f;

    /* JADX INFO: renamed from: h1, reason: collision with root package name */
    public int f9451h1 = 0;

    /* JADX INFO: renamed from: i1, reason: collision with root package name */
    public int f9452i1 = 0;

    /* JADX INFO: renamed from: j1, reason: collision with root package name */
    public int f9453j1 = 2;

    /* JADX INFO: renamed from: k1, reason: collision with root package name */
    public int f9454k1 = 2;

    /* JADX INFO: renamed from: l1, reason: collision with root package name */
    public int f9455l1 = 0;

    /* JADX INFO: renamed from: m1, reason: collision with root package name */
    public int f9456m1 = -1;

    /* JADX INFO: renamed from: n1, reason: collision with root package name */
    public int f9457n1 = 0;

    /* JADX INFO: renamed from: o1, reason: collision with root package name */
    public ArrayList<a> f9458o1 = new ArrayList<>();

    /* JADX INFO: renamed from: p1, reason: collision with root package name */
    public ConstraintWidget[] f9459p1 = null;

    /* JADX INFO: renamed from: q1, reason: collision with root package name */
    public ConstraintWidget[] f9460q1 = null;

    /* JADX INFO: renamed from: r1, reason: collision with root package name */
    public int[] f9461r1 = null;

    /* JADX INFO: renamed from: t1, reason: collision with root package name */
    public int f9463t1 = 0;

    /* JADX INFO: compiled from: Flow.java */
    public class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f9464a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ConstraintAnchor f9467d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ConstraintAnchor f9468e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public ConstraintAnchor f9469f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ConstraintAnchor f9470g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f9471h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f9472i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f9473j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f9474k;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f9480q;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ConstraintWidget f9465b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f9466c = 0;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int f9475l = 0;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f9476m = 0;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public int f9477n = 0;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public int f9478o = 0;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public int f9479p = 0;

        public a(int i8, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i9) {
            this.f9464a = 0;
            this.f9471h = 0;
            this.f9472i = 0;
            this.f9473j = 0;
            this.f9474k = 0;
            this.f9480q = 0;
            this.f9464a = i8;
            this.f9467d = constraintAnchor;
            this.f9468e = constraintAnchor2;
            this.f9469f = constraintAnchor3;
            this.f9470g = constraintAnchor4;
            this.f9471h = e.this.Q0();
            this.f9472i = e.this.S0();
            this.f9473j = e.this.R0();
            this.f9474k = e.this.P0();
            this.f9480q = i9;
        }

        public void b(ConstraintWidget constraintWidget) {
            if (this.f9464a == 0) {
                int iC1 = e.this.C1(constraintWidget, this.f9480q);
                if (constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.f9479p++;
                    iC1 = 0;
                }
                this.f9475l += iC1 + (constraintWidget.P() != 8 ? e.this.f9451h1 : 0);
                int iB1 = e.this.B1(constraintWidget, this.f9480q);
                if (this.f9465b == null || this.f9466c < iB1) {
                    this.f9465b = constraintWidget;
                    this.f9466c = iB1;
                    this.f9476m = iB1;
                }
            } else {
                int iC12 = e.this.C1(constraintWidget, this.f9480q);
                int iB12 = e.this.B1(constraintWidget, this.f9480q);
                if (constraintWidget.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.f9479p++;
                    iB12 = 0;
                }
                this.f9476m += iB12 + (constraintWidget.P() != 8 ? e.this.f9452i1 : 0);
                if (this.f9465b == null || this.f9466c < iC12) {
                    this.f9465b = constraintWidget;
                    this.f9466c = iC12;
                    this.f9475l = iC12;
                }
            }
            this.f9478o++;
        }

        public void c() {
            this.f9466c = 0;
            this.f9465b = null;
            this.f9475l = 0;
            this.f9476m = 0;
            this.f9477n = 0;
            this.f9478o = 0;
            this.f9479p = 0;
        }

        public void d(boolean z7, int i8, boolean z8) {
            ConstraintWidget constraintWidget;
            char c8;
            int i9 = this.f9478o;
            for (int i10 = 0; i10 < i9 && this.f9477n + i10 < e.this.f9463t1; i10++) {
                ConstraintWidget constraintWidget2 = e.this.f9462s1[this.f9477n + i10];
                if (constraintWidget2 != null) {
                    constraintWidget2.a0();
                }
            }
            if (i9 == 0 || this.f9465b == null) {
                return;
            }
            boolean z9 = z8 && i8 == 0;
            int i11 = -1;
            int i12 = -1;
            for (int i13 = 0; i13 < i9; i13++) {
                int i14 = z7 ? (i9 - 1) - i13 : i13;
                if (this.f9477n + i14 >= e.this.f9463t1) {
                    break;
                }
                if (e.this.f9462s1[this.f9477n + i14].P() == 0) {
                    if (i11 == -1) {
                        i11 = i13;
                    }
                    i12 = i13;
                }
            }
            ConstraintWidget constraintWidget3 = null;
            if (this.f9464a != 0) {
                ConstraintWidget constraintWidget4 = this.f9465b;
                constraintWidget4.k0(e.this.V0);
                int i15 = this.f9471h;
                if (i8 > 0) {
                    i15 += e.this.f9451h1;
                }
                if (z7) {
                    constraintWidget4.G.a(this.f9469f, i15);
                    if (z8) {
                        constraintWidget4.E.a(this.f9467d, this.f9473j);
                    }
                    if (i8 > 0) {
                        this.f9469f.f917b.E.a(constraintWidget4.G, 0);
                    }
                } else {
                    constraintWidget4.E.a(this.f9467d, i15);
                    if (z8) {
                        constraintWidget4.G.a(this.f9469f, this.f9473j);
                    }
                    if (i8 > 0) {
                        this.f9467d.f917b.G.a(constraintWidget4.E, 0);
                    }
                }
                int i16 = 0;
                while (i16 < i9 && this.f9477n + i16 < e.this.f9463t1) {
                    ConstraintWidget constraintWidget5 = e.this.f9462s1[this.f9477n + i16];
                    if (i16 == 0) {
                        constraintWidget5.j(constraintWidget5.F, this.f9468e, this.f9472i);
                        int i17 = e.this.W0;
                        float f8 = e.this.f9446c1;
                        if (this.f9477n == 0 && e.this.Y0 != -1) {
                            i17 = e.this.Y0;
                            f8 = e.this.f9448e1;
                        } else if (z8 && e.this.f9444a1 != -1) {
                            i17 = e.this.f9444a1;
                            f8 = e.this.f9450g1;
                        }
                        constraintWidget5.z0(i17);
                        constraintWidget5.y0(f8);
                    }
                    if (i16 == i9 - 1) {
                        constraintWidget5.j(constraintWidget5.H, this.f9470g, this.f9474k);
                    }
                    if (constraintWidget3 != null) {
                        constraintWidget5.F.a(constraintWidget3.H, e.this.f9452i1);
                        if (i16 == i11) {
                            constraintWidget5.F.n(this.f9472i);
                        }
                        constraintWidget3.H.a(constraintWidget5.F, 0);
                        if (i16 == i12 + 1) {
                            constraintWidget3.H.n(this.f9474k);
                        }
                    }
                    if (constraintWidget5 != constraintWidget4) {
                        if (z7) {
                            int i18 = e.this.f9453j1;
                            if (i18 == 0) {
                                constraintWidget5.G.a(constraintWidget4.G, 0);
                            } else if (i18 == 1) {
                                constraintWidget5.E.a(constraintWidget4.E, 0);
                            } else if (i18 == 2) {
                                constraintWidget5.E.a(constraintWidget4.E, 0);
                                constraintWidget5.G.a(constraintWidget4.G, 0);
                            }
                        } else {
                            int i19 = e.this.f9453j1;
                            if (i19 == 0) {
                                constraintWidget5.E.a(constraintWidget4.E, 0);
                            } else if (i19 == 1) {
                                constraintWidget5.G.a(constraintWidget4.G, 0);
                            } else if (i19 == 2) {
                                if (z9) {
                                    constraintWidget5.E.a(this.f9467d, this.f9471h);
                                    constraintWidget5.G.a(this.f9469f, this.f9473j);
                                } else {
                                    constraintWidget5.E.a(constraintWidget4.E, 0);
                                    constraintWidget5.G.a(constraintWidget4.G, 0);
                                }
                            }
                        }
                    }
                    i16++;
                    constraintWidget3 = constraintWidget5;
                }
                return;
            }
            ConstraintWidget constraintWidget6 = this.f9465b;
            constraintWidget6.z0(e.this.W0);
            int i20 = this.f9472i;
            if (i8 > 0) {
                i20 += e.this.f9452i1;
            }
            constraintWidget6.F.a(this.f9468e, i20);
            if (z8) {
                constraintWidget6.H.a(this.f9470g, this.f9474k);
            }
            if (i8 > 0) {
                this.f9468e.f917b.H.a(constraintWidget6.F, 0);
            }
            if (e.this.f9454k1 != 3 || constraintWidget6.T()) {
                constraintWidget = constraintWidget6;
            } else {
                for (int i21 = 0; i21 < i9; i21++) {
                    int i22 = z7 ? (i9 - 1) - i21 : i21;
                    if (this.f9477n + i22 >= e.this.f9463t1) {
                        break;
                    }
                    constraintWidget = e.this.f9462s1[this.f9477n + i22];
                    if (constraintWidget.T()) {
                        break;
                    }
                }
                constraintWidget = constraintWidget6;
            }
            int i23 = 0;
            while (i23 < i9) {
                int i24 = z7 ? (i9 - 1) - i23 : i23;
                if (this.f9477n + i24 >= e.this.f9463t1) {
                    return;
                }
                ConstraintWidget constraintWidget7 = e.this.f9462s1[this.f9477n + i24];
                if (i23 == 0) {
                    constraintWidget7.j(constraintWidget7.E, this.f9467d, this.f9471h);
                }
                if (i24 == 0) {
                    int i25 = e.this.V0;
                    float f9 = e.this.f9445b1;
                    if (this.f9477n == 0 && e.this.X0 != -1) {
                        i25 = e.this.X0;
                        f9 = e.this.f9447d1;
                    } else if (z8 && e.this.Z0 != -1) {
                        i25 = e.this.Z0;
                        f9 = e.this.f9449f1;
                    }
                    constraintWidget7.k0(i25);
                    constraintWidget7.j0(f9);
                }
                if (i23 == i9 - 1) {
                    constraintWidget7.j(constraintWidget7.G, this.f9469f, this.f9473j);
                }
                if (constraintWidget3 != null) {
                    constraintWidget7.E.a(constraintWidget3.G, e.this.f9451h1);
                    if (i23 == i11) {
                        constraintWidget7.E.n(this.f9471h);
                    }
                    constraintWidget3.G.a(constraintWidget7.E, 0);
                    if (i23 == i12 + 1) {
                        constraintWidget3.G.n(this.f9473j);
                    }
                }
                if (constraintWidget7 != constraintWidget6) {
                    c8 = 3;
                    if (e.this.f9454k1 == 3 && constraintWidget.T() && constraintWidget7 != constraintWidget && constraintWidget7.T()) {
                        constraintWidget7.I.a(constraintWidget.I, 0);
                    } else {
                        int i26 = e.this.f9454k1;
                        if (i26 == 0) {
                            constraintWidget7.F.a(constraintWidget6.F, 0);
                        } else if (i26 == 1) {
                            constraintWidget7.H.a(constraintWidget6.H, 0);
                        } else if (z9) {
                            constraintWidget7.F.a(this.f9468e, this.f9472i);
                            constraintWidget7.H.a(this.f9470g, this.f9474k);
                        } else {
                            constraintWidget7.F.a(constraintWidget6.F, 0);
                            constraintWidget7.H.a(constraintWidget6.H, 0);
                        }
                    }
                } else {
                    c8 = 3;
                }
                i23++;
                constraintWidget3 = constraintWidget7;
            }
        }

        public int e() {
            return this.f9464a == 1 ? this.f9476m - e.this.f9452i1 : this.f9476m;
        }

        public int f() {
            return this.f9464a == 0 ? this.f9475l - e.this.f9451h1 : this.f9475l;
        }

        public void g(int i8) {
            int i9 = this.f9479p;
            if (i9 == 0) {
                return;
            }
            int i10 = this.f9478o;
            int i11 = i8 / i9;
            for (int i12 = 0; i12 < i10 && this.f9477n + i12 < e.this.f9463t1; i12++) {
                ConstraintWidget constraintWidget = e.this.f9462s1[this.f9477n + i12];
                if (this.f9464a == 0) {
                    if (constraintWidget != null && constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.f948m == 0) {
                        e.this.U0(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i11, constraintWidget.N(), constraintWidget.w());
                    }
                } else if (constraintWidget != null && constraintWidget.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.f950n == 0) {
                    e.this.U0(constraintWidget, constraintWidget.z(), constraintWidget.Q(), ConstraintWidget.DimensionBehaviour.FIXED, i11);
                }
            }
            h();
        }

        public final void h() {
            this.f9475l = 0;
            this.f9476m = 0;
            this.f9465b = null;
            this.f9466c = 0;
            int i8 = this.f9478o;
            for (int i9 = 0; i9 < i8 && this.f9477n + i9 < e.this.f9463t1; i9++) {
                ConstraintWidget constraintWidget = e.this.f9462s1[this.f9477n + i9];
                if (this.f9464a == 0) {
                    int iQ = constraintWidget.Q();
                    int i10 = e.this.f9451h1;
                    if (constraintWidget.P() == 8) {
                        i10 = 0;
                    }
                    this.f9475l += iQ + i10;
                    int iB1 = e.this.B1(constraintWidget, this.f9480q);
                    if (this.f9465b == null || this.f9466c < iB1) {
                        this.f9465b = constraintWidget;
                        this.f9466c = iB1;
                        this.f9476m = iB1;
                    }
                } else {
                    int iC1 = e.this.C1(constraintWidget, this.f9480q);
                    int iB12 = e.this.B1(constraintWidget, this.f9480q);
                    int i11 = e.this.f9452i1;
                    if (constraintWidget.P() == 8) {
                        i11 = 0;
                    }
                    this.f9476m += iB12 + i11;
                    if (this.f9465b == null || this.f9466c < iC1) {
                        this.f9465b = constraintWidget;
                        this.f9466c = iC1;
                        this.f9475l = iC1;
                    }
                }
            }
        }

        public void i(int i8) {
            this.f9477n = i8;
        }

        public void j(int i8, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i9, int i10, int i11, int i12, int i13) {
            this.f9464a = i8;
            this.f9467d = constraintAnchor;
            this.f9468e = constraintAnchor2;
            this.f9469f = constraintAnchor3;
            this.f9470g = constraintAnchor4;
            this.f9471h = i9;
            this.f9472i = i10;
            this.f9473j = i11;
            this.f9474k = i12;
            this.f9480q = i13;
        }
    }

    public final void A1(boolean z7) {
        ConstraintWidget constraintWidget;
        if (this.f9461r1 == null || this.f9460q1 == null || this.f9459p1 == null) {
            return;
        }
        for (int i8 = 0; i8 < this.f9463t1; i8++) {
            this.f9462s1[i8].a0();
        }
        int[] iArr = this.f9461r1;
        int i9 = iArr[0];
        int i10 = iArr[1];
        ConstraintWidget constraintWidget2 = null;
        for (int i11 = 0; i11 < i9; i11++) {
            ConstraintWidget constraintWidget3 = this.f9460q1[z7 ? (i9 - i11) - 1 : i11];
            if (constraintWidget3 != null && constraintWidget3.P() != 8) {
                if (i11 == 0) {
                    constraintWidget3.j(constraintWidget3.E, this.E, Q0());
                    constraintWidget3.k0(this.V0);
                    constraintWidget3.j0(this.f9445b1);
                }
                if (i11 == i9 - 1) {
                    constraintWidget3.j(constraintWidget3.G, this.G, R0());
                }
                if (i11 > 0) {
                    constraintWidget3.j(constraintWidget3.E, constraintWidget2.G, this.f9451h1);
                    constraintWidget2.j(constraintWidget2.G, constraintWidget3.E, 0);
                }
                constraintWidget2 = constraintWidget3;
            }
        }
        for (int i12 = 0; i12 < i10; i12++) {
            ConstraintWidget constraintWidget4 = this.f9459p1[i12];
            if (constraintWidget4 != null && constraintWidget4.P() != 8) {
                if (i12 == 0) {
                    constraintWidget4.j(constraintWidget4.F, this.F, S0());
                    constraintWidget4.z0(this.W0);
                    constraintWidget4.y0(this.f9446c1);
                }
                if (i12 == i10 - 1) {
                    constraintWidget4.j(constraintWidget4.H, this.H, P0());
                }
                if (i12 > 0) {
                    constraintWidget4.j(constraintWidget4.F, constraintWidget2.H, this.f9452i1);
                    constraintWidget2.j(constraintWidget2.H, constraintWidget4.F, 0);
                }
                constraintWidget2 = constraintWidget4;
            }
        }
        for (int i13 = 0; i13 < i9; i13++) {
            for (int i14 = 0; i14 < i10; i14++) {
                int i15 = (i14 * i9) + i13;
                if (this.f9457n1 == 1) {
                    i15 = (i13 * i10) + i14;
                }
                ConstraintWidget[] constraintWidgetArr = this.f9462s1;
                if (i15 < constraintWidgetArr.length && (constraintWidget = constraintWidgetArr[i15]) != null && constraintWidget.P() != 8) {
                    ConstraintWidget constraintWidget5 = this.f9460q1[i13];
                    ConstraintWidget constraintWidget6 = this.f9459p1[i14];
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

    public final int B1(ConstraintWidget constraintWidget, int i8) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i9 = constraintWidget.f950n;
            if (i9 == 0) {
                return 0;
            }
            if (i9 == 2) {
                int i10 = (int) (constraintWidget.f964u * i8);
                if (i10 != constraintWidget.w()) {
                    U0(constraintWidget, constraintWidget.z(), constraintWidget.Q(), ConstraintWidget.DimensionBehaviour.FIXED, i10);
                }
                return i10;
            }
            if (i9 == 1) {
                return constraintWidget.w();
            }
            if (i9 == 3) {
                return (int) ((constraintWidget.Q() * constraintWidget.T) + 0.5f);
            }
        }
        return constraintWidget.w();
    }

    public final int C1(ConstraintWidget constraintWidget, int i8) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i9 = constraintWidget.f948m;
            if (i9 == 0) {
                return 0;
            }
            if (i9 == 2) {
                int i10 = (int) (constraintWidget.f958r * i8);
                if (i10 != constraintWidget.Q()) {
                    U0(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i10, constraintWidget.N(), constraintWidget.w());
                }
                return i10;
            }
            if (i9 == 1) {
                return constraintWidget.Q();
            }
            if (i9 == 3) {
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
        throw new UnsupportedOperationException("Method not decompiled: z.e.D1(androidx.constraintlayout.solver.widgets.ConstraintWidget[], int, int, int, int[]):void");
    }

    public final void E1(ConstraintWidget[] constraintWidgetArr, int i8, int i9, int i10, int[] iArr) {
        int i11;
        int i12;
        int i13;
        ConstraintAnchor constraintAnchor;
        int iR0;
        ConstraintAnchor constraintAnchor2;
        int iP0;
        int i14;
        if (i8 == 0) {
            return;
        }
        this.f9458o1.clear();
        a aVar = new a(i9, this.E, this.F, this.G, this.H, i10);
        this.f9458o1.add(aVar);
        if (i9 == 0) {
            i11 = 0;
            int i15 = 0;
            int i16 = 0;
            while (i16 < i8) {
                ConstraintWidget constraintWidget = constraintWidgetArr[i16];
                int iC1 = C1(constraintWidget, i10);
                if (constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i11++;
                }
                int i17 = i11;
                boolean z7 = (i15 == i10 || (this.f9451h1 + i15) + iC1 > i10) && aVar.f9465b != null;
                if (!z7 && i16 > 0 && (i14 = this.f9456m1) > 0 && i16 % i14 == 0) {
                    z7 = true;
                }
                if (z7) {
                    aVar = new a(i9, this.E, this.F, this.G, this.H, i10);
                    aVar.i(i16);
                    this.f9458o1.add(aVar);
                } else {
                    if (i16 > 0) {
                        i15 += this.f9451h1 + iC1;
                    }
                    aVar.b(constraintWidget);
                    i16++;
                    i11 = i17;
                }
                i15 = iC1;
                aVar.b(constraintWidget);
                i16++;
                i11 = i17;
            }
        } else {
            i11 = 0;
            int i18 = 0;
            int i19 = 0;
            while (i19 < i8) {
                ConstraintWidget constraintWidget2 = constraintWidgetArr[i19];
                int iB1 = B1(constraintWidget2, i10);
                if (constraintWidget2.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i11++;
                }
                int i20 = i11;
                boolean z8 = (i18 == i10 || (this.f9452i1 + i18) + iB1 > i10) && aVar.f9465b != null;
                if (!z8 && i19 > 0 && (i12 = this.f9456m1) > 0 && i19 % i12 == 0) {
                    z8 = true;
                }
                if (z8) {
                    aVar = new a(i9, this.E, this.F, this.G, this.H, i10);
                    aVar.i(i19);
                    this.f9458o1.add(aVar);
                } else {
                    if (i19 > 0) {
                        i18 += this.f9452i1 + iB1;
                    }
                    aVar.b(constraintWidget2);
                    i19++;
                    i11 = i20;
                }
                i18 = iB1;
                aVar.b(constraintWidget2);
                i19++;
                i11 = i20;
            }
        }
        int size = this.f9458o1.size();
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
        boolean z9 = dimensionBehaviourZ == dimensionBehaviour || N() == dimensionBehaviour;
        if (i11 > 0 && z9) {
            for (int i21 = 0; i21 < size; i21++) {
                a aVar2 = this.f9458o1.get(i21);
                if (i9 == 0) {
                    aVar2.g(i10 - aVar2.f());
                } else {
                    aVar2.g(i10 - aVar2.e());
                }
            }
        }
        int i22 = iS0;
        int i23 = iR02;
        int iE = 0;
        int iF = 0;
        int i24 = 0;
        int i25 = iQ0;
        ConstraintAnchor constraintAnchor7 = constraintAnchor4;
        ConstraintAnchor constraintAnchor8 = constraintAnchor3;
        int i26 = iP02;
        while (i24 < size) {
            a aVar3 = this.f9458o1.get(i24);
            if (i9 == 0) {
                if (i24 < size - 1) {
                    constraintAnchor2 = this.f9458o1.get(i24 + 1).f9465b.F;
                    iP0 = 0;
                } else {
                    constraintAnchor2 = this.H;
                    iP0 = P0();
                }
                ConstraintAnchor constraintAnchor9 = aVar3.f9465b.H;
                ConstraintAnchor constraintAnchor10 = constraintAnchor8;
                ConstraintAnchor constraintAnchor11 = constraintAnchor8;
                int i27 = iE;
                ConstraintAnchor constraintAnchor12 = constraintAnchor7;
                int i28 = iF;
                ConstraintAnchor constraintAnchor13 = constraintAnchor5;
                ConstraintAnchor constraintAnchor14 = constraintAnchor5;
                i13 = i24;
                aVar3.j(i9, constraintAnchor10, constraintAnchor12, constraintAnchor13, constraintAnchor2, i25, i22, i23, iP0, i10);
                int iMax = Math.max(i28, aVar3.f());
                iE = i27 + aVar3.e();
                if (i13 > 0) {
                    iE += this.f9452i1;
                }
                constraintAnchor8 = constraintAnchor11;
                iF = iMax;
                i22 = 0;
                constraintAnchor7 = constraintAnchor9;
                constraintAnchor = constraintAnchor14;
                int i29 = iP0;
                constraintAnchor6 = constraintAnchor2;
                i26 = i29;
            } else {
                ConstraintAnchor constraintAnchor15 = constraintAnchor8;
                int i30 = iE;
                int i31 = iF;
                i13 = i24;
                if (i13 < size - 1) {
                    constraintAnchor = this.f9458o1.get(i13 + 1).f9465b.E;
                    iR0 = 0;
                } else {
                    constraintAnchor = this.G;
                    iR0 = R0();
                }
                ConstraintAnchor constraintAnchor16 = aVar3.f9465b.G;
                aVar3.j(i9, constraintAnchor15, constraintAnchor7, constraintAnchor, constraintAnchor6, i25, i22, iR0, i26, i10);
                iF = i31 + aVar3.f();
                int iMax2 = Math.max(i30, aVar3.e());
                if (i13 > 0) {
                    iF += this.f9451h1;
                }
                iE = iMax2;
                i25 = 0;
                i23 = iR0;
                constraintAnchor8 = constraintAnchor16;
            }
            i24 = i13 + 1;
            constraintAnchor5 = constraintAnchor;
        }
        iArr[0] = iF;
        iArr[1] = iE;
    }

    public final void F1(ConstraintWidget[] constraintWidgetArr, int i8, int i9, int i10, int[] iArr) {
        a aVar;
        if (i8 == 0) {
            return;
        }
        if (this.f9458o1.size() == 0) {
            aVar = new a(i9, this.E, this.F, this.G, this.H, i10);
            this.f9458o1.add(aVar);
        } else {
            a aVar2 = this.f9458o1.get(0);
            aVar2.c();
            aVar = aVar2;
            aVar.j(i9, this.E, this.F, this.G, this.H, Q0(), S0(), R0(), P0(), i10);
        }
        for (int i11 = 0; i11 < i8; i11++) {
            aVar.b(constraintWidgetArr[i11]);
        }
        iArr[0] = aVar.f();
        iArr[1] = aVar.e();
    }

    public void G1(float f8) {
        this.f9447d1 = f8;
    }

    public void H1(int i8) {
        this.X0 = i8;
    }

    public void I1(float f8) {
        this.f9448e1 = f8;
    }

    public void J1(int i8) {
        this.Y0 = i8;
    }

    public void K1(int i8) {
        this.f9453j1 = i8;
    }

    public void L1(float f8) {
        this.f9445b1 = f8;
    }

    public void M1(int i8) {
        this.f9451h1 = i8;
    }

    public void N1(int i8) {
        this.V0 = i8;
    }

    public void O1(float f8) {
        this.f9449f1 = f8;
    }

    public void P1(int i8) {
        this.Z0 = i8;
    }

    public void Q1(float f8) {
        this.f9450g1 = f8;
    }

    public void R1(int i8) {
        this.f9444a1 = i8;
    }

    public void S1(int i8) {
        this.f9456m1 = i8;
    }

    @Override // z.j
    public void T0(int i8, int i9, int i10, int i11) {
        int i12;
        int i13;
        int[] iArr;
        boolean z7;
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
        int i14 = (i9 - iQ0) - iR0;
        int i15 = this.f9457n1;
        if (i15 == 1) {
            i14 = (i11 - iS0) - iP0;
        }
        int i16 = i14;
        if (i15 == 0) {
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
        int i17 = 0;
        int i18 = 0;
        while (true) {
            i12 = this.H0;
            if (i17 >= i12) {
                break;
            }
            if (this.G0[i17].P() == 8) {
                i18++;
            }
            i17++;
        }
        if (i18 > 0) {
            constraintWidgetArr = new ConstraintWidget[i12 - i18];
            int i19 = 0;
            for (int i20 = 0; i20 < this.H0; i20++) {
                ConstraintWidget constraintWidget = this.G0[i20];
                if (constraintWidget.P() != 8) {
                    constraintWidgetArr[i19] = constraintWidget;
                    i19++;
                }
            }
            i13 = i19;
        } else {
            i13 = i12;
        }
        this.f9462s1 = constraintWidgetArr;
        this.f9463t1 = i13;
        int i21 = this.f9455l1;
        if (i21 == 0) {
            iArr = iArr2;
            z7 = true;
            F1(constraintWidgetArr, i13, this.f9457n1, i16, iArr2);
        } else if (i21 == 1) {
            z7 = true;
            iArr = iArr2;
            E1(constraintWidgetArr, i13, this.f9457n1, i16, iArr2);
        } else if (i21 != 2) {
            z7 = true;
            iArr = iArr2;
        } else {
            z7 = true;
            iArr = iArr2;
            D1(constraintWidgetArr, i13, this.f9457n1, i16, iArr2);
        }
        int iMin = iArr[0] + iQ0 + iR0;
        int iMin2 = iArr[z7 ? 1 : 0] + iS0 + iP0;
        if (i8 == 1073741824) {
            iMin = i9;
        } else if (i8 == Integer.MIN_VALUE) {
            iMin = Math.min(iMin, i9);
        } else if (i8 != 0) {
            iMin = 0;
        }
        if (i10 == 1073741824) {
            iMin2 = i11;
        } else if (i10 == Integer.MIN_VALUE) {
            iMin2 = Math.min(iMin2, i11);
        } else if (i10 != 0) {
            iMin2 = 0;
        }
        Y0(iMin, iMin2);
        F0(iMin);
        i0(iMin2);
        if (this.H0 <= 0) {
            z7 = false;
        }
        X0(z7);
    }

    public void T1(int i8) {
        this.f9457n1 = i8;
    }

    public void U1(int i8) {
        this.f9454k1 = i8;
    }

    public void V1(float f8) {
        this.f9446c1 = f8;
    }

    public void W1(int i8) {
        this.f9452i1 = i8;
    }

    public void X1(int i8) {
        this.W0 = i8;
    }

    public void Y1(int i8) {
        this.f9455l1 = i8;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void f(y.d dVar) {
        super.f(dVar);
        boolean zC1 = H() != null ? ((d) H()).c1() : false;
        int i8 = this.f9455l1;
        if (i8 != 0) {
            if (i8 == 1) {
                int size = this.f9458o1.size();
                int i9 = 0;
                while (i9 < size) {
                    this.f9458o1.get(i9).d(zC1, i9, i9 == size + (-1));
                    i9++;
                }
            } else if (i8 == 2) {
                A1(zC1);
            }
        } else if (this.f9458o1.size() > 0) {
            this.f9458o1.get(0).d(zC1, 0, true);
        }
        X0(false);
    }

    @Override // z.h, androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void l(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.l(constraintWidget, map);
        e eVar = (e) constraintWidget;
        this.V0 = eVar.V0;
        this.W0 = eVar.W0;
        this.X0 = eVar.X0;
        this.Y0 = eVar.Y0;
        this.Z0 = eVar.Z0;
        this.f9444a1 = eVar.f9444a1;
        this.f9445b1 = eVar.f9445b1;
        this.f9446c1 = eVar.f9446c1;
        this.f9447d1 = eVar.f9447d1;
        this.f9448e1 = eVar.f9448e1;
        this.f9449f1 = eVar.f9449f1;
        this.f9450g1 = eVar.f9450g1;
        this.f9451h1 = eVar.f9451h1;
        this.f9452i1 = eVar.f9452i1;
        this.f9453j1 = eVar.f9453j1;
        this.f9454k1 = eVar.f9454k1;
        this.f9455l1 = eVar.f9455l1;
        this.f9456m1 = eVar.f9456m1;
        this.f9457n1 = eVar.f9457n1;
    }
}
