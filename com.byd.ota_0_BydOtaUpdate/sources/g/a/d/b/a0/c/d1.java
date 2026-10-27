package g.a.d.b.a0.c;

import g.a.d.b.h;

/* JADX INFO: loaded from: classes.dex */
public class d1 extends h.b {
    public d1(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        this(dVar, eVar, eVar2, false);
    }

    public d1(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        super(dVar, eVar, eVar2);
        if ((eVar == null) != (eVar2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f5510f = z;
    }

    public d1(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        super(dVar, eVar, eVar2, eVarArr);
        this.f5510f = z;
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h J() {
        if (u()) {
            return this;
        }
        g.a.d.b.d dVarI = i();
        g.a.d.b.e eVarJ = this.f5507c;
        if (eVarJ.i()) {
            return dVarI.v();
        }
        g.a.d.b.e eVar = this.f5508d;
        g.a.d.b.e eVar2 = this.f5509e[0];
        boolean zH = eVar2.h();
        g.a.d.b.e eVarJ2 = zH ? eVar : eVar.j(eVar2);
        g.a.d.b.e eVarO = zH ? eVar2 : eVar2.o();
        g.a.d.b.e eVarO2 = dVarI.o();
        if (!zH) {
            eVarO2 = eVarO2.j(eVarO);
        }
        g.a.d.b.e eVarA = eVar.o().a(eVarJ2).a(eVarO2);
        if (eVarA.i()) {
            return new d1(dVarI, eVarA, dVarI.p().n(), this.f5510f);
        }
        g.a.d.b.e eVarO3 = eVarA.o();
        g.a.d.b.e eVarJ3 = zH ? eVarA : eVarA.j(eVarO);
        if (!zH) {
            eVarJ = eVarJ.j(eVar2);
        }
        return new d1(dVarI, eVarO3, eVarJ.p(eVarA, eVarJ2).a(eVarO3).a(eVarJ3), new g.a.d.b.e[]{eVarJ3}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h K(g.a.d.b.h hVar) {
        if (u()) {
            return hVar;
        }
        if (hVar.u()) {
            return J();
        }
        g.a.d.b.d dVarI = i();
        g.a.d.b.e eVar = this.f5507c;
        if (eVar.i()) {
            return hVar;
        }
        g.a.d.b.e eVarN = hVar.n();
        g.a.d.b.e eVarS = hVar.s(0);
        if (eVarN.i() || !eVarS.h()) {
            return J().a(hVar);
        }
        g.a.d.b.e eVar2 = this.f5508d;
        g.a.d.b.e eVar3 = this.f5509e[0];
        g.a.d.b.e eVarO = hVar.o();
        g.a.d.b.e eVarO2 = eVar.o();
        g.a.d.b.e eVarO3 = eVar2.o();
        g.a.d.b.e eVarO4 = eVar3.o();
        g.a.d.b.e eVarA = dVarI.o().j(eVarO4).a(eVarO3).a(eVar2.j(eVar3));
        g.a.d.b.e eVarB = eVarO.b();
        g.a.d.b.e eVarL = dVarI.o().a(eVarB).j(eVarO4).a(eVarO3).l(eVarA, eVarO2, eVarO4);
        g.a.d.b.e eVarJ = eVarN.j(eVarO4);
        g.a.d.b.e eVarO5 = eVarJ.a(eVarA).o();
        if (eVarO5.i()) {
            return eVarL.i() ? hVar.J() : dVarI.v();
        }
        if (eVarL.i()) {
            return new d1(dVarI, eVarL, dVarI.p().n(), this.f5510f);
        }
        g.a.d.b.e eVarJ2 = eVarL.o().j(eVarJ);
        g.a.d.b.e eVarJ3 = eVarL.j(eVarO5).j(eVarO4);
        return new d1(dVarI, eVarJ2, eVarL.a(eVarO5).o().l(eVarA, eVarB, eVarJ3), new g.a.d.b.e[]{eVarJ3}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h a(g.a.d.b.h hVar) {
        g.a.d.b.e eVarJ;
        g.a.d.b.e eVarJ2;
        g.a.d.b.e eVarJ3;
        g.a.d.b.e eVarA;
        g.a.d.b.e eVar;
        g.a.d.b.e eVarN;
        if (u()) {
            return hVar;
        }
        if (hVar.u()) {
            return this;
        }
        g.a.d.b.d dVarI = i();
        g.a.d.b.e eVarJ4 = this.f5507c;
        g.a.d.b.e eVarN2 = hVar.n();
        if (eVarJ4.i()) {
            return eVarN2.i() ? dVarI.v() : hVar.a(this);
        }
        g.a.d.b.e eVar2 = this.f5508d;
        g.a.d.b.e eVar3 = this.f5509e[0];
        g.a.d.b.e eVarO = hVar.o();
        g.a.d.b.e eVarS = hVar.s(0);
        boolean zH = eVar3.h();
        if (zH) {
            eVarJ = eVarN2;
            eVarJ2 = eVarO;
        } else {
            eVarJ = eVarN2.j(eVar3);
            eVarJ2 = eVarO.j(eVar3);
        }
        boolean zH2 = eVarS.h();
        if (zH2) {
            eVarJ3 = eVar2;
        } else {
            eVarJ4 = eVarJ4.j(eVarS);
            eVarJ3 = eVar2.j(eVarS);
        }
        g.a.d.b.e eVarA2 = eVarJ3.a(eVarJ2);
        g.a.d.b.e eVarA3 = eVarJ4.a(eVarJ);
        if (eVarA3.i()) {
            return eVarA2.i() ? J() : dVarI.v();
        }
        if (eVarN2.i()) {
            g.a.d.b.h hVarA = A();
            g.a.d.b.e eVarQ = hVarA.q();
            g.a.d.b.e eVarR = hVarA.r();
            g.a.d.b.e eVarD = eVarR.a(eVarO).d(eVarQ);
            eVarA = eVarD.o().a(eVarD).a(eVarQ).a(dVarI.o());
            if (eVarA.i()) {
                return new d1(dVarI, eVarA, dVarI.p().n(), this.f5510f);
            }
            g.a.d.b.e eVarA4 = eVarD.j(eVarQ.a(eVarA)).a(eVarA).a(eVarR).d(eVarA).a(eVarA);
            eVarN = dVarI.n(g.a.d.b.c.f5471b);
            eVar = eVarA4;
        } else {
            g.a.d.b.e eVarO2 = eVarA3.o();
            g.a.d.b.e eVarJ5 = eVarA2.j(eVarJ4);
            g.a.d.b.e eVarJ6 = eVarA2.j(eVarJ);
            g.a.d.b.e eVarJ7 = eVarJ5.j(eVarJ6);
            if (eVarJ7.i()) {
                return new d1(dVarI, eVarJ7, dVarI.p().n(), this.f5510f);
            }
            g.a.d.b.e eVarJ8 = eVarA2.j(eVarO2);
            g.a.d.b.e eVarJ9 = !zH2 ? eVarJ8.j(eVarS) : eVarJ8;
            g.a.d.b.e eVarP = eVarJ6.a(eVarO2).p(eVarJ9, eVar2.a(eVar3));
            if (!zH) {
                eVarJ9 = eVarJ9.j(eVar3);
            }
            eVarA = eVarJ7;
            eVar = eVarP;
            eVarN = eVarJ9;
        }
        return new d1(dVarI, eVarA, eVar, new g.a.d.b.e[]{eVarN}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h d() {
        return new d1(null, f(), g());
    }

    @Override // g.a.d.b.h
    public boolean h() {
        g.a.d.b.e eVarN = n();
        return (eVarN.i() || o().s() == eVarN.s()) ? false : true;
    }

    @Override // g.a.d.b.h
    public g.a.d.b.e r() {
        g.a.d.b.e eVar = this.f5507c;
        g.a.d.b.e eVar2 = this.f5508d;
        if (u() || eVar.i()) {
            return eVar2;
        }
        g.a.d.b.e eVarJ = eVar2.a(eVar).j(eVar);
        g.a.d.b.e eVar3 = this.f5509e[0];
        return !eVar3.h() ? eVarJ.d(eVar3) : eVarJ;
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h z() {
        if (u()) {
            return this;
        }
        g.a.d.b.e eVar = this.f5507c;
        if (eVar.i()) {
            return this;
        }
        g.a.d.b.e eVar2 = this.f5508d;
        g.a.d.b.e eVar3 = this.f5509e[0];
        return new d1(this.f5506b, eVar, eVar2.a(eVar3), new g.a.d.b.e[]{eVar3}, this.f5510f);
    }
}
