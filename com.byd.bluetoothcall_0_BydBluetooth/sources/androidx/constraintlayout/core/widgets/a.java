package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.HashMap;

/* JADX INFO: compiled from: Barrier.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends h {
    private int W0 = 0;
    private boolean X0 = true;
    private int Y0 = 0;
    boolean Z0 = false;

    public int A1() {
        int i = this.W0;
        if (i == 0 || i == 1) {
            return 0;
        }
        return (i == 2 || i == 3) ? 1 : -1;
    }

    protected void B1() {
        for (int i = 0; i < this.V0; i++) {
            ConstraintWidget constraintWidget = this.U0[i];
            if (this.X0 || constraintWidget.h()) {
                int i2 = this.W0;
                if (i2 == 0 || i2 == 1) {
                    constraintWidget.V0(0, true);
                } else if (i2 == 2 || i2 == 3) {
                    constraintWidget.V0(1, true);
                }
            }
        }
    }

    public void C1(boolean z) {
        this.X0 = z;
    }

    public void D1(int i) {
        this.W0 = i;
    }

    public void E1(int i) {
        this.Y0 = i;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void g(androidx.constraintlayout.core.d dVar, boolean z) {
        ConstraintAnchor[] constraintAnchorArr;
        boolean z2;
        int i;
        int i2;
        int i3;
        ConstraintAnchor[] constraintAnchorArr2 = this.Z;
        constraintAnchorArr2[0] = this.R;
        constraintAnchorArr2[2] = this.S;
        constraintAnchorArr2[1] = this.T;
        constraintAnchorArr2[3] = this.U;
        int i4 = 0;
        while (true) {
            constraintAnchorArr = this.Z;
            if (i4 >= constraintAnchorArr.length) {
                break;
            }
            constraintAnchorArr[i4].i = dVar.q(constraintAnchorArr[i4]);
            i4++;
        }
        int i5 = this.W0;
        if (i5 < 0 || i5 >= 4) {
            return;
        }
        ConstraintAnchor constraintAnchor = constraintAnchorArr[i5];
        if (!this.Z0) {
            w1();
        }
        if (this.Z0) {
            this.Z0 = false;
            int i6 = this.W0;
            if (i6 == 0 || i6 == 1) {
                dVar.f(this.R.i, this.i0);
                dVar.f(this.T.i, this.i0);
                return;
            } else {
                if (i6 == 2 || i6 == 3) {
                    dVar.f(this.S.i, this.j0);
                    dVar.f(this.U.i, this.j0);
                    return;
                }
                return;
            }
        }
        for (int i7 = 0; i7 < this.V0; i7++) {
            ConstraintWidget constraintWidget = this.U0[i7];
            if ((this.X0 || constraintWidget.h()) && ((((i2 = this.W0) == 0 || i2 == 1) && constraintWidget.C() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.R.f != null && constraintWidget.T.f != null) || (((i3 = this.W0) == 2 || i3 == 3) && constraintWidget.V() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.S.f != null && constraintWidget.U.f != null))) {
                z2 = true;
                break;
            }
        }
        z2 = false;
        boolean z3 = this.R.l() || this.T.l();
        boolean z4 = this.S.l() || this.U.l();
        int i8 = !z2 && (((i = this.W0) == 0 && z3) || ((i == 2 && z4) || ((i == 1 && z3) || (i == 3 && z4)))) ? 5 : 4;
        for (int i9 = 0; i9 < this.V0; i9++) {
            ConstraintWidget constraintWidget2 = this.U0[i9];
            if (this.X0 || constraintWidget2.h()) {
                SolverVariable solverVariableQ = dVar.q(constraintWidget2.Z[this.W0]);
                ConstraintAnchor[] constraintAnchorArr3 = constraintWidget2.Z;
                int i10 = this.W0;
                constraintAnchorArr3[i10].i = solverVariableQ;
                int i11 = (constraintAnchorArr3[i10].f == null || constraintAnchorArr3[i10].f.f818d != this) ? 0 : constraintAnchorArr3[i10].g + 0;
                if (i10 == 0 || i10 == 2) {
                    dVar.i(constraintAnchor.i, solverVariableQ, this.Y0 - i11, z2);
                } else {
                    dVar.g(constraintAnchor.i, solverVariableQ, this.Y0 + i11, z2);
                }
                dVar.e(constraintAnchor.i, solverVariableQ, this.Y0 + i11, i8);
            }
        }
        int i12 = this.W0;
        if (i12 == 0) {
            dVar.e(this.T.i, this.R.i, 0, 8);
            dVar.e(this.R.i, this.d0.T.i, 0, 4);
            dVar.e(this.R.i, this.d0.R.i, 0, 0);
            return;
        }
        if (i12 == 1) {
            dVar.e(this.R.i, this.T.i, 0, 8);
            dVar.e(this.R.i, this.d0.R.i, 0, 4);
            dVar.e(this.R.i, this.d0.T.i, 0, 0);
        } else if (i12 == 2) {
            dVar.e(this.U.i, this.S.i, 0, 8);
            dVar.e(this.S.i, this.d0.U.i, 0, 4);
            dVar.e(this.S.i, this.d0.S.i, 0, 0);
        } else if (i12 == 3) {
            dVar.e(this.S.i, this.U.i, 0, 8);
            dVar.e(this.S.i, this.d0.S.i, 0, 4);
            dVar.e(this.S.i, this.d0.U.i, 0, 0);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean h() {
        return true;
    }

    @Override // androidx.constraintlayout.core.widgets.h, androidx.constraintlayout.core.widgets.ConstraintWidget
    public void n(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.n(constraintWidget, map);
        a aVar = (a) constraintWidget;
        this.W0 = aVar.W0;
        this.X0 = aVar.X0;
        this.Y0 = aVar.Y0;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean p0() {
        return this.Z0;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean q0() {
        return this.Z0;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public String toString() {
        String str = "[Barrier] " + v() + " {";
        for (int i = 0; i < this.V0; i++) {
            ConstraintWidget constraintWidget = this.U0[i];
            if (i > 0) {
                str = str + ", ";
            }
            str = str + constraintWidget.v();
        }
        return str + "}";
    }

    public boolean w1() {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        boolean z = true;
        while (true) {
            i = this.V0;
            if (i4 >= i) {
                break;
            }
            ConstraintWidget constraintWidget = this.U0[i4];
            if ((this.X0 || constraintWidget.h()) && ((((i2 = this.W0) == 0 || i2 == 1) && !constraintWidget.p0()) || (((i3 = this.W0) == 2 || i3 == 3) && !constraintWidget.q0()))) {
                z = false;
            }
            i4++;
        }
        if (!z || i <= 0) {
            return false;
        }
        int iMax = 0;
        boolean z2 = false;
        for (int i5 = 0; i5 < this.V0; i5++) {
            ConstraintWidget constraintWidget2 = this.U0[i5];
            if (this.X0 || constraintWidget2.h()) {
                if (!z2) {
                    int i6 = this.W0;
                    if (i6 == 0) {
                        iMax = constraintWidget2.q(ConstraintAnchor.Type.LEFT).e();
                    } else if (i6 == 1) {
                        iMax = constraintWidget2.q(ConstraintAnchor.Type.RIGHT).e();
                    } else if (i6 == 2) {
                        iMax = constraintWidget2.q(ConstraintAnchor.Type.TOP).e();
                    } else if (i6 == 3) {
                        iMax = constraintWidget2.q(ConstraintAnchor.Type.BOTTOM).e();
                    }
                    z2 = true;
                }
                int i7 = this.W0;
                if (i7 == 0) {
                    iMax = Math.min(iMax, constraintWidget2.q(ConstraintAnchor.Type.LEFT).e());
                } else if (i7 == 1) {
                    iMax = Math.max(iMax, constraintWidget2.q(ConstraintAnchor.Type.RIGHT).e());
                } else if (i7 == 2) {
                    iMax = Math.min(iMax, constraintWidget2.q(ConstraintAnchor.Type.TOP).e());
                } else if (i7 == 3) {
                    iMax = Math.max(iMax, constraintWidget2.q(ConstraintAnchor.Type.BOTTOM).e());
                }
            }
        }
        int i8 = iMax + this.Y0;
        int i9 = this.W0;
        if (i9 == 0 || i9 == 1) {
            I0(i8, i8);
        } else {
            L0(i8, i8);
        }
        this.Z0 = true;
        return true;
    }

    public boolean x1() {
        return this.X0;
    }

    public int y1() {
        return this.W0;
    }

    public int z1() {
        return this.Y0;
    }
}
