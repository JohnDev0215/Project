package Q;

import S8.InterfaceC0725w;
import e0.C1178r;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0504f extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C.k f5880m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1178r f5881n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0504f(C.k kVar, C1178r c1178r, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f5880m = kVar;
        this.f5881n = c1178r;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0504f(this.f5880m, this.f5881n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0504f) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 != 0) {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
            return m7.q.f19267a;
        }
        AbstractC1386c.u(obj);
        V8.S s6 = this.f5880m.f832a;
        C0502e c0502e = new C0502e(this.f5881n, 0);
        this.l = 1;
        s6.b(c0502e, this);
        return enumC2303a;
    }
}
