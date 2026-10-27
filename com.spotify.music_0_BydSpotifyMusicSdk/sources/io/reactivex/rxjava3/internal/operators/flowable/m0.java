package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import java.util.concurrent.atomic.AtomicReference;
import p.az1;
import p.he1;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class m0 implements az1 {
    public final AtomicReference c;
    public final io.reactivex.rxjava3.functions.q e;

    public m0(AtomicReference atomicReference, io.reactivex.rxjava3.functions.q qVar) {
        this.c = atomicReference;
        this.e = qVar;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // p.az1
    public void subscribe(rg2 rg2Var) {
        FlowableReplay$ReplaySubscriber flowableReplay$ReplaySubscriber;
        loop0: while (true) {
            AtomicReference atomicReference = this.c;
            flowableReplay$ReplaySubscriber = (FlowableReplay$ReplaySubscriber) atomicReference.get();
            if (flowableReplay$ReplaySubscriber != null) {
                break;
            }
            try {
                FlowableReplay$ReplaySubscriber flowableReplay$ReplaySubscriber2 = new FlowableReplay$ReplaySubscriber((k0) this.e.get(), atomicReference);
                while (!atomicReference.compareAndSet(null, flowableReplay$ReplaySubscriber2)) {
                    if (atomicReference.get() != null) {
                        break;
                    }
                }
                flowableReplay$ReplaySubscriber = flowableReplay$ReplaySubscriber2;
                break loop0;
            } catch (Throwable th) {
                he1.R(th);
                rg2Var.onSubscribe(EmptySubscription.c);
                rg2Var.onError(th);
                return;
            }
        }
        FlowableReplay$InnerSubscription flowableReplay$InnerSubscription = new FlowableReplay$InnerSubscription(flowableReplay$ReplaySubscriber, rg2Var);
        rg2Var.onSubscribe(flowableReplay$InnerSubscription);
        loop2: while (true) {
            FlowableReplay$InnerSubscription[] flowableReplay$InnerSubscriptionArr = (FlowableReplay$InnerSubscription[]) flowableReplay$ReplaySubscriber.subscribers.get();
            if (flowableReplay$InnerSubscriptionArr != FlowableReplay$ReplaySubscriber.e) {
                int length = flowableReplay$InnerSubscriptionArr.length;
                FlowableReplay$InnerSubscription[] flowableReplay$InnerSubscriptionArr2 = new FlowableReplay$InnerSubscription[length + 1];
                System.arraycopy(flowableReplay$InnerSubscriptionArr, 0, flowableReplay$InnerSubscriptionArr2, 0, length);
                flowableReplay$InnerSubscriptionArr2[length] = flowableReplay$InnerSubscription;
                AtomicReference<FlowableReplay$InnerSubscription<T>[]> atomicReference2 = flowableReplay$ReplaySubscriber.subscribers;
                while (!atomicReference2.compareAndSet((FlowableReplay$InnerSubscription<T>[]) flowableReplay$InnerSubscriptionArr, (FlowableReplay$InnerSubscription<T>[]) flowableReplay$InnerSubscriptionArr2)) {
                    if (atomicReference2.get() != flowableReplay$InnerSubscriptionArr) {
                        break;
                    }
                }
                break loop2;
            }
            break;
        }
        if (flowableReplay$InnerSubscription.isDisposed()) {
            flowableReplay$ReplaySubscriber.b(flowableReplay$InnerSubscription);
        } else {
            flowableReplay$ReplaySubscriber.a();
            flowableReplay$ReplaySubscriber.buffer.h(flowableReplay$InnerSubscription);
        }
    }
}
