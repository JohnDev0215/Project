package com.amazon.avod.pmet;

import com.amazon.avod.metrics.pmet.MetricParameter;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class CdnPivot implements MetricParameter {
    private final String mCdn;

    public CdnPivot(@Nullable String str) {
        this.mCdn = str;
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        String str = this.mCdn;
        return str != null ? str : PlaybackPmetMetricReporter.UNAVAILABLE_REPORTABLE_STRING.toReportableString();
    }
}
