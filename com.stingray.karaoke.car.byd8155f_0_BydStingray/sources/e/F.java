package E;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class F extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1322m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f1323n;
    public final /* synthetic */ int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ F(int i7, int i10, int i11) {
        super(0);
        this.f1322m = i11;
        this.f1323n = i7;
        this.o = i10;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f1322m) {
            case 0:
                return new D(this.f1323n, this.o);
            default:
                return new F.y(this.f1323n, this.o);
        }
    }
}
