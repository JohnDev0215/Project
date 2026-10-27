package b.e.b.j;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.HashMap;

/* JADX INFO: compiled from: Barrier.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends h {
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
        for (int i2 = 0; i2 < this.H0; i2++) {
            ConstraintWidget constraintWidget = this.G0[i2];
            int i3 = this.I0;
            if (i3 == 0 || i3 == 1) {
                constraintWidget.p0(0, true);
            } else if (i3 == 2 || i3 == 3) {
                constraintWidget.p0(1, true);
            }
        }
    }

    public void P0(boolean z) {
        this.J0 = z;
    }

    public void Q0(int i2) {
        this.I0 = i2;
    }

    public void R0(int i2) {
        this.K0 = i2;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void f(b.e.b.d dVar) {
        ConstraintAnchor[] constraintAnchorArr;
        boolean z;
        int i2;
        int i3;
        int i4;
        ConstraintAnchor[] constraintAnchorArr2 = this.M;
        constraintAnchorArr2[0] = this.E;
        constraintAnchorArr2[2] = this.F;
        constraintAnchorArr2[1] = this.G;
        constraintAnchorArr2[3] = this.H;
        int i5 = 0;
        while (true) {
            constraintAnchorArr = this.M;
            if (i5 >= constraintAnchorArr.length) {
                break;
            }
            constraintAnchorArr[i5].f335g = dVar.q(constraintAnchorArr[i5]);
            i5++;
        }
        int i6 = this.I0;
        if (i6 < 0 || i6 >= 4) {
            return;
        }
        ConstraintAnchor constraintAnchor = constraintAnchorArr[i6];
        for (int i7 = 0; i7 < this.H0; i7++) {
            ConstraintWidget constraintWidget = this.G0[i7];
            if ((this.J0 || constraintWidget.g()) && ((((i3 = this.I0) == 0 || i3 == 1) && constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.E.f332d != null && constraintWidget.G.f332d != null) || (((i4 = this.I0) == 2 || i4 == 3) && constraintWidget.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.F.f332d != null && constraintWidget.H.f332d != null))) {
                z = true;
                break;
            }
        }
        z = false;
        boolean z2 = this.E.i() || this.G.i();
        boolean z3 = this.F.i() || this.H.i();
        int i8 = !z && (((i2 = this.I0) == 0 && z2) || ((i2 == 2 && z3) || ((i2 == 1 && z2) || (i2 == 3 && z3)))) ? 5 : 4;
        for (int i9 = 0; i9 < this.H0; i9++) {
            ConstraintWidget constraintWidget2 = this.G0[i9];
            if (this.J0 || constraintWidget2.g()) {
                SolverVariable solverVariableQ = dVar.q(constraintWidget2.M[this.I0]);
                ConstraintAnchor[] constraintAnchorArr3 = constraintWidget2.M;
                int i10 = this.I0;
                constraintAnchorArr3[i10].f335g = solverVariableQ;
                int i11 = (constraintAnchorArr3[i10].f332d == null || constraintAnchorArr3[i10].f332d.f330b != this) ? 0 : constraintAnchorArr3[i10].f333e + 0;
                if (i10 == 0 || i10 == 2) {
                    dVar.i(constraintAnchor.f335g, solverVariableQ, this.K0 - i11, z);
                } else {
                    dVar.g(constraintAnchor.f335g, solverVariableQ, this.K0 + i11, z);
                }
                dVar.e(constraintAnchor.f335g, solverVariableQ, this.K0 + i11, i8);
            }
        }
        int i12 = this.I0;
        if (i12 == 0) {
            dVar.e(this.G.f335g, this.E.f335g, 0, 8);
            dVar.e(this.E.f335g, this.Q.G.f335g, 0, 4);
            dVar.e(this.E.f335g, this.Q.E.f335g, 0, 0);
            return;
        }
        if (i12 == 1) {
            dVar.e(this.E.f335g, this.G.f335g, 0, 8);
            dVar.e(this.E.f335g, this.Q.E.f335g, 0, 4);
            dVar.e(this.E.f335g, this.Q.G.f335g, 0, 0);
        } else if (i12 == 2) {
            dVar.e(this.H.f335g, this.F.f335g, 0, 8);
            dVar.e(this.F.f335g, this.Q.H.f335g, 0, 4);
            dVar.e(this.F.f335g, this.Q.F.f335g, 0, 0);
        } else if (i12 == 3) {
            dVar.e(this.F.f335g, this.H.f335g, 0, 8);
            dVar.e(this.F.f335g, this.Q.F.f335g, 0, 4);
            dVar.e(this.F.f335g, this.Q.H.f335g, 0, 0);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean g() {
        return true;
    }

    @Override // b.e.b.j.h, androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void l(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.l(constraintWidget, map);
        a aVar = (a) constraintWidget;
        this.I0 = aVar.I0;
        this.J0 = aVar.J0;
        this.K0 = aVar.K0;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public String toString() {
        String str = "[Barrier] " + s() + " {";
        for (int i2 = 0; i2 < this.H0; i2++) {
            ConstraintWidget constraintWidget = this.G0[i2];
            if (i2 > 0) {
                str = str + ", ";
            }
            str = str + constraintWidget.s();
        }
        return str + "}";
    }
}
