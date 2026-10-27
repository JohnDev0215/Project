package io.reactivex.rxjava3.processors;

import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicLong;
import p.ci1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class PublishProcessor$PublishSubscription<T> extends AtomicLong implements tg2 {
    private static final long serialVersionUID = 3562861878281475070L;
    final rg2 downstream;
    final b parent;

    public PublishProcessor$PublishSubscription(rg2 rg2Var, b bVar) {
        this.downstream = rg2Var;
        this.parent = bVar;
    }

    @Override // p.tg2
    public final void a(long j) {
        long j2;
        if (SubscriptionHelper.f(j)) {
            do {
                j2 = get();
                if (j2 == Long.MIN_VALUE || j2 == Long.MAX_VALUE) {
                    return;
                }
            } while (!compareAndSet(j2, ci1.e(j2, j)));
        }
    }

    @Override // p.tg2
    public final void cancel() {
        if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
            this.parent.o(this);
        }
    }
}
