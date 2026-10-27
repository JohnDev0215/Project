package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;
import spi.bk;
import spi.q6;

/* JADX INFO: loaded from: classes2.dex */
public final class BlockingObserver<T> extends AtomicReference<q6> implements bk<T>, q6 {
    public static final Object TERMINATED = new Object();
    private static final long serialVersionUID = -4875965440900746268L;
    public final Queue<Object> queue;

    public BlockingObserver(Queue<Object> queue) {
        this.queue = queue;
    }

    @Override // spi.q6
    public void dispose() {
        if (DisposableHelper.dispose(this)) {
            this.queue.offer(TERMINATED);
        }
    }

    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    @Override // spi.bk
    public void onComplete() {
        this.queue.offer(NotificationLite.complete());
    }

    @Override // spi.bk
    public void onError(Throwable th) {
        this.queue.offer(NotificationLite.error(th));
    }

    @Override // spi.bk
    public void onNext(T t) {
        this.queue.offer(NotificationLite.next(t));
    }

    @Override // spi.bk
    public void onSubscribe(q6 q6Var) {
        DisposableHelper.setOnce(this, q6Var);
    }
}
