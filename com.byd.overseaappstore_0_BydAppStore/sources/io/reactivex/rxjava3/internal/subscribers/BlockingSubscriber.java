package io.reactivex.rxjava3.internal.subscribers;

import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;
import spi.os;
import spi.ps;

/* JADX INFO: loaded from: classes2.dex */
public final class BlockingSubscriber<T> extends AtomicReference<ps> implements os, ps {
    public static final Object TERMINATED = new Object();
    private static final long serialVersionUID = -4875965440900746268L;
    public final Queue<Object> queue;

    public BlockingSubscriber(Queue<Object> queue) {
        this.queue = queue;
    }

    @Override // spi.ps
    public void cancel() {
        if (SubscriptionHelper.cancel(this)) {
            this.queue.offer(TERMINATED);
        }
    }

    public boolean isCancelled() {
        return get() == SubscriptionHelper.CANCELLED;
    }

    @Override // spi.os
    public void onComplete() {
        this.queue.offer(NotificationLite.complete());
    }

    @Override // spi.os
    public void onError(Throwable th) {
        this.queue.offer(NotificationLite.error(th));
    }

    @Override // spi.os
    public void onNext(T t) {
        this.queue.offer(NotificationLite.next(t));
    }

    @Override // spi.os
    public void onSubscribe(ps psVar) {
        if (SubscriptionHelper.setOnce(this, psVar)) {
            this.queue.offer(NotificationLite.subscription(this));
        }
    }

    @Override // spi.ps
    public void request(long j) {
        get().request(j);
    }
}
