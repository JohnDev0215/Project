package com.amazon.avod.qos.reporter.internal;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.pmet.ContentTypePivot;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.qos.QosReportingTag;
import com.amazon.avod.qos.internal.LowMemoryReporter;
import com.amazon.avod.qos.internal.metrics.QoSMetric;
import com.amazon.avod.qos.metadata.DeliveryType;
import com.amazon.avod.qos.metadata.QOSMetaData;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.qos.model.internal.ReporterContext;
import com.amazon.avod.util.DLog;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class EventReporterBase implements LowMemoryReporter {
    private String mPlaybackReferrer = null;
    private ReporterContext mReporterContext;
    private final QoSMetricEventSender mSender;

    public EventReporterBase(@Nonnull QoSMetricEventSender qoSMetricEventSender) {
        this.mSender = (QoSMetricEventSender) Preconditions.checkNotNull(qoSMetricEventSender, "sender");
    }

    @Override // com.amazon.avod.qos.internal.LowMemoryReporter
    public void reportLowMemory() {
        processErrorEvent(TimeSpan.now(), 4, "LowMemory", null, null, null);
    }

    public void initialize(ReporterContext reporterContext) {
        this.mReporterContext = (ReporterContext) Preconditions.checkNotNull(reporterContext);
    }

    public void reportSession(TimeSpan timeSpan) {
        if (this.mReporterContext.getPlaybackStarted()) {
            MetricsBuilder metricsBuilderPlaybackSettingsId = getMetricsBuilder().playbackSettingsId(this.mReporterContext.getHeuristicsSettingsId());
            this.mReporterContext.buildSessionMetrics(metricsBuilderPlaybackSettingsId, timeSpan);
            reportMetric(QoSMetric.PlaybackSession, metricsBuilderPlaybackSettingsId);
        }
    }

    public void setPluginAvailability(String str, boolean z) {
        appendReportingTag(QosReportingTag.newBooleanTag((String) MoreObjects.firstNonNull(QOSMetaData.PLUGIN_NAME_TO_AVAILABILITY_ATTRIBUTES_MAP.get(str), str), Boolean.valueOf(z), null));
    }

    public void appendReportingTag(QosReportingTag<?> qosReportingTag) {
        this.mReporterContext.setReportingTag(qosReportingTag);
    }

    public void setDuration(TimeSpan timeSpan) {
        this.mReporterContext.setVideoDuration(timeSpan);
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.mReporterContext.setDeliveryType(deliveryType);
    }

    public void setAudioLanguage(String str) {
        this.mReporterContext.setAudioLanguage(str);
    }

    public void setContentMetadata(DeliveryType deliveryType, TimeSpan timeSpan) {
        setDeliveryType(deliveryType);
        setDuration(timeSpan);
    }

    public void setInitialCdnConnection(String str, String str2, String str3, String str4) {
        this.mReporterContext.setCdnInfo(str, str2, str3, str4);
    }

    public String getPrimitiveSessionId() {
        return this.mReporterContext.getPrimitiveSessionId();
    }

    public void reportMetric(String str, String str2, TimeSpan timeSpan, String str3, String str4) {
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        metricsBuilder.eventDuration(timeSpan != null ? Long.valueOf(timeSpan.getTotalMilliseconds()) : null);
        metricsBuilder.eventSubtype(str2);
        metricsBuilder.note(str3);
        metricsBuilder.language(str4);
        sendMetric(str, metricsBuilder, false);
    }

    public void reportError(String str, String str2, String str3) {
        processErrorEvent(TimeSpan.now(), 3, str, str2, str3, null);
    }

    public void reportError(String str, String str2, String str3, int i) {
        Preconditions.checkArgument(i >= 2 && i <= 4);
        processErrorEvent(TimeSpan.now(), i, str, str2, str3, null);
    }

    public void reportError(@Nonnull String str, @Nonnull String str2, @Nullable String str3, @Nonnull String str4, boolean z) {
        processErrorEvent(TimeSpan.now(), z ? 2 : 3, str, str2, str3, str4);
    }

    protected ReporterContext getReporterContext() {
        return this.mReporterContext;
    }

    protected MetricsBuilder getMetricsBuilder() {
        MetricsBuilder metricsBuilder = new MetricsBuilder();
        metricsBuilder.referringUrl(this.mPlaybackReferrer);
        return metricsBuilder;
    }

    protected void reportMetric(QoSMetric qoSMetric, MetricsBuilder metricsBuilder) {
        reportMetric(qoSMetric, metricsBuilder, false);
    }

    protected void reportMetric(QoSMetric qoSMetric, MetricsBuilder metricsBuilder, boolean z) {
        sendMetric(qoSMetric.getEventName().name(), metricsBuilder, z);
    }

    protected void processErrorEvent(TimeSpan timeSpan, int i, String str, String str2, String str3, @Nullable String str4) {
        processErrorEvent(timeSpan, i, str, str2, str3, str4, null);
    }

    protected void processErrorEvent(TimeSpan timeSpan, int i, String str, String str2, String str3, @Nullable String str4, @Nullable String str5) {
        if (i <= 2) {
            this.mReporterContext.incrementErrorCount();
            PlaybackPmetMetricReporter.getInstance().reportPlaybackFatalErrorEvent(getContentType(), getCdn(), getOrigin(), getTitleId());
        }
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        metricsBuilder.errorMessage(str2).asin(str4).eventSubtype(str).errorSeverity(Integer.valueOf(i)).millisecondsStreamed(Long.valueOf(this.mReporterContext.getPlayedDuration(timeSpan))).note(str3).url(str5);
        reportMetric(QoSMetric.PlaybackError, metricsBuilder, str5 != null);
    }

    @Nonnull
    protected ContentTypePivot getContentType() {
        return ContentType.isLive(this.mReporterContext.getContentType()) ? ContentTypePivot.LIVE : ContentTypePivot.VOD;
    }

    @Nullable
    protected String getCdn() {
        return this.mReporterContext.getCdnName();
    }

    @Nullable
    protected String getOrigin() {
        return this.mReporterContext.getOrigin();
    }

    @Nullable
    protected String getTitleId() {
        return this.mReporterContext.getTitleId();
    }

    private void sendMetric(String str, MetricsBuilder metricsBuilder, boolean z) {
        this.mReporterContext.buildCommonMetrics(metricsBuilder, z);
        DLog.logf("Sending QoS metric: type: %s subtype: %s, note: %s, error_message: %s", str, metricsBuilder.eventSubtype, metricsBuilder.note, metricsBuilder.errorMessage);
        this.mSender.send(str, this.mReporterContext.getApplicationSessionId(), metricsBuilder, this.mReporterContext.getAuthenticationTokenKey());
    }
}
