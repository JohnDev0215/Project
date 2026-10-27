package io.reactivex.rxjava3.internal.disposables;

import java.util.concurrent.atomic.AtomicReference;
import spi.g3;
import spi.q6;
import spi.vn;
import spi.x8;

/* JADX INFO: loaded from: classes2.dex */
public final class CancellableDisposable extends AtomicReference<g3> implements q6 {
    private static final long serialVersionUID = 5718521705281392066L;

    public CancellableDisposable(g3 g3Var) {
        super(g3Var);
    }

    @Override // spi.q6
    public void dispose() {
        g3 andSet;
        if (get() == null || (andSet = getAndSet(null)) == null) {
            return;
        }
        try {
            andSet.cancel();
        } catch (Throwable th) {
            x8.a(th);
            vn.a(th);
        }
    }

    public boolean isDisposed() {
        return get() == null;
    }
}
