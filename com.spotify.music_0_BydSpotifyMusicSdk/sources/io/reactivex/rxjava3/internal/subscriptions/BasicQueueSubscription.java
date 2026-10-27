package io.reactivex.rxjava3.internal.subscriptions;

import io.reactivex.rxjava3.operators.d;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public abstract class BasicQueueSubscription<T> extends AtomicLong implements d {
    private static final long serialVersionUID = -6671519529404341862L;

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
