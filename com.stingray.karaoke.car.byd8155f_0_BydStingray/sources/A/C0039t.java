package A;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: A.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0039t extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0043v f268m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ y.j0 f269n;
    public final /* synthetic */ InterfaceC2785n o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0039t(C0043v c0043v, y.j0 j0Var, InterfaceC2785n interfaceC2785n, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f268m = c0043v;
        this.f269n = j0Var;
        this.o = interfaceC2785n;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0039t(this.f268m, this.f269n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0039t) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C0043v c0043v = this.f268m;
            y.n0 n0Var = c0043v.c;
            C0041u c0041u = c0043v.f282b;
            C0037s c0037s = new C0037s(c0043v, this.o, null);
            this.l = 1;
            n0Var.getClass();
            if (AbstractC0727y.h(new y.m0(this.f269n, n0Var, c0037s, c0041u, null), this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return m7.q.f19267a;
    }
}
