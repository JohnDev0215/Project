package x;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: x.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2625b extends s7.i implements InterfaceC2782k {
    public final /* synthetic */ C2629d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f23484m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2625b(C2629d c2629d, Object obj, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.l = c2629d;
        this.f23484m = obj;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) throws Throwable {
        C2625b c2625b = (C2625b) create((InterfaceC2189c) obj);
        m7.q qVar = m7.q.f19267a;
        c2625b.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new C2625b(this.l, this.f23484m, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        C2629d c2629d = this.l;
        C2629d.b(c2629d);
        Object objA = C2629d.a(c2629d, this.f23484m);
        c2629d.c.f23569m.setValue(objA);
        c2629d.f23493e.setValue(objA);
        return m7.q.f19267a;
    }
}
