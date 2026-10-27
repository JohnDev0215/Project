package n6;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: n6.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1973f extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ C1972e l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ J f20079m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1973f(C1972e c1972e, J j3, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = c1972e;
        this.f20079m = j3;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C1973f(this.l, this.f20079m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C1973f c1973f = (C1973f) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c1973f.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        J j3 = this.f20079m;
        float f10 = j3.d;
        C1972e c1972e = this.l;
        c1972e.f20062g = f10;
        c1972e.f20063h = j3.f20029e;
        c1972e.f20065j = j3.f20032h;
        c1972e.f20066k = j3.f20031g;
        if (!c1972e.l) {
            Z8.e eVar = S8.E.f8084a;
            AbstractC0727y.w(AbstractC0727y.a(X8.m.f10573a), null, null, new C1971d(c1972e, null), 3);
            c1972e.l = true;
        }
        return m7.q.f19267a;
    }
}
