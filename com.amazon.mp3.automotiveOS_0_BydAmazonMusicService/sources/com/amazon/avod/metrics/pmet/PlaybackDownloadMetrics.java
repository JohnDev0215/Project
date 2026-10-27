package com.amazon.avod.metrics.pmet;

import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.metrics.pmet.internal.MetricNameTemplate;
import com.amazon.avod.metrics.pmet.internal.MetricValueTemplates;
import com.amazon.avod.metrics.pmet.internal.ValidatedCounterMetric;
import com.amazon.avod.userdownload.UserDownloadLocation;
import com.amazon.avod.userdownload.reporting.Cause;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum PlaybackDownloadMetrics implements EnumeratedCounterMetricTemplate {
    READY_TO_WATCH(new MetricNameTemplate("PlaybackDownload:ReadyToWatch")),
    PERSISTENCE_FAILED(new MetricNameTemplate("PlaybackDownload:PersistenceFailed")),
    ASSERT_FAILED(new MetricNameTemplate("PlaybackDownload:AssertFailed")),
    OPERATION(new MetricNameTemplate("PlaybackDownload:Operation:", ImmutableList.of(Cause.class)), addDownloadDefaults(MetricValueTemplates.defaultBuilder())),
    DOWNLOAD_PLAYBACK(new MetricNameTemplate("PlaybackDownload:Playback"), addDownloadDefaults(MetricValueTemplates.defaultBuilder().add("Connectivity:", ClientMode.class)));

    private final MetricValueTemplates METRIC_VALUE_TEMPLATES;
    private final MetricNameTemplate mNameTemplate;
    private final MetricValueTemplates mValueTemplates;

    @Override // com.amazon.avod.metrics.pmet.EnumeratedCounterMetricTemplate
    @Nonnull
    public /* bridge */ /* synthetic */ ValidatedCounterMetric format(@Nonnull ImmutableList immutableList, @Nonnull ImmutableList immutableList2, long j) {
        return super.format(immutableList, immutableList2, j);
    }

    private static MetricValueTemplates addDownloadDefaults(MetricValueTemplates.Builder builder) {
        return builder.add("Location:", UserDownloadLocation.class).add("Quality:", MediaQuality.class).build();
    }

    PlaybackDownloadMetrics(@Nonnull MetricNameTemplate metricNameTemplate) {
        MetricValueTemplates metricValueTemplatesAddDownloadDefaults = addDownloadDefaults(MetricValueTemplates.defaultBuilder());
        this.METRIC_VALUE_TEMPLATES = metricValueTemplatesAddDownloadDefaults;
        this.mNameTemplate = (MetricNameTemplate) Preconditions.checkNotNull(metricNameTemplate, "nameTemplate");
        this.mValueTemplates = metricValueTemplatesAddDownloadDefaults;
    }

    PlaybackDownloadMetrics(@Nonnull MetricNameTemplate metricNameTemplate, @Nonnull MetricValueTemplates metricValueTemplates) {
        this.METRIC_VALUE_TEMPLATES = addDownloadDefaults(MetricValueTemplates.defaultBuilder());
        this.mNameTemplate = (MetricNameTemplate) Preconditions.checkNotNull(metricNameTemplate, "nameTemplate");
        this.mValueTemplates = (MetricValueTemplates) Preconditions.checkNotNull(metricValueTemplates, "valueTemplates");
    }

    @Override // com.amazon.avod.metrics.pmet.EnumeratedCounterMetricTemplate
    public ValidatedCounterMetric format(ImmutableList<MetricParameter> immutableList, ImmutableList<ImmutableList<MetricParameter>> immutableList2) {
        return new ValidatedCounterMetric(this.mNameTemplate.format(immutableList), this.mValueTemplates.format(immutableList2), DownloadMetricComponent.DOWNLOADS);
    }

    public enum ClientMode implements MetricParameter {
        ONLINE("Online"),
        OFFLINE("Offline");

        private final String mReportableName;

        ClientMode(String str) {
            this.mReportableName = str;
        }

        @Override // com.amazon.avod.metrics.pmet.MetricParameter
        @Nonnull
        public String toReportableString() {
            return this.mReportableName;
        }
    }
}
