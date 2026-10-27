package A;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class C0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ J0 f7m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f8n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0(J0 j02, long j3, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f7m = j02;
        this.f8n = j3;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0(this.f7m, this.f8n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            S0 s02 = this.f7m.f53N;
            y.j0 j0Var = y.j0.f23942m;
            B0 b02 = new B0(this.f8n, null);
            this.l = 1;
            if (s02.e(j0Var, b02, this) == enumC2303a) {
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
