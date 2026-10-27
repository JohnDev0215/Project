package O;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class D extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final D f4894n = new D(0, 0);
    public static final D o = new D(0, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4895m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ D(int i7, int i10) {
        super(i7);
        this.f4895m = i10;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f4895m) {
            case 0:
                return null;
            default:
                return T.f4942b;
        }
    }
}
