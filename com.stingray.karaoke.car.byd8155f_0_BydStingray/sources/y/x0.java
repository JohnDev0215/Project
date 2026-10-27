package y;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class x0 extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f24025m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ y0 f24026n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ x0(y0 y0Var, int i7) {
        super(0);
        this.f24025m = i7;
        this.f24026n = y0Var;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f24025m) {
            case 0:
                return Boolean.valueOf(this.f24026n.f24028a.i() > 0);
            default:
                y0 y0Var = this.f24026n;
                return Boolean.valueOf(y0Var.f24028a.i() < y0Var.d.i());
        }
    }
}
