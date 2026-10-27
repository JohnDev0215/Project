package g.a.d.b.a0.c;

import g.a.d.b.h;

/* JADX INFO: loaded from: classes.dex */
public class l0 extends h.c {
    public l0(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        this(dVar, eVar, eVar2, false);
    }

    public l0(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        super(dVar, eVar, eVar2);
        if ((eVar == null) != (eVar2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f5510f = z;
    }

    public l0(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
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
        k0 k0Var = (k0) this.f5508d;
        if (k0Var.i()) {
            return dVarI.v();
        }
        k0 k0Var2 = (k0) this.f5507c;
        k0 k0Var3 = (k0) this.f5509e[0];
        int[] iArrH = g.a.d.d.g.h();
        int[] iArrH2 = g.a.d.d.g.h();
        int[] iArrH3 = g.a.d.d.g.h();
        j0.j(k0Var.f5366h, iArrH3);
        int[] iArrH4 = g.a.d.d.g.h();
        j0.j(iArrH3, iArrH4);
        boolean zH = k0Var3.h();
        int[] iArr = k0Var3.f5366h;
        if (!zH) {
            j0.j(iArr, iArrH2);
            iArr = iArrH2;
        }
        j0.m(k0Var2.f5366h, iArr, iArrH);
        j0.a(k0Var2.f5366h, iArr, iArrH2);
        j0.e(iArrH2, iArrH, iArrH2);
        j0.i(g.a.d.d.g.b(iArrH2, iArrH2, iArrH2), iArrH2);
        j0.e(iArrH3, k0Var2.f5366h, iArrH3);
        j0.i(g.a.d.d.m.G(8, iArrH3, 2, 0), iArrH3);
        j0.i(g.a.d.d.m.H(8, iArrH4, 3, 0, iArrH), iArrH);
        k0 k0Var4 = new k0(iArrH4);
        j0.j(iArrH2, k0Var4.f5366h);
        int[] iArr2 = k0Var4.f5366h;
        j0.m(iArr2, iArrH3, iArr2);
        int[] iArr3 = k0Var4.f5366h;
        j0.m(iArr3, iArrH3, iArr3);
        k0 k0Var5 = new k0(iArrH3);
        j0.m(iArrH3, k0Var4.f5366h, k0Var5.f5366h);
        int[] iArr4 = k0Var5.f5366h;
        j0.e(iArr4, iArrH2, iArr4);
        int[] iArr5 = k0Var5.f5366h;
        j0.m(iArr5, iArrH, iArr5);
        k0 k0Var6 = new k0(iArrH2);
        j0.n(k0Var.f5366h, k0Var6.f5366h);
        if (!zH) {
            int[] iArr6 = k0Var6.f5366h;
            j0.e(iArr6, k0Var3.f5366h, iArr6);
        }
        return new l0(dVarI, k0Var4, k0Var5, new g.a.d.b.e[]{k0Var6}, this.f5510f);
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
        k0 k0Var = (k0) this.f5507c;
        k0 k0Var2 = (k0) this.f5508d;
        k0 k0Var3 = (k0) hVar.q();
        k0 k0Var4 = (k0) hVar.r();
        k0 k0Var5 = (k0) this.f5509e[0];
        k0 k0Var6 = (k0) hVar.s(0);
        int[] iArrJ = g.a.d.d.g.j();
        int[] iArrH = g.a.d.d.g.h();
        int[] iArrH2 = g.a.d.d.g.h();
        int[] iArrH3 = g.a.d.d.g.h();
        boolean zH = k0Var5.h();
        if (zH) {
            iArr = k0Var3.f5366h;
            iArr2 = k0Var4.f5366h;
        } else {
            j0.j(k0Var5.f5366h, iArrH2);
            j0.e(iArrH2, k0Var3.f5366h, iArrH);
            j0.e(iArrH2, k0Var5.f5366h, iArrH2);
            j0.e(iArrH2, k0Var4.f5366h, iArrH2);
            iArr = iArrH;
            iArr2 = iArrH2;
        }
        boolean zH2 = k0Var6.h();
        if (zH2) {
            iArr3 = k0Var.f5366h;
            iArr4 = k0Var2.f5366h;
        } else {
            j0.j(k0Var6.f5366h, iArrH3);
            j0.e(iArrH3, k0Var.f5366h, iArrJ);
            j0.e(iArrH3, k0Var6.f5366h, iArrH3);
            j0.e(iArrH3, k0Var2.f5366h, iArrH3);
            iArr3 = iArrJ;
            iArr4 = iArrH3;
        }
        int[] iArrH4 = g.a.d.d.g.h();
        j0.m(iArr3, iArr, iArrH4);
        j0.m(iArr4, iArr2, iArrH);
        if (g.a.d.d.g.v(iArrH4)) {
            return g.a.d.d.g.v(iArrH) ? J() : dVarI.v();
        }
        j0.j(iArrH4, iArrH2);
        int[] iArrH5 = g.a.d.d.g.h();
        j0.e(iArrH2, iArrH4, iArrH5);
        j0.e(iArrH2, iArr3, iArrH2);
        j0.g(iArrH5, iArrH5);
        g.a.d.d.g.y(iArr4, iArrH5, iArrJ);
        j0.i(g.a.d.d.g.b(iArrH2, iArrH2, iArrH5), iArrH5);
        k0 k0Var7 = new k0(iArrH3);
        j0.j(iArrH, k0Var7.f5366h);
        int[] iArr5 = k0Var7.f5366h;
        j0.m(iArr5, iArrH5, iArr5);
        k0 k0Var8 = new k0(iArrH5);
        j0.m(iArrH2, k0Var7.f5366h, k0Var8.f5366h);
        j0.f(k0Var8.f5366h, iArrH, iArrJ);
        j0.h(iArrJ, k0Var8.f5366h);
        k0 k0Var9 = new k0(iArrH4);
        if (!zH) {
            int[] iArr6 = k0Var9.f5366h;
            j0.e(iArr6, k0Var5.f5366h, iArr6);
        }
        if (!zH2) {
            int[] iArr7 = k0Var9.f5366h;
            j0.e(iArr7, k0Var6.f5366h, iArr7);
        }
        return new l0(dVarI, k0Var7, k0Var8, new g.a.d.b.e[]{k0Var9}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h d() {
        return new l0(null, f(), g());
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h z() {
        return u() ? this : new l0(this.f5506b, this.f5507c, this.f5508d.m(), this.f5509e, this.f5510f);
    }
}
