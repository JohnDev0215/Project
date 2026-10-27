package Q;

import A.r1;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y0.C2722B;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q.k0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0515k0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f5942m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0523o0 f5943n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0515k0(C0523o0 c0523o0, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f5943n = c0523o0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0515k0 c0515k0 = new C0515k0(this.f5943n, interfaceC2189c);
        c0515k0.f5942m = obj;
        return c0515k0;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0515k0) create((C2722B) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C2722B c2722b = (C2722B) this.f5942m;
            C0523o0 c0523o0 = this.f5943n;
            C0513j0 c0513j0 = new C0513j0(c0523o0, null);
            C0505f0 c0505f0 = new C0505f0(c0523o0, 2);
            this.l = 1;
            if (r1.d(c2722b, c0513j0, c0505f0, this, 3) == enumC2303a) {
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
