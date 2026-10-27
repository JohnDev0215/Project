package io.reactivex.rxjava3.internal.subscribers;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.operators.SpscArrayQueue;
import io.reactivex.rxjava3.operators.d;
import io.reactivex.rxjava3.operators.g;
import io.reactivex.rxjava3.operators.h;
import java.util.concurrent.atomic.AtomicReference;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public final class InnerQueuedSubscriber<T> extends AtomicReference<tg2> implements FlowableSubscriber<T>, tg2 {
    private static final long serialVersionUID = 22876611072430776L;
    volatile boolean done;
    int fusionMode;
    final int limit;
    final c parent;
    final int prefetch;
    long produced;
    volatile g queue;

    public InnerQueuedSubscriber(c cVar, int i) {
        this.parent = cVar;
        this.prefetch = i;
        this.limit = i - (i >> 2);
    }

    @Override // p.tg2
    public final void a(long j) {
        if (this.fusionMode != 1) {
            long j2 = this.produced + j;
            if (j2 < this.limit) {
                this.produced = j2;
            } else {
                this.produced = 0L;
                get().a(j2);
            }
        }
    }

    public final boolean b() {
        return this.done;
    }

    public final g c() {
        return this.queue;
    }

    @Override // p.tg2
    public final void cancel() {
        SubscriptionHelper.b(this);
    }

    public final void e() {
        this.done = true;
    }

    @Override // p.rg2
    public final void onComplete() {
        this.parent.c(this);
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        this.parent.e(this, th);
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (this.fusionMode == 0) {
            this.parent.f(this, obj);
        } else {
            this.parent.b();
        }
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.e(this, tg2Var)) {
            if (tg2Var instanceof d) {
                d dVar = (d) tg2Var;
                int iB = dVar.b(3);
                if (iB == 1) {
                    this.fusionMode = iB;
                    this.queue = dVar;
                    this.done = true;
                    this.parent.c(this);
                    return;
                }
                if (iB == 2) {
                    this.fusionMode = iB;
                    this.queue = dVar;
                    int i = this.prefetch;
                    tg2Var.a(i >= 0 ? i : Long.MAX_VALUE);
                    return;
                }
            }
            int i2 = this.prefetch;
            this.queue = i2 < 0 ? new h(-i2) : new SpscArrayQueue(i2);
            int i3 = this.prefetch;
            tg2Var.a(i3 >= 0 ? i3 : Long.MAX_VALUE);
        }
    }
}
