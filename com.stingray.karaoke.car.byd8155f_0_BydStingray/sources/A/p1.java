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
public final class p1 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f248m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2722B f249n;
    public final /* synthetic */ InterfaceC2786o o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f250p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f251q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f252r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p1(C2722B c2722b, InterfaceC2786o interfaceC2786o, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, InterfaceC2782k interfaceC2782k3, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f249n = c2722b;
        this.o = interfaceC2786o;
        this.f250p = interfaceC2782k;
        this.f251q = interfaceC2782k2;
        this.f252r = interfaceC2782k3;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        p1 p1Var = new p1(this.f249n, this.o, this.f250p, this.f251q, this.f252r, interfaceC2189c);
        p1Var.f248m = obj;
        return p1Var;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((p1) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.f248m;
            C2722B c2722b = this.f249n;
            C0023k0 c0023k0 = new C0023k0(c2722b);
            o1 o1Var = new o1(interfaceC0725w, this.o, this.f250p, this.f251q, this.f252r, c0023k0, null);
            this.l = 1;
            if (C7.a.w(c2722b, o1Var, this) == enumC2303a) {
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
