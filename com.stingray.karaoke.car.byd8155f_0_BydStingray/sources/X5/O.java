package X5;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class O extends s7.i implements InterfaceC2785n {
    public /* synthetic */ boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ D0 f10189m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public O(D0 d02, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f10189m = d02;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        O o = new O(this.f10189m, interfaceC2189c);
        o.l = ((Boolean) obj).booleanValue();
        return o;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        O o = (O) create(bool, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        o.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        if (this.l) {
            this.f10189m.t();
        }
        return m7.q.f19267a;
    }
}
