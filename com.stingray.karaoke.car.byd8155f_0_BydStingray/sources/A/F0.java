package A;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class F0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ J0 f21m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f22n;
    public final /* synthetic */ float o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public F0(J0 j02, float f10, float f11, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f21m = j02;
        this.f22n = f10;
        this.o = f11;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new F0(this.f21m, this.f22n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((F0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            S0 s02 = this.f21m.f53N;
            long jA = h6.e.a(this.f22n, this.o);
            this.l = 1;
            if (androidx.compose.foundation.gestures.a.a(s02, jA, this) == enumC2303a) {
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
