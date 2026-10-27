package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.operators.SpscArrayQueue;
import java.util.concurrent.atomic.AtomicReference;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableFlatMap$InnerSubscriber<T, U> extends AtomicReference<tg2> implements FlowableSubscriber<U>, Disposable {
    private static final long serialVersionUID = -4606175640614850599L;
    final int bufferSize;
    volatile boolean done;
    int fusionMode;
    final long id;
    final int limit;
    final FlowableFlatMap$MergeSubscriber<T, U> parent;
    long produced;
    volatile io.reactivex.rxjava3.operators.g queue;

    public FlowableFlatMap$InnerSubscriber(FlowableFlatMap$MergeSubscriber flowableFlatMap$MergeSubscriber, int i, long j) {
        this.id = j;
        this.parent = flowableFlatMap$MergeSubscriber;
        this.bufferSize = i;
        this.limit = i >> 2;
    }

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

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        SubscriptionHelper.b(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get() == SubscriptionHelper.c;
    }

    @Override // p.rg2
    public final void onComplete() {
        this.done = true;
        this.parent.c();
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        lazySet(SubscriptionHelper.c);
        FlowableFlatMap$MergeSubscriber<T, U> flowableFlatMap$MergeSubscriber = this.parent;
        if (flowableFlatMap$MergeSubscriber.errors.a(th)) {
            this.done = true;
            if (!flowableFlatMap$MergeSubscriber.delayErrors) {
                flowableFlatMap$MergeSubscriber.upstream.cancel();
                for (FlowableFlatMap$InnerSubscriber<?, ?> flowableFlatMap$InnerSubscriber : flowableFlatMap$MergeSubscriber.subscribers.getAndSet(FlowableFlatMap$MergeSubscriber.e)) {
                    flowableFlatMap$InnerSubscriber.dispose();
                }
            }
            flowableFlatMap$MergeSubscriber.c();
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (this.fusionMode == 2) {
            this.parent.c();
            return;
        }
        FlowableFlatMap$MergeSubscriber<T, U> flowableFlatMap$MergeSubscriber = this.parent;
        if (flowableFlatMap$MergeSubscriber.get() == 0 && flowableFlatMap$MergeSubscriber.compareAndSet(0, 1)) {
            long j = flowableFlatMap$MergeSubscriber.requested.get();
            io.reactivex.rxjava3.operators.g spscArrayQueue = this.queue;
            if (j == 0 || !(spscArrayQueue == null || spscArrayQueue.isEmpty())) {
                if (spscArrayQueue == null) {
                    spscArrayQueue = new SpscArrayQueue(flowableFlatMap$MergeSubscriber.bufferSize);
                    this.queue = spscArrayQueue;
                }
                if (!spscArrayQueue.offer(obj)) {
                    flowableFlatMap$MergeSubscriber.onError(new MissingBackpressureException("Inner queue full?!"));
                }
            } else {
                flowableFlatMap$MergeSubscriber.downstream.onNext(obj);
                if (j != Long.MAX_VALUE) {
                    flowableFlatMap$MergeSubscriber.requested.decrementAndGet();
                }
                a(1L);
            }
            if (flowableFlatMap$MergeSubscriber.decrementAndGet() == 0) {
                return;
            }
        } else {
            io.reactivex.rxjava3.operators.g spscArrayQueue2 = this.queue;
            if (spscArrayQueue2 == null) {
                spscArrayQueue2 = new SpscArrayQueue(flowableFlatMap$MergeSubscriber.bufferSize);
                this.queue = spscArrayQueue2;
            }
            if (!spscArrayQueue2.offer(obj)) {
                flowableFlatMap$MergeSubscriber.onError(new MissingBackpressureException("Inner queue full?!"));
                return;
            } else if (flowableFlatMap$MergeSubscriber.getAndIncrement() != 0) {
                return;
            }
        }
        flowableFlatMap$MergeSubscriber.e();
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.e(this, tg2Var)) {
            if (tg2Var instanceof io.reactivex.rxjava3.operators.d) {
                io.reactivex.rxjava3.operators.d dVar = (io.reactivex.rxjava3.operators.d) tg2Var;
                int iB = dVar.b(7);
                if (iB == 1) {
                    this.fusionMode = iB;
                    this.queue = dVar;
                    this.done = true;
                    this.parent.c();
                    return;
                }
                if (iB == 2) {
                    this.fusionMode = iB;
                    this.queue = dVar;
                }
            }
            tg2Var.a(this.bufferSize);
        }
    }
}
