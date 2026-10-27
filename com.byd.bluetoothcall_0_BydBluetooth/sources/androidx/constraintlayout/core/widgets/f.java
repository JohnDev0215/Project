package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.HashMap;

/* JADX INFO: compiled from: Guideline.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends ConstraintWidget {
    protected float U0 = -1.0f;
    protected int V0 = -1;
    protected int W0 = -1;
    protected boolean X0 = true;
    private ConstraintAnchor Y0 = this.S;
    private int Z0 = 0;
    private int a1 = 0;
    private boolean b1;

    /* JADX INFO: compiled from: Guideline.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f886a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f886a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f886a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f886a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f886a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f886a[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f886a[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f886a[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f886a[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f886a[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public f() {
        this.a0.clear();
        this.a0.add(this.Y0);
        int length = this.Z.length;
        for (int i = 0; i < length; i++) {
            this.Z[i] = this.Y0;
        }
    }

    public void A1(int i) {
        if (i > -1) {
            this.U0 = -1.0f;
            this.V0 = i;
            this.W0 = -1;
        }
    }

    public void B1(int i) {
        if (i > -1) {
            this.U0 = -1.0f;
            this.V0 = -1;
            this.W0 = i;
        }
    }

    public void C1(float f) {
        if (f > -1.0f) {
            this.U0 = f;
            this.V0 = -1;
            this.W0 = -1;
        }
    }

    public void D1(int i) {
        if (this.Z0 == i) {
            return;
        }
        this.Z0 = i;
        this.a0.clear();
        if (this.Z0 == 1) {
            this.Y0 = this.R;
        } else {
            this.Y0 = this.S;
        }
        this.a0.add(this.Y0);
        int length = this.Z.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.Z[i2] = this.Y0;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void g(androidx.constraintlayout.core.d dVar, boolean z) {
        d dVar2 = (d) M();
        if (dVar2 == null) {
            return;
        }
        ConstraintAnchor constraintAnchorQ = dVar2.q(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor constraintAnchorQ2 = dVar2.q(ConstraintAnchor.Type.RIGHT);
        ConstraintWidget constraintWidget = this.d0;
        boolean z2 = constraintWidget != null && constraintWidget.c0[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (this.Z0 == 0) {
            constraintAnchorQ = dVar2.q(ConstraintAnchor.Type.TOP);
            constraintAnchorQ2 = dVar2.q(ConstraintAnchor.Type.BOTTOM);
            ConstraintWidget constraintWidget2 = this.d0;
            z2 = constraintWidget2 != null && constraintWidget2.c0[1] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        if (this.b1 && this.Y0.n()) {
            SolverVariable solverVariableQ = dVar.q(this.Y0);
            dVar.f(solverVariableQ, this.Y0.e());
            if (this.V0 != -1) {
                if (z2) {
                    dVar.h(dVar.q(constraintAnchorQ2), solverVariableQ, 0, 5);
                }
            } else if (this.W0 != -1 && z2) {
                SolverVariable solverVariableQ2 = dVar.q(constraintAnchorQ2);
                dVar.h(solverVariableQ, dVar.q(constraintAnchorQ), 0, 5);
                dVar.h(solverVariableQ2, solverVariableQ, 0, 5);
            }
            this.b1 = false;
            return;
        }
        if (this.V0 != -1) {
            SolverVariable solverVariableQ3 = dVar.q(this.Y0);
            dVar.e(solverVariableQ3, dVar.q(constraintAnchorQ), this.V0, 8);
            if (z2) {
                dVar.h(dVar.q(constraintAnchorQ2), solverVariableQ3, 0, 5);
                return;
            }
            return;
        }
        if (this.W0 == -1) {
            if (this.U0 != -1.0f) {
                dVar.d(androidx.constraintlayout.core.d.s(dVar, dVar.q(this.Y0), dVar.q(constraintAnchorQ2), this.U0));
                return;
            }
            return;
        }
        SolverVariable solverVariableQ4 = dVar.q(this.Y0);
        SolverVariable solverVariableQ5 = dVar.q(constraintAnchorQ2);
        dVar.e(solverVariableQ4, solverVariableQ5, -this.W0, 8);
        if (z2) {
            dVar.h(solverVariableQ4, dVar.q(constraintAnchorQ), 0, 5);
            dVar.h(solverVariableQ5, solverVariableQ4, 0, 5);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean h() {
        return true;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void n(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.n(constraintWidget, map);
        f fVar = (f) constraintWidget;
        this.U0 = fVar.U0;
        this.V0 = fVar.V0;
        this.W0 = fVar.W0;
        this.X0 = fVar.X0;
        D1(fVar.Z0);
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean p0() {
        return this.b1;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public ConstraintAnchor q(ConstraintAnchor.Type type) {
        int i = a.f886a[type.ordinal()];
        if (i == 1 || i == 2) {
            if (this.Z0 == 1) {
                return this.Y0;
            }
            return null;
        }
        if ((i == 3 || i == 4) && this.Z0 == 0) {
            return this.Y0;
        }
        return null;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean q0() {
        return this.b1;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void t1(androidx.constraintlayout.core.d dVar, boolean z) {
        if (M() == null) {
            return;
        }
        int iX = dVar.x(this.Y0);
        if (this.Z0 == 1) {
            p1(iX);
            q1(0);
            O0(M().z());
            n1(0);
            return;
        }
        p1(0);
        q1(iX);
        n1(M().Y());
        O0(0);
    }

    public ConstraintAnchor u1() {
        return this.Y0;
    }

    public int v1() {
        return this.Z0;
    }

    public int w1() {
        return this.V0;
    }

    public int x1() {
        return this.W0;
    }

    public float y1() {
        return this.U0;
    }

    public void z1(int i) {
        this.Y0.t(i);
        this.b1 = true;
    }
}
