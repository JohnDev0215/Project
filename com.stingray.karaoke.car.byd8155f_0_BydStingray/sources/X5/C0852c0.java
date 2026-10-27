package X5;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: X5.c0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0852c0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ D0 f10230m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ j6.n f10231n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0852c0(D0 d02, j6.n nVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f10230m = d02;
        this.f10231n = nVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0852c0(this.f10230m, this.f10231n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        ((C0852c0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
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
        D0 d02 = this.f10230m;
        V8.N n10 = d02.f10158x.f4637P;
        C.f fVar = new C.f(d02, 10, this.f10231n);
        this.l = 1;
        ((V8.f0) n10.l).b(fVar, this);
        return enumC2303a;
    }
}
