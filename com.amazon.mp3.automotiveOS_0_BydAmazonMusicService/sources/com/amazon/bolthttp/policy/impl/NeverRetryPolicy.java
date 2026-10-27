package com.amazon.bolthttp.policy.impl;

import com.amazon.bolthttp.policy.AttemptContext;
import com.amazon.bolthttp.policy.RetryPolicy;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class NeverRetryPolicy implements RetryPolicy<AttemptContext> {
    @Override // com.amazon.bolthttp.policy.RetryPolicy
    public void before(@Nonnull AttemptContext attemptContext) {
    }

    @Override // com.amazon.bolthttp.policy.RetryPolicy
    public boolean shouldRetry(@Nonnull AttemptContext attemptContext) {
        Preconditions.checkNotNull(attemptContext, "context");
        return attemptContext.getAttemptCount() == 0;
    }

    @Override // com.amazon.bolthttp.policy.RetryPolicy
    @Nullable
    public Exception getReturnedException(@Nonnull AttemptContext attemptContext) {
        Preconditions.checkNotNull(attemptContext, "context");
        return attemptContext.getLastException();
    }
}
