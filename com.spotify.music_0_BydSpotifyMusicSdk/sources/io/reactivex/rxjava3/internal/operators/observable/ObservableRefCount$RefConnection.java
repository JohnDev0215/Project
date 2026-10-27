package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableRefCount$RefConnection extends AtomicReference<Disposable> implements Runnable, io.reactivex.rxjava3.functions.g {
    private static final long serialVersionUID = -4552101107598366241L;
    boolean connected;
    boolean disconnectedEarly;
    final m3 parent;
    long subscriberCount;
    Disposable timer;

    public ObservableRefCount$RefConnection(m3 m3Var) {
        this.parent = m3Var;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) {
        DisposableHelper.c(this, (Disposable) obj);
        synchronized (this.parent) {
            try {
                if (this.disconnectedEarly) {
                    this.parent.c.b();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.parent.b(this);
    }
}
