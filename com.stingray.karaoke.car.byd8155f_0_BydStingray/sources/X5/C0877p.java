package X5;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: X5.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0877p extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0886u f10370m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0877p(C0886u c0886u, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f10370m = c0886u;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0877p(this.f10370m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        ((C0877p) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
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
        C0886u c0886u = this.f10370m;
        V8.N n10 = c0886u.f10384a.f4635N;
        A3.i iVar = new A3.i(c0886u, 1);
        this.l = 1;
        n10.b(iVar, this);
        return enumC2303a;
    }
}
