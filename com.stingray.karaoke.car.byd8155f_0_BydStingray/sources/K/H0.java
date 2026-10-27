package K;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import u.C2482v;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class H0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0333c0 f3166m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C.k f3167n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H0(C0333c0 c0333c0, C.k kVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3166m = c0333c0;
        this.f3167n = kVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new H0(this.f3166m, this.f3167n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((H0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        if (i7 != 0) {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
            return qVar;
        }
        AbstractC1386c.u(obj);
        this.l = 1;
        C0333c0 c0333c0 = this.f3166m;
        c0333c0.getClass();
        this.f3167n.f832a.b(new C.f(new C2482v(), 1, c0333c0), this);
        return enumC2303a;
    }
}
