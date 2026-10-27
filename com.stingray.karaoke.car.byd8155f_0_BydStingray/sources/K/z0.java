package K;

import A.C0023k0;
import A.b1;
import A.r1;
import S8.AbstractC0727y;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y0.C2722B;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class z0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f3473m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ X8.c f3474n;
    public final /* synthetic */ T.X o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C.k f3475p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ T.X f3476q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z0(X8.c cVar, T.X x8, C.k kVar, T.X x9, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3474n = cVar;
        this.o = x8;
        this.f3475p = kVar;
        this.f3476q = x9;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        z0 z0Var = new z0(this.f3474n, this.o, this.f3475p, this.f3476q, interfaceC2189c);
        z0Var.f3473m = obj;
        return z0Var;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((z0) create((C2722B) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C2722B c2722b = (C2722B) this.f3473m;
            y0 y0Var = new y0(this.f3474n, this.o, this.f3475p, null);
            A.L0 l02 = new A.L0(this.f3476q, 4);
            this.l = 1;
            A.W w10 = r1.f263a;
            Object objH = AbstractC0727y.h(new b1(c2722b, y0Var, l02, new C0023k0(c2722b), null), this);
            if (objH != enumC2303a) {
                objH = qVar;
            }
            if (objH == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return qVar;
    }
}
