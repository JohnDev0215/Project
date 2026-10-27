package E;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import x.AbstractC2631e;
import x.C2640i0;
import x.C2648n;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class C extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ D f1300m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C(D d, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f1300m = d;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C(this.f1300m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C2648n c2648n = this.f1300m.f1320w;
            Float f10 = new Float(0.0f);
            C2640i0 c2640i0P = AbstractC2631e.p(400.0f, 1, new Float(0.5f));
            this.l = 1;
            if (AbstractC2631e.g(c2648n, f10, c2640i0P, true, null, this, 8) == enumC2303a) {
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
