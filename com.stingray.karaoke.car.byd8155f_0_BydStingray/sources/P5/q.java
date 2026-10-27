package P5;

import A7.E;
import F0.k1;
import S8.InterfaceC0725w;
import T.C0735d;
import U8.EnumC0810a;
import V8.AbstractC0836z;
import V8.C0816e;
import V8.C0819h;
import h8.AbstractC1386c;
import q7.C2195i;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class q extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ p f5391m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(p pVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f5391m = pVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new q(this.f5391m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((q) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            p pVar = this.f5391m;
            pVar.getClass();
            C0819h c0819hW = C0735d.W(new k(pVar, 2));
            v vVar = new v(0, pVar, null);
            int i10 = AbstractC0836z.f9667a;
            C0819h c0819h = new C0819h(new W8.n(vVar, c0819hW, C2195i.l, -2, EnumC0810a.l), 1);
            l lVar = new l(pVar, 1);
            E.e(2, lVar);
            C0816e c0816e = new C0816e(c0819h, lVar);
            k1 k1Var = new k1(pVar, 3);
            this.l = 1;
            if (c0816e.b(k1Var, this) == enumC2303a) {
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
