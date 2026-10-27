package io.reactivex.rxjava3.internal.observers;

import spi.bk;
import spi.vn;

/* JADX INFO: loaded from: classes2.dex */
public class DeferredScalarDisposable<T> extends BasicIntQueueDisposable<T> {
    public static final int DISPOSED = 4;
    public static final int FUSED_CONSUMED = 32;
    public static final int FUSED_EMPTY = 8;
    public static final int FUSED_READY = 16;
    public static final int TERMINATED = 2;
    private static final long serialVersionUID = -5502432239815349361L;
    public final bk<? super T> downstream;
    public T value;

    public DeferredScalarDisposable(bk<? super T> bkVar) {
        this.downstream = bkVar;
    }

    @Override // io.reactivex.rxjava3.internal.observers.BasicIntQueueDisposable, spi.kp
    public final void clear() {
        lazySet(32);
        this.value = null;
    }

    public final void complete() {
        if ((get() & 54) != 0) {
            return;
        }
        lazySet(2);
        this.downstream.onComplete();
    }

    public final void complete(T t) {
        int i = get();
        if ((i & 54) != 0) {
            return;
        }
        bk<? super T> bkVar = this.downstream;
        if (i == 8) {
            this.value = t;
            lazySet(16);
            t = null;
        } else {
            lazySet(2);
        }
        bkVar.onNext(t);
        if (get() != 4) {
            bkVar.onComplete();
        }
    }

    @Override // io.reactivex.rxjava3.internal.observers.BasicIntQueueDisposable, spi.q6
    public void dispose() {
        set(4);
        this.value = null;
    }

    public final void error(Throwable th) {
        if ((get() & 54) != 0) {
            vn.a(th);
        } else {
            lazySet(2);
            this.downstream.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.internal.observers.BasicIntQueueDisposable
    public final boolean isDisposed() {
        return get() == 4;
    }

    @Override // io.reactivex.rxjava3.internal.observers.BasicIntQueueDisposable
    public final boolean isEmpty() {
        return get() != 16;
    }

    @Override // io.reactivex.rxjava3.internal.observers.BasicIntQueueDisposable
    public final T poll() {
        if (get() != 16) {
            return null;
        }
        T t = this.value;
        this.value = null;
        lazySet(32);
        return t;
    }

    @Override // io.reactivex.rxjava3.internal.observers.BasicIntQueueDisposable, spi.rl
    public final int requestFusion(int i) {
        if ((i & 2) == 0) {
            return 0;
        }
        lazySet(8);
        return 2;
    }

    public final boolean tryDispose() {
        return getAndSet(4) != 4;
    }
}
