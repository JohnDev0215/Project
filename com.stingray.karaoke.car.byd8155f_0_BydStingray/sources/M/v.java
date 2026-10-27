package M;

import E0.InterfaceC0162l;
import E0.InterfaceC0163m;
import E0.InterfaceC0166p;
import E0.d0;
import K.C0331b0;
import O.K;
import T.C0735d;
import T.C0738e0;
import T.S;

/* JADX INFO: loaded from: classes.dex */
public final class v extends g0.n implements InterfaceC0162l, InterfaceC0166p, InterfaceC0163m {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public K f4154A;
    public final C0738e0 B = C0735d.O(null, S.f8224q);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public f f4155y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public C0331b0 f4156z;

    public v(f fVar, C0331b0 c0331b0, K k10) {
        this.f4155y = fVar;
        this.f4156z = c0331b0;
        this.f4154A = k10;
    }

    @Override // g0.n
    public final void A0() {
        this.f4155y.k(this);
    }

    @Override // E0.InterfaceC0166p
    public final void l0(d0 d0Var) {
        this.B.setValue(d0Var);
    }

    @Override // g0.n
    public final void z0() {
        f fVar = this.f4155y;
        if (fVar.f4137a != null) {
            throw new IllegalStateException("Expected textInputModifierNode to be null");
        }
        fVar.f4137a = this;
    }
}
