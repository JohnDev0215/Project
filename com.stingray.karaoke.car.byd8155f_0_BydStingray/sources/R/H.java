package R;

import n0.C1863M;
import x.u0;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class H extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7246m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ u0 f7247n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ H(u0 u0Var, int i7) {
        super(1);
        this.f7246m = i7;
        this.f7247n = u0Var;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f7246m) {
            case 0:
                ((C1863M) obj).a(((Number) this.f7247n.f23639u.getValue()).floatValue());
                break;
            case 1:
                ((C1863M) obj).a(((Number) this.f7247n.f23639u.getValue()).floatValue());
                break;
            case 2:
                ((C1863M) obj).a(((Number) this.f7247n.f23639u.getValue()).floatValue());
                break;
            default:
                ((C1863M) obj).a(((Number) this.f7247n.f23639u.getValue()).floatValue());
                break;
        }
        return m7.q.f19267a;
    }
}
