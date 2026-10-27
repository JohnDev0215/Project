package M;

import F0.K0;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class u extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ v f4152m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ e f4153n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(v vVar, e eVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4152m = vVar;
        this.f4153n = eVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new u(this.f4152m, this.f4153n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        ((u) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
        return EnumC2303a.l;
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
            throw new A9.k();
        }
        AbstractC1386c.u(obj);
        this.l = 1;
        K0.a(this.f4152m, this.f4153n, this);
        return enumC2303a;
    }
}
