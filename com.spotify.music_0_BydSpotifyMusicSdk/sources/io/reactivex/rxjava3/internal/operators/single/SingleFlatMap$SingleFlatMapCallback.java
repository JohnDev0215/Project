package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class SingleFlatMap$SingleFlatMapCallback<T, R> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable {
    private static final long serialVersionUID = 3258103020495908596L;
    final SingleObserver<? super R> downstream;
    final io.reactivex.rxjava3.functions.o mapper;

    public SingleFlatMap$SingleFlatMapCallback(SingleObserver singleObserver, io.reactivex.rxjava3.functions.o oVar) {
        this.downstream = singleObserver;
        this.mapper = oVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(get());
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.f(this, disposable)) {
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        try {
            Object objApply = this.mapper.apply(obj);
            Objects.requireNonNull(objApply, "The single returned by the mapper is null");
            SingleSource singleSource = (SingleSource) objApply;
            if (isDisposed()) {
                return;
            }
            singleSource.subscribe(new io.reactivex.rxjava3.internal.observers.l(this, this.downstream, 1));
        } catch (Throwable th) {
            he1.R(th);
            this.downstream.onError(th);
        }
    }
}
