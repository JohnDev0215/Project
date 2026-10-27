package n6;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class P extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ C1972e l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ J f20049m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public P(C1972e c1972e, J j3, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = c1972e;
        this.f20049m = j3;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new P(this.l, this.f20049m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        P p10 = (P) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        p10.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        C1972e c1972e = this.l;
        if (c1972e != null) {
            J j3 = this.f20049m;
            c1972e.f20062g = j3.d;
            c1972e.f20063h = j3.f20029e;
            c1972e.f20065j = j3.f20032h;
            c1972e.f20066k = j3.f20031g;
        }
        return m7.q.f19267a;
    }
}
