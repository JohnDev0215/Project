package A;

import S8.AbstractC0727y;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y0.C2722B;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class O extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f78m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ V f79n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public O(V v10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f79n = v10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        O o = new O(this.f79n, interfaceC2189c);
        o.f78m = obj;
        return o;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((O) create((C2722B) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C2722B c2722b = (C2722B) this.f78m;
            z0.c cVar = new z0.c();
            V v10 = this.f79n;
            K k10 = new K(v10, c2722b, new N(v10, 0, cVar), new C0004b(cVar, 1, v10), new M(v10, 0), new M(v10, 1), new L(cVar, 0, v10), null);
            this.l = 1;
            if (AbstractC0727y.h(k10, this) == enumC2303a) {
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
