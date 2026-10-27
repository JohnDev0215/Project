package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

/* JADX INFO: loaded from: classes7.dex */
final class FlowableRepeatWhen$WhenReceiver<T, U> extends AtomicInteger implements FlowableSubscriber<Object>, Subscription {
    final Publisher<T> source;
    FlowableRepeatWhen$WhenSourceSubscriber<T, U> subscriber;
    final AtomicReference<Subscription> upstream = new AtomicReference<>();
    final AtomicLong requested = new AtomicLong();

    FlowableRepeatWhen$WhenReceiver(Publisher<T> source) {
        this.source = source;
    }

    @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription s) {
        SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, s);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (getAndIncrement() == 0) {
            while (this.upstream.get() != SubscriptionHelper.CANCELLED) {
                this.source.subscribe(this.subscriber);
                if (decrementAndGet() == 0) {
                    return;
                }
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable t) {
        this.subscriber.cancel();
        this.subscriber.downstream.onError(t);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.subscriber.cancel();
        this.subscriber.downstream.onComplete();
    }

    @Override // org.reactivestreams.Subscription
    public void request(long n) {
        SubscriptionHelper.deferredRequest(this.upstream, this.requested, n);
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        SubscriptionHelper.cancel(this.upstream);
    }
}
