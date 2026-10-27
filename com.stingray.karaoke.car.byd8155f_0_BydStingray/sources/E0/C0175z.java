package E0;

import C0.C0107n;

/* JADX INFO: renamed from: E0.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0175z extends S {

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final /* synthetic */ A f1714C;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0175z(A a10) {
        super(a10);
        this.f1714C = a10;
    }

    @Override // C0.J
    public final int P(int i7) {
        A a10 = this.f1714C;
        InterfaceC0174y interfaceC0174y = a10.f1456V;
        d0 d0Var = a10.f1649x;
        A7.m.c(d0Var);
        S sO0 = d0Var.O0();
        A7.m.c(sO0);
        return interfaceC0174y.c(this, sO0, i7);
    }

    @Override // C0.J
    public final int V(int i7) {
        A a10 = this.f1714C;
        InterfaceC0174y interfaceC0174y = a10.f1456V;
        d0 d0Var = a10.f1649x;
        A7.m.c(d0Var);
        S sO0 = d0Var.O0();
        A7.m.c(sO0);
        return interfaceC0174y.d(this, sO0, i7);
    }

    @Override // C0.J
    public final int a0(int i7) {
        A a10 = this.f1714C;
        InterfaceC0174y interfaceC0174y = a10.f1456V;
        d0 d0Var = a10.f1649x;
        A7.m.c(d0Var);
        S sO0 = d0Var.O0();
        A7.m.c(sO0);
        return interfaceC0174y.f(this, sO0, i7);
    }

    @Override // C0.J
    public final C0.V b(long j3) {
        o0(j3);
        new a1.a(j3);
        A a10 = this.f1714C;
        a10.getClass();
        InterfaceC0174y interfaceC0174y = a10.f1456V;
        d0 d0Var = a10.f1649x;
        A7.m.c(d0Var);
        S sO0 = d0Var.O0();
        A7.m.c(sO0);
        S.E0(this, interfaceC0174y.g(this, sO0, j3));
        return this;
    }

    @Override // C0.J
    public final int c(int i7) {
        A a10 = this.f1714C;
        InterfaceC0174y interfaceC0174y = a10.f1456V;
        d0 d0Var = a10.f1649x;
        A7.m.c(d0Var);
        S sO0 = d0Var.O0();
        A7.m.c(sO0);
        return interfaceC0174y.b(this, sO0, i7);
    }

    @Override // E0.Q
    public final int q0(C0107n c0107n) {
        int iC = AbstractC0156f.c(this, c0107n);
        this.B.put(c0107n, Integer.valueOf(iC));
        return iC;
    }
}
