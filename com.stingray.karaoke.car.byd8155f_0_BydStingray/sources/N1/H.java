package N1;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class H extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Q f4469m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H(Q q4, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4469m = q4;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new H(this.f4469m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((H) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        Q q4 = this.f4469m;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                if (q4.f4492r.a() instanceof T) {
                    return q4.f4492r.a();
                }
                this.l = 1;
                if (q4.g(this) == enumC2303a) {
                    return enumC2303a;
                }
            } else {
                if (i7 != 1) {
                    if (i7 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    AbstractC1386c.u(obj);
                    return (c0) obj;
                }
                AbstractC1386c.u(obj);
            }
            this.l = 2;
            obj = Q.b(q4, false, this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
            return (c0) obj;
        } catch (Throwable th) {
            return new V(th, -1);
        }
    }
}
