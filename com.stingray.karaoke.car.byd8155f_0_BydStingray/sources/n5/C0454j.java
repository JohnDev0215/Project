package N5;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: N5.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0454j extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ Z l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0454j(Z z6, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = z6;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0454j(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C0454j c0454j = (C0454j) create((f0) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c0454j.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        this.l.z();
        return m7.q.f19267a;
    }
}
