package K;

import S8.AbstractC0727y;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y0.C2722B;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class M extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f3182m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0355n0 f3183n;
    public final /* synthetic */ O.K o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public M(InterfaceC0355n0 interfaceC0355n0, O.K k10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3183n = interfaceC0355n0;
        this.o = k10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        M m10 = new M(this.f3183n, this.o, interfaceC2189c);
        m10.f3182m = obj;
        return m10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((M) create((C2722B) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            L l = new L((C2722B) this.f3182m, this.f3183n, this.o, null);
            this.l = 1;
            if (AbstractC0727y.h(l, this) == enumC2303a) {
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
