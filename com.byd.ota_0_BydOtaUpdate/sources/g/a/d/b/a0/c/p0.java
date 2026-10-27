package g.a.d.b.a0.c;

import g.a.d.b.h;

/* JADX INFO: loaded from: classes.dex */
public class p0 extends h.c {
    public p0(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        this(dVar, eVar, eVar2, false);
    }

    public p0(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        super(dVar, eVar, eVar2);
        if ((eVar == null) != (eVar2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f5510f = z;
    }

    public p0(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
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
        o0 o0Var = (o0) this.f5508d;
        if (o0Var.i()) {
            return dVarI.v();
        }
        o0 o0Var2 = (o0) this.f5507c;
        o0 o0Var3 = (o0) this.f5509e[0];
        int[] iArrJ = g.a.d.d.m.j(12);
        int[] iArrJ2 = g.a.d.d.m.j(12);
        int[] iArrJ3 = g.a.d.d.m.j(12);
        n0.j(o0Var.f5394h, iArrJ3);
        int[] iArrJ4 = g.a.d.d.m.j(12);
        n0.j(iArrJ3, iArrJ4);
        boolean zH = o0Var3.h();
        int[] iArr = o0Var3.f5394h;
        if (!zH) {
            n0.j(iArr, iArrJ2);
            iArr = iArrJ2;
        }
        n0.m(o0Var2.f5394h, iArr, iArrJ);
        n0.a(o0Var2.f5394h, iArr, iArrJ2);
        n0.f(iArrJ2, iArrJ, iArrJ2);
        n0.i(g.a.d.d.m.c(12, iArrJ2, iArrJ2, iArrJ2), iArrJ2);
        n0.f(iArrJ3, o0Var2.f5394h, iArrJ3);
        n0.i(g.a.d.d.m.G(12, iArrJ3, 2, 0), iArrJ3);
        n0.i(g.a.d.d.m.H(12, iArrJ4, 3, 0, iArrJ), iArrJ);
        o0 o0Var4 = new o0(iArrJ4);
        n0.j(iArrJ2, o0Var4.f5394h);
        int[] iArr2 = o0Var4.f5394h;
        n0.m(iArr2, iArrJ3, iArr2);
        int[] iArr3 = o0Var4.f5394h;
        n0.m(iArr3, iArrJ3, iArr3);
        o0 o0Var5 = new o0(iArrJ3);
        n0.m(iArrJ3, o0Var4.f5394h, o0Var5.f5394h);
        int[] iArr4 = o0Var5.f5394h;
        n0.f(iArr4, iArrJ2, iArr4);
        int[] iArr5 = o0Var5.f5394h;
        n0.m(iArr5, iArrJ, iArr5);
        o0 o0Var6 = new o0(iArrJ2);
        n0.n(o0Var.f5394h, o0Var6.f5394h);
        if (!zH) {
            int[] iArr6 = o0Var6.f5394h;
            n0.f(iArr6, o0Var3.f5394h, iArr6);
        }
        return new p0(dVarI, o0Var4, o0Var5, new g.a.d.b.e[]{o0Var6}, this.f5510f);
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
        o0 o0Var = (o0) this.f5507c;
        o0 o0Var2 = (o0) this.f5508d;
        o0 o0Var3 = (o0) hVar.q();
        o0 o0Var4 = (o0) hVar.r();
        o0 o0Var5 = (o0) this.f5509e[0];
        o0 o0Var6 = (o0) hVar.s(0);
        int[] iArrJ = g.a.d.d.m.j(24);
        int[] iArrJ2 = g.a.d.d.m.j(24);
        int[] iArrJ3 = g.a.d.d.m.j(12);
        int[] iArrJ4 = g.a.d.d.m.j(12);
        boolean zH = o0Var5.h();
        if (zH) {
            iArr = o0Var3.f5394h;
            iArr2 = o0Var4.f5394h;
        } else {
            n0.j(o0Var5.f5394h, iArrJ3);
            n0.f(iArrJ3, o0Var3.f5394h, iArrJ2);
            n0.f(iArrJ3, o0Var5.f5394h, iArrJ3);
            n0.f(iArrJ3, o0Var4.f5394h, iArrJ3);
            iArr = iArrJ2;
            iArr2 = iArrJ3;
        }
        boolean zH2 = o0Var6.h();
        if (zH2) {
            iArr3 = o0Var.f5394h;
            iArr4 = o0Var2.f5394h;
        } else {
            n0.j(o0Var6.f5394h, iArrJ4);
            n0.f(iArrJ4, o0Var.f5394h, iArrJ);
            n0.f(iArrJ4, o0Var6.f5394h, iArrJ4);
            n0.f(iArrJ4, o0Var2.f5394h, iArrJ4);
            iArr3 = iArrJ;
            iArr4 = iArrJ4;
        }
        int[] iArrJ5 = g.a.d.d.m.j(12);
        n0.m(iArr3, iArr, iArrJ5);
        int[] iArrJ6 = g.a.d.d.m.j(12);
        n0.m(iArr4, iArr2, iArrJ6);
        if (g.a.d.d.m.w(12, iArrJ5)) {
            return g.a.d.d.m.w(12, iArrJ6) ? J() : dVarI.v();
        }
        n0.j(iArrJ5, iArrJ3);
        int[] iArrJ7 = g.a.d.d.m.j(12);
        n0.f(iArrJ3, iArrJ5, iArrJ7);
        n0.f(iArrJ3, iArr3, iArrJ3);
        n0.g(iArrJ7, iArrJ7);
        g.a.d.d.i.a(iArr4, iArrJ7, iArrJ);
        n0.i(g.a.d.d.m.c(12, iArrJ3, iArrJ3, iArrJ7), iArrJ7);
        o0 o0Var7 = new o0(iArrJ4);
        n0.j(iArrJ6, o0Var7.f5394h);
        int[] iArr5 = o0Var7.f5394h;
        n0.m(iArr5, iArrJ7, iArr5);
        o0 o0Var8 = new o0(iArrJ7);
        n0.m(iArrJ3, o0Var7.f5394h, o0Var8.f5394h);
        g.a.d.d.i.a(o0Var8.f5394h, iArrJ6, iArrJ2);
        n0.b(iArrJ, iArrJ2, iArrJ);
        n0.h(iArrJ, o0Var8.f5394h);
        o0 o0Var9 = new o0(iArrJ5);
        if (!zH) {
            int[] iArr6 = o0Var9.f5394h;
            n0.f(iArr6, o0Var5.f5394h, iArr6);
        }
        if (!zH2) {
            int[] iArr7 = o0Var9.f5394h;
            n0.f(iArr7, o0Var6.f5394h, iArr7);
        }
        return new p0(dVarI, o0Var7, o0Var8, new g.a.d.b.e[]{o0Var9}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h d() {
        return new p0(null, f(), g());
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h z() {
        return u() ? this : new p0(this.f5506b, this.f5507c, this.f5508d.m(), this.f5509e, this.f5510f);
    }
}
