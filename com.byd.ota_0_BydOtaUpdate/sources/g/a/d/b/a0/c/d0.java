package g.a.d.b.a0.c;

import g.a.d.b.h;

/* JADX INFO: loaded from: classes.dex */
public class d0 extends h.c {
    public d0(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        this(dVar, eVar, eVar2, false);
    }

    public d0(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        super(dVar, eVar, eVar2);
        if ((eVar == null) != (eVar2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f5510f = z;
    }

    public d0(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
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
        c0 c0Var = (c0) this.f5508d;
        if (c0Var.i()) {
            return dVarI.v();
        }
        c0 c0Var2 = (c0) this.f5507c;
        c0 c0Var3 = (c0) this.f5509e[0];
        int[] iArrE = g.a.d.d.f.e();
        int[] iArrE2 = g.a.d.d.f.e();
        int[] iArrE3 = g.a.d.d.f.e();
        b0.j(c0Var.f5310h, iArrE3);
        int[] iArrE4 = g.a.d.d.f.e();
        b0.j(iArrE3, iArrE4);
        boolean zH = c0Var3.h();
        int[] iArr = c0Var3.f5310h;
        if (!zH) {
            b0.j(iArr, iArrE2);
            iArr = iArrE2;
        }
        b0.m(c0Var2.f5310h, iArr, iArrE);
        b0.a(c0Var2.f5310h, iArr, iArrE2);
        b0.e(iArrE2, iArrE, iArrE2);
        b0.i(g.a.d.d.f.b(iArrE2, iArrE2, iArrE2), iArrE2);
        b0.e(iArrE3, c0Var2.f5310h, iArrE3);
        b0.i(g.a.d.d.m.G(7, iArrE3, 2, 0), iArrE3);
        b0.i(g.a.d.d.m.H(7, iArrE4, 3, 0, iArrE), iArrE);
        c0 c0Var4 = new c0(iArrE4);
        b0.j(iArrE2, c0Var4.f5310h);
        int[] iArr2 = c0Var4.f5310h;
        b0.m(iArr2, iArrE3, iArr2);
        int[] iArr3 = c0Var4.f5310h;
        b0.m(iArr3, iArrE3, iArr3);
        c0 c0Var5 = new c0(iArrE3);
        b0.m(iArrE3, c0Var4.f5310h, c0Var5.f5310h);
        int[] iArr4 = c0Var5.f5310h;
        b0.e(iArr4, iArrE2, iArr4);
        int[] iArr5 = c0Var5.f5310h;
        b0.m(iArr5, iArrE, iArr5);
        c0 c0Var6 = new c0(iArrE2);
        b0.n(c0Var.f5310h, c0Var6.f5310h);
        if (!zH) {
            int[] iArr6 = c0Var6.f5310h;
            b0.e(iArr6, c0Var3.f5310h, iArr6);
        }
        return new d0(dVarI, c0Var4, c0Var5, new g.a.d.b.e[]{c0Var6}, this.f5510f);
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
        c0 c0Var = (c0) this.f5507c;
        c0 c0Var2 = (c0) this.f5508d;
        c0 c0Var3 = (c0) hVar.q();
        c0 c0Var4 = (c0) hVar.r();
        c0 c0Var5 = (c0) this.f5509e[0];
        c0 c0Var6 = (c0) hVar.s(0);
        int[] iArrF = g.a.d.d.f.f();
        int[] iArrE = g.a.d.d.f.e();
        int[] iArrE2 = g.a.d.d.f.e();
        int[] iArrE3 = g.a.d.d.f.e();
        boolean zH = c0Var5.h();
        if (zH) {
            iArr = c0Var3.f5310h;
            iArr2 = c0Var4.f5310h;
        } else {
            b0.j(c0Var5.f5310h, iArrE2);
            b0.e(iArrE2, c0Var3.f5310h, iArrE);
            b0.e(iArrE2, c0Var5.f5310h, iArrE2);
            b0.e(iArrE2, c0Var4.f5310h, iArrE2);
            iArr = iArrE;
            iArr2 = iArrE2;
        }
        boolean zH2 = c0Var6.h();
        if (zH2) {
            iArr3 = c0Var.f5310h;
            iArr4 = c0Var2.f5310h;
        } else {
            b0.j(c0Var6.f5310h, iArrE3);
            b0.e(iArrE3, c0Var.f5310h, iArrF);
            b0.e(iArrE3, c0Var6.f5310h, iArrE3);
            b0.e(iArrE3, c0Var2.f5310h, iArrE3);
            iArr3 = iArrF;
            iArr4 = iArrE3;
        }
        int[] iArrE4 = g.a.d.d.f.e();
        b0.m(iArr3, iArr, iArrE4);
        b0.m(iArr4, iArr2, iArrE);
        if (g.a.d.d.f.l(iArrE4)) {
            return g.a.d.d.f.l(iArrE) ? J() : dVarI.v();
        }
        b0.j(iArrE4, iArrE2);
        int[] iArrE5 = g.a.d.d.f.e();
        b0.e(iArrE2, iArrE4, iArrE5);
        b0.e(iArrE2, iArr3, iArrE2);
        b0.g(iArrE5, iArrE5);
        g.a.d.d.f.m(iArr4, iArrE5, iArrF);
        b0.i(g.a.d.d.f.b(iArrE2, iArrE2, iArrE5), iArrE5);
        c0 c0Var7 = new c0(iArrE3);
        b0.j(iArrE, c0Var7.f5310h);
        int[] iArr5 = c0Var7.f5310h;
        b0.m(iArr5, iArrE5, iArr5);
        c0 c0Var8 = new c0(iArrE5);
        b0.m(iArrE2, c0Var7.f5310h, c0Var8.f5310h);
        b0.f(c0Var8.f5310h, iArrE, iArrF);
        b0.h(iArrF, c0Var8.f5310h);
        c0 c0Var9 = new c0(iArrE4);
        if (!zH) {
            int[] iArr6 = c0Var9.f5310h;
            b0.e(iArr6, c0Var5.f5310h, iArr6);
        }
        if (!zH2) {
            int[] iArr7 = c0Var9.f5310h;
            b0.e(iArr7, c0Var6.f5310h, iArr7);
        }
        return new d0(dVarI, c0Var7, c0Var8, new g.a.d.b.e[]{c0Var9}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h d() {
        return new d0(null, f(), g());
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h z() {
        return u() ? this : new d0(this.f5506b, this.f5507c, this.f5508d.m(), this.f5509e, this.f5510f);
    }
}
