package P;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import x.C0;
import x.C2629d;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class B extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C f5200m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0 f5201n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public B(C c, C0 c02, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f5200m = c;
        this.f5201n = c02;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new B(this.f5200m, this.f5201n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((B) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C2629d c2629d = (C2629d) this.f5200m.c;
            Float f10 = new Float(0.0f);
            this.l = 1;
            if (C2629d.c(c2629d, f10, this.f5201n, null, this, 12) == enumC2303a) {
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
