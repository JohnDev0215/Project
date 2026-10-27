package b.e.b.j;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: compiled from: ChainHead.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ConstraintWidget f1402a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ConstraintWidget f1403b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ConstraintWidget f1404c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ConstraintWidget f1405d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ConstraintWidget f1406e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ConstraintWidget f1407f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ConstraintWidget f1408g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList<ConstraintWidget> f1409h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1410i;
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
        this.f1402a = constraintWidget;
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
        ConstraintWidget constraintWidget = this.f1402a;
        this.o = true;
        ConstraintWidget constraintWidget2 = constraintWidget;
        boolean z = false;
        while (!z) {
            this.f1410i++;
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
                if (this.f1403b == null) {
                    this.f1403b = constraintWidget;
                }
                this.f1405d = constraintWidget;
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
                            if (this.f1409h == null) {
                                this.f1409h = new ArrayList<>();
                            }
                            this.f1409h.add(constraintWidget);
                        }
                        if (this.f1407f == null) {
                            this.f1407f = constraintWidget;
                        }
                        ConstraintWidget constraintWidget4 = this.f1408g;
                        if (constraintWidget4 != null) {
                            constraintWidget4.C0[this.p] = constraintWidget;
                        }
                        this.f1408g = constraintWidget;
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
            ConstraintAnchor constraintAnchor = constraintWidget.M[i2 + 1].f332d;
            if (constraintAnchor != null) {
                ConstraintWidget constraintWidget5 = constraintAnchor.f330b;
                ConstraintAnchor[] constraintAnchorArr = constraintWidget5.M;
                if (constraintAnchorArr[i2].f332d != null && constraintAnchorArr[i2].f332d.f330b == constraintWidget) {
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
        ConstraintWidget constraintWidget6 = this.f1403b;
        if (constraintWidget6 != null) {
            this.m -= constraintWidget6.M[i2].c();
        }
        ConstraintWidget constraintWidget7 = this.f1405d;
        if (constraintWidget7 != null) {
            this.m -= constraintWidget7.M[i2 + 1].c();
        }
        this.f1404c = constraintWidget;
        if (this.p == 0 && this.q) {
            this.f1406e = constraintWidget;
        } else {
            this.f1406e = this.f1402a;
        }
        this.t = this.s && this.r;
    }
}
