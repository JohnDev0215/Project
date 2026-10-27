package X5;

import S8.InterfaceC0725w;
import V8.AbstractC0828q;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class U extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ D0 f10204m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public U(D0 d02, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f10204m = d02;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new U(this.f10204m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((U) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            D0 d02 = this.f10204m;
            V8.f0 f0Var = d02.f10102A0;
            O o = new O(d02, null);
            this.l = 1;
            if (AbstractC0828q.g(f0Var, o, this) == enumC2303a) {
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
