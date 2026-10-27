package K7;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class e0 extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3783m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ f0 f3784n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e0(f0 f0Var, int i7) {
        super(0);
        this.f3783m = i7;
        this.f3784n = f0Var;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f3783m) {
            case 0:
                return new d0(this.f3784n);
            default:
                return this.f3784n.s();
        }
    }
}
