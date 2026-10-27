package defpackage;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.HashMap;

/* JADX INFO: compiled from: Guideline.java */
/* JADX INFO: loaded from: classes.dex */
public class y5 extends ConstraintWidget {
    public float G0 = -1.0f;
    public int H0 = -1;
    public int I0 = -1;
    public ConstraintAnchor J0 = this.F;
    public int K0 = 0;
    public int L0 = 0;

    /* JADX INFO: compiled from: Guideline.java */
    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public y5() {
        this.N.clear();
        this.N.add(this.J0);
        int length = this.M.length;
        for (int i = 0; i < length; i++) {
            this.M[i] = this.J0;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void K0(n5 n5Var) {
        if (H() == null) {
            return;
        }
        int iX = n5Var.x(this.J0);
        if (this.K0 == 1) {
            G0(iX);
            H0(0);
            i0(H().w());
            F0(0);
            return;
        }
        G0(0);
        H0(iX);
        F0(H().Q());
        i0(0);
    }

    public int L0() {
        return this.K0;
    }

    public int M0() {
        return this.H0;
    }

    public int N0() {
        return this.I0;
    }

    public float O0() {
        return this.G0;
    }

    public void P0(int i) {
        if (i > -1) {
            this.G0 = -1.0f;
            this.H0 = i;
            this.I0 = -1;
        }
    }

    public void Q0(int i) {
        if (i > -1) {
            this.G0 = -1.0f;
            this.H0 = -1;
            this.I0 = i;
        }
    }

    public void R0(float f) {
        if (f > -1.0f) {
            this.G0 = f;
            this.H0 = -1;
            this.I0 = -1;
        }
    }

    public void S0(int i) {
        if (this.K0 == i) {
            return;
        }
        this.K0 = i;
        this.N.clear();
        if (this.K0 == 1) {
            this.J0 = this.E;
        } else {
            this.J0 = this.F;
        }
        this.N.add(this.J0);
        int length = this.M.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.M[i2] = this.J0;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void f(n5 n5Var) {
        w5 w5Var = (w5) H();
        if (w5Var == null) {
            return;
        }
        ConstraintAnchor constraintAnchorN = w5Var.n(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor constraintAnchorN2 = w5Var.n(ConstraintAnchor.Type.RIGHT);
        ConstraintWidget constraintWidget = this.Q;
        boolean z = constraintWidget != null && constraintWidget.P[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (this.K0 == 0) {
            constraintAnchorN = w5Var.n(ConstraintAnchor.Type.TOP);
            constraintAnchorN2 = w5Var.n(ConstraintAnchor.Type.BOTTOM);
            ConstraintWidget constraintWidget2 = this.Q;
            z = constraintWidget2 != null && constraintWidget2.P[1] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        if (this.H0 != -1) {
            SolverVariable solverVariableQ = n5Var.q(this.J0);
            n5Var.e(solverVariableQ, n5Var.q(constraintAnchorN), this.H0, 8);
            if (z) {
                n5Var.h(n5Var.q(constraintAnchorN2), solverVariableQ, 0, 5);
                return;
            }
            return;
        }
        if (this.I0 == -1) {
            if (this.G0 != -1.0f) {
                n5Var.d(n5.s(n5Var, n5Var.q(this.J0), n5Var.q(constraintAnchorN2), this.G0));
                return;
            }
            return;
        }
        SolverVariable solverVariableQ2 = n5Var.q(this.J0);
        SolverVariable solverVariableQ3 = n5Var.q(constraintAnchorN2);
        n5Var.e(solverVariableQ2, solverVariableQ3, -this.I0, 8);
        if (z) {
            n5Var.h(solverVariableQ2, n5Var.q(constraintAnchorN), 0, 5);
            n5Var.h(solverVariableQ3, solverVariableQ2, 0, 5);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean g() {
        return true;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void l(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.l(constraintWidget, map);
        y5 y5Var = (y5) constraintWidget;
        this.G0 = y5Var.G0;
        this.H0 = y5Var.H0;
        this.I0 = y5Var.I0;
        S0(y5Var.K0);
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public ConstraintAnchor n(ConstraintAnchor.Type type) {
        switch (a.a[type.ordinal()]) {
            case 1:
            case 2:
                if (this.K0 == 1) {
                    return this.J0;
                }
                break;
            case 3:
            case 4:
                if (this.K0 == 0) {
                    return this.J0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
        }
        throw new AssertionError(type.name());
    }
}
