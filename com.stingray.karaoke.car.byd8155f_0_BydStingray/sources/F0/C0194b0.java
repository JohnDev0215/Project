package F0;

import A.C0004b;
import S8.C0712i;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: F0.b0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0194b0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f2177m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0196c0 f2178n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0194b0(C0196c0 c0196c0, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f2178n = c0196c0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0194b0 c0194b0 = new C0194b0(this.f2178n, interfaceC2189c);
        c0194b0.f2177m = obj;
        return c0194b0;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        ((C0194b0) create((C0) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
        return EnumC2303a.l;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C0 c02 = (C0) this.f2177m;
            this.f2177m = c02;
            C0196c0 c0196c0 = this.f2178n;
            this.l = 1;
            C0712i c0712i = new C0712i(1, h6.e.k(this));
            c0712i.s();
            T0.z zVar = c0196c0.f2184m;
            T0.t tVar = zVar.f8483a;
            tVar.b();
            zVar.f8484b.set(new T0.E(zVar, tVar));
            c0712i.u(new C0004b(c02, 11, c0196c0));
            if (c0712i.q() == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        throw new A9.k();
    }
}
