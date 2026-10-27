package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.operators.SpscArrayQueue;
import p.he1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public final class z extends a {
    public final Scheduler k;
    public final boolean l;
    public final int m;

    public z(Flowable flowable, Scheduler scheduler, int i) {
        super(flowable);
        this.k = scheduler;
        this.l = false;
        this.m = i;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(final rg2 rg2Var) {
        final Scheduler.Worker workerB = this.k.b();
        boolean z = rg2Var instanceof io.reactivex.rxjava3.operators.a;
        final int i = this.m;
        final boolean z2 = this.l;
        Flowable flowable = this.e;
        if (!z) {
            flowable.subscribe((FlowableSubscriber) new FlowableObserveOn$BaseObserveOnSubscriber<T>(rg2Var, workerB, z2, i) { // from class: io.reactivex.rxjava3.internal.operators.flowable.FlowableObserveOn$ObserveOnSubscriber
                private static final long serialVersionUID = -4547113800637756442L;
                final rg2 downstream;

                {
                    super(workerB, z2, i);
                    this.downstream = rg2Var;
                }

                @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableObserveOn$BaseObserveOnSubscriber
                public final void e() {
                    rg2 rg2Var2 = this.downstream;
                    io.reactivex.rxjava3.operators.g gVar = this.queue;
                    long j = this.produced;
                    int iAddAndGet = 1;
                    while (true) {
                        long jAddAndGet = this.requested.get();
                        while (j != jAddAndGet) {
                            boolean z3 = this.done;
                            try {
                                Object objPoll = gVar.poll();
                                boolean z4 = objPoll == null;
                                if (c(z3, z4, rg2Var2)) {
                                    return;
                                }
                                if (z4) {
                                    break;
                                }
                                rg2Var2.onNext(objPoll);
                                j++;
                                if (j == this.limit) {
                                    if (jAddAndGet != Long.MAX_VALUE) {
                                        jAddAndGet = this.requested.addAndGet(-j);
                                    }
                                    this.upstream.a(j);
                                    j = 0;
                                }
                            } catch (Throwable th) {
                                he1.R(th);
                                this.cancelled = true;
                                this.upstream.cancel();
                                gVar.clear();
                                rg2Var2.onError(th);
                                this.worker.dispose();
                                return;
                            }
                        }
                        if (j == jAddAndGet && c(this.done, gVar.isEmpty(), rg2Var2)) {
                            return;
                        }
                        int i2 = get();
                        if (iAddAndGet == i2) {
                            this.produced = j;
                            iAddAndGet = addAndGet(-iAddAndGet);
                            if (iAddAndGet == 0) {
                                return;
                            }
                        } else {
                            iAddAndGet = i2;
                        }
                    }
                }

                @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableObserveOn$BaseObserveOnSubscriber
                public final void f() {
                    int iAddAndGet = 1;
                    while (!this.cancelled) {
                        boolean z3 = this.done;
                        this.downstream.onNext(null);
                        if (z3) {
                            this.cancelled = true;
                            Throwable th = this.error;
                            if (th != null) {
                                this.downstream.onError(th);
                            } else {
                                this.downstream.onComplete();
                            }
                            this.worker.dispose();
                            return;
                        }
                        iAddAndGet = addAndGet(-iAddAndGet);
                        if (iAddAndGet == 0) {
                            return;
                        }
                    }
                }

                @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableObserveOn$BaseObserveOnSubscriber
                public final void g() {
                    rg2 rg2Var2 = this.downstream;
                    io.reactivex.rxjava3.operators.g gVar = this.queue;
                    long j = this.produced;
                    int iAddAndGet = 1;
                    do {
                        long j2 = this.requested.get();
                        while (j != j2) {
                            try {
                                Object objPoll = gVar.poll();
                                if (this.cancelled) {
                                    return;
                                }
                                if (objPoll == null) {
                                    this.cancelled = true;
                                    rg2Var2.onComplete();
                                    this.worker.dispose();
                                    return;
                                }
                                rg2Var2.onNext(objPoll);
                                j++;
                            } catch (Throwable th) {
                                he1.R(th);
                                this.cancelled = true;
                                this.upstream.cancel();
                                rg2Var2.onError(th);
                                this.worker.dispose();
                                return;
                            }
                        }
                        if (this.cancelled) {
                            return;
                        }
                        if (gVar.isEmpty()) {
                            this.cancelled = true;
                            rg2Var2.onComplete();
                            this.worker.dispose();
                            return;
                        }
                        this.produced = j;
                        iAddAndGet = addAndGet(-iAddAndGet);
                    } while (iAddAndGet != 0);
                }

                @Override // p.rg2
                public final void onSubscribe(tg2 tg2Var) {
                    if (SubscriptionHelper.g(this.upstream, tg2Var)) {
                        this.upstream = tg2Var;
                        if (tg2Var instanceof io.reactivex.rxjava3.operators.d) {
                            io.reactivex.rxjava3.operators.d dVar = (io.reactivex.rxjava3.operators.d) tg2Var;
                            int iB = dVar.b(7);
                            if (iB == 1) {
                                this.sourceMode = 1;
                                this.queue = dVar;
                                this.done = true;
                                this.downstream.onSubscribe(this);
                                return;
                            }
                            if (iB == 2) {
                                this.sourceMode = 2;
                                this.queue = dVar;
                                this.downstream.onSubscribe(this);
                                tg2Var.a(this.prefetch);
                                return;
                            }
                        }
                        this.queue = new SpscArrayQueue(this.prefetch);
                        this.downstream.onSubscribe(this);
                        tg2Var.a(this.prefetch);
                    }
                }

                @Override // io.reactivex.rxjava3.operators.g
                public final Object poll() {
                    Object objPoll = this.queue.poll();
                    if (objPoll != null && this.sourceMode != 1) {
                        long j = this.produced + 1;
                        if (j == this.limit) {
                            this.produced = 0L;
                            this.upstream.a(j);
                        } else {
                            this.produced = j;
                        }
                    }
                    return objPoll;
                }
            });
        } else {
            final io.reactivex.rxjava3.operators.a aVar = (io.reactivex.rxjava3.operators.a) rg2Var;
            flowable.subscribe((FlowableSubscriber) new FlowableObserveOn$BaseObserveOnSubscriber<T>(aVar, workerB, z2, i) { // from class: io.reactivex.rxjava3.internal.operators.flowable.FlowableObserveOn$ObserveOnConditionalSubscriber
                private static final long serialVersionUID = 644624475404284533L;
                long consumed;
                final io.reactivex.rxjava3.operators.a downstream;

                {
                    super(workerB, z2, i);
                    this.downstream = aVar;
                }

                @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableObserveOn$BaseObserveOnSubscriber
                public final void e() {
                    io.reactivex.rxjava3.operators.a aVar2 = this.downstream;
                    io.reactivex.rxjava3.operators.g gVar = this.queue;
                    long j = this.produced;
                    long j2 = this.consumed;
                    int iAddAndGet = 1;
                    do {
                        long j3 = this.requested.get();
                        while (j != j3) {
                            boolean z3 = this.done;
                            try {
                                Object objPoll = gVar.poll();
                                boolean z4 = objPoll == null;
                                if (c(z3, z4, aVar2)) {
                                    return;
                                }
                                if (z4) {
                                    break;
                                }
                                if (aVar2.d(objPoll)) {
                                    j++;
                                }
                                j2++;
                                if (j2 == this.limit) {
                                    this.upstream.a(j2);
                                    j2 = 0;
                                }
                            } catch (Throwable th) {
                                he1.R(th);
                                this.cancelled = true;
                                this.upstream.cancel();
                                gVar.clear();
                                aVar2.onError(th);
                                this.worker.dispose();
                                return;
                            }
                        }
                        if (j == j3 && c(this.done, gVar.isEmpty(), aVar2)) {
                            return;
                        }
                        this.produced = j;
                        this.consumed = j2;
                        iAddAndGet = addAndGet(-iAddAndGet);
                    } while (iAddAndGet != 0);
                }

                @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableObserveOn$BaseObserveOnSubscriber
                public final void f() {
                    int iAddAndGet = 1;
                    while (!this.cancelled) {
                        boolean z3 = this.done;
                        this.downstream.onNext(null);
                        if (z3) {
                            this.cancelled = true;
                            Throwable th = this.error;
                            if (th != null) {
                                this.downstream.onError(th);
                            } else {
                                this.downstream.onComplete();
                            }
                            this.worker.dispose();
                            return;
                        }
                        iAddAndGet = addAndGet(-iAddAndGet);
                        if (iAddAndGet == 0) {
                            return;
                        }
                    }
                }

                @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableObserveOn$BaseObserveOnSubscriber
                public final void g() {
                    io.reactivex.rxjava3.operators.a aVar2 = this.downstream;
                    io.reactivex.rxjava3.operators.g gVar = this.queue;
                    long j = this.produced;
                    int iAddAndGet = 1;
                    do {
                        long j2 = this.requested.get();
                        while (j != j2) {
                            try {
                                Object objPoll = gVar.poll();
                                if (this.cancelled) {
                                    return;
                                }
                                if (objPoll == null) {
                                    this.cancelled = true;
                                    aVar2.onComplete();
                                    this.worker.dispose();
                                    return;
                                } else if (aVar2.d(objPoll)) {
                                    j++;
                                }
                            } catch (Throwable th) {
                                he1.R(th);
                                this.cancelled = true;
                                this.upstream.cancel();
                                aVar2.onError(th);
                                this.worker.dispose();
                                return;
                            }
                        }
                        if (this.cancelled) {
                            return;
                        }
                        if (gVar.isEmpty()) {
                            this.cancelled = true;
                            aVar2.onComplete();
                            this.worker.dispose();
                            return;
                        }
                        this.produced = j;
                        iAddAndGet = addAndGet(-iAddAndGet);
                    } while (iAddAndGet != 0);
                }

                @Override // p.rg2
                public final void onSubscribe(tg2 tg2Var) {
                    if (SubscriptionHelper.g(this.upstream, tg2Var)) {
                        this.upstream = tg2Var;
                        if (tg2Var instanceof io.reactivex.rxjava3.operators.d) {
                            io.reactivex.rxjava3.operators.d dVar = (io.reactivex.rxjava3.operators.d) tg2Var;
                            int iB = dVar.b(7);
                            if (iB == 1) {
                                this.sourceMode = 1;
                                this.queue = dVar;
                                this.done = true;
                                this.downstream.onSubscribe(this);
                                return;
                            }
                            if (iB == 2) {
                                this.sourceMode = 2;
                                this.queue = dVar;
                                this.downstream.onSubscribe(this);
                                tg2Var.a(this.prefetch);
                                return;
                            }
                        }
                        this.queue = new SpscArrayQueue(this.prefetch);
                        this.downstream.onSubscribe(this);
                        tg2Var.a(this.prefetch);
                    }
                }

                @Override // io.reactivex.rxjava3.operators.g
                public final Object poll() {
                    Object objPoll = this.queue.poll();
                    if (objPoll != null && this.sourceMode != 1) {
                        long j = this.consumed + 1;
                        if (j == this.limit) {
                            this.consumed = 0L;
                            this.upstream.a(j);
                        } else {
                            this.consumed = j;
                        }
                    }
                    return objPoll;
                }
            });
        }
    }
}
