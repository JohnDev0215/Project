package io.reactivex.internal.observers;

import d.a.p.c.a;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class BasicIntQueueDisposable<T> extends AtomicInteger implements a<T> {
    private static final long serialVersionUID = -1001730202384742097L;

    @Override // d.a.p.c.e
    public abstract /* synthetic */ void clear();

    @Override // d.a.m.b
    public abstract /* synthetic */ void dispose();

    @Override // d.a.m.b
    public abstract /* synthetic */ boolean isDisposed();

    @Override // d.a.p.c.e
    public abstract /* synthetic */ boolean isEmpty();

    @Override // d.a.p.c.e
    public final boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called");
    }

    @Override // d.a.p.c.e
    public abstract /* synthetic */ T poll() throws Exception;

    @Override // d.a.p.c.b
    public abstract /* synthetic */ int requestFusion(int i2);

    public final boolean offer(T t, T t2) {
        throw new UnsupportedOperationException("Should not be called");
    }
}
