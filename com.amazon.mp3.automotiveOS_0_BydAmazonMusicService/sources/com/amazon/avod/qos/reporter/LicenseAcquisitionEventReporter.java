package com.amazon.avod.qos.reporter;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.qos.internal.DurationTimer;
import com.amazon.avod.qos.internal.metrics.QoSMetric;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.qos.reporter.internal.EventReporterBase;
import com.amazon.avod.qos.reporter.internal.QoSMetricEventSender;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class LicenseAcquisitionEventReporter extends EventReporterBase {
    private final DurationTimer mAcquisitionTimer;

    public LicenseAcquisitionEventReporter(@Nonnull QoSMetricEventSender qoSMetricEventSender) {
        super(qoSMetricEventSender);
        this.mAcquisitionTimer = new DurationTimer();
    }

    public void reportLicenseStart(TimeSpan timeSpan, String str) {
        this.mAcquisitionTimer.setStartTime(timeSpan);
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        metricsBuilder.eventSubtype("Request").note(str);
        reportMetric(QoSMetric.DrmRequest, metricsBuilder);
    }

    public void reportLicenseAcquired(TimeSpan timeSpan, String str) {
        long jElapsedMillisUntil = this.mAcquisitionTimer.elapsedMillisUntil(timeSpan);
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        metricsBuilder.eventDuration(Long.valueOf(jElapsedMillisUntil)).eventSubtype("Response").note(str);
        reportMetric(QoSMetric.DrmAcquired, metricsBuilder);
    }

    public void reportLicensingError(TimeSpan timeSpan, String str, String str2, @Nullable String str3) {
        long jElapsedMillisUntil = this.mAcquisitionTimer.elapsedMillisUntil(timeSpan);
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        metricsBuilder.eventDuration(Long.valueOf(jElapsedMillisUntil)).eventSubtype("DRMLicensingError").errorMessage(str).errorSeverity(3).note(str2).url(str3);
        reportMetric(QoSMetric.DrmAcquisitionError, metricsBuilder, str3 != null);
    }
}
