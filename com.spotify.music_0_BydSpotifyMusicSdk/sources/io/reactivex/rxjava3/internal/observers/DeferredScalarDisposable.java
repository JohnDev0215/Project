package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: classes.dex */
public class DeferredScalarDisposable<T> extends BasicIntQueueDisposable<T> {
    private static final long serialVersionUID = -5502432239815349361L;
    protected final Observer<? super T> downstream;
    protected T value;

    public DeferredScalarDisposable(Observer observer) {
        this.downstream = observer;
    }

    public final void a() {
        if ((get() & 54) != 0) {
            return;
        }
        lazySet(2);
        this.downstream.onComplete();
    }

    @Override // io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        if ((i & 2) == 0) {
            return 0;
        }
        lazySet(8);
        return 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void c(Object obj) {
        int i = get();
        if ((i & 54) != 0) {
            return;
        }
        Observer<? super T> observer = this.downstream;
        if (i == 8) {
            this.value = obj;
            lazySet(16);
            observer.onNext(null);
        } else {
            lazySet(2);
            observer.onNext(obj);
        }
        if (get() != 4) {
            observer.onComplete();
        }
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
        lazySet(32);
        this.value = null;
    }

    public final void d(Throwable th) {
        if ((get() & 54) != 0) {
            RxJavaPlugins.b(th);
        } else {
            lazySet(2);
            this.downstream.onError(th);
        }
    }

    public void dispose() {
        set(4);
        this.value = null;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final boolean isDisposed() {
        return get() == 4;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return get() != 16;
    }

    public void onComplete() {
        a();
    }

    public void onError(Throwable th) {
        d(th);
    }

    public void onSuccess(Object obj) {
        c(obj);
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        if (get() != 16) {
            return null;
        }
        T t = this.value;
        this.value = null;
        lazySet(32);
        return t;
    }
}
