package com.amazon.avod.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface PlaybackEventReporter {
    void appendReportingTag(@Nonnull String str, @Nonnull String str2, @Nonnull String str3);

    MetricsBuilder createMetricsBuilder();

    @Nonnull
    String getPrimitiveSessionId();

    @Nonnull
    String getUserWatchSessionId();

    void reportError(@Nonnull String str, @Nonnull String str2, @Nullable String str3);

    void reportError(@Nonnull String str, @Nonnull String str2, @Nullable String str3, @Nonnull String str4, boolean z);

    void reportFeatureAvailability(@Nonnull String str, boolean z);

    void reportMetric(@Nonnull MetricsBuilder metricsBuilder);

    void reportMetric(@Nonnull String str, @Nullable String str2, @Nullable TimeSpan timeSpan, @Nullable String str3, @Nullable String str4);
}
