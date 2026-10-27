package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.CancellableDisposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableCreate$CreateEmitter<T> extends AtomicReference<Disposable> implements ObservableEmitter<T>, Disposable {
    private static final long serialVersionUID = -3434801548987643227L;
    final Observer<? super T> observer;

    public ObservableCreate$CreateEmitter(Observer observer) {
        this.observer = observer;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this);
    }

    @Override // io.reactivex.rxjava3.core.ObservableEmitter, io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(get());
    }

    @Override // io.reactivex.rxjava3.core.ObservableEmitter, io.reactivex.rxjava3.core.Emitter
    public final void onComplete() {
        if (isDisposed()) {
            return;
        }
        try {
            this.observer.onComplete();
        } finally {
            dispose();
        }
    }

    @Override // io.reactivex.rxjava3.core.ObservableEmitter, io.reactivex.rxjava3.core.Emitter
    public final void onError(Throwable th) {
        if (tryOnError(th)) {
            return;
        }
        RxJavaPlugins.b(th);
    }

    @Override // io.reactivex.rxjava3.core.ObservableEmitter, io.reactivex.rxjava3.core.Emitter
    public void onNext(T t) {
        if (t == null) {
            onError(io.reactivex.rxjava3.internal.util.c.b("onNext called with a null value."));
        } else {
            if (isDisposed()) {
                return;
            }
            this.observer.onNext(t);
        }
    }

    @Override // io.reactivex.rxjava3.core.ObservableEmitter
    public final ObservableEmitter serialize() {
        return new ObservableCreate$SerializedEmitter(this);
    }

    @Override // io.reactivex.rxjava3.core.ObservableEmitter
    public void setCancellable(io.reactivex.rxjava3.functions.f fVar) {
        DisposableHelper.d(this, new CancellableDisposable(fVar));
    }

    @Override // io.reactivex.rxjava3.core.ObservableEmitter
    public final void setDisposable(Disposable disposable) {
        DisposableHelper.d(this, disposable);
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        return String.format("%s{%s}", ObservableCreate$CreateEmitter.class.getSimpleName(), super.toString());
    }

    @Override // io.reactivex.rxjava3.core.ObservableEmitter
    public final boolean tryOnError(Throwable th) {
        if (th == null) {
            th = io.reactivex.rxjava3.internal.util.c.b("onError called with a null Throwable.");
        }
        if (isDisposed()) {
            return false;
        }
        try {
            this.observer.onError(th);
            dispose();
            return true;
        } catch (Throwable th2) {
            dispose();
            throw th2;
        }
    }
}
