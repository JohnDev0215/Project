package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class SingleFlatMapBiSelector$FlatMapBiMainObserver$InnerObserver<T, U, R> extends AtomicReference<Disposable> implements SingleObserver<U> {
    private static final long serialVersionUID = -2897979525538174559L;
    final SingleObserver<? super R> downstream;
    final io.reactivex.rxjava3.functions.c resultSelector;
    T value;

    public SingleFlatMapBiSelector$FlatMapBiMainObserver$InnerObserver(SingleObserver singleObserver, io.reactivex.rxjava3.functions.c cVar) {
        this.downstream = singleObserver;
        this.resultSelector = cVar;
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this, disposable);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        T t = this.value;
        this.value = null;
        try {
            Object objK = this.resultSelector.k(t, obj);
            Objects.requireNonNull(objK, "The resultSelector returned a null value");
            this.downstream.onSuccess(objK);
        } catch (Throwable th) {
            he1.R(th);
            this.downstream.onError(th);
        }
    }
}
