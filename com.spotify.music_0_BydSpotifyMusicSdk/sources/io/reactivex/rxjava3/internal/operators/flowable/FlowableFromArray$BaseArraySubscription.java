package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import java.util.Objects;
import p.ci1;

/* JADX INFO: loaded from: classes.dex */
abstract class FlowableFromArray$BaseArraySubscription<T> extends BasicQueueSubscription<T> {
    private static final long serialVersionUID = -2252972430506210021L;
    final T[] array;
    volatile boolean cancelled;
    int index;

    /* JADX WARN: Multi-variable type inference failed */
    public FlowableFromArray$BaseArraySubscription(Object[] objArr) {
        this.array = objArr;
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
        this.index = this.array.length;
    }

    public abstract void d(long j);

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return this.index == this.array.length;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        int i = this.index;
        T[] tArr = this.array;
        if (i == tArr.length) {
            return null;
        }
        this.index = i + 1;
        T t = tArr[i];
        Objects.requireNonNull(t, "array element is null");
        return t;
    }
}
