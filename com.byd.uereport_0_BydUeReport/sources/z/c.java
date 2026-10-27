package z;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: compiled from: ChainHead.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ConstraintWidget f9417a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ConstraintWidget f9418b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ConstraintWidget f9419c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ConstraintWidget f9420d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ConstraintWidget f9421e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ConstraintWidget f9422f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ConstraintWidget f9423g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList<ConstraintWidget> f9424h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f9425i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f9426j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f9427k = 0.0f;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f9428l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f9429m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f9430n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f9431o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f9432p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f9433q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f9434r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f9435s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f9436t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f9437u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f9438v;

    public c(ConstraintWidget constraintWidget, int i8, boolean z7) {
        this.f9433q = false;
        this.f9417a = constraintWidget;
        this.f9432p = i8;
        this.f9433q = z7;
    }

    public static boolean c(ConstraintWidget constraintWidget, int i8) {
        if (constraintWidget.P() != 8 && constraintWidget.P[i8] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int[] iArr = constraintWidget.f952o;
            if (iArr[i8] == 0 || iArr[i8] == 3) {
                return true;
            }
        }
        return false;
    }

    public void a() {
        if (!this.f9438v) {
            b();
        }
        this.f9438v = true;
    }

    public final void b() {
        int i8 = this.f9432p * 2;
        ConstraintWidget constraintWidget = this.f9417a;
        this.f9431o = true;
        ConstraintWidget constraintWidget2 = constraintWidget;
        boolean z7 = false;
        while (!z7) {
            this.f9425i++;
            ConstraintWidget[] constraintWidgetArr = constraintWidget.D0;
            int i9 = this.f9432p;
            ConstraintWidget constraintWidget3 = null;
            constraintWidgetArr[i9] = null;
            constraintWidget.C0[i9] = null;
            if (constraintWidget.P() != 8) {
                this.f9428l++;
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT = constraintWidget.t(this.f9432p);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviourT != dimensionBehaviour) {
                    this.f9429m += constraintWidget.B(this.f9432p);
                }
                int iC = this.f9429m + constraintWidget.M[i8].c();
                this.f9429m = iC;
                int i10 = i8 + 1;
                this.f9429m = iC + constraintWidget.M[i10].c();
                int iC2 = this.f9430n + constraintWidget.M[i8].c();
                this.f9430n = iC2;
                this.f9430n = iC2 + constraintWidget.M[i10].c();
                if (this.f9418b == null) {
                    this.f9418b = constraintWidget;
                }
                this.f9420d = constraintWidget;
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.P;
                int i11 = this.f9432p;
                if (dimensionBehaviourArr[i11] == dimensionBehaviour) {
                    int[] iArr = constraintWidget.f952o;
                    if (iArr[i11] == 0 || iArr[i11] == 3 || iArr[i11] == 2) {
                        this.f9426j++;
                        float[] fArr = constraintWidget.B0;
                        float f8 = fArr[i11];
                        if (f8 > 0.0f) {
                            this.f9427k += fArr[i11];
                        }
                        if (c(constraintWidget, i11)) {
                            if (f8 < 0.0f) {
                                this.f9434r = true;
                            } else {
                                this.f9435s = true;
                            }
                            if (this.f9424h == null) {
                                this.f9424h = new ArrayList<>();
                            }
                            this.f9424h.add(constraintWidget);
                        }
                        if (this.f9422f == null) {
                            this.f9422f = constraintWidget;
                        }
                        ConstraintWidget constraintWidget4 = this.f9423g;
                        if (constraintWidget4 != null) {
                            constraintWidget4.C0[this.f9432p] = constraintWidget;
                        }
                        this.f9423g = constraintWidget;
                    }
                    if (this.f9432p == 0) {
                        if (constraintWidget.f948m != 0 || constraintWidget.f954p != 0 || constraintWidget.f956q != 0) {
                            this.f9431o = false;
                        }
                    } else if (constraintWidget.f950n != 0 || constraintWidget.f960s != 0 || constraintWidget.f962t != 0) {
                        this.f9431o = false;
                    }
                    if (constraintWidget.T != 0.0f) {
                        this.f9431o = false;
                        this.f9437u = true;
                    }
                }
            }
            if (constraintWidget2 != constraintWidget) {
                constraintWidget2.D0[this.f9432p] = constraintWidget;
            }
            ConstraintAnchor constraintAnchor = constraintWidget.M[i8 + 1].f919d;
            if (constraintAnchor != null) {
                ConstraintWidget constraintWidget5 = constraintAnchor.f917b;
                ConstraintAnchor[] constraintAnchorArr = constraintWidget5.M;
                if (constraintAnchorArr[i8].f919d != null && constraintAnchorArr[i8].f919d.f917b == constraintWidget) {
                    constraintWidget3 = constraintWidget5;
                }
            }
            if (constraintWidget3 == null) {
                constraintWidget3 = constraintWidget;
                z7 = true;
            }
            constraintWidget2 = constraintWidget;
            constraintWidget = constraintWidget3;
        }
        ConstraintWidget constraintWidget6 = this.f9418b;
        if (constraintWidget6 != null) {
            this.f9429m -= constraintWidget6.M[i8].c();
        }
        ConstraintWidget constraintWidget7 = this.f9420d;
        if (constraintWidget7 != null) {
            this.f9429m -= constraintWidget7.M[i8 + 1].c();
        }
        this.f9419c = constraintWidget;
        if (this.f9432p == 0 && this.f9433q) {
            this.f9421e = constraintWidget;
        } else {
            this.f9421e = this.f9417a;
        }
        this.f9436t = this.f9435s && this.f9434r;
    }
}
