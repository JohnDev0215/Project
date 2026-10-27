package x;

import S8.AbstractC0727y;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class X extends s7.i implements InterfaceC2782k {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ x0 f23467m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2634f0 f23468n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2615D f23469p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public X(Object obj, InterfaceC2189c interfaceC2189c, InterfaceC2615D interfaceC2615D, C2634f0 c2634f0, x0 x0Var) {
        super(1, interfaceC2189c);
        this.f23467m = x0Var;
        this.f23468n = c2634f0;
        this.o = obj;
        this.f23469p = interfaceC2615D;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((X) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new X(this.o, interfaceC2189c, this.f23469p, this.f23468n, this.f23467m);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            Object obj2 = this.o;
            x0 x0Var = this.f23467m;
            W w10 = new W(obj2, null, this.f23469p, this.f23468n, x0Var);
            this.l = 1;
            if (AbstractC0727y.h(w10, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        this.f23467m.i();
        return m7.q.f19267a;
    }
}
