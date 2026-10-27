package io.reactivex.rxjava3.internal.observers;

import java.util.concurrent.atomic.AtomicInteger;
import spi.ql;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BasicIntQueueDisposable<T> extends AtomicInteger implements ql<T> {
    private static final long serialVersionUID = -1001730202384742097L;

    @Override // spi.kp
    public abstract /* synthetic */ void clear();

    @Override // spi.q6
    public abstract /* synthetic */ void dispose();

    public abstract /* synthetic */ boolean isDisposed();

    public abstract /* synthetic */ boolean isEmpty();

    @Override // spi.kp
    public final boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called");
    }

    public final boolean offer(T t, T t2) {
        throw new UnsupportedOperationException("Should not be called");
    }

    public abstract /* synthetic */ T poll() throws Throwable;

    @Override // spi.rl
    public abstract /* synthetic */ int requestFusion(int i);
}
