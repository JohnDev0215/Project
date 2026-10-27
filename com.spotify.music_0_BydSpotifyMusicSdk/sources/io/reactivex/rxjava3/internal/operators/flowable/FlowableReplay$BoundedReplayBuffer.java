package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.internal.util.NotificationLite;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p.ci1;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
abstract class FlowableReplay$BoundedReplayBuffer<T> extends AtomicReference<FlowableReplay$Node> implements k0 {
    private static final long serialVersionUID = 2346567790059478686L;
    final boolean eagerTruncate;
    long index;
    int size;
    FlowableReplay$Node tail;

    public FlowableReplay$BoundedReplayBuffer(boolean z) {
        this.eagerTruncate = z;
        FlowableReplay$Node flowableReplay$Node = new FlowableReplay$Node(0L, null);
        this.tail = flowableReplay$Node;
        set(flowableReplay$Node);
    }

    @Override // io.reactivex.rxjava3.internal.operators.flowable.k0
    public final void a() {
        NotificationLite notificationLite = NotificationLite.c;
        long j = this.index + 1;
        this.index = j;
        FlowableReplay$Node flowableReplay$Node = new FlowableReplay$Node(j, notificationLite);
        this.tail.set(flowableReplay$Node);
        this.tail = flowableReplay$Node;
        this.size++;
        FlowableReplay$Node flowableReplay$Node2 = get();
        if (flowableReplay$Node2.value != null) {
            FlowableReplay$Node flowableReplay$Node3 = new FlowableReplay$Node(0L, null);
            flowableReplay$Node3.lazySet(flowableReplay$Node2.get());
            set(flowableReplay$Node3);
        }
    }

    @Override // io.reactivex.rxjava3.internal.operators.flowable.k0
    public final void d(Object obj) {
        long j = this.index + 1;
        this.index = j;
        FlowableReplay$Node flowableReplay$Node = new FlowableReplay$Node(j, obj);
        this.tail.set(flowableReplay$Node);
        this.tail = flowableReplay$Node;
        this.size++;
        FlowableReplay$SizeBoundReplayBuffer flowableReplay$SizeBoundReplayBuffer = (FlowableReplay$SizeBoundReplayBuffer) this;
        if (flowableReplay$SizeBoundReplayBuffer.size > flowableReplay$SizeBoundReplayBuffer.limit) {
            FlowableReplay$Node flowableReplay$Node2 = flowableReplay$SizeBoundReplayBuffer.get().get();
            if (flowableReplay$Node2 == null) {
                throw new IllegalStateException("Empty list!");
            }
            flowableReplay$SizeBoundReplayBuffer.size--;
            if (flowableReplay$SizeBoundReplayBuffer.eagerTruncate) {
                FlowableReplay$Node flowableReplay$Node3 = new FlowableReplay$Node(flowableReplay$Node2.index, null);
                flowableReplay$Node3.lazySet(flowableReplay$Node2.get());
                flowableReplay$Node2 = flowableReplay$Node3;
            }
            flowableReplay$SizeBoundReplayBuffer.set(flowableReplay$Node2);
        }
    }

    @Override // io.reactivex.rxjava3.internal.operators.flowable.k0
    public final void e(Throwable th) {
        Object objF = NotificationLite.f(th);
        long j = this.index + 1;
        this.index = j;
        FlowableReplay$Node flowableReplay$Node = new FlowableReplay$Node(j, objF);
        this.tail.set(flowableReplay$Node);
        this.tail = flowableReplay$Node;
        this.size++;
        FlowableReplay$Node flowableReplay$Node2 = get();
        if (flowableReplay$Node2.value != null) {
            FlowableReplay$Node flowableReplay$Node3 = new FlowableReplay$Node(0L, null);
            flowableReplay$Node3.lazySet(flowableReplay$Node2.get());
            set(flowableReplay$Node3);
        }
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
                while (true) {
                    long j = flowableReplay$InnerSubscription.get();
                    boolean z = j == Long.MAX_VALUE;
                    FlowableReplay$Node flowableReplay$Node = (FlowableReplay$Node) flowableReplay$InnerSubscription.index;
                    if (flowableReplay$Node == null) {
                        flowableReplay$Node = get();
                        flowableReplay$InnerSubscription.index = flowableReplay$Node;
                        ci1.d(flowableReplay$InnerSubscription.totalRequested, flowableReplay$Node.index);
                    }
                    long j2 = 0;
                    while (j != 0) {
                        if (!flowableReplay$InnerSubscription.isDisposed()) {
                            FlowableReplay$Node flowableReplay$Node2 = flowableReplay$Node.get();
                            if (flowableReplay$Node2 == null) {
                                break;
                            }
                            Object obj = flowableReplay$Node2.value;
                            try {
                                if (NotificationLite.b(obj, flowableReplay$InnerSubscription.child)) {
                                    flowableReplay$InnerSubscription.index = null;
                                    return;
                                } else {
                                    j2++;
                                    j--;
                                    flowableReplay$Node = flowableReplay$Node2;
                                }
                            } catch (Throwable th) {
                                he1.R(th);
                                flowableReplay$InnerSubscription.index = null;
                                flowableReplay$InnerSubscription.dispose();
                                if (NotificationLite.i(obj) || NotificationLite.h(obj)) {
                                    RxJavaPlugins.b(th);
                                    return;
                                } else {
                                    flowableReplay$InnerSubscription.child.onError(th);
                                    return;
                                }
                            }
                        } else {
                            flowableReplay$InnerSubscription.index = null;
                            return;
                        }
                    }
                    if (j == 0 && flowableReplay$InnerSubscription.isDisposed()) {
                        flowableReplay$InnerSubscription.index = null;
                        return;
                    }
                    if (j2 != 0) {
                        flowableReplay$InnerSubscription.index = flowableReplay$Node;
                        if (!z) {
                            ci1.B(flowableReplay$InnerSubscription, j2);
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
