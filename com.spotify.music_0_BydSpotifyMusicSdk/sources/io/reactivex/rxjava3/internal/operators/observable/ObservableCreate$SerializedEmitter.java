package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
final class ObservableCreate$SerializedEmitter<T> extends AtomicInteger implements ObservableEmitter<T> {
    private static final long serialVersionUID = 4883307006032401862L;
    volatile boolean done;
    final ObservableEmitter<T> emitter;
    final AtomicThrowable errors = new AtomicThrowable();
    final io.reactivex.rxjava3.operators.h queue = new io.reactivex.rxjava3.operators.h(16);

    public ObservableCreate$SerializedEmitter(ObservableEmitter observableEmitter) {
        this.emitter = observableEmitter;
    }

    public final void a() {
        if (getAndIncrement() == 0) {
            b();
        }
    }

    public final void b() {
        ObservableEmitter<T> observableEmitter = this.emitter;
        io.reactivex.rxjava3.operators.h hVar = this.queue;
        AtomicThrowable atomicThrowable = this.errors;
        int iAddAndGet = 1;
        while (!observableEmitter.isDisposed()) {
            if (atomicThrowable.get() != null) {
                hVar.clear();
                Throwable thD = io.reactivex.rxjava3.internal.util.c.d(atomicThrowable);
                if (thD == null) {
                    observableEmitter.onComplete();
                    return;
                } else {
                    if (thD != io.reactivex.rxjava3.internal.util.c.a) {
                        observableEmitter.onError(thD);
                        return;
                    }
                    return;
                }
            }
            boolean z = this.done;
            Object objPoll = hVar.poll();
            boolean z2 = objPoll == null;
            if (z && z2) {
                observableEmitter.onComplete();
                return;
            } else if (z2) {
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            } else {
                observableEmitter.onNext(objPoll);
            }
        }
        hVar.clear();
    }

    @Override // io.reactivex.rxjava3.core.ObservableEmitter, io.reactivex.rxjava3.disposables.Disposable
    public final boolean isDisposed() {
        return this.emitter.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.ObservableEmitter, io.reactivex.rxjava3.core.Emitter
    public final void onComplete() {
        if (this.done || this.emitter.isDisposed()) {
            return;
        }
        this.done = true;
        a();
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
        if (this.done || this.emitter.isDisposed()) {
            return;
        }
        if (t == null) {
            onError(io.reactivex.rxjava3.internal.util.c.b("onNext called with a null value."));
            return;
        }
        if (get() == 0 && compareAndSet(0, 1)) {
            this.emitter.onNext(t);
            if (decrementAndGet() == 0) {
                return;
            }
        } else {
            io.reactivex.rxjava3.operators.h hVar = this.queue;
            synchronized (hVar) {
                hVar.offer(t);
            }
            if (getAndIncrement() != 0) {
                return;
            }
        }
        b();
    }

    @Override // io.reactivex.rxjava3.core.ObservableEmitter
    public final ObservableEmitter serialize() {
        return this;
    }

    @Override // io.reactivex.rxjava3.core.ObservableEmitter
    public void setCancellable(io.reactivex.rxjava3.functions.f fVar) {
        this.emitter.setCancellable(fVar);
    }

    @Override // io.reactivex.rxjava3.core.ObservableEmitter
    public final void setDisposable(Disposable disposable) {
        this.emitter.setDisposable(disposable);
    }

    @Override // java.util.concurrent.atomic.AtomicInteger
    public final String toString() {
        return this.emitter.toString();
    }

    @Override // io.reactivex.rxjava3.core.ObservableEmitter
    public final boolean tryOnError(Throwable th) {
        if (!this.done && !this.emitter.isDisposed()) {
            if (th == null) {
                th = io.reactivex.rxjava3.internal.util.c.b("onError called with a null Throwable.");
            }
            AtomicThrowable atomicThrowable = this.errors;
            atomicThrowable.getClass();
            if (io.reactivex.rxjava3.internal.util.c.a(atomicThrowable, th)) {
                this.done = true;
                a();
                return true;
            }
        }
        return false;
    }
}
