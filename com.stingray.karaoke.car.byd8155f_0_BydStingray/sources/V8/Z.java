package V8;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class Z extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f9602m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ W8.E f9603n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Z(W8.E e10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f9603n = e10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        Z z6 = new Z(this.f9603n, interfaceC2189c);
        z6.f9602m = obj;
        return z6;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        ((Z) create((InterfaceC0818g) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
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
        C.f fVar = new C.f(new A7.v(), 9, (InterfaceC0818g) this.f9602m);
        this.l = 1;
        this.f9603n.b(fVar, this);
        return enumC2303a;
    }
}
