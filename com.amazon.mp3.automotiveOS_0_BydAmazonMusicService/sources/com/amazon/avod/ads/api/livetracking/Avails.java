package com.amazon.avod.ads.api.livetracking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = Builder.class)
class Avails {
    private final List<Ads> mAds;
    private final double mDurationInSeconds;
    private final double mStartTimeInSeconds;

    @Nullable
    public List<Ads> getAds() {
        return this.mAds;
    }

    private Avails(Builder builder) {
        this.mAds = builder.mAds;
        this.mDurationInSeconds = builder.mDurationInSeconds;
        this.mStartTimeInSeconds = builder.mStartTimeInSeconds;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private List<Ads> mAds;
        private double mDurationInSeconds;
        private double mStartTimeInSeconds;

        public Builder ads(@Nullable List<Ads> list) {
            this.mAds = list;
            return this;
        }

        public Builder durationInSeconds(double d) {
            this.mDurationInSeconds = d;
            return this;
        }

        public Builder startTimeInSeconds(double d) {
            this.mStartTimeInSeconds = d;
            return this;
        }

        public Avails build() {
            return new Avails(this);
        }
    }
}
