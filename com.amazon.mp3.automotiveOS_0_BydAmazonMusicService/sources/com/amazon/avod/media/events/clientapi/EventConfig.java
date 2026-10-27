package com.amazon.avod.media.events.clientapi;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@JsonDeserialize(builder = Builder.class)
public class EventConfig {
    private final boolean mEnabled;
    private final boolean mForceDispatch;
    private final Integer mPeriod;

    private EventConfig(Builder builder) {
        this.mPeriod = builder.mPeriod;
        this.mForceDispatch = builder.mForceDispatch != null ? builder.mForceDispatch.booleanValue() : false;
        this.mEnabled = builder.mEnabled != null ? builder.mEnabled.booleanValue() : true;
    }

    @Nullable
    public Integer getPeriod() {
        return this.mPeriod;
    }

    public boolean getEnabled() {
        return this.mEnabled;
    }

    public boolean getForceDispatch() {
        return this.mForceDispatch;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private Boolean mEnabled;
        private Boolean mForceDispatch;
        private Integer mPeriod;

        public Builder period(@Nullable Integer num) {
            this.mPeriod = num;
            return this;
        }

        public Builder forceDispatch(@Nullable Boolean bool) {
            this.mForceDispatch = bool;
            return this;
        }

        public Builder enable(@Nullable Boolean bool) {
            this.mEnabled = bool;
            return this;
        }

        public EventConfig build() {
            return new EventConfig(this);
        }
    }
}
