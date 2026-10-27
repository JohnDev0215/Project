package com.amazon.coral.retry.strategy;

import com.amazon.coral.retry.RetryContext;
import com.amazon.coral.retry.RetryStrategy;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class RetryStrategyChain<CONTEXT> implements RetryStrategy<CONTEXT> {
    private final RetryStrategy<RetryContext> retrofitRetryStrategy;
    private final List<RetryStrategy<CONTEXT>> strategies;

    public RetryStrategyChain(List<RetryStrategy<CONTEXT>> list) {
        this.strategies = list;
        this.retrofitRetryStrategy = null;
    }

    RetryStrategyChain(List<RetryStrategy<CONTEXT>> list, RetryStrategy<RetryContext> retryStrategy) {
        this.strategies = list;
        this.retrofitRetryStrategy = retryStrategy;
    }

    @Override // com.amazon.coral.retry.RetryStrategy
    public long nextDelayMillis(long j, int i, Exception exc, CONTEXT context) {
        Iterator<RetryStrategy<CONTEXT>> it = this.strategies.iterator();
        long jMax = -1;
        while (it.hasNext()) {
            long jNextDelayMillis = it.next().nextDelayMillis(j, i, exc, context);
            if (jNextDelayMillis == -1) {
                return jNextDelayMillis;
            }
            jMax = Math.max(jMax, jNextDelayMillis);
        }
        return jMax;
    }
}
