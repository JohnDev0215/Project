package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableConcatMapScheduler$ConcatMapObserver<T, U> extends AtomicInteger implements Observer<T>, Disposable, Runnable {
    private static final long serialVersionUID = 8828587559905699186L;
    volatile boolean active;
    final int bufferSize;
    volatile boolean disposed;
    volatile boolean done;
    final Observer<? super U> downstream;
    int fusionMode;
    final InnerObserver<U> inner;
    final io.reactivex.rxjava3.functions.o mapper;
    io.reactivex.rxjava3.operators.g queue;
    Disposable upstream;
    final Scheduler.Worker worker;

    public static final class InnerObserver<U> extends AtomicReference<Disposable> implements Observer<U> {
        private static final long serialVersionUID = -7449079488798789337L;
        final Observer<? super U> downstream;
        final ObservableConcatMapScheduler$ConcatMapObserver<?, ?> parent;

        public InnerObserver(io.reactivex.rxjava3.observers.d dVar, ObservableConcatMapScheduler$ConcatMapObserver observableConcatMapScheduler$ConcatMapObserver) {
            this.downstream = dVar;
            this.parent = observableConcatMapScheduler$ConcatMapObserver;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onComplete() {
            ObservableConcatMapScheduler$ConcatMapObserver<?, ?> observableConcatMapScheduler$ConcatMapObserver = this.parent;
            observableConcatMapScheduler$ConcatMapObserver.active = false;
            if (observableConcatMapScheduler$ConcatMapObserver.getAndIncrement() != 0) {
                return;
            }
            observableConcatMapScheduler$ConcatMapObserver.worker.a(observableConcatMapScheduler$ConcatMapObserver);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onError(Throwable th) {
            this.parent.dispose();
            this.downstream.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onNext(Object obj) {
            this.downstream.onNext(obj);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onSubscribe(Disposable disposable) {
            DisposableHelper.c(this, disposable);
        }
    }

    public ObservableConcatMapScheduler$ConcatMapObserver(io.reactivex.rxjava3.observers.d dVar, io.reactivex.rxjava3.functions.o oVar, int i, Scheduler.Worker worker) {
        this.downstream = dVar;
        this.mapper = oVar;
        this.bufferSize = i;
        this.inner = new InnerObserver<>(dVar, this);
        this.worker = worker;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.disposed = true;
        InnerObserver<U> innerObserver = this.inner;
        innerObserver.getClass();
        DisposableHelper.a(innerObserver);
        this.upstream.dispose();
        this.worker.dispose();
        if (getAndIncrement() == 0) {
            this.queue.clear();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.disposed;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        if (getAndIncrement() != 0) {
            return;
        }
        this.worker.a(this);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.b(th);
            return;
        }
        this.done = true;
        dispose();
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.done) {
            return;
        }
        if (this.fusionMode == 0) {
            this.queue.offer(obj);
        }
        if (getAndIncrement() != 0) {
            return;
        }
        this.worker.a(this);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            if (disposable instanceof io.reactivex.rxjava3.operators.b) {
                io.reactivex.rxjava3.operators.b bVar = (io.reactivex.rxjava3.operators.b) disposable;
                int iB = bVar.b(3);
                if (iB == 1) {
                    this.fusionMode = iB;
                    this.queue = bVar;
                    this.done = true;
                    this.downstream.onSubscribe(this);
                    if (getAndIncrement() != 0) {
                        return;
                    }
                    this.worker.a(this);
                    return;
                }
                if (iB == 2) {
                    this.fusionMode = iB;
                    this.queue = bVar;
                    this.downstream.onSubscribe(this);
                    return;
                }
            }
            this.queue = new io.reactivex.rxjava3.operators.h(this.bufferSize);
            this.downstream.onSubscribe(this);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        while (!this.disposed) {
            if (!this.active) {
                boolean z = this.done;
                try {
                    Object objPoll = this.queue.poll();
                    boolean z2 = objPoll == null;
                    if (z && z2) {
                        this.disposed = true;
                        this.downstream.onComplete();
                        this.worker.dispose();
                        return;
                    } else if (!z2) {
                        try {
                            Object objApply = this.mapper.apply(objPoll);
                            Objects.requireNonNull(objApply, "The mapper returned a null ObservableSource");
                            ObservableSource observableSource = (ObservableSource) objApply;
                            this.active = true;
                            observableSource.subscribe(this.inner);
                        } catch (Throwable th) {
                            he1.R(th);
                            dispose();
                            this.queue.clear();
                            this.downstream.onError(th);
                            this.worker.dispose();
                            return;
                        }
                    }
                } catch (Throwable th2) {
                    he1.R(th2);
                    dispose();
                    this.queue.clear();
                    this.downstream.onError(th2);
                    this.worker.dispose();
                    return;
                }
            }
            if (decrementAndGet() == 0) {
                return;
            }
        }
        this.queue.clear();
    }
}
