package Q5;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0640y extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ h6.l l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0640y(h6.l lVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = lVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0640y(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C0640y c0640y = (C0640y) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c0640y.invokeSuspend(qVar);
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
