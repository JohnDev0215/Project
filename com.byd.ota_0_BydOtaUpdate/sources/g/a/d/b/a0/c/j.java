package g.a.d.b.a0.c;

import g.a.d.b.h;

/* JADX INFO: loaded from: classes.dex */
public class j extends h.c {
    public j(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        this(dVar, eVar, eVar2, false);
    }

    public j(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        super(dVar, eVar, eVar2);
        if ((eVar == null) != (eVar2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f5510f = z;
    }

    public j(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
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
        i iVar = (i) this.f5508d;
        if (iVar.i()) {
            return dVarI.v();
        }
        i iVar2 = (i) this.f5507c;
        i iVar3 = (i) this.f5509e[0];
        int[] iArrD = g.a.d.d.d.d();
        int[] iArrD2 = g.a.d.d.d.d();
        int[] iArrD3 = g.a.d.d.d.d();
        h.i(iVar.f5348h, iArrD3);
        int[] iArrD4 = g.a.d.d.d.d();
        h.i(iArrD3, iArrD4);
        boolean zH = iVar3.h();
        int[] iArr = iVar3.f5348h;
        if (!zH) {
            h.i(iArr, iArrD2);
            iArr = iArrD2;
        }
        h.k(iVar2.f5348h, iArr, iArrD);
        h.a(iVar2.f5348h, iArr, iArrD2);
        h.d(iArrD2, iArrD, iArrD2);
        h.h(g.a.d.d.d.b(iArrD2, iArrD2, iArrD2), iArrD2);
        h.d(iArrD3, iVar2.f5348h, iArrD3);
        h.h(g.a.d.d.m.G(5, iArrD3, 2, 0), iArrD3);
        h.h(g.a.d.d.m.H(5, iArrD4, 3, 0, iArrD), iArrD);
        i iVar4 = new i(iArrD4);
        h.i(iArrD2, iVar4.f5348h);
        int[] iArr2 = iVar4.f5348h;
        h.k(iArr2, iArrD3, iArr2);
        int[] iArr3 = iVar4.f5348h;
        h.k(iArr3, iArrD3, iArr3);
        i iVar5 = new i(iArrD3);
        h.k(iArrD3, iVar4.f5348h, iVar5.f5348h);
        int[] iArr4 = iVar5.f5348h;
        h.d(iArr4, iArrD2, iArr4);
        int[] iArr5 = iVar5.f5348h;
        h.k(iArr5, iArrD, iArr5);
        i iVar6 = new i(iArrD2);
        h.l(iVar.f5348h, iVar6.f5348h);
        if (!zH) {
            int[] iArr6 = iVar6.f5348h;
            h.d(iArr6, iVar3.f5348h, iArr6);
        }
        return new j(dVarI, iVar4, iVar5, new g.a.d.b.e[]{iVar6}, this.f5510f);
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
        i iVar = (i) this.f5507c;
        i iVar2 = (i) this.f5508d;
        i iVar3 = (i) hVar.q();
        i iVar4 = (i) hVar.r();
        i iVar5 = (i) this.f5509e[0];
        i iVar6 = (i) hVar.s(0);
        int[] iArrE = g.a.d.d.d.e();
        int[] iArrD = g.a.d.d.d.d();
        int[] iArrD2 = g.a.d.d.d.d();
        int[] iArrD3 = g.a.d.d.d.d();
        boolean zH = iVar5.h();
        if (zH) {
            iArr = iVar3.f5348h;
            iArr2 = iVar4.f5348h;
        } else {
            h.i(iVar5.f5348h, iArrD2);
            h.d(iArrD2, iVar3.f5348h, iArrD);
            h.d(iArrD2, iVar5.f5348h, iArrD2);
            h.d(iArrD2, iVar4.f5348h, iArrD2);
            iArr = iArrD;
            iArr2 = iArrD2;
        }
        boolean zH2 = iVar6.h();
        if (zH2) {
            iArr3 = iVar.f5348h;
            iArr4 = iVar2.f5348h;
        } else {
            h.i(iVar6.f5348h, iArrD3);
            h.d(iArrD3, iVar.f5348h, iArrE);
            h.d(iArrD3, iVar6.f5348h, iArrD3);
            h.d(iArrD3, iVar2.f5348h, iArrD3);
            iArr3 = iArrE;
            iArr4 = iArrD3;
        }
        int[] iArrD4 = g.a.d.d.d.d();
        h.k(iArr3, iArr, iArrD4);
        h.k(iArr4, iArr2, iArrD);
        if (g.a.d.d.d.k(iArrD4)) {
            return g.a.d.d.d.k(iArrD) ? J() : dVarI.v();
        }
        h.i(iArrD4, iArrD2);
        int[] iArrD5 = g.a.d.d.d.d();
        h.d(iArrD2, iArrD4, iArrD5);
        h.d(iArrD2, iArr3, iArrD2);
        h.f(iArrD5, iArrD5);
        g.a.d.d.d.l(iArr4, iArrD5, iArrE);
        h.h(g.a.d.d.d.b(iArrD2, iArrD2, iArrD5), iArrD5);
        i iVar7 = new i(iArrD3);
        h.i(iArrD, iVar7.f5348h);
        int[] iArr5 = iVar7.f5348h;
        h.k(iArr5, iArrD5, iArr5);
        i iVar8 = new i(iArrD5);
        h.k(iArrD2, iVar7.f5348h, iVar8.f5348h);
        h.e(iVar8.f5348h, iArrD, iArrE);
        h.g(iArrE, iVar8.f5348h);
        i iVar9 = new i(iArrD4);
        if (!zH) {
            int[] iArr6 = iVar9.f5348h;
            h.d(iArr6, iVar5.f5348h, iArr6);
        }
        if (!zH2) {
            int[] iArr7 = iVar9.f5348h;
            h.d(iArr7, iVar6.f5348h, iArr7);
        }
        return new j(dVarI, iVar7, iVar8, new g.a.d.b.e[]{iVar9}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h d() {
        return new j(null, f(), g());
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h z() {
        return u() ? this : new j(this.f5506b, this.f5507c, this.f5508d.m(), this.f5509e, this.f5510f);
    }
}
