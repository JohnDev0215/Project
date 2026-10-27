package N5;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class n0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ q0 f4724m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f4725n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n0(q0 q0Var, String str, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4724m = q0Var;
        this.f4725n = str;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new n0(this.f4724m, this.f4725n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((n0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        q0 q0Var = this.f4724m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            K5.t tVar = q0Var.f4737e;
            String str = this.f4725n;
            A7.m.c(str);
            this.l = 1;
            Object objX = H7.H.x(tVar.a(tVar.f3657a), new K5.s(str, null), this);
            if (objX != enumC2303a) {
                objX = qVar;
            }
            if (objX == enumC2303a) {
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
        K5.t tVar2 = q0Var.f4737e;
        int i10 = q0Var.f4743k;
        this.l = 2;
        Object objX2 = H7.H.x(tVar2.a(tVar2.f3657a), new K5.r(i10, null), this);
        if (objX2 != enumC2303a) {
            objX2 = qVar;
        }
        return objX2 == enumC2303a ? enumC2303a : qVar;
    }
}
