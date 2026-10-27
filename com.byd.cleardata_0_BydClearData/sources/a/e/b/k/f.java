package a.e.b.k;

import a.e.b.k.e;
import a.e.b.k.m.b;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class f extends l {
    public a.e.b.e B0;
    int D0;
    int E0;
    int F0;
    int G0;
    a.e.b.k.m.b x0 = new a.e.b.k.m.b(this);
    public a.e.b.k.m.e y0 = new a.e.b.k.m.e(this);
    protected b.InterfaceC0006b z0 = null;
    private boolean A0 = false;
    protected a.e.b.d C0 = new a.e.b.d();
    int H0 = 0;
    int I0 = 0;
    c[] J0 = new c[4];
    c[] K0 = new c[4];
    public boolean L0 = false;
    public boolean M0 = false;
    public boolean N0 = false;
    public int O0 = 0;
    public int P0 = 0;
    private int Q0 = 263;
    public boolean R0 = false;
    private boolean S0 = false;
    private boolean T0 = false;
    int U0 = 0;

    private void M0(e eVar) {
        int i = this.H0 + 1;
        c[] cVarArr = this.K0;
        if (i >= cVarArr.length) {
            this.K0 = (c[]) Arrays.copyOf(cVarArr, cVarArr.length * 2);
        }
        this.K0[this.H0] = new c(eVar, 0, W0());
        this.H0++;
    }

    private void N0(e eVar) {
        int i = this.I0 + 1;
        c[] cVarArr = this.J0;
        if (i >= cVarArr.length) {
            this.J0 = (c[]) Arrays.copyOf(cVarArr, cVarArr.length * 2);
        }
        this.J0[this.I0] = new c(eVar, 1, W0());
        this.I0++;
    }

    private void a1() {
        this.H0 = 0;
        this.I0 = 0;
    }

    @Override // a.e.b.k.e
    public void F0(boolean z, boolean z2) {
        super.F0(z, z2);
        int size = this.w0.size();
        for (int i = 0; i < size; i++) {
            this.w0.get(i).F0(z, z2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01c5 A[PHI: r0 r5
  0x01c5: PHI (r0v26 ??) = (r0v25 ??), (r0v28 ??), (r0v28 ??), (r0v28 ??) binds: [B:70:0x018a, B:78:0x01ad, B:79:0x01af, B:81:0x01b5] A[DONT_GENERATE, DONT_INLINE]
  0x01c5: PHI (r5v11 boolean) = (r5v10 boolean), (r5v13 boolean), (r5v13 boolean), (r5v13 boolean) binds: [B:70:0x018a, B:78:0x01ad, B:79:0x01af, B:81:0x01b5] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v26 */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v31 */
    /* JADX WARN: Type inference failed for: r0v33 */
    /* JADX WARN: Type inference failed for: r0v64 */
    /* JADX WARN: Type inference failed for: r0v65 */
    /* JADX WARN: Type inference failed for: r0v66 */
    /* JADX WARN: Type inference failed for: r0v67 */
    /* JADX WARN: Type inference failed for: r0v68 */
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
    @Override // a.e.b.k.l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void H0() {
        /*
            Method dump skipped, instruction units count: 480
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.e.b.k.f.H0():void");
    }

    void K0(e eVar, int i) {
        if (i == 0) {
            M0(eVar);
        } else if (i == 1) {
            N0(eVar);
        }
    }

    public boolean L0(a.e.b.d dVar) {
        f(dVar);
        int size = this.w0.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            e eVar = this.w0.get(i);
            eVar.l0(0, false);
            eVar.l0(1, false);
            if (eVar instanceof a) {
                z = true;
            }
        }
        if (z) {
            for (int i2 = 0; i2 < size; i2++) {
                e eVar2 = this.w0.get(i2);
                if (eVar2 instanceof a) {
                    ((a) eVar2).K0();
                }
            }
        }
        for (int i3 = 0; i3 < size; i3++) {
            e eVar3 = this.w0.get(i3);
            if (eVar3.e()) {
                eVar3.f(dVar);
            }
        }
        for (int i4 = 0; i4 < size; i4++) {
            e eVar4 = this.w0.get(i4);
            if (eVar4 instanceof f) {
                e.b[] bVarArr = eVar4.N;
                e.b bVar = bVarArr[0];
                e.b bVar2 = bVarArr[1];
                e.b bVar3 = e.b.WRAP_CONTENT;
                if (bVar == bVar3) {
                    eVar4.i0(e.b.FIXED);
                }
                if (bVar2 == bVar3) {
                    eVar4.x0(e.b.FIXED);
                }
                eVar4.f(dVar);
                if (bVar == bVar3) {
                    eVar4.i0(bVar);
                }
                if (bVar2 == bVar3) {
                    eVar4.x0(bVar2);
                }
            } else {
                j.a(this, dVar, eVar4);
                if (!eVar4.e()) {
                    eVar4.f(dVar);
                }
            }
        }
        if (this.H0 > 0) {
            b.a(this, dVar, 0);
        }
        if (this.I0 > 0) {
            b.a(this, dVar, 1);
        }
        return true;
    }

    public boolean O0(boolean z) {
        return this.y0.f(z);
    }

    public boolean P0(boolean z) {
        return this.y0.g(z);
    }

    public boolean Q0(boolean z, int i) {
        return this.y0.h(z, i);
    }

    public b.InterfaceC0006b R0() {
        return this.z0;
    }

    public int S0() {
        return this.Q0;
    }

    public void T0() {
        this.y0.j();
    }

    public void U0() {
        this.y0.k();
    }

    public boolean V0() {
        return this.T0;
    }

    @Override // a.e.b.k.l, a.e.b.k.e
    public void W() {
        this.C0.D();
        this.D0 = 0;
        this.F0 = 0;
        this.E0 = 0;
        this.G0 = 0;
        this.R0 = false;
        super.W();
    }

    public boolean W0() {
        return this.A0;
    }

    public boolean X0() {
        return this.S0;
    }

    public long Y0(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.D0 = i8;
        this.E0 = i9;
        return this.x0.d(this, i, i8, i9, i2, i3, i4, i5, i6, i7);
    }

    public boolean Z0(int i) {
        return (this.Q0 & i) == i;
    }

    public void b1(b.InterfaceC0006b interfaceC0006b) {
        this.z0 = interfaceC0006b;
        this.y0.n(interfaceC0006b);
    }

    public void c1(int i) {
        this.Q0 = i;
        a.e.b.d.f181c = j.b(i, 256);
    }

    public void d1(boolean z) {
        this.A0 = z;
    }

    public void e1(a.e.b.d dVar, boolean[] zArr) {
        zArr[2] = false;
        G0(dVar);
        int size = this.w0.size();
        for (int i = 0; i < size; i++) {
            this.w0.get(i).G0(dVar);
        }
    }

    public void f1() {
        this.x0.e(this);
    }
}
