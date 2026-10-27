package R;

import A.Y;
import Q.C0521n0;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class v extends s7.i implements InterfaceC2786o {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ M.t f7324m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Y f7325n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(M.t tVar, Y y10, InterfaceC2189c interfaceC2189c) {
        super(3, interfaceC2189c);
        this.f7324m = tVar;
        this.f7325n = y10;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        Y y10 = this.f7325n;
        return new v(this.f7324m, y10, (InterfaceC2189c) obj3).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C0521n0 c0521n0 = (C0521n0) this.f7324m.l;
            this.l = 1;
            if (this.f7325n.invoke(c0521n0, this) == enumC2303a) {
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
