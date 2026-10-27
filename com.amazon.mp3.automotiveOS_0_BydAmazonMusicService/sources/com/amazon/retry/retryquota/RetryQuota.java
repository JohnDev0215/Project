package com.amazon.retry.retryquota;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongUnaryOperator;

/* JADX INFO: loaded from: classes5.dex */
public class RetryQuota {
    private final Object accessLock = new Object();
    private long maxTokenCount;
    private volatile long numberOfTokens;
    private double successRequestRefillRate;

    public RetryQuota(long j, double d, long j2) {
        Preconditions.checkArgument(j >= 1, "maxRetryCount should not less than 1");
        Preconditions.checkArgument(d >= 0.0d, "initialQuota should be between [0 1]");
        Preconditions.checkArgument(d <= 1.0d, "initialQuota should be between [0 1]");
        Preconditions.checkArgument(j2 >= 1, "successRequesstRefillRate should not be less than 1");
        this.maxTokenCount = j;
        this.numberOfTokens = (long) (j * d);
        this.successRequestRefillRate = j2;
    }

    private long getActualRefillAmount(AtomicLong atomicLong) {
        long andUpdate = (long) (atomicLong.getAndUpdate(new LongUnaryOperator() { // from class: com.amazon.retry.retryquota.RetryQuota.1
            @Override // java.util.function.LongUnaryOperator
            public long applyAsLong(long j) {
                return (long) (j % RetryQuota.this.successRequestRefillRate);
            }
        }) / this.successRequestRefillRate);
        long j = this.numberOfTokens + andUpdate;
        long j2 = this.maxTokenCount;
        return j > j2 ? j2 - this.numberOfTokens : andUpdate;
    }

    public TokenBucketCapacity allowRetry(AtomicLong atomicLong) {
        TokenBucketCapacity tokenBucketCapacity;
        synchronized (this.accessLock) {
            long actualRefillAmount = getActualRefillAmount(atomicLong);
            long j = this.numberOfTokens + actualRefillAmount;
            this.numberOfTokens = j > 0 ? j - 1 : 0L;
            tokenBucketCapacity = new TokenBucketCapacity(j, actualRefillAmount);
        }
        return tokenBucketCapacity;
    }

    public TokenBucketCapacity getCapacity(AtomicLong atomicLong) {
        TokenBucketCapacity tokenBucketCapacity;
        synchronized (this.accessLock) {
            long actualRefillAmount = getActualRefillAmount(atomicLong);
            this.numberOfTokens += actualRefillAmount;
            tokenBucketCapacity = new TokenBucketCapacity(this.numberOfTokens, actualRefillAmount);
        }
        return tokenBucketCapacity;
    }
}
