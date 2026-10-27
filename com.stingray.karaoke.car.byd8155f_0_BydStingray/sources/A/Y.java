package A;

import Q.C0521n0;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class Y extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f134m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ T f135n;
    public final /* synthetic */ C0005b0 o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Y(T t10, C0005b0 c0005b0, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f135n = t10;
        this.o = c0005b0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        Y y10 = new Y(this.f135n, this.o, interfaceC2189c);
        y10.f134m = obj;
        return y10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((Y) create((C0521n0) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C0004b c0004b = new C0004b((C0521n0) this.f134m, 2, this.o);
            this.l = 1;
            if (this.f135n.invoke(c0004b, this) == enumC2303a) {
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
