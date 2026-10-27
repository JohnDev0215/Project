package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.b;
import java.util.Arrays;

/* JADX INFO: compiled from: ConstraintWidgetContainer.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends k {
    public androidx.constraintlayout.solver.e L0;
    int N0;
    int O0;
    int P0;
    int Q0;
    androidx.constraintlayout.solver.widgets.analyzer.b H0 = new androidx.constraintlayout.solver.widgets.analyzer.b(this);
    public androidx.constraintlayout.solver.widgets.analyzer.e I0 = new androidx.constraintlayout.solver.widgets.analyzer.e(this);
    protected b.InterfaceC0009b J0 = null;
    private boolean K0 = false;
    protected androidx.constraintlayout.solver.d M0 = new androidx.constraintlayout.solver.d();
    int R0 = 0;
    int S0 = 0;
    c[] T0 = new c[4];
    c[] U0 = new c[4];
    public boolean V0 = false;
    public boolean W0 = false;
    public boolean X0 = false;
    public int Y0 = 0;
    public int Z0 = 0;
    private int a1 = 263;
    public boolean b1 = false;
    private boolean c1 = false;
    private boolean d1 = false;
    int e1 = 0;

    private void R0(ConstraintWidget constraintWidget) {
        int i = this.R0 + 1;
        c[] cVarArr = this.U0;
        if (i >= cVarArr.length) {
            this.U0 = (c[]) Arrays.copyOf(cVarArr, cVarArr.length * 2);
        }
        this.U0[this.R0] = new c(constraintWidget, 0, c1());
        this.R0++;
    }

    private void S0(ConstraintWidget constraintWidget) {
        int i = this.S0 + 1;
        c[] cVarArr = this.T0;
        if (i >= cVarArr.length) {
            this.T0 = (c[]) Arrays.copyOf(cVarArr, cVarArr.length * 2);
        }
        this.T0[this.S0] = new c(constraintWidget, 1, c1());
        this.S0++;
    }

    private void g1() {
        this.R0 = 0;
        this.S0 = 0;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void J0(boolean z, boolean z2) {
        super.J0(z, z2);
        int size = this.G0.size();
        for (int i = 0; i < size; i++) {
            this.G0.get(i).J0(z, z2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01c7 A[PHI: r0 r5
  0x01c7: PHI (r0v26 ??) = (r0v25 ??), (r0v28 ??), (r0v28 ??), (r0v28 ??) binds: [B:69:0x018c, B:77:0x01af, B:78:0x01b1, B:80:0x01b7] A[DONT_GENERATE, DONT_INLINE]
  0x01c7: PHI (r5v11 boolean) = (r5v10 boolean), (r5v13 boolean), (r5v13 boolean), (r5v13 boolean) binds: [B:69:0x018c, B:77:0x01af, B:78:0x01b1, B:80:0x01b7] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v26 */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v31 */
    /* JADX WARN: Type inference failed for: r0v33 */
    /* JADX WARN: Type inference failed for: r0v65 */
    /* JADX WARN: Type inference failed for: r0v66 */
    /* JADX WARN: Type inference failed for: r0v67 */
    /* JADX WARN: Type inference failed for: r0v68 */
    /* JADX WARN: Type inference failed for: r0v69 */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v13 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v23 */
    /* JADX WARN: Type inference failed for: r11v24 */
    /* JADX WARN: Type inference failed for: r11v25 */
    /* JADX WARN: Type inference failed for: r11v26 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9, types: [boolean] */
    @Override // androidx.constraintlayout.solver.widgets.k
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void M0() {
        /*
            Method dump skipped, instruction units count: 482
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.d.M0():void");
    }

    void P0(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            R0(constraintWidget);
        } else if (i == 1) {
            S0(constraintWidget);
        }
    }

    public boolean Q0(androidx.constraintlayout.solver.d dVar) {
        f(dVar);
        int size = this.G0.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = this.G0.get(i);
            constraintWidget.p0(0, false);
            constraintWidget.p0(1, false);
            if (constraintWidget instanceof a) {
                z = true;
            }
        }
        if (z) {
            for (int i2 = 0; i2 < size; i2++) {
                ConstraintWidget constraintWidget2 = this.G0.get(i2);
                if (constraintWidget2 instanceof a) {
                    ((a) constraintWidget2).O0();
                }
            }
        }
        for (int i3 = 0; i3 < size; i3++) {
            ConstraintWidget constraintWidget3 = this.G0.get(i3);
            if (constraintWidget3.e()) {
                constraintWidget3.f(dVar);
            }
        }
        for (int i4 = 0; i4 < size; i4++) {
            ConstraintWidget constraintWidget4 = this.G0.get(i4);
            if (constraintWidget4 instanceof d) {
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget4.P;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                if (dimensionBehaviour == dimensionBehaviour3) {
                    constraintWidget4.m0(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                if (dimensionBehaviour2 == dimensionBehaviour3) {
                    constraintWidget4.B0(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                constraintWidget4.f(dVar);
                if (dimensionBehaviour == dimensionBehaviour3) {
                    constraintWidget4.m0(dimensionBehaviour);
                }
                if (dimensionBehaviour2 == dimensionBehaviour3) {
                    constraintWidget4.B0(dimensionBehaviour2);
                }
            } else {
                i.a(this, dVar, constraintWidget4);
                if (!constraintWidget4.e()) {
                    constraintWidget4.f(dVar);
                }
            }
        }
        if (this.R0 > 0) {
            b.a(this, dVar, 0);
        }
        if (this.S0 > 0) {
            b.a(this, dVar, 1);
        }
        return true;
    }

    public boolean T0(boolean z) {
        return this.I0.f(z);
    }

    public boolean U0(boolean z) {
        return this.I0.g(z);
    }

    public boolean V0(boolean z, int i) {
        return this.I0.h(z, i);
    }

    public b.InterfaceC0009b W0() {
        return this.J0;
    }

    public int X0() {
        return this.a1;
    }

    public boolean Y0() {
        return false;
    }

    @Override // androidx.constraintlayout.solver.widgets.k, androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void Z() {
        this.M0.D();
        this.N0 = 0;
        this.P0 = 0;
        this.O0 = 0;
        this.Q0 = 0;
        this.b1 = false;
        super.Z();
    }

    public void Z0() {
        this.I0.j();
    }

    public void a1() {
        this.I0.k();
    }

    public boolean b1() {
        return this.d1;
    }

    public boolean c1() {
        return this.K0;
    }

    public boolean d1() {
        return this.c1;
    }

    public long e1(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.N0 = i8;
        this.O0 = i9;
        return this.H0.d(this, i, i8, i9, i2, i3, i4, i5, i6, i7);
    }

    public boolean f1(int i) {
        return (this.a1 & i) == i;
    }

    public void h1(b.InterfaceC0009b interfaceC0009b) {
        this.J0 = interfaceC0009b;
        this.I0.n(interfaceC0009b);
    }

    public void i1(int i) {
        this.a1 = i;
        androidx.constraintlayout.solver.d.f638c = i.b(i, 256);
    }

    public void j1(boolean z) {
        this.K0 = z;
    }

    public void k1(androidx.constraintlayout.solver.d dVar, boolean[] zArr) {
        zArr[2] = false;
        K0(dVar);
        int size = this.G0.size();
        for (int i = 0; i < size; i++) {
            this.G0.get(i).K0(dVar);
        }
    }

    public void l1() {
        this.H0.e(this);
    }
}
