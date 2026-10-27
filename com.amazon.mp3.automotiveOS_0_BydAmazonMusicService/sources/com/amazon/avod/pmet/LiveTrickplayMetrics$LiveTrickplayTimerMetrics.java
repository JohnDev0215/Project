package com.amazon.avod.pmet;

import com.amazon.avod.metrics.pmet.MetricParameter;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum LiveTrickplayMetrics$LiveTrickplayTimerMetrics implements MetricParameter {
    TIME_TO_FIRST_SEEK,
    IMAGE_LATENCY,
    IMAGE_FIDELITY,
    IMAGE_UPDATE_FAILURE_PCT,
    SCRUBBING_DURATION,
    PLAYBACK_POSITION_CHANGE,
    TRICKPLAY_SCRUBBING_SUMMARY,
    NUM_IMAGE_QUALITY_FLIP,
    IMAGE_WIDTH;

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return name();
    }
}
