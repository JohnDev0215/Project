package T5;

import F0.k1;
import S8.InterfaceC0725w;
import T.X;
import V8.S;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class k extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C.k f8734m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ X f8735n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(C.k kVar, X x8, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f8734m = kVar;
        this.f8735n = x8;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new k(this.f8734m, this.f8735n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((k) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
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
            return q.f19267a;
        }
        AbstractC1386c.u(obj);
        S s6 = this.f8734m.f832a;
        k1 k1Var = new k1(this.f8735n, 5);
        this.l = 1;
        s6.b(k1Var, this);
        return enumC2303a;
    }
}
