package K5;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class m extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f3642m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(boolean z6, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3642m = z6;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        m mVar = new m(this.f3642m, interfaceC2189c);
        mVar.l = obj;
        return mVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        m mVar = (m) create((S1.b) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        mVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        S1.b bVar = (S1.b) this.l;
        t.c.getClass();
        bVar.d(t.f3654k, Boolean.valueOf(this.f3642m));
        return m7.q.f19267a;
    }
}
