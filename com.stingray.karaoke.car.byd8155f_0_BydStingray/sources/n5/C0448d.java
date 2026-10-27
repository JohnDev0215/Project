package N5;

import S8.InterfaceC0725w;
import V8.AbstractC0828q;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: N5.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0448d extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0449e f4682m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0448d(C0449e c0449e, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4682m = c0449e;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0448d(this.f4682m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0448d) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C0449e c0449e = this.f4682m;
            V8.J j3 = new V8.J(c0449e.f4686a.f10211h, c0449e.f4687b.f4661h0, new H5.d(c0449e, null));
            C0447c c0447c = new C0447c(2, null);
            this.l = 1;
            if (AbstractC0828q.g(j3, c0447c, this) == enumC2303a) {
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
