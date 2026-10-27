package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.rxjava3.processors.FlowableProcessor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* JADX INFO: loaded from: classes7.dex */
abstract class FlowableRepeatWhen$WhenSourceSubscriber<T, U> extends SubscriptionArbiter implements FlowableSubscriber<T> {
    protected final Subscriber<? super T> downstream;
    protected final FlowableProcessor<U> processor;
    private long produced;
    protected final Subscription receiver;

    FlowableRepeatWhen$WhenSourceSubscriber(Subscriber<? super T> actual, FlowableProcessor<U> processor, Subscription receiver) {
        super(false);
        this.downstream = actual;
        this.processor = processor;
        this.receiver = receiver;
    }

    @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
    public final void onSubscribe(Subscription s) {
        setSubscription(s);
    }

    @Override // org.reactivestreams.Subscriber
    public final void onNext(T t) {
        this.produced++;
        this.downstream.onNext(t);
    }

    protected final void again(U signal) {
        setSubscription(EmptySubscription.INSTANCE);
        long j = this.produced;
        if (j != 0) {
            this.produced = 0L;
            produced(j);
        }
        this.receiver.request(1L);
        this.processor.onNext(signal);
    }

    @Override // io.reactivex.rxjava3.internal.subscriptions.SubscriptionArbiter, org.reactivestreams.Subscription
    public final void cancel() {
        super.cancel();
        this.receiver.cancel();
    }
}
