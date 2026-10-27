package com.amazon.avod.metrics.pmet;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum PlaybackVoiceControls implements MetricParameter {
    PLAY,
    PAUSE,
    SEEK_TO,
    SKIP_TO_NEXT,
    TOGGLE_SUBTITLE,
    TOGGLE_XRAY;

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return name();
    }
}
