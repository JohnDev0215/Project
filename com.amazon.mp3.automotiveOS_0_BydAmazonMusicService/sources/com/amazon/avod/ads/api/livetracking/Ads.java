package com.amazon.avod.ads.api.livetracking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = Builder.class)
class Ads {
    private final double mDurationInSeconds;
    private final double mStartTimeInSeconds;
    private final List<TrackingEvents> mTrackingEvents;

    private Ads(Builder builder) {
        this.mDurationInSeconds = builder.mDurationInSeconds;
        this.mStartTimeInSeconds = builder.mStartTimeInSeconds;
        this.mTrackingEvents = builder.mTrackingEvents;
    }

    public double getDurationInSeconds() {
        return this.mDurationInSeconds;
    }

    public double getStartTimeInSeconds() {
        return this.mStartTimeInSeconds;
    }

    @Nullable
    public List<TrackingEvents> getTrackingEvents() {
        return this.mTrackingEvents;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private double mDurationInSeconds;
        private double mStartTimeInSeconds;
        private List<TrackingEvents> mTrackingEvents;

        public Builder durationInSeconds(double d) {
            this.mDurationInSeconds = d;
            return this;
        }

        public Builder startTimeInSeconds(double d) {
            this.mStartTimeInSeconds = d;
            return this;
        }

        public Builder trackingEvents(@Nullable List<TrackingEvents> list) {
            this.mTrackingEvents = list;
            return this;
        }

        public Ads build() {
            return new Ads(this);
        }
    }
}
