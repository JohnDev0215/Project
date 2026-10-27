package com.amazon.avod.media.events.clientapi;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@JsonDeserialize(builder = Builder.class)
public class RetryConfig {
    private final Integer mJitter;
    private final Integer mMax;
    private final Integer mModifier;

    private RetryConfig(Builder builder) {
        this.mModifier = builder.mModifier;
        this.mMax = builder.mMax;
        this.mJitter = builder.mJitter;
    }

    public Integer getModifier() {
        return this.mModifier;
    }

    public Integer getMax() {
        return this.mMax;
    }

    public Integer getJitter() {
        return this.mJitter;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private Integer mJitter;
        private Integer mMax;
        private Integer mModifier;

        public Builder modifier(@Nullable Integer num) {
            this.mModifier = num;
            return this;
        }

        public Builder max(@Nullable Integer num) {
            this.mMax = num;
            return this;
        }

        public Builder jitter(@Nullable Integer num) {
            this.mJitter = num;
            return this;
        }

        public RetryConfig build() {
            return new RetryConfig(this);
        }
    }
}
