package w;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.Arrays;
import x.b;

/* JADX INFO: compiled from: ConstraintWidgetContainer.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends k {
    public v.e L0;
    public int N0;
    public int O0;
    public int P0;
    public int Q0;
    public x.b H0 = new x.b(this);
    public x.e I0 = new x.e(this);
    public b.InterfaceC0072b J0 = null;
    public boolean K0 = false;
    public v.d M0 = new v.d();
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
    public int f5472a1 = 263;

    /* JADX INFO: renamed from: b1, reason: collision with root package name */
    public boolean f5473b1 = false;

    /* JADX INFO: renamed from: c1, reason: collision with root package name */
    public boolean f5474c1 = false;

    /* JADX INFO: renamed from: d1, reason: collision with root package name */
    public boolean f5475d1 = false;

    /* JADX INFO: renamed from: e1, reason: collision with root package name */
    public int f5476e1 = 0;

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void J0(boolean z3, boolean z4) {
        super.J0(z3, z4);
        int size = this.G0.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.G0.get(i3).J0(z3, z4);
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
    @Override // w.k
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void M0() {
        /*
            Method dump skipped, instruction units count: 482
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: w.d.M0():void");
    }

    public void P0(ConstraintWidget constraintWidget, int i3) {
        if (i3 == 0) {
            R0(constraintWidget);
        } else if (i3 == 1) {
            S0(constraintWidget);
        }
    }

    public boolean Q0(v.d dVar) {
        f(dVar);
        int size = this.G0.size();
        boolean z3 = false;
        for (int i3 = 0; i3 < size; i3++) {
            ConstraintWidget constraintWidget = this.G0.get(i3);
            constraintWidget.p0(0, false);
            constraintWidget.p0(1, false);
            if (constraintWidget instanceof a) {
                z3 = true;
            }
        }
        if (z3) {
            for (int i4 = 0; i4 < size; i4++) {
                ConstraintWidget constraintWidget2 = this.G0.get(i4);
                if (constraintWidget2 instanceof a) {
                    ((a) constraintWidget2).O0();
                }
            }
        }
        for (int i5 = 0; i5 < size; i5++) {
            ConstraintWidget constraintWidget3 = this.G0.get(i5);
            if (constraintWidget3.e()) {
                constraintWidget3.f(dVar);
            }
        }
        for (int i6 = 0; i6 < size; i6++) {
            ConstraintWidget constraintWidget4 = this.G0.get(i6);
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
        int i3 = this.R0 + 1;
        c[] cVarArr = this.U0;
        if (i3 >= cVarArr.length) {
            this.U0 = (c[]) Arrays.copyOf(cVarArr, cVarArr.length * 2);
        }
        this.U0[this.R0] = new c(constraintWidget, 0, c1());
        this.R0++;
    }

    public final void S0(ConstraintWidget constraintWidget) {
        int i3 = this.S0 + 1;
        c[] cVarArr = this.T0;
        if (i3 >= cVarArr.length) {
            this.T0 = (c[]) Arrays.copyOf(cVarArr, cVarArr.length * 2);
        }
        this.T0[this.S0] = new c(constraintWidget, 1, c1());
        this.S0++;
    }

    public boolean T0(boolean z3) {
        return this.I0.f(z3);
    }

    public boolean U0(boolean z3) {
        return this.I0.g(z3);
    }

    public boolean V0(boolean z3, int i3) {
        return this.I0.h(z3, i3);
    }

    public b.InterfaceC0072b W0() {
        return this.J0;
    }

    public int X0() {
        return this.f5472a1;
    }

    public boolean Y0() {
        return false;
    }

    @Override // w.k, androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void Z() {
        this.M0.D();
        this.N0 = 0;
        this.P0 = 0;
        this.O0 = 0;
        this.Q0 = 0;
        this.f5473b1 = false;
        super.Z();
    }

    public void Z0() {
        this.I0.j();
    }

    public void a1() {
        this.I0.k();
    }

    public boolean b1() {
        return this.f5475d1;
    }

    public boolean c1() {
        return this.K0;
    }

    public boolean d1() {
        return this.f5474c1;
    }

    public long e1(int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        this.N0 = i10;
        this.O0 = i11;
        return this.H0.d(this, i3, i10, i11, i4, i5, i6, i7, i8, i9);
    }

    public boolean f1(int i3) {
        return (this.f5472a1 & i3) == i3;
    }

    public final void g1() {
        this.R0 = 0;
        this.S0 = 0;
    }

    public void h1(b.InterfaceC0072b interfaceC0072b) {
        this.J0 = interfaceC0072b;
        this.I0.n(interfaceC0072b);
    }

    public void i1(int i3) {
        this.f5472a1 = i3;
        v.d.f5373c = i.b(i3, 256);
    }

    public void j1(boolean z3) {
        this.K0 = z3;
    }

    public void k1(v.d dVar, boolean[] zArr) {
        zArr[2] = false;
        K0(dVar);
        int size = this.G0.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.G0.get(i3).K0(dVar);
        }
    }

    public void l1() {
        this.H0.e(this);
    }
}
