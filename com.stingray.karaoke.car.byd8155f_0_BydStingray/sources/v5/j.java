package V5;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y.j0;
import y.l0;
import y.n0;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class j extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ o f9520m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(o oVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f9520m = oVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new j(this.f9520m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((j) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        q qVar = q.f19267a;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            o oVar = this.f9520m;
            if (!((Boolean) oVar.d.getValue()).booleanValue()) {
                this.l = 1;
                m mVar = new m(oVar, null);
                j0 j0Var = j0.l;
                n0 n0Var = oVar.f9531b;
                n0Var.getClass();
                Object objH = AbstractC0727y.h(new l0(j0Var, n0Var, mVar, null), this);
                if (objH != enumC2303a) {
                    objH = qVar;
                }
                if (objH == enumC2303a) {
                    return enumC2303a;
                }
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
