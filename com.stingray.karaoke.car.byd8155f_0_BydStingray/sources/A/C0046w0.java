package A;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import x.AbstractC2631e;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: A.w0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0046w0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f293m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ S0 f294n;
    public final /* synthetic */ long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ A7.w f295p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0046w0(S0 s02, long j3, A7.w wVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f294n = s02;
        this.o = j3;
        this.f295p = wVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0046w0 c0046w0 = new C0046w0(this.f294n, this.o, this.f295p, interfaceC2189c);
        c0046w0.f293m = obj;
        return c0046w0;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0046w0) create((P0) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            P0 p02 = (P0) this.f293m;
            S0 s02 = this.f294n;
            float f10 = s02.f(this.o);
            C0044v0 c0044v0 = new C0044v0(this.f295p, s02, p02, 0);
            this.l = 1;
            if (AbstractC2631e.e(0.0f, f10, null, c0044v0, this, 12) == enumC2303a) {
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
