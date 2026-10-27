package g.a.d.b.a0.c;

import g.a.d.b.h;

/* JADX INFO: loaded from: classes.dex */
public class t0 extends h.c {
    public t0(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        this(dVar, eVar, eVar2, false);
    }

    public t0(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        super(dVar, eVar, eVar2);
        if ((eVar == null) != (eVar2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f5510f = z;
    }

    public t0(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
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
        s0 s0Var = (s0) this.f5508d;
        if (s0Var.i()) {
            return dVarI.v();
        }
        s0 s0Var2 = (s0) this.f5507c;
        s0 s0Var3 = (s0) this.f5509e[0];
        int[] iArrJ = g.a.d.d.m.j(17);
        int[] iArrJ2 = g.a.d.d.m.j(17);
        int[] iArrJ3 = g.a.d.d.m.j(17);
        r0.j(s0Var.f5419h, iArrJ3);
        int[] iArrJ4 = g.a.d.d.m.j(17);
        r0.j(iArrJ3, iArrJ4);
        boolean zH = s0Var3.h();
        int[] iArr = s0Var3.f5419h;
        if (!zH) {
            r0.j(iArr, iArrJ2);
            iArr = iArrJ2;
        }
        r0.l(s0Var2.f5419h, iArr, iArrJ);
        r0.a(s0Var2.f5419h, iArr, iArrJ2);
        r0.f(iArrJ2, iArrJ, iArrJ2);
        g.a.d.d.m.c(17, iArrJ2, iArrJ2, iArrJ2);
        r0.i(iArrJ2);
        r0.f(iArrJ3, s0Var2.f5419h, iArrJ3);
        g.a.d.d.m.G(17, iArrJ3, 2, 0);
        r0.i(iArrJ3);
        g.a.d.d.m.H(17, iArrJ4, 3, 0, iArrJ);
        r0.i(iArrJ);
        s0 s0Var4 = new s0(iArrJ4);
        r0.j(iArrJ2, s0Var4.f5419h);
        int[] iArr2 = s0Var4.f5419h;
        r0.l(iArr2, iArrJ3, iArr2);
        int[] iArr3 = s0Var4.f5419h;
        r0.l(iArr3, iArrJ3, iArr3);
        s0 s0Var5 = new s0(iArrJ3);
        r0.l(iArrJ3, s0Var4.f5419h, s0Var5.f5419h);
        int[] iArr4 = s0Var5.f5419h;
        r0.f(iArr4, iArrJ2, iArr4);
        int[] iArr5 = s0Var5.f5419h;
        r0.l(iArr5, iArrJ, iArr5);
        s0 s0Var6 = new s0(iArrJ2);
        r0.m(s0Var.f5419h, s0Var6.f5419h);
        if (!zH) {
            int[] iArr6 = s0Var6.f5419h;
            r0.f(iArr6, s0Var3.f5419h, iArr6);
        }
        return new t0(dVarI, s0Var4, s0Var5, new g.a.d.b.e[]{s0Var6}, this.f5510f);
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
        s0 s0Var = (s0) this.f5507c;
        s0 s0Var2 = (s0) this.f5508d;
        s0 s0Var3 = (s0) hVar.q();
        s0 s0Var4 = (s0) hVar.r();
        s0 s0Var5 = (s0) this.f5509e[0];
        s0 s0Var6 = (s0) hVar.s(0);
        int[] iArrJ = g.a.d.d.m.j(17);
        int[] iArrJ2 = g.a.d.d.m.j(17);
        int[] iArrJ3 = g.a.d.d.m.j(17);
        int[] iArrJ4 = g.a.d.d.m.j(17);
        boolean zH = s0Var5.h();
        if (zH) {
            iArr = s0Var3.f5419h;
            iArr2 = s0Var4.f5419h;
        } else {
            r0.j(s0Var5.f5419h, iArrJ3);
            r0.f(iArrJ3, s0Var3.f5419h, iArrJ2);
            r0.f(iArrJ3, s0Var5.f5419h, iArrJ3);
            r0.f(iArrJ3, s0Var4.f5419h, iArrJ3);
            iArr = iArrJ2;
            iArr2 = iArrJ3;
        }
        boolean zH2 = s0Var6.h();
        if (zH2) {
            iArr3 = s0Var.f5419h;
            iArr4 = s0Var2.f5419h;
        } else {
            r0.j(s0Var6.f5419h, iArrJ4);
            r0.f(iArrJ4, s0Var.f5419h, iArrJ);
            r0.f(iArrJ4, s0Var6.f5419h, iArrJ4);
            r0.f(iArrJ4, s0Var2.f5419h, iArrJ4);
            iArr3 = iArrJ;
            iArr4 = iArrJ4;
        }
        int[] iArrJ5 = g.a.d.d.m.j(17);
        r0.l(iArr3, iArr, iArrJ5);
        r0.l(iArr4, iArr2, iArrJ2);
        if (g.a.d.d.m.w(17, iArrJ5)) {
            return g.a.d.d.m.w(17, iArrJ2) ? J() : dVarI.v();
        }
        r0.j(iArrJ5, iArrJ3);
        int[] iArrJ6 = g.a.d.d.m.j(17);
        r0.f(iArrJ3, iArrJ5, iArrJ6);
        r0.f(iArrJ3, iArr3, iArrJ3);
        r0.f(iArr4, iArrJ6, iArrJ);
        s0 s0Var7 = new s0(iArrJ4);
        r0.j(iArrJ2, s0Var7.f5419h);
        int[] iArr5 = s0Var7.f5419h;
        r0.a(iArr5, iArrJ6, iArr5);
        int[] iArr6 = s0Var7.f5419h;
        r0.l(iArr6, iArrJ3, iArr6);
        int[] iArr7 = s0Var7.f5419h;
        r0.l(iArr7, iArrJ3, iArr7);
        s0 s0Var8 = new s0(iArrJ6);
        r0.l(iArrJ3, s0Var7.f5419h, s0Var8.f5419h);
        r0.f(s0Var8.f5419h, iArrJ2, iArrJ2);
        r0.l(iArrJ2, iArrJ, s0Var8.f5419h);
        s0 s0Var9 = new s0(iArrJ5);
        if (!zH) {
            int[] iArr8 = s0Var9.f5419h;
            r0.f(iArr8, s0Var5.f5419h, iArr8);
        }
        if (!zH2) {
            int[] iArr9 = s0Var9.f5419h;
            r0.f(iArr9, s0Var6.f5419h, iArr9);
        }
        return new t0(dVarI, s0Var7, s0Var8, new g.a.d.b.e[]{s0Var9}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h d() {
        return new t0(null, f(), g());
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h z() {
        return u() ? this : new t0(this.f5506b, this.f5507c, this.f5508d.m(), this.f5509e, this.f5510f);
    }
}
