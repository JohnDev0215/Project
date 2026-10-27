package X5;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: X5.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0891x extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ n6.J l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ T.X f10463m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0891x(n6.J j3, T.X x8, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = j3;
        this.f10463m = x8;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0891x(this.l, this.f10463m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C0891x c0891x = (C0891x) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c0891x.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        float f10 = h6.n.f15590h;
        float f11 = h6.n.f15591i;
        int i7 = h6.n.f15592j;
        n6.J j3 = this.l;
        this.f10463m.setValue(new n6.J(j3.f20027a, j3.f20028b, j3.c, f10, f11, j3.f20030f, i7, j3.f20032h));
        return m7.q.f19267a;
    }
}
