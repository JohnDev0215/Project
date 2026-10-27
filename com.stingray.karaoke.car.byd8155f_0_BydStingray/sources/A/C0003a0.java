package A;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: A.a0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0003a0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f139m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0005b0 f140n;
    public final /* synthetic */ long o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0003a0(C0005b0 c0005b0, long j3, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f140n = c0005b0;
        this.o = j3;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0003a0 c0003a0 = new C0003a0(this.f140n, this.o, interfaceC2189c);
        c0003a0.f139m = obj;
        return c0003a0;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0003a0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.f139m;
            C0005b0 c0005b0 = this.f140n;
            InterfaceC2786o interfaceC2786o = c0005b0.f152M;
            boolean z6 = c0005b0.f153N;
            long jF = a1.o.f(z6 ? -1.0f : 1.0f, this.o);
            EnumC0017h0 enumC0017h0 = c0005b0.f149J;
            W w10 = X.f130a;
            Float f10 = new Float(enumC0017h0 == EnumC0017h0.l ? a1.o.c(jF) : a1.o.b(jF));
            this.l = 1;
            if (interfaceC2786o.g(interfaceC0725w, f10, this) == enumC2303a) {
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
