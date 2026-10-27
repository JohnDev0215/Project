package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: compiled from: ChainHead.java */
/* JADX INFO: loaded from: classes.dex */
public class v5 {
    public ConstraintWidget a;
    public ConstraintWidget b;
    public ConstraintWidget c;
    public ConstraintWidget d;
    public ConstraintWidget e;
    public ConstraintWidget f;
    public ConstraintWidget g;
    public ArrayList<ConstraintWidget> h;
    public int i;
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

    public v5(ConstraintWidget constraintWidget, int i, boolean z) {
        this.q = false;
        this.a = constraintWidget;
        this.p = i;
        this.q = z;
    }

    public static boolean c(ConstraintWidget constraintWidget, int i) {
        if (constraintWidget.P() != 8 && constraintWidget.P[i] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int[] iArr = constraintWidget.o;
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

    public final void b() {
        int i = this.p * 2;
        ConstraintWidget constraintWidget = this.a;
        this.o = true;
        ConstraintWidget constraintWidget2 = constraintWidget;
        boolean z = false;
        while (!z) {
            this.i++;
            ConstraintWidget[] constraintWidgetArr = constraintWidget.D0;
            int i2 = this.p;
            ConstraintWidget constraintWidget3 = null;
            constraintWidgetArr[i2] = null;
            constraintWidget.C0[i2] = null;
            if (constraintWidget.P() != 8) {
                this.l++;
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT = constraintWidget.t(this.p);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviourT != dimensionBehaviour) {
                    this.m += constraintWidget.B(this.p);
                }
                int iC = this.m + constraintWidget.M[i].c();
                this.m = iC;
                int i3 = i + 1;
                this.m = iC + constraintWidget.M[i3].c();
                int iC2 = this.n + constraintWidget.M[i].c();
                this.n = iC2;
                this.n = iC2 + constraintWidget.M[i3].c();
                if (this.b == null) {
                    this.b = constraintWidget;
                }
                this.d = constraintWidget;
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.P;
                int i4 = this.p;
                if (dimensionBehaviourArr[i4] == dimensionBehaviour) {
                    int[] iArr = constraintWidget.o;
                    if (iArr[i4] == 0 || iArr[i4] == 3 || iArr[i4] == 2) {
                        this.j++;
                        float[] fArr = constraintWidget.B0;
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
                            constraintWidget4.C0[this.p] = constraintWidget;
                        }
                        this.g = constraintWidget;
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
            ConstraintAnchor constraintAnchor = constraintWidget.M[i + 1].d;
            if (constraintAnchor != null) {
                ConstraintWidget constraintWidget5 = constraintAnchor.b;
                ConstraintAnchor[] constraintAnchorArr = constraintWidget5.M;
                if (constraintAnchorArr[i].d != null && constraintAnchorArr[i].d.b == constraintWidget) {
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
        ConstraintWidget constraintWidget6 = this.b;
        if (constraintWidget6 != null) {
            this.m -= constraintWidget6.M[i].c();
        }
        ConstraintWidget constraintWidget7 = this.d;
        if (constraintWidget7 != null) {
            this.m -= constraintWidget7.M[i + 1].c();
        }
        this.c = constraintWidget;
        if (this.p == 0 && this.q) {
            this.e = constraintWidget;
        } else {
            this.e = this.a;
        }
        this.t = this.s && this.r;
    }
}
