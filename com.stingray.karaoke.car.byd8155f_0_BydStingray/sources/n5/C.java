package N5;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class C extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Z f4595m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ f0 f4596n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C(String str, Z z6, f0 f0Var, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = str;
        this.f4595m = z6;
        this.f4596n = f0Var;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C(this.l, this.f4595m, this.f4596n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C c = (C) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        Z z6 = this.f4595m;
        f0 f0Var = (f0) z6.f4636O.getValue();
        if (A7.m.a(this.l, f0Var != null ? f0Var.d : null)) {
            f0 f0Var2 = this.f4596n;
            z6.f4642U.l(f0Var2 != null ? f0Var2.f4692b : null);
            z6.f4644W.l(f0Var2 != null ? f0Var2.c : null);
        }
        return m7.q.f19267a;
    }
}
