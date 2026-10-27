package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConcatMapXMainObserver<T> extends AtomicInteger implements Observer<T>, Disposable {
    private static final long serialVersionUID = -3214213361171757852L;
    volatile boolean disposed;
    volatile boolean done;
    final ErrorMode errorMode;
    final AtomicThrowable errors = new AtomicThrowable();
    final int prefetch;
    io.reactivex.rxjava3.operators.g queue;
    Disposable upstream;

    public ConcatMapXMainObserver(int i, ErrorMode errorMode) {
        this.errorMode = errorMode;
        this.prefetch = i;
    }

    public void a() {
    }

    public abstract void b();

    public abstract void c();

    public abstract void d();

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final void dispose() {
        this.disposed = true;
        this.upstream.dispose();
        b();
        this.errors.b();
        if (getAndIncrement() == 0) {
            this.queue.clear();
            a();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final boolean isDisposed() {
        return this.disposed;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.done = true;
        c();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.errors.a(th)) {
            if (this.errorMode == ErrorMode.c) {
                b();
            }
            this.done = true;
            c();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (obj != null) {
            this.queue.offer(obj);
        }
        c();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            if (disposable instanceof io.reactivex.rxjava3.operators.b) {
                io.reactivex.rxjava3.operators.b bVar = (io.reactivex.rxjava3.operators.b) disposable;
                int iB = bVar.b(7);
                if (iB == 1) {
                    this.queue = bVar;
                    this.done = true;
                    d();
                    c();
                    return;
                }
                if (iB == 2) {
                    this.queue = bVar;
                    d();
                    return;
                }
            }
            this.queue = new io.reactivex.rxjava3.operators.h(this.prefetch);
            d();
        }
    }
}
