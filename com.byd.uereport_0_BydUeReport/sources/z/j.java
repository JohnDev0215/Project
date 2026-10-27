package z;

import a0.b;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;

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
    public b.InterfaceC0000b U0 = null;

    public void L0(boolean z7) {
        int i8 = this.M0;
        if (i8 > 0 || this.N0 > 0) {
            if (z7) {
                this.O0 = this.N0;
                this.P0 = i8;
            } else {
                this.O0 = i8;
                this.P0 = this.N0;
            }
        }
    }

    public void M0() {
        for (int i8 = 0; i8 < this.H0; i8++) {
            ConstraintWidget constraintWidget = this.G0[i8];
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

    public void T0(int i8, int i9, int i10, int i11) {
    }

    public void U0(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i8, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i9) {
        while (this.U0 == null && H() != null) {
            this.U0 = ((d) H()).W0();
        }
        b.a aVar = this.T0;
        aVar.f5a = dimensionBehaviour;
        aVar.f6b = dimensionBehaviour2;
        aVar.f7c = i8;
        aVar.f8d = i9;
        this.U0.a(constraintWidget, aVar);
        constraintWidget.F0(this.T0.f9e);
        constraintWidget.i0(this.T0.f10f);
        constraintWidget.h0(this.T0.f12h);
        constraintWidget.c0(this.T0.f11g);
    }

    public boolean V0() {
        ConstraintWidget constraintWidget = this.Q;
        b.InterfaceC0000b interfaceC0000bW0 = constraintWidget != null ? ((d) constraintWidget).W0() : null;
        if (interfaceC0000bW0 == null) {
            return false;
        }
        int i8 = 0;
        while (true) {
            if (i8 >= this.H0) {
                return true;
            }
            ConstraintWidget constraintWidget2 = this.G0[i8];
            if (constraintWidget2 != null && !(constraintWidget2 instanceof f)) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT = constraintWidget2.t(0);
                ConstraintWidget.DimensionBehaviour dimensionBehaviourT2 = constraintWidget2.t(1);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (!(dimensionBehaviourT == dimensionBehaviour && constraintWidget2.f948m != 1 && dimensionBehaviourT2 == dimensionBehaviour && constraintWidget2.f950n != 1)) {
                    if (dimensionBehaviourT == dimensionBehaviour) {
                        dimensionBehaviourT = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    if (dimensionBehaviourT2 == dimensionBehaviour) {
                        dimensionBehaviourT2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    b.a aVar = this.T0;
                    aVar.f5a = dimensionBehaviourT;
                    aVar.f6b = dimensionBehaviourT2;
                    aVar.f7c = constraintWidget2.Q();
                    this.T0.f8d = constraintWidget2.w();
                    interfaceC0000bW0.a(constraintWidget2, this.T0);
                    constraintWidget2.F0(this.T0.f9e);
                    constraintWidget2.i0(this.T0.f10f);
                    constraintWidget2.c0(this.T0.f11g);
                }
            }
            i8++;
        }
    }

    public boolean W0() {
        return this.Q0;
    }

    public void X0(boolean z7) {
        this.Q0 = z7;
    }

    public void Y0(int i8, int i9) {
        this.R0 = i8;
        this.S0 = i9;
    }

    public void Z0(int i8) {
        this.K0 = i8;
        this.I0 = i8;
        this.L0 = i8;
        this.J0 = i8;
        this.M0 = i8;
        this.N0 = i8;
    }

    public void a1(int i8) {
        this.J0 = i8;
    }

    @Override // z.h, z.g
    public void b(d dVar) {
        M0();
    }

    public void b1(int i8) {
        this.N0 = i8;
    }

    public void c1(int i8) {
        this.K0 = i8;
        this.O0 = i8;
    }

    public void d1(int i8) {
        this.L0 = i8;
        this.P0 = i8;
    }

    public void e1(int i8) {
        this.M0 = i8;
        this.O0 = i8;
        this.P0 = i8;
    }

    public void f1(int i8) {
        this.I0 = i8;
    }
}
