package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;

/* JADX INFO: loaded from: classes.dex */
public final class b0 implements SingleObserver {
    public final /* synthetic */ SingleFlatMapNotification$FlatMapSingleObserver c;

    public b0(SingleFlatMapNotification$FlatMapSingleObserver singleFlatMapNotification$FlatMapSingleObserver) {
        this.c = singleFlatMapNotification$FlatMapSingleObserver;
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.c.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this.c, disposable);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        this.c.downstream.onSuccess(obj);
    }
}
