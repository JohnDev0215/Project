package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.operators.QueueDisposable;

/* JADX INFO: loaded from: classes7.dex */
public abstract class BasicQueueDisposable<T> implements QueueDisposable<T> {
    @Override // io.reactivex.rxjava3.operators.SimpleQueue
    public final boolean offer(T e) {
        throw new UnsupportedOperationException("Should not be called");
    }
}
