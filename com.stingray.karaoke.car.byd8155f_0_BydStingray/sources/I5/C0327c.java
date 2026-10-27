package I5;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: I5.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0327c extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ h6.p l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0327c(h6.p pVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = pVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0327c(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C0327c c0327c = (C0327c) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c0327c.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        this.l.i(false);
        return m7.q.f19267a;
    }
}
