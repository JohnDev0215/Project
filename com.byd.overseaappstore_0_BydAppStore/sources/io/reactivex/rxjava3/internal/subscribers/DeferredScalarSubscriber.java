package io.reactivex.rxjava3.internal.subscribers;

import io.reactivex.rxjava3.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import spi.os;
import spi.ps;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DeferredScalarSubscriber<T, R> extends DeferredScalarSubscription<R> implements os {
    private static final long serialVersionUID = 2984505488220891551L;
    public boolean hasValue;
    public ps upstream;

    public DeferredScalarSubscriber(os<? super R> osVar) {
        super(osVar);
    }

    @Override // io.reactivex.rxjava3.internal.subscriptions.DeferredScalarSubscription, io.reactivex.rxjava3.internal.subscriptions.BasicIntQueueSubscription, spi.ps
    public void cancel() {
        super.cancel();
        this.upstream.cancel();
    }

    @Override // spi.os
    public void onComplete() {
        if (this.hasValue) {
            complete(this.value);
        } else {
            this.downstream.onComplete();
        }
    }

    @Override // spi.os
    public void onError(Throwable th) {
        this.value = null;
        this.downstream.onError(th);
    }

    @Override // spi.os
    public abstract /* synthetic */ void onNext(T t);

    @Override // spi.os
    public void onSubscribe(ps psVar) {
        if (SubscriptionHelper.validate(this.upstream, psVar)) {
            this.upstream = psVar;
            this.downstream.onSubscribe(this);
            psVar.request(Long.MAX_VALUE);
        }
    }
}
