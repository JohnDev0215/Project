package R;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import x.AbstractC2631e;
import x.C2640i0;
import z7.InterfaceC2787p;

/* JADX INFO: renamed from: R.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0680h extends s7.i implements InterfaceC2787p {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ u f7295m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ E f7296n;
    public /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ w f7297p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ float f7298q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0680h(w wVar, float f10, InterfaceC2189c interfaceC2189c) {
        super(4, interfaceC2189c);
        this.f7297p = wVar;
        this.f7298q = f10;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            u uVar = this.f7295m;
            float fC = this.f7296n.c(this.o);
            if (!Float.isNaN(fC)) {
                A7.w wVar = new A7.w();
                w wVar2 = this.f7297p;
                float fI = Float.isNaN(wVar2.f7332i.i()) ? 0.0f : wVar2.f7332i.i();
                wVar.l = fI;
                C2640i0 c2640i0 = AbstractC0677e.f7290a;
                A.L l = new A.L(uVar, 12, wVar);
                this.f7295m = null;
                this.f7296n = null;
                this.l = 1;
                if (AbstractC2631e.c(fI, fC, this.f7298q, c2640i0, l, this) == enumC2303a) {
                    return enumC2303a;
                }
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return m7.q.f19267a;
    }

    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) {
        C0680h c0680h = new C0680h(this.f7297p, this.f7298q, (InterfaceC2189c) obj4);
        c0680h.f7295m = (u) obj;
        c0680h.f7296n = (E) obj2;
        c0680h.o = obj3;
        return c0680h.invokeSuspend(m7.q.f19267a);
    }
}
