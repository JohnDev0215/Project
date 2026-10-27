package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.b;

/* JADX INFO: compiled from: VirtualLayout.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends h {
    private int I0 = 0;
    private int J0 = 0;
    private int K0 = 0;
    private int L0 = 0;
    private int M0 = 0;
    private int N0 = 0;
    private int O0 = 0;
    private int P0 = 0;
    private boolean Q0 = false;
    private int R0 = 0;
    private int S0 = 0;
    protected b.a T0 = new b.a();
    b.InterfaceC0009b U0 = null;

    public void L0(boolean z) {
        int i = this.M0;
        if (i > 0 || this.N0 > 0) {
            if (z) {
                this.O0 = this.N0;
                this.P0 = i;
            } else {
                this.O0 = i;
                this.P0 = this.N0;
            }
        }
    }

    public void M0() {
        for (int i = 0; i < this.H0; i++) {
            ConstraintWidget constraintWidget = this.G0[i];
            if (constraintWidget != null) {
                constraintWidget.r0(true);
            }
        }
    }

    public int N0() {
        return this.S0;
    }

    public int O0() {
        return this.R0;
    }

    public int P0() {
        return this.J0;
    }

    public int Q0() {
        return this.O0;
    }

    public int R0() {
        return this.P0;
    }

    public int S0() {
        return this.I0;
    }

    public void T0(int i, int i2, int i3, int i4) {
    }

    protected void U0(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i2) {
        while (this.U0 == null && H() != null) {
            this.U0 = ((d) H()).W0();
        }
        b.a aVar = this.T0;
        aVar.f759a = dimensionBehaviour;
        aVar.f760b = dimensionBehaviour2;
        aVar.f761c = i;
        aVar.f762d = i2;
        this.U0.a(constraintWidget, aVar);
        constraintWidget.F0(this.T0.f763e);
        constraintWidget.i0(this.T0.f);
        constraintWidget.h0(this.T0.h);
        constraintWidget.c0(this.T0.g);
    }

    protected boolean V0() {
        ConstraintWidget constraintWidget = this.Q;
        b.InterfaceC0009b interfaceC0009bW0 = constraintWidget != null ? ((d) constraintWidget).W0() : null;
        if (interfaceC0009bW0 == null) {
            return false;
        }
        int i = 0;
        while (true) {
            if (i >= this.H0) {
                return true;
            }
            ConstraintWidget constraintWidget2 = this.G0[i];
            if (constraintWidget2 != null && !(constraintWidget2 instanceof f)) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT = constraintWidget2.t(0);
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = constraintWidget2.t(1);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (!(dimensionBehaviourT == dimensionBehaviour && constraintWidget2.m != 1 && dimensionBehaviourT2 == dimensionBehaviour && constraintWidget2.n != 1)) {
                    if (dimensionBehaviourT == dimensionBehaviour) {
                        dimensionBehaviourT = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    if (dimensionBehaviourT2 == dimensionBehaviour) {
                        dimensionBehaviourT2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    b.a aVar = this.T0;
                    aVar.f759a = dimensionBehaviourT;
                    aVar.f760b = dimensionBehaviourT2;
                    aVar.f761c = constraintWidget2.Q();
                    this.T0.f762d = constraintWidget2.w();
                    interfaceC0009bW0.a(constraintWidget2, this.T0);
                    constraintWidget2.F0(this.T0.f763e);
                    constraintWidget2.i0(this.T0.f);
                    constraintWidget2.c0(this.T0.g);
                }
            }
            i++;
        }
    }

    public boolean W0() {
        return this.Q0;
    }

    protected void X0(boolean z) {
        this.Q0 = z;
    }

    public void Y0(int i, int i2) {
        this.R0 = i;
        this.S0 = i2;
    }

    public void Z0(int i) {
        this.K0 = i;
        this.I0 = i;
        this.L0 = i;
        this.J0 = i;
        this.M0 = i;
        this.N0 = i;
    }

    public void a1(int i) {
        this.J0 = i;
    }

    @Override // androidx.constraintlayout.solver.widgets.h, androidx.constraintlayout.solver.widgets.g
    public void b(d dVar) {
        M0();
    }

    public void b1(int i) {
        this.N0 = i;
    }

    public void c1(int i) {
        this.K0 = i;
        this.O0 = i;
    }

    public void d1(int i) {
        this.L0 = i;
        this.P0 = i;
    }

    public void e1(int i) {
        this.M0 = i;
        this.O0 = i;
        this.P0 = i;
    }

    public void f1(int i) {
        this.I0 = i;
    }
}
