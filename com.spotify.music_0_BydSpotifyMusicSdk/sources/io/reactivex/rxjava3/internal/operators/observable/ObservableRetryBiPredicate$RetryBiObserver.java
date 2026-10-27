package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import p.he1;
import p.u11;

/* JADX INFO: loaded from: classes.dex */
final class ObservableRetryBiPredicate$RetryBiObserver<T> extends AtomicInteger implements Observer<T> {
    private static final long serialVersionUID = -7098360935104053232L;
    final Observer<? super T> downstream;
    final io.reactivex.rxjava3.functions.d predicate;
    int retries;
    final ObservableSource<? extends T> source;
    final SequentialDisposable upstream;

    public ObservableRetryBiPredicate$RetryBiObserver(Observer observer, io.reactivex.rxjava3.functions.d dVar, SequentialDisposable sequentialDisposable, ObservableSource observableSource) {
        this.downstream = observer;
        this.upstream = sequentialDisposable;
        this.source = observableSource;
        this.predicate = dVar;
    }

    public final void a() {
        if (getAndIncrement() == 0) {
            int iAddAndGet = 1;
            while (!this.upstream.isDisposed()) {
                this.source.subscribe(this);
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.downstream.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        try {
            io.reactivex.rxjava3.functions.d dVar = this.predicate;
            int i = this.retries + 1;
            this.retries = i;
            Integer numValueOf = Integer.valueOf(i);
            ((u11) dVar).getClass();
            if (Objects.equals(numValueOf, th)) {
                a();
            } else {
                this.downstream.onError(th);
            }
        } catch (Throwable th2) {
            he1.R(th2);
            this.downstream.onError(new CompositeException(th, th2));
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        this.downstream.onNext(obj);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        SequentialDisposable sequentialDisposable = this.upstream;
        sequentialDisposable.getClass();
        DisposableHelper.c(sequentialDisposable, disposable);
    }
}
