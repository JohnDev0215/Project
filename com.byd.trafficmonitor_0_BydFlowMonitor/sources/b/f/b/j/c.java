package b.f.b.j;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: compiled from: ChainHead.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ConstraintWidget f2214a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ConstraintWidget f2215b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ConstraintWidget f2216c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ConstraintWidget f2217d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ConstraintWidget f2218e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ConstraintWidget f2219f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ConstraintWidget f2220g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList<ConstraintWidget> f2221h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2222i;
    public int j;
    public float k = 0.0f;
    public int l;
    public int m;
    public int n;
    public boolean o;
    public int p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v;

    public c(ConstraintWidget constraintWidget, int i2, boolean z) {
        this.q = false;
        this.f2214a = constraintWidget;
        this.p = i2;
        this.q = z;
    }

    public static boolean c(ConstraintWidget constraintWidget, int i2) {
        if (constraintWidget.P() != 8 && constraintWidget.P[i2] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int[] iArr = constraintWidget.o;
            if (iArr[i2] == 0 || iArr[i2] == 3) {
                return true;
            }
        }
        return false;
    }

    public void a() {
        if (!this.v) {
            b();
        }
        this.v = true;
    }

    public final void b() {
        int i2 = this.p * 2;
        ConstraintWidget constraintWidget = this.f2214a;
        this.o = true;
        ConstraintWidget constraintWidget2 = constraintWidget;
        boolean z = false;
        while (!z) {
            this.f2222i++;
            ConstraintWidget[] constraintWidgetArr = constraintWidget.D0;
            int i3 = this.p;
            ConstraintWidget constraintWidget3 = null;
            constraintWidgetArr[i3] = null;
            constraintWidget.C0[i3] = null;
            if (constraintWidget.P() != 8) {
                this.l++;
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT = constraintWidget.t(this.p);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviourT != dimensionBehaviour) {
                    this.m += constraintWidget.B(this.p);
                }
                int iC = this.m + constraintWidget.M[i2].c();
                this.m = iC;
                int i4 = i2 + 1;
                this.m = iC + constraintWidget.M[i4].c();
                int iC2 = this.n + constraintWidget.M[i2].c();
                this.n = iC2;
                this.n = iC2 + constraintWidget.M[i4].c();
                if (this.f2215b == null) {
                    this.f2215b = constraintWidget;
                }
                this.f2217d = constraintWidget;
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.P;
                int i5 = this.p;
                if (dimensionBehaviourArr[i5] == dimensionBehaviour) {
                    int[] iArr = constraintWidget.o;
                    if (iArr[i5] == 0 || iArr[i5] == 3 || iArr[i5] == 2) {
                        this.j++;
                        float[] fArr = constraintWidget.B0;
                        float f2 = fArr[i5];
                        if (f2 > 0.0f) {
                            this.k += fArr[i5];
                        }
                        if (c(constraintWidget, i5)) {
                            if (f2 < 0.0f) {
                                this.r = true;
                            } else {
                                this.s = true;
                            }
                            if (this.f2221h == null) {
                                this.f2221h = new ArrayList<>();
                            }
                            this.f2221h.add(constraintWidget);
                        }
                        if (this.f2219f == null) {
                            this.f2219f = constraintWidget;
                        }
                        ConstraintWidget constraintWidget4 = this.f2220g;
                        if (constraintWidget4 != null) {
                            constraintWidget4.C0[this.p] = constraintWidget;
                        }
                        this.f2220g = constraintWidget;
                    }
                    if (this.p == 0) {
                        if (constraintWidget.m != 0 || constraintWidget.p != 0 || constraintWidget.q != 0) {
                            this.o = false;
                        }
                    } else if (constraintWidget.n != 0 || constraintWidget.s != 0 || constraintWidget.t != 0) {
                        this.o = false;
                    }
                    if (constraintWidget.T != 0.0f) {
                        this.o = false;
                        this.u = true;
                    }
                }
            }
            if (constraintWidget2 != constraintWidget) {
                constraintWidget2.D0[this.p] = constraintWidget;
            }
            ConstraintAnchor constraintAnchor = constraintWidget.M[i2 + 1].f621d;
            if (constraintAnchor != null) {
                ConstraintWidget constraintWidget5 = constraintAnchor.f619b;
                ConstraintAnchor[] constraintAnchorArr = constraintWidget5.M;
                if (constraintAnchorArr[i2].f621d != null && constraintAnchorArr[i2].f621d.f619b == constraintWidget) {
                    constraintWidget3 = constraintWidget5;
                }
            }
            if (constraintWidget3 == null) {
                constraintWidget3 = constraintWidget;
                z = true;
            }
            constraintWidget2 = constraintWidget;
            constraintWidget = constraintWidget3;
        }
        ConstraintWidget constraintWidget6 = this.f2215b;
        if (constraintWidget6 != null) {
            this.m -= constraintWidget6.M[i2].c();
        }
        ConstraintWidget constraintWidget7 = this.f2217d;
        if (constraintWidget7 != null) {
            this.m -= constraintWidget7.M[i2 + 1].c();
        }
        this.f2216c = constraintWidget;
        if (this.p == 0 && this.q) {
            this.f2218e = constraintWidget;
        } else {
            this.f2218e = this.f2214a;
        }
        this.t = this.s && this.r;
    }
}
