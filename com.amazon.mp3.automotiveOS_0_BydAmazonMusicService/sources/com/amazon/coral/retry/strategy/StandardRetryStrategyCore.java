package com.amazon.coral.retry.strategy;

import com.amazon.coral.retry.CallListener;
import com.amazon.coral.retry.RetryContext;
import com.amazon.coral.retry.RetryStrategy;
import com.amazon.retry.retryquota.RetryQuotaWrapper;
import com.amazon.retry.retryquota.TokenBucketCapacity;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes2.dex */
final class StandardRetryStrategyCore implements RetryStrategy<RetryContext>, CallListener {
    private static final ConcurrentHashMap<RetryContext, RetryQuotaWrapper> faultQuota = new ConcurrentHashMap<>();
    private final long faultQuotaRefillRate;
    private final int maxTokenCount;
    private final boolean strictMode;

    StandardRetryStrategyCore(long j, int i, boolean z) {
        this.faultQuotaRefillRate = j;
        this.maxTokenCount = i;
        this.strictMode = z;
    }

    @Override // com.amazon.coral.retry.CallListener
    public void onSuccess(RetryContext retryContext) {
        faultQuota.get(retryContext).recordSuccess();
    }

    @Override // com.amazon.coral.retry.CallListener
    public void onAttemptBegin(RetryContext retryContext) {
        ConcurrentHashMap<RetryContext, RetryQuotaWrapper> concurrentHashMap = faultQuota;
        if (concurrentHashMap.containsKey(retryContext)) {
            return;
        }
        concurrentHashMap.putIfAbsent(retryContext, new RetryQuotaWrapper(this.maxTokenCount, 1.0d, this.faultQuotaRefillRate));
    }

    @Override // com.amazon.coral.retry.RetryStrategy
    public long nextDelayMillis(long j, int i, Exception exc, RetryContext retryContext) {
        TokenBucketCapacity capacity;
        if (retryContext == null) {
            throw new NullPointerException("A RetryContext is required to use StandardRetryStrategy. If using RetryingCallable, pass an instance of com.amazon.coral.retry.RetryContext to the constructor.");
        }
        Predicate<Throwable> availabilityInspector = retryContext.getAvailabilityInspector();
        if (this.strictMode || availabilityInspector == null || !availabilityInspector.test(exc)) {
            capacity = faultQuota.get(retryContext).allowRetryAndGetCapacity();
        } else {
            capacity = faultQuota.get(retryContext).getCapacity();
        }
        boolean z = capacity.tokenGranted();
        Consumer<String> statCounter = retryContext.getStatCounter();
        if (statCounter != null) {
            if (z) {
                statCounter.accept("StandardRetryFaultTokenGranted");
            } else {
                statCounter.accept("StandardRetryFaultTokenDenied");
            }
        }
        return z ? 0L : -1L;
    }
}
