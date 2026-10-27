package com.amazon.avod.pmet;

import com.amazon.avod.metrics.pmet.MetricParameter;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum LiveTrickplayMetrics$ImageDownloaderTimerMetrics implements MetricParameter {
    LATENCY,
    DOWNLOAD_TIME,
    THROUGHPUT,
    TIME_TO_READY,
    TIME_TO_FINISH,
    IMAGE_DOWNLOAD_SUMMARY;

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return name();
    }
}
