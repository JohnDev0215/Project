package com.amazon.avod.qos.listeners;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.PlaybackEventReporterBase;
import com.amazon.avod.qos.QosReportingTag;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.qos.model.QosClientContext;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultPlaybackEventReporter extends PlaybackEventReporterBase {
    private final QosClientContext mClientContext;
    private final PlaybackEventReporter mPlaybackEventReporter;
    private final com.amazon.avod.qos.reporter.PlaybackEventReporter mQosReporter;

    public DefaultPlaybackEventReporter(@Nonnull QosClientContext qosClientContext, @Nonnull com.amazon.avod.qos.reporter.PlaybackEventReporter playbackEventReporter, @Nonnull PlaybackEventReporter playbackEventReporter2) {
        this.mClientContext = (QosClientContext) Preconditions.checkNotNull(qosClientContext, "clientContext");
        this.mQosReporter = (com.amazon.avod.qos.reporter.PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter, "qosReporter");
        this.mPlaybackEventReporter = (PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter2, "playbackEventReporter");
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public String getUserWatchSessionId() {
        return this.mClientContext.getUserWatchSessionId();
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public String getPrimitiveSessionId() {
        return this.mQosReporter.getPrimitiveSessionId();
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public void reportFeatureAvailability(@Nonnull String str, boolean z) {
        this.mQosReporter.setPluginAvailability((String) Preconditions.checkNotNull(str), z);
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public void reportMetric(@Nonnull String str, String str2, TimeSpan timeSpan, String str3, String str4) {
        this.mQosReporter.reportMetric((String) Preconditions.checkNotNull(str), str2, timeSpan, str3, str4);
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public void reportError(@Nonnull String str, @Nonnull String str2, String str3) {
        this.mQosReporter.reportError((String) Preconditions.checkNotNull(str), (String) Preconditions.checkNotNull(str2), str3);
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public void reportError(@Nonnull String str, @Nonnull String str2, @Nullable String str3, @Nonnull String str4, boolean z) {
        this.mQosReporter.reportError((String) Preconditions.checkNotNull(str), (String) Preconditions.checkNotNull(str2), str3, (String) Preconditions.checkNotNull(str4), z);
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public void appendReportingTag(@Nonnull String str, @Nonnull String str2, @Nonnull String str3) {
        this.mQosReporter.appendReportingTag(QosReportingTag.newStringTag((String) Preconditions.checkNotNull(str), (String) Preconditions.checkNotNull(str2), (String) Preconditions.checkNotNull(str3)));
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporterBase, com.amazon.avod.playback.PlaybackEventReporter
    public void reportMetric(@Nonnull MetricsBuilder metricsBuilder) {
        metricsBuilder.streamingSessionId(getPrimitiveSessionId()).userWatchSessionId(getUserWatchSessionId());
        this.mPlaybackEventReporter.reportMetric(metricsBuilder);
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public MetricsBuilder createMetricsBuilder() {
        return this.mPlaybackEventReporter.createMetricsBuilder();
    }
}
