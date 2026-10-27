package b.e.b.k;

import b.e.b.k.e;
import b.e.b.k.n.b;

/* JADX INFO: compiled from: VirtualLayout.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends j {
    private int I0 = 0;
    private int J0 = 0;
    private int K0 = 0;
    private int L0 = 0;
    private int M0 = 0;
    private int N0 = 0;
    private int O0 = 0;
    private int P0 = 0;
    private boolean Q0 = false;
    private int R0 = 0;
    private int S0 = 0;
    protected b.a T0 = new b.a();
    b.InterfaceC0038b U0 = null;

    public void L0(boolean z) {
        int i2 = this.M0;
        if (i2 > 0 || this.N0 > 0) {
            if (z) {
                this.O0 = this.N0;
                this.P0 = i2;
            } else {
                this.O0 = i2;
                this.P0 = this.N0;
            }
        }
    }

    public void M0() {
        for (int i2 = 0; i2 < this.H0; i2++) {
            e eVar = this.G0[i2];
            if (eVar != null) {
                eVar.r0(true);
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

    public void T0(int i2, int i3, int i4, int i5) {
    }

    protected void U0(e eVar, e.b bVar, int i2, e.b bVar2, int i3) {
        while (this.U0 == null && H() != null) {
            this.U0 = ((f) H()).W0();
        }
        b.a aVar = this.T0;
        aVar.f2468a = bVar;
        aVar.f2469b = bVar2;
        aVar.f2470c = i2;
        aVar.f2471d = i3;
        this.U0.a(eVar, aVar);
        eVar.F0(this.T0.f2472e);
        eVar.i0(this.T0.f2473f);
        eVar.h0(this.T0.f2475h);
        eVar.c0(this.T0.f2474g);
    }

    protected boolean V0() {
        e eVar = this.Q;
        b.InterfaceC0038b interfaceC0038bW0 = eVar != null ? ((f) eVar).W0() : null;
        if (interfaceC0038bW0 == null) {
            return false;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= this.H0) {
                return true;
            }
            e eVar2 = this.G0[i2];
            if (eVar2 != null && !(eVar2 instanceof h)) {
                e.b bVarT = eVar2.t(0);
                e.b bVarT2 = eVar2.t(1);
                e.b bVar = e.b.MATCH_CONSTRAINT;
                if (!(bVarT == bVar && eVar2.m != 1 && bVarT2 == bVar && eVar2.n != 1)) {
                    if (bVarT == bVar) {
                        bVarT = e.b.WRAP_CONTENT;
                    }
                    if (bVarT2 == bVar) {
                        bVarT2 = e.b.WRAP_CONTENT;
                    }
                    b.a aVar = this.T0;
                    aVar.f2468a = bVarT;
                    aVar.f2469b = bVarT2;
                    aVar.f2470c = eVar2.Q();
                    this.T0.f2471d = eVar2.w();
                    interfaceC0038bW0.a(eVar2, this.T0);
                    eVar2.F0(this.T0.f2472e);
                    eVar2.i0(this.T0.f2473f);
                    eVar2.c0(this.T0.f2474g);
                }
            }
            i2++;
        }
    }

    public boolean W0() {
        return this.Q0;
    }

    protected void X0(boolean z) {
        this.Q0 = z;
    }

    public void Y0(int i2, int i3) {
        this.R0 = i2;
        this.S0 = i3;
    }

    public void Z0(int i2) {
        this.K0 = i2;
        this.I0 = i2;
        this.L0 = i2;
        this.J0 = i2;
        this.M0 = i2;
        this.N0 = i2;
    }

    public void a1(int i2) {
        this.J0 = i2;
    }

    @Override // b.e.b.k.j, b.e.b.k.i
    public void b(f fVar) {
        M0();
    }

    public void b1(int i2) {
        this.N0 = i2;
    }

    public void c1(int i2) {
        this.K0 = i2;
        this.O0 = i2;
    }

    public void d1(int i2) {
        this.L0 = i2;
        this.P0 = i2;
    }

    public void e1(int i2) {
        this.M0 = i2;
        this.O0 = i2;
        this.P0 = i2;
    }

    public void f1(int i2) {
        this.I0 = i2;
    }
}
