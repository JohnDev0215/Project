package A;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: A.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0026m extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f204m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ v1 f205n;
    public final /* synthetic */ C0030o o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0012f f206p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ S8.W f207q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0026m(v1 v1Var, C0030o c0030o, InterfaceC0012f interfaceC0012f, S8.W w10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f205n = v1Var;
        this.o = c0030o;
        this.f206p = interfaceC0012f;
        this.f207q = w10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0026m c0026m = new C0026m(this.f205n, this.o, this.f206p, this.f207q, interfaceC2189c);
        c0026m.f204m = obj;
        return c0026m;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0026m) create((P0) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            P0 p02 = (P0) this.f204m;
            InterfaceC0012f interfaceC0012f = this.f206p;
            C0030o c0030o = this.o;
            float fH0 = C0030o.H0(c0030o, interfaceC0012f);
            v1 v1Var = this.f205n;
            v1Var.f291e = fH0;
            C0022k c0022k = new C0022k(c0030o, this.f207q, p02, 0);
            C0024l c0024l = new C0024l(c0030o, v1Var, interfaceC0012f, 0);
            this.l = 1;
            if (v1Var.a(c0022k, c0024l, this) == enumC2303a) {
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
