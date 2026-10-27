package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.operators.SpscArrayQueue;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class InnerQueuedObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
    private static final long serialVersionUID = -5417183359794346637L;
    volatile boolean done;
    int fusionMode;
    final j parent;
    final int prefetch;
    io.reactivex.rxjava3.operators.g queue;

    public InnerQueuedObserver(j jVar, int i) {
        this.parent = jVar;
        this.prefetch = i;
    }

    public final boolean a() {
        return this.done;
    }

    public final io.reactivex.rxjava3.operators.g b() {
        return this.queue;
    }

    public final void c() {
        this.done = true;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(get());
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.parent.f(this);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.parent.c(this, th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.fusionMode == 0) {
            this.parent.d(this, obj);
        } else {
            this.parent.b();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.f(this, disposable)) {
            if (disposable instanceof io.reactivex.rxjava3.operators.b) {
                io.reactivex.rxjava3.operators.b bVar = (io.reactivex.rxjava3.operators.b) disposable;
                int iB = bVar.b(3);
                if (iB == 1) {
                    this.fusionMode = iB;
                    this.queue = bVar;
                    this.done = true;
                    this.parent.f(this);
                    return;
                }
                if (iB == 2) {
                    this.fusionMode = iB;
                    this.queue = bVar;
                    return;
                }
            }
            int i = -this.prefetch;
            this.queue = i < 0 ? new io.reactivex.rxjava3.operators.h(-i) : new SpscArrayQueue(i);
        }
    }
}
