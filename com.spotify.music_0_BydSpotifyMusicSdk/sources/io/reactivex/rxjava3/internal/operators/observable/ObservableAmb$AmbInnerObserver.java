package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableAmb$AmbInnerObserver<T> extends AtomicReference<Disposable> implements Observer<T> {
    private static final long serialVersionUID = -1185974347409665484L;
    final Observer<? super T> downstream;
    final int index;
    final n parent;
    boolean won;

    public ObservableAmb$AmbInnerObserver(n nVar, int i, Observer observer) {
        this.parent = nVar;
        this.index = i;
        this.downstream = observer;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.won) {
            this.downstream.onComplete();
        } else if (this.parent.a(this.index)) {
            this.won = true;
            this.downstream.onComplete();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.won) {
            this.downstream.onError(th);
        } else if (!this.parent.a(this.index)) {
            RxJavaPlugins.b(th);
        } else {
            this.won = true;
            this.downstream.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.won) {
            this.downstream.onNext(obj);
        } else if (!this.parent.a(this.index)) {
            get().dispose();
        } else {
            this.won = true;
            this.downstream.onNext(obj);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this, disposable);
    }
}
