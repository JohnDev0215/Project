package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class v implements SingleObserver, Disposable {
    public final io.reactivex.rxjava3.functions.o c;
    public final SingleFlatMapBiSelector$FlatMapBiMainObserver$InnerObserver e;

    public v(SingleObserver singleObserver, io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.c cVar) {
        this.e = new SingleFlatMapBiSelector$FlatMapBiMainObserver$InnerObserver(singleObserver, cVar);
        this.c = oVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this.e);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(this.e.get());
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.e.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        SingleFlatMapBiSelector$FlatMapBiMainObserver$InnerObserver singleFlatMapBiSelector$FlatMapBiMainObserver$InnerObserver = this.e;
        if (DisposableHelper.f(singleFlatMapBiSelector$FlatMapBiMainObserver$InnerObserver, disposable)) {
            singleFlatMapBiSelector$FlatMapBiMainObserver$InnerObserver.downstream.onSubscribe(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        SingleFlatMapBiSelector$FlatMapBiMainObserver$InnerObserver singleFlatMapBiSelector$FlatMapBiMainObserver$InnerObserver = this.e;
        try {
            Object objApply = this.c.apply(obj);
            Objects.requireNonNull(objApply, "The mapper returned a null MaybeSource");
            SingleSource singleSource = (SingleSource) objApply;
            if (DisposableHelper.c(singleFlatMapBiSelector$FlatMapBiMainObserver$InnerObserver, null)) {
                singleFlatMapBiSelector$FlatMapBiMainObserver$InnerObserver.value = obj;
                singleSource.subscribe(singleFlatMapBiSelector$FlatMapBiMainObserver$InnerObserver);
            }
        } catch (Throwable th) {
            he1.R(th);
            singleFlatMapBiSelector$FlatMapBiMainObserver$InnerObserver.downstream.onError(th);
        }
    }
}
