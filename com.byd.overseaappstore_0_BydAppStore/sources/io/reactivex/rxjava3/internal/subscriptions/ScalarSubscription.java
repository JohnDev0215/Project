package io.reactivex.rxjava3.internal.subscriptions;

import java.util.concurrent.atomic.AtomicInteger;
import spi.os;
import spi.sl;

/* JADX INFO: loaded from: classes2.dex */
public final class ScalarSubscription<T> extends AtomicInteger implements sl<T> {
    public static final int CANCELLED = 2;
    public static final int NO_REQUEST = 0;
    public static final int REQUESTED = 1;
    private static final long serialVersionUID = -3830916580126663321L;
    public final os<? super T> subscriber;
    public final T value;

    public ScalarSubscription(os<? super T> osVar, T t) {
        this.subscriber = osVar;
        this.value = t;
    }

    @Override // spi.ps
    public void cancel() {
        lazySet(2);
    }

    @Override // spi.kp
    public void clear() {
        lazySet(1);
    }

    public boolean isCancelled() {
        return get() == 2;
    }

    public boolean isEmpty() {
        return get() != 0;
    }

    @Override // spi.kp
    public boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public boolean offer(T t, T t2) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public T poll() {
        if (get() != 0) {
            return null;
        }
        lazySet(1);
        return this.value;
    }

    @Override // spi.ps
    public void request(long j) {
        if (SubscriptionHelper.validate(j) && compareAndSet(0, 1)) {
            os<? super T> osVar = this.subscriber;
            osVar.onNext(this.value);
            if (get() != 2) {
                osVar.onComplete();
            }
        }
    }

    @Override // spi.rl
    public int requestFusion(int i) {
        return i & 1;
    }
}
