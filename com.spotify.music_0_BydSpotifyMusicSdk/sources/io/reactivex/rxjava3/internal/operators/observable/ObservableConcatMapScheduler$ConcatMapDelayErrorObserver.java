package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableConcatMapScheduler$ConcatMapDelayErrorObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable, Runnable {
    private static final long serialVersionUID = -6951100001833242599L;
    volatile boolean active;
    final int bufferSize;
    volatile boolean cancelled;
    volatile boolean done;
    final Observer<? super R> downstream;
    final AtomicThrowable errors = new AtomicThrowable();
    final io.reactivex.rxjava3.functions.o mapper;
    final DelayErrorInnerObserver<R> observer;
    io.reactivex.rxjava3.operators.g queue;
    int sourceMode;
    final boolean tillTheEnd;
    Disposable upstream;
    final Scheduler.Worker worker;

    public static final class DelayErrorInnerObserver<R> extends AtomicReference<Disposable> implements Observer<R> {
        private static final long serialVersionUID = 2620149119579502636L;
        final Observer<? super R> downstream;
        final ObservableConcatMapScheduler$ConcatMapDelayErrorObserver<?, R> parent;

        public DelayErrorInnerObserver(Observer observer, ObservableConcatMapScheduler$ConcatMapDelayErrorObserver observableConcatMapScheduler$ConcatMapDelayErrorObserver) {
            this.downstream = observer;
            this.parent = observableConcatMapScheduler$ConcatMapDelayErrorObserver;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onComplete() {
            ObservableConcatMapScheduler$ConcatMapDelayErrorObserver<?, R> observableConcatMapScheduler$ConcatMapDelayErrorObserver = this.parent;
            observableConcatMapScheduler$ConcatMapDelayErrorObserver.active = false;
            observableConcatMapScheduler$ConcatMapDelayErrorObserver.a();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onError(Throwable th) {
            ObservableConcatMapScheduler$ConcatMapDelayErrorObserver<?, R> observableConcatMapScheduler$ConcatMapDelayErrorObserver = this.parent;
            if (observableConcatMapScheduler$ConcatMapDelayErrorObserver.errors.a(th)) {
                if (!observableConcatMapScheduler$ConcatMapDelayErrorObserver.tillTheEnd) {
                    observableConcatMapScheduler$ConcatMapDelayErrorObserver.upstream.dispose();
                }
                observableConcatMapScheduler$ConcatMapDelayErrorObserver.active = false;
                observableConcatMapScheduler$ConcatMapDelayErrorObserver.a();
            }
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

    public ObservableConcatMapScheduler$ConcatMapDelayErrorObserver(Observer observer, io.reactivex.rxjava3.functions.o oVar, int i, boolean z, Scheduler.Worker worker) {
        this.downstream = observer;
        this.mapper = oVar;
        this.bufferSize = i;
        this.tillTheEnd = z;
        this.observer = new DelayErrorInnerObserver<>(observer, this);
        this.worker = worker;
    }

    public final void a() {
        if (getAndIncrement() != 0) {
            return;
        }
        this.worker.a(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.cancelled = true;
        this.upstream.dispose();
        DelayErrorInnerObserver<R> delayErrorInnerObserver = this.observer;
        delayErrorInnerObserver.getClass();
        DisposableHelper.a(delayErrorInnerObserver);
        this.worker.dispose();
        this.errors.b();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.cancelled;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.done = true;
        a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.errors.a(th)) {
            this.done = true;
            a();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.sourceMode == 0) {
            this.queue.offer(obj);
        }
        a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            if (disposable instanceof io.reactivex.rxjava3.operators.b) {
                io.reactivex.rxjava3.operators.b bVar = (io.reactivex.rxjava3.operators.b) disposable;
                int iB = bVar.b(3);
                if (iB == 1) {
                    this.sourceMode = iB;
                    this.queue = bVar;
                    this.done = true;
                    this.downstream.onSubscribe(this);
                    a();
                    return;
                }
                if (iB == 2) {
                    this.sourceMode = iB;
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
        Observer<? super R> observer = this.downstream;
        io.reactivex.rxjava3.operators.g gVar = this.queue;
        AtomicThrowable atomicThrowable = this.errors;
        while (true) {
            if (!this.active) {
                if (this.cancelled) {
                    gVar.clear();
                    return;
                }
                if (!this.tillTheEnd && atomicThrowable.get() != null) {
                    gVar.clear();
                    this.cancelled = true;
                    atomicThrowable.d(observer);
                    this.worker.dispose();
                    return;
                }
                boolean z = this.done;
                try {
                    Object objPoll = gVar.poll();
                    boolean z2 = objPoll == null;
                    if (z && z2) {
                        this.cancelled = true;
                        atomicThrowable.d(observer);
                        this.worker.dispose();
                        return;
                    }
                    if (!z2) {
                        try {
                            Object objApply = this.mapper.apply(objPoll);
                            Objects.requireNonNull(objApply, "The mapper returned a null ObservableSource");
                            ObservableSource observableSource = (ObservableSource) objApply;
                            if (observableSource instanceof io.reactivex.rxjava3.functions.q) {
                                try {
                                    Object obj = ((io.reactivex.rxjava3.functions.q) observableSource).get();
                                    if (obj != null && !this.cancelled) {
                                        observer.onNext(obj);
                                    }
                                } catch (Throwable th) {
                                    he1.R(th);
                                    atomicThrowable.a(th);
                                }
                            } else {
                                this.active = true;
                                observableSource.subscribe(this.observer);
                            }
                        } catch (Throwable th2) {
                            he1.R(th2);
                            this.cancelled = true;
                            this.upstream.dispose();
                            gVar.clear();
                            atomicThrowable.a(th2);
                            atomicThrowable.d(observer);
                            this.worker.dispose();
                            return;
                        }
                    }
                } catch (Throwable th3) {
                    he1.R(th3);
                    this.cancelled = true;
                    this.upstream.dispose();
                    atomicThrowable.a(th3);
                    atomicThrowable.d(observer);
                    this.worker.dispose();
                    return;
                }
            }
            if (decrementAndGet() == 0) {
                return;
            }
        }
    }
}
