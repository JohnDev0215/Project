package com.amazon.coral.retry.strategy;

import com.amazon.coral.retry.RetryStrategy;
import com.amazon.coral.retry.transiency.TransiencyStrategy;

/* JADX INFO: loaded from: classes2.dex */
public class TransiencyRetryStrategy<CONTEXT> implements RetryStrategy<CONTEXT> {
    private final TransiencyStrategy transiencyStrategy;

    public TransiencyRetryStrategy(TransiencyStrategy transiencyStrategy) {
        this.transiencyStrategy = transiencyStrategy;
    }

    @Override // com.amazon.coral.retry.RetryStrategy
    public long nextDelayMillis(long j, int i, Exception exc, CONTEXT context) {
        return this.transiencyStrategy.isTransient(exc) ? 0L : -1L;
    }
}
