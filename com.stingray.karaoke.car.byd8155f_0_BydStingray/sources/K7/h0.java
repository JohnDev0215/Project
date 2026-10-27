package K7;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class h0 extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3789m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ i0 f3790n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h0(i0 i0Var, int i7) {
        super(0);
        this.f3789m = i7;
        this.f3790n = i0Var;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f3789m) {
            case 0:
                return v0.f(this.f3790n, true);
            default:
                i0 i0Var = this.f3790n;
                T7.I iC = i0Var.t().n().c();
                return iC == null ? r8.m.f(i0Var.t().n(), R7.g.f7535a) : iC;
        }
    }
}
