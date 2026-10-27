package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import io.reactivex.rxjava3.operators.SpscArrayQueue;
import java.util.concurrent.atomic.AtomicInteger;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConcatMapXMainSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -3214213361171757852L;
    volatile boolean cancelled;
    volatile boolean done;
    final ErrorMode errorMode;
    final AtomicThrowable errors = new AtomicThrowable();
    final int prefetch;
    io.reactivex.rxjava3.operators.g queue;
    boolean syncFused;
    tg2 upstream;

    public ConcatMapXMainSubscriber(int i, ErrorMode errorMode) {
        this.errorMode = errorMode;
        this.prefetch = i;
    }

    public void b() {
    }

    public abstract void c();

    public void cancel() {
        h();
    }

    public abstract void f();

    public abstract void g();

    public final void h() {
        this.cancelled = true;
        this.upstream.cancel();
        c();
        this.errors.b();
        if (getAndIncrement() == 0) {
            this.queue.clear();
            b();
        }
    }

    @Override // p.rg2
    public final void onComplete() {
        this.done = true;
        f();
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        if (this.errors.a(th)) {
            if (this.errorMode == ErrorMode.c) {
                c();
            }
            this.done = true;
            f();
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (obj == null || this.queue.offer(obj)) {
            f();
        } else {
            this.upstream.cancel();
            onError(new MissingBackpressureException("queue full?!"));
        }
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.g(this.upstream, tg2Var)) {
            this.upstream = tg2Var;
            if (tg2Var instanceof io.reactivex.rxjava3.operators.d) {
                io.reactivex.rxjava3.operators.d dVar = (io.reactivex.rxjava3.operators.d) tg2Var;
                int iB = dVar.b(7);
                if (iB == 1) {
                    this.queue = dVar;
                    this.syncFused = true;
                    this.done = true;
                    g();
                    f();
                    return;
                }
                if (iB == 2) {
                    this.queue = dVar;
                    g();
                    this.upstream.a(this.prefetch);
                    return;
                }
            }
            this.queue = new SpscArrayQueue(this.prefetch);
            g();
            this.upstream.a(this.prefetch);
        }
    }
}
