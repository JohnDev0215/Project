package S8;

import h8.AbstractC1386c;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class G extends Z {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f8085p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Object f8086q;

    public /* synthetic */ G(Object obj, int i7) {
        this.f8085p = i7;
        this.f8086q = obj;
    }

    @Override // S8.Z
    public final boolean k() {
        switch (this.f8085p) {
        }
        return false;
    }

    @Override // S8.Z
    public final void l(Throwable th) {
        switch (this.f8085p) {
            case 0:
                ((F) this.f8086q).a();
                break;
            case 1:
                ((InterfaceC2782k) this.f8086q).b(th);
                break;
            default:
                Object obj = d0.l.get(j());
                boolean z6 = obj instanceof C0720q;
                a0 a0Var = (a0) this.f8086q;
                if (!z6) {
                    a0Var.resumeWith(AbstractC0727y.D(obj));
                } else {
                    a0Var.resumeWith(AbstractC1386c.f(((C0720q) obj).f8130a));
                }
                break;
        }
    }
}
