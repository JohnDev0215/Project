package com.amazon.retry.retryquota;

/* JADX INFO: loaded from: classes5.dex */
public final class TokenBucketCapacity {
    private final long availableTokens;
    private final long refillAmount;

    public TokenBucketCapacity(long j, long j2) {
        this.availableTokens = j;
        this.refillAmount = j2;
    }

    public boolean tokenGranted() {
        return this.availableTokens > 0;
    }
}
