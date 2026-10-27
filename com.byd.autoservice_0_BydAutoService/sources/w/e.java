package w;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: Flow.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends j {

    /* JADX INFO: renamed from: s1, reason: collision with root package name */
    public ConstraintWidget[] f5495s1;
    public int V0 = -1;
    public int W0 = -1;
    public int X0 = -1;
    public int Y0 = -1;
    public int Z0 = -1;

    /* JADX INFO: renamed from: a1, reason: collision with root package name */
    public int f5477a1 = -1;

    /* JADX INFO: renamed from: b1, reason: collision with root package name */
    public float f5478b1 = 0.5f;

    /* JADX INFO: renamed from: c1, reason: collision with root package name */
    public float f5479c1 = 0.5f;

    /* JADX INFO: renamed from: d1, reason: collision with root package name */
    public float f5480d1 = 0.5f;

    /* JADX INFO: renamed from: e1, reason: collision with root package name */
    public float f5481e1 = 0.5f;

    /* JADX INFO: renamed from: f1, reason: collision with root package name */
    public float f5482f1 = 0.5f;

    /* JADX INFO: renamed from: g1, reason: collision with root package name */
    public float f5483g1 = 0.5f;

    /* JADX INFO: renamed from: h1, reason: collision with root package name */
    public int f5484h1 = 0;

    /* JADX INFO: renamed from: i1, reason: collision with root package name */
    public int f5485i1 = 0;

    /* JADX INFO: renamed from: j1, reason: collision with root package name */
    public int f5486j1 = 2;

    /* JADX INFO: renamed from: k1, reason: collision with root package name */
    public int f5487k1 = 2;

    /* JADX INFO: renamed from: l1, reason: collision with root package name */
    public int f5488l1 = 0;

    /* JADX INFO: renamed from: m1, reason: collision with root package name */
    public int f5489m1 = -1;

    /* JADX INFO: renamed from: n1, reason: collision with root package name */
    public int f5490n1 = 0;

    /* JADX INFO: renamed from: o1, reason: collision with root package name */
    public ArrayList<a> f5491o1 = new ArrayList<>();

    /* JADX INFO: renamed from: p1, reason: collision with root package name */
    public ConstraintWidget[] f5492p1 = null;

    /* JADX INFO: renamed from: q1, reason: collision with root package name */
    public ConstraintWidget[] f5493q1 = null;

    /* JADX INFO: renamed from: r1, reason: collision with root package name */
    public int[] f5494r1 = null;

    /* JADX INFO: renamed from: t1, reason: collision with root package name */
    public int f5496t1 = 0;

    /* JADX INFO: compiled from: Flow.java */
    public class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5497a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ConstraintAnchor f5500d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ConstraintAnchor f5501e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public ConstraintAnchor f5502f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ConstraintAnchor f5503g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f5504h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f5505i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f5506j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f5507k;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f5513q;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ConstraintWidget f5498b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5499c = 0;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int f5508l = 0;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f5509m = 0;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public int f5510n = 0;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public int f5511o = 0;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public int f5512p = 0;

        public a(int i3, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i4) {
            this.f5497a = 0;
            this.f5504h = 0;
            this.f5505i = 0;
            this.f5506j = 0;
            this.f5507k = 0;
            this.f5513q = 0;
            this.f5497a = i3;
            this.f5500d = constraintAnchor;
            this.f5501e = constraintAnchor2;
            this.f5502f = constraintAnchor3;
            this.f5503g = constraintAnchor4;
            this.f5504h = e.this.Q0();
            this.f5505i = e.this.S0();
            this.f5506j = e.this.R0();
            this.f5507k = e.this.P0();
            this.f5513q = i4;
        }

        public void b(ConstraintWidget constraintWidget) {
            if (this.f5497a == 0) {
                int iC1 = e.this.C1(constraintWidget, this.f5513q);
                if (constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.f5512p++;
                    iC1 = 0;
                }
                this.f5508l += iC1 + (constraintWidget.P() != 8 ? e.this.f5484h1 : 0);
                int iB1 = e.this.B1(constraintWidget, this.f5513q);
                if (this.f5498b == null || this.f5499c < iB1) {
                    this.f5498b = constraintWidget;
                    this.f5499c = iB1;
                    this.f5509m = iB1;
                }
            } else {
                int iC12 = e.this.C1(constraintWidget, this.f5513q);
                int iB12 = e.this.B1(constraintWidget, this.f5513q);
                if (constraintWidget.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.f5512p++;
                    iB12 = 0;
                }
                this.f5509m += iB12 + (constraintWidget.P() != 8 ? e.this.f5485i1 : 0);
                if (this.f5498b == null || this.f5499c < iC12) {
                    this.f5498b = constraintWidget;
                    this.f5499c = iC12;
                    this.f5508l = iC12;
                }
            }
            this.f5511o++;
        }

        public void c() {
            this.f5499c = 0;
            this.f5498b = null;
            this.f5508l = 0;
            this.f5509m = 0;
            this.f5510n = 0;
            this.f5511o = 0;
            this.f5512p = 0;
        }

        public void d(boolean z3, int i3, boolean z4) {
            ConstraintWidget constraintWidget;
            char c3;
            int i4 = this.f5511o;
            for (int i5 = 0; i5 < i4 && this.f5510n + i5 < e.this.f5496t1; i5++) {
                ConstraintWidget constraintWidget2 = e.this.f5495s1[this.f5510n + i5];
                if (constraintWidget2 != null) {
                    constraintWidget2.a0();
                }
            }
            if (i4 == 0 || this.f5498b == null) {
                return;
            }
            boolean z5 = z4 && i3 == 0;
            int i6 = -1;
            int i7 = -1;
            for (int i8 = 0; i8 < i4; i8++) {
                int i9 = z3 ? (i4 - 1) - i8 : i8;
                if (this.f5510n + i9 >= e.this.f5496t1) {
                    break;
                }
                if (e.this.f5495s1[this.f5510n + i9].P() == 0) {
                    if (i6 == -1) {
                        i6 = i8;
                    }
                    i7 = i8;
                }
            }
            ConstraintWidget constraintWidget3 = null;
            if (this.f5497a != 0) {
                ConstraintWidget constraintWidget4 = this.f5498b;
                constraintWidget4.k0(e.this.V0);
                int i10 = this.f5504h;
                if (i3 > 0) {
                    i10 += e.this.f5484h1;
                }
                if (z3) {
                    constraintWidget4.G.a(this.f5502f, i10);
                    if (z4) {
                        constraintWidget4.E.a(this.f5500d, this.f5506j);
                    }
                    if (i3 > 0) {
                        this.f5502f.f826b.E.a(constraintWidget4.G, 0);
                    }
                } else {
                    constraintWidget4.E.a(this.f5500d, i10);
                    if (z4) {
                        constraintWidget4.G.a(this.f5502f, this.f5506j);
                    }
                    if (i3 > 0) {
                        this.f5500d.f826b.G.a(constraintWidget4.E, 0);
                    }
                }
                int i11 = 0;
                while (i11 < i4 && this.f5510n + i11 < e.this.f5496t1) {
                    ConstraintWidget constraintWidget5 = e.this.f5495s1[this.f5510n + i11];
                    if (i11 == 0) {
                        constraintWidget5.j(constraintWidget5.F, this.f5501e, this.f5505i);
                        int i12 = e.this.W0;
                        float f3 = e.this.f5479c1;
                        if (this.f5510n == 0 && e.this.Y0 != -1) {
                            i12 = e.this.Y0;
                            f3 = e.this.f5481e1;
                        } else if (z4 && e.this.f5477a1 != -1) {
                            i12 = e.this.f5477a1;
                            f3 = e.this.f5483g1;
                        }
                        constraintWidget5.z0(i12);
                        constraintWidget5.y0(f3);
                    }
                    if (i11 == i4 - 1) {
                        constraintWidget5.j(constraintWidget5.H, this.f5503g, this.f5507k);
                    }
                    if (constraintWidget3 != null) {
                        constraintWidget5.F.a(constraintWidget3.H, e.this.f5485i1);
                        if (i11 == i6) {
                            constraintWidget5.F.n(this.f5505i);
                        }
                        constraintWidget3.H.a(constraintWidget5.F, 0);
                        if (i11 == i7 + 1) {
                            constraintWidget3.H.n(this.f5507k);
                        }
                    }
                    if (constraintWidget5 != constraintWidget4) {
                        if (z3) {
                            int i13 = e.this.f5486j1;
                            if (i13 == 0) {
                                constraintWidget5.G.a(constraintWidget4.G, 0);
                            } else if (i13 == 1) {
                                constraintWidget5.E.a(constraintWidget4.E, 0);
                            } else if (i13 == 2) {
                                constraintWidget5.E.a(constraintWidget4.E, 0);
                                constraintWidget5.G.a(constraintWidget4.G, 0);
                            }
                        } else {
                            int i14 = e.this.f5486j1;
                            if (i14 == 0) {
                                constraintWidget5.E.a(constraintWidget4.E, 0);
                            } else if (i14 == 1) {
                                constraintWidget5.G.a(constraintWidget4.G, 0);
                            } else if (i14 == 2) {
                                if (z5) {
                                    constraintWidget5.E.a(this.f5500d, this.f5504h);
                                    constraintWidget5.G.a(this.f5502f, this.f5506j);
                                } else {
                                    constraintWidget5.E.a(constraintWidget4.E, 0);
                                    constraintWidget5.G.a(constraintWidget4.G, 0);
                                }
                            }
                        }
                    }
                    i11++;
                    constraintWidget3 = constraintWidget5;
                }
                return;
            }
            ConstraintWidget constraintWidget6 = this.f5498b;
            constraintWidget6.z0(e.this.W0);
            int i15 = this.f5505i;
            if (i3 > 0) {
                i15 += e.this.f5485i1;
            }
            constraintWidget6.F.a(this.f5501e, i15);
            if (z4) {
                constraintWidget6.H.a(this.f5503g, this.f5507k);
            }
            if (i3 > 0) {
                this.f5501e.f826b.H.a(constraintWidget6.F, 0);
            }
            if (e.this.f5487k1 != 3 || constraintWidget6.T()) {
                constraintWidget = constraintWidget6;
            } else {
                for (int i16 = 0; i16 < i4; i16++) {
                    int i17 = z3 ? (i4 - 1) - i16 : i16;
                    if (this.f5510n + i17 >= e.this.f5496t1) {
                        break;
                    }
                    constraintWidget = e.this.f5495s1[this.f5510n + i17];
                    if (constraintWidget.T()) {
                        break;
                    }
                }
                constraintWidget = constraintWidget6;
            }
            int i18 = 0;
            while (i18 < i4) {
                int i19 = z3 ? (i4 - 1) - i18 : i18;
                if (this.f5510n + i19 >= e.this.f5496t1) {
                    return;
                }
                ConstraintWidget constraintWidget7 = e.this.f5495s1[this.f5510n + i19];
                if (i18 == 0) {
                    constraintWidget7.j(constraintWidget7.E, this.f5500d, this.f5504h);
                }
                if (i19 == 0) {
                    int i20 = e.this.V0;
                    float f4 = e.this.f5478b1;
                    if (this.f5510n == 0 && e.this.X0 != -1) {
                        i20 = e.this.X0;
                        f4 = e.this.f5480d1;
                    } else if (z4 && e.this.Z0 != -1) {
                        i20 = e.this.Z0;
                        f4 = e.this.f5482f1;
                    }
                    constraintWidget7.k0(i20);
                    constraintWidget7.j0(f4);
                }
                if (i18 == i4 - 1) {
                    constraintWidget7.j(constraintWidget7.G, this.f5502f, this.f5506j);
                }
                if (constraintWidget3 != null) {
                    constraintWidget7.E.a(constraintWidget3.G, e.this.f5484h1);
                    if (i18 == i6) {
                        constraintWidget7.E.n(this.f5504h);
                    }
                    constraintWidget3.G.a(constraintWidget7.E, 0);
                    if (i18 == i7 + 1) {
                        constraintWidget3.G.n(this.f5506j);
                    }
                }
                if (constraintWidget7 != constraintWidget6) {
                    c3 = 3;
                    if (e.this.f5487k1 == 3 && constraintWidget.T() && constraintWidget7 != constraintWidget && constraintWidget7.T()) {
                        constraintWidget7.I.a(constraintWidget.I, 0);
                    } else {
                        int i21 = e.this.f5487k1;
                        if (i21 == 0) {
                            constraintWidget7.F.a(constraintWidget6.F, 0);
                        } else if (i21 == 1) {
                            constraintWidget7.H.a(constraintWidget6.H, 0);
                        } else if (z5) {
                            constraintWidget7.F.a(this.f5501e, this.f5505i);
                            constraintWidget7.H.a(this.f5503g, this.f5507k);
                        } else {
                            constraintWidget7.F.a(constraintWidget6.F, 0);
                            constraintWidget7.H.a(constraintWidget6.H, 0);
                        }
                    }
                } else {
                    c3 = 3;
                }
                i18++;
                constraintWidget3 = constraintWidget7;
            }
        }

        public int e() {
            return this.f5497a == 1 ? this.f5509m - e.this.f5485i1 : this.f5509m;
        }

        public int f() {
            return this.f5497a == 0 ? this.f5508l - e.this.f5484h1 : this.f5508l;
        }

        public void g(int i3) {
            int i4 = this.f5512p;
            if (i4 == 0) {
                return;
            }
            int i5 = this.f5511o;
            int i6 = i3 / i4;
            for (int i7 = 0; i7 < i5 && this.f5510n + i7 < e.this.f5496t1; i7++) {
                ConstraintWidget constraintWidget = e.this.f5495s1[this.f5510n + i7];
                if (this.f5497a == 0) {
                    if (constraintWidget != null && constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.f857m == 0) {
                        e.this.U0(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i6, constraintWidget.N(), constraintWidget.w());
                    }
                } else if (constraintWidget != null && constraintWidget.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.f859n == 0) {
                    e.this.U0(constraintWidget, constraintWidget.z(), constraintWidget.Q(), ConstraintWidget.DimensionBehaviour.FIXED, i6);
                }
            }
            h();
        }

        public final void h() {
            this.f5508l = 0;
            this.f5509m = 0;
            this.f5498b = null;
            this.f5499c = 0;
            int i3 = this.f5511o;
            for (int i4 = 0; i4 < i3 && this.f5510n + i4 < e.this.f5496t1; i4++) {
                ConstraintWidget constraintWidget = e.this.f5495s1[this.f5510n + i4];
                if (this.f5497a == 0) {
                    int iQ = constraintWidget.Q();
                    int i5 = e.this.f5484h1;
                    if (constraintWidget.P() == 8) {
                        i5 = 0;
                    }
                    this.f5508l += iQ + i5;
                    int iB1 = e.this.B1(constraintWidget, this.f5513q);
                    if (this.f5498b == null || this.f5499c < iB1) {
                        this.f5498b = constraintWidget;
                        this.f5499c = iB1;
                        this.f5509m = iB1;
                    }
                } else {
                    int iC1 = e.this.C1(constraintWidget, this.f5513q);
                    int iB12 = e.this.B1(constraintWidget, this.f5513q);
                    int i6 = e.this.f5485i1;
                    if (constraintWidget.P() == 8) {
                        i6 = 0;
                    }
                    this.f5509m += iB12 + i6;
                    if (this.f5498b == null || this.f5499c < iC1) {
                        this.f5498b = constraintWidget;
                        this.f5499c = iC1;
                        this.f5508l = iC1;
                    }
                }
            }
        }

        public void i(int i3) {
            this.f5510n = i3;
        }

        public void j(int i3, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i4, int i5, int i6, int i7, int i8) {
            this.f5497a = i3;
            this.f5500d = constraintAnchor;
            this.f5501e = constraintAnchor2;
            this.f5502f = constraintAnchor3;
            this.f5503g = constraintAnchor4;
            this.f5504h = i4;
            this.f5505i = i5;
            this.f5506j = i6;
            this.f5507k = i7;
            this.f5513q = i8;
        }
    }

    public final void A1(boolean z3) {
        ConstraintWidget constraintWidget;
        if (this.f5494r1 == null || this.f5493q1 == null || this.f5492p1 == null) {
            return;
        }
        for (int i3 = 0; i3 < this.f5496t1; i3++) {
            this.f5495s1[i3].a0();
        }
        int[] iArr = this.f5494r1;
        int i4 = iArr[0];
        int i5 = iArr[1];
        ConstraintWidget constraintWidget2 = null;
        for (int i6 = 0; i6 < i4; i6++) {
            ConstraintWidget constraintWidget3 = this.f5493q1[z3 ? (i4 - i6) - 1 : i6];
            if (constraintWidget3 != null && constraintWidget3.P() != 8) {
                if (i6 == 0) {
                    constraintWidget3.j(constraintWidget3.E, this.E, Q0());
                    constraintWidget3.k0(this.V0);
                    constraintWidget3.j0(this.f5478b1);
                }
                if (i6 == i4 - 1) {
                    constraintWidget3.j(constraintWidget3.G, this.G, R0());
                }
                if (i6 > 0) {
                    constraintWidget3.j(constraintWidget3.E, constraintWidget2.G, this.f5484h1);
                    constraintWidget2.j(constraintWidget2.G, constraintWidget3.E, 0);
                }
                constraintWidget2 = constraintWidget3;
            }
        }
        for (int i7 = 0; i7 < i5; i7++) {
            ConstraintWidget constraintWidget4 = this.f5492p1[i7];
            if (constraintWidget4 != null && constraintWidget4.P() != 8) {
                if (i7 == 0) {
                    constraintWidget4.j(constraintWidget4.F, this.F, S0());
                    constraintWidget4.z0(this.W0);
                    constraintWidget4.y0(this.f5479c1);
                }
                if (i7 == i5 - 1) {
                    constraintWidget4.j(constraintWidget4.H, this.H, P0());
                }
                if (i7 > 0) {
                    constraintWidget4.j(constraintWidget4.F, constraintWidget2.H, this.f5485i1);
                    constraintWidget2.j(constraintWidget2.H, constraintWidget4.F, 0);
                }
                constraintWidget2 = constraintWidget4;
            }
        }
        for (int i8 = 0; i8 < i4; i8++) {
            for (int i9 = 0; i9 < i5; i9++) {
                int i10 = (i9 * i4) + i8;
                if (this.f5490n1 == 1) {
                    i10 = (i8 * i5) + i9;
                }
                ConstraintWidget[] constraintWidgetArr = this.f5495s1;
                if (i10 < constraintWidgetArr.length && (constraintWidget = constraintWidgetArr[i10]) != null && constraintWidget.P() != 8) {
                    ConstraintWidget constraintWidget5 = this.f5493q1[i8];
                    ConstraintWidget constraintWidget6 = this.f5492p1[i9];
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

    public final int B1(ConstraintWidget constraintWidget, int i3) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i4 = constraintWidget.f859n;
            if (i4 == 0) {
                return 0;
            }
            if (i4 == 2) {
                int i5 = (int) (constraintWidget.f873u * i3);
                if (i5 != constraintWidget.w()) {
                    U0(constraintWidget, constraintWidget.z(), constraintWidget.Q(), ConstraintWidget.DimensionBehaviour.FIXED, i5);
                }
                return i5;
            }
            if (i4 == 1) {
                return constraintWidget.w();
            }
            if (i4 == 3) {
                return (int) ((constraintWidget.Q() * constraintWidget.T) + 0.5f);
            }
        }
        return constraintWidget.w();
    }

    public final int C1(ConstraintWidget constraintWidget, int i3) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i4 = constraintWidget.f857m;
            if (i4 == 0) {
                return 0;
            }
            if (i4 == 2) {
                int i5 = (int) (constraintWidget.f867r * i3);
                if (i5 != constraintWidget.Q()) {
                    U0(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i5, constraintWidget.N(), constraintWidget.w());
                }
                return i5;
            }
            if (i4 == 1) {
                return constraintWidget.Q();
            }
            if (i4 == 3) {
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
        throw new UnsupportedOperationException("Method not decompiled: w.e.D1(androidx.constraintlayout.solver.widgets.ConstraintWidget[], int, int, int, int[]):void");
    }

    public final void E1(ConstraintWidget[] constraintWidgetArr, int i3, int i4, int i5, int[] iArr) {
        int i6;
        int i7;
        int i8;
        ConstraintAnchor constraintAnchor;
        int iR0;
        ConstraintAnchor constraintAnchor2;
        int iP0;
        int i9;
        if (i3 == 0) {
            return;
        }
        this.f5491o1.clear();
        a aVar = new a(i4, this.E, this.F, this.G, this.H, i5);
        this.f5491o1.add(aVar);
        if (i4 == 0) {
            i6 = 0;
            int i10 = 0;
            int i11 = 0;
            while (i11 < i3) {
                ConstraintWidget constraintWidget = constraintWidgetArr[i11];
                int iC1 = C1(constraintWidget, i5);
                if (constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i6++;
                }
                int i12 = i6;
                boolean z3 = (i10 == i5 || (this.f5484h1 + i10) + iC1 > i5) && aVar.f5498b != null;
                if (!z3 && i11 > 0 && (i9 = this.f5489m1) > 0 && i11 % i9 == 0) {
                    z3 = true;
                }
                if (z3) {
                    aVar = new a(i4, this.E, this.F, this.G, this.H, i5);
                    aVar.i(i11);
                    this.f5491o1.add(aVar);
                } else {
                    if (i11 > 0) {
                        i10 += this.f5484h1 + iC1;
                    }
                    aVar.b(constraintWidget);
                    i11++;
                    i6 = i12;
                }
                i10 = iC1;
                aVar.b(constraintWidget);
                i11++;
                i6 = i12;
            }
        } else {
            i6 = 0;
            int i13 = 0;
            int i14 = 0;
            while (i14 < i3) {
                ConstraintWidget constraintWidget2 = constraintWidgetArr[i14];
                int iB1 = B1(constraintWidget2, i5);
                if (constraintWidget2.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i6++;
                }
                int i15 = i6;
                boolean z4 = (i13 == i5 || (this.f5485i1 + i13) + iB1 > i5) && aVar.f5498b != null;
                if (!z4 && i14 > 0 && (i7 = this.f5489m1) > 0 && i14 % i7 == 0) {
                    z4 = true;
                }
                if (z4) {
                    aVar = new a(i4, this.E, this.F, this.G, this.H, i5);
                    aVar.i(i14);
                    this.f5491o1.add(aVar);
                } else {
                    if (i14 > 0) {
                        i13 += this.f5485i1 + iB1;
                    }
                    aVar.b(constraintWidget2);
                    i14++;
                    i6 = i15;
                }
                i13 = iB1;
                aVar.b(constraintWidget2);
                i14++;
                i6 = i15;
            }
        }
        int size = this.f5491o1.size();
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
        boolean z5 = dimensionBehaviourZ == dimensionBehaviour || N() == dimensionBehaviour;
        if (i6 > 0 && z5) {
            for (int i16 = 0; i16 < size; i16++) {
                a aVar2 = this.f5491o1.get(i16);
                if (i4 == 0) {
                    aVar2.g(i5 - aVar2.f());
                } else {
                    aVar2.g(i5 - aVar2.e());
                }
            }
        }
        int i17 = iS0;
        int i18 = iR02;
        int iE = 0;
        int iF = 0;
        int i19 = 0;
        int i20 = iQ0;
        ConstraintAnchor constraintAnchor7 = constraintAnchor4;
        ConstraintAnchor constraintAnchor8 = constraintAnchor3;
        int i21 = iP02;
        while (i19 < size) {
            a aVar3 = this.f5491o1.get(i19);
            if (i4 == 0) {
                if (i19 < size - 1) {
                    constraintAnchor2 = this.f5491o1.get(i19 + 1).f5498b.F;
                    iP0 = 0;
                } else {
                    constraintAnchor2 = this.H;
                    iP0 = P0();
                }
                ConstraintAnchor constraintAnchor9 = aVar3.f5498b.H;
                ConstraintAnchor constraintAnchor10 = constraintAnchor8;
                ConstraintAnchor constraintAnchor11 = constraintAnchor8;
                int i22 = iE;
                ConstraintAnchor constraintAnchor12 = constraintAnchor7;
                int i23 = iF;
                ConstraintAnchor constraintAnchor13 = constraintAnchor5;
                ConstraintAnchor constraintAnchor14 = constraintAnchor5;
                i8 = i19;
                aVar3.j(i4, constraintAnchor10, constraintAnchor12, constraintAnchor13, constraintAnchor2, i20, i17, i18, iP0, i5);
                int iMax = Math.max(i23, aVar3.f());
                iE = i22 + aVar3.e();
                if (i8 > 0) {
                    iE += this.f5485i1;
                }
                constraintAnchor8 = constraintAnchor11;
                iF = iMax;
                i17 = 0;
                constraintAnchor7 = constraintAnchor9;
                constraintAnchor = constraintAnchor14;
                int i24 = iP0;
                constraintAnchor6 = constraintAnchor2;
                i21 = i24;
            } else {
                ConstraintAnchor constraintAnchor15 = constraintAnchor8;
                int i25 = iE;
                int i26 = iF;
                i8 = i19;
                if (i8 < size - 1) {
                    constraintAnchor = this.f5491o1.get(i8 + 1).f5498b.E;
                    iR0 = 0;
                } else {
                    constraintAnchor = this.G;
                    iR0 = R0();
                }
                ConstraintAnchor constraintAnchor16 = aVar3.f5498b.G;
                aVar3.j(i4, constraintAnchor15, constraintAnchor7, constraintAnchor, constraintAnchor6, i20, i17, iR0, i21, i5);
                iF = i26 + aVar3.f();
                int iMax2 = Math.max(i25, aVar3.e());
                if (i8 > 0) {
                    iF += this.f5484h1;
                }
                iE = iMax2;
                i20 = 0;
                i18 = iR0;
                constraintAnchor8 = constraintAnchor16;
            }
            i19 = i8 + 1;
            constraintAnchor5 = constraintAnchor;
        }
        iArr[0] = iF;
        iArr[1] = iE;
    }

    public final void F1(ConstraintWidget[] constraintWidgetArr, int i3, int i4, int i5, int[] iArr) {
        a aVar;
        if (i3 == 0) {
            return;
        }
        if (this.f5491o1.size() == 0) {
            aVar = new a(i4, this.E, this.F, this.G, this.H, i5);
            this.f5491o1.add(aVar);
        } else {
            a aVar2 = this.f5491o1.get(0);
            aVar2.c();
            aVar = aVar2;
            aVar.j(i4, this.E, this.F, this.G, this.H, Q0(), S0(), R0(), P0(), i5);
        }
        for (int i6 = 0; i6 < i3; i6++) {
            aVar.b(constraintWidgetArr[i6]);
        }
        iArr[0] = aVar.f();
        iArr[1] = aVar.e();
    }

    public void G1(float f3) {
        this.f5480d1 = f3;
    }

    public void H1(int i3) {
        this.X0 = i3;
    }

    public void I1(float f3) {
        this.f5481e1 = f3;
    }

    public void J1(int i3) {
        this.Y0 = i3;
    }

    public void K1(int i3) {
        this.f5486j1 = i3;
    }

    public void L1(float f3) {
        this.f5478b1 = f3;
    }

    public void M1(int i3) {
        this.f5484h1 = i3;
    }

    public void N1(int i3) {
        this.V0 = i3;
    }

    public void O1(float f3) {
        this.f5482f1 = f3;
    }

    public void P1(int i3) {
        this.Z0 = i3;
    }

    public void Q1(float f3) {
        this.f5483g1 = f3;
    }

    public void R1(int i3) {
        this.f5477a1 = i3;
    }

    public void S1(int i3) {
        this.f5489m1 = i3;
    }

    @Override // w.j
    public void T0(int i3, int i4, int i5, int i6) {
        int i7;
        int i8;
        int[] iArr;
        boolean z3;
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
        int i9 = (i4 - iQ0) - iR0;
        int i10 = this.f5490n1;
        if (i10 == 1) {
            i9 = (i6 - iS0) - iP0;
        }
        int i11 = i9;
        if (i10 == 0) {
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
        int i12 = 0;
        int i13 = 0;
        while (true) {
            i7 = this.H0;
            if (i12 >= i7) {
                break;
            }
            if (this.G0[i12].P() == 8) {
                i13++;
            }
            i12++;
        }
        if (i13 > 0) {
            constraintWidgetArr = new ConstraintWidget[i7 - i13];
            int i14 = 0;
            for (int i15 = 0; i15 < this.H0; i15++) {
                ConstraintWidget constraintWidget = this.G0[i15];
                if (constraintWidget.P() != 8) {
                    constraintWidgetArr[i14] = constraintWidget;
                    i14++;
                }
            }
            i8 = i14;
        } else {
            i8 = i7;
        }
        this.f5495s1 = constraintWidgetArr;
        this.f5496t1 = i8;
        int i16 = this.f5488l1;
        if (i16 == 0) {
            iArr = iArr2;
            z3 = true;
            F1(constraintWidgetArr, i8, this.f5490n1, i11, iArr2);
        } else if (i16 == 1) {
            z3 = true;
            iArr = iArr2;
            E1(constraintWidgetArr, i8, this.f5490n1, i11, iArr2);
        } else if (i16 != 2) {
            z3 = true;
            iArr = iArr2;
        } else {
            z3 = true;
            iArr = iArr2;
            D1(constraintWidgetArr, i8, this.f5490n1, i11, iArr2);
        }
        int iMin = iArr[0] + iQ0 + iR0;
        int iMin2 = iArr[z3 ? 1 : 0] + iS0 + iP0;
        if (i3 == 1073741824) {
            iMin = i4;
        } else if (i3 == Integer.MIN_VALUE) {
            iMin = Math.min(iMin, i4);
        } else if (i3 != 0) {
            iMin = 0;
        }
        if (i5 == 1073741824) {
            iMin2 = i6;
        } else if (i5 == Integer.MIN_VALUE) {
            iMin2 = Math.min(iMin2, i6);
        } else if (i5 != 0) {
            iMin2 = 0;
        }
        Y0(iMin, iMin2);
        F0(iMin);
        i0(iMin2);
        if (this.H0 <= 0) {
            z3 = false;
        }
        X0(z3);
    }

    public void T1(int i3) {
        this.f5490n1 = i3;
    }

    public void U1(int i3) {
        this.f5487k1 = i3;
    }

    public void V1(float f3) {
        this.f5479c1 = f3;
    }

    public void W1(int i3) {
        this.f5485i1 = i3;
    }

    public void X1(int i3) {
        this.W0 = i3;
    }

    public void Y1(int i3) {
        this.f5488l1 = i3;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void f(v.d dVar) {
        super.f(dVar);
        boolean zC1 = H() != null ? ((d) H()).c1() : false;
        int i3 = this.f5488l1;
        if (i3 != 0) {
            if (i3 == 1) {
                int size = this.f5491o1.size();
                int i4 = 0;
                while (i4 < size) {
                    this.f5491o1.get(i4).d(zC1, i4, i4 == size + (-1));
                    i4++;
                }
            } else if (i3 == 2) {
                A1(zC1);
            }
        } else if (this.f5491o1.size() > 0) {
            this.f5491o1.get(0).d(zC1, 0, true);
        }
        X0(false);
    }

    @Override // w.h, androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void l(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.l(constraintWidget, map);
        e eVar = (e) constraintWidget;
        this.V0 = eVar.V0;
        this.W0 = eVar.W0;
        this.X0 = eVar.X0;
        this.Y0 = eVar.Y0;
        this.Z0 = eVar.Z0;
        this.f5477a1 = eVar.f5477a1;
        this.f5478b1 = eVar.f5478b1;
        this.f5479c1 = eVar.f5479c1;
        this.f5480d1 = eVar.f5480d1;
        this.f5481e1 = eVar.f5481e1;
        this.f5482f1 = eVar.f5482f1;
        this.f5483g1 = eVar.f5483g1;
        this.f5484h1 = eVar.f5484h1;
        this.f5485i1 = eVar.f5485i1;
        this.f5486j1 = eVar.f5486j1;
        this.f5487k1 = eVar.f5487k1;
        this.f5488l1 = eVar.f5488l1;
        this.f5489m1 = eVar.f5489m1;
        this.f5490n1 = eVar.f5490n1;
    }
}
