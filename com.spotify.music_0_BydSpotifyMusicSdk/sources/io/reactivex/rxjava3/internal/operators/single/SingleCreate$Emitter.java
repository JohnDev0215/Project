package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.CancellableDisposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class SingleCreate$Emitter<T> extends AtomicReference<Disposable> implements SingleEmitter<T>, Disposable {
    private static final long serialVersionUID = -2467358622224974244L;
    final SingleObserver<? super T> downstream;

    public SingleCreate$Emitter(SingleObserver singleObserver) {
        this.downstream = singleObserver;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this);
    }

    @Override // io.reactivex.rxjava3.core.SingleEmitter, io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(get());
    }

    @Override // io.reactivex.rxjava3.core.SingleEmitter
    public final void onError(Throwable th) {
        if (tryOnError(th)) {
            return;
        }
        RxJavaPlugins.b(th);
    }

    @Override // io.reactivex.rxjava3.core.SingleEmitter
    public void onSuccess(T t) {
        Disposable andSet;
        Disposable disposable = get();
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable == disposableHelper || (andSet = getAndSet(disposableHelper)) == disposableHelper) {
            return;
        }
        try {
            if (t == null) {
                this.downstream.onError(io.reactivex.rxjava3.internal.util.c.b("onSuccess called with a null value."));
            } else {
                this.downstream.onSuccess(t);
            }
            if (andSet != null) {
                andSet.dispose();
            }
        } catch (Throwable th) {
            if (andSet != null) {
                andSet.dispose();
            }
            throw th;
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleEmitter
    public void setCancellable(io.reactivex.rxjava3.functions.f fVar) {
        DisposableHelper.d(this, new CancellableDisposable(fVar));
    }

    @Override // io.reactivex.rxjava3.core.SingleEmitter
    public final void setDisposable(Disposable disposable) {
        DisposableHelper.d(this, disposable);
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        return String.format("%s{%s}", SingleCreate$Emitter.class.getSimpleName(), super.toString());
    }

    @Override // io.reactivex.rxjava3.core.SingleEmitter
    public final boolean tryOnError(Throwable th) {
        Disposable andSet;
        if (th == null) {
            th = io.reactivex.rxjava3.internal.util.c.b("onError called with a null Throwable.");
        }
        Disposable disposable = get();
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable == disposableHelper || (andSet = getAndSet(disposableHelper)) == disposableHelper) {
            return false;
        }
        try {
            this.downstream.onError(th);
        } finally {
            if (andSet != null) {
                andSet.dispose();
            }
        }
    }
}
