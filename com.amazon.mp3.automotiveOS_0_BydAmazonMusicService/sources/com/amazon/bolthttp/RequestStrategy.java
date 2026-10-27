package com.amazon.bolthttp;

import com.amazon.bolthttp.policy.BackoffPolicy;
import com.amazon.bolthttp.policy.RequestContext;
import com.amazon.bolthttp.policy.RetryPolicy;
import com.amazon.bolthttp.policy.impl.ExponentialBackoffPolicy;
import com.amazon.bolthttp.policy.impl.NeverRetryPolicy;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class RequestStrategy {
    private final BackoffPolicy<? super RequestContext> mBackoffPolicy;
    private final RetryPolicy<? super RequestContext> mRetryPolicy;
    public static final RetryPolicy<? super RequestContext> DEFAULT_RETRY = new NeverRetryPolicy();
    public static final BackoffPolicy<? super RequestContext> DEFAULT_BACKOFF = new ExponentialBackoffPolicy();

    private RequestStrategy(Builder builder) {
        this.mRetryPolicy = builder.mRetryPolicy;
        this.mBackoffPolicy = builder.mBackoffPolicy;
    }

    @Nonnull
    public RetryPolicy<? super RequestContext> getRetryPolicy() {
        return this.mRetryPolicy;
    }

    @Nonnull
    public BackoffPolicy<? super RequestContext> getBackoffPolicy() {
        return this.mBackoffPolicy;
    }

    @Nonnull
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private BackoffPolicy<? super RequestContext> mBackoffPolicy;
        private RetryPolicy<? super RequestContext> mRetryPolicy;

        private Builder() {
            this.mRetryPolicy = RequestStrategy.DEFAULT_RETRY;
            this.mBackoffPolicy = RequestStrategy.DEFAULT_BACKOFF;
        }

        public Builder setRetryPolicy(@Nonnull RetryPolicy<? super RequestContext> retryPolicy) {
            this.mRetryPolicy = (RetryPolicy) Preconditions.checkNotNull(retryPolicy, "retryPolicy");
            return this;
        }

        public Builder setBackoffPolicy(@Nonnull BackoffPolicy<? super RequestContext> backoffPolicy) {
            this.mBackoffPolicy = (BackoffPolicy) Preconditions.checkNotNull(backoffPolicy, "backoffPolicy");
            return this;
        }

        @Nonnull
        public RequestStrategy build() {
            return new RequestStrategy(this);
        }
    }
}
