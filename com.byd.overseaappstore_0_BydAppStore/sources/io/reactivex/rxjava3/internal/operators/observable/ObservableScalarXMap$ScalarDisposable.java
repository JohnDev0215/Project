package io.reactivex.rxjava3.internal.operators.observable;

import java.util.concurrent.atomic.AtomicInteger;
import spi.bk;
import spi.ql;

/* JADX INFO: loaded from: classes2.dex */
public final class ObservableScalarXMap$ScalarDisposable<T> extends AtomicInteger implements ql<T>, Runnable {
    public static final int FUSED = 1;
    public static final int ON_COMPLETE = 3;
    public static final int ON_NEXT = 2;
    public static final int START = 0;
    private static final long serialVersionUID = 3880992722410194083L;
    public final bk<? super T> observer;
    public final T value;

    public ObservableScalarXMap$ScalarDisposable(bk<? super T> bkVar, T t) {
        this.observer = bkVar;
        this.value = t;
    }

    @Override // spi.kp
    public void clear() {
        lazySet(3);
    }

    @Override // spi.q6
    public void dispose() {
        set(3);
    }

    public boolean isDisposed() {
        return get() == 3;
    }

    public boolean isEmpty() {
        return get() != 1;
    }

    @Override // spi.kp
    public boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public boolean offer(T t, T t2) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public T poll() {
        if (get() != 1) {
            return null;
        }
        lazySet(3);
        return this.value;
    }

    @Override // spi.rl
    public int requestFusion(int i) {
        if ((i & 1) == 0) {
            return 0;
        }
        lazySet(1);
        return 1;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (get() == 0 && compareAndSet(0, 2)) {
            this.observer.onNext(this.value);
            if (get() == 2) {
                lazySet(3);
                this.observer.onComplete();
            }
        }
    }
}
