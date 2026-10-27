package y;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class u0 extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f24011m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ v0 f24012n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ u0(v0 v0Var, int i7) {
        super(0);
        this.f24011m = i7;
        this.f24012n = v0Var;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f24011m) {
            case 0:
                return Float.valueOf(this.f24012n.f24016y.f24028a.i());
            default:
                return Float.valueOf(this.f24012n.f24016y.d.i());
        }
    }
}
