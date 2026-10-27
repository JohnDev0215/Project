package N1;

import S8.k0;
import V8.InterfaceC0818g;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: N1.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0434o extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ k0 l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0434o(k0 k0Var, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = k0Var;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0434o(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C0434o c0434o = (C0434o) create((InterfaceC0818g) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c0434o.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        this.l.start();
        return m7.q.f19267a;
    }
}
