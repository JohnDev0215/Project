package com.amazon.bolthttp.policy.impl;

import com.amazon.bolthttp.policy.AttemptContext;
import com.amazon.bolthttp.policy.BackoffPolicy;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ConstantBackoffPolicy implements BackoffPolicy<AttemptContext> {
    private final long mRetryMillis;

    @Override // com.amazon.bolthttp.policy.BackoffPolicy
    public void before(AttemptContext attemptContext) {
    }

    public ConstantBackoffPolicy() {
        this(200L);
    }

    public ConstantBackoffPolicy(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("retryMillis < 0");
        }
        this.mRetryMillis = j;
    }

    @Override // com.amazon.bolthttp.policy.BackoffPolicy
    public long getBackoffMillis(@Nonnull AttemptContext attemptContext) {
        Preconditions.checkNotNull(attemptContext, "context");
        if (attemptContext.getAttemptCount() == 0) {
            return 0L;
        }
        return this.mRetryMillis;
    }
}
