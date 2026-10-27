package A;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y0.C2722B;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class b1 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f154m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2722B f155n;
    public final /* synthetic */ s7.i o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ A7.o f156p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C0023k0 f157q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public b1(C2722B c2722b, InterfaceC2786o interfaceC2786o, InterfaceC2782k interfaceC2782k, C0023k0 c0023k0, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f155n = c2722b;
        this.o = (s7.i) interfaceC2786o;
        this.f156p = (A7.o) interfaceC2782k;
        this.f157q = c0023k0;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [s7.i, z7.o] */
    /* JADX WARN: Type inference failed for: r3v0, types: [A7.o, z7.k] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        ?? r32 = this.f156p;
        C0023k0 c0023k0 = this.f157q;
        b1 b1Var = new b1(this.f155n, this.o, r32, c0023k0, interfaceC2189c);
        b1Var.f154m = obj;
        return b1Var;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((b1) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Type inference failed for: r5v0, types: [s7.i, z7.o] */
    /* JADX WARN: Type inference failed for: r6v0, types: [A7.o, z7.k] */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            a1 a1Var = new a1((InterfaceC0725w) this.f154m, this.o, this.f156p, this.f157q, null);
            this.l = 1;
            if (C7.a.w(this.f155n, a1Var, this) == enumC2303a) {
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
