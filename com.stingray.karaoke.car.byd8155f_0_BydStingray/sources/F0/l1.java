package F0;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class l1 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ V8.d0 f2219m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ F0 f2220n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l1(V8.d0 d0Var, F0 f02, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f2219m = d0Var;
        this.f2220n = f02;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new l1(this.f2219m, this.f2220n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        ((l1) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
        return EnumC2303a.l;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            k1 k1Var = new k1(this.f2220n, 0);
            this.l = 1;
            if (this.f2219m.b(k1Var, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        throw new A9.k();
    }
}
