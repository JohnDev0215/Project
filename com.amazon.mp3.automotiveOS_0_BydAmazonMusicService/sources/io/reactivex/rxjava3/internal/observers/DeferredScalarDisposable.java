package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: classes7.dex */
public class DeferredScalarDisposable<T> extends BasicIntQueueDisposable<T> {
    protected final Observer<? super T> downstream;
    protected T value;

    public DeferredScalarDisposable(Observer<? super T> downstream) {
        this.downstream = downstream;
    }

    @Override // io.reactivex.rxjava3.operators.QueueFuseable
    public final int requestFusion(int mode) {
        if ((mode & 2) == 0) {
            return 0;
        }
        lazySet(8);
        return 2;
    }

    public final void complete(T value) {
        int i = get();
        if ((i & 54) != 0) {
            return;
        }
        Observer<? super T> observer = this.downstream;
        if (i == 8) {
            this.value = value;
            lazySet(16);
            observer.onNext(null);
        } else {
            lazySet(2);
            observer.onNext(value);
        }
        if (get() != 4) {
            observer.onComplete();
        }
    }

    public final void error(Throwable t) {
        if ((get() & 54) != 0) {
            RxJavaPlugins.onError(t);
        } else {
            lazySet(2);
            this.downstream.onError(t);
        }
    }

    public final void complete() {
        if ((get() & 54) != 0) {
            return;
        }
        lazySet(2);
        this.downstream.onComplete();
    }

    @Override // io.reactivex.rxjava3.operators.SimpleQueue
    public final T poll() {
        if (get() != 16) {
            return null;
        }
        T t = this.value;
        this.value = null;
        lazySet(32);
        return t;
    }

    @Override // io.reactivex.rxjava3.operators.SimpleQueue
    public final boolean isEmpty() {
        return get() != 16;
    }

    @Override // io.reactivex.rxjava3.operators.SimpleQueue
    public final void clear() {
        lazySet(32);
        this.value = null;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        set(4);
        this.value = null;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final boolean isDisposed() {
        return get() == 4;
    }
}
