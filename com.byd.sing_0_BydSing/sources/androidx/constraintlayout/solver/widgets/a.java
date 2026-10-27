package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.HashMap;

/* JADX INFO: compiled from: Barrier.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends h {
    private int I0 = 0;
    private boolean J0 = true;
    private int K0 = 0;

    public boolean L0() {
        return this.J0;
    }

    public int M0() {
        return this.I0;
    }

    public int N0() {
        return this.K0;
    }

    protected void O0() {
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
    public void f(androidx.constraintlayout.solver.d dVar) {
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
            constraintAnchorArr[i4].g = dVar.q(constraintAnchorArr[i4]);
            i4++;
        }
        int i5 = this.I0;
        if (i5 < 0 || i5 >= 4) {
            return;
        }
        ConstraintAnchor constraintAnchor = constraintAnchorArr[i5];
        for (int i6 = 0; i6 < this.H0; i6++) {
            ConstraintWidget constraintWidget = this.G0[i6];
            if ((this.J0 || constraintWidget.g()) && ((((i2 = this.I0) == 0 || i2 == 1) && constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.E.f1172d != null && constraintWidget.G.f1172d != null) || (((i3 = this.I0) == 2 || i3 == 3) && constraintWidget.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.F.f1172d != null && constraintWidget.H.f1172d != null))) {
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
                SolverVariable solverVariableQ = dVar.q(constraintWidget2.M[this.I0]);
                ConstraintAnchor[] constraintAnchorArr3 = constraintWidget2.M;
                int i9 = this.I0;
                constraintAnchorArr3[i9].g = solverVariableQ;
                int i10 = (constraintAnchorArr3[i9].f1172d == null || constraintAnchorArr3[i9].f1172d.f1170b != this) ? 0 : constraintAnchorArr3[i9].e + 0;
                if (i9 == 0 || i9 == 2) {
                    dVar.i(constraintAnchor.g, solverVariableQ, this.K0 - i10, z);
                } else {
                    dVar.g(constraintAnchor.g, solverVariableQ, this.K0 + i10, z);
                }
                dVar.e(constraintAnchor.g, solverVariableQ, this.K0 + i10, i7);
            }
        }
        int i11 = this.I0;
        if (i11 == 0) {
            dVar.e(this.G.g, this.E.g, 0, 8);
            dVar.e(this.E.g, this.Q.G.g, 0, 4);
            dVar.e(this.E.g, this.Q.E.g, 0, 0);
            return;
        }
        if (i11 == 1) {
            dVar.e(this.E.g, this.G.g, 0, 8);
            dVar.e(this.E.g, this.Q.E.g, 0, 4);
            dVar.e(this.E.g, this.Q.G.g, 0, 0);
        } else if (i11 == 2) {
            dVar.e(this.H.g, this.F.g, 0, 8);
            dVar.e(this.F.g, this.Q.H.g, 0, 4);
            dVar.e(this.F.g, this.Q.F.g, 0, 0);
        } else if (i11 == 3) {
            dVar.e(this.F.g, this.H.g, 0, 8);
            dVar.e(this.F.g, this.Q.F.g, 0, 4);
            dVar.e(this.F.g, this.Q.H.g, 0, 0);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean g() {
        return true;
    }

    @Override // androidx.constraintlayout.solver.widgets.h, androidx.constraintlayout.solver.widgets.ConstraintWidget
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
