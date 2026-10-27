package com.amazon.avod.media;

import com.amazon.avod.metrics.pmet.MetricParameter;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum MediaQuality implements MetricParameter {
    LOWEST(1, "DataSaver"),
    LOW(2, "Good"),
    MEDIUM(3, "Better"),
    HIGH(4, "Best"),
    HIGHEST(5, "Unrestricted");

    private final String mMetricName;
    private final int mValue;

    MediaQuality(int i, @Nonnull String str) {
        this.mValue = i;
        this.mMetricName = (String) Preconditions.checkNotNull(str, "metricName");
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return this.mMetricName;
    }

    @Nonnull
    public static MediaQuality getOptionFromInt(int i) {
        MediaQuality mediaQuality = LOW;
        for (MediaQuality mediaQuality2 : values()) {
            if (mediaQuality2.mValue == i) {
                return mediaQuality2;
            }
        }
        return mediaQuality;
    }
}
