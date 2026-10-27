package A;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class e1 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2786o f166m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0023k0 f167n;
    public final /* synthetic */ y0.q o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e1(InterfaceC2786o interfaceC2786o, C0023k0 c0023k0, y0.q qVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f166m = interfaceC2786o;
        this.f167n = c0023k0;
        this.o = qVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new e1(this.f166m, this.f167n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((e1) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            m0.c cVar = new m0.c(this.o.c);
            this.l = 1;
            if (this.f166m.g(this.f167n, cVar, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return m7.q.f19267a;
    }
}
