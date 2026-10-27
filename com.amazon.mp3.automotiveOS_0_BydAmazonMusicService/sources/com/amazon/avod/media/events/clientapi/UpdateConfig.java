package com.amazon.avod.media.events.clientapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = Builder.class)
public class UpdateConfig {
    private final BatchConfig mBatching;
    private final RetryConfig mRetry;

    private UpdateConfig(Builder builder) {
        this.mBatching = builder.mBatchConfig;
        this.mRetry = builder.mRetryConfig;
    }

    public BatchConfig getBatching() {
        return this.mBatching;
    }

    public RetryConfig getRetry() {
        return this.mRetry;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private BatchConfig mBatchConfig;
        private RetryConfig mRetryConfig;

        public Builder batching(@Nonnull BatchConfig batchConfig) {
            this.mBatchConfig = (BatchConfig) Preconditions.checkNotNull(batchConfig, "batchConfig");
            return this;
        }

        public Builder retry(@Nonnull RetryConfig retryConfig) {
            this.mRetryConfig = (RetryConfig) Preconditions.checkNotNull(retryConfig, "retryConfig");
            return this;
        }

        public UpdateConfig build() {
            return new UpdateConfig(this);
        }
    }
}
