package P;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class m extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ p f5236m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(p pVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f5236m = pVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        m mVar = new m(this.f5236m, interfaceC2189c);
        mVar.l = obj;
        return mVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((m) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.l;
        p pVar = this.f5236m;
        AbstractC0727y.w(interfaceC0725w, null, null, new j(pVar, null), 3);
        AbstractC0727y.w(interfaceC0725w, null, null, new k(pVar, null), 3);
        return AbstractC0727y.w(interfaceC0725w, null, null, new l(pVar, null), 3);
    }
}
