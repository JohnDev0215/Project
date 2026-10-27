package com.amazon.avod.media.events.clientapi;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@JsonDeserialize(builder = Builder.class)
public class BatchConfig {
    private final Integer mDepth;
    private final Long mPeriod;

    private BatchConfig(Builder builder) {
        this.mPeriod = builder.mPeriod;
        this.mDepth = builder.mDepth;
    }

    public Integer getDepth() {
        return this.mDepth;
    }

    public Long getPeriod() {
        return this.mPeriod;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private Integer mDepth;
        private Long mPeriod;

        public Builder period(@Nullable Long l) {
            this.mPeriod = l;
            return this;
        }

        public Builder depth(@Nullable Integer num) {
            this.mDepth = num;
            return this;
        }

        public BatchConfig build() {
            return new BatchConfig(this);
        }
    }
}
