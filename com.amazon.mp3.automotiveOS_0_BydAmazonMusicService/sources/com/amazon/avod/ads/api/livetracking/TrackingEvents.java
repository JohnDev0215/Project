package com.amazon.avod.ads.api.livetracking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.base.Preconditions;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = Builder.class)
class TrackingEvents {
    private final List<BeaconUrls> mBeaconUrls;
    private double mDurationInSeconds;
    private String mEventType;
    private long mStartTimeInMilliseconds;

    @Nullable
    public List<BeaconUrls> getBeaconUrls() {
        return this.mBeaconUrls;
    }

    public double getDurationInSeconds() {
        return this.mDurationInSeconds;
    }

    @Nullable
    public String getEventType() {
        return this.mEventType;
    }

    public long getStartTimeInMilliseconds() {
        return this.mStartTimeInMilliseconds;
    }

    private TrackingEvents(Builder builder) {
        this.mBeaconUrls = builder.mBeaconUrls;
        this.mDurationInSeconds = builder.mDurationInSeconds;
        this.mEventType = builder.mEventType;
        this.mStartTimeInMilliseconds = builder.mStartTimeInMilliseconds;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private static String[] PERIOD_UNITS = {"Y", "M", "W", "D", "H", "M", "S"};
        private static boolean[] PERIOD_UNITS_IS_TIME;
        private static long[] PERIOD_UNIT_MULTIPLYER;
        private List<BeaconUrls> mBeaconUrls;
        private double mDurationInSeconds;
        private String mEventType;
        private long mStartTimeInMilliseconds;

        static {
            TimeUnit timeUnit = TimeUnit.DAYS;
            PERIOD_UNIT_MULTIPLYER = new long[]{timeUnit.toMillis(1L) * 365, timeUnit.toMillis(1L) * 30, timeUnit.toMillis(1L) * 7, timeUnit.toMillis(1L), TimeUnit.HOURS.toMillis(1L), TimeUnit.MINUTES.toMillis(1L), TimeUnit.SECONDS.toMillis(1L)};
            PERIOD_UNITS_IS_TIME = new boolean[]{false, false, false, false, true, true, true};
        }

        public Builder beaconUrls(@Nullable List<BeaconUrls> list) {
            this.mBeaconUrls = list;
            return this;
        }

        public Builder durationInSeconds(double d) {
            this.mDurationInSeconds = d;
            return this;
        }

        public Builder startTime(@Nonnull String str) {
            this.mStartTimeInMilliseconds = convertStartTimeToMilliseconds(str);
            return this;
        }

        long convertStartTimeToMilliseconds(@Nonnull String str) {
            Preconditions.checkNotNull(str, "startTime");
            int i = 0;
            Preconditions.checkArgument(str.contains("P") && str.contains("T"), "startTime is expected to contain \"P\" and \"T\"");
            String strSubstring = str.substring(str.indexOf("P") + 1, str.indexOf("T"));
            String strSubstring2 = str.substring(str.indexOf("T") + 1);
            long j = 0;
            while (true) {
                String[] strArr = PERIOD_UNITS;
                if (i >= strArr.length) {
                    return j;
                }
                String str2 = PERIOD_UNITS_IS_TIME[i] ? strSubstring2 : strSubstring;
                int iIndexOf = str2.indexOf(strArr[i]);
                if (iIndexOf >= 0) {
                    int i2 = iIndexOf - 1;
                    while (i2 > 0) {
                        int i3 = i2 - 1;
                        if (str2.charAt(i3) != '.' && (str2.charAt(i3) < '0' || str2.charAt(i3) > '9')) {
                            break;
                        }
                        i2--;
                    }
                    j += (long) (Double.parseDouble(str2.substring(i2, iIndexOf)) * PERIOD_UNIT_MULTIPLYER[i]);
                }
                i++;
            }
        }

        public Builder eventType(@Nullable String str) {
            this.mEventType = str;
            return this;
        }

        public TrackingEvents build() {
            return new TrackingEvents(this);
        }
    }
}
