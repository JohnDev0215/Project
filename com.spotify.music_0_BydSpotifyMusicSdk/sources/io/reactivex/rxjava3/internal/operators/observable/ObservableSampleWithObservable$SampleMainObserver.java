package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
abstract class ObservableSampleWithObservable$SampleMainObserver<T> extends AtomicReference<T> implements Observer<T>, Disposable {
    private static final long serialVersionUID = -3517602651313910099L;
    final Observer<? super T> downstream;
    final AtomicReference<Disposable> other = new AtomicReference<>();
    final ObservableSource<?> sampler;
    Disposable upstream;

    public ObservableSampleWithObservable$SampleMainObserver(ObservableSource observableSource, io.reactivex.rxjava3.observers.d dVar) {
        this.downstream = dVar;
        this.sampler = observableSource;
    }

    public abstract void a();

    public abstract void b();

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this.other);
        this.upstream.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.other.get() == DisposableHelper.c;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        DisposableHelper.a(this.other);
        a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        DisposableHelper.a(this.other);
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        lazySet(obj);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
            if (this.other.get() == null) {
                this.sampler.subscribe(new z3(this, 0));
            }
        }
    }
}
