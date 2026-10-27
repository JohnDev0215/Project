package io.reactivex.rxjava3.internal.subscriptions;

import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import spi.ps;
import spi.q6;

/* JADX INFO: loaded from: classes2.dex */
public final class AsyncSubscription extends AtomicLong implements ps, q6 {
    private static final long serialVersionUID = 7028635084060361255L;
    public final AtomicReference<ps> actual;
    public final AtomicReference<q6> resource;

    public AsyncSubscription() {
        this.resource = new AtomicReference<>();
        this.actual = new AtomicReference<>();
    }

    public AsyncSubscription(q6 q6Var) {
        this();
        this.resource.lazySet(q6Var);
    }

    @Override // spi.ps
    public void cancel() {
        dispose();
    }

    @Override // spi.q6
    public void dispose() {
        SubscriptionHelper.cancel(this.actual);
        DisposableHelper.dispose(this.resource);
    }

    public boolean isDisposed() {
        return this.actual.get() == SubscriptionHelper.CANCELLED;
    }

    public boolean replaceResource(q6 q6Var) {
        return DisposableHelper.replace(this.resource, q6Var);
    }

    @Override // spi.ps
    public void request(long j) {
        SubscriptionHelper.deferredRequest(this.actual, this, j);
    }

    public boolean setResource(q6 q6Var) {
        return DisposableHelper.set(this.resource, q6Var);
    }

    public void setSubscription(ps psVar) {
        SubscriptionHelper.deferredSetOnce(this.actual, this, psVar);
    }
}
