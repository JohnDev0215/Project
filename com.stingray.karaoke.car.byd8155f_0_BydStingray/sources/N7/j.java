package N7;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class j extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4799m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ k f4800n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j(k kVar, int i7) {
        super(0);
        this.f4799m = i7;
        this.f4800n = kVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f4799m) {
            case 0:
                return o.f4875j.c(this.f4800n.f4811m);
            default:
                return o.f4875j.c(this.f4800n.l);
        }
    }
}
