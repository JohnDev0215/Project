package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
public final class ObservableThrottleLatest<T> extends AbstractObservableWithUpstream<T, T> {
    final boolean emitLast;
    final Consumer<? super T> onDropped;
    final Scheduler scheduler;
    final long timeout;
    final TimeUnit unit;

    public ObservableThrottleLatest(Observable<T> source, long timeout, TimeUnit unit, Scheduler scheduler, boolean emitLast, Consumer<? super T> onDropped) {
        super(source);
        this.timeout = timeout;
        this.unit = unit;
        this.scheduler = scheduler;
        this.emitLast = emitLast;
        this.onDropped = onDropped;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    protected void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new ThrottleLatestObserver(observer, this.timeout, this.unit, this.scheduler.createWorker(), this.emitLast, this.onDropped));
    }

    static final class ThrottleLatestObserver<T> extends AtomicInteger implements Observer<T>, Disposable, Runnable {
        private static final long serialVersionUID = -8296689127439125014L;
        volatile boolean cancelled;
        volatile boolean done;
        final Observer<? super T> downstream;
        final boolean emitLast;
        Throwable error;
        final AtomicReference<T> latest = new AtomicReference<>();
        final Consumer<? super T> onDropped;
        final long timeout;
        volatile boolean timerFired;
        boolean timerRunning;
        final TimeUnit unit;
        Disposable upstream;
        final Scheduler.Worker worker;

        ThrottleLatestObserver(Observer<? super T> downstream, long timeout, TimeUnit unit, Scheduler.Worker worker, boolean emitLast, Consumer<? super T> onDropped) {
            this.downstream = downstream;
            this.timeout = timeout;
            this.unit = unit;
            this.worker = worker;
            this.emitLast = emitLast;
            this.onDropped = onDropped;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.upstream, d)) {
                this.upstream = d;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            T andSet = this.latest.getAndSet(t);
            Consumer<? super T> consumer = this.onDropped;
            if (consumer != null && andSet != null) {
                try {
                    consumer.accept(andSet);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.upstream.dispose();
                    this.error = th;
                    this.done = true;
                }
            }
            drain();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable t) {
            this.error = t;
            this.done = true;
            drain();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.cancelled = true;
            this.upstream.dispose();
            this.worker.dispose();
            if (getAndIncrement() == 0) {
                clear();
            }
        }

        void clear() {
            if (this.onDropped != null) {
                T andSet = this.latest.getAndSet(null);
                if (andSet != null) {
                    try {
                        this.onDropped.accept(andSet);
                        return;
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        RxJavaPlugins.onError(th);
                        return;
                    }
                }
                return;
            }
            this.latest.lazySet(null);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.timerFired = true;
            drain();
        }

        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            AtomicReference<T> atomicReference = this.latest;
            Observer<? super T> observer = this.downstream;
            int iAddAndGet = 1;
            while (!this.cancelled) {
                boolean z = this.done;
                Throwable compositeException = this.error;
                if (z && compositeException != null) {
                    if (this.onDropped != null) {
                        T andSet = atomicReference.getAndSet(null);
                        if (andSet != null) {
                            try {
                                this.onDropped.accept(andSet);
                            } catch (Throwable th) {
                                Exceptions.throwIfFatal(th);
                                compositeException = new CompositeException(compositeException, th);
                            }
                        }
                    } else {
                        atomicReference.lazySet(null);
                    }
                    observer.onError(compositeException);
                    this.worker.dispose();
                    return;
                }
                boolean z2 = atomicReference.get() == null;
                if (z) {
                    if (!z2) {
                        T andSet2 = atomicReference.getAndSet(null);
                        if (this.emitLast) {
                            observer.onNext(andSet2);
                        } else {
                            Consumer<? super T> consumer = this.onDropped;
                            if (consumer != null) {
                                try {
                                    consumer.accept(andSet2);
                                } catch (Throwable th2) {
                                    Exceptions.throwIfFatal(th2);
                                    observer.onError(th2);
                                    this.worker.dispose();
                                    return;
                                }
                            }
                        }
                    }
                    observer.onComplete();
                    this.worker.dispose();
                    return;
                }
                if (z2) {
                    if (this.timerFired) {
                        this.timerRunning = false;
                        this.timerFired = false;
                    }
                } else if (!this.timerRunning || this.timerFired) {
                    observer.onNext(atomicReference.getAndSet(null));
                    this.timerFired = false;
                    this.timerRunning = true;
                    this.worker.schedule(this, this.timeout, this.unit);
                }
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
            clear();
        }
    }
}
