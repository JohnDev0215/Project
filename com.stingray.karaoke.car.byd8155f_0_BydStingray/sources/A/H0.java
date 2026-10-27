package A;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class H0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ long f41m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ J0 f42n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H0(J0 j02, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f42n = j02;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        H0 h02 = new H0(this.f42n, interfaceC2189c);
        h02.f41m = ((m0.c) obj).f18880a;
        return h02;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        long j3 = ((m0.c) obj).f18880a;
        H0 h02 = new H0(this.f42n, (InterfaceC2189c) obj2);
        h02.f41m = j3;
        return h02.invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            long j3 = this.f41m;
            S0 s02 = this.f42n.f53N;
            this.l = 1;
            obj = androidx.compose.foundation.gestures.a.a(s02, j3, this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return obj;
    }
}
