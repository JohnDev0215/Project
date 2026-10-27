package P5;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y0.C2722B;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class o extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f5378m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ p f5379n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(p pVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f5379n = pVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        o oVar = new o(this.f5379n, interfaceC2189c);
        oVar.f5378m = obj;
        return oVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((o) create((C2722B) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C2722B c2722b = (C2722B) this.f5378m;
            n nVar = new n(this.f5379n, null);
            this.l = 1;
            if (C7.a.H(c2722b, nVar, this) == enumC2303a) {
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
