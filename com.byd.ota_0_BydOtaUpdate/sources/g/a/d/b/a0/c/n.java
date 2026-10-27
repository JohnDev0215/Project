package g.a.d.b.a0.c;

import g.a.d.b.h;

/* JADX INFO: loaded from: classes.dex */
public class n extends h.c {
    public n(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        this(dVar, eVar, eVar2, false);
    }

    public n(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        super(dVar, eVar, eVar2);
        if ((eVar == null) != (eVar2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f5510f = z;
    }

    public n(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
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
        m mVar = (m) this.f5508d;
        if (mVar.i()) {
            return dVarI.v();
        }
        m mVar2 = (m) this.f5507c;
        m mVar3 = (m) this.f5509e[0];
        int[] iArrD = g.a.d.d.d.d();
        int[] iArrD2 = g.a.d.d.d.d();
        int[] iArrD3 = g.a.d.d.d.d();
        l.i(mVar.f5375h, iArrD3);
        int[] iArrD4 = g.a.d.d.d.d();
        l.i(iArrD3, iArrD4);
        boolean zH = mVar3.h();
        int[] iArr = mVar3.f5375h;
        if (!zH) {
            l.i(iArr, iArrD2);
            iArr = iArrD2;
        }
        l.k(mVar2.f5375h, iArr, iArrD);
        l.a(mVar2.f5375h, iArr, iArrD2);
        l.d(iArrD2, iArrD, iArrD2);
        l.h(g.a.d.d.d.b(iArrD2, iArrD2, iArrD2), iArrD2);
        l.d(iArrD3, mVar2.f5375h, iArrD3);
        l.h(g.a.d.d.m.G(5, iArrD3, 2, 0), iArrD3);
        l.h(g.a.d.d.m.H(5, iArrD4, 3, 0, iArrD), iArrD);
        m mVar4 = new m(iArrD4);
        l.i(iArrD2, mVar4.f5375h);
        int[] iArr2 = mVar4.f5375h;
        l.k(iArr2, iArrD3, iArr2);
        int[] iArr3 = mVar4.f5375h;
        l.k(iArr3, iArrD3, iArr3);
        m mVar5 = new m(iArrD3);
        l.k(iArrD3, mVar4.f5375h, mVar5.f5375h);
        int[] iArr4 = mVar5.f5375h;
        l.d(iArr4, iArrD2, iArr4);
        int[] iArr5 = mVar5.f5375h;
        l.k(iArr5, iArrD, iArr5);
        m mVar6 = new m(iArrD2);
        l.l(mVar.f5375h, mVar6.f5375h);
        if (!zH) {
            int[] iArr6 = mVar6.f5375h;
            l.d(iArr6, mVar3.f5375h, iArr6);
        }
        return new n(dVarI, mVar4, mVar5, new g.a.d.b.e[]{mVar6}, this.f5510f);
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
        m mVar = (m) this.f5507c;
        m mVar2 = (m) this.f5508d;
        m mVar3 = (m) hVar.q();
        m mVar4 = (m) hVar.r();
        m mVar5 = (m) this.f5509e[0];
        m mVar6 = (m) hVar.s(0);
        int[] iArrE = g.a.d.d.d.e();
        int[] iArrD = g.a.d.d.d.d();
        int[] iArrD2 = g.a.d.d.d.d();
        int[] iArrD3 = g.a.d.d.d.d();
        boolean zH = mVar5.h();
        if (zH) {
            iArr = mVar3.f5375h;
            iArr2 = mVar4.f5375h;
        } else {
            l.i(mVar5.f5375h, iArrD2);
            l.d(iArrD2, mVar3.f5375h, iArrD);
            l.d(iArrD2, mVar5.f5375h, iArrD2);
            l.d(iArrD2, mVar4.f5375h, iArrD2);
            iArr = iArrD;
            iArr2 = iArrD2;
        }
        boolean zH2 = mVar6.h();
        if (zH2) {
            iArr3 = mVar.f5375h;
            iArr4 = mVar2.f5375h;
        } else {
            l.i(mVar6.f5375h, iArrD3);
            l.d(iArrD3, mVar.f5375h, iArrE);
            l.d(iArrD3, mVar6.f5375h, iArrD3);
            l.d(iArrD3, mVar2.f5375h, iArrD3);
            iArr3 = iArrE;
            iArr4 = iArrD3;
        }
        int[] iArrD4 = g.a.d.d.d.d();
        l.k(iArr3, iArr, iArrD4);
        l.k(iArr4, iArr2, iArrD);
        if (g.a.d.d.d.k(iArrD4)) {
            return g.a.d.d.d.k(iArrD) ? J() : dVarI.v();
        }
        l.i(iArrD4, iArrD2);
        int[] iArrD5 = g.a.d.d.d.d();
        l.d(iArrD2, iArrD4, iArrD5);
        l.d(iArrD2, iArr3, iArrD2);
        l.f(iArrD5, iArrD5);
        g.a.d.d.d.l(iArr4, iArrD5, iArrE);
        l.h(g.a.d.d.d.b(iArrD2, iArrD2, iArrD5), iArrD5);
        m mVar7 = new m(iArrD3);
        l.i(iArrD, mVar7.f5375h);
        int[] iArr5 = mVar7.f5375h;
        l.k(iArr5, iArrD5, iArr5);
        m mVar8 = new m(iArrD5);
        l.k(iArrD2, mVar7.f5375h, mVar8.f5375h);
        l.e(mVar8.f5375h, iArrD, iArrE);
        l.g(iArrE, mVar8.f5375h);
        m mVar9 = new m(iArrD4);
        if (!zH) {
            int[] iArr6 = mVar9.f5375h;
            l.d(iArr6, mVar5.f5375h, iArr6);
        }
        if (!zH2) {
            int[] iArr7 = mVar9.f5375h;
            l.d(iArr7, mVar6.f5375h, iArr7);
        }
        return new n(dVarI, mVar7, mVar8, new g.a.d.b.e[]{mVar9}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h d() {
        return new n(null, f(), g());
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h z() {
        return u() ? this : new n(this.f5506b, this.f5507c, this.f5508d.m(), this.f5509e, this.f5510f);
    }
}
