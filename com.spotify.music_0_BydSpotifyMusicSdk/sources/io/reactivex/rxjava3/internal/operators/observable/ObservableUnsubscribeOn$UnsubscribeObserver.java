package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
final class ObservableUnsubscribeOn$UnsubscribeObserver<T> extends AtomicBoolean implements Observer<T>, Disposable {
    private static final long serialVersionUID = 1015244841293359600L;
    final Observer<? super T> downstream;
    final Scheduler scheduler;
    Disposable upstream;

    public ObservableUnsubscribeOn$UnsubscribeObserver(Observer observer, Scheduler scheduler) {
        this.downstream = observer;
        this.scheduler = scheduler;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (compareAndSet(false, true)) {
            this.scheduler.c(new h5(0, this));
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (get()) {
            return;
        }
        this.downstream.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (get()) {
            RxJavaPlugins.b(th);
        } else {
            this.downstream.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (get()) {
            return;
        }
        this.downstream.onNext(obj);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }
}
