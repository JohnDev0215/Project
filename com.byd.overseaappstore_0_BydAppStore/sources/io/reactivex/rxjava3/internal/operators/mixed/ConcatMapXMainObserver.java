package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import java.util.concurrent.atomic.AtomicInteger;
import spi.bk;
import spi.kp;
import spi.q6;
import spi.ql;
import spi.zr;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ConcatMapXMainObserver<T> extends AtomicInteger implements bk<T>, q6 {
    private static final long serialVersionUID = -3214213361171757852L;
    public volatile boolean disposed;
    public volatile boolean done;
    public final ErrorMode errorMode;
    public final AtomicThrowable errors = new AtomicThrowable();
    public final int prefetch;
    public kp<T> queue;
    public q6 upstream;

    public ConcatMapXMainObserver(int i, ErrorMode errorMode) {
        this.errorMode = errorMode;
        this.prefetch = i;
    }

    public void clearValue() {
    }

    @Override // spi.q6
    public final void dispose() {
        this.disposed = true;
        this.upstream.dispose();
        disposeInner();
        this.errors.tryTerminateAndReport();
        if (getAndIncrement() == 0) {
            this.queue.clear();
            clearValue();
        }
    }

    public abstract void disposeInner();

    public abstract void drain();

    public final boolean isDisposed() {
        return this.disposed;
    }

    @Override // spi.bk
    public final void onComplete() {
        this.done = true;
        drain();
    }

    @Override // spi.bk
    public final void onError(Throwable th) {
        if (this.errors.tryAddThrowableOrReport(th)) {
            if (this.errorMode == ErrorMode.IMMEDIATE) {
                disposeInner();
            }
            this.done = true;
            drain();
        }
    }

    @Override // spi.bk
    public final void onNext(T t) {
        if (t != null) {
            this.queue.offer(t);
        }
        drain();
    }

    @Override // spi.bk
    public final void onSubscribe(q6 q6Var) {
        if (DisposableHelper.validate(this.upstream, q6Var)) {
            this.upstream = q6Var;
            if (q6Var instanceof ql) {
                ql qlVar = (ql) q6Var;
                int iRequestFusion = qlVar.requestFusion(7);
                if (iRequestFusion == 1) {
                    this.queue = qlVar;
                    this.done = true;
                    onSubscribeDownstream();
                    drain();
                    return;
                }
                if (iRequestFusion == 2) {
                    this.queue = qlVar;
                    onSubscribeDownstream();
                    return;
                }
            }
            this.queue = new zr(this.prefetch);
            onSubscribeDownstream();
        }
    }

    public abstract void onSubscribeDownstream();
}
