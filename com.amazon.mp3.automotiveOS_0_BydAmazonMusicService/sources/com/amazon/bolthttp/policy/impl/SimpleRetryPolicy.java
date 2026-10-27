package com.amazon.bolthttp.policy.impl;

import com.amazon.bolthttp.policy.AttemptContext;
import com.amazon.bolthttp.policy.RetryLimitException;
import com.amazon.bolthttp.policy.RetryPolicy;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleRetryPolicy implements RetryPolicy<AttemptContext> {
    public static final long DEFAULT_RETRY_LIMIT_MILLIS = TimeUnit.SECONDS.toMillis(30);
    private final int mMaxAttempts;
    private final long mRetryLimitMillis;
    private final Set<Class<? extends Exception>> mRetryableExceptions;

    @Override // com.amazon.bolthttp.policy.RetryPolicy
    public void before(@Nonnull AttemptContext attemptContext) {
    }

    public SimpleRetryPolicy(@Nonnull Set<Class<? extends Exception>> set) {
        this(3, DEFAULT_RETRY_LIMIT_MILLIS, set);
    }

    public SimpleRetryPolicy(int i, long j, @Nonnull Set<Class<? extends Exception>> set) {
        if (i < 0) {
            throw new IllegalArgumentException("maxAttempts < 0");
        }
        if (j < 0) {
            throw new IllegalArgumentException("retryLimitMillis < 0");
        }
        this.mRetryableExceptions = (Set) Preconditions.checkNotNull(set, "retryableExceptions");
        this.mMaxAttempts = i;
        this.mRetryLimitMillis = j;
    }

    @Override // com.amazon.bolthttp.policy.RetryPolicy
    public boolean shouldRetry(@Nonnull AttemptContext attemptContext) {
        Preconditions.checkNotNull(attemptContext, "context");
        if (attemptContext.getAttemptCount() == 0) {
            return true;
        }
        return (attemptContext.getAttemptCount() < this.mMaxAttempts) && ((attemptContext.getTotalElapsedTime() > this.mRetryLimitMillis ? 1 : (attemptContext.getTotalElapsedTime() == this.mRetryLimitMillis ? 0 : -1)) < 0) && isRetryableException(attemptContext.getLastException());
    }

    @Override // com.amazon.bolthttp.policy.RetryPolicy
    public Exception getReturnedException(@Nonnull AttemptContext attemptContext) {
        Preconditions.checkNotNull(attemptContext, "context");
        Exception lastException = attemptContext.getLastException();
        return !isRetryableException(lastException) ? lastException : new RetryLimitException(String.format("Retry limit (%s, %sms) reached", Integer.valueOf(this.mMaxAttempts), Long.valueOf(this.mRetryLimitMillis)), lastException);
    }

    protected boolean isRetryableException(Exception exc) {
        Iterator<Class<? extends Exception>> it = this.mRetryableExceptions.iterator();
        while (it.hasNext()) {
            if (it.next().equals(exc.getClass())) {
                return true;
            }
        }
        return false;
    }
}
