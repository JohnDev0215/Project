package com.amazon.avod.metrics;

import com.amazon.avod.metrics.pmet.MetricParameter;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum InPlaybackRatingMetrics implements MetricParameter {
    RATING_METADATA_MISSING,
    RATING_ACTION_SHOW,
    RATING_FETCH_TIMEOUT,
    CONTENT_DESCRIPTOR_MISSING,
    CONTENT_DESCRIPTOR_SHOW;

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return name();
    }
}
