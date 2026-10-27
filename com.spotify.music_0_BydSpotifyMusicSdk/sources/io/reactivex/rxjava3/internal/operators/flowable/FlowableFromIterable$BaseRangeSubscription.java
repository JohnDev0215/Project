package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import java.util.Iterator;
import java.util.Objects;
import p.ci1;

/* JADX INFO: loaded from: classes.dex */
abstract class FlowableFromIterable$BaseRangeSubscription<T> extends BasicQueueSubscription<T> {
    private static final long serialVersionUID = -2252972430506210021L;
    volatile boolean cancelled;
    Iterator<? extends T> iterator;
    boolean once;

    public FlowableFromIterable$BaseRangeSubscription(Iterator it) {
        this.iterator = it;
    }

    @Override // p.tg2
    public final void a(long j) {
        if (SubscriptionHelper.f(j) && ci1.d(this, j) == 0) {
            if (j == Long.MAX_VALUE) {
                c();
            } else {
                d(j);
            }
        }
    }

    @Override // io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        return i & 1;
    }

    public abstract void c();

    @Override // p.tg2
    public final void cancel() {
        this.cancelled = true;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
        this.iterator = null;
    }

    public abstract void d(long j);

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        Iterator<? extends T> it = this.iterator;
        if (it == null) {
            return true;
        }
        if (!this.once || it.hasNext()) {
            return false;
        }
        this.iterator = null;
        return true;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        Iterator<? extends T> it = this.iterator;
        if (it == null) {
            return null;
        }
        if (!this.once) {
            this.once = true;
        } else if (!it.hasNext()) {
            return null;
        }
        T next = this.iterator.next();
        Objects.requireNonNull(next, "Iterator.next() returned a null value");
        return next;
    }
}
