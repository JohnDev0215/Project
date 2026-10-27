package G;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class Y extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2529m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ b0 f2530n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Y(b0 b0Var, int i7) {
        super(0);
        this.f2529m = i7;
        this.f2530n = b0Var;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f2529m) {
            case 0:
                b0 b0Var = this.f2530n;
                return Float.valueOf(b0Var.f2548z.a() - b0Var.f2548z.d());
            case 1:
                return Float.valueOf(this.f2530n.f2548z.b());
            default:
                return Float.valueOf(this.f2530n.f2548z.e());
        }
    }
}
