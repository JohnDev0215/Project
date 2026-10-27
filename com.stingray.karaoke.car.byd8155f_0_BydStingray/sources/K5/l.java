package K5;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class l extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f3641m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(String str, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3641m = str;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        l lVar = new l(this.f3641m, interfaceC2189c);
        lVar.l = obj;
        return lVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        l lVar = (l) create((S1.b) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        lVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        S1.b bVar = (S1.b) this.l;
        t.c.getClass();
        bVar.d(t.l, this.f3641m);
        return m7.q.f19267a;
    }
}
