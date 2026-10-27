package com.amazon.bolthttp;

import com.amazon.bolthttp.policy.BackoffPolicy;
import com.amazon.bolthttp.policy.NetworkContext;
import com.amazon.bolthttp.policy.RetryPolicy;
import com.amazon.bolthttp.policy.TimeoutPolicy;
import com.amazon.bolthttp.policy.impl.ConstantBackoffPolicy;
import com.amazon.bolthttp.policy.impl.Retryability;
import com.amazon.bolthttp.policy.impl.SimpleRetryPolicy;
import com.amazon.bolthttp.policy.impl.SimpleTimeoutPolicy;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class NetworkStrategy {
    private final BackoffPolicy<? super NetworkContext> mBackoffPolicy;
    private final RetryPolicy<? super NetworkContext> mRetryPolicy;
    private final TimeoutPolicy<? super NetworkContext> mTimeoutPolicy;
    public static final RetryPolicy<? super NetworkContext> DEFAULT_RETRY = new SimpleRetryPolicy(Retryability.SAFE_RETRYABLE_NETWORK_EXCEPTIONS);
    public static final BackoffPolicy<? super NetworkContext> DEFAULT_BACKOFF = new ConstantBackoffPolicy();
    public static final TimeoutPolicy<? super NetworkContext> DEFAULT_TIMEOUT = new SimpleTimeoutPolicy();

    private NetworkStrategy(Builder builder) {
        this.mRetryPolicy = builder.mRetryPolicy;
        this.mBackoffPolicy = builder.mBackoffPolicy;
        this.mTimeoutPolicy = builder.mTimeoutPolicy;
    }

    @Nonnull
    public RetryPolicy<? super NetworkContext> getRetryPolicy() {
        return this.mRetryPolicy;
    }

    @Nonnull
    public BackoffPolicy<? super NetworkContext> getBackoffPolicy() {
        return this.mBackoffPolicy;
    }

    @Nonnull
    public TimeoutPolicy<? super NetworkContext> getTimeoutPolicy() {
        return this.mTimeoutPolicy;
    }

    @Nonnull
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private BackoffPolicy<? super NetworkContext> mBackoffPolicy;
        private RetryPolicy<? super NetworkContext> mRetryPolicy;
        private TimeoutPolicy<? super NetworkContext> mTimeoutPolicy;

        private Builder() {
            this.mRetryPolicy = NetworkStrategy.DEFAULT_RETRY;
            this.mBackoffPolicy = NetworkStrategy.DEFAULT_BACKOFF;
            this.mTimeoutPolicy = NetworkStrategy.DEFAULT_TIMEOUT;
        }

        public Builder setRetryPolicy(@Nonnull RetryPolicy<? super NetworkContext> retryPolicy) {
            this.mRetryPolicy = (RetryPolicy) Preconditions.checkNotNull(retryPolicy, "retryPolicy");
            return this;
        }

        public Builder setTimeoutPolicy(@Nonnull TimeoutPolicy<? super NetworkContext> timeoutPolicy) {
            this.mTimeoutPolicy = (TimeoutPolicy) Preconditions.checkNotNull(timeoutPolicy, "timeoutPolicy");
            return this;
        }

        @Nonnull
        public NetworkStrategy build() {
            return new NetworkStrategy(this);
        }
    }
}
