package io.reactivex.rxjava3.internal.subscriptions;

import java.util.concurrent.atomic.AtomicBoolean;
import spi.e;
import spi.ps;

/* JADX INFO: loaded from: classes2.dex */
public final class BooleanSubscription extends AtomicBoolean implements ps {
    private static final long serialVersionUID = -8127758972444290902L;

    @Override // spi.ps
    public void cancel() {
        lazySet(true);
    }

    public boolean isCancelled() {
        return get();
    }

    @Override // spi.ps
    public void request(long j) {
        SubscriptionHelper.validate(j);
    }

    @Override // java.util.concurrent.atomic.AtomicBoolean
    public String toString() {
        StringBuilder sbK = e.k("BooleanSubscription(cancelled=");
        sbK.append(get());
        sbK.append(")");
        return sbK.toString();
    }
}
