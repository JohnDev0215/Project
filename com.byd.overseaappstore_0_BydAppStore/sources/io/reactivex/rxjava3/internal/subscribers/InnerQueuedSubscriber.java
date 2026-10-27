package io.reactivex.rxjava3.internal.subscribers;

import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.operators.SpscArrayQueue;
import java.util.concurrent.atomic.AtomicReference;
import spi.kp;
import spi.os;
import spi.ps;
import spi.rf;
import spi.sl;
import spi.zr;

/* JADX INFO: loaded from: classes2.dex */
public final class InnerQueuedSubscriber<T> extends AtomicReference<ps> implements os, ps {
    private static final long serialVersionUID = 22876611072430776L;
    public volatile boolean done;
    public int fusionMode;
    public final int limit;
    public final rf<T> parent;
    public final int prefetch;
    public long produced;
    public volatile kp<T> queue;

    public InnerQueuedSubscriber(rf<T> rfVar, int i) {
        this.parent = rfVar;
        this.prefetch = i;
        this.limit = i - (i >> 2);
    }

    @Override // spi.ps
    public void cancel() {
        SubscriptionHelper.cancel(this);
    }

    public boolean isDone() {
        return this.done;
    }

    @Override // spi.os
    public void onComplete() {
        this.parent.d();
    }

    @Override // spi.os
    public void onError(Throwable th) {
        this.parent.a();
    }

    @Override // spi.os
    public void onNext(T t) {
        if (this.fusionMode == 0) {
            this.parent.b();
        } else {
            this.parent.c();
        }
    }

    @Override // spi.os
    public void onSubscribe(ps psVar) {
        if (SubscriptionHelper.setOnce(this, psVar)) {
            if (psVar instanceof sl) {
                sl slVar = (sl) psVar;
                int iRequestFusion = slVar.requestFusion(3);
                if (iRequestFusion == 1) {
                    this.fusionMode = iRequestFusion;
                    this.queue = slVar;
                    this.done = true;
                    this.parent.d();
                    return;
                }
                if (iRequestFusion == 2) {
                    this.fusionMode = iRequestFusion;
                    this.queue = slVar;
                    int i = this.prefetch;
                    psVar.request(i >= 0 ? i : Long.MAX_VALUE);
                    return;
                }
            }
            int i2 = this.prefetch;
            this.queue = i2 < 0 ? new zr<>(-i2) : new SpscArrayQueue<>(i2);
            int i3 = this.prefetch;
            psVar.request(i3 >= 0 ? i3 : Long.MAX_VALUE);
        }
    }

    public kp<T> queue() {
        return this.queue;
    }

    @Override // spi.ps
    public void request(long j) {
        if (this.fusionMode != 1) {
            long j2 = this.produced + j;
            if (j2 < this.limit) {
                this.produced = j2;
            } else {
                this.produced = 0L;
                get().request(j2);
            }
        }
    }

    public void setDone() {
        this.done = true;
    }
}
