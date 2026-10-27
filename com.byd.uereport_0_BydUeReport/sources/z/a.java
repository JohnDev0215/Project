package z;

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
        for (int i8 = 0; i8 < this.H0; i8++) {
            ConstraintWidget constraintWidget = this.G0[i8];
            int i9 = this.I0;
            if (i9 == 0 || i9 == 1) {
                constraintWidget.p0(0, true);
            } else if (i9 == 2 || i9 == 3) {
                constraintWidget.p0(1, true);
            }
        }
    }

    public void P0(boolean z7) {
        this.J0 = z7;
    }

    public void Q0(int i8) {
        this.I0 = i8;
    }

    public void R0(int i8) {
        this.K0 = i8;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void f(y.d dVar) {
        ConstraintAnchor[] constraintAnchorArr;
        boolean z7;
        int i8;
        int i9;
        int i10;
        ConstraintAnchor[] constraintAnchorArr2 = this.M;
        constraintAnchorArr2[0] = this.E;
        constraintAnchorArr2[2] = this.F;
        constraintAnchorArr2[1] = this.G;
        constraintAnchorArr2[3] = this.H;
        int i11 = 0;
        while (true) {
            constraintAnchorArr = this.M;
            if (i11 >= constraintAnchorArr.length) {
                break;
            }
            constraintAnchorArr[i11].f922g = dVar.q(constraintAnchorArr[i11]);
            i11++;
        }
        int i12 = this.I0;
        if (i12 < 0 || i12 >= 4) {
            return;
        }
        ConstraintAnchor constraintAnchor = constraintAnchorArr[i12];
        for (int i13 = 0; i13 < this.H0; i13++) {
            ConstraintWidget constraintWidget = this.G0[i13];
            if ((this.J0 || constraintWidget.g()) && ((((i9 = this.I0) == 0 || i9 == 1) && constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.E.f919d != null && constraintWidget.G.f919d != null) || (((i10 = this.I0) == 2 || i10 == 3) && constraintWidget.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.F.f919d != null && constraintWidget.H.f919d != null))) {
                z7 = true;
                break;
            }
        }
        z7 = false;
        boolean z8 = this.E.i() || this.G.i();
        boolean z9 = this.F.i() || this.H.i();
        int i14 = !z7 && (((i8 = this.I0) == 0 && z8) || ((i8 == 2 && z9) || ((i8 == 1 && z8) || (i8 == 3 && z9)))) ? 5 : 4;
        for (int i15 = 0; i15 < this.H0; i15++) {
            ConstraintWidget constraintWidget2 = this.G0[i15];
            if (this.J0 || constraintWidget2.g()) {
                SolverVariable solverVariableQ = dVar.q(constraintWidget2.M[this.I0]);
                ConstraintAnchor[] constraintAnchorArr3 = constraintWidget2.M;
                int i16 = this.I0;
                constraintAnchorArr3[i16].f922g = solverVariableQ;
                int i17 = (constraintAnchorArr3[i16].f919d == null || constraintAnchorArr3[i16].f919d.f917b != this) ? 0 : constraintAnchorArr3[i16].f920e + 0;
                if (i16 == 0 || i16 == 2) {
                    dVar.i(constraintAnchor.f922g, solverVariableQ, this.K0 - i17, z7);
                } else {
                    dVar.g(constraintAnchor.f922g, solverVariableQ, this.K0 + i17, z7);
                }
                dVar.e(constraintAnchor.f922g, solverVariableQ, this.K0 + i17, i14);
            }
        }
        int i18 = this.I0;
        if (i18 == 0) {
            dVar.e(this.G.f922g, this.E.f922g, 0, 8);
            dVar.e(this.E.f922g, this.Q.G.f922g, 0, 4);
            dVar.e(this.E.f922g, this.Q.E.f922g, 0, 0);
            return;
        }
        if (i18 == 1) {
            dVar.e(this.E.f922g, this.G.f922g, 0, 8);
            dVar.e(this.E.f922g, this.Q.E.f922g, 0, 4);
            dVar.e(this.E.f922g, this.Q.G.f922g, 0, 0);
        } else if (i18 == 2) {
            dVar.e(this.H.f922g, this.F.f922g, 0, 8);
            dVar.e(this.F.f922g, this.Q.H.f922g, 0, 4);
            dVar.e(this.F.f922g, this.Q.F.f922g, 0, 0);
        } else if (i18 == 3) {
            dVar.e(this.F.f922g, this.H.f922g, 0, 8);
            dVar.e(this.F.f922g, this.Q.F.f922g, 0, 4);
            dVar.e(this.F.f922g, this.Q.H.f922g, 0, 0);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean g() {
        return true;
    }

    @Override // z.h, androidx.constraintlayout.solver.widgets.ConstraintWidget
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
        for (int i8 = 0; i8 < this.H0; i8++) {
            ConstraintWidget constraintWidget = this.G0[i8];
            if (i8 > 0) {
                str = str + ", ";
            }
            str = str + constraintWidget.s();
        }
        return str + "}";
    }
}
