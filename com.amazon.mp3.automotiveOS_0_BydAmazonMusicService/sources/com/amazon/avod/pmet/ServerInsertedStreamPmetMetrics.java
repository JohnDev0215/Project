package com.amazon.avod.pmet;

import com.amazon.avod.metrics.pmet.MetricParameter;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum ServerInsertedStreamPmetMetrics implements MetricParameter {
    TIME_LINE_CREATION_ERROR,
    EMPTY_AD_PLAN,
    LEARN_MORE_FAILED,
    AD_CLIP_SKIP_SEEK_INCORRECT,
    AD_CLIP_SKIP_IGNORED,
    AD_BREAK_SEEK_OVER,
    VAST_MISMATCH,
    SSAI,
    CSAI;

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return name();
    }
}
