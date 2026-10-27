package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.exceptions.OnErrorNotImplementedException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;
import spi.q6;
import spi.s3;
import spi.vn;

/* JADX INFO: loaded from: classes2.dex */
public final class EmptyCompletableObserver extends AtomicReference<q6> implements s3, q6 {
    private static final long serialVersionUID = -7545121636549663526L;

    @Override // spi.q6
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean hasCustomOnError() {
        return false;
    }

    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    @Override // spi.s3
    public void onComplete() {
        lazySet(DisposableHelper.DISPOSED);
    }

    @Override // spi.s3
    public void onError(Throwable th) {
        lazySet(DisposableHelper.DISPOSED);
        vn.a(new OnErrorNotImplementedException(th));
    }

    @Override // spi.s3
    public void onSubscribe(q6 q6Var) {
        DisposableHelper.setOnce(this, q6Var);
    }
}
