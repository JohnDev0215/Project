package U5;

import S8.InterfaceC0725w;
import T.C0730a0;
import h8.AbstractC1386c;
import n6.C1966G;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class p extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ C1966G l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0730a0 f9175m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(C1966G c1966g, C0730a0 c0730a0, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = c1966g;
        this.f9175m = c0730a0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new p(this.l, this.f9175m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        p pVar = (p) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        pVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        float f10 = this.l.d;
        this.f9175m.j(f10 >= 0.8f ? 5.0f : f10 >= 0.6f ? 4.0f : f10 >= 0.4f ? 3.0f : f10 >= 0.2f ? 2.0f : f10 > 0.0f ? 1.0f : 0.0f);
        return m7.q.f19267a;
    }
}
