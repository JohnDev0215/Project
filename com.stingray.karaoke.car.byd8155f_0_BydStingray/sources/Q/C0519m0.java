package Q;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q.m0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0519m0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0523o0 f5995m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ A.Y f5996n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0519m0(C0523o0 c0523o0, A.Y y10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f5995m = c0523o0;
        this.f5996n = y10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0519m0(this.f5995m, this.f5996n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0519m0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        C0523o0 c0523o0 = this.f5995m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            c0523o0.f6011v.setValue(Boolean.TRUE);
            this.l = 1;
            y.j0 j0Var = y.j0.f23942m;
            A.Y y10 = this.f5996n;
            y.n0 n0Var = c0523o0.f6002A;
            n0Var.getClass();
            if (AbstractC0727y.h(new y.m0(j0Var, n0Var, y10, c0523o0.f6015z, null), this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        c0523o0.f6011v.setValue(Boolean.FALSE);
        return m7.q.f19267a;
    }
}
