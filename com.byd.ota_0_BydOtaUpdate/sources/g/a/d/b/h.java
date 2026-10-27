package g.a.d.b;

import g.a.d.b.d;
import g.a.d.b.e;
import java.math.BigInteger;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static g.a.d.b.e[] f5505a = new g.a.d.b.e[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g.a.d.b.d f5506b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.d.b.e f5507c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.d.b.e f5508d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g.a.d.b.e[] f5509e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5510f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Hashtable f5511g;

    public class a implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f5512a;

        public a(boolean z) {
            this.f5512a = z;
        }

        @Override // g.a.d.b.o
        public p a(p pVar) {
            t tVar = pVar instanceof t ? (t) pVar : null;
            if (tVar == null) {
                tVar = new t();
            }
            if (tVar.b()) {
                return tVar;
            }
            if (!tVar.a()) {
                if (!h.this.C()) {
                    tVar.e();
                    return tVar;
                }
                tVar.d();
            }
            if (this.f5512a && !tVar.c()) {
                if (!h.this.D()) {
                    tVar.e();
                    return tVar;
                }
                tVar.f();
            }
            return tVar;
        }
    }

    public static abstract class b extends h {
        public b(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2) {
            super(dVar, eVar, eVar2);
        }

        public b(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr) {
            super(dVar, eVar, eVar2, eVarArr);
        }

        @Override // g.a.d.b.h
        public boolean C() {
            g.a.d.b.e eVarL;
            g.a.d.b.e eVarP;
            g.a.d.b.d dVarI = i();
            g.a.d.b.e eVar = this.f5507c;
            g.a.d.b.e eVarO = dVarI.o();
            g.a.d.b.e eVarP2 = dVarI.p();
            int iR = dVarI.r();
            if (iR != 6) {
                g.a.d.b.e eVar2 = this.f5508d;
                g.a.d.b.e eVarJ = eVar2.a(eVar).j(eVar2);
                if (iR != 0) {
                    if (iR != 1) {
                        throw new IllegalStateException("unsupported coordinate system");
                    }
                    g.a.d.b.e eVar3 = this.f5509e[0];
                    if (!eVar3.h()) {
                        g.a.d.b.e eVarJ2 = eVar3.j(eVar3.o());
                        eVarJ = eVarJ.j(eVar3);
                        eVarO = eVarO.j(eVar3);
                        eVarP2 = eVarP2.j(eVarJ2);
                    }
                }
                return eVarJ.equals(eVar.a(eVarO).j(eVar.o()).a(eVarP2));
            }
            g.a.d.b.e eVar4 = this.f5509e[0];
            boolean zH = eVar4.h();
            if (eVar.i()) {
                g.a.d.b.e eVarO2 = this.f5508d.o();
                if (!zH) {
                    eVarP2 = eVarP2.j(eVar4.o());
                }
                return eVarO2.equals(eVarP2);
            }
            g.a.d.b.e eVar5 = this.f5508d;
            g.a.d.b.e eVarO3 = eVar.o();
            if (zH) {
                eVarL = eVar5.o().a(eVar5).a(eVarO);
                eVarP = eVarO3.o().a(eVarP2);
            } else {
                g.a.d.b.e eVarO4 = eVar4.o();
                g.a.d.b.e eVarO5 = eVarO4.o();
                eVarL = eVar5.a(eVar4).l(eVar5, eVarO, eVarO4);
                eVarP = eVarO3.p(eVarP2, eVarO5);
            }
            return eVarL.j(eVarO3).equals(eVarP);
        }

        @Override // g.a.d.b.h
        public boolean D() {
            BigInteger bigIntegerQ = this.f5506b.q();
            if (g.a.d.b.c.f5472c.equals(bigIntegerQ)) {
                return ((e.a) A().f().a(this.f5506b.o())).u() == 0;
            }
            if (!g.a.d.b.c.f5474e.equals(bigIntegerQ)) {
                return super.D();
            }
            h hVarA = A();
            g.a.d.b.e eVarF = hVarA.f();
            g.a.d.b.d dVar = this.f5506b;
            g.a.d.b.e eVarJ = ((d.b) dVar).J(eVarF.a(dVar.o()));
            if (eVarJ == null) {
                return false;
            }
            g.a.d.b.e eVarA = eVarF.j(eVarJ).a(hVarA.g()).a(this.f5506b.o());
            return ((e.a) eVarA).u() == 0 || ((e.a) eVarA.a(eVarF)).u() == 0;
        }

        @Override // g.a.d.b.h
        public h E(g.a.d.b.e eVar) {
            if (u()) {
                return this;
            }
            int iJ = j();
            if (iJ == 5) {
                g.a.d.b.e eVarN = n();
                return i().j(eVarN, o().a(eVarN).d(eVar).a(eVarN.j(eVar)), p(), this.f5510f);
            }
            if (iJ != 6) {
                return super.E(eVar);
            }
            g.a.d.b.e eVarN2 = n();
            g.a.d.b.e eVarO = o();
            g.a.d.b.e eVar2 = p()[0];
            g.a.d.b.e eVarJ = eVarN2.j(eVar.o());
            return i().j(eVarJ, eVarO.a(eVarN2).a(eVarJ), new g.a.d.b.e[]{eVar2.j(eVar)}, this.f5510f);
        }

        @Override // g.a.d.b.h
        public h F(g.a.d.b.e eVar) {
            if (u()) {
                return this;
            }
            int iJ = j();
            if (iJ != 5 && iJ != 6) {
                return super.F(eVar);
            }
            g.a.d.b.e eVarN = n();
            return i().j(eVarN, o().a(eVarN).j(eVar).a(eVarN), p(), this.f5510f);
        }

        @Override // g.a.d.b.h
        public h G(h hVar) {
            return hVar.u() ? this : a(hVar.z());
        }

        public b L(int i2) {
            h hVarI;
            if (u()) {
                return this;
            }
            g.a.d.b.d dVarI = i();
            int iR = dVarI.r();
            g.a.d.b.e eVar = this.f5507c;
            if (iR == 0) {
                hVarI = dVarI.i(eVar.q(i2), this.f5508d.q(i2), this.f5510f);
            } else {
                if (iR != 1) {
                    if (iR != 5) {
                        if (iR != 6) {
                            throw new IllegalStateException("unsupported coordinate system");
                        }
                    }
                    hVarI = dVarI.i(eVar.q(i2), this.f5508d.q(i2), this.f5510f);
                }
                hVarI = dVarI.j(eVar.q(i2), this.f5508d.q(i2), new g.a.d.b.e[]{this.f5509e[0].q(i2)}, this.f5510f);
            }
            return (b) hVarI;
        }
    }

    public static abstract class c extends h {
        public c(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2) {
            super(dVar, eVar, eVar2);
        }

        public c(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr) {
            super(dVar, eVar, eVar2, eVarArr);
        }

        @Override // g.a.d.b.h
        public boolean C() {
            g.a.d.b.e eVar = this.f5507c;
            g.a.d.b.e eVar2 = this.f5508d;
            g.a.d.b.e eVarO = this.f5506b.o();
            g.a.d.b.e eVarP = this.f5506b.p();
            g.a.d.b.e eVarO2 = eVar2.o();
            int iJ = j();
            if (iJ != 0) {
                if (iJ == 1) {
                    g.a.d.b.e eVar3 = this.f5509e[0];
                    if (!eVar3.h()) {
                        g.a.d.b.e eVarO3 = eVar3.o();
                        g.a.d.b.e eVarJ = eVar3.j(eVarO3);
                        eVarO2 = eVarO2.j(eVar3);
                        eVarO = eVarO.j(eVarO3);
                        eVarP = eVarP.j(eVarJ);
                    }
                } else {
                    if (iJ != 2 && iJ != 3 && iJ != 4) {
                        throw new IllegalStateException("unsupported coordinate system");
                    }
                    g.a.d.b.e eVar4 = this.f5509e[0];
                    if (!eVar4.h()) {
                        g.a.d.b.e eVarO4 = eVar4.o();
                        g.a.d.b.e eVarO5 = eVarO4.o();
                        g.a.d.b.e eVarJ2 = eVarO4.j(eVarO5);
                        eVarO = eVarO.j(eVarO5);
                        eVarP = eVarP.j(eVarJ2);
                    }
                }
            }
            return eVarO2.equals(eVar.o().a(eVarO).j(eVar).a(eVarP));
        }

        @Override // g.a.d.b.h
        public h G(h hVar) {
            return hVar.u() ? this : a(hVar.z());
        }

        @Override // g.a.d.b.h
        public boolean h() {
            return g().s();
        }
    }

    public static class d extends b {
        public d(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
            super(dVar, eVar, eVar2);
            if ((eVar == null) != (eVar2 == null)) {
                throw new IllegalArgumentException("Exactly one of the field elements is null");
            }
            if (eVar != null) {
                e.c.v(this.f5507c, this.f5508d);
                if (dVar != null) {
                    e.c.v(this.f5507c, this.f5506b.o());
                }
            }
            this.f5510f = z;
        }

        public d(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
            super(dVar, eVar, eVar2, eVarArr);
            this.f5510f = z;
        }

        @Override // g.a.d.b.h
        public h J() {
            g.a.d.b.e eVarA;
            if (u()) {
                return this;
            }
            g.a.d.b.d dVarI = i();
            g.a.d.b.e eVarJ = this.f5507c;
            if (eVarJ.i()) {
                return dVarI.v();
            }
            int iR = dVarI.r();
            if (iR == 0) {
                g.a.d.b.e eVarA2 = this.f5508d.d(eVarJ).a(eVarJ);
                g.a.d.b.e eVarA3 = eVarA2.o().a(eVarA2).a(dVarI.o());
                return new d(dVarI, eVarA3, eVarJ.p(eVarA3, eVarA2.b()), this.f5510f);
            }
            if (iR == 1) {
                g.a.d.b.e eVarJ2 = this.f5508d;
                g.a.d.b.e eVar = this.f5509e[0];
                boolean zH = eVar.h();
                g.a.d.b.e eVarJ3 = zH ? eVarJ : eVarJ.j(eVar);
                if (!zH) {
                    eVarJ2 = eVarJ2.j(eVar);
                }
                g.a.d.b.e eVarO = eVarJ.o();
                g.a.d.b.e eVarA4 = eVarO.a(eVarJ2);
                g.a.d.b.e eVarO2 = eVarJ3.o();
                g.a.d.b.e eVarA5 = eVarA4.a(eVarJ3);
                g.a.d.b.e eVarL = eVarA5.l(eVarA4, eVarO2, dVarI.o());
                return new d(dVarI, eVarJ3.j(eVarL), eVarO.o().l(eVarJ3, eVarL, eVarA5), new g.a.d.b.e[]{eVarJ3.j(eVarO2)}, this.f5510f);
            }
            if (iR != 6) {
                throw new IllegalStateException("unsupported coordinate system");
            }
            g.a.d.b.e eVar2 = this.f5508d;
            g.a.d.b.e eVar3 = this.f5509e[0];
            boolean zH2 = eVar3.h();
            g.a.d.b.e eVarJ4 = zH2 ? eVar2 : eVar2.j(eVar3);
            g.a.d.b.e eVarO3 = zH2 ? eVar3 : eVar3.o();
            g.a.d.b.e eVarO4 = dVarI.o();
            g.a.d.b.e eVarJ5 = zH2 ? eVarO4 : eVarO4.j(eVarO3);
            g.a.d.b.e eVarA6 = eVar2.o().a(eVarJ4).a(eVarJ5);
            if (eVarA6.i()) {
                return new d(dVarI, eVarA6, dVarI.p().n(), this.f5510f);
            }
            g.a.d.b.e eVarO5 = eVarA6.o();
            g.a.d.b.e eVarJ6 = zH2 ? eVarA6 : eVarA6.j(eVarO3);
            g.a.d.b.e eVarP = dVarI.p();
            if (eVarP.c() < (dVarI.u() >> 1)) {
                g.a.d.b.e eVarO6 = eVar2.a(eVarJ).o();
                eVarA = eVarO6.a(eVarA6).a(eVarO3).j(eVarO6).a(eVarP.h() ? eVarJ5.a(eVarO3).o() : eVarJ5.p(eVarP, eVarO3.o())).a(eVarO5);
                if (!eVarO4.i()) {
                    if (!eVarO4.h()) {
                        eVarA = eVarA.a(eVarO4.b().j(eVarJ6));
                    }
                }
                return new d(dVarI, eVarO5, eVarA, new g.a.d.b.e[]{eVarJ6}, this.f5510f);
            }
            if (!zH2) {
                eVarJ = eVarJ.j(eVar3);
            }
            eVarA = eVarJ.p(eVarA6, eVarJ4).a(eVarO5);
            eVarA = eVarA.a(eVarJ6);
            return new d(dVarI, eVarO5, eVarA, new g.a.d.b.e[]{eVarJ6}, this.f5510f);
        }

        @Override // g.a.d.b.h
        public h K(h hVar) {
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
            if (dVarI.r() != 6) {
                return J().a(hVar);
            }
            g.a.d.b.e eVar2 = hVar.f5507c;
            g.a.d.b.e eVar3 = hVar.f5509e[0];
            if (eVar2.i() || !eVar3.h()) {
                return J().a(hVar);
            }
            g.a.d.b.e eVar4 = this.f5508d;
            g.a.d.b.e eVar5 = this.f5509e[0];
            g.a.d.b.e eVar6 = hVar.f5508d;
            g.a.d.b.e eVarO = eVar.o();
            g.a.d.b.e eVarO2 = eVar4.o();
            g.a.d.b.e eVarO3 = eVar5.o();
            g.a.d.b.e eVarA = dVarI.o().j(eVarO3).a(eVarO2).a(eVar4.j(eVar5));
            g.a.d.b.e eVarB = eVar6.b();
            g.a.d.b.e eVarL = dVarI.o().a(eVarB).j(eVarO3).a(eVarO2).l(eVarA, eVarO, eVarO3);
            g.a.d.b.e eVarJ = eVar2.j(eVarO3);
            g.a.d.b.e eVarO4 = eVarJ.a(eVarA).o();
            if (eVarO4.i()) {
                return eVarL.i() ? hVar.J() : dVarI.v();
            }
            if (eVarL.i()) {
                return new d(dVarI, eVarL, dVarI.p().n(), this.f5510f);
            }
            g.a.d.b.e eVarJ2 = eVarL.o().j(eVarJ);
            g.a.d.b.e eVarJ3 = eVarL.j(eVarO4).j(eVarO3);
            return new d(dVarI, eVarJ2, eVarL.a(eVarO4).o().l(eVarA, eVarB, eVarJ3), new g.a.d.b.e[]{eVarJ3}, this.f5510f);
        }

        @Override // g.a.d.b.h
        public h a(h hVar) {
            g.a.d.b.e eVarJ;
            g.a.d.b.e eVarJ2;
            g.a.d.b.e eVarJ3;
            g.a.d.b.e eVarA;
            g.a.d.b.e eVarN;
            g.a.d.b.e eVarA2;
            if (u()) {
                return hVar;
            }
            if (hVar.u()) {
                return this;
            }
            g.a.d.b.d dVarI = i();
            int iR = dVarI.r();
            g.a.d.b.e eVarJ4 = this.f5507c;
            g.a.d.b.e eVar = hVar.f5507c;
            if (iR == 0) {
                g.a.d.b.e eVar2 = this.f5508d;
                g.a.d.b.e eVar3 = hVar.f5508d;
                g.a.d.b.e eVarA3 = eVarJ4.a(eVar);
                g.a.d.b.e eVarA4 = eVar2.a(eVar3);
                if (eVarA3.i()) {
                    return eVarA4.i() ? J() : dVarI.v();
                }
                g.a.d.b.e eVarD = eVarA4.d(eVarA3);
                g.a.d.b.e eVarA5 = eVarD.o().a(eVarD).a(eVarA3).a(dVarI.o());
                return new d(dVarI, eVarA5, eVarD.j(eVarJ4.a(eVarA5)).a(eVarA5).a(eVar2), this.f5510f);
            }
            if (iR == 1) {
                g.a.d.b.e eVar4 = this.f5508d;
                g.a.d.b.e eVarJ5 = this.f5509e[0];
                g.a.d.b.e eVar5 = hVar.f5508d;
                g.a.d.b.e eVar6 = hVar.f5509e[0];
                boolean zH = eVar6.h();
                g.a.d.b.e eVarA6 = eVarJ5.j(eVar5).a(zH ? eVar4 : eVar4.j(eVar6));
                g.a.d.b.e eVarA7 = eVarJ5.j(eVar).a(zH ? eVarJ4 : eVarJ4.j(eVar6));
                if (eVarA7.i()) {
                    return eVarA6.i() ? J() : dVarI.v();
                }
                g.a.d.b.e eVarO = eVarA7.o();
                g.a.d.b.e eVarJ6 = eVarO.j(eVarA7);
                if (!zH) {
                    eVarJ5 = eVarJ5.j(eVar6);
                }
                g.a.d.b.e eVarA8 = eVarA6.a(eVarA7);
                g.a.d.b.e eVarA9 = eVarA8.l(eVarA6, eVarO, dVarI.o()).j(eVarJ5).a(eVarJ6);
                g.a.d.b.e eVarJ7 = eVarA7.j(eVarA9);
                if (!zH) {
                    eVarO = eVarO.j(eVar6);
                }
                return new d(dVarI, eVarJ7, eVarA6.l(eVarJ4, eVarA7, eVar4).l(eVarO, eVarA8, eVarA9), new g.a.d.b.e[]{eVarJ6.j(eVarJ5)}, this.f5510f);
            }
            if (iR != 6) {
                throw new IllegalStateException("unsupported coordinate system");
            }
            if (eVarJ4.i()) {
                return eVar.i() ? dVarI.v() : hVar.a(this);
            }
            g.a.d.b.e eVar7 = this.f5508d;
            g.a.d.b.e eVar8 = this.f5509e[0];
            g.a.d.b.e eVar9 = hVar.f5508d;
            g.a.d.b.e eVar10 = hVar.f5509e[0];
            boolean zH2 = eVar8.h();
            if (zH2) {
                eVarJ = eVar;
                eVarJ2 = eVar9;
            } else {
                eVarJ = eVar.j(eVar8);
                eVarJ2 = eVar9.j(eVar8);
            }
            boolean zH3 = eVar10.h();
            if (zH3) {
                eVarJ3 = eVar7;
            } else {
                eVarJ4 = eVarJ4.j(eVar10);
                eVarJ3 = eVar7.j(eVar10);
            }
            g.a.d.b.e eVarA10 = eVarJ3.a(eVarJ2);
            g.a.d.b.e eVarA11 = eVarJ4.a(eVarJ);
            if (eVarA11.i()) {
                return eVarA10.i() ? J() : dVarI.v();
            }
            if (eVar.i()) {
                h hVarA = A();
                g.a.d.b.e eVarQ = hVarA.q();
                g.a.d.b.e eVarR = hVarA.r();
                g.a.d.b.e eVarD2 = eVarR.a(eVar9).d(eVarQ);
                eVarA = eVarD2.o().a(eVarD2).a(eVarQ).a(dVarI.o());
                if (eVarA.i()) {
                    return new d(dVarI, eVarA, dVarI.p().n(), this.f5510f);
                }
                eVarA2 = eVarD2.j(eVarQ.a(eVarA)).a(eVarA).a(eVarR).d(eVarA).a(eVarA);
                eVarN = dVarI.n(g.a.d.b.c.f5471b);
            } else {
                g.a.d.b.e eVarO2 = eVarA11.o();
                g.a.d.b.e eVarJ8 = eVarA10.j(eVarJ4);
                g.a.d.b.e eVarJ9 = eVarA10.j(eVarJ);
                g.a.d.b.e eVarJ10 = eVarJ8.j(eVarJ9);
                if (eVarJ10.i()) {
                    return new d(dVarI, eVarJ10, dVarI.p().n(), this.f5510f);
                }
                g.a.d.b.e eVarJ11 = eVarA10.j(eVarO2);
                g.a.d.b.e eVarJ12 = !zH3 ? eVarJ11.j(eVar10) : eVarJ11;
                g.a.d.b.e eVarP = eVarJ9.a(eVarO2).p(eVarJ12, eVar7.a(eVar8));
                if (!zH2) {
                    eVarJ12 = eVarJ12.j(eVar8);
                }
                eVarA = eVarJ10;
                eVarN = eVarJ12;
                eVarA2 = eVarP;
            }
            return new d(dVarI, eVarA, eVarA2, new g.a.d.b.e[]{eVarN}, this.f5510f);
        }

        @Override // g.a.d.b.h
        public h d() {
            return new d(null, f(), g(), false);
        }

        @Override // g.a.d.b.h
        public boolean h() {
            g.a.d.b.e eVarN = n();
            if (eVarN.i()) {
                return false;
            }
            g.a.d.b.e eVarO = o();
            int iJ = j();
            return (iJ == 5 || iJ == 6) ? eVarO.s() != eVarN.s() : eVarO.d(eVarN).s();
        }

        @Override // g.a.d.b.h
        public g.a.d.b.e r() {
            int iJ = j();
            if (iJ != 5 && iJ != 6) {
                return this.f5508d;
            }
            g.a.d.b.e eVar = this.f5507c;
            g.a.d.b.e eVar2 = this.f5508d;
            if (u() || eVar.i()) {
                return eVar2;
            }
            g.a.d.b.e eVarJ = eVar2.a(eVar).j(eVar);
            if (6 != iJ) {
                return eVarJ;
            }
            g.a.d.b.e eVar3 = this.f5509e[0];
            return !eVar3.h() ? eVarJ.d(eVar3) : eVarJ;
        }

        @Override // g.a.d.b.h
        public h z() {
            if (u()) {
                return this;
            }
            g.a.d.b.e eVar = this.f5507c;
            if (eVar.i()) {
                return this;
            }
            int iJ = j();
            if (iJ == 0) {
                return new d(this.f5506b, eVar, this.f5508d.a(eVar), this.f5510f);
            }
            if (iJ == 1) {
                return new d(this.f5506b, eVar, this.f5508d.a(eVar), new g.a.d.b.e[]{this.f5509e[0]}, this.f5510f);
            }
            if (iJ == 5) {
                return new d(this.f5506b, eVar, this.f5508d.b(), this.f5510f);
            }
            if (iJ != 6) {
                throw new IllegalStateException("unsupported coordinate system");
            }
            g.a.d.b.e eVar2 = this.f5508d;
            g.a.d.b.e eVar3 = this.f5509e[0];
            return new d(this.f5506b, eVar, eVar2.a(eVar3), new g.a.d.b.e[]{eVar3}, this.f5510f);
        }
    }

    public static class e extends c {
        public e(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
            super(dVar, eVar, eVar2);
            if ((eVar == null) != (eVar2 == null)) {
                throw new IllegalArgumentException("Exactly one of the field elements is null");
            }
            this.f5510f = z;
        }

        public e(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
            super(dVar, eVar, eVar2, eVarArr);
            this.f5510f = z;
        }

        @Override // g.a.d.b.h
        public h H() {
            if (u()) {
                return this;
            }
            g.a.d.b.e eVar = this.f5508d;
            if (eVar.i()) {
                return this;
            }
            g.a.d.b.d dVarI = i();
            int iR = dVarI.r();
            if (iR != 0) {
                return iR != 4 ? J().a(this) : Q(false).a(this);
            }
            g.a.d.b.e eVar2 = this.f5507c;
            g.a.d.b.e eVarR = R(eVar);
            g.a.d.b.e eVarO = eVarR.o();
            g.a.d.b.e eVarA = P(eVar2.o()).a(i().o());
            g.a.d.b.e eVarR2 = P(eVar2).j(eVarO).r(eVarA.o());
            if (eVarR2.i()) {
                return i().v();
            }
            g.a.d.b.e eVarG = eVarR2.j(eVarR).g();
            g.a.d.b.e eVarJ = eVarR2.j(eVarG).j(eVarA);
            g.a.d.b.e eVarR3 = eVarO.o().j(eVarG).r(eVarJ);
            g.a.d.b.e eVarA2 = eVarR3.r(eVarJ).j(eVarJ.a(eVarR3)).a(eVar2);
            return new e(dVarI, eVarA2, eVar2.r(eVarA2).j(eVarR3).r(eVar), this.f5510f);
        }

        @Override // g.a.d.b.h
        public h I(int i2) {
            g.a.d.b.e eVarO;
            if (i2 < 0) {
                throw new IllegalArgumentException("'e' cannot be negative");
            }
            if (i2 == 0 || u()) {
                return this;
            }
            if (i2 == 1) {
                return J();
            }
            g.a.d.b.d dVarI = i();
            g.a.d.b.e eVarJ = this.f5508d;
            if (eVarJ.i()) {
                return dVarI.v();
            }
            int iR = dVarI.r();
            g.a.d.b.e eVarO2 = dVarI.o();
            g.a.d.b.e eVarJ2 = this.f5507c;
            g.a.d.b.e[] eVarArr = this.f5509e;
            g.a.d.b.e eVarN = eVarArr.length < 1 ? dVarI.n(g.a.d.b.c.f5471b) : eVarArr[0];
            if (!eVarN.h() && iR != 0) {
                if (iR == 1) {
                    eVarO = eVarN.o();
                    eVarJ2 = eVarJ2.j(eVarN);
                    eVarJ = eVarJ.j(eVarO);
                } else if (iR == 2) {
                    eVarO = null;
                } else {
                    if (iR != 4) {
                        throw new IllegalStateException("unsupported coordinate system");
                    }
                    eVarO2 = O();
                }
                eVarO2 = L(eVarN, eVarO);
            }
            int i3 = 0;
            g.a.d.b.e eVar = eVarO2;
            g.a.d.b.e eVarR = eVarJ;
            g.a.d.b.e eVar2 = eVarJ2;
            g.a.d.b.e eVarR2 = eVar;
            while (i3 < i2) {
                if (eVarR.i()) {
                    return dVarI.v();
                }
                g.a.d.b.e eVarP = P(eVar2.o());
                g.a.d.b.e eVarR3 = R(eVarR);
                g.a.d.b.e eVarJ3 = eVarR3.j(eVarR);
                g.a.d.b.e eVarR4 = R(eVar2.j(eVarJ3));
                g.a.d.b.e eVarR5 = R(eVarJ3.o());
                if (!eVarR2.i()) {
                    eVarP = eVarP.a(eVarR2);
                    eVarR2 = R(eVarR5.j(eVarR2));
                }
                g.a.d.b.e eVarR6 = eVarP.o().r(R(eVarR4));
                eVarR = eVarP.j(eVarR4.r(eVarR6)).r(eVarR5);
                eVarN = eVarN.h() ? eVarR3 : eVarR3.j(eVarN);
                i3++;
                eVar2 = eVarR6;
            }
            if (iR == 0) {
                g.a.d.b.e eVarG = eVarN.g();
                g.a.d.b.e eVarO3 = eVarG.o();
                return new e(dVarI, eVar2.j(eVarO3), eVarR.j(eVarO3.j(eVarG)), this.f5510f);
            }
            if (iR == 1) {
                return new e(dVarI, eVar2.j(eVarN), eVarR, new g.a.d.b.e[]{eVarN.j(eVarN.o())}, this.f5510f);
            }
            if (iR == 2) {
                return new e(dVarI, eVar2, eVarR, new g.a.d.b.e[]{eVarN}, this.f5510f);
            }
            if (iR == 4) {
                return new e(dVarI, eVar2, eVarR, new g.a.d.b.e[]{eVarN, eVarR2}, this.f5510f);
            }
            throw new IllegalStateException("unsupported coordinate system");
        }

        @Override // g.a.d.b.h
        public h J() {
            g.a.d.b.e eVarR;
            g.a.d.b.e eVarJ;
            if (u()) {
                return this;
            }
            g.a.d.b.d dVarI = i();
            g.a.d.b.e eVar = this.f5508d;
            if (eVar.i()) {
                return dVarI.v();
            }
            int iR = dVarI.r();
            g.a.d.b.e eVar2 = this.f5507c;
            if (iR == 0) {
                g.a.d.b.e eVarD = P(eVar2.o()).a(i().o()).d(R(eVar));
                g.a.d.b.e eVarR2 = eVarD.o().r(R(eVar2));
                return new e(dVarI, eVarR2, eVarD.j(eVar2.r(eVarR2)).r(eVar), this.f5510f);
            }
            if (iR == 1) {
                g.a.d.b.e eVar3 = this.f5509e[0];
                boolean zH = eVar3.h();
                g.a.d.b.e eVarO = dVarI.o();
                if (!eVarO.i() && !zH) {
                    eVarO = eVarO.j(eVar3.o());
                }
                g.a.d.b.e eVarA = eVarO.a(P(eVar2.o()));
                g.a.d.b.e eVarJ2 = zH ? eVar : eVar.j(eVar3);
                g.a.d.b.e eVarO2 = zH ? eVar.o() : eVarJ2.j(eVar);
                g.a.d.b.e eVarN = N(eVar2.j(eVarO2));
                g.a.d.b.e eVarR3 = eVarA.o().r(R(eVarN));
                g.a.d.b.e eVarR4 = R(eVarJ2);
                g.a.d.b.e eVarJ3 = eVarR3.j(eVarR4);
                g.a.d.b.e eVarR5 = R(eVarO2);
                return new e(dVarI, eVarJ3, eVarN.r(eVarR3).j(eVarA).r(R(eVarR5.o())), new g.a.d.b.e[]{R(zH ? R(eVarR5) : eVarR4.o()).j(eVarJ2)}, this.f5510f);
            }
            if (iR != 2) {
                if (iR == 4) {
                    return Q(true);
                }
                throw new IllegalStateException("unsupported coordinate system");
            }
            g.a.d.b.e eVar4 = this.f5509e[0];
            boolean zH2 = eVar4.h();
            g.a.d.b.e eVarO3 = eVar.o();
            g.a.d.b.e eVarO4 = eVarO3.o();
            g.a.d.b.e eVarO5 = dVarI.o();
            g.a.d.b.e eVarM = eVarO5.m();
            if (eVarM.t().equals(BigInteger.valueOf(3L))) {
                g.a.d.b.e eVarO6 = zH2 ? eVar4 : eVar4.o();
                eVarR = P(eVar2.a(eVarO6).j(eVar2.r(eVarO6)));
                eVarJ = eVarO3.j(eVar2);
            } else {
                g.a.d.b.e eVarP = P(eVar2.o());
                if (zH2) {
                    eVarR = eVarP.a(eVarO5);
                    eVarJ = eVar2.j(eVarO3);
                } else {
                    if (eVarO5.i()) {
                        eVarR = eVarP;
                    } else {
                        g.a.d.b.e eVarO7 = eVar4.o().o();
                        if (eVarM.c() < eVarO5.c()) {
                            eVarR = eVarP.r(eVarO7.j(eVarM));
                        } else {
                            eVarO5 = eVarO7.j(eVarO5);
                            eVarR = eVarP.a(eVarO5);
                        }
                    }
                    eVarJ = eVar2.j(eVarO3);
                }
            }
            g.a.d.b.e eVarN2 = N(eVarJ);
            g.a.d.b.e eVarR6 = eVarR.o().r(R(eVarN2));
            g.a.d.b.e eVarR7 = eVarN2.r(eVarR6).j(eVarR).r(M(eVarO4));
            g.a.d.b.e eVarR8 = R(eVar);
            if (!zH2) {
                eVarR8 = eVarR8.j(eVar4);
            }
            return new e(dVarI, eVarR6, eVarR7, new g.a.d.b.e[]{eVarR8}, this.f5510f);
        }

        @Override // g.a.d.b.h
        public h K(h hVar) {
            if (this == hVar) {
                return H();
            }
            if (u()) {
                return hVar;
            }
            if (hVar.u()) {
                return J();
            }
            g.a.d.b.e eVar = this.f5508d;
            if (eVar.i()) {
                return hVar;
            }
            g.a.d.b.d dVarI = i();
            int iR = dVarI.r();
            if (iR != 0) {
                return iR != 4 ? J().a(hVar) : Q(false).a(hVar);
            }
            g.a.d.b.e eVar2 = this.f5507c;
            g.a.d.b.e eVar3 = hVar.f5507c;
            g.a.d.b.e eVar4 = hVar.f5508d;
            g.a.d.b.e eVarR = eVar3.r(eVar2);
            g.a.d.b.e eVarR2 = eVar4.r(eVar);
            if (eVarR.i()) {
                return eVarR2.i() ? H() : this;
            }
            g.a.d.b.e eVarO = eVarR.o();
            g.a.d.b.e eVarR3 = eVarO.j(R(eVar2).a(eVar3)).r(eVarR2.o());
            if (eVarR3.i()) {
                return dVarI.v();
            }
            g.a.d.b.e eVarG = eVarR3.j(eVarR).g();
            g.a.d.b.e eVarJ = eVarR3.j(eVarG).j(eVarR2);
            g.a.d.b.e eVarR4 = R(eVar).j(eVarO).j(eVarR).j(eVarG).r(eVarJ);
            g.a.d.b.e eVarA = eVarR4.r(eVarJ).j(eVarJ.a(eVarR4)).a(eVar3);
            return new e(dVarI, eVarA, eVar2.r(eVarA).j(eVarR4).r(eVar), this.f5510f);
        }

        public g.a.d.b.e L(g.a.d.b.e eVar, g.a.d.b.e eVar2) {
            g.a.d.b.e eVarO = i().o();
            if (eVarO.i() || eVar.h()) {
                return eVarO;
            }
            if (eVar2 == null) {
                eVar2 = eVar.o();
            }
            g.a.d.b.e eVarO2 = eVar2.o();
            g.a.d.b.e eVarM = eVarO.m();
            return eVarM.c() < eVarO.c() ? eVarO2.j(eVarM).m() : eVarO2.j(eVarO);
        }

        public g.a.d.b.e M(g.a.d.b.e eVar) {
            return N(R(eVar));
        }

        public g.a.d.b.e N(g.a.d.b.e eVar) {
            return R(R(eVar));
        }

        public g.a.d.b.e O() {
            g.a.d.b.e[] eVarArr = this.f5509e;
            g.a.d.b.e eVar = eVarArr[1];
            if (eVar != null) {
                return eVar;
            }
            g.a.d.b.e eVarL = L(eVarArr[0], null);
            eVarArr[1] = eVarL;
            return eVarL;
        }

        public g.a.d.b.e P(g.a.d.b.e eVar) {
            return R(eVar).a(eVar);
        }

        public e Q(boolean z) {
            g.a.d.b.e eVar = this.f5507c;
            g.a.d.b.e eVar2 = this.f5508d;
            g.a.d.b.e eVar3 = this.f5509e[0];
            g.a.d.b.e eVarO = O();
            g.a.d.b.e eVarA = P(eVar.o()).a(eVarO);
            g.a.d.b.e eVarR = R(eVar2);
            g.a.d.b.e eVarJ = eVarR.j(eVar2);
            g.a.d.b.e eVarR2 = R(eVar.j(eVarJ));
            g.a.d.b.e eVarR3 = eVarA.o().r(R(eVarR2));
            g.a.d.b.e eVarR4 = R(eVarJ.o());
            g.a.d.b.e eVarR5 = eVarA.j(eVarR2.r(eVarR3)).r(eVarR4);
            g.a.d.b.e eVarR6 = z ? R(eVarR4.j(eVarO)) : null;
            if (!eVar3.h()) {
                eVarR = eVarR.j(eVar3);
            }
            return new e(i(), eVarR3, eVarR5, new g.a.d.b.e[]{eVarR, eVarR6}, this.f5510f);
        }

        public g.a.d.b.e R(g.a.d.b.e eVar) {
            return eVar.a(eVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:60:0x0127  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x0135  */
        @Override // g.a.d.b.h
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public g.a.d.b.h a(g.a.d.b.h r17) {
            /*
                Method dump skipped, instruction units count: 543
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: g.a.d.b.h.e.a(g.a.d.b.h):g.a.d.b.h");
        }

        @Override // g.a.d.b.h
        public h d() {
            return new e(null, f(), g(), false);
        }

        @Override // g.a.d.b.h
        public g.a.d.b.e s(int i2) {
            return (i2 == 1 && 4 == j()) ? O() : super.s(i2);
        }

        @Override // g.a.d.b.h
        public h z() {
            if (u()) {
                return this;
            }
            g.a.d.b.d dVarI = i();
            return dVarI.r() != 0 ? new e(dVarI, this.f5507c, this.f5508d.m(), this.f5509e, this.f5510f) : new e(dVarI, this.f5507c, this.f5508d.m(), this.f5510f);
        }
    }

    public h(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        this(dVar, eVar, eVar2, m(dVar));
    }

    public h(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr) {
        this.f5511g = null;
        this.f5506b = dVar;
        this.f5507c = eVar;
        this.f5508d = eVar2;
        this.f5509e = eVarArr;
    }

    public static g.a.d.b.e[] m(g.a.d.b.d dVar) {
        int iR = dVar == null ? 0 : dVar.r();
        if (iR == 0 || iR == 5) {
            return f5505a;
        }
        g.a.d.b.e eVarN = dVar.n(g.a.d.b.c.f5471b);
        if (iR != 1 && iR != 2) {
            if (iR == 3) {
                return new g.a.d.b.e[]{eVarN, eVarN, eVarN};
            }
            if (iR == 4) {
                return new g.a.d.b.e[]{eVarN, dVar.o()};
            }
            if (iR != 6) {
                throw new IllegalArgumentException("unknown coordinate system");
            }
        }
        return new g.a.d.b.e[]{eVarN};
    }

    public h A() {
        int iJ;
        if (u() || (iJ = j()) == 0 || iJ == 5) {
            return this;
        }
        g.a.d.b.e eVarS = s(0);
        return eVarS.h() ? this : B(eVarS.g());
    }

    public h B(g.a.d.b.e eVar) {
        int iJ = j();
        if (iJ != 1) {
            if (iJ == 2 || iJ == 3 || iJ == 4) {
                g.a.d.b.e eVarO = eVar.o();
                return c(eVarO, eVarO.j(eVar));
            }
            if (iJ != 6) {
                throw new IllegalStateException("not a projective coordinate system");
            }
        }
        return c(eVar, eVar);
    }

    public abstract boolean C();

    public boolean D() {
        BigInteger bigIntegerX;
        return g.a.d.b.c.f5471b.equals(this.f5506b.q()) || (bigIntegerX = this.f5506b.x()) == null || g.a.d.b.b.p(this, bigIntegerX).u();
    }

    public h E(g.a.d.b.e eVar) {
        return u() ? this : i().j(n().j(eVar), o(), p(), this.f5510f);
    }

    public h F(g.a.d.b.e eVar) {
        return u() ? this : i().j(n(), o().j(eVar), p(), this.f5510f);
    }

    public abstract h G(h hVar);

    public h H() {
        return K(this);
    }

    public h I(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("'e' cannot be negative");
        }
        h hVarJ = this;
        while (true) {
            i2--;
            if (i2 < 0) {
                return hVarJ;
            }
            hVarJ = hVarJ.J();
        }
    }

    public abstract h J();

    public h K(h hVar) {
        return J().a(hVar);
    }

    public abstract h a(h hVar);

    public void b() {
        if (!v()) {
            throw new IllegalStateException("point not in normal form");
        }
    }

    public h c(g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        return i().i(n().j(eVar), o().j(eVar2), this.f5510f);
    }

    public abstract h d();

    public boolean e(h hVar) {
        h hVarA;
        if (hVar == null) {
            return false;
        }
        g.a.d.b.d dVarI = i();
        g.a.d.b.d dVarI2 = hVar.i();
        boolean z = dVarI == null;
        boolean z2 = dVarI2 == null;
        boolean zU = u();
        boolean zU2 = hVar.u();
        if (zU || zU2) {
            if (zU && zU2) {
                return z || z2 || dVarI.m(dVarI2);
            }
            return false;
        }
        if (z && z2) {
            hVarA = this;
        } else if (z) {
            hVar = hVar.A();
            hVarA = this;
        } else if (z2) {
            hVarA = A();
        } else {
            if (!dVarI.m(dVarI2)) {
                return false;
            }
            h[] hVarArr = {this, dVarI.z(hVar)};
            dVarI.B(hVarArr);
            hVarA = hVarArr[0];
            hVar = hVarArr[1];
        }
        return hVarA.q().equals(hVar.q()) && hVarA.r().equals(hVar.r());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof h) {
            return e((h) obj);
        }
        return false;
    }

    public g.a.d.b.e f() {
        b();
        return q();
    }

    public g.a.d.b.e g() {
        b();
        return r();
    }

    public abstract boolean h();

    public int hashCode() {
        g.a.d.b.d dVarI = i();
        int i2 = dVarI == null ? 0 : ~dVarI.hashCode();
        if (u()) {
            return i2;
        }
        h hVarA = A();
        return (i2 ^ (hVarA.q().hashCode() * 17)) ^ (hVarA.r().hashCode() * 257);
    }

    public g.a.d.b.d i() {
        return this.f5506b;
    }

    public int j() {
        g.a.d.b.d dVar = this.f5506b;
        if (dVar == null) {
            return 0;
        }
        return dVar.r();
    }

    public final h k() {
        return A().d();
    }

    public byte[] l(boolean z) {
        if (u()) {
            return new byte[1];
        }
        h hVarA = A();
        byte[] bArrE = hVarA.q().e();
        if (z) {
            byte[] bArr = new byte[bArrE.length + 1];
            bArr[0] = (byte) (hVarA.h() ? 3 : 2);
            System.arraycopy(bArrE, 0, bArr, 1, bArrE.length);
            return bArr;
        }
        byte[] bArrE2 = hVarA.r().e();
        byte[] bArr2 = new byte[bArrE.length + bArrE2.length + 1];
        bArr2[0] = 4;
        System.arraycopy(bArrE, 0, bArr2, 1, bArrE.length);
        System.arraycopy(bArrE2, 0, bArr2, bArrE.length + 1, bArrE2.length);
        return bArr2;
    }

    public final g.a.d.b.e n() {
        return this.f5507c;
    }

    public final g.a.d.b.e o() {
        return this.f5508d;
    }

    public final g.a.d.b.e[] p() {
        return this.f5509e;
    }

    public g.a.d.b.e q() {
        return this.f5507c;
    }

    public g.a.d.b.e r() {
        return this.f5508d;
    }

    public g.a.d.b.e s(int i2) {
        if (i2 >= 0) {
            g.a.d.b.e[] eVarArr = this.f5509e;
            if (i2 < eVarArr.length) {
                return eVarArr[i2];
            }
        }
        return null;
    }

    public boolean t(boolean z) {
        if (u()) {
            return true;
        }
        return !((t) i().D(this, "bc_validity", new a(z))).b();
    }

    public String toString() {
        if (u()) {
            return "INF";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        stringBuffer.append(n());
        stringBuffer.append(',');
        stringBuffer.append(o());
        for (int i2 = 0; i2 < this.f5509e.length; i2++) {
            stringBuffer.append(',');
            stringBuffer.append(this.f5509e[i2]);
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public boolean u() {
        if (this.f5507c != null && this.f5508d != null) {
            g.a.d.b.e[] eVarArr = this.f5509e;
            if (eVarArr.length <= 0 || !eVarArr[0].i()) {
                return false;
            }
        }
        return true;
    }

    public boolean v() {
        int iJ = j();
        return iJ == 0 || iJ == 5 || u() || this.f5509e[0].h();
    }

    public boolean w() {
        return t(true);
    }

    public boolean x() {
        return t(false);
    }

    public h y(BigInteger bigInteger) {
        return i().w().a(this, bigInteger);
    }

    public abstract h z();
}
