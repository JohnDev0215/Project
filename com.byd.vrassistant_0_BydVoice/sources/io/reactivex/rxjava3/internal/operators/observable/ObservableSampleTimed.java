package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.observers.SerializedObserver;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
public final class ObservableSampleTimed<T> extends AbstractObservableWithUpstream<T, T> {
    final boolean emitLast;
    final Consumer<? super T> onDropped;
    final long period;
    final Scheduler scheduler;
    final TimeUnit unit;

    public ObservableSampleTimed(ObservableSource<T> source, long period, TimeUnit unit, Scheduler scheduler, boolean emitLast, Consumer<? super T> onDropped) {
        super(source);
        this.period = period;
        this.unit = unit;
        this.scheduler = scheduler;
        this.emitLast = emitLast;
        this.onDropped = onDropped;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> t) {
        SerializedObserver serializedObserver = new SerializedObserver(t);
        if (this.emitLast) {
            this.source.subscribe(new SampleTimedEmitLast(serializedObserver, this.period, this.unit, this.scheduler, this.onDropped));
        } else {
            this.source.subscribe(new SampleTimedNoLast(serializedObserver, this.period, this.unit, this.scheduler, this.onDropped));
        }
    }

    static abstract class SampleTimedObserver<T> extends AtomicReference<T> implements Observer<T>, Disposable, Runnable {
        private static final long serialVersionUID = -3517602651313910099L;
        final Observer<? super T> downstream;
        final Consumer<? super T> onDropped;
        final long period;
        final Scheduler scheduler;
        final AtomicReference<Disposable> timer = new AtomicReference<>();
        final TimeUnit unit;
        Disposable upstream;

        abstract void complete();

        SampleTimedObserver(Observer<? super T> actual, long period, TimeUnit unit, Scheduler scheduler, Consumer<? super T> onDropped) {
            this.downstream = actual;
            this.period = period;
            this.unit = unit;
            this.scheduler = scheduler;
            this.onDropped = onDropped;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.upstream, d)) {
                this.upstream = d;
                this.downstream.onSubscribe(this);
                Scheduler scheduler = this.scheduler;
                long j = this.period;
                DisposableHelper.replace(this.timer, scheduler.schedulePeriodicallyDirect(this, j, j, this.unit));
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            Consumer<? super T> consumer;
            T andSet = getAndSet(t);
            if (andSet == null || (consumer = this.onDropped) == null) {
                return;
            }
            try {
                consumer.accept(andSet);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                cancelTimer();
                this.upstream.dispose();
                this.downstream.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable t) {
            cancelTimer();
            this.downstream.onError(t);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            cancelTimer();
            complete();
        }

        void cancelTimer() {
            DisposableHelper.dispose(this.timer);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            cancelTimer();
            this.upstream.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        void emit() {
            T andSet = getAndSet(null);
            if (andSet != null) {
                this.downstream.onNext(andSet);
            }
        }
    }

    static final class SampleTimedNoLast<T> extends SampleTimedObserver<T> {
        private static final long serialVersionUID = -7139995637533111443L;

        SampleTimedNoLast(Observer<? super T> actual, long period, TimeUnit unit, Scheduler scheduler, Consumer<? super T> onDropped) {
            super(actual, period, unit, scheduler, onDropped);
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableSampleTimed.SampleTimedObserver
        void complete() {
            this.downstream.onComplete();
        }

        @Override // java.lang.Runnable
        public void run() {
            emit();
        }
    }

    static final class SampleTimedEmitLast<T> extends SampleTimedObserver<T> {
        private static final long serialVersionUID = -7139995637533111443L;
        final AtomicInteger wip;

        SampleTimedEmitLast(Observer<? super T> actual, long period, TimeUnit unit, Scheduler scheduler, Consumer<? super T> onDropped) {
            super(actual, period, unit, scheduler, onDropped);
            this.wip = new AtomicInteger(1);
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableSampleTimed.SampleTimedObserver
        void complete() {
            emit();
            if (this.wip.decrementAndGet() == 0) {
                this.downstream.onComplete();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.wip.incrementAndGet() == 2) {
                emit();
                if (this.wip.decrementAndGet() == 0) {
                    this.downstream.onComplete();
                }
            }
        }
    }
}
