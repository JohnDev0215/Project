package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableTimeout$TimeoutObserver<T> extends AtomicLong implements Observer<T>, Disposable, b5 {
    private static final long serialVersionUID = 3764492702657003550L;
    final Observer<? super T> downstream;
    final io.reactivex.rxjava3.functions.o itemTimeoutIndicator;
    final SequentialDisposable task = new SequentialDisposable();
    final AtomicReference<Disposable> upstream = new AtomicReference<>();

    public ObservableTimeout$TimeoutObserver(Observer observer, io.reactivex.rxjava3.functions.o oVar) {
        this.downstream = observer;
        this.itemTimeoutIndicator = oVar;
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.b5
    public final void a(long j, Throwable th) {
        if (!compareAndSet(j, Long.MAX_VALUE)) {
            RxJavaPlugins.b(th);
        } else {
            DisposableHelper.a(this.upstream);
            this.downstream.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.c5
    public final void b(long j) {
        if (compareAndSet(j, Long.MAX_VALUE)) {
            DisposableHelper.a(this.upstream);
            this.downstream.onError(new TimeoutException());
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this.upstream);
        this.task.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(this.upstream.get());
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
            this.task.dispose();
            this.downstream.onComplete();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (getAndSet(Long.MAX_VALUE) == Long.MAX_VALUE) {
            RxJavaPlugins.b(th);
        } else {
            this.task.dispose();
            this.downstream.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        long j = get();
        if (j != Long.MAX_VALUE) {
            long j2 = 1 + j;
            if (compareAndSet(j, j2)) {
                Disposable disposable = this.task.get();
                if (disposable != null) {
                    disposable.dispose();
                }
                this.downstream.onNext(obj);
                try {
                    Object objApply = this.itemTimeoutIndicator.apply(obj);
                    Objects.requireNonNull(objApply, "The itemTimeoutIndicator returned a null ObservableSource.");
                    ObservableSource observableSource = (ObservableSource) objApply;
                    ObservableTimeout$TimeoutConsumer observableTimeout$TimeoutConsumer = new ObservableTimeout$TimeoutConsumer(j2, this);
                    SequentialDisposable sequentialDisposable = this.task;
                    sequentialDisposable.getClass();
                    if (DisposableHelper.c(sequentialDisposable, observableTimeout$TimeoutConsumer)) {
                        observableSource.subscribe(observableTimeout$TimeoutConsumer);
                    }
                } catch (Throwable th) {
                    he1.R(th);
                    this.upstream.get().dispose();
                    getAndSet(Long.MAX_VALUE);
                    this.downstream.onError(th);
                }
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this.upstream, disposable);
    }
}
