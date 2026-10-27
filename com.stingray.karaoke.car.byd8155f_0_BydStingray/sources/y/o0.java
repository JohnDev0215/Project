package y;

import A.Q0;
import A.S0;
import n0.AbstractC1858H;
import n0.C1856F;
import n0.InterfaceC1866P;
import q7.InterfaceC2189c;
import r7.EnumC2303a;

/* JADX INFO: loaded from: classes.dex */
public final class o0 implements a0, r0, InterfaceC1866P {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final o0 f23976m = new o0(0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final o0 f23977n = new o0(1);
    public static final o0 o = new o0(2);
    public final /* synthetic */ int l;

    public /* synthetic */ o0(int i7) {
        this.l = i7;
    }

    @Override // y.r0
    public long a(long j3, int i7, A.F f10) {
        f10.getClass();
        S0 s02 = (S0) f10.f20n;
        return new m0.c(S0.a(s02, s02.f105h, j3, s02.f104g)).f18880a;
    }

    @Override // y.r0
    public Object b(long j3, Q0 q02, InterfaceC2189c interfaceC2189c) {
        Q0 q03 = new Q0(q02.o, interfaceC2189c);
        q03.f94n = j3;
        m7.q qVar = m7.q.f19267a;
        Object objInvokeSuspend = q03.invokeSuspend(qVar);
        return objInvokeSuspend == EnumC2303a.l ? objInvokeSuspend : qVar;
    }

    @Override // y.r0
    public boolean c() {
        return false;
    }

    @Override // y.r0
    public g0.o d() {
        return g0.l.f14684a;
    }

    @Override // y.a0
    public void e(E0.H h10) {
        h10.a();
    }

    @Override // n0.InterfaceC1866P
    public AbstractC1858H v(long j3, a1.k kVar, a1.b bVar) {
        switch (this.l) {
            case 4:
                float fO = bVar.O(AbstractC2680A.f23824a);
                return new C1856F(new m0.d(0.0f, -fO, m0.f.d(j3), m0.f.b(j3) + fO));
            default:
                float fO2 = bVar.O(AbstractC2680A.f23824a);
                return new C1856F(new m0.d(-fO2, 0.0f, m0.f.d(j3) + fO2, m0.f.b(j3)));
        }
    }
}
