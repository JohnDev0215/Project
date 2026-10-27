package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.b;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: ConstraintWidgetContainer.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends l {
    private int X0;
    public androidx.constraintlayout.core.e a1;
    int c1;
    int d1;
    int e1;
    int f1;
    androidx.constraintlayout.core.widgets.analyzer.b V0 = new androidx.constraintlayout.core.widgets.analyzer.b(this);
    public androidx.constraintlayout.core.widgets.analyzer.e W0 = new androidx.constraintlayout.core.widgets.analyzer.e(this);
    protected b.InterfaceC0016b Y0 = null;
    private boolean Z0 = false;
    protected androidx.constraintlayout.core.d b1 = new androidx.constraintlayout.core.d();
    public int g1 = 0;
    public int h1 = 0;
    c[] i1 = new c[4];
    c[] j1 = new c[4];
    public boolean k1 = false;
    public boolean l1 = false;
    public boolean m1 = false;
    public int n1 = 0;
    public int o1 = 0;
    private int p1 = 257;
    public boolean q1 = false;
    private boolean r1 = false;
    private boolean s1 = false;
    int t1 = 0;
    private WeakReference<ConstraintAnchor> u1 = null;
    private WeakReference<ConstraintAnchor> v1 = null;
    private WeakReference<ConstraintAnchor> w1 = null;
    private WeakReference<ConstraintAnchor> x1 = null;
    HashSet<ConstraintWidget> y1 = new HashSet<>();
    public b.a z1 = new b.a();

    private void A1(ConstraintWidget constraintWidget) {
        int i = this.g1 + 1;
        c[] cVarArr = this.j1;
        if (i >= cVarArr.length) {
            this.j1 = (c[]) Arrays.copyOf(cVarArr, cVarArr.length * 2);
        }
        this.j1[this.g1] = new c(constraintWidget, 0, S1());
        this.g1++;
    }

    private void D1(ConstraintAnchor constraintAnchor, SolverVariable solverVariable) {
        this.b1.h(solverVariable, this.b1.q(constraintAnchor), 0, 5);
    }

    private void E1(ConstraintAnchor constraintAnchor, SolverVariable solverVariable) {
        this.b1.h(this.b1.q(constraintAnchor), solverVariable, 0, 5);
    }

    private void F1(ConstraintWidget constraintWidget) {
        int i = this.h1 + 1;
        c[] cVarArr = this.i1;
        if (i >= cVarArr.length) {
            this.i1 = (c[]) Arrays.copyOf(cVarArr, cVarArr.length * 2);
        }
        this.i1[this.h1] = new c(constraintWidget, 1, S1());
        this.h1++;
    }

    public static boolean V1(int i, ConstraintWidget constraintWidget, b.InterfaceC0016b interfaceC0016b, b.a aVar, int i2) {
        int i3;
        int i4;
        if (interfaceC0016b == null) {
            return false;
        }
        if (constraintWidget.X() == 8 || (constraintWidget instanceof f) || (constraintWidget instanceof a)) {
            aVar.h = 0;
            aVar.i = 0;
            return false;
        }
        aVar.f849d = constraintWidget.C();
        aVar.f850e = constraintWidget.V();
        aVar.f = constraintWidget.Y();
        aVar.g = constraintWidget.z();
        aVar.l = false;
        aVar.m = i2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = aVar.f849d;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z = dimensionBehaviour == dimensionBehaviour2;
        boolean z2 = aVar.f850e == dimensionBehaviour2;
        boolean z3 = z && constraintWidget.g0 > 0.0f;
        boolean z4 = z2 && constraintWidget.g0 > 0.0f;
        if (z && constraintWidget.c0(0) && constraintWidget.x == 0 && !z3) {
            aVar.f849d = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (z2 && constraintWidget.y == 0) {
                aVar.f849d = ConstraintWidget.DimensionBehaviour.FIXED;
            }
            z = false;
        }
        if (z2 && constraintWidget.c0(1) && constraintWidget.y == 0 && !z4) {
            aVar.f850e = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (z && constraintWidget.x == 0) {
                aVar.f850e = ConstraintWidget.DimensionBehaviour.FIXED;
            }
            z2 = false;
        }
        if (constraintWidget.p0()) {
            aVar.f849d = ConstraintWidget.DimensionBehaviour.FIXED;
            z = false;
        }
        if (constraintWidget.q0()) {
            aVar.f850e = ConstraintWidget.DimensionBehaviour.FIXED;
            z2 = false;
        }
        if (z3) {
            if (constraintWidget.z[0] == 4) {
                aVar.f849d = ConstraintWidget.DimensionBehaviour.FIXED;
            } else if (!z2) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = aVar.f850e;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                if (dimensionBehaviour3 == dimensionBehaviour4) {
                    i4 = aVar.g;
                } else {
                    aVar.f849d = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    interfaceC0016b.b(constraintWidget, aVar);
                    i4 = aVar.i;
                }
                aVar.f849d = dimensionBehaviour4;
                aVar.f = (int) (constraintWidget.x() * i4);
            }
        }
        if (z4) {
            if (constraintWidget.z[1] == 4) {
                aVar.f850e = ConstraintWidget.DimensionBehaviour.FIXED;
            } else if (!z) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = aVar.f849d;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.FIXED;
                if (dimensionBehaviour5 == dimensionBehaviour6) {
                    i3 = aVar.f;
                } else {
                    aVar.f850e = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    interfaceC0016b.b(constraintWidget, aVar);
                    i3 = aVar.h;
                }
                aVar.f850e = dimensionBehaviour6;
                if (constraintWidget.y() == -1) {
                    aVar.g = (int) (i3 / constraintWidget.x());
                } else {
                    aVar.g = (int) (constraintWidget.x() * i3);
                }
            }
        }
        interfaceC0016b.b(constraintWidget, aVar);
        constraintWidget.n1(aVar.h);
        constraintWidget.O0(aVar.i);
        constraintWidget.N0(aVar.k);
        constraintWidget.D0(aVar.j);
        aVar.m = b.a.f846a;
        return aVar.l;
    }

    private void X1() {
        this.g1 = 0;
        this.h1 = 0;
    }

    public void B1(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.x1;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.e() > this.x1.get().e()) {
            this.x1 = new WeakReference<>(constraintAnchor);
        }
    }

    public void C1(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.v1;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.e() > this.v1.get().e()) {
            this.v1 = new WeakReference<>(constraintAnchor);
        }
    }

    void G1(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.w1;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.e() > this.w1.get().e()) {
            this.w1 = new WeakReference<>(constraintAnchor);
        }
    }

    void H1(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.u1;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.e() > this.u1.get().e()) {
            this.u1 = new WeakReference<>(constraintAnchor);
        }
    }

    public boolean I1(boolean z) {
        return this.W0.f(z);
    }

    public boolean J1(boolean z) {
        return this.W0.g(z);
    }

    public boolean K1(boolean z, int i) {
        return this.W0.h(z, i);
    }

    public b.InterfaceC0016b L1() {
        return this.Y0;
    }

    public int M1() {
        return this.p1;
    }

    public androidx.constraintlayout.core.d N1() {
        return this.b1;
    }

    public boolean O1() {
        return false;
    }

    public void P1() {
        this.W0.j();
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void Q(StringBuilder sb) {
        sb.append(this.p + ":{\n");
        sb.append("  actualWidth:" + this.e0);
        sb.append(BydAdbHelper.COMMAND_LINE_END);
        sb.append("  actualHeight:" + this.f0);
        sb.append(BydAdbHelper.COMMAND_LINE_END);
        Iterator<ConstraintWidget> it = u1().iterator();
        while (it.hasNext()) {
            it.next().Q(sb);
            sb.append(",\n");
        }
        sb.append("}");
    }

    public void Q1() {
        this.W0.k();
    }

    public boolean R1() {
        return this.s1;
    }

    public boolean S1() {
        return this.Z0;
    }

    public boolean T1() {
        return this.r1;
    }

    public long U1(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.c1 = i8;
        this.d1 = i9;
        return this.V0.d(this, i, i8, i9, i2, i3, i4, i5, i6, i7);
    }

    public boolean W1(int i) {
        return (this.p1 & i) == i;
    }

    public void Y1(b.InterfaceC0016b interfaceC0016b) {
        this.Y0 = interfaceC0016b;
        this.W0.n(interfaceC0016b);
    }

    public void Z1(int i) {
        this.p1 = i;
        androidx.constraintlayout.core.d.f719a = W1(512);
    }

    public void a2(int i) {
        this.X0 = i;
    }

    public void b2(boolean z) {
        this.Z0 = z;
    }

    public boolean c2(androidx.constraintlayout.core.d dVar, boolean[] zArr) {
        zArr[2] = false;
        boolean zW1 = W1(64);
        t1(dVar, zW1);
        int size = this.U0.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = this.U0.get(i);
            constraintWidget.t1(dVar, zW1);
            if (constraintWidget.e0()) {
                z = true;
            }
        }
        return z;
    }

    public void d2() {
        this.V0.e(this);
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void s1(boolean z, boolean z2) {
        super.s1(z, z2);
        int size = this.U0.size();
        for (int i = 0; i < size; i++) {
            this.U0.get(i).s1(z, z2);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.l, androidx.constraintlayout.core.widgets.ConstraintWidget
    public void v0() {
        this.b1.D();
        this.c1 = 0;
        this.e1 = 0;
        this.d1 = 0;
        this.f1 = 0;
        this.q1 = false;
        super.v0();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:154:0x030e A[PHI: r6 r16
  0x030e: PHI (r6v12 ??) = (r6v11 ??), (r6v16 ??), (r6v16 ??), (r6v16 ??) binds: [B:141:0x02cf, B:149:0x02f4, B:150:0x02f6, B:152:0x02fc] A[DONT_GENERATE, DONT_INLINE]
  0x030e: PHI (r16v4 boolean) = (r16v3 boolean), (r16v5 boolean), (r16v5 boolean), (r16v5 boolean) binds: [B:141:0x02cf, B:149:0x02f4, B:150:0x02f6, B:152:0x02fc] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v30 */
    /* JADX WARN: Type inference failed for: r11v31 */
    /* JADX WARN: Type inference failed for: r11v32 */
    /* JADX WARN: Type inference failed for: r11v33 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v22 */
    /* JADX WARN: Type inference failed for: r6v45 */
    /* JADX WARN: Type inference failed for: r6v46 */
    /* JADX WARN: Type inference failed for: r6v47 */
    /* JADX WARN: Type inference failed for: r6v48 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v8 */
    @Override // androidx.constraintlayout.core.widgets.l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void v1() {
        /*
            Method dump skipped, instruction units count: 822
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.d.v1():void");
    }

    void y1(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            A1(constraintWidget);
        } else if (i == 1) {
            F1(constraintWidget);
        }
    }

    public boolean z1(androidx.constraintlayout.core.d dVar) {
        boolean zW1 = W1(64);
        g(dVar, zW1);
        int size = this.U0.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = this.U0.get(i);
            constraintWidget.V0(0, false);
            constraintWidget.V0(1, false);
            if (constraintWidget instanceof a) {
                z = true;
            }
        }
        if (z) {
            for (int i2 = 0; i2 < size; i2++) {
                ConstraintWidget constraintWidget2 = this.U0.get(i2);
                if (constraintWidget2 instanceof a) {
                    ((a) constraintWidget2).B1();
                }
            }
        }
        this.y1.clear();
        for (int i3 = 0; i3 < size; i3++) {
            ConstraintWidget constraintWidget3 = this.U0.get(i3);
            if (constraintWidget3.f()) {
                if (constraintWidget3 instanceof k) {
                    this.y1.add(constraintWidget3);
                } else {
                    constraintWidget3.g(dVar, zW1);
                }
            }
        }
        while (this.y1.size() > 0) {
            int size2 = this.y1.size();
            Iterator<ConstraintWidget> it = this.y1.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                k kVar = (k) it.next();
                if (kVar.y1(this.y1)) {
                    kVar.g(dVar, zW1);
                    this.y1.remove(kVar);
                    break;
                }
            }
            if (size2 == this.y1.size()) {
                Iterator<ConstraintWidget> it2 = this.y1.iterator();
                while (it2.hasNext()) {
                    it2.next().g(dVar, zW1);
                }
                this.y1.clear();
            }
        }
        if (androidx.constraintlayout.core.d.f719a) {
            HashSet<ConstraintWidget> hashSet = new HashSet<>();
            for (int i4 = 0; i4 < size; i4++) {
                ConstraintWidget constraintWidget4 = this.U0.get(i4);
                if (!constraintWidget4.f()) {
                    hashSet.add(constraintWidget4);
                }
            }
            e(this, dVar, hashSet, C() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT ? 0 : 1, false);
            for (ConstraintWidget constraintWidget5 : hashSet) {
                i.a(this, dVar, constraintWidget5);
                constraintWidget5.g(dVar, zW1);
            }
        } else {
            for (int i5 = 0; i5 < size; i5++) {
                ConstraintWidget constraintWidget6 = this.U0.get(i5);
                if (constraintWidget6 instanceof d) {
                    ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget6.c0;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    if (dimensionBehaviour == dimensionBehaviour3) {
                        constraintWidget6.S0(ConstraintWidget.DimensionBehaviour.FIXED);
                    }
                    if (dimensionBehaviour2 == dimensionBehaviour3) {
                        constraintWidget6.j1(ConstraintWidget.DimensionBehaviour.FIXED);
                    }
                    constraintWidget6.g(dVar, zW1);
                    if (dimensionBehaviour == dimensionBehaviour3) {
                        constraintWidget6.S0(dimensionBehaviour);
                    }
                    if (dimensionBehaviour2 == dimensionBehaviour3) {
                        constraintWidget6.j1(dimensionBehaviour2);
                    }
                } else {
                    i.a(this, dVar, constraintWidget6);
                    if (!constraintWidget6.f()) {
                        constraintWidget6.g(dVar, zW1);
                    }
                }
            }
        }
        if (this.g1 > 0) {
            b.b(this, dVar, null, 0);
        }
        if (this.h1 > 0) {
            b.b(this, dVar, null, 1);
        }
        return true;
    }
}
