package T;

import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class E implements InterfaceC0769u0 {
    public final InterfaceC2782k l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public F f8183m;

    public E(InterfaceC2782k interfaceC2782k) {
        this.l = interfaceC2782k;
    }

    @Override // T.InterfaceC0769u0
    public final void a() {
        this.f8183m = (F) this.l.b(C0735d.f8242h);
    }

    @Override // T.InterfaceC0769u0
    public final void c() {
    }

    @Override // T.InterfaceC0769u0
    public final void d() {
        F f10 = this.f8183m;
        if (f10 != null) {
            f10.a();
        }
        this.f8183m = null;
    }
}
