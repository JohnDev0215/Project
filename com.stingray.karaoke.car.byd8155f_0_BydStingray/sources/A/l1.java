package A;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class l1 extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ C0023k0 l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l1(C0023k0 c0023k0, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = c0023k0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new l1(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        l1 l1Var = (l1) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        l1Var.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        C0023k0 c0023k0 = this.l;
        c0023k0.f195m = true;
        c0023k0.o.e(null);
        return m7.q.f19267a;
    }
}
