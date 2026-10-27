package com.amazon.avod.pmet;

import com.amazon.avod.metrics.pmet.MetricParameter;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class OriginPivot implements MetricParameter {
    private final String mOrigin;

    public OriginPivot(@Nullable String str) {
        this.mOrigin = str;
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        String str = this.mOrigin;
        return str != null ? str : PlaybackPmetMetricReporter.UNAVAILABLE_REPORTABLE_STRING.toReportableString();
    }
}
