package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.operators.SpscArrayQueue;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p.az1;
import p.ci1;
import p.he1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableFlatMap$MergeSubscriber<T, U> extends AtomicInteger implements FlowableSubscriber<T>, tg2 {
    public static final FlowableFlatMap$InnerSubscriber[] c = new FlowableFlatMap$InnerSubscriber[0];
    public static final FlowableFlatMap$InnerSubscriber[] e = new FlowableFlatMap$InnerSubscriber[0];
    private static final long serialVersionUID = -2117620485640801370L;
    final int bufferSize;
    volatile boolean cancelled;
    final boolean delayErrors;
    volatile boolean done;
    final rg2 downstream;
    final AtomicThrowable errors = new AtomicThrowable();
    long lastId;
    int lastIndex;
    final io.reactivex.rxjava3.functions.o mapper;
    final int maxConcurrency;
    volatile io.reactivex.rxjava3.operators.f queue;
    final AtomicLong requested;
    int scalarEmitted;
    final int scalarLimit;
    final AtomicReference<FlowableFlatMap$InnerSubscriber<?, ?>[]> subscribers;
    long uniqueId;
    tg2 upstream;

    public FlowableFlatMap$MergeSubscriber(rg2 rg2Var, io.reactivex.rxjava3.functions.o oVar, boolean z, int i, int i2) {
        AtomicReference<FlowableFlatMap$InnerSubscriber<?, ?>[]> atomicReference = new AtomicReference<>();
        this.subscribers = atomicReference;
        this.requested = new AtomicLong();
        this.downstream = rg2Var;
        this.mapper = oVar;
        this.delayErrors = z;
        this.maxConcurrency = i;
        this.bufferSize = i2;
        this.scalarLimit = Math.max(1, i >> 1);
        atomicReference.lazySet(c);
    }

    @Override // p.tg2
    public final void a(long j) {
        if (SubscriptionHelper.f(j)) {
            ci1.d(this.requested, j);
            c();
        }
    }

    public final boolean b() {
        if (this.cancelled) {
            io.reactivex.rxjava3.operators.f fVar = this.queue;
            if (fVar != null) {
                fVar.clear();
            }
            return true;
        }
        if (this.delayErrors || this.errors.get() == null) {
            return false;
        }
        io.reactivex.rxjava3.operators.f fVar2 = this.queue;
        if (fVar2 != null) {
            fVar2.clear();
        }
        this.errors.e(this.downstream);
        return true;
    }

    public final void c() {
        if (getAndIncrement() == 0) {
            e();
        }
    }

    @Override // p.tg2
    public final void cancel() {
        io.reactivex.rxjava3.operators.f fVar;
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.upstream.cancel();
        AtomicReference<FlowableFlatMap$InnerSubscriber<?, ?>[]> atomicReference = this.subscribers;
        FlowableFlatMap$InnerSubscriber<?, ?>[] flowableFlatMap$InnerSubscriberArr = e;
        FlowableFlatMap$InnerSubscriber<?, ?>[] andSet = atomicReference.getAndSet(flowableFlatMap$InnerSubscriberArr);
        if (andSet != flowableFlatMap$InnerSubscriberArr) {
            for (FlowableFlatMap$InnerSubscriber<?, ?> flowableFlatMap$InnerSubscriber : andSet) {
                flowableFlatMap$InnerSubscriber.dispose();
            }
            this.errors.b();
        }
        if (getAndIncrement() != 0 || (fVar = this.queue) == null) {
            return;
        }
        fVar.clear();
    }

    public final void e() {
        long j;
        long j2;
        long j3;
        boolean z;
        int i;
        int i2;
        long j4;
        long j5;
        rg2 rg2Var = this.downstream;
        int iAddAndGet = 1;
        while (!b()) {
            io.reactivex.rxjava3.operators.f fVar = this.queue;
            long jAddAndGet = this.requested.get();
            boolean z2 = jAddAndGet == Long.MAX_VALUE;
            long j6 = 0;
            if (fVar != null) {
                long j7 = 0;
                j = 0;
                while (jAddAndGet != 0) {
                    Object objPoll = fVar.poll();
                    if (b()) {
                        return;
                    }
                    if (objPoll == null) {
                        break;
                    }
                    rg2Var.onNext(objPoll);
                    j++;
                    j7++;
                    jAddAndGet--;
                }
                if (j7 != 0) {
                    jAddAndGet = z2 ? Long.MAX_VALUE : this.requested.addAndGet(-j7);
                }
            } else {
                j = 0;
            }
            boolean z3 = this.done;
            io.reactivex.rxjava3.operators.f fVar2 = this.queue;
            FlowableFlatMap$InnerSubscriber<?, ?>[] flowableFlatMap$InnerSubscriberArr = this.subscribers.get();
            int length = flowableFlatMap$InnerSubscriberArr.length;
            if (z3 && ((fVar2 == null || fVar2.isEmpty()) && length == 0)) {
                this.errors.e(this.downstream);
                return;
            }
            int i3 = iAddAndGet;
            if (length != 0) {
                long j8 = this.lastId;
                int i4 = this.lastIndex;
                if (length <= i4 || flowableFlatMap$InnerSubscriberArr[i4].id != j8) {
                    if (length <= i4) {
                        i4 = 0;
                    }
                    for (int i5 = 0; i5 < length && flowableFlatMap$InnerSubscriberArr[i4].id != j8; i5++) {
                        i4++;
                        if (i4 == length) {
                            i4 = 0;
                        }
                    }
                    this.lastIndex = i4;
                    this.lastId = flowableFlatMap$InnerSubscriberArr[i4].id;
                }
                int i6 = i4;
                boolean z4 = false;
                int i7 = 0;
                while (true) {
                    if (i7 >= length) {
                        z = z4;
                        break;
                    }
                    if (b()) {
                        return;
                    }
                    FlowableFlatMap$InnerSubscriber<?, ?> flowableFlatMap$InnerSubscriber = flowableFlatMap$InnerSubscriberArr[i6];
                    Object obj = null;
                    while (true) {
                        io.reactivex.rxjava3.operators.g gVar = flowableFlatMap$InnerSubscriber.queue;
                        if (gVar == null) {
                            i = length;
                            break;
                        }
                        i = length;
                        Object obj2 = obj;
                        long j9 = j6;
                        while (true) {
                            if (jAddAndGet == j6) {
                                j4 = j6;
                                break;
                            }
                            if (b()) {
                                return;
                            }
                            try {
                                Object objPoll2 = gVar.poll();
                                if (objPoll2 == null) {
                                    obj2 = objPoll2;
                                    j4 = 0;
                                    break;
                                } else {
                                    rg2Var.onNext(objPoll2);
                                    jAddAndGet--;
                                    j9++;
                                    obj2 = objPoll2;
                                    j6 = 0;
                                }
                            } catch (Throwable th) {
                                he1.R(th);
                                flowableFlatMap$InnerSubscriber.dispose();
                                this.errors.a(th);
                                if (!this.delayErrors) {
                                    this.upstream.cancel();
                                }
                                if (b()) {
                                    return;
                                }
                                g(flowableFlatMap$InnerSubscriber);
                                i7++;
                                i2 = i;
                                z4 = true;
                            }
                        }
                        if (j9 != j4) {
                            jAddAndGet = !z2 ? this.requested.addAndGet(-j9) : Long.MAX_VALUE;
                            flowableFlatMap$InnerSubscriber.a(j9);
                            j5 = 0;
                        } else {
                            j5 = j4;
                        }
                        if (jAddAndGet == j5 || obj2 == null) {
                            break;
                        }
                        length = i;
                        obj = obj2;
                        j6 = 0;
                    }
                    boolean z5 = flowableFlatMap$InnerSubscriber.done;
                    io.reactivex.rxjava3.operators.g gVar2 = flowableFlatMap$InnerSubscriber.queue;
                    if (z5 && (gVar2 == null || gVar2.isEmpty())) {
                        g(flowableFlatMap$InnerSubscriber);
                        if (b()) {
                            return;
                        }
                        j++;
                        z4 = true;
                    }
                    if (jAddAndGet == 0) {
                        z = z4;
                        break;
                    }
                    i6++;
                    i2 = i;
                    if (i6 == i2) {
                        i6 = 0;
                    }
                    i7++;
                    length = i2;
                    j6 = 0;
                }
                this.lastIndex = i6;
                this.lastId = flowableFlatMap$InnerSubscriberArr[i6].id;
                j3 = j;
                j2 = 0;
            } else {
                j2 = 0;
                j3 = j;
                z = false;
            }
            if (j3 != j2 && !this.cancelled) {
                this.upstream.a(j3);
            }
            if (z) {
                iAddAndGet = i3;
            } else {
                iAddAndGet = addAndGet(-i3);
                if (iAddAndGet == 0) {
                    return;
                }
            }
        }
    }

    public final io.reactivex.rxjava3.operators.f f() {
        io.reactivex.rxjava3.operators.f hVar = this.queue;
        if (hVar == null) {
            hVar = this.maxConcurrency == Integer.MAX_VALUE ? new io.reactivex.rxjava3.operators.h(this.bufferSize) : new SpscArrayQueue(this.maxConcurrency);
            this.queue = hVar;
        }
        return hVar;
    }

    public final void g(FlowableFlatMap$InnerSubscriber flowableFlatMap$InnerSubscriber) {
        FlowableFlatMap$InnerSubscriber<?, ?>[] flowableFlatMap$InnerSubscriberArr;
        while (true) {
            FlowableFlatMap$InnerSubscriber<?, ?>[] flowableFlatMap$InnerSubscriberArr2 = this.subscribers.get();
            int length = flowableFlatMap$InnerSubscriberArr2.length;
            if (length == 0) {
                return;
            }
            int i = 0;
            while (true) {
                if (i >= length) {
                    i = -1;
                    break;
                } else if (flowableFlatMap$InnerSubscriberArr2[i] == flowableFlatMap$InnerSubscriber) {
                    break;
                } else {
                    i++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                flowableFlatMap$InnerSubscriberArr = c;
            } else {
                FlowableFlatMap$InnerSubscriber<?, ?>[] flowableFlatMap$InnerSubscriberArr3 = new FlowableFlatMap$InnerSubscriber[length - 1];
                System.arraycopy(flowableFlatMap$InnerSubscriberArr2, 0, flowableFlatMap$InnerSubscriberArr3, 0, i);
                System.arraycopy(flowableFlatMap$InnerSubscriberArr2, i + 1, flowableFlatMap$InnerSubscriberArr3, i, (length - i) - 1);
                flowableFlatMap$InnerSubscriberArr = flowableFlatMap$InnerSubscriberArr3;
            }
            AtomicReference<FlowableFlatMap$InnerSubscriber<?, ?>[]> atomicReference = this.subscribers;
            while (!atomicReference.compareAndSet(flowableFlatMap$InnerSubscriberArr2, flowableFlatMap$InnerSubscriberArr)) {
                if (atomicReference.get() != flowableFlatMap$InnerSubscriberArr2) {
                    break;
                }
            }
            return;
        }
    }

    @Override // p.rg2
    public final void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        c();
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.b(th);
            return;
        }
        if (this.errors.a(th)) {
            this.done = true;
            if (!this.delayErrors) {
                for (FlowableFlatMap$InnerSubscriber<?, ?> flowableFlatMap$InnerSubscriber : this.subscribers.getAndSet(e)) {
                    flowableFlatMap$InnerSubscriber.dispose();
                }
            }
            c();
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (this.done) {
            return;
        }
        try {
            Object objApply = this.mapper.apply(obj);
            Objects.requireNonNull(objApply, "The mapper returned a null Publisher");
            az1 az1Var = (az1) objApply;
            if (!(az1Var instanceof io.reactivex.rxjava3.functions.q)) {
                int i = this.bufferSize;
                long j = this.uniqueId;
                this.uniqueId = 1 + j;
                FlowableFlatMap$InnerSubscriber<?, ?> flowableFlatMap$InnerSubscriber = new FlowableFlatMap$InnerSubscriber<>(this, i, j);
                while (true) {
                    FlowableFlatMap$InnerSubscriber<?, ?>[] flowableFlatMap$InnerSubscriberArr = this.subscribers.get();
                    if (flowableFlatMap$InnerSubscriberArr == e) {
                        flowableFlatMap$InnerSubscriber.dispose();
                        return;
                    }
                    int length = flowableFlatMap$InnerSubscriberArr.length;
                    FlowableFlatMap$InnerSubscriber<?, ?>[] flowableFlatMap$InnerSubscriberArr2 = new FlowableFlatMap$InnerSubscriber[length + 1];
                    System.arraycopy(flowableFlatMap$InnerSubscriberArr, 0, flowableFlatMap$InnerSubscriberArr2, 0, length);
                    flowableFlatMap$InnerSubscriberArr2[length] = flowableFlatMap$InnerSubscriber;
                    AtomicReference<FlowableFlatMap$InnerSubscriber<?, ?>[]> atomicReference = this.subscribers;
                    while (!atomicReference.compareAndSet(flowableFlatMap$InnerSubscriberArr, flowableFlatMap$InnerSubscriberArr2)) {
                        if (atomicReference.get() != flowableFlatMap$InnerSubscriberArr) {
                            break;
                        }
                    }
                    az1Var.subscribe(flowableFlatMap$InnerSubscriber);
                    return;
                }
            }
            try {
                Object obj2 = ((io.reactivex.rxjava3.functions.q) az1Var).get();
                if (obj2 == null) {
                    if (this.maxConcurrency == Integer.MAX_VALUE || this.cancelled) {
                        return;
                    }
                    int i2 = this.scalarEmitted + 1;
                    this.scalarEmitted = i2;
                    int i3 = this.scalarLimit;
                    if (i2 == i3) {
                        this.scalarEmitted = 0;
                        this.upstream.a(i3);
                        return;
                    }
                    return;
                }
                if (get() == 0 && compareAndSet(0, 1)) {
                    long j2 = this.requested.get();
                    io.reactivex.rxjava3.operators.f fVarF = this.queue;
                    if (j2 == 0 || !(fVarF == null || fVarF.isEmpty())) {
                        if (fVarF == null) {
                            fVarF = f();
                        }
                        if (!fVarF.offer(obj2)) {
                            onError(new MissingBackpressureException("Scalar queue full?!"));
                        }
                    } else {
                        this.downstream.onNext(obj2);
                        if (j2 != Long.MAX_VALUE) {
                            this.requested.decrementAndGet();
                        }
                        if (this.maxConcurrency != Integer.MAX_VALUE && !this.cancelled) {
                            int i4 = this.scalarEmitted + 1;
                            this.scalarEmitted = i4;
                            int i5 = this.scalarLimit;
                            if (i4 == i5) {
                                this.scalarEmitted = 0;
                                this.upstream.a(i5);
                            }
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                } else if (!f().offer(obj2)) {
                    onError(new MissingBackpressureException("Scalar queue full?!"));
                    return;
                } else if (getAndIncrement() != 0) {
                    return;
                }
                e();
            } catch (Throwable th) {
                he1.R(th);
                this.errors.a(th);
                c();
            }
        } catch (Throwable th2) {
            he1.R(th2);
            this.upstream.cancel();
            onError(th2);
        }
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.g(this.upstream, tg2Var)) {
            this.upstream = tg2Var;
            this.downstream.onSubscribe(this);
            if (this.cancelled) {
                return;
            }
            int i = this.maxConcurrency;
            if (i == Integer.MAX_VALUE) {
                tg2Var.a(Long.MAX_VALUE);
            } else {
                tg2Var.a(i);
            }
        }
    }
}
