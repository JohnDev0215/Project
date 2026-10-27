package io.reactivex.rxjava3.internal.disposables;

import java.util.concurrent.atomic.AtomicReference;
import spi.q6;

/* JADX INFO: loaded from: classes2.dex */
public final class SequentialDisposable extends AtomicReference<q6> implements q6 {
    private static final long serialVersionUID = -754898800686245608L;

    public SequentialDisposable() {
    }

    public SequentialDisposable(q6 q6Var) {
        lazySet(q6Var);
    }

    @Override // spi.q6
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    public boolean replace(q6 q6Var) {
        return DisposableHelper.replace(this, q6Var);
    }

    public boolean update(q6 q6Var) {
        return DisposableHelper.set(this, q6Var);
    }
}
