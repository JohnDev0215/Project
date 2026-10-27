package K;

import A.C0023k0;
import S8.AbstractC0727y;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class y0 extends s7.i implements InterfaceC2786o {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ C0023k0 f3460m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ long f3461n;
    public final /* synthetic */ X8.c o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ T.X f3462p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C.k f3463q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y0(X8.c cVar, T.X x8, C.k kVar, InterfaceC2189c interfaceC2189c) {
        super(3, interfaceC2189c);
        this.o = cVar;
        this.f3462p = x8;
        this.f3463q = kVar;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        long j3 = ((m0.c) obj2).f18880a;
        y0 y0Var = new y0(this.o, this.f3462p, this.f3463q, (InterfaceC2189c) obj3);
        y0Var.f3460m = (C0023k0) obj;
        y0Var.f3461n = j3;
        return y0Var.invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        X8.c cVar = this.o;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C0023k0 c0023k0 = this.f3460m;
            AbstractC0727y.w(cVar, null, null, new w0(this.f3462p, this.f3461n, this.f3463q, null), 3);
            this.l = 1;
            obj = c0023k0.b(this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        AbstractC0727y.w(cVar, null, null, new x0(this.f3462p, ((Boolean) obj).booleanValue(), this.f3463q, null), 3);
        return m7.q.f19267a;
    }
}
