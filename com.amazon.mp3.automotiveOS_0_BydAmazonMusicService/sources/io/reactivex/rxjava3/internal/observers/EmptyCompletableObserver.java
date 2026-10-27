package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.OnErrorNotImplementedException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes7.dex */
public final class EmptyCompletableObserver extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver, io.reactivex.rxjava3.core.MaybeObserver
    public void onComplete() {
        lazySet(DisposableHelper.DISPOSED);
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public void onError(Throwable e) {
        lazySet(DisposableHelper.DISPOSED);
        RxJavaPlugins.onError(new OnErrorNotImplementedException(e));
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public void onSubscribe(Disposable d) {
        DisposableHelper.setOnce(this, d);
    }
}
