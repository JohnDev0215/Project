package G;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import x.C2629d;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: G.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0310u extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0314y f2613m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0310u(C0314y c0314y, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f2613m = c0314y;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0310u(this.f2613m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0310u) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        C0314y c0314y = this.f2613m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C2629d c2629d = c0314y.f2628m;
            a1.h hVar = new a1.h(0L);
            this.l = 1;
            if (c2629d.e(hVar, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        int i10 = C0314y.f2618r;
        c0314y.e(0L);
        c0314y.d(false);
        return m7.q.f19267a;
    }
}
