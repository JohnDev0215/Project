package K;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y0.C2722B;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class J extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C2722B f3170m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0355n0 f3171n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public J(C2722B c2722b, InterfaceC0355n0 interfaceC0355n0, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3170m = c2722b;
        this.f3171n = interfaceC0355n0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new J(this.f3170m, this.f3171n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((J) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            this.l = 1;
            Object objH = AbstractC0727y.h(new C0341g0(this.f3170m, this.f3171n, null), this);
            if (objH != enumC2303a) {
                objH = qVar;
            }
            if (objH == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return qVar;
    }
}
