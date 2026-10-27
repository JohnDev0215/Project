package w;

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
        for (int i3 = 0; i3 < this.H0; i3++) {
            ConstraintWidget constraintWidget = this.G0[i3];
            int i4 = this.I0;
            if (i4 == 0 || i4 == 1) {
                constraintWidget.p0(0, true);
            } else if (i4 == 2 || i4 == 3) {
                constraintWidget.p0(1, true);
            }
        }
    }

    public void P0(boolean z3) {
        this.J0 = z3;
    }

    public void Q0(int i3) {
        this.I0 = i3;
    }

    public void R0(int i3) {
        this.K0 = i3;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void f(v.d dVar) {
        ConstraintAnchor[] constraintAnchorArr;
        boolean z3;
        int i3;
        int i4;
        int i5;
        ConstraintAnchor[] constraintAnchorArr2 = this.M;
        constraintAnchorArr2[0] = this.E;
        constraintAnchorArr2[2] = this.F;
        constraintAnchorArr2[1] = this.G;
        constraintAnchorArr2[3] = this.H;
        int i6 = 0;
        while (true) {
            constraintAnchorArr = this.M;
            if (i6 >= constraintAnchorArr.length) {
                break;
            }
            constraintAnchorArr[i6].f831g = dVar.q(constraintAnchorArr[i6]);
            i6++;
        }
        int i7 = this.I0;
        if (i7 < 0 || i7 >= 4) {
            return;
        }
        ConstraintAnchor constraintAnchor = constraintAnchorArr[i7];
        for (int i8 = 0; i8 < this.H0; i8++) {
            ConstraintWidget constraintWidget = this.G0[i8];
            if ((this.J0 || constraintWidget.g()) && ((((i4 = this.I0) == 0 || i4 == 1) && constraintWidget.z() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.E.f828d != null && constraintWidget.G.f828d != null) || (((i5 = this.I0) == 2 || i5 == 3) && constraintWidget.N() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.F.f828d != null && constraintWidget.H.f828d != null))) {
                z3 = true;
                break;
            }
        }
        z3 = false;
        boolean z4 = this.E.i() || this.G.i();
        boolean z5 = this.F.i() || this.H.i();
        int i9 = !z3 && (((i3 = this.I0) == 0 && z4) || ((i3 == 2 && z5) || ((i3 == 1 && z4) || (i3 == 3 && z5)))) ? 5 : 4;
        for (int i10 = 0; i10 < this.H0; i10++) {
            ConstraintWidget constraintWidget2 = this.G0[i10];
            if (this.J0 || constraintWidget2.g()) {
                SolverVariable solverVariableQ = dVar.q(constraintWidget2.M[this.I0]);
                ConstraintAnchor[] constraintAnchorArr3 = constraintWidget2.M;
                int i11 = this.I0;
                constraintAnchorArr3[i11].f831g = solverVariableQ;
                int i12 = (constraintAnchorArr3[i11].f828d == null || constraintAnchorArr3[i11].f828d.f826b != this) ? 0 : constraintAnchorArr3[i11].f829e + 0;
                if (i11 == 0 || i11 == 2) {
                    dVar.i(constraintAnchor.f831g, solverVariableQ, this.K0 - i12, z3);
                } else {
                    dVar.g(constraintAnchor.f831g, solverVariableQ, this.K0 + i12, z3);
                }
                dVar.e(constraintAnchor.f831g, solverVariableQ, this.K0 + i12, i9);
            }
        }
        int i13 = this.I0;
        if (i13 == 0) {
            dVar.e(this.G.f831g, this.E.f831g, 0, 8);
            dVar.e(this.E.f831g, this.Q.G.f831g, 0, 4);
            dVar.e(this.E.f831g, this.Q.E.f831g, 0, 0);
            return;
        }
        if (i13 == 1) {
            dVar.e(this.E.f831g, this.G.f831g, 0, 8);
            dVar.e(this.E.f831g, this.Q.E.f831g, 0, 4);
            dVar.e(this.E.f831g, this.Q.G.f831g, 0, 0);
        } else if (i13 == 2) {
            dVar.e(this.H.f831g, this.F.f831g, 0, 8);
            dVar.e(this.F.f831g, this.Q.H.f831g, 0, 4);
            dVar.e(this.F.f831g, this.Q.F.f831g, 0, 0);
        } else if (i13 == 3) {
            dVar.e(this.F.f831g, this.H.f831g, 0, 8);
            dVar.e(this.F.f831g, this.Q.F.f831g, 0, 4);
            dVar.e(this.F.f831g, this.Q.H.f831g, 0, 0);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean g() {
        return true;
    }

    @Override // w.h, androidx.constraintlayout.solver.widgets.ConstraintWidget
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
        for (int i3 = 0; i3 < this.H0; i3++) {
            ConstraintWidget constraintWidget = this.G0[i3];
            if (i3 > 0) {
                str = str + ", ";
            }
            str = str + constraintWidget.s();
        }
        return str + "}";
    }
}
