package N1;

import h8.AbstractC1386c;
import java.util.List;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class d0 extends s7.i implements InterfaceC2786o {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4517m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f4518n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d0(int i7, InterfaceC2189c interfaceC2189c, int i10) {
        super(i7, interfaceC2189c);
        this.l = i10;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        switch (this.l) {
            case 0:
                ((Boolean) obj2).getClass();
                d0 d0Var = new d0(3, (InterfaceC2189c) obj3, 0);
                d0Var.f4518n = (P1.c) obj;
                return d0Var.invokeSuspend(m7.q.f19267a);
            default:
                int iIntValue = ((Number) obj).intValue();
                d0 d0Var2 = new d0(3, (InterfaceC2189c) obj3, 1);
                d0Var2.f4517m = iIntValue;
                d0Var2.f4518n = (List) obj2;
                return d0Var2.invokeSuspend(m7.q.f19267a);
        }
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        switch (this.l) {
            case 0:
                EnumC2303a enumC2303a = EnumC2303a.l;
                int i7 = this.f4517m;
                if (i7 == 0) {
                    AbstractC1386c.u(obj);
                    P1.c cVar = (P1.c) this.f4518n;
                    this.f4517m = 1;
                    cVar.getClass();
                    obj = P1.c.a(cVar, this);
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
            default:
                EnumC2303a enumC2303a2 = EnumC2303a.l;
                AbstractC1386c.u(obj);
                return Boolean.valueOf(((List) this.f4518n).size() == this.f4517m + 1);
        }
    }
}
