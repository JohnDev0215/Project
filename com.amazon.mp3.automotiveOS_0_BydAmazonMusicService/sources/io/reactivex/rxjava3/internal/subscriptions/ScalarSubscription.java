package io.reactivex.rxjava3.internal.subscriptions;

import io.reactivex.rxjava3.operators.QueueSubscription;
import java.util.concurrent.atomic.AtomicInteger;
import org.reactivestreams.Subscriber;

/* JADX INFO: loaded from: classes7.dex */
public final class ScalarSubscription<T> extends AtomicInteger implements QueueSubscription<T> {
    final Subscriber<? super T> subscriber;
    final T value;

    @Override // io.reactivex.rxjava3.operators.QueueFuseable
    public int requestFusion(int mode) {
        return mode & 1;
    }

    public ScalarSubscription(Subscriber<? super T> subscriber, T value) {
        this.subscriber = subscriber;
        this.value = value;
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j) {
        if (SubscriptionHelper.validate(j) && compareAndSet(0, 1)) {
            Subscriber<? super T> subscriber = this.subscriber;
            subscriber.onNext(this.value);
            if (get() != 2) {
                subscriber.onComplete();
            }
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        lazySet(2);
    }

    @Override // io.reactivex.rxjava3.operators.SimpleQueue
    public boolean offer(T e) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // io.reactivex.rxjava3.operators.SimpleQueue
    public T poll() {
        if (get() != 0) {
            return null;
        }
        lazySet(1);
        return this.value;
    }

    @Override // io.reactivex.rxjava3.operators.SimpleQueue
    public boolean isEmpty() {
        return get() != 0;
    }

    @Override // io.reactivex.rxjava3.operators.SimpleQueue
    public void clear() {
        lazySet(1);
    }
}
