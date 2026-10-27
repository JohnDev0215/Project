package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.operators.SpscArrayQueue;
import java.util.concurrent.atomic.AtomicReference;
import spi.bk;
import spi.kp;
import spi.q6;
import spi.qf;
import spi.ql;
import spi.zr;

/* JADX INFO: loaded from: classes2.dex */
public final class InnerQueuedObserver<T> extends AtomicReference<q6> implements bk<T>, q6 {
    private static final long serialVersionUID = -5417183359794346637L;
    public volatile boolean done;
    public int fusionMode;
    public final qf<T> parent;
    public final int prefetch;
    public kp<T> queue;

    public InnerQueuedObserver(qf<T> qfVar, int i) {
        this.parent = qfVar;
        this.prefetch = i;
    }

    @Override // spi.q6
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    public boolean isDone() {
        return this.done;
    }

    @Override // spi.bk
    public void onComplete() {
        this.parent.d();
    }

    @Override // spi.bk
    public void onError(Throwable th) {
        this.parent.a();
    }

    @Override // spi.bk
    public void onNext(T t) {
        if (this.fusionMode == 0) {
            this.parent.b();
        } else {
            this.parent.c();
        }
    }

    @Override // spi.bk
    public void onSubscribe(q6 q6Var) {
        if (DisposableHelper.setOnce(this, q6Var)) {
            if (q6Var instanceof ql) {
                ql qlVar = (ql) q6Var;
                int iRequestFusion = qlVar.requestFusion(3);
                if (iRequestFusion == 1) {
                    this.fusionMode = iRequestFusion;
                    this.queue = qlVar;
                    this.done = true;
                    this.parent.d();
                    return;
                }
                if (iRequestFusion == 2) {
                    this.fusionMode = iRequestFusion;
                    this.queue = qlVar;
                    return;
                }
            }
            int i = -this.prefetch;
            this.queue = i < 0 ? new zr<>(-i) : new SpscArrayQueue<>(i);
        }
    }

    public kp<T> queue() {
        return this.queue;
    }

    public void setDone() {
        this.done = true;
    }
}
