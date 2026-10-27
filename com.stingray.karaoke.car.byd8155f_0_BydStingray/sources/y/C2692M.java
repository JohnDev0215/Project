package y;

import E0.AbstractC0156f;
import E0.AbstractC0164n;
import E0.InterfaceC0166p;
import S8.AbstractC0727y;
import d8.C1134u;
import e0.C1183w;
import l0.C1727r;
import l0.EnumC1726q;
import l0.InterfaceC1712c;
import l0.InterfaceC1724o;

/* JADX INFO: renamed from: y.M, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2692M extends AbstractC0164n implements InterfaceC1712c, E0.p0, InterfaceC0166p, InterfaceC1724o {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public EnumC1726q f23852A;
    public final C2690K B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final C2693N f23853C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final C2694O f23854D;

    public C2692M(C.k kVar) {
        C2690K c2690k = new C2690K();
        c2690k.f23849y = kVar;
        H0(c2690k);
        this.B = c2690k;
        C2693N c2693n = new C2693N();
        H0(c2693n);
        this.f23853C = c2693n;
        C2694O c2694o = new C2694O();
        H0(c2694o);
        this.f23854D = c2694o;
        H0(new C1727r());
    }

    @Override // l0.InterfaceC1712c
    public final void D(EnumC1726q enumC1726q) {
        C2695P c2695pH0;
        if (A7.m.a(this.f23852A, enumC1726q)) {
            return;
        }
        boolean zA = enumC1726q.a();
        G.Q q4 = null;
        if (zA) {
            AbstractC0727y.w(v0(), null, null, new C2691L(this, null), 3);
        }
        if (this.f14695x) {
            AbstractC0156f.o(this);
        }
        C2690K c2690k = this.B;
        C.k kVar = c2690k.f23849y;
        if (kVar != null) {
            if (zA) {
                C.d dVar = c2690k.f23850z;
                if (dVar != null) {
                    c2690k.H0(kVar, new C.e(dVar));
                    c2690k.f23850z = null;
                }
                C.d dVar2 = new C.d();
                c2690k.H0(kVar, dVar2);
                c2690k.f23850z = dVar2;
            } else {
                C.d dVar3 = c2690k.f23850z;
                if (dVar3 != null) {
                    c2690k.H0(kVar, new C.e(dVar3));
                    c2690k.f23850z = null;
                }
            }
        }
        C2694O c2694o = this.f23854D;
        if (zA != c2694o.f23858y) {
            if (zA) {
                E0.d0 d0Var = c2694o.f23859z;
                if (d0Var != null && d0Var.Q0().f14695x && (c2695pH0 = c2694o.H0()) != null) {
                    c2695pH0.H0(c2694o.f23859z);
                }
            } else {
                C2695P c2695pH02 = c2694o.H0();
                if (c2695pH02 != null) {
                    c2695pH02.H0(null);
                }
            }
            c2694o.f23858y = zA;
        }
        C2693N c2693n = this.f23853C;
        if (zA) {
            c2693n.getClass();
            A7.z zVar = new A7.z();
            AbstractC0156f.q(c2693n, new C1134u(zVar, 5, c2693n));
            G.Q q10 = (G.Q) zVar.l;
            if (q10 != null) {
                q10.a();
                q4 = q10;
            }
            c2693n.f23855y = q4;
        } else {
            G.Q q11 = c2693n.f23855y;
            if (q11 != null) {
                q11.b();
            }
            c2693n.f23855y = null;
        }
        c2693n.f23856z = zA;
        this.f23852A = enumC1726q;
    }

    @Override // E0.p0
    public final void H(L0.j jVar) {
        EnumC1726q enumC1726q = this.f23852A;
        boolean z6 = false;
        if (enumC1726q != null && enumC1726q.a()) {
            z6 = true;
        }
        H7.w[] wVarArr = L0.t.f3964a;
        L0.u uVar = L0.r.f3949k;
        H7.w wVar = L0.t.f3964a[4];
        uVar.a(jVar, Boolean.valueOf(z6));
        jVar.l(L0.i.f3903u, new L0.a(null, new C1183w(this, 18)));
    }

    public final void K0(C.k kVar) {
        C.d dVar;
        C2690K c2690k = this.B;
        if (A7.m.a(c2690k.f23849y, kVar)) {
            return;
        }
        C.k kVar2 = c2690k.f23849y;
        if (kVar2 != null && (dVar = c2690k.f23850z) != null) {
            kVar2.b(new C.e(dVar));
        }
        c2690k.f23850z = null;
        c2690k.f23849y = kVar;
    }

    @Override // E0.InterfaceC0166p
    public final void l0(E0.d0 d0Var) {
        this.f23854D.l0(d0Var);
    }

    @Override // g0.n
    public final boolean w0() {
        return false;
    }
}
