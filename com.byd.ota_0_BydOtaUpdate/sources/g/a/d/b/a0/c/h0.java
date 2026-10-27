package g.a.d.b.a0.c;

import g.a.d.b.h;

/* JADX INFO: loaded from: classes.dex */
public class h0 extends h.c {
    public h0(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        this(dVar, eVar, eVar2, false);
    }

    public h0(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        super(dVar, eVar, eVar2);
        if ((eVar == null) != (eVar2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f5510f = z;
    }

    public h0(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        super(dVar, eVar, eVar2, eVarArr);
        this.f5510f = z;
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h H() {
        return (u() || this.f5508d.i()) ? this : J().a(this);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h J() {
        if (u()) {
            return this;
        }
        g.a.d.b.d dVarI = i();
        g0 g0Var = (g0) this.f5508d;
        if (g0Var.i()) {
            return dVarI.v();
        }
        g0 g0Var2 = (g0) this.f5507c;
        g0 g0Var3 = (g0) this.f5509e[0];
        int[] iArrH = g.a.d.d.g.h();
        f0.i(g0Var.f5338h, iArrH);
        int[] iArrH2 = g.a.d.d.g.h();
        f0.i(iArrH, iArrH2);
        int[] iArrH3 = g.a.d.d.g.h();
        f0.i(g0Var2.f5338h, iArrH3);
        f0.h(g.a.d.d.g.b(iArrH3, iArrH3, iArrH3), iArrH3);
        f0.d(iArrH, g0Var2.f5338h, iArrH);
        f0.h(g.a.d.d.m.G(8, iArrH, 2, 0), iArrH);
        int[] iArrH4 = g.a.d.d.g.h();
        f0.h(g.a.d.d.m.H(8, iArrH2, 3, 0, iArrH4), iArrH4);
        g0 g0Var4 = new g0(iArrH2);
        f0.i(iArrH3, g0Var4.f5338h);
        int[] iArr = g0Var4.f5338h;
        f0.k(iArr, iArrH, iArr);
        int[] iArr2 = g0Var4.f5338h;
        f0.k(iArr2, iArrH, iArr2);
        g0 g0Var5 = new g0(iArrH);
        f0.k(iArrH, g0Var4.f5338h, g0Var5.f5338h);
        int[] iArr3 = g0Var5.f5338h;
        f0.d(iArr3, iArrH3, iArr3);
        int[] iArr4 = g0Var5.f5338h;
        f0.k(iArr4, iArrH4, iArr4);
        g0 g0Var6 = new g0(iArrH3);
        f0.l(g0Var.f5338h, g0Var6.f5338h);
        if (!g0Var3.h()) {
            int[] iArr5 = g0Var6.f5338h;
            f0.d(iArr5, g0Var3.f5338h, iArr5);
        }
        return new h0(dVarI, g0Var4, g0Var5, new g.a.d.b.e[]{g0Var6}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h K(g.a.d.b.h hVar) {
        return this == hVar ? H() : u() ? hVar : hVar.u() ? J() : this.f5508d.i() ? hVar : J().a(hVar);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h a(g.a.d.b.h hVar) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        if (u()) {
            return hVar;
        }
        if (hVar.u()) {
            return this;
        }
        if (this == hVar) {
            return J();
        }
        g.a.d.b.d dVarI = i();
        g0 g0Var = (g0) this.f5507c;
        g0 g0Var2 = (g0) this.f5508d;
        g0 g0Var3 = (g0) hVar.q();
        g0 g0Var4 = (g0) hVar.r();
        g0 g0Var5 = (g0) this.f5509e[0];
        g0 g0Var6 = (g0) hVar.s(0);
        int[] iArrJ = g.a.d.d.g.j();
        int[] iArrH = g.a.d.d.g.h();
        int[] iArrH2 = g.a.d.d.g.h();
        int[] iArrH3 = g.a.d.d.g.h();
        boolean zH = g0Var5.h();
        if (zH) {
            iArr = g0Var3.f5338h;
            iArr2 = g0Var4.f5338h;
        } else {
            f0.i(g0Var5.f5338h, iArrH2);
            f0.d(iArrH2, g0Var3.f5338h, iArrH);
            f0.d(iArrH2, g0Var5.f5338h, iArrH2);
            f0.d(iArrH2, g0Var4.f5338h, iArrH2);
            iArr = iArrH;
            iArr2 = iArrH2;
        }
        boolean zH2 = g0Var6.h();
        if (zH2) {
            iArr3 = g0Var.f5338h;
            iArr4 = g0Var2.f5338h;
        } else {
            f0.i(g0Var6.f5338h, iArrH3);
            f0.d(iArrH3, g0Var.f5338h, iArrJ);
            f0.d(iArrH3, g0Var6.f5338h, iArrH3);
            f0.d(iArrH3, g0Var2.f5338h, iArrH3);
            iArr3 = iArrJ;
            iArr4 = iArrH3;
        }
        int[] iArrH4 = g.a.d.d.g.h();
        f0.k(iArr3, iArr, iArrH4);
        f0.k(iArr4, iArr2, iArrH);
        if (g.a.d.d.g.v(iArrH4)) {
            return g.a.d.d.g.v(iArrH) ? J() : dVarI.v();
        }
        f0.i(iArrH4, iArrH2);
        int[] iArrH5 = g.a.d.d.g.h();
        f0.d(iArrH2, iArrH4, iArrH5);
        f0.d(iArrH2, iArr3, iArrH2);
        f0.f(iArrH5, iArrH5);
        g.a.d.d.g.y(iArr4, iArrH5, iArrJ);
        f0.h(g.a.d.d.g.b(iArrH2, iArrH2, iArrH5), iArrH5);
        g0 g0Var7 = new g0(iArrH3);
        f0.i(iArrH, g0Var7.f5338h);
        int[] iArr5 = g0Var7.f5338h;
        f0.k(iArr5, iArrH5, iArr5);
        g0 g0Var8 = new g0(iArrH5);
        f0.k(iArrH2, g0Var7.f5338h, g0Var8.f5338h);
        f0.e(g0Var8.f5338h, iArrH, iArrJ);
        f0.g(iArrJ, g0Var8.f5338h);
        g0 g0Var9 = new g0(iArrH4);
        if (!zH) {
            int[] iArr6 = g0Var9.f5338h;
            f0.d(iArr6, g0Var5.f5338h, iArr6);
        }
        if (!zH2) {
            int[] iArr7 = g0Var9.f5338h;
            f0.d(iArr7, g0Var6.f5338h, iArr7);
        }
        return new h0(dVarI, g0Var7, g0Var8, new g.a.d.b.e[]{g0Var9}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h d() {
        return new h0(null, f(), g());
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h z() {
        return u() ? this : new h0(this.f5506b, this.f5507c, this.f5508d.m(), this.f5509e, this.f5510f);
    }
}
