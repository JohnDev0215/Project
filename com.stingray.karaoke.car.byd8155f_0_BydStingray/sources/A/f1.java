package A;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y0.EnumC2731i;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class f1 extends s7.h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f172m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f173n;

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        f1 f1Var = new f1(interfaceC2189c);
        f1Var.f173n = obj;
        return f1Var;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((f1) create((y0.z) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f172m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            y0.z zVar = (y0.z) this.f173n;
            this.f172m = 1;
            obj = r1.e(zVar, EnumC2731i.f24067m, this);
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
