package n6;

import F0.k1;
import S8.InterfaceC0725w;
import V8.f0;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: n6.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1971d extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1972e f20057m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1971d(C1972e c1972e, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f20057m = c1972e;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C1971d(this.f20057m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        ((C1971d) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
        return EnumC2303a.l;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 != 0) {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
            throw new A9.k();
        }
        AbstractC1386c.u(obj);
        C1972e c1972e = this.f20057m;
        V8.N n10 = c1972e.f20058a.f20152b;
        k1 k1Var = new k1(c1972e, 11);
        this.l = 1;
        ((f0) n10.l).b(k1Var, this);
        return enumC2303a;
    }
}
