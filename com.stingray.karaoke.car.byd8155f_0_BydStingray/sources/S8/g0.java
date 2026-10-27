package S8;

import F0.C0223q;
import java.util.concurrent.CancellationException;
import q7.AbstractC2187a;
import s7.AbstractC2410c;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class g0 extends AbstractC2187a implements W {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final g0 f8113m = new g0(C0722t.f8137m);

    @Override // S8.W
    public final Object K(AbstractC2410c abstractC2410c) {
        throw new UnsupportedOperationException("This job is always active");
    }

    @Override // S8.W
    public final boolean b() {
        return true;
    }

    @Override // S8.W
    public final void g(CancellationException cancellationException) {
    }

    @Override // S8.W
    public final F m(boolean z6, boolean z10, C0223q c0223q) {
        return h0.l;
    }

    @Override // S8.W
    public final InterfaceC0715l q(d0 d0Var) {
        return h0.l;
    }

    @Override // S8.W
    public final boolean start() {
        return false;
    }

    public final String toString() {
        return "NonCancellable";
    }

    @Override // S8.W
    public final CancellationException y() {
        throw new IllegalStateException("This job is always active");
    }

    @Override // S8.W
    public final F z(InterfaceC2782k interfaceC2782k) {
        return h0.l;
    }
}
