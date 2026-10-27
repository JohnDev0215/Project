package com.amazon.bolthttp.policy.impl;

import com.amazon.bolthttp.policy.AttemptContext;
import com.amazon.bolthttp.policy.BackoffPolicy;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ExponentialBackoffPolicy implements BackoffPolicy<AttemptContext> {
    private final long mBaseRetryMillis;
    private final double mGrowthFactor;
    private final float mJitterMillis;

    @Override // com.amazon.bolthttp.policy.BackoffPolicy
    public void before(AttemptContext attemptContext) {
    }

    public ExponentialBackoffPolicy() {
        this(500L, 2.0d, 200L);
    }

    public ExponentialBackoffPolicy(long j, double d, long j2) {
        if (j < 0) {
            throw new IllegalArgumentException("baseRetryMillis < 0");
        }
        if (d < 0.0d) {
            throw new IllegalArgumentException("growthFactor < 0");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("jitterMillis < 0");
        }
        this.mBaseRetryMillis = j;
        this.mGrowthFactor = d;
        this.mJitterMillis = j2;
    }

    @Override // com.amazon.bolthttp.policy.BackoffPolicy
    public long getBackoffMillis(@Nonnull AttemptContext attemptContext) {
        Preconditions.checkNotNull(attemptContext, "context");
        if (attemptContext.getAttemptCount() == 0) {
            return 0L;
        }
        return ((long) (this.mBaseRetryMillis * Math.pow(this.mGrowthFactor, r7 - 1))) + ((long) (Math.random() * ((double) this.mJitterMillis) * ((double) attemptContext.getAttemptCount())));
    }
}
