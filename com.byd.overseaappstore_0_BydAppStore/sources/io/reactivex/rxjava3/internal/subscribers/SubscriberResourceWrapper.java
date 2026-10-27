package io.reactivex.rxjava3.internal.subscribers;

import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicReference;
import spi.os;
import spi.ps;
import spi.q6;

/* JADX INFO: loaded from: classes2.dex */
public final class SubscriberResourceWrapper<T> extends AtomicReference<q6> implements os, q6, ps {
    private static final long serialVersionUID = -8612022020200669122L;
    public final os<? super T> downstream;
    public final AtomicReference<ps> upstream = new AtomicReference<>();

    public SubscriberResourceWrapper(os<? super T> osVar) {
        this.downstream = osVar;
    }

    @Override // spi.ps
    public void cancel() {
        dispose();
    }

    @Override // spi.q6
    public void dispose() {
        SubscriptionHelper.cancel(this.upstream);
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return this.upstream.get() == SubscriptionHelper.CANCELLED;
    }

    @Override // spi.os
    public void onComplete() {
        DisposableHelper.dispose(this);
        this.downstream.onComplete();
    }

    @Override // spi.os
    public void onError(Throwable th) {
        DisposableHelper.dispose(this);
        this.downstream.onError(th);
    }

    @Override // spi.os
    public void onNext(T t) {
        this.downstream.onNext(t);
    }

    @Override // spi.os
    public void onSubscribe(ps psVar) {
        if (SubscriptionHelper.setOnce(this.upstream, psVar)) {
            this.downstream.onSubscribe(this);
        }
    }

    @Override // spi.ps
    public void request(long j) {
        if (SubscriptionHelper.validate(j)) {
            this.upstream.get().request(j);
        }
    }

    public void setResource(q6 q6Var) {
        DisposableHelper.set(this, q6Var);
    }
}
