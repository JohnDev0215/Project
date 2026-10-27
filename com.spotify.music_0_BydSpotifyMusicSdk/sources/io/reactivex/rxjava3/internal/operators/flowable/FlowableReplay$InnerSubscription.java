package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicLong;
import p.ci1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableReplay$InnerSubscription<T> extends AtomicLong implements tg2, Disposable {
    private static final long serialVersionUID = -4453897557930727610L;
    final rg2 child;
    boolean emitting;
    Object index;
    boolean missed;
    final FlowableReplay$ReplaySubscriber<T> parent;
    final AtomicLong totalRequested = new AtomicLong();

    public FlowableReplay$InnerSubscription(FlowableReplay$ReplaySubscriber flowableReplay$ReplaySubscriber, rg2 rg2Var) {
        this.parent = flowableReplay$ReplaySubscriber;
        this.child = rg2Var;
    }

    @Override // p.tg2
    public final void a(long j) {
        long j2;
        if (SubscriptionHelper.f(j)) {
            while (true) {
                j2 = get();
                if (j2 == Long.MIN_VALUE) {
                    j2 = Long.MIN_VALUE;
                    break;
                } else if (j2 == Long.MAX_VALUE) {
                    j2 = Long.MAX_VALUE;
                    break;
                } else if (compareAndSet(j2, ci1.e(j2, j))) {
                    break;
                }
            }
            if (j2 != Long.MIN_VALUE) {
                ci1.d(this.totalRequested, j);
                this.parent.a();
                this.parent.buffer.h(this);
            }
        }
    }

    @Override // p.tg2
    public final void cancel() {
        dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
            this.parent.b(this);
            this.parent.a();
            this.index = null;
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get() == Long.MIN_VALUE;
    }
}
