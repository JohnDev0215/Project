package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import defpackage.f6;
import java.util.Arrays;

/* JADX INFO: compiled from: ConstraintWidgetContainer.java */
/* JADX INFO: loaded from: classes.dex */
public class w5 extends d6 {
    public o5 L0;
    public int N0;
    public int O0;
    public int P0;
    public int Q0;
    public f6 H0 = new f6(this);
    public i6 I0 = new i6(this);
    public f6.b J0 = null;
    public boolean K0 = false;
    public n5 M0 = new n5();
    public int R0 = 0;
    public int S0 = 0;
    public v5[] T0 = new v5[4];
    public v5[] U0 = new v5[4];
    public boolean V0 = false;
    public boolean W0 = false;
    public boolean X0 = false;
    public int Y0 = 0;
    public int Z0 = 0;
    public int a1 = 263;
    public boolean b1 = false;
    public boolean c1 = false;
    public boolean d1 = false;
    public int e1 = 0;

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
    @Override // defpackage.d6
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void M0() {
        /*
            Method dump skipped, instruction units count: 482
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.w5.M0():void");
    }

    public void P0(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            R0(constraintWidget);
        } else if (i == 1) {
            S0(constraintWidget);
        }
    }

    public boolean Q0(n5 n5Var) {
        f(n5Var);
        int size = this.G0.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = this.G0.get(i);
            constraintWidget.p0(0, false);
            constraintWidget.p0(1, false);
            if (constraintWidget instanceof t5) {
                z = true;
            }
        }
        if (z) {
            for (int i2 = 0; i2 < size; i2++) {
                ConstraintWidget constraintWidget2 = this.G0.get(i2);
                if (constraintWidget2 instanceof t5) {
                    ((t5) constraintWidget2).O0();
                }
            }
        }
        for (int i3 = 0; i3 < size; i3++) {
            ConstraintWidget constraintWidget3 = this.G0.get(i3);
            if (constraintWidget3.e()) {
                constraintWidget3.f(n5Var);
            }
        }
        for (int i4 = 0; i4 < size; i4++) {
            ConstraintWidget constraintWidget4 = this.G0.get(i4);
            if (constraintWidget4 instanceof w5) {
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
                constraintWidget4.f(n5Var);
                if (dimensionBehaviour == dimensionBehaviour3) {
                    constraintWidget4.m0(dimensionBehaviour);
                }
                if (dimensionBehaviour2 == dimensionBehaviour3) {
                    constraintWidget4.B0(dimensionBehaviour2);
                }
            } else {
                b6.a(this, n5Var, constraintWidget4);
                if (!constraintWidget4.e()) {
                    constraintWidget4.f(n5Var);
                }
            }
        }
        if (this.R0 > 0) {
            u5.a(this, n5Var, 0);
        }
        if (this.S0 > 0) {
            u5.a(this, n5Var, 1);
        }
        return true;
    }

    public final void R0(ConstraintWidget constraintWidget) {
        int i = this.R0 + 1;
        v5[] v5VarArr = this.U0;
        if (i >= v5VarArr.length) {
            this.U0 = (v5[]) Arrays.copyOf(v5VarArr, v5VarArr.length * 2);
        }
        this.U0[this.R0] = new v5(constraintWidget, 0, c1());
        this.R0++;
    }

    public final void S0(ConstraintWidget constraintWidget) {
        int i = this.S0 + 1;
        v5[] v5VarArr = this.T0;
        if (i >= v5VarArr.length) {
            this.T0 = (v5[]) Arrays.copyOf(v5VarArr, v5VarArr.length * 2);
        }
        this.T0[this.S0] = new v5(constraintWidget, 1, c1());
        this.S0++;
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

    public f6.b W0() {
        return this.J0;
    }

    public int X0() {
        return this.a1;
    }

    public boolean Y0() {
        return false;
    }

    @Override // defpackage.d6, androidx.constraintlayout.solver.widgets.ConstraintWidget
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

    public final void g1() {
        this.R0 = 0;
        this.S0 = 0;
    }

    public void h1(f6.b bVar) {
        this.J0 = bVar;
        this.I0.n(bVar);
    }

    public void i1(int i) {
        this.a1 = i;
        n5.c = b6.b(i, 256);
    }

    public void j1(boolean z) {
        this.K0 = z;
    }

    public void k1(n5 n5Var, boolean[] zArr) {
        zArr[2] = false;
        K0(n5Var);
        int size = this.G0.size();
        for (int i = 0; i < size; i++) {
            this.G0.get(i).K0(n5Var);
        }
    }

    public void l1() {
        this.H0.e(this);
    }
}
