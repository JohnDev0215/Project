package com.amazon.avod.qos.reporter.internal;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.PlaybackEventReporterBase;
import com.amazon.avod.qos.QosReportingTag;
import com.amazon.avod.qos.internal.metrics.QoSMetric;
import com.amazon.avod.qos.metadata.QOSMetaData;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.qos.model.internal.QosCommonMetricsContext;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class StandaloneEventReporter extends PlaybackEventReporterBase {
    private final Map<String, QosReportingTag<?>> mCustomTags;
    private final String mPrimitiveSessionId;
    private final String mUserWatchSessionId;

    public StandaloneEventReporter(@Nonnull QosCommonMetricsContext qosCommonMetricsContext, @Nullable String str, @Nonnull QoSMetricEventSender qoSMetricEventSender) {
        super(qosCommonMetricsContext, qoSMetricEventSender);
        this.mCustomTags = Maps.newConcurrentMap();
        this.mUserWatchSessionId = str;
        this.mPrimitiveSessionId = UUID.randomUUID().toString();
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public String getUserWatchSessionId() {
        return this.mUserWatchSessionId;
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public String getPrimitiveSessionId() {
        return this.mPrimitiveSessionId;
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public void reportFeatureAvailability(String str, boolean z) {
        appendReportingTag((String) MoreObjects.firstNonNull(QOSMetaData.PLUGIN_NAME_TO_AVAILABILITY_ATTRIBUTES_MAP.get(str), str), Boolean.valueOf(z).toString());
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public void reportMetric(String str, String str2, TimeSpan timeSpan, String str3, String str4) {
        Preconditions.checkNotNull(str, "metricName");
        Long lValueOf = timeSpan != null ? Long.valueOf(timeSpan.getTotalMilliseconds()) : null;
        MetricsBuilder metricsBuilderCreateMetricsBuilder = createMetricsBuilder();
        metricsBuilderCreateMetricsBuilder.eventSubtype(str2).eventDuration(lValueOf).language(str4).note(str3);
        this.mQoSMetricEventSender.send(str, this.mCommonMetricsContext.getApplicationSessionId(), metricsBuilderCreateMetricsBuilder);
    }

    public void reportMetric(String str, MetricsBuilder metricsBuilder) {
        Preconditions.checkNotNull(str, "metricName");
        this.mQoSMetricEventSender.send(str, this.mCommonMetricsContext.getApplicationSessionId(), metricsBuilder);
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public void reportError(String str, String str2, String str3) {
        MetricsBuilder metricsBuilderCreateMetricsBuilder = createMetricsBuilder();
        metricsBuilderCreateMetricsBuilder.errorMessage((String) Preconditions.checkNotNull(str2)).eventSubtype((String) Preconditions.checkNotNull(str)).errorSeverity(3).note(str3);
        this.mQoSMetricEventSender.send(QoSMetric.PlaybackError.getEventName().name(), this.mCommonMetricsContext.getApplicationSessionId(), metricsBuilderCreateMetricsBuilder);
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public void reportError(@Nonnull String str, @Nonnull String str2, @Nullable String str3, @Nonnull String str4, boolean z) {
        MetricsBuilder metricsBuilderCreateMetricsBuilder = createMetricsBuilder();
        metricsBuilderCreateMetricsBuilder.errorMessage((String) Preconditions.checkNotNull(str2)).eventSubtype((String) Preconditions.checkNotNull(str)).errorSeverity(Integer.valueOf(z ? 2 : 3)).asin(str4).note(str3);
        this.mQoSMetricEventSender.send(QoSMetric.PlaybackError.getEventName().name(), this.mCommonMetricsContext.getApplicationSessionId(), metricsBuilderCreateMetricsBuilder);
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public void appendReportingTag(String str, String str2, String str3) {
        innerAppendReportingTag(str, str2, (String) Preconditions.checkNotNull(str3));
    }

    public void appendReportingTag(String str, String str2) {
        innerAppendReportingTag(str, str2, null);
    }

    private void innerAppendReportingTag(String str, String str2, String str3) {
        this.mCustomTags.put(str, QosReportingTag.newStringTag((String) Preconditions.checkNotNull(str), (String) Preconditions.checkNotNull(str2), str3));
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public MetricsBuilder createMetricsBuilder() {
        MetricsBuilder metricsBuilder = new MetricsBuilder();
        this.mCommonMetricsContext.buildCommonMetrics(metricsBuilder);
        metricsBuilder.primitiveSessionId(this.mPrimitiveSessionId).customTags(ImmutableMap.copyOf((Map) this.mCustomTags)).streamingSessionId(this.mPrimitiveSessionId).downloadSessionId(this.mPrimitiveSessionId).userWatchSessionId(this.mUserWatchSessionId);
        return metricsBuilder;
    }
}
