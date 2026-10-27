package N1;

import F0.k1;
import S8.C0718o;
import V8.AbstractC0828q;
import V8.InterfaceC0817f;
import V8.InterfaceC0818g;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class K extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Q f4475m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public K(Q q4, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4475m = q4;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new K(this.f4475m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((K) create((InterfaceC0818g) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        Q q4 = this.f4475m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            B8.t tVar = q4.f4493s;
            this.l = 1;
            Object objU = ((C0718o) tVar.o).u(this);
            if (objU != enumC2303a) {
                objU = qVar;
            }
            if (objU == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                if (i7 == 2) {
                    AbstractC1386c.u(obj);
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        InterfaceC0817f interfaceC0817fF = AbstractC0828q.f(q4.f().c, -1);
        k1 k1Var = new k1(q4, 2);
        this.l = 2;
        return interfaceC0817fF.b(k1Var, this) == enumC2303a ? enumC2303a : qVar;
    }
}
