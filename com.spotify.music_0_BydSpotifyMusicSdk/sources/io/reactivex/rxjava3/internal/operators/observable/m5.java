package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class m5 extends Observable implements Observer, Disposable {
    public final ObservableWindowBoundarySelector$WindowBoundaryMainObserver c;
    public final io.reactivex.rxjava3.subjects.f e;
    public final AtomicReference k = new AtomicReference();
    public final AtomicBoolean l = new AtomicBoolean();

    public m5(ObservableWindowBoundarySelector$WindowBoundaryMainObserver observableWindowBoundarySelector$WindowBoundaryMainObserver, io.reactivex.rxjava3.subjects.f fVar) {
        this.c = observableWindowBoundarySelector$WindowBoundaryMainObserver;
        this.e = fVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this.k);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.k.get() == DisposableHelper.c;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        ObservableWindowBoundarySelector$WindowBoundaryMainObserver observableWindowBoundarySelector$WindowBoundaryMainObserver = this.c;
        observableWindowBoundarySelector$WindowBoundaryMainObserver.queue.offer(this);
        observableWindowBoundarySelector$WindowBoundaryMainObserver.a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (isDisposed()) {
            RxJavaPlugins.b(th);
            return;
        }
        ObservableWindowBoundarySelector$WindowBoundaryMainObserver observableWindowBoundarySelector$WindowBoundaryMainObserver = this.c;
        observableWindowBoundarySelector$WindowBoundaryMainObserver.upstream.dispose();
        AtomicReference atomicReference = observableWindowBoundarySelector$WindowBoundaryMainObserver.startObserver;
        atomicReference.getClass();
        DisposableHelper.a(atomicReference);
        observableWindowBoundarySelector$WindowBoundaryMainObserver.resources.dispose();
        if (observableWindowBoundarySelector$WindowBoundaryMainObserver.error.a(th)) {
            observableWindowBoundarySelector$WindowBoundaryMainObserver.upstreamDone = true;
            observableWindowBoundarySelector$WindowBoundaryMainObserver.a();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (DisposableHelper.a(this.k)) {
            ObservableWindowBoundarySelector$WindowBoundaryMainObserver observableWindowBoundarySelector$WindowBoundaryMainObserver = this.c;
            observableWindowBoundarySelector$WindowBoundaryMainObserver.queue.offer(this);
            observableWindowBoundarySelector$WindowBoundaryMainObserver.a();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this.k, disposable);
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        this.e.subscribe(observer);
        this.l.set(true);
    }
}
