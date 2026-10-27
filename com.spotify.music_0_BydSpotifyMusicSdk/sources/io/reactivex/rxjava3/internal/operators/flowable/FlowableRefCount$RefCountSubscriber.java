package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableRefCount$RefCountSubscriber<T> extends AtomicBoolean implements FlowableSubscriber<T>, tg2 {
    private static final long serialVersionUID = -7419642935409022375L;
    final FlowableRefCount$RefConnection connection;
    final rg2 downstream;
    final h0 parent;
    tg2 upstream;

    public FlowableRefCount$RefCountSubscriber(rg2 rg2Var, h0 h0Var, FlowableRefCount$RefConnection flowableRefCount$RefConnection) {
        this.downstream = rg2Var;
        this.parent = h0Var;
        this.connection = flowableRefCount$RefConnection;
    }

    @Override // p.tg2
    public final void a(long j) {
        this.upstream.a(j);
    }

    @Override // p.tg2
    public final void cancel() {
        this.upstream.cancel();
        if (compareAndSet(false, true)) {
            h0 h0Var = this.parent;
            FlowableRefCount$RefConnection flowableRefCount$RefConnection = this.connection;
            synchronized (h0Var) {
                try {
                    FlowableRefCount$RefConnection flowableRefCount$RefConnection2 = h0Var.l;
                    if (flowableRefCount$RefConnection2 != null && flowableRefCount$RefConnection2 == flowableRefCount$RefConnection) {
                        long j = flowableRefCount$RefConnection.subscriberCount - 1;
                        flowableRefCount$RefConnection.subscriberCount = j;
                        if (j == 0 && flowableRefCount$RefConnection.connected) {
                            h0Var.p(flowableRefCount$RefConnection);
                        }
                    }
                } finally {
                }
            }
        }
    }

    @Override // p.rg2
    public final void onComplete() {
        if (compareAndSet(false, true)) {
            this.parent.o(this.connection);
            this.downstream.onComplete();
        }
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        if (!compareAndSet(false, true)) {
            RxJavaPlugins.b(th);
        } else {
            this.parent.o(this.connection);
            this.downstream.onError(th);
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        this.downstream.onNext(obj);
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.g(this.upstream, tg2Var)) {
            this.upstream = tg2Var;
            this.downstream.onSubscribe(this);
        }
    }
}
