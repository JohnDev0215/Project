package A;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class X0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ s7.i f132m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0023k0 f133n;
    public final /* synthetic */ y0.q o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public X0(InterfaceC2786o interfaceC2786o, C0023k0 c0023k0, y0.q qVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f132m = (s7.i) interfaceC2786o;
        this.f133n = c0023k0;
        this.o = qVar;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [s7.i, z7.o] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new X0(this.f132m, this.f133n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((X0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [s7.i, z7.o] */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            m0.c cVar = new m0.c(this.o.c);
            this.l = 1;
            if (this.f132m.g(this.f133n, cVar, this) == enumC2303a) {
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
