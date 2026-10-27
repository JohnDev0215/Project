package n6;

import S8.InterfaceC0725w;
import T.C0730a0;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: n6.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1974g extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ C1972e l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0730a0 f20080m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0730a0 f20081n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1974g(C1972e c1972e, C0730a0 c0730a0, C0730a0 c0730a02, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = c1972e;
        this.f20080m = c0730a0;
        this.f20081n = c0730a02;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C1974g(this.l, this.f20080m, this.f20081n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C1974g c1974g = (C1974g) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c1974g.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        C1972e c1972e = this.l;
        this.f20080m.j(((Number) c1972e.f20075v.getValue()).floatValue() - (((Number) c1972e.f20076w.getValue()).floatValue() * 0.3f));
        this.f20081n.j((((Number) c1972e.f20076w.getValue()).floatValue() * 0.7f) + ((Number) c1972e.f20075v.getValue()).floatValue());
        return m7.q.f19267a;
    }
}
