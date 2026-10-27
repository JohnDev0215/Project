package A;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: A.o0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0031o0 extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ A7.w f231m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f232n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0031o0(A7.w wVar, float f10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f231m = wVar;
        this.f232n = f10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0031o0 c0031o0 = new C0031o0(this.f231m, this.f232n, interfaceC2189c);
        c0031o0.l = obj;
        return c0031o0;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C0031o0 c0031o0 = (C0031o0) create((InterfaceC0033p0) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c0031o0.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        this.f231m.l = ((InterfaceC0033p0) this.l).a(this.f232n);
        return m7.q.f19267a;
    }
}
