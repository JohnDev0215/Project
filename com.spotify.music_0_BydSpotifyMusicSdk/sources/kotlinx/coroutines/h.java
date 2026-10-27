package kotlinx.coroutines;

import p.fz2;
import p.y10;

/* JADX INFO: loaded from: classes.dex */
public final class h extends b {
    public static final h k = new h();

    @Override // kotlinx.coroutines.b
    public final void G0(y10 y10Var, Runnable runnable) {
        fz2 fz2Var = (fz2) y10Var.E0(fz2.k);
        if (fz2Var == null) {
            throw new UnsupportedOperationException("Dispatchers.Unconfined.dispatch function can only be used by the yield function. If you wrap Unconfined dispatcher in your code, make sure you properly delegate isDispatchNeeded and dispatch calls.");
        }
        fz2Var.e = true;
    }

    @Override // kotlinx.coroutines.b
    public final String toString() {
        return "Dispatchers.Unconfined";
    }
}
