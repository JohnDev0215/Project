package E;

import A.InterfaceC0033p0;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class A extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ D l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1297m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f1298n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public A(D d, int i7, int i10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = d;
        this.f1297m = i7;
        this.f1298n = i10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new A(this.l, this.f1297m, this.f1298n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        A a10 = (A) create((InterfaceC0033p0) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        a10.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        this.l.k(this.f1297m, this.f1298n);
        return m7.q.f19267a;
    }
}
