package K6;

import T.X;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y.y0;
import y0.C2722B;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class i extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f3708m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ n f3709n;
    public final /* synthetic */ y0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ X8.c f3710p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ X f3711q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(n nVar, y0 y0Var, X8.c cVar, X x8, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3709n = nVar;
        this.o = y0Var;
        this.f3710p = cVar;
        this.f3711q = x8;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        i iVar = new i(this.f3709n, this.o, this.f3710p, this.f3711q, interfaceC2189c);
        iVar.f3708m = obj;
        return iVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((i) create((C2722B) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C2722B c2722b = (C2722B) this.f3708m;
            h hVar = new h(this.f3709n, this.o, this.f3710p, this.f3711q, null);
            this.l = 1;
            if (C7.a.w(c2722b, hVar, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return q.f19267a;
    }
}
