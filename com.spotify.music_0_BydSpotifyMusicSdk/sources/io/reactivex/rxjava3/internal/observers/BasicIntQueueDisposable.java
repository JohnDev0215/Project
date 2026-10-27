package io.reactivex.rxjava3.internal.observers;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class BasicIntQueueDisposable<T> extends AtomicInteger implements io.reactivex.rxjava3.operators.b {
    private static final long serialVersionUID = -1001730202384742097L;

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called");
    }
}
