package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.operators.SpscArrayQueue;
import java.util.concurrent.atomic.AtomicInteger;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
abstract class FlowableConcatMap$BaseConcatMapSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, c, tg2 {
    private static final long serialVersionUID = -3511336836796789179L;
    volatile boolean active;
    volatile boolean cancelled;
    int consumed;
    volatile boolean done;
    final int limit;
    final io.reactivex.rxjava3.functions.o mapper;
    final int prefetch;
    io.reactivex.rxjava3.operators.g queue;
    int sourceMode;
    tg2 upstream;
    final FlowableConcatMap$ConcatMapInner<R> inner = new FlowableConcatMap$ConcatMapInner<>(this);
    final AtomicThrowable errors = new AtomicThrowable();

    public FlowableConcatMap$BaseConcatMapSubscriber(io.reactivex.rxjava3.functions.o oVar, int i) {
        this.mapper = oVar;
        this.prefetch = i;
        this.limit = i - (i >> 2);
    }

    public abstract void e();

    public abstract void f();

    @Override // p.rg2
    public final void onComplete() {
        this.done = true;
        e();
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (this.sourceMode == 2 || this.queue.offer(obj)) {
            e();
        } else {
            this.upstream.cancel();
            onError(new IllegalStateException("Queue full?!"));
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
                    this.sourceMode = iB;
                    this.queue = dVar;
                    this.done = true;
                    f();
                    e();
                    return;
                }
                if (iB == 2) {
                    this.sourceMode = iB;
                    this.queue = dVar;
                    f();
                    tg2Var.a(this.prefetch);
                    return;
                }
            }
            this.queue = new SpscArrayQueue(this.prefetch);
            f();
            tg2Var.a(this.prefetch);
        }
    }
}
