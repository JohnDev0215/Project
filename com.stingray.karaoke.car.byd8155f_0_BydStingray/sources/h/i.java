package H;

import A.C0024l;
import A.C0030o;
import C0.r;
import E0.AbstractC0156f;
import E0.InterfaceC0173x;
import E0.d0;
import E0.s0;
import S8.AbstractC0727y;
import g0.n;
import m7.q;
import r7.EnumC2303a;
import s7.AbstractC2410c;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class i extends n implements a, InterfaceC0173x, s0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final C8.d f2761A = new C8.d(3);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public C0030o f2762y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f2763z;

    public static final m0.d H0(i iVar, d0 d0Var, InterfaceC2772a interfaceC2772a) {
        m0.d dVar;
        if (!iVar.f14695x || !iVar.f2763z) {
            return null;
        }
        d0 d0VarS = AbstractC0156f.s(iVar);
        if (!d0Var.Q0().f14695x) {
            d0Var = null;
        }
        if (d0Var == null || (dVar = (m0.d) interfaceC2772a.a()) == null) {
            return null;
        }
        m0.d dVarO = d0VarS.o(d0Var, false);
        return dVar.h(h6.e.a(dVarO.f18882a, dVarO.f18883b));
    }

    @Override // H.a
    public final Object k0(d0 d0Var, InterfaceC2772a interfaceC2772a, AbstractC2410c abstractC2410c) {
        Object objH = AbstractC0727y.h(new h(this, d0Var, interfaceC2772a, new C0024l(this, d0Var, interfaceC2772a), null), abstractC2410c);
        return objH == EnumC2303a.l ? objH : q.f19267a;
    }

    @Override // E0.s0
    public final Object r() {
        return f2761A;
    }

    @Override // E0.InterfaceC0173x
    public final void t0(r rVar) {
        this.f2763z = true;
    }

    @Override // g0.n
    public final boolean w0() {
        return false;
    }
}
