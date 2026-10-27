package com.amazon.avod.media.framework.retry;

import com.amazon.avod.util.BackoffPolicy;
import java.util.concurrent.Callable;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class BackoffPolicySupportedRetriableCall<T> extends RetriableCall<T> {
    private final BackoffPolicy mBackoffPolicy;

    public BackoffPolicySupportedRetriableCall(Callable<T> callable, RetryPolicy<T> retryPolicy, int i, RetryListener<T> retryListener, int i2, @Nullable BackoffPolicy backoffPolicy) {
        super(callable, retryPolicy, i, retryListener, i2);
        this.mBackoffPolicy = backoffPolicy;
    }

    @Override // com.amazon.avod.media.framework.retry.RetriableCall
    protected void delayForTry(int i) throws InterruptedException {
        BackoffPolicy backoffPolicy = this.mBackoffPolicy;
        if (backoffPolicy != null) {
            super.delayForTry(backoffPolicy.nextBackoffMillis());
        } else {
            super.delayForTry(i);
        }
    }
}
