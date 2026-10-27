package com.amazon.avod.playback;

import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.qos.model.internal.QosCommonMetricsContext;
import com.amazon.avod.qos.reporter.internal.QoSMetricEventSender;
import com.amazon.avod.util.DLog;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PlaybackEventReporterBase implements PlaybackEventReporter {
    protected final QosCommonMetricsContext mCommonMetricsContext;
    protected final QoSMetricEventSender mQoSMetricEventSender;

    public PlaybackEventReporterBase() {
        this(null, null);
    }

    public PlaybackEventReporterBase(@Nullable QosCommonMetricsContext qosCommonMetricsContext, @Nullable QoSMetricEventSender qoSMetricEventSender) {
        this.mCommonMetricsContext = qosCommonMetricsContext;
        this.mQoSMetricEventSender = qoSMetricEventSender;
    }

    @Override // com.amazon.avod.playback.PlaybackEventReporter
    public void reportMetric(@Nonnull MetricsBuilder metricsBuilder) {
        if (this.mCommonMetricsContext == null || this.mQoSMetricEventSender == null) {
            DLog.logf("Logging context is missing. Not reporting anything.");
        } else {
            this.mQoSMetricEventSender.send(metricsBuilder.build().getEventType(), this.mCommonMetricsContext.getApplicationSessionId(), metricsBuilder);
        }
    }
}
