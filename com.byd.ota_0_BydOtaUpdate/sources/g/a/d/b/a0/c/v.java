package g.a.d.b.a0.c;

import g.a.d.b.h;

/* JADX INFO: loaded from: classes.dex */
public class v extends h.c {
    public v(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        this(dVar, eVar, eVar2, false);
    }

    public v(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        super(dVar, eVar, eVar2);
        if ((eVar == null) != (eVar2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f5510f = z;
    }

    public v(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
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
        u uVar = (u) this.f5508d;
        if (uVar.i()) {
            return dVarI.v();
        }
        u uVar2 = (u) this.f5507c;
        u uVar3 = (u) this.f5509e[0];
        int[] iArrG = g.a.d.d.e.g();
        int[] iArrG2 = g.a.d.d.e.g();
        int[] iArrG3 = g.a.d.d.e.g();
        t.j(uVar.f5430h, iArrG3);
        int[] iArrG4 = g.a.d.d.e.g();
        t.j(iArrG3, iArrG4);
        boolean zH = uVar3.h();
        int[] iArr = uVar3.f5430h;
        if (!zH) {
            t.j(iArr, iArrG2);
            iArr = iArrG2;
        }
        t.m(uVar2.f5430h, iArr, iArrG);
        t.a(uVar2.f5430h, iArr, iArrG2);
        t.e(iArrG2, iArrG, iArrG2);
        t.i(g.a.d.d.e.b(iArrG2, iArrG2, iArrG2), iArrG2);
        t.e(iArrG3, uVar2.f5430h, iArrG3);
        t.i(g.a.d.d.m.G(6, iArrG3, 2, 0), iArrG3);
        t.i(g.a.d.d.m.H(6, iArrG4, 3, 0, iArrG), iArrG);
        u uVar4 = new u(iArrG4);
        t.j(iArrG2, uVar4.f5430h);
        int[] iArr2 = uVar4.f5430h;
        t.m(iArr2, iArrG3, iArr2);
        int[] iArr3 = uVar4.f5430h;
        t.m(iArr3, iArrG3, iArr3);
        u uVar5 = new u(iArrG3);
        t.m(iArrG3, uVar4.f5430h, uVar5.f5430h);
        int[] iArr4 = uVar5.f5430h;
        t.e(iArr4, iArrG2, iArr4);
        int[] iArr5 = uVar5.f5430h;
        t.m(iArr5, iArrG, iArr5);
        u uVar6 = new u(iArrG2);
        t.n(uVar.f5430h, uVar6.f5430h);
        if (!zH) {
            int[] iArr6 = uVar6.f5430h;
            t.e(iArr6, uVar3.f5430h, iArr6);
        }
        return new v(dVarI, uVar4, uVar5, new g.a.d.b.e[]{uVar6}, this.f5510f);
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
        u uVar = (u) this.f5507c;
        u uVar2 = (u) this.f5508d;
        u uVar3 = (u) hVar.q();
        u uVar4 = (u) hVar.r();
        u uVar5 = (u) this.f5509e[0];
        u uVar6 = (u) hVar.s(0);
        int[] iArrI = g.a.d.d.e.i();
        int[] iArrG = g.a.d.d.e.g();
        int[] iArrG2 = g.a.d.d.e.g();
        int[] iArrG3 = g.a.d.d.e.g();
        boolean zH = uVar5.h();
        if (zH) {
            iArr = uVar3.f5430h;
            iArr2 = uVar4.f5430h;
        } else {
            t.j(uVar5.f5430h, iArrG2);
            t.e(iArrG2, uVar3.f5430h, iArrG);
            t.e(iArrG2, uVar5.f5430h, iArrG2);
            t.e(iArrG2, uVar4.f5430h, iArrG2);
            iArr = iArrG;
            iArr2 = iArrG2;
        }
        boolean zH2 = uVar6.h();
        if (zH2) {
            iArr3 = uVar.f5430h;
            iArr4 = uVar2.f5430h;
        } else {
            t.j(uVar6.f5430h, iArrG3);
            t.e(iArrG3, uVar.f5430h, iArrI);
            t.e(iArrG3, uVar6.f5430h, iArrG3);
            t.e(iArrG3, uVar2.f5430h, iArrG3);
            iArr3 = iArrI;
            iArr4 = iArrG3;
        }
        int[] iArrG4 = g.a.d.d.e.g();
        t.m(iArr3, iArr, iArrG4);
        t.m(iArr4, iArr2, iArrG);
        if (g.a.d.d.e.u(iArrG4)) {
            return g.a.d.d.e.u(iArrG) ? J() : dVarI.v();
        }
        t.j(iArrG4, iArrG2);
        int[] iArrG5 = g.a.d.d.e.g();
        t.e(iArrG2, iArrG4, iArrG5);
        t.e(iArrG2, iArr3, iArrG2);
        t.g(iArrG5, iArrG5);
        g.a.d.d.e.x(iArr4, iArrG5, iArrI);
        t.i(g.a.d.d.e.b(iArrG2, iArrG2, iArrG5), iArrG5);
        u uVar7 = new u(iArrG3);
        t.j(iArrG, uVar7.f5430h);
        int[] iArr5 = uVar7.f5430h;
        t.m(iArr5, iArrG5, iArr5);
        u uVar8 = new u(iArrG5);
        t.m(iArrG2, uVar7.f5430h, uVar8.f5430h);
        t.f(uVar8.f5430h, iArrG, iArrI);
        t.h(iArrI, uVar8.f5430h);
        u uVar9 = new u(iArrG4);
        if (!zH) {
            int[] iArr6 = uVar9.f5430h;
            t.e(iArr6, uVar5.f5430h, iArr6);
        }
        if (!zH2) {
            int[] iArr7 = uVar9.f5430h;
            t.e(iArr7, uVar6.f5430h, iArr7);
        }
        return new v(dVarI, uVar7, uVar8, new g.a.d.b.e[]{uVar9}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h d() {
        return new v(null, f(), g());
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h z() {
        return u() ? this : new v(this.f5506b, this.f5507c, this.f5508d.m(), this.f5509e, this.f5510f);
    }
}
