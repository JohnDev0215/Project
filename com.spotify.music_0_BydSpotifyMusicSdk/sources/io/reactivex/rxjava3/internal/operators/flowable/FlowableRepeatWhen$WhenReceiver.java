package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p.az1;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableRepeatWhen$WhenReceiver<T, U> extends AtomicInteger implements FlowableSubscriber<Object>, tg2 {
    private static final long serialVersionUID = 2827772011130406689L;
    final az1 source;
    FlowableRepeatWhen$WhenSourceSubscriber<T, U> subscriber;
    final AtomicReference<tg2> upstream = new AtomicReference<>();
    final AtomicLong requested = new AtomicLong();

    public FlowableRepeatWhen$WhenReceiver(Flowable flowable) {
        this.source = flowable;
    }

    @Override // p.tg2
    public final void a(long j) {
        SubscriptionHelper.c(this.upstream, this.requested, j);
    }

    @Override // p.tg2
    public final void cancel() {
        SubscriptionHelper.b(this.upstream);
    }

    @Override // p.rg2
    public final void onComplete() {
        this.subscriber.cancel();
        this.subscriber.downstream.onComplete();
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        this.subscriber.cancel();
        this.subscriber.downstream.onError(th);
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (getAndIncrement() == 0) {
            while (this.upstream.get() != SubscriptionHelper.c) {
                this.source.subscribe(this.subscriber);
                if (decrementAndGet() == 0) {
                    return;
                }
            }
        }
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        SubscriptionHelper.d(this.upstream, this.requested, tg2Var);
    }
}
