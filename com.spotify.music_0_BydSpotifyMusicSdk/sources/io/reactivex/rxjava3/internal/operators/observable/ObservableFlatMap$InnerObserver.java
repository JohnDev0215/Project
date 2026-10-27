package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableFlatMap$InnerObserver<T, U> extends AtomicReference<Disposable> implements Observer<U> {
    private static final long serialVersionUID = -4606175640614850599L;
    volatile boolean done;
    int fusionMode;
    final long id;
    final ObservableFlatMap$MergeObserver<T, U> parent;
    volatile io.reactivex.rxjava3.operators.g queue;

    public ObservableFlatMap$InnerObserver(ObservableFlatMap$MergeObserver observableFlatMap$MergeObserver, long j) {
        this.id = j;
        this.parent = observableFlatMap$MergeObserver;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.done = true;
        this.parent.c();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.parent.errors.a(th)) {
            ObservableFlatMap$MergeObserver<T, U> observableFlatMap$MergeObserver = this.parent;
            if (!observableFlatMap$MergeObserver.delayErrors) {
                observableFlatMap$MergeObserver.b();
            }
            this.done = true;
            this.parent.c();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.fusionMode != 0) {
            this.parent.c();
            return;
        }
        ObservableFlatMap$MergeObserver<T, U> observableFlatMap$MergeObserver = this.parent;
        if (observableFlatMap$MergeObserver.get() == 0 && observableFlatMap$MergeObserver.compareAndSet(0, 1)) {
            observableFlatMap$MergeObserver.downstream.onNext(obj);
            if (observableFlatMap$MergeObserver.decrementAndGet() == 0) {
                return;
            }
        } else {
            io.reactivex.rxjava3.operators.g hVar = this.queue;
            if (hVar == null) {
                hVar = new io.reactivex.rxjava3.operators.h(observableFlatMap$MergeObserver.bufferSize);
                this.queue = hVar;
            }
            hVar.offer(obj);
            if (observableFlatMap$MergeObserver.getAndIncrement() != 0) {
                return;
            }
        }
        observableFlatMap$MergeObserver.d();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.f(this, disposable) && (disposable instanceof io.reactivex.rxjava3.operators.b)) {
            io.reactivex.rxjava3.operators.b bVar = (io.reactivex.rxjava3.operators.b) disposable;
            int iB = bVar.b(7);
            if (iB == 1) {
                this.fusionMode = iB;
                this.queue = bVar;
                this.done = true;
                this.parent.c();
                return;
            }
            if (iB == 2) {
                this.fusionMode = iB;
                this.queue = bVar;
            }
        }
    }
}
