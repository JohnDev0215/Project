package g.a.d.b.a0.c;

import g.a.d.b.h;

/* JADX INFO: loaded from: classes.dex */
public class d extends h.c {
    public d(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        this(dVar, eVar, eVar2, false);
    }

    public d(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        super(dVar, eVar, eVar2);
        if ((eVar == null) != (eVar2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f5510f = z;
    }

    public d(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
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
        c cVar = (c) this.f5508d;
        if (cVar.i()) {
            return dVarI.v();
        }
        c cVar2 = (c) this.f5507c;
        c cVar3 = (c) this.f5509e[0];
        int[] iArrE = g.a.d.d.c.e();
        int[] iArrE2 = g.a.d.d.c.e();
        int[] iArrE3 = g.a.d.d.c.e();
        b.j(cVar.f5308h, iArrE3);
        int[] iArrE4 = g.a.d.d.c.e();
        b.j(iArrE3, iArrE4);
        boolean zH = cVar3.h();
        int[] iArr = cVar3.f5308h;
        if (!zH) {
            b.j(iArr, iArrE2);
            iArr = iArrE2;
        }
        b.m(cVar2.f5308h, iArr, iArrE);
        b.a(cVar2.f5308h, iArr, iArrE2);
        b.e(iArrE2, iArrE, iArrE2);
        b.i(g.a.d.d.c.b(iArrE2, iArrE2, iArrE2), iArrE2);
        b.e(iArrE3, cVar2.f5308h, iArrE3);
        b.i(g.a.d.d.m.G(4, iArrE3, 2, 0), iArrE3);
        b.i(g.a.d.d.m.H(4, iArrE4, 3, 0, iArrE), iArrE);
        c cVar4 = new c(iArrE4);
        b.j(iArrE2, cVar4.f5308h);
        int[] iArr2 = cVar4.f5308h;
        b.m(iArr2, iArrE3, iArr2);
        int[] iArr3 = cVar4.f5308h;
        b.m(iArr3, iArrE3, iArr3);
        c cVar5 = new c(iArrE3);
        b.m(iArrE3, cVar4.f5308h, cVar5.f5308h);
        int[] iArr4 = cVar5.f5308h;
        b.e(iArr4, iArrE2, iArr4);
        int[] iArr5 = cVar5.f5308h;
        b.m(iArr5, iArrE, iArr5);
        c cVar6 = new c(iArrE2);
        b.n(cVar.f5308h, cVar6.f5308h);
        if (!zH) {
            int[] iArr6 = cVar6.f5308h;
            b.e(iArr6, cVar3.f5308h, iArr6);
        }
        return new d(dVarI, cVar4, cVar5, new g.a.d.b.e[]{cVar6}, this.f5510f);
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
        c cVar = (c) this.f5507c;
        c cVar2 = (c) this.f5508d;
        c cVar3 = (c) hVar.q();
        c cVar4 = (c) hVar.r();
        c cVar5 = (c) this.f5509e[0];
        c cVar6 = (c) hVar.s(0);
        int[] iArrG = g.a.d.d.c.g();
        int[] iArrE = g.a.d.d.c.e();
        int[] iArrE2 = g.a.d.d.c.e();
        int[] iArrE3 = g.a.d.d.c.e();
        boolean zH = cVar5.h();
        if (zH) {
            iArr = cVar3.f5308h;
            iArr2 = cVar4.f5308h;
        } else {
            b.j(cVar5.f5308h, iArrE2);
            b.e(iArrE2, cVar3.f5308h, iArrE);
            b.e(iArrE2, cVar5.f5308h, iArrE2);
            b.e(iArrE2, cVar4.f5308h, iArrE2);
            iArr = iArrE;
            iArr2 = iArrE2;
        }
        boolean zH2 = cVar6.h();
        if (zH2) {
            iArr3 = cVar.f5308h;
            iArr4 = cVar2.f5308h;
        } else {
            b.j(cVar6.f5308h, iArrE3);
            b.e(iArrE3, cVar.f5308h, iArrG);
            b.e(iArrE3, cVar6.f5308h, iArrE3);
            b.e(iArrE3, cVar2.f5308h, iArrE3);
            iArr3 = iArrG;
            iArr4 = iArrE3;
        }
        int[] iArrE4 = g.a.d.d.c.e();
        b.m(iArr3, iArr, iArrE4);
        b.m(iArr4, iArr2, iArrE);
        if (g.a.d.d.c.q(iArrE4)) {
            return g.a.d.d.c.q(iArrE) ? J() : dVarI.v();
        }
        b.j(iArrE4, iArrE2);
        int[] iArrE5 = g.a.d.d.c.e();
        b.e(iArrE2, iArrE4, iArrE5);
        b.e(iArrE2, iArr3, iArrE2);
        b.g(iArrE5, iArrE5);
        g.a.d.d.c.s(iArr4, iArrE5, iArrG);
        b.i(g.a.d.d.c.b(iArrE2, iArrE2, iArrE5), iArrE5);
        c cVar7 = new c(iArrE3);
        b.j(iArrE, cVar7.f5308h);
        int[] iArr5 = cVar7.f5308h;
        b.m(iArr5, iArrE5, iArr5);
        c cVar8 = new c(iArrE5);
        b.m(iArrE2, cVar7.f5308h, cVar8.f5308h);
        b.f(cVar8.f5308h, iArrE, iArrG);
        b.h(iArrG, cVar8.f5308h);
        c cVar9 = new c(iArrE4);
        if (!zH) {
            int[] iArr6 = cVar9.f5308h;
            b.e(iArr6, cVar5.f5308h, iArr6);
        }
        if (!zH2) {
            int[] iArr7 = cVar9.f5308h;
            b.e(iArr7, cVar6.f5308h, iArr7);
        }
        return new d(dVarI, cVar7, cVar8, new g.a.d.b.e[]{cVar9}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h d() {
        return new d(null, f(), g());
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h z() {
        return u() ? this : new d(this.f5506b, this.f5507c, this.f5508d.m(), this.f5509e, this.f5510f);
    }
}
