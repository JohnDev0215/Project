package A;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class D0 extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f14m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public D0(long j3, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f14m = j3;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        D0 d02 = new D0(this.f14m, interfaceC2189c);
        d02.l = obj;
        return d02;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        D0 d02 = (D0) create((P0) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        d02.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        S0 s02 = ((P0) this.l).f88a;
        S0.a(s02, s02.f105h, this.f14m, 1);
        return m7.q.f19267a;
    }
}
