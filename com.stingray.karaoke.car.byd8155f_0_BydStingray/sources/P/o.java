package P;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class o extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ p f5238m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(p pVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f5238m = pVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        o oVar = new o(this.f5238m, interfaceC2189c);
        oVar.l = obj;
        return oVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((o) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        return AbstractC0727y.w((InterfaceC0725w) this.l, null, null, new n(this.f5238m, null), 3);
    }
}
