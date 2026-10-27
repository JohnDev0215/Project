package K7;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class j0 extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3797m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ k0 f3798n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j0(k0 k0Var, int i7) {
        super(0);
        this.f3797m = i7;
        this.f3798n = k0Var;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f3797m) {
            case 0:
                return v0.f(this.f3798n, false);
            default:
                k0 k0Var = this.f3798n;
                T7.J jD = k0Var.t().n().d();
                return jD == null ? r8.m.g(k0Var.t().n(), R7.g.f7535a) : jD;
        }
    }
}
