package R;

import x.u0;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class K extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7255m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ u0 f7256n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ K(u0 u0Var, int i7) {
        super(0);
        this.f7255m = i7;
        this.f7256n = u0Var;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f7255m) {
            case 0:
                return Boolean.valueOf(((Number) this.f7256n.f23639u.getValue()).floatValue() > 0.0f);
            default:
                return Boolean.valueOf(((Number) this.f7256n.f23639u.getValue()).floatValue() > 0.0f);
        }
    }
}
