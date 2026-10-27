package com.amazon.retry.retryquota;

import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes5.dex */
public class RetryQuotaWrapper {
    private RetryQuota retryQuota;
    private AtomicLong successCount = new AtomicLong();

    public RetryQuotaWrapper(long j, double d, long j2) {
        this.retryQuota = new RetryQuota(j, d, j2);
    }

    public void recordSuccess() {
        this.successCount.incrementAndGet();
    }

    public TokenBucketCapacity allowRetryAndGetCapacity() {
        return this.retryQuota.allowRetry(this.successCount);
    }

    public TokenBucketCapacity getCapacity() {
        return this.retryQuota.getCapacity(this.successCount);
    }
}
