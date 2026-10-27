package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import java.util.concurrent.atomic.AtomicInteger;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableRepeatUntil$RepeatUntilObserver<T> extends AtomicInteger implements Observer<T> {
    private static final long serialVersionUID = -7098360935104053232L;
    final Observer<? super T> downstream;
    final ObservableSource<? extends T> source;
    final io.reactivex.rxjava3.functions.e stop;
    final SequentialDisposable upstream;

    public ObservableRepeatUntil$RepeatUntilObserver(Observer observer, SequentialDisposable sequentialDisposable, ObservableSource observableSource) {
        this.downstream = observer;
        this.upstream = sequentialDisposable;
        this.source = observableSource;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        try {
            throw null;
        } catch (Throwable th) {
            he1.R(th);
            this.downstream.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.downstream.onError(th);
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
