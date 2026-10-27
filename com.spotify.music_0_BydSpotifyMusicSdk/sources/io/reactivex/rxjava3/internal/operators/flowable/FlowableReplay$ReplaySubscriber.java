package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableReplay$ReplaySubscriber<T> extends AtomicReference<tg2> implements FlowableSubscriber<T>, Disposable {
    public static final FlowableReplay$InnerSubscription[] c = new FlowableReplay$InnerSubscription[0];
    public static final FlowableReplay$InnerSubscription[] e = new FlowableReplay$InnerSubscription[0];
    private static final long serialVersionUID = 7224554242710036740L;
    final k0 buffer;
    final AtomicReference<FlowableReplay$ReplaySubscriber<T>> current;
    boolean done;
    long requestedFromUpstream;
    final AtomicInteger management = new AtomicInteger();
    final AtomicReference<FlowableReplay$InnerSubscription<T>[]> subscribers = new AtomicReference<>(c);
    final AtomicBoolean shouldConnect = new AtomicBoolean();

    public FlowableReplay$ReplaySubscriber(k0 k0Var, AtomicReference atomicReference) {
        this.buffer = k0Var;
        this.current = atomicReference;
    }

    public final void a() {
        AtomicInteger atomicInteger = this.management;
        if (atomicInteger.getAndIncrement() != 0) {
            return;
        }
        int iAddAndGet = 1;
        while (!isDisposed()) {
            tg2 tg2Var = get();
            if (tg2Var != null) {
                long j = this.requestedFromUpstream;
                long jMax = j;
                for (FlowableReplay$InnerSubscription<T> flowableReplay$InnerSubscription : this.subscribers.get()) {
                    jMax = Math.max(jMax, flowableReplay$InnerSubscription.totalRequested.get());
                }
                long j2 = jMax - j;
                if (j2 != 0) {
                    this.requestedFromUpstream = jMax;
                    tg2Var.a(j2);
                }
            }
            iAddAndGet = atomicInteger.addAndGet(-iAddAndGet);
            if (iAddAndGet == 0) {
                return;
            }
        }
    }

    public final void b(FlowableReplay$InnerSubscription flowableReplay$InnerSubscription) {
        FlowableReplay$InnerSubscription<T>[] flowableReplay$InnerSubscriptionArr;
        while (true) {
            FlowableReplay$InnerSubscription<T>[] flowableReplay$InnerSubscriptionArr2 = this.subscribers.get();
            int length = flowableReplay$InnerSubscriptionArr2.length;
            if (length == 0) {
                return;
            }
            int i = 0;
            while (true) {
                if (i >= length) {
                    i = -1;
                    break;
                } else if (flowableReplay$InnerSubscriptionArr2[i].equals(flowableReplay$InnerSubscription)) {
                    break;
                } else {
                    i++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                flowableReplay$InnerSubscriptionArr = c;
            } else {
                FlowableReplay$InnerSubscription<T>[] flowableReplay$InnerSubscriptionArr3 = new FlowableReplay$InnerSubscription[length - 1];
                System.arraycopy(flowableReplay$InnerSubscriptionArr2, 0, flowableReplay$InnerSubscriptionArr3, 0, i);
                System.arraycopy(flowableReplay$InnerSubscriptionArr2, i + 1, flowableReplay$InnerSubscriptionArr3, i, (length - i) - 1);
                flowableReplay$InnerSubscriptionArr = flowableReplay$InnerSubscriptionArr3;
            }
            AtomicReference<FlowableReplay$InnerSubscription<T>[]> atomicReference = this.subscribers;
            while (!atomicReference.compareAndSet(flowableReplay$InnerSubscriptionArr2, flowableReplay$InnerSubscriptionArr)) {
                if (atomicReference.get() != flowableReplay$InnerSubscriptionArr2) {
                    break;
                }
            }
            return;
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.subscribers.set(e);
        AtomicReference<FlowableReplay$ReplaySubscriber<T>> atomicReference = this.current;
        while (!atomicReference.compareAndSet(this, null) && atomicReference.get() == this) {
        }
        SubscriptionHelper.b(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.subscribers.get() == e;
    }

    @Override // p.rg2
    public final void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        this.buffer.a();
        for (FlowableReplay$InnerSubscription<T> flowableReplay$InnerSubscription : this.subscribers.getAndSet(e)) {
            this.buffer.h(flowableReplay$InnerSubscription);
        }
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.b(th);
            return;
        }
        this.done = true;
        this.buffer.e(th);
        for (FlowableReplay$InnerSubscription<T> flowableReplay$InnerSubscription : this.subscribers.getAndSet(e)) {
            this.buffer.h(flowableReplay$InnerSubscription);
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (this.done) {
            return;
        }
        this.buffer.d(obj);
        for (FlowableReplay$InnerSubscription<T> flowableReplay$InnerSubscription : this.subscribers.get()) {
            this.buffer.h(flowableReplay$InnerSubscription);
        }
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.e(this, tg2Var)) {
            a();
            for (FlowableReplay$InnerSubscription<T> flowableReplay$InnerSubscription : this.subscribers.get()) {
                this.buffer.h(flowableReplay$InnerSubscription);
            }
        }
    }
}
