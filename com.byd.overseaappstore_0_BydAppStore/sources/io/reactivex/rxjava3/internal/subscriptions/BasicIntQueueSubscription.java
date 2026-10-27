package io.reactivex.rxjava3.internal.subscriptions;

import java.util.concurrent.atomic.AtomicInteger;
import spi.sl;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BasicIntQueueSubscription<T> extends AtomicInteger implements sl<T> {
    private static final long serialVersionUID = -6671519529404341862L;

    public abstract /* synthetic */ void cancel();

    @Override // spi.kp
    public abstract /* synthetic */ void clear();

    public abstract /* synthetic */ boolean isEmpty();

    @Override // spi.kp
    public final boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public final boolean offer(T t, T t2) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public abstract /* synthetic */ T poll() throws Throwable;

    @Override // spi.ps
    public abstract /* synthetic */ void request(long j);

    @Override // spi.rl
    public abstract /* synthetic */ int requestFusion(int i);
}
