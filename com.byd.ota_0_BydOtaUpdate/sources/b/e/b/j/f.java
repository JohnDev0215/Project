package b.e.b.j;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.HashMap;

/* JADX INFO: compiled from: Guideline.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends ConstraintWidget {
    public float G0 = -1.0f;
    public int H0 = -1;
    public int I0 = -1;
    public ConstraintAnchor J0 = this.F;
    public int K0 = 0;
    public int L0 = 0;

    /* JADX INFO: compiled from: Guideline.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1420a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f1420a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1420a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1420a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1420a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1420a[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1420a[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1420a[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1420a[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1420a[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public f() {
        this.N.clear();
        this.N.add(this.J0);
        int length = this.M.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.M[i2] = this.J0;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void K0(b.e.b.d dVar) {
        if (H() == null) {
            return;
        }
        int iX = dVar.x(this.J0);
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

    public void P0(int i2) {
        if (i2 > -1) {
            this.G0 = -1.0f;
            this.H0 = i2;
            this.I0 = -1;
        }
    }

    public void Q0(int i2) {
        if (i2 > -1) {
            this.G0 = -1.0f;
            this.H0 = -1;
            this.I0 = i2;
        }
    }

    public void R0(float f2) {
        if (f2 > -1.0f) {
            this.G0 = f2;
            this.H0 = -1;
            this.I0 = -1;
        }
    }

    public void S0(int i2) {
        if (this.K0 == i2) {
            return;
        }
        this.K0 = i2;
        this.N.clear();
        if (this.K0 == 1) {
            this.J0 = this.E;
        } else {
            this.J0 = this.F;
        }
        this.N.add(this.J0);
        int length = this.M.length;
        for (int i3 = 0; i3 < length; i3++) {
            this.M[i3] = this.J0;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void f(b.e.b.d dVar) {
        d dVar2 = (d) H();
        if (dVar2 == null) {
            return;
        }
        ConstraintAnchor constraintAnchorN = dVar2.n(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor constraintAnchorN2 = dVar2.n(ConstraintAnchor.Type.RIGHT);
        ConstraintWidget constraintWidget = this.Q;
        boolean z = constraintWidget != null && constraintWidget.P[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (this.K0 == 0) {
            constraintAnchorN = dVar2.n(ConstraintAnchor.Type.TOP);
            constraintAnchorN2 = dVar2.n(ConstraintAnchor.Type.BOTTOM);
            ConstraintWidget constraintWidget2 = this.Q;
            z = constraintWidget2 != null && constraintWidget2.P[1] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        if (this.H0 != -1) {
            SolverVariable solverVariableQ = dVar.q(this.J0);
            dVar.e(solverVariableQ, dVar.q(constraintAnchorN), this.H0, 8);
            if (z) {
                dVar.h(dVar.q(constraintAnchorN2), solverVariableQ, 0, 5);
                return;
            }
            return;
        }
        if (this.I0 == -1) {
            if (this.G0 != -1.0f) {
                dVar.d(b.e.b.d.s(dVar, dVar.q(this.J0), dVar.q(constraintAnchorN2), this.G0));
                return;
            }
            return;
        }
        SolverVariable solverVariableQ2 = dVar.q(this.J0);
        SolverVariable solverVariableQ3 = dVar.q(constraintAnchorN2);
        dVar.e(solverVariableQ2, solverVariableQ3, -this.I0, 8);
        if (z) {
            dVar.h(solverVariableQ2, dVar.q(constraintAnchorN), 0, 5);
            dVar.h(solverVariableQ3, solverVariableQ2, 0, 5);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean g() {
        return true;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void l(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.l(constraintWidget, map);
        f fVar = (f) constraintWidget;
        this.G0 = fVar.G0;
        this.H0 = fVar.H0;
        this.I0 = fVar.I0;
        S0(fVar.K0);
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public ConstraintAnchor n(ConstraintAnchor.Type type) {
        switch (a.f1420a[type.ordinal()]) {
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
