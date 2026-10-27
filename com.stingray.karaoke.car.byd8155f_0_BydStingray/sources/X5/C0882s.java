package X5;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: X5.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0882s extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ C0886u l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0882s(C0886u c0886u, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = c0886u;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0882s(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0882s c0882s = (C0882s) create((m7.q) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c0882s.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        this.l.a();
        return m7.q.f19267a;
    }
}
