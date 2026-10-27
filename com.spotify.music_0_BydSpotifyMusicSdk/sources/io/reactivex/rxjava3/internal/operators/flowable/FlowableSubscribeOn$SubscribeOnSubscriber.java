package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p.az1;
import p.ci1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableSubscribeOn$SubscribeOnSubscriber<T> extends AtomicReference<Thread> implements FlowableSubscriber<T>, tg2, Runnable {
    private static final long serialVersionUID = 8094547886072529208L;
    final rg2 downstream;
    final boolean nonScheduledRequests;
    az1 source;
    final Scheduler.Worker worker;
    final AtomicReference<tg2> upstream = new AtomicReference<>();
    final AtomicLong requested = new AtomicLong();

    public FlowableSubscribeOn$SubscribeOnSubscriber(rg2 rg2Var, Scheduler.Worker worker, Flowable flowable, boolean z) {
        this.downstream = rg2Var;
        this.worker = worker;
        this.source = flowable;
        this.nonScheduledRequests = !z;
    }

    @Override // p.tg2
    public final void a(long j) {
        if (SubscriptionHelper.f(j)) {
            tg2 tg2Var = this.upstream.get();
            if (tg2Var != null) {
                b(j, tg2Var);
                return;
            }
            ci1.d(this.requested, j);
            tg2 tg2Var2 = this.upstream.get();
            if (tg2Var2 != null) {
                long andSet = this.requested.getAndSet(0L);
                if (andSet != 0) {
                    b(andSet, tg2Var2);
                }
            }
        }
    }

    public final void b(long j, tg2 tg2Var) {
        if (this.nonScheduledRequests || Thread.currentThread() == get()) {
            tg2Var.a(j);
        } else {
            this.worker.a(new u0(j, tg2Var));
        }
    }

    @Override // p.tg2
    public final void cancel() {
        SubscriptionHelper.b(this.upstream);
        this.worker.dispose();
    }

    @Override // p.rg2
    public final void onComplete() {
        this.downstream.onComplete();
        this.worker.dispose();
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        this.downstream.onError(th);
        this.worker.dispose();
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        this.downstream.onNext(obj);
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.e(this.upstream, tg2Var)) {
            long andSet = this.requested.getAndSet(0L);
            if (andSet != 0) {
                b(andSet, tg2Var);
            }
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        lazySet(Thread.currentThread());
        az1 az1Var = this.source;
        this.source = null;
        az1Var.subscribe(this);
    }
}
