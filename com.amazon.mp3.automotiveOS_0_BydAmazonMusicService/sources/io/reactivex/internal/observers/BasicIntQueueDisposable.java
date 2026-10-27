package io.reactivex.internal.observers;

import io.reactivex.internal.fuseable.QueueDisposable;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes7.dex */
public abstract class BasicIntQueueDisposable<T> extends AtomicInteger implements QueueDisposable<T> {
    @Override // io.reactivex.internal.fuseable.SimpleQueue
    public final boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called");
    }
}
