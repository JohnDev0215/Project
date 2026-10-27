package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.b;
import java.util.HashSet;

/* JADX INFO: compiled from: VirtualLayout.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends h {
    private int W0 = 0;
    private int X0 = 0;
    private int Y0 = 0;
    private int Z0 = 0;
    private int a1 = 0;
    private int b1 = 0;
    private int c1 = 0;
    private int d1 = 0;
    private boolean e1 = false;
    private int f1 = 0;
    private int g1 = 0;
    protected b.a h1 = new b.a();
    b.InterfaceC0016b i1 = null;

    public int A1() {
        return this.f1;
    }

    public int B1() {
        return this.X0;
    }

    public int C1() {
        return this.c1;
    }

    public int D1() {
        return this.d1;
    }

    public int E1() {
        return this.W0;
    }

    public void F1(int i, int i2, int i3, int i4) {
    }

    protected void G1(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i2) {
        while (this.i1 == null && M() != null) {
            this.i1 = ((d) M()).L1();
        }
        b.a aVar = this.h1;
        aVar.f849d = dimensionBehaviour;
        aVar.f850e = dimensionBehaviour2;
        aVar.f = i;
        aVar.g = i2;
        this.i1.b(constraintWidget, aVar);
        constraintWidget.n1(this.h1.h);
        constraintWidget.O0(this.h1.i);
        constraintWidget.N0(this.h1.k);
        constraintWidget.D0(this.h1.j);
    }

    protected boolean H1() {
        ConstraintWidget constraintWidget = this.d0;
        b.InterfaceC0016b interfaceC0016bL1 = constraintWidget != null ? ((d) constraintWidget).L1() : null;
        if (interfaceC0016bL1 == null) {
            return false;
        }
        int i = 0;
        while (true) {
            if (i >= this.V0) {
                return true;
            }
            ConstraintWidget constraintWidget2 = this.U0[i];
            if (constraintWidget2 != null && !(constraintWidget2 instanceof f)) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviourW = constraintWidget2.w(0);
                ConstraintWidget.DimensionBehaviour dimensionBehaviourW2 = constraintWidget2.w(1);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (!(dimensionBehaviourW == dimensionBehaviour && constraintWidget2.x != 1 && dimensionBehaviourW2 == dimensionBehaviour && constraintWidget2.y != 1)) {
                    if (dimensionBehaviourW == dimensionBehaviour) {
                        dimensionBehaviourW = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    if (dimensionBehaviourW2 == dimensionBehaviour) {
                        dimensionBehaviourW2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    b.a aVar = this.h1;
                    aVar.f849d = dimensionBehaviourW;
                    aVar.f850e = dimensionBehaviourW2;
                    aVar.f = constraintWidget2.Y();
                    this.h1.g = constraintWidget2.z();
                    interfaceC0016bL1.b(constraintWidget2, this.h1);
                    constraintWidget2.n1(this.h1.h);
                    constraintWidget2.O0(this.h1.i);
                    constraintWidget2.D0(this.h1.j);
                }
            }
            i++;
        }
    }

    public boolean I1() {
        return this.e1;
    }

    protected void J1(boolean z) {
        this.e1 = z;
    }

    public void K1(int i, int i2) {
        this.f1 = i;
        this.g1 = i2;
    }

    public void L1(int i) {
        this.Y0 = i;
        this.W0 = i;
        this.Z0 = i;
        this.X0 = i;
        this.a1 = i;
        this.b1 = i;
    }

    public void M1(int i) {
        this.X0 = i;
    }

    public void N1(int i) {
        this.b1 = i;
    }

    public void O1(int i) {
        this.Y0 = i;
        this.c1 = i;
    }

    public void P1(int i) {
        this.Z0 = i;
        this.d1 = i;
    }

    public void Q1(int i) {
        this.a1 = i;
        this.c1 = i;
        this.d1 = i;
    }

    public void R1(int i) {
        this.W0 = i;
    }

    @Override // androidx.constraintlayout.core.widgets.h, androidx.constraintlayout.core.widgets.g
    public void b(d dVar) {
        x1();
    }

    public void w1(boolean z) {
        int i = this.a1;
        if (i > 0 || this.b1 > 0) {
            if (z) {
                this.c1 = this.b1;
                this.d1 = i;
            } else {
                this.c1 = i;
                this.d1 = this.b1;
            }
        }
    }

    public void x1() {
        for (int i = 0; i < this.V0; i++) {
            ConstraintWidget constraintWidget = this.U0[i];
            if (constraintWidget != null) {
                constraintWidget.X0(true);
            }
        }
    }

    public boolean y1(HashSet<ConstraintWidget> hashSet) {
        for (int i = 0; i < this.V0; i++) {
            if (hashSet.contains(this.U0[i])) {
                return true;
            }
        }
        return false;
    }

    public int z1() {
        return this.g1;
    }
}
