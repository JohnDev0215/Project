package n6;

import F0.k1;
import N1.S;
import S8.InterfaceC0725w;
import V8.f0;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class r extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ u f20125m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(u uVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f20125m = uVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new r(this.f20125m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        ((r) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
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
        u uVar = this.f20125m;
        f0 f0Var = ((S) uVar.f20128b.f18608m).f4497a;
        k1 k1Var = new k1(uVar, 12);
        this.l = 1;
        f0Var.b(k1Var, this);
        return enumC2303a;
    }
}
