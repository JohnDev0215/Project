package O;

import x.C2648n;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class z extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5007m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2648n f5008n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ z(int i7, C2648n c2648n) {
        super(0);
        this.f5007m = i7;
        this.f5008n = c2648n;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f5007m) {
            case 0:
                break;
            case 1:
                this.f5008n.f23572q = false;
                break;
            default:
                this.f5008n.f23572q = false;
                break;
        }
        return m7.q.f19267a;
    }
}
