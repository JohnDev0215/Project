package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: compiled from: ChainHead.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected ConstraintWidget f876a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected ConstraintWidget f877b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected ConstraintWidget f878c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected ConstraintWidget f879d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected ConstraintWidget f880e;
    protected ConstraintWidget f;
    protected ConstraintWidget g;
    protected ArrayList<ConstraintWidget> h;
    protected int i;
    protected int j;
    protected float k = 0.0f;
    int l;
    int m;
    int n;
    boolean o;
    private int p;
    private boolean q;
    protected boolean r;
    protected boolean s;
    protected boolean t;
    protected boolean u;
    private boolean v;

    public c(ConstraintWidget constraintWidget, int i, boolean z) {
        this.q = false;
        this.f876a = constraintWidget;
        this.p = i;
        this.q = z;
    }

    private void b() {
        int i = this.p * 2;
        ConstraintWidget constraintWidget = this.f876a;
        this.o = true;
        ConstraintWidget constraintWidget2 = constraintWidget;
        boolean z = false;
        while (!z) {
            this.i++;
            ConstraintWidget[] constraintWidgetArr = constraintWidget.P0;
            int i2 = this.p;
            ConstraintWidget constraintWidget3 = null;
            constraintWidgetArr[i2] = null;
            constraintWidget.O0[i2] = null;
            if (constraintWidget.X() != 8) {
                this.l++;
                ConstraintWidget.DimensionBehaviour dimensionBehaviourW = constraintWidget.w(this.p);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviourW != dimensionBehaviour) {
                    this.m += constraintWidget.G(this.p);
                }
                int iF = this.m + constraintWidget.Z[i].f();
                this.m = iF;
                int i3 = i + 1;
                this.m = iF + constraintWidget.Z[i3].f();
                int iF2 = this.n + constraintWidget.Z[i].f();
                this.n = iF2;
                this.n = iF2 + constraintWidget.Z[i3].f();
                if (this.f877b == null) {
                    this.f877b = constraintWidget;
                }
                this.f879d = constraintWidget;
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.c0;
                int i4 = this.p;
                if (dimensionBehaviourArr[i4] == dimensionBehaviour) {
                    int[] iArr = constraintWidget.z;
                    if (iArr[i4] == 0 || iArr[i4] == 3 || iArr[i4] == 2) {
                        this.j++;
                        float[] fArr = constraintWidget.N0;
                        float f = fArr[i4];
                        if (f > 0.0f) {
                            this.k += fArr[i4];
                        }
                        if (c(constraintWidget, i4)) {
                            if (f < 0.0f) {
                                this.r = true;
                            } else {
                                this.s = true;
                            }
                            if (this.h == null) {
                                this.h = new ArrayList<>();
                            }
                            this.h.add(constraintWidget);
                        }
                        if (this.f == null) {
                            this.f = constraintWidget;
                        }
                        ConstraintWidget constraintWidget4 = this.g;
                        if (constraintWidget4 != null) {
                            constraintWidget4.O0[this.p] = constraintWidget;
                        }
                        this.g = constraintWidget;
                    }
                    if (this.p == 0) {
                        if (constraintWidget.x != 0 || constraintWidget.A != 0 || constraintWidget.B != 0) {
                            this.o = false;
                        }
                    } else if (constraintWidget.y != 0 || constraintWidget.D != 0 || constraintWidget.E != 0) {
                        this.o = false;
                    }
                    if (constraintWidget.g0 != 0.0f) {
                        this.o = false;
                        this.u = true;
                    }
                }
            }
            if (constraintWidget2 != constraintWidget) {
                constraintWidget2.P0[this.p] = constraintWidget;
            }
            ConstraintAnchor constraintAnchor = constraintWidget.Z[i + 1].f;
            if (constraintAnchor != null) {
                ConstraintWidget constraintWidget5 = constraintAnchor.f818d;
                ConstraintAnchor[] constraintAnchorArr = constraintWidget5.Z;
                if (constraintAnchorArr[i].f != null && constraintAnchorArr[i].f.f818d == constraintWidget) {
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
        ConstraintWidget constraintWidget6 = this.f877b;
        if (constraintWidget6 != null) {
            this.m -= constraintWidget6.Z[i].f();
        }
        ConstraintWidget constraintWidget7 = this.f879d;
        if (constraintWidget7 != null) {
            this.m -= constraintWidget7.Z[i + 1].f();
        }
        this.f878c = constraintWidget;
        if (this.p == 0 && this.q) {
            this.f880e = constraintWidget;
        } else {
            this.f880e = this.f876a;
        }
        this.t = this.s && this.r;
    }

    private static boolean c(ConstraintWidget constraintWidget, int i) {
        if (constraintWidget.X() != 8 && constraintWidget.c0[i] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int[] iArr = constraintWidget.z;
            if (iArr[i] == 0 || iArr[i] == 3) {
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
}
