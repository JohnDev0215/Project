package N5;

import S8.InterfaceC0725w;
import V8.AbstractC0828q;
import h8.AbstractC1386c;
import java.util.Set;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class Y extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Z f4621m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Set f4622n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Y(Z z6, Set set, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4621m = z6;
        this.f4622n = set;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new Y(this.f4621m, this.f4622n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((Y) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            V8.f0 f0Var = this.f4621m.f4634M;
            X x8 = new X(this.f4622n, null);
            this.l = 1;
            obj = AbstractC0828q.k(f0Var, x8, this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return obj;
    }
}
