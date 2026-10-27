package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class ObservableScalarXMap$ScalarDisposable<T> extends AtomicInteger implements io.reactivex.rxjava3.operators.b, Runnable {
    private static final long serialVersionUID = 3880992722410194083L;
    final Observer<? super T> observer;
    final T value;

    /* JADX WARN: Multi-variable type inference failed */
    public ObservableScalarXMap$ScalarDisposable(Observer observer, Object obj) {
        this.observer = observer;
        this.value = obj;
    }

    @Override // io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        if ((i & 1) == 0) {
            return 0;
        }
        lazySet(1);
        return 1;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
        lazySet(3);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        set(3);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get() == 3;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return get() != 1;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        if (get() != 1) {
            return null;
        }
        lazySet(3);
        return this.value;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (get() == 0 && compareAndSet(0, 2)) {
            this.observer.onNext(this.value);
            if (get() == 2) {
                lazySet(3);
                this.observer.onComplete();
            }
        }
    }
}
