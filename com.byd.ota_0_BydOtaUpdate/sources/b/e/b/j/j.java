package b.e.b.j;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import b.e.b.j.l.b;

/* JADX INFO: compiled from: VirtualLayout.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends h {
    public int I0 = 0;
    public int J0 = 0;
    public int K0 = 0;
    public int L0 = 0;
    public int M0 = 0;
    public int N0 = 0;
    public int O0 = 0;
    public int P0 = 0;
    public boolean Q0 = false;
    public int R0 = 0;
    public int S0 = 0;
    public b.a T0 = new b.a();
    public b.InterfaceC0016b U0 = null;

    public void L0(boolean z) {
        int i2 = this.M0;
        if (i2 > 0 || this.N0 > 0) {
            if (z) {
                this.O0 = this.N0;
                this.P0 = i2;
            } else {
                this.O0 = i2;
                this.P0 = this.N0;
            }
        }
    }

    public void M0() {
        for (int i2 = 0; i2 < this.H0; i2++) {
            ConstraintWidget constraintWidget = this.G0[i2];
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

    public void T0(int i2, int i3, int i4, int i5) {
    }

    public void U0(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i2, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i3) {
        while (this.U0 == null && H() != null) {
            this.U0 = ((d) H()).W0();
        }
        b.a aVar = this.T0;
        aVar.f1425a = dimensionBehaviour;
        aVar.f1426b = dimensionBehaviour2;
        aVar.f1427c = i2;
        aVar.f1428d = i3;
        this.U0.a(constraintWidget, aVar);
        constraintWidget.F0(this.T0.f1429e);
        constraintWidget.i0(this.T0.f1430f);
        constraintWidget.h0(this.T0.f1432h);
        constraintWidget.c0(this.T0.f1431g);
    }

    public boolean V0() {
        ConstraintWidget constraintWidget = this.Q;
        b.InterfaceC0016b interfaceC0016bW0 = constraintWidget != null ? ((d) constraintWidget).W0() : null;
        if (interfaceC0016bW0 == null) {
            return false;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= this.H0) {
                return true;
            }
            ConstraintWidget constraintWidget2 = this.G0[i2];
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
                    aVar.f1425a = dimensionBehaviourT;
                    aVar.f1426b = dimensionBehaviourT2;
                    aVar.f1427c = constraintWidget2.Q();
                    this.T0.f1428d = constraintWidget2.w();
                    interfaceC0016bW0.a(constraintWidget2, this.T0);
                    constraintWidget2.F0(this.T0.f1429e);
                    constraintWidget2.i0(this.T0.f1430f);
                    constraintWidget2.c0(this.T0.f1431g);
                }
            }
            i2++;
        }
    }

    public boolean W0() {
        return this.Q0;
    }

    public void X0(boolean z) {
        this.Q0 = z;
    }

    public void Y0(int i2, int i3) {
        this.R0 = i2;
        this.S0 = i3;
    }

    public void Z0(int i2) {
        this.K0 = i2;
        this.I0 = i2;
        this.L0 = i2;
        this.J0 = i2;
        this.M0 = i2;
        this.N0 = i2;
    }

    public void a1(int i2) {
        this.J0 = i2;
    }

    @Override // b.e.b.j.h, b.e.b.j.g
    public void b(d dVar) {
        M0();
    }

    public void b1(int i2) {
        this.N0 = i2;
    }

    public void c1(int i2) {
        this.K0 = i2;
        this.O0 = i2;
    }

    public void d1(int i2) {
        this.L0 = i2;
        this.P0 = i2;
    }

    public void e1(int i2) {
        this.M0 = i2;
        this.O0 = i2;
        this.P0 = i2;
    }

    public void f1(int i2) {
        this.I0 = i2;
    }
}
