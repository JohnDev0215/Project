package com.amazon.avod.media.events.clientapi;

import com.amazon.avod.media.events.model.MediaEvent;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = Builder.class)
public class BootstrapConfig {
    private final String mAppInstanceId;
    private final BatchConfig mBatching;
    private final Map<MediaEvent.MediaEventType, EventConfig> mEventConfig;
    private final RetryConfig mRetry;

    private BootstrapConfig(Builder builder) {
        this.mAppInstanceId = builder.mAppInstanceId;
        this.mBatching = builder.mBatchConfig;
        this.mRetry = builder.mRetryConfig;
        this.mEventConfig = builder.mEventConfig;
    }

    public String getAppInstanceId() {
        return this.mAppInstanceId;
    }

    public BatchConfig getBatching() {
        return this.mBatching;
    }

    public RetryConfig getRetry() {
        return this.mRetry;
    }

    public Map<MediaEvent.MediaEventType, EventConfig> getEventConfig() {
        return this.mEventConfig;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private String mAppInstanceId;
        private BatchConfig mBatchConfig;
        private Map<MediaEvent.MediaEventType, EventConfig> mEventConfig;
        private RetryConfig mRetryConfig;

        public Builder appInstanceId(@Nonnull String str) {
            this.mAppInstanceId = (String) Preconditions.checkNotNull(str, "appInstanceId");
            return this;
        }

        public Builder batching(@Nonnull BatchConfig batchConfig) {
            this.mBatchConfig = (BatchConfig) Preconditions.checkNotNull(batchConfig, "batchConfig");
            return this;
        }

        public Builder events(@Nonnull Map<MediaEvent.MediaEventType, EventConfig> map) {
            this.mEventConfig = (Map) Preconditions.checkNotNull(map, "eventConfig");
            return this;
        }

        public Builder retry(@Nonnull RetryConfig retryConfig) {
            this.mRetryConfig = (RetryConfig) Preconditions.checkNotNull(retryConfig, "retryConfig");
            return this;
        }

        public BootstrapConfig build() {
            return new BootstrapConfig(this);
        }
    }
}
