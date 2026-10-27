package w;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: compiled from: ChainHead.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ConstraintWidget f5450a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ConstraintWidget f5451b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ConstraintWidget f5452c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ConstraintWidget f5453d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ConstraintWidget f5454e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ConstraintWidget f5455f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ConstraintWidget f5456g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList<ConstraintWidget> f5457h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5458i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f5459j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f5460k = 0.0f;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f5461l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f5462m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f5463n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f5464o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f5465p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f5466q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f5467r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f5468s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f5469t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f5470u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f5471v;

    public c(ConstraintWidget constraintWidget, int i3, boolean z3) {
        this.f5466q = false;
        this.f5450a = constraintWidget;
        this.f5465p = i3;
        this.f5466q = z3;
    }

    public static boolean c(ConstraintWidget constraintWidget, int i3) {
        if (constraintWidget.P() != 8 && constraintWidget.P[i3] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int[] iArr = constraintWidget.f861o;
            if (iArr[i3] == 0 || iArr[i3] == 3) {
                return true;
            }
        }
        return false;
    }

    public void a() {
        if (!this.f5471v) {
            b();
        }
        this.f5471v = true;
    }

    public final void b() {
        int i3 = this.f5465p * 2;
        ConstraintWidget constraintWidget = this.f5450a;
        this.f5464o = true;
        ConstraintWidget constraintWidget2 = constraintWidget;
        boolean z3 = false;
        while (!z3) {
            this.f5458i++;
            ConstraintWidget[] constraintWidgetArr = constraintWidget.D0;
            int i4 = this.f5465p;
            ConstraintWidget constraintWidget3 = null;
            constraintWidgetArr[i4] = null;
            constraintWidget.C0[i4] = null;
            if (constraintWidget.P() != 8) {
                this.f5461l++;
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT = constraintWidget.t(this.f5465p);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviourT != dimensionBehaviour) {
                    this.f5462m += constraintWidget.B(this.f5465p);
                }
                int iC = this.f5462m + constraintWidget.M[i3].c();
                this.f5462m = iC;
                int i5 = i3 + 1;
                this.f5462m = iC + constraintWidget.M[i5].c();
                int iC2 = this.f5463n + constraintWidget.M[i3].c();
                this.f5463n = iC2;
                this.f5463n = iC2 + constraintWidget.M[i5].c();
                if (this.f5451b == null) {
                    this.f5451b = constraintWidget;
                }
                this.f5453d = constraintWidget;
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.P;
                int i6 = this.f5465p;
                if (dimensionBehaviourArr[i6] == dimensionBehaviour) {
                    int[] iArr = constraintWidget.f861o;
                    if (iArr[i6] == 0 || iArr[i6] == 3 || iArr[i6] == 2) {
                        this.f5459j++;
                        float[] fArr = constraintWidget.B0;
                        float f3 = fArr[i6];
                        if (f3 > 0.0f) {
                            this.f5460k += fArr[i6];
                        }
                        if (c(constraintWidget, i6)) {
                            if (f3 < 0.0f) {
                                this.f5467r = true;
                            } else {
                                this.f5468s = true;
                            }
                            if (this.f5457h == null) {
                                this.f5457h = new ArrayList<>();
                            }
                            this.f5457h.add(constraintWidget);
                        }
                        if (this.f5455f == null) {
                            this.f5455f = constraintWidget;
                        }
                        ConstraintWidget constraintWidget4 = this.f5456g;
                        if (constraintWidget4 != null) {
                            constraintWidget4.C0[this.f5465p] = constraintWidget;
                        }
                        this.f5456g = constraintWidget;
                    }
                    if (this.f5465p == 0) {
                        if (constraintWidget.f857m != 0 || constraintWidget.f863p != 0 || constraintWidget.f865q != 0) {
                            this.f5464o = false;
                        }
                    } else if (constraintWidget.f859n != 0 || constraintWidget.f869s != 0 || constraintWidget.f871t != 0) {
                        this.f5464o = false;
                    }
                    if (constraintWidget.T != 0.0f) {
                        this.f5464o = false;
                        this.f5470u = true;
                    }
                }
            }
            if (constraintWidget2 != constraintWidget) {
                constraintWidget2.D0[this.f5465p] = constraintWidget;
            }
            ConstraintAnchor constraintAnchor = constraintWidget.M[i3 + 1].f828d;
            if (constraintAnchor != null) {
                ConstraintWidget constraintWidget5 = constraintAnchor.f826b;
                ConstraintAnchor[] constraintAnchorArr = constraintWidget5.M;
                if (constraintAnchorArr[i3].f828d != null && constraintAnchorArr[i3].f828d.f826b == constraintWidget) {
                    constraintWidget3 = constraintWidget5;
                }
            }
            if (constraintWidget3 == null) {
                constraintWidget3 = constraintWidget;
                z3 = true;
            }
            constraintWidget2 = constraintWidget;
            constraintWidget = constraintWidget3;
        }
        ConstraintWidget constraintWidget6 = this.f5451b;
        if (constraintWidget6 != null) {
            this.f5462m -= constraintWidget6.M[i3].c();
        }
        ConstraintWidget constraintWidget7 = this.f5453d;
        if (constraintWidget7 != null) {
            this.f5462m -= constraintWidget7.M[i3 + 1].c();
        }
        this.f5452c = constraintWidget;
        if (this.f5465p == 0 && this.f5466q) {
            this.f5454e = constraintWidget;
        } else {
            this.f5454e = this.f5450a;
        }
        this.f5469t = this.f5468s && this.f5467r;
    }
}
