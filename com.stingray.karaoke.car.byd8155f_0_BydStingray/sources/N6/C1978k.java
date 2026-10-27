package n6;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import n0.C1878g;
import o6.C2048a;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: n6.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1978k extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ C1878g l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ float f20109m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f20110n;
    public final /* synthetic */ C2048a o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1978k(C1878g c1878g, float f10, float f11, C2048a c2048a, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = c1878g;
        this.f20109m = f10;
        this.f20110n = f11;
        this.o = c2048a;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C1978k(this.l, this.f20109m, this.f20110n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C1978k c1978k = (C1978k) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c1978k.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        this.l.h(h6.m.f(this.f20109m, this.f20110n, this.o.d));
        return m7.q.f19267a;
    }
}
