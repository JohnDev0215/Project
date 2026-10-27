package io.reactivex.internal.observers;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class BasicIntQueueDisposable<T> extends AtomicInteger implements d.a.n.b.a<T> {
    private static final long serialVersionUID = -1001730202384742097L;

    @Override // d.a.n.b.c
    public abstract /* synthetic */ void clear();

    @Override // io.reactivex.disposables.b
    public abstract /* synthetic */ void dispose();

    @Override // io.reactivex.disposables.b
    public abstract /* synthetic */ boolean isDisposed();

    @Override // d.a.n.b.c
    public abstract /* synthetic */ boolean isEmpty();

    @Override // d.a.n.b.c
    public final boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called");
    }

    @Override // d.a.n.b.c
    public abstract /* synthetic */ T poll() throws Exception;

    @Override // d.a.n.b.b
    public abstract /* synthetic */ int requestFusion(int i);

    public final boolean offer(T t, T t2) {
        throw new UnsupportedOperationException("Should not be called");
    }
}
