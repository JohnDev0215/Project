package Q5;

import S8.InterfaceC0725w;
import T.C0732b0;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class M2 extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0732b0 f6442m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public M2(int i7, C0732b0 c0732b0, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = i7;
        this.f6442m = c0732b0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new M2(this.l, this.f6442m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        M2 m22 = (M2) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        m22.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        this.f6442m.j(this.l);
        return m7.q.f19267a;
    }
}
