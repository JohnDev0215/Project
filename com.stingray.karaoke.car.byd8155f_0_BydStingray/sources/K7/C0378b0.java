package K7;

import z7.InterfaceC2772a;

/* JADX INFO: renamed from: K7.b0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0378b0 extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3775m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ c0 f3776n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0378b0(c0 c0Var, int i7) {
        super(0);
        this.f3775m = i7;
        this.f3776n = c0Var;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f3775m) {
            case 0:
                return new C0376a0(this.f3776n);
            default:
                return this.f3776n.s();
        }
    }
}
