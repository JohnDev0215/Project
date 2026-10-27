package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableWithLatestFrom$WithLatestFromObserver<T, U, R> extends AtomicReference<U> implements Observer<T>, Disposable {
    private static final long serialVersionUID = -312246233408980075L;
    final io.reactivex.rxjava3.functions.c combiner;
    final Observer<? super R> downstream;
    final AtomicReference<Disposable> upstream = new AtomicReference<>();
    final AtomicReference<Disposable> other = new AtomicReference<>();

    public ObservableWithLatestFrom$WithLatestFromObserver(io.reactivex.rxjava3.observers.d dVar, io.reactivex.rxjava3.functions.c cVar) {
        this.downstream = dVar;
        this.combiner = cVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this.upstream);
        DisposableHelper.a(this.other);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(this.upstream.get());
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        DisposableHelper.a(this.other);
        this.downstream.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        DisposableHelper.a(this.other);
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        U u = get();
        if (u != null) {
            try {
                Object objK = this.combiner.k(obj, u);
                Objects.requireNonNull(objK, "The combiner returned a null value");
                this.downstream.onNext(objK);
            } catch (Throwable th) {
                he1.R(th);
                dispose();
                this.downstream.onError(th);
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this.upstream, disposable);
    }
}
