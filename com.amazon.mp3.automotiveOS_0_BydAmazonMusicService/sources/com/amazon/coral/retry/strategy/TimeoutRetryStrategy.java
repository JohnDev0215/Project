package com.amazon.coral.retry.strategy;

import com.amazon.coral.retry.RetryStrategy;
import com.amazon.coral.retry.sequence.Sequence;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes2.dex */
public class TimeoutRetryStrategy<CONTEXT> implements RetryStrategy<CONTEXT> {
    private final Sequence sequence;
    private final long timeout;

    public TimeoutRetryStrategy(long j, Sequence sequence) {
        this.timeout = j;
        this.sequence = sequence;
    }

    @Override // com.amazon.coral.retry.RetryStrategy
    public long nextDelayMillis(long j, int i, Exception exc, CONTEXT context) {
        long j2 = (long) this.sequence.get(i);
        if (j2 >= 0 && BigInteger.valueOf(System.currentTimeMillis()).add(BigInteger.valueOf(j2)).compareTo(BigInteger.valueOf(j).add(BigInteger.valueOf(this.timeout))) <= 0) {
            return j2;
        }
        return -1L;
    }
}
