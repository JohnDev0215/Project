package K;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class A0 extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3118m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ E0 f3119n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ A0(E0 e02, int i7) {
        super(0);
        this.f3118m = i7;
        this.f3119n = e02;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f3118m) {
            case 0:
                return Boolean.valueOf(this.f3119n.f3142a.i() > 0.0f);
            default:
                E0 e02 = this.f3119n;
                return Boolean.valueOf(e02.f3142a.i() < e02.f3143b.i());
        }
    }
}
