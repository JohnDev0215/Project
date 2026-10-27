package g.a.d.b.a0.c;

import g.a.d.b.h;

/* JADX INFO: loaded from: classes.dex */
public class t2 extends h.b {
    public t2(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        this(dVar, eVar, eVar2, false);
    }

    public t2(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        super(dVar, eVar, eVar2);
        if ((eVar == null) != (eVar2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f5510f = z;
    }

    public t2(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        super(dVar, eVar, eVar2, eVarArr);
        this.f5510f = z;
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h J() {
        if (u()) {
            return this;
        }
        g.a.d.b.d dVarI = i();
        g.a.d.b.e eVar = this.f5507c;
        if (eVar.i()) {
            return dVarI.v();
        }
        g.a.d.b.e eVar2 = this.f5508d;
        g.a.d.b.e eVarO = this.f5509e[0];
        boolean zH = eVarO.h();
        g.a.d.b.e eVarO2 = zH ? eVarO : eVarO.o();
        g.a.d.b.e eVarA = zH ? eVar2.o().a(eVar2) : eVar2.a(eVarO).j(eVar2);
        if (eVarA.i()) {
            return new t2(dVarI, eVarA, dVarI.p(), this.f5510f);
        }
        g.a.d.b.e eVarO3 = eVarA.o();
        g.a.d.b.e eVarJ = zH ? eVarA : eVarA.j(eVarO2);
        g.a.d.b.e eVarO4 = eVar2.a(eVar).o();
        if (!zH) {
            eVarO = eVarO2.o();
        }
        return new t2(dVarI, eVarO3, eVarO4.a(eVarA).a(eVarO2).j(eVarO4).a(eVarO).a(eVarO3).a(eVarJ), new g.a.d.b.e[]{eVarJ}, this.f5510f);
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
        g.a.d.b.e eVarA = eVarO3.a(eVar2.j(eVar3));
        g.a.d.b.e eVarB = eVarO.b();
        g.a.d.b.e eVarL = eVarB.j(eVarO4).a(eVarO3).l(eVarA, eVarO2, eVarO4);
        g.a.d.b.e eVarJ = eVarN.j(eVarO4);
        g.a.d.b.e eVarO5 = eVarJ.a(eVarA).o();
        if (eVarO5.i()) {
            return eVarL.i() ? hVar.J() : dVarI.v();
        }
        if (eVarL.i()) {
            return new t2(dVarI, eVarL, dVarI.p(), this.f5510f);
        }
        g.a.d.b.e eVarJ2 = eVarL.o().j(eVarJ);
        g.a.d.b.e eVarJ3 = eVarL.j(eVarO5).j(eVarO4);
        return new t2(dVarI, eVarJ2, eVarL.a(eVarO5).o().l(eVarA, eVarB, eVarJ3), new g.a.d.b.e[]{eVarJ3}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h a(g.a.d.b.h hVar) {
        long[] jArr;
        long[] jArr2;
        long[] jArr3;
        r2 r2Var;
        r2 r2Var2;
        r2 r2Var3;
        if (u()) {
            return hVar;
        }
        if (hVar.u()) {
            return this;
        }
        g.a.d.b.d dVarI = i();
        r2 r2Var4 = (r2) this.f5507c;
        r2 r2Var5 = (r2) hVar.n();
        if (r2Var4.i()) {
            return r2Var5.i() ? dVarI.v() : hVar.a(this);
        }
        r2 r2Var6 = (r2) this.f5508d;
        r2 r2Var7 = (r2) this.f5509e[0];
        r2 r2Var8 = (r2) hVar.o();
        r2 r2Var9 = (r2) hVar.s(0);
        long[] jArrB = g.a.d.d.l.b();
        long[] jArrB2 = g.a.d.d.l.b();
        long[] jArrB3 = g.a.d.d.l.b();
        long[] jArrB4 = g.a.d.d.l.b();
        long[] jArrP = r2Var7.h() ? null : q2.p(r2Var7.f5413g);
        if (jArrP == null) {
            jArr = r2Var5.f5413g;
            jArr2 = r2Var8.f5413g;
        } else {
            q2.n(r2Var5.f5413g, jArrP, jArrB2);
            q2.n(r2Var8.f5413g, jArrP, jArrB4);
            jArr = jArrB2;
            jArr2 = jArrB4;
        }
        long[] jArrP2 = r2Var9.h() ? null : q2.p(r2Var9.f5413g);
        long[] jArr4 = r2Var4.f5413g;
        if (jArrP2 == null) {
            jArr3 = r2Var6.f5413g;
        } else {
            q2.n(jArr4, jArrP2, jArrB);
            q2.n(r2Var6.f5413g, jArrP2, jArrB3);
            jArr4 = jArrB;
            jArr3 = jArrB3;
        }
        q2.b(jArr3, jArr2, jArrB3);
        q2.b(jArr4, jArr, jArrB4);
        if (g.a.d.d.l.g(jArrB4)) {
            return g.a.d.d.l.g(jArrB3) ? J() : dVarI.v();
        }
        if (r2Var5.i()) {
            g.a.d.b.h hVarA = A();
            r2 r2Var10 = (r2) hVarA.q();
            g.a.d.b.e eVarR = hVarA.r();
            g.a.d.b.e eVarD = eVarR.a(r2Var8).d(r2Var10);
            r2Var = (r2) eVarD.o().a(eVarD).a(r2Var10);
            if (r2Var.i()) {
                return new t2(dVarI, r2Var, dVarI.p(), this.f5510f);
            }
            r2 r2Var11 = (r2) eVarD.j(r2Var10.a(r2Var)).a(r2Var).a(eVarR).d(r2Var).a(r2Var);
            r2Var3 = (r2) dVarI.n(g.a.d.b.c.f5471b);
            r2Var2 = r2Var11;
        } else {
            q2.t(jArrB4, jArrB4);
            long[] jArrP3 = q2.p(jArrB3);
            q2.n(jArr4, jArrP3, jArrB);
            q2.n(jArr, jArrP3, jArrB2);
            r2 r2Var12 = new r2(jArrB);
            q2.l(jArrB, jArrB2, r2Var12.f5413g);
            if (r2Var12.i()) {
                return new t2(dVarI, r2Var12, dVarI.p(), this.f5510f);
            }
            r2 r2Var13 = new r2(jArrB3);
            q2.n(jArrB4, jArrP3, r2Var13.f5413g);
            if (jArrP2 != null) {
                long[] jArr5 = r2Var13.f5413g;
                q2.n(jArr5, jArrP2, jArr5);
            }
            long[] jArrC = g.a.d.d.l.c();
            q2.b(jArrB2, jArrB4, jArrB4);
            q2.u(jArrB4, jArrC);
            q2.b(r2Var6.f5413g, r2Var7.f5413g, jArrB4);
            q2.m(jArrB4, r2Var13.f5413g, jArrC);
            r2 r2Var14 = new r2(jArrB4);
            q2.q(jArrC, r2Var14.f5413g);
            if (jArrP != null) {
                long[] jArr6 = r2Var13.f5413g;
                q2.n(jArr6, jArrP, jArr6);
            }
            r2Var = r2Var12;
            r2Var2 = r2Var14;
            r2Var3 = r2Var13;
        }
        return new t2(dVarI, r2Var, r2Var2, new g.a.d.b.e[]{r2Var3}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h d() {
        return new t2(null, f(), g());
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
        return new t2(this.f5506b, eVar, eVar2.a(eVar3), new g.a.d.b.e[]{eVar3}, this.f5510f);
    }
}
