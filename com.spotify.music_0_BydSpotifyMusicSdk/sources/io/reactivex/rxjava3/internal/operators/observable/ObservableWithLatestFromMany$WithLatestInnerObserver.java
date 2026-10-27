package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;
import p.ux2;

/* JADX INFO: loaded from: classes.dex */
final class ObservableWithLatestFromMany$WithLatestInnerObserver extends AtomicReference<Disposable> implements Observer<Object> {
    private static final long serialVersionUID = 3256684027868224024L;
    boolean hasValue;
    final int index;
    final ObservableWithLatestFromMany$WithLatestFromObserver<?, ?> parent;

    public ObservableWithLatestFromMany$WithLatestInnerObserver(ObservableWithLatestFromMany$WithLatestFromObserver observableWithLatestFromMany$WithLatestFromObserver, int i) {
        this.parent = observableWithLatestFromMany$WithLatestFromObserver;
        this.index = i;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        ObservableWithLatestFromMany$WithLatestFromObserver<?, ?> observableWithLatestFromMany$WithLatestFromObserver = this.parent;
        int i = this.index;
        if (this.hasValue) {
            observableWithLatestFromMany$WithLatestFromObserver.getClass();
            return;
        }
        observableWithLatestFromMany$WithLatestFromObserver.done = true;
        observableWithLatestFromMany$WithLatestFromObserver.a(i);
        ux2.t(observableWithLatestFromMany$WithLatestFromObserver.downstream, observableWithLatestFromMany$WithLatestFromObserver, observableWithLatestFromMany$WithLatestFromObserver.error);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        ObservableWithLatestFromMany$WithLatestFromObserver<?, ?> observableWithLatestFromMany$WithLatestFromObserver = this.parent;
        int i = this.index;
        observableWithLatestFromMany$WithLatestFromObserver.done = true;
        DisposableHelper.a(observableWithLatestFromMany$WithLatestFromObserver.upstream);
        observableWithLatestFromMany$WithLatestFromObserver.a(i);
        ux2.u(observableWithLatestFromMany$WithLatestFromObserver.downstream, th, observableWithLatestFromMany$WithLatestFromObserver, observableWithLatestFromMany$WithLatestFromObserver.error);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (!this.hasValue) {
            this.hasValue = true;
        }
        ObservableWithLatestFromMany$WithLatestFromObserver<?, ?> observableWithLatestFromMany$WithLatestFromObserver = this.parent;
        observableWithLatestFromMany$WithLatestFromObserver.values.set(this.index, obj);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this, disposable);
    }
}
