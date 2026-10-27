package w;

import R.C0684l;
import T.C0735d;
import T.C0748j0;
import T.X;
import V8.C0819h;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import x.x0;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: w.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2583n extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f23118m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ x0 f23119n;
    public final /* synthetic */ X o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2583n(x0 x0Var, X x8, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f23119n = x0Var;
        this.o = x8;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C2583n c2583n = new C2583n(this.f23119n, this.o, interfaceC2189c);
        c2583n.f23118m = obj;
        return c2583n;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C2583n) create((C0748j0) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C0748j0 c0748j0 = (C0748j0) this.f23118m;
            x0 x0Var = this.f23119n;
            C0819h c0819hW = C0735d.W(new C2582m(x0Var, 0));
            C0684l c0684l = new C0684l(c0748j0, x0Var, this.o, 5);
            this.l = 1;
            if (c0819hW.b(c0684l, this) == enumC2303a) {
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
