package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.subscriptions.DeferredScalarSubscription;

/* JADX INFO: loaded from: classes.dex */
final class SingleToFlowable$SingleToFlowableObserver<T> extends DeferredScalarSubscription<T> implements SingleObserver<T> {
    private static final long serialVersionUID = 187782011903685568L;
    Disposable upstream;

    @Override // io.reactivex.rxjava3.internal.subscriptions.DeferredScalarSubscription, p.tg2
    public final void cancel() {
        super.cancel();
        this.upstream.dispose();
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }
}
