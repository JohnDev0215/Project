package io.reactivex.rxjava3.internal.subscribers;

import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicLong;
import spi.os;
import spi.ps;
import spi.z0;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SinglePostCompleteSubscriber<T, R> extends AtomicLong implements os, ps {
    public static final long COMPLETE_MASK = Long.MIN_VALUE;
    public static final long REQUEST_MASK = Long.MAX_VALUE;
    private static final long serialVersionUID = 7917814472626990048L;
    public final os<? super R> downstream;
    public long produced;
    public ps upstream;
    public R value;

    public SinglePostCompleteSubscriber(os<? super R> osVar) {
        this.downstream = osVar;
    }

    @Override // spi.ps
    public void cancel() {
        this.upstream.cancel();
    }

    public final void complete(R r) {
        long j = this.produced;
        if (j != 0) {
            z0.c(this, j);
        }
        while (true) {
            long j2 = get();
            if ((j2 & Long.MIN_VALUE) != 0) {
                onDrop(r);
                return;
            }
            if ((j2 & Long.MAX_VALUE) != 0) {
                lazySet(-9223372036854775807L);
                this.downstream.onNext(r);
                this.downstream.onComplete();
                return;
            } else {
                this.value = r;
                if (compareAndSet(0L, Long.MIN_VALUE)) {
                    return;
                } else {
                    this.value = null;
                }
            }
        }
    }

    @Override // spi.os
    public abstract /* synthetic */ void onComplete();

    public void onDrop(R r) {
    }

    @Override // spi.os
    public abstract /* synthetic */ void onError(Throwable th);

    @Override // spi.os
    public abstract /* synthetic */ void onNext(T t);

    @Override // spi.os
    public void onSubscribe(ps psVar) {
        if (SubscriptionHelper.validate(this.upstream, psVar)) {
            this.upstream = psVar;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // spi.ps
    public final void request(long j) {
        long j2;
        if (SubscriptionHelper.validate(j)) {
            do {
                j2 = get();
                if ((j2 & Long.MIN_VALUE) != 0) {
                    if (compareAndSet(Long.MIN_VALUE, -9223372036854775807L)) {
                        this.downstream.onNext(this.value);
                        this.downstream.onComplete();
                        return;
                    }
                    return;
                }
            } while (!compareAndSet(j2, z0.b(j2, j)));
            this.upstream.request(j);
        }
    }
}
