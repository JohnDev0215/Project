package com.amazon.avod.playbackclient.reporting;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.download.plugin.reporting.PluginErrorType;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.PlaybackEventReporterBase;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class MetricEventReporter extends PlaybackEventReporterBase {
    private final PlaybackEventReporter mPlaybackEventReporter;

    public static class Factory {
        public MetricEventReporter createMetricEventReporter(@Nonnull PlaybackEventReporter playbackEventReporter) {
            return new MetricEventReporter(playbackEventReporter);
        }
    }

    MetricEventReporter(@Nonnull PlaybackEventReporter playbackEventReporter) {
        this.mPlaybackEventReporter = (PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter, "playbackEventReporter");
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public void reportMetric(@Nonnull String str, @Nullable String str2, @Nullable TimeSpan timeSpan, @Nullable String str3, @Nullable String str4) {
        this.mPlaybackEventReporter.reportMetric(str, str2, timeSpan, str3, str4);
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public void reportError(@Nonnull String str, @Nonnull String str2, @Nullable String str3) {
        this.mPlaybackEventReporter.reportError(str, str2, str3);
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public void reportError(@Nonnull String str, @Nonnull String str2, @Nullable String str3, @Nonnull String str4, boolean z) {
        this.mPlaybackEventReporter.reportError(str, str2, str3, str4, z);
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public void reportFeatureAvailability(@Nonnull String str, boolean z) {
        this.mPlaybackEventReporter.reportFeatureAvailability(str, z);
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public void appendReportingTag(@Nonnull String str, @Nonnull String str2, @Nonnull String str3) {
        this.mPlaybackEventReporter.appendReportingTag(str, str2, str3);
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    @Nonnull
    public String getUserWatchSessionId() {
        return this.mPlaybackEventReporter.getUserWatchSessionId();
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    @Nonnull
    public String getPrimitiveSessionId() {
        return this.mPlaybackEventReporter.getPrimitiveSessionId();
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public MetricsBuilder createMetricsBuilder() {
        return this.mPlaybackEventReporter.createMetricsBuilder();
    }

    public void reportPluginAvailability(@Nonnull String str, boolean z) {
        this.mPlaybackEventReporter.reportFeatureAvailability(str, z);
    }

    public void reportPluginDownloadSuccessful(@Nonnull String str, @Nonnull TimeSpan timeSpan, @Nullable String str2) {
        Preconditions.checkNotNull(timeSpan, "elapsedTime");
        this.mPlaybackEventReporter.reportMetric(str, "DownloadSuccessful", timeSpan, str2, null);
    }

    public void reportPluginError(@Nonnull String str, @Nonnull PluginErrorType pluginErrorType, @Nonnull String str2, @Nullable String str3) {
        Preconditions.checkNotNull(str, "eventType");
        Preconditions.checkNotNull(pluginErrorType, "errorType");
        this.mPlaybackEventReporter.reportError(str + pluginErrorType.getEventSubtype(), str2, str3);
    }
}
