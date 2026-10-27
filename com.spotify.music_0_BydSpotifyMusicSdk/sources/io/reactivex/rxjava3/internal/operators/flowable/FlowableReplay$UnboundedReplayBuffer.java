package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.internal.util.NotificationLite;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.ArrayList;
import p.ci1;
import p.he1;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableReplay$UnboundedReplayBuffer<T> extends ArrayList<Object> implements k0 {
    private static final long serialVersionUID = 7063189396499112664L;
    volatile int size;

    @Override // io.reactivex.rxjava3.internal.operators.flowable.k0
    public final void a() {
        add(NotificationLite.c);
        this.size++;
    }

    @Override // io.reactivex.rxjava3.internal.operators.flowable.k0
    public final void d(Object obj) {
        add(obj);
        this.size++;
    }

    @Override // io.reactivex.rxjava3.internal.operators.flowable.k0
    public final void e(Throwable th) {
        add(NotificationLite.f(th));
        this.size++;
    }

    @Override // io.reactivex.rxjava3.internal.operators.flowable.k0
    public final void h(FlowableReplay$InnerSubscription flowableReplay$InnerSubscription) {
        synchronized (flowableReplay$InnerSubscription) {
            try {
                if (flowableReplay$InnerSubscription.emitting) {
                    flowableReplay$InnerSubscription.missed = true;
                    return;
                }
                flowableReplay$InnerSubscription.emitting = true;
                rg2 rg2Var = flowableReplay$InnerSubscription.child;
                while (!flowableReplay$InnerSubscription.isDisposed()) {
                    int i = this.size;
                    Integer num = (Integer) flowableReplay$InnerSubscription.index;
                    int iIntValue = num != null ? num.intValue() : 0;
                    long j = flowableReplay$InnerSubscription.get();
                    long j2 = j;
                    long j3 = 0;
                    while (j2 != 0 && iIntValue < i) {
                        Object obj = get(iIntValue);
                        try {
                            if (NotificationLite.b(obj, rg2Var) || flowableReplay$InnerSubscription.isDisposed()) {
                                return;
                            }
                            iIntValue++;
                            j2--;
                            j3++;
                        } catch (Throwable th) {
                            he1.R(th);
                            flowableReplay$InnerSubscription.dispose();
                            if (NotificationLite.i(obj) || NotificationLite.h(obj)) {
                                RxJavaPlugins.b(th);
                                return;
                            } else {
                                rg2Var.onError(th);
                                return;
                            }
                        }
                    }
                    if (j3 != 0) {
                        flowableReplay$InnerSubscription.index = Integer.valueOf(iIntValue);
                        if (j != Long.MAX_VALUE) {
                            ci1.B(flowableReplay$InnerSubscription, j3);
                        }
                    }
                    synchronized (flowableReplay$InnerSubscription) {
                        try {
                            if (!flowableReplay$InnerSubscription.missed) {
                                flowableReplay$InnerSubscription.emitting = false;
                                return;
                            }
                            flowableReplay$InnerSubscription.missed = false;
                        } finally {
                        }
                    }
                }
            } finally {
            }
        }
    }
}
