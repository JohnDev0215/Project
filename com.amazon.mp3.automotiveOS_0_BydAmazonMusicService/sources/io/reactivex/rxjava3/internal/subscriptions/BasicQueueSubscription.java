package io.reactivex.rxjava3.internal.subscriptions;

import io.reactivex.rxjava3.operators.QueueSubscription;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes7.dex */
public abstract class BasicQueueSubscription<T> extends AtomicLong implements QueueSubscription<T> {
    @Override // io.reactivex.rxjava3.operators.SimpleQueue
    public final boolean offer(T e) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
