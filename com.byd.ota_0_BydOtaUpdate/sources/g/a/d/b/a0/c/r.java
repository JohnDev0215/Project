package g.a.d.b.a0.c;

import g.a.d.b.h;

/* JADX INFO: loaded from: classes.dex */
public class r extends h.c {
    public r(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        this(dVar, eVar, eVar2, false);
    }

    public r(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        super(dVar, eVar, eVar2);
        if ((eVar == null) != (eVar2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f5510f = z;
    }

    public r(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
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
        q qVar = (q) this.f5508d;
        if (qVar.i()) {
            return dVarI.v();
        }
        q qVar2 = (q) this.f5507c;
        q qVar3 = (q) this.f5509e[0];
        int[] iArrG = g.a.d.d.e.g();
        p.i(qVar.f5403h, iArrG);
        int[] iArrG2 = g.a.d.d.e.g();
        p.i(iArrG, iArrG2);
        int[] iArrG3 = g.a.d.d.e.g();
        p.i(qVar2.f5403h, iArrG3);
        p.h(g.a.d.d.e.b(iArrG3, iArrG3, iArrG3), iArrG3);
        p.d(iArrG, qVar2.f5403h, iArrG);
        p.h(g.a.d.d.m.G(6, iArrG, 2, 0), iArrG);
        int[] iArrG4 = g.a.d.d.e.g();
        p.h(g.a.d.d.m.H(6, iArrG2, 3, 0, iArrG4), iArrG4);
        q qVar4 = new q(iArrG2);
        p.i(iArrG3, qVar4.f5403h);
        int[] iArr = qVar4.f5403h;
        p.k(iArr, iArrG, iArr);
        int[] iArr2 = qVar4.f5403h;
        p.k(iArr2, iArrG, iArr2);
        q qVar5 = new q(iArrG);
        p.k(iArrG, qVar4.f5403h, qVar5.f5403h);
        int[] iArr3 = qVar5.f5403h;
        p.d(iArr3, iArrG3, iArr3);
        int[] iArr4 = qVar5.f5403h;
        p.k(iArr4, iArrG4, iArr4);
        q qVar6 = new q(iArrG3);
        p.l(qVar.f5403h, qVar6.f5403h);
        if (!qVar3.h()) {
            int[] iArr5 = qVar6.f5403h;
            p.d(iArr5, qVar3.f5403h, iArr5);
        }
        return new r(dVarI, qVar4, qVar5, new g.a.d.b.e[]{qVar6}, this.f5510f);
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
        q qVar = (q) this.f5507c;
        q qVar2 = (q) this.f5508d;
        q qVar3 = (q) hVar.q();
        q qVar4 = (q) hVar.r();
        q qVar5 = (q) this.f5509e[0];
        q qVar6 = (q) hVar.s(0);
        int[] iArrI = g.a.d.d.e.i();
        int[] iArrG = g.a.d.d.e.g();
        int[] iArrG2 = g.a.d.d.e.g();
        int[] iArrG3 = g.a.d.d.e.g();
        boolean zH = qVar5.h();
        if (zH) {
            iArr = qVar3.f5403h;
            iArr2 = qVar4.f5403h;
        } else {
            p.i(qVar5.f5403h, iArrG2);
            p.d(iArrG2, qVar3.f5403h, iArrG);
            p.d(iArrG2, qVar5.f5403h, iArrG2);
            p.d(iArrG2, qVar4.f5403h, iArrG2);
            iArr = iArrG;
            iArr2 = iArrG2;
        }
        boolean zH2 = qVar6.h();
        if (zH2) {
            iArr3 = qVar.f5403h;
            iArr4 = qVar2.f5403h;
        } else {
            p.i(qVar6.f5403h, iArrG3);
            p.d(iArrG3, qVar.f5403h, iArrI);
            p.d(iArrG3, qVar6.f5403h, iArrG3);
            p.d(iArrG3, qVar2.f5403h, iArrG3);
            iArr3 = iArrI;
            iArr4 = iArrG3;
        }
        int[] iArrG4 = g.a.d.d.e.g();
        p.k(iArr3, iArr, iArrG4);
        p.k(iArr4, iArr2, iArrG);
        if (g.a.d.d.e.u(iArrG4)) {
            return g.a.d.d.e.u(iArrG) ? J() : dVarI.v();
        }
        p.i(iArrG4, iArrG2);
        int[] iArrG5 = g.a.d.d.e.g();
        p.d(iArrG2, iArrG4, iArrG5);
        p.d(iArrG2, iArr3, iArrG2);
        p.f(iArrG5, iArrG5);
        g.a.d.d.e.x(iArr4, iArrG5, iArrI);
        p.h(g.a.d.d.e.b(iArrG2, iArrG2, iArrG5), iArrG5);
        q qVar7 = new q(iArrG3);
        p.i(iArrG, qVar7.f5403h);
        int[] iArr5 = qVar7.f5403h;
        p.k(iArr5, iArrG5, iArr5);
        q qVar8 = new q(iArrG5);
        p.k(iArrG2, qVar7.f5403h, qVar8.f5403h);
        p.e(qVar8.f5403h, iArrG, iArrI);
        p.g(iArrI, qVar8.f5403h);
        q qVar9 = new q(iArrG4);
        if (!zH) {
            int[] iArr6 = qVar9.f5403h;
            p.d(iArr6, qVar5.f5403h, iArr6);
        }
        if (!zH2) {
            int[] iArr7 = qVar9.f5403h;
            p.d(iArr7, qVar6.f5403h, iArr7);
        }
        return new r(dVarI, qVar7, qVar8, new g.a.d.b.e[]{qVar9}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h d() {
        return new r(null, f(), g());
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h z() {
        return u() ? this : new r(this.f5506b, this.f5507c, this.f5508d.m(), this.f5509e, this.f5510f);
    }
}
