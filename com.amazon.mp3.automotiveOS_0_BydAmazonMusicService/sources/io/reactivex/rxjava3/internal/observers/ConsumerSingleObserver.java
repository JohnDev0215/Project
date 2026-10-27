package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes7.dex */
public final class ConsumerSingleObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable {
    final Consumer<? super Throwable> onError;
    final Consumer<? super T> onSuccess;

    public ConsumerSingleObserver(Consumer<? super T> onSuccess, Consumer<? super Throwable> onError) {
        this.onSuccess = onSuccess;
        this.onError = onError;
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public void onError(Throwable e) {
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onError.accept(e);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(new CompositeException(e, th));
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public void onSubscribe(Disposable d) {
        DisposableHelper.setOnce(this, d);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public void onSuccess(T value) {
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onSuccess.accept(value);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }
}
