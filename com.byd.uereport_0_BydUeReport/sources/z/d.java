package z;

import a0.b;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.Arrays;

/* JADX INFO: compiled from: ConstraintWidgetContainer.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends k {
    public y.e L0;
    public int N0;
    public int O0;
    public int P0;
    public int Q0;
    public a0.b H0 = new a0.b(this);
    public a0.e I0 = new a0.e(this);
    public b.InterfaceC0000b J0 = null;
    public boolean K0 = false;
    public y.d M0 = new y.d();
    public int R0 = 0;
    public int S0 = 0;
    public c[] T0 = new c[4];
    public c[] U0 = new c[4];
    public boolean V0 = false;
    public boolean W0 = false;
    public boolean X0 = false;
    public int Y0 = 0;
    public int Z0 = 0;

    /* JADX INFO: renamed from: a1, reason: collision with root package name */
    public int f9439a1 = 263;

    /* JADX INFO: renamed from: b1, reason: collision with root package name */
    public boolean f9440b1 = false;

    /* JADX INFO: renamed from: c1, reason: collision with root package name */
    public boolean f9441c1 = false;

    /* JADX INFO: renamed from: d1, reason: collision with root package name */
    public boolean f9442d1 = false;

    /* JADX INFO: renamed from: e1, reason: collision with root package name */
    public int f9443e1 = 0;

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void J0(boolean z7, boolean z8) {
        super.J0(z7, z8);
        int size = this.G0.size();
        for (int i8 = 0; i8 < size; i8++) {
            this.G0.get(i8).J0(z7, z8);
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
    @Override // z.k
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void M0() {
        /*
            Method dump skipped, instruction units count: 482
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: z.d.M0():void");
    }

    public void P0(ConstraintWidget constraintWidget, int i8) {
        if (i8 == 0) {
            R0(constraintWidget);
        } else if (i8 == 1) {
            S0(constraintWidget);
        }
    }

    public boolean Q0(y.d dVar) {
        f(dVar);
        int size = this.G0.size();
        boolean z7 = false;
        for (int i8 = 0; i8 < size; i8++) {
            ConstraintWidget constraintWidget = this.G0.get(i8);
            constraintWidget.p0(0, false);
            constraintWidget.p0(1, false);
            if (constraintWidget instanceof a) {
                z7 = true;
            }
        }
        if (z7) {
            for (int i9 = 0; i9 < size; i9++) {
                ConstraintWidget constraintWidget2 = this.G0.get(i9);
                if (constraintWidget2 instanceof a) {
                    ((a) constraintWidget2).O0();
                }
            }
        }
        for (int i10 = 0; i10 < size; i10++) {
            ConstraintWidget constraintWidget3 = this.G0.get(i10);
            if (constraintWidget3.e()) {
                constraintWidget3.f(dVar);
            }
        }
        for (int i11 = 0; i11 < size; i11++) {
            ConstraintWidget constraintWidget4 = this.G0.get(i11);
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

    public final void R0(ConstraintWidget constraintWidget) {
        int i8 = this.R0 + 1;
        c[] cVarArr = this.U0;
        if (i8 >= cVarArr.length) {
            this.U0 = (c[]) Arrays.copyOf(cVarArr, cVarArr.length * 2);
        }
        this.U0[this.R0] = new c(constraintWidget, 0, c1());
        this.R0++;
    }

    public final void S0(ConstraintWidget constraintWidget) {
        int i8 = this.S0 + 1;
        c[] cVarArr = this.T0;
        if (i8 >= cVarArr.length) {
            this.T0 = (c[]) Arrays.copyOf(cVarArr, cVarArr.length * 2);
        }
        this.T0[this.S0] = new c(constraintWidget, 1, c1());
        this.S0++;
    }

    public boolean T0(boolean z7) {
        return this.I0.f(z7);
    }

    public boolean U0(boolean z7) {
        return this.I0.g(z7);
    }

    public boolean V0(boolean z7, int i8) {
        return this.I0.h(z7, i8);
    }

    public b.InterfaceC0000b W0() {
        return this.J0;
    }

    public int X0() {
        return this.f9439a1;
    }

    public boolean Y0() {
        return false;
    }

    @Override // z.k, androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void Z() {
        this.M0.D();
        this.N0 = 0;
        this.P0 = 0;
        this.O0 = 0;
        this.Q0 = 0;
        this.f9440b1 = false;
        super.Z();
    }

    public void Z0() {
        this.I0.j();
    }

    public void a1() {
        this.I0.k();
    }

    public boolean b1() {
        return this.f9442d1;
    }

    public boolean c1() {
        return this.K0;
    }

    public boolean d1() {
        return this.f9441c1;
    }

    public long e1(int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
        this.N0 = i15;
        this.O0 = i16;
        return this.H0.d(this, i8, i15, i16, i9, i10, i11, i12, i13, i14);
    }

    public boolean f1(int i8) {
        return (this.f9439a1 & i8) == i8;
    }

    public final void g1() {
        this.R0 = 0;
        this.S0 = 0;
    }

    public void h1(b.InterfaceC0000b interfaceC0000b) {
        this.J0 = interfaceC0000b;
        this.I0.n(interfaceC0000b);
    }

    public void i1(int i8) {
        this.f9439a1 = i8;
        y.d.f9118c = i.b(i8, 256);
    }

    public void j1(boolean z7) {
        this.K0 = z7;
    }

    public void k1(y.d dVar, boolean[] zArr) {
        zArr[2] = false;
        K0(dVar);
        int size = this.G0.size();
        for (int i8 = 0; i8 < size; i8++) {
            this.G0.get(i8).K0(dVar);
        }
    }

    public void l1() {
        this.H0.e(this);
    }
}
