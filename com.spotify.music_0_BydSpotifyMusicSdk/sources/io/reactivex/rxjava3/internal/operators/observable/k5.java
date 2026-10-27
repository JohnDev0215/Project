package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: classes.dex */
public final class k5 extends io.reactivex.rxjava3.observers.a {
    public final ObservableWindowBoundary$WindowBoundaryMainObserver e;
    public boolean k;

    public k5(ObservableWindowBoundary$WindowBoundaryMainObserver observableWindowBoundary$WindowBoundaryMainObserver) {
        this.e = observableWindowBoundary$WindowBoundaryMainObserver;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.k) {
            return;
        }
        this.k = true;
        ObservableWindowBoundary$WindowBoundaryMainObserver observableWindowBoundary$WindowBoundaryMainObserver = this.e;
        DisposableHelper.a(observableWindowBoundary$WindowBoundaryMainObserver.upstream);
        observableWindowBoundary$WindowBoundaryMainObserver.done = true;
        observableWindowBoundary$WindowBoundaryMainObserver.a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.k) {
            RxJavaPlugins.b(th);
            return;
        }
        this.k = true;
        ObservableWindowBoundary$WindowBoundaryMainObserver observableWindowBoundary$WindowBoundaryMainObserver = this.e;
        DisposableHelper.a(observableWindowBoundary$WindowBoundaryMainObserver.upstream);
        if (observableWindowBoundary$WindowBoundaryMainObserver.errors.a(th)) {
            observableWindowBoundary$WindowBoundaryMainObserver.done = true;
            observableWindowBoundary$WindowBoundaryMainObserver.a();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.k) {
            return;
        }
        this.e.b();
    }
}
