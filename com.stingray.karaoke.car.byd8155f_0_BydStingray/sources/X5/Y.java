package X5;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class Y extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ D0 f10217m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Y(D0 d02, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f10217m = d02;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new Y(this.f10217m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((Y) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
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
        V8.N n10 = this.f10217m.f10144n0;
        W w10 = new W();
        this.l = 1;
        n10.b(new A3.m(w10, 14), this);
        return enumC2303a;
    }
}
