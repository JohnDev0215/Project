package X5;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class r extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0886u f10376m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(C0886u c0886u, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f10376m = c0886u;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        r rVar = new r(this.f10376m, interfaceC2189c);
        rVar.l = obj;
        return rVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        r rVar = (r) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        rVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.l;
        C0886u c0886u = this.f10376m;
        AbstractC0727y.w(interfaceC0725w, null, null, new C0877p(c0886u, null), 3);
        AbstractC0727y.w(interfaceC0725w, null, null, new C0879q(c0886u, null), 3);
        return m7.q.f19267a;
    }
}
