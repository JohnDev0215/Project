package X5;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class V extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ D0 f10205m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public V(D0 d02, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f10205m = d02;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new V(this.f10205m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        ((V) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
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
        D0 d02 = this.f10205m;
        V8.N n10 = d02.f10158x.f4643V;
        S s6 = new S(d02, 1);
        this.l = 1;
        ((V8.f0) n10.l).b(s6, this);
        return enumC2303a;
    }
}
