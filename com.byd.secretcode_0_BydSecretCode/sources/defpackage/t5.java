package defpackage;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.HashMap;

/* JADX INFO: compiled from: Barrier.java */
/* JADX INFO: loaded from: classes.dex */
public class t5 extends a6 {
    public int I0 = 0;
    public boolean J0 = true;
    public int K0 = 0;

    public boolean L0() {
        return this.J0;
    }

    public int M0() {
        return this.I0;
    }

    public int N0() {
        return this.K0;
    }

    public void O0() {
        for (int i = 0; i < this.H0; i++) {
            ConstraintWidget constraintWidget = this.G0[i];
            int i2 = this.I0;
            if (i2 == 0 || i2 == 1) {
                constraintWidget.p0(0, true);
            } else if (i2 == 2 || i2 == 3) {
                constraintWidget.p0(1, true);
            }
        }
    }

    public void P0(boolean z) {
        this.J0 = z;
    }

    public void Q0(int i) {
        this.I0 = i;
    }

    public void R0(int i) {
        this.K0 = i;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void f(n5 n5Var) {
        ConstraintAnchor[] constraintAnchorArr;
        boolean z;
        int i;
        int i2;
        int i3;
        ConstraintAnchor[] constraintAnchorArr2 = this.M;
        constraintAnchorArr2[0] = this.E;
        constraintAnchorArr2[2] = this.F;
        constraintAnchorArr2[1] = this.G;
        constraintAnchorArr2[3] = this.H;
        int i4 = 0;
        while (true) {
            constraintAnchorArr = this.M;
            if (i4 >= constraintAnchorArr.length) {
                break;
            }
            constraintAnchorArr[i4].g = n5Var.q(constraintAnchorArr[i4]);
            i4++;
        }
        int i5 = this.I0;
        if (i5 < 0 || i5 >= 4) {
            return;
        }
        ConstraintAnchor constraintAnchor = constraintAnchorArr[i5];
        for (int i6 = 0; i6 < this.H0; i6++) {
            ConstraintWidget constraintWidget = this.G0[i6];
            if ((this.J0 || constraintWidget.g()) && ((((i2 = this.I0) == 0 || i2 == 1) && constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.E.d != null && constraintWidget.G.d != null) || (((i3 = this.I0) == 2 || i3 == 3) && constraintWidget.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.F.d != null && constraintWidget.H.d != null))) {
                z = true;
                break;
            }
        }
        z = false;
        boolean z2 = this.E.i() || this.G.i();
        boolean z3 = this.F.i() || this.H.i();
        int i7 = !z && (((i = this.I0) == 0 && z2) || ((i == 2 && z3) || ((i == 1 && z2) || (i == 3 && z3)))) ? 5 : 4;
        for (int i8 = 0; i8 < this.H0; i8++) {
            ConstraintWidget constraintWidget2 = this.G0[i8];
            if (this.J0 || constraintWidget2.g()) {
                SolverVariable solverVariableQ = n5Var.q(constraintWidget2.M[this.I0]);
                ConstraintAnchor[] constraintAnchorArr3 = constraintWidget2.M;
                int i9 = this.I0;
                constraintAnchorArr3[i9].g = solverVariableQ;
                int i10 = (constraintAnchorArr3[i9].d == null || constraintAnchorArr3[i9].d.b != this) ? 0 : constraintAnchorArr3[i9].e + 0;
                if (i9 == 0 || i9 == 2) {
                    n5Var.i(constraintAnchor.g, solverVariableQ, this.K0 - i10, z);
                } else {
                    n5Var.g(constraintAnchor.g, solverVariableQ, this.K0 + i10, z);
                }
                n5Var.e(constraintAnchor.g, solverVariableQ, this.K0 + i10, i7);
            }
        }
        int i11 = this.I0;
        if (i11 == 0) {
            n5Var.e(this.G.g, this.E.g, 0, 8);
            n5Var.e(this.E.g, this.Q.G.g, 0, 4);
            n5Var.e(this.E.g, this.Q.E.g, 0, 0);
            return;
        }
        if (i11 == 1) {
            n5Var.e(this.E.g, this.G.g, 0, 8);
            n5Var.e(this.E.g, this.Q.E.g, 0, 4);
            n5Var.e(this.E.g, this.Q.G.g, 0, 0);
        } else if (i11 == 2) {
            n5Var.e(this.H.g, this.F.g, 0, 8);
            n5Var.e(this.F.g, this.Q.H.g, 0, 4);
            n5Var.e(this.F.g, this.Q.F.g, 0, 0);
        } else if (i11 == 3) {
            n5Var.e(this.F.g, this.H.g, 0, 8);
            n5Var.e(this.F.g, this.Q.F.g, 0, 4);
            n5Var.e(this.F.g, this.Q.H.g, 0, 0);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean g() {
        return true;
    }

    @Override // defpackage.a6, androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void l(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.l(constraintWidget, map);
        t5 t5Var = (t5) constraintWidget;
        this.I0 = t5Var.I0;
        this.J0 = t5Var.J0;
        this.K0 = t5Var.K0;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public String toString() {
        String str = "[Barrier] " + s() + " {";
        for (int i = 0; i < this.H0; i++) {
            ConstraintWidget constraintWidget = this.G0[i];
            if (i > 0) {
                str = str + ", ";
            }
            str = str + constraintWidget.s();
        }
        return str + "}";
    }
}
