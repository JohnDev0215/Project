package b.e.b.k;

import b.e.b.k.e;
import b.e.b.k.n.b;
import java.util.Arrays;

/* JADX INFO: compiled from: ConstraintWidgetContainer.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends m {
    public b.e.b.e L0;
    int N0;
    int O0;
    int P0;
    int Q0;
    b.e.b.k.n.b H0 = new b.e.b.k.n.b(this);
    public b.e.b.k.n.e I0 = new b.e.b.k.n.e(this);
    protected b.InterfaceC0038b J0 = null;
    private boolean K0 = false;
    protected b.e.b.d M0 = new b.e.b.d();
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

    private void R0(e eVar) {
        int i2 = this.R0 + 1;
        c[] cVarArr = this.U0;
        if (i2 >= cVarArr.length) {
            this.U0 = (c[]) Arrays.copyOf(cVarArr, cVarArr.length * 2);
        }
        this.U0[this.R0] = new c(eVar, 0, c1());
        this.R0++;
    }

    private void S0(e eVar) {
        int i2 = this.S0 + 1;
        c[] cVarArr = this.T0;
        if (i2 >= cVarArr.length) {
            this.T0 = (c[]) Arrays.copyOf(cVarArr, cVarArr.length * 2);
        }
        this.T0[this.S0] = new c(eVar, 1, c1());
        this.S0++;
    }

    private void g1() {
        this.R0 = 0;
        this.S0 = 0;
    }

    @Override // b.e.b.k.e
    public void J0(boolean z, boolean z2) {
        super.J0(z, z2);
        int size = this.G0.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.G0.get(i2).J0(z, z2);
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
    @Override // b.e.b.k.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void M0() {
        /*
            Method dump skipped, instruction units count: 482
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.e.b.k.f.M0():void");
    }

    void P0(e eVar, int i2) {
        if (i2 == 0) {
            R0(eVar);
        } else if (i2 == 1) {
            S0(eVar);
        }
    }

    public boolean Q0(b.e.b.d dVar) {
        f(dVar);
        int size = this.G0.size();
        boolean z = false;
        for (int i2 = 0; i2 < size; i2++) {
            e eVar = this.G0.get(i2);
            eVar.p0(0, false);
            eVar.p0(1, false);
            if (eVar instanceof a) {
                z = true;
            }
        }
        if (z) {
            for (int i3 = 0; i3 < size; i3++) {
                e eVar2 = this.G0.get(i3);
                if (eVar2 instanceof a) {
                    ((a) eVar2).O0();
                }
            }
        }
        for (int i4 = 0; i4 < size; i4++) {
            e eVar3 = this.G0.get(i4);
            if (eVar3.e()) {
                eVar3.f(dVar);
            }
        }
        for (int i5 = 0; i5 < size; i5++) {
            e eVar4 = this.G0.get(i5);
            if (eVar4 instanceof f) {
                e.b[] bVarArr = eVar4.P;
                e.b bVar = bVarArr[0];
                e.b bVar2 = bVarArr[1];
                e.b bVar3 = e.b.WRAP_CONTENT;
                if (bVar == bVar3) {
                    eVar4.m0(e.b.FIXED);
                }
                if (bVar2 == bVar3) {
                    eVar4.B0(e.b.FIXED);
                }
                eVar4.f(dVar);
                if (bVar == bVar3) {
                    eVar4.m0(bVar);
                }
                if (bVar2 == bVar3) {
                    eVar4.B0(bVar2);
                }
            } else {
                k.a(this, dVar, eVar4);
                if (!eVar4.e()) {
                    eVar4.f(dVar);
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

    public boolean V0(boolean z, int i2) {
        return this.I0.h(z, i2);
    }

    public b.InterfaceC0038b W0() {
        return this.J0;
    }

    public int X0() {
        return this.a1;
    }

    public boolean Y0() {
        return false;
    }

    @Override // b.e.b.k.m, b.e.b.k.e
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

    public long e1(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.N0 = i9;
        this.O0 = i10;
        return this.H0.d(this, i2, i9, i10, i3, i4, i5, i6, i7, i8);
    }

    public boolean f1(int i2) {
        return (this.a1 & i2) == i2;
    }

    public void h1(b.InterfaceC0038b interfaceC0038b) {
        this.J0 = interfaceC0038b;
        this.I0.n(interfaceC0038b);
    }

    public void i1(int i2) {
        this.a1 = i2;
        b.e.b.d.f2372c = k.b(i2, 256);
    }

    public void j1(boolean z) {
        this.K0 = z;
    }

    public void k1(b.e.b.d dVar, boolean[] zArr) {
        zArr[2] = false;
        K0(dVar);
        int size = this.G0.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.G0.get(i2).K0(dVar);
        }
    }

    public void l1() {
        this.H0.e(this);
    }
}
