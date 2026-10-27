package K5;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class o extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f3643m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(long j3, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3643m = j3;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        o oVar = new o(this.f3643m, interfaceC2189c);
        oVar.l = obj;
        return oVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        o oVar = (o) create((S1.b) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        oVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        S1.b bVar = (S1.b) this.l;
        t.c.getClass();
        bVar.d(t.f3649f, new Long(this.f3643m));
        return m7.q.f19267a;
    }
}
