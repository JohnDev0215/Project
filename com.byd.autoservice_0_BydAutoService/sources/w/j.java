package w;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import x.b;

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
    public b.InterfaceC0072b U0 = null;

    public void L0(boolean z3) {
        int i3 = this.M0;
        if (i3 > 0 || this.N0 > 0) {
            if (z3) {
                this.O0 = this.N0;
                this.P0 = i3;
            } else {
                this.O0 = i3;
                this.P0 = this.N0;
            }
        }
    }

    public void M0() {
        for (int i3 = 0; i3 < this.H0; i3++) {
            ConstraintWidget constraintWidget = this.G0[i3];
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

    public void T0(int i3, int i4, int i5, int i6) {
    }

    public void U0(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i3, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i4) {
        while (this.U0 == null && H() != null) {
            this.U0 = ((d) H()).W0();
        }
        b.a aVar = this.T0;
        aVar.f5684a = dimensionBehaviour;
        aVar.f5685b = dimensionBehaviour2;
        aVar.f5686c = i3;
        aVar.f5687d = i4;
        this.U0.a(constraintWidget, aVar);
        constraintWidget.F0(this.T0.f5688e);
        constraintWidget.i0(this.T0.f5689f);
        constraintWidget.h0(this.T0.f5691h);
        constraintWidget.c0(this.T0.f5690g);
    }

    public boolean V0() {
        ConstraintWidget constraintWidget = this.Q;
        b.InterfaceC0072b interfaceC0072bW0 = constraintWidget != null ? ((d) constraintWidget).W0() : null;
        if (interfaceC0072bW0 == null) {
            return false;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= this.H0) {
                return true;
            }
            ConstraintWidget constraintWidget2 = this.G0[i3];
            if (constraintWidget2 != null && !(constraintWidget2 instanceof f)) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT = constraintWidget2.t(0);
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = constraintWidget2.t(1);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (!(dimensionBehaviourT == dimensionBehaviour && constraintWidget2.f857m != 1 && dimensionBehaviourT2 == dimensionBehaviour && constraintWidget2.f859n != 1)) {
                    if (dimensionBehaviourT == dimensionBehaviour) {
                        dimensionBehaviourT = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    if (dimensionBehaviourT2 == dimensionBehaviour) {
                        dimensionBehaviourT2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    b.a aVar = this.T0;
                    aVar.f5684a = dimensionBehaviourT;
                    aVar.f5685b = dimensionBehaviourT2;
                    aVar.f5686c = constraintWidget2.Q();
                    this.T0.f5687d = constraintWidget2.w();
                    interfaceC0072bW0.a(constraintWidget2, this.T0);
                    constraintWidget2.F0(this.T0.f5688e);
                    constraintWidget2.i0(this.T0.f5689f);
                    constraintWidget2.c0(this.T0.f5690g);
                }
            }
            i3++;
        }
    }

    public boolean W0() {
        return this.Q0;
    }

    public void X0(boolean z3) {
        this.Q0 = z3;
    }

    public void Y0(int i3, int i4) {
        this.R0 = i3;
        this.S0 = i4;
    }

    public void Z0(int i3) {
        this.K0 = i3;
        this.I0 = i3;
        this.L0 = i3;
        this.J0 = i3;
        this.M0 = i3;
        this.N0 = i3;
    }

    public void a1(int i3) {
        this.J0 = i3;
    }

    @Override // w.h, w.g
    public void b(d dVar) {
        M0();
    }

    public void b1(int i3) {
        this.N0 = i3;
    }

    public void c1(int i3) {
        this.K0 = i3;
        this.O0 = i3;
    }

    public void d1(int i3) {
        this.L0 = i3;
        this.P0 = i3;
    }

    public void e1(int i3) {
        this.M0 = i3;
        this.O0 = i3;
        this.P0 = i3;
    }

    public void f1(int i3) {
        this.I0 = i3;
    }
}
