package com.amazon.avod.content.image;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.ImageDownloader;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.pmet.LiveTrickplayMetrics$ImageDownloaderCounterMetrics;
import com.amazon.avod.pmet.LiveTrickplayMetrics$ImageDownloaderTimerMetrics;
import com.amazon.avod.pmet.LiveTrickplayMetrics$LiveTrickplayTimerMetrics;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticEvent;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticsState;
import com.amazon.avod.util.DLog;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ImageDownloaderReporter implements ImageDownloader.ImageDownloaderListener {
    private final ContentManagementEventBus mContentManagementEventBus;
    private final PlaybackPmetMetricReporter mPlaybackPmetMetricReporter;
    private final boolean mReportCancelledDownloadToPmet;
    private final boolean mReportDownloadTimeToPmet;
    private final boolean mReportFailedDownloadToPmet;
    private final boolean mReportLatencyToPmet;
    private final boolean mReportThroughputToPmet;
    private final boolean mReportToAloysius;
    private final boolean mReportToPmet;
    private final boolean mReportToQos;
    private final boolean mReportTotalDownloadToPmet;
    private final PlaybackEventReporter mReporter;

    public ImageDownloaderReporter(@Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter) {
        this.mReporter = (PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter, "playbackEventReporter");
        this.mContentManagementEventBus = (ContentManagementEventBus) Preconditions.checkNotNull(contentManagementEventBus, "contentManagementEventBus");
        this.mPlaybackPmetMetricReporter = (PlaybackPmetMetricReporter) Preconditions.checkNotNull(playbackPmetMetricReporter, "playbackPmetMetricReporter");
        ImageDownloaderReporterConfig imageDownloaderReporterConfig = ImageDownloaderReporterConfig.getInstance();
        this.mReportToQos = ((Boolean) imageDownloaderReporterConfig.mReportImageDownloaderEventsToQos.getValue()).booleanValue();
        this.mReportToAloysius = ((Boolean) imageDownloaderReporterConfig.mReportImageDownloaderEventsToAloysius.getValue()).booleanValue();
        this.mReportToPmet = ((Boolean) imageDownloaderReporterConfig.mReportImageDownloaderEventsToPmet.getValue()).booleanValue();
        this.mReportTotalDownloadToPmet = ((Boolean) imageDownloaderReporterConfig.mReportImageDownloaderTotalDownloadToPmet.getValue()).booleanValue();
        this.mReportFailedDownloadToPmet = ((Boolean) imageDownloaderReporterConfig.mReportImageDownloaderFailedDownloadToPmet.getValue()).booleanValue();
        this.mReportCancelledDownloadToPmet = ((Boolean) imageDownloaderReporterConfig.mReportImageDownloaderCancelledDownloadToPmet.getValue()).booleanValue();
        this.mReportThroughputToPmet = ((Boolean) imageDownloaderReporterConfig.mReportImageDownloaderThroughputToPmet.getValue()).booleanValue();
        this.mReportDownloadTimeToPmet = ((Boolean) imageDownloaderReporterConfig.mReportImageDownloaderDownloadTimeToPmet.getValue()).booleanValue();
        this.mReportLatencyToPmet = ((Boolean) imageDownloaderReporterConfig.mReportImageDownloaderLatencyToPmet.getValue()).booleanValue();
    }

    private static class ImageDownloaderReporterConfig extends MediaConfigBase {
        private final ConfigurationValue<Boolean> mReportImageDownloaderCancelledDownloadToPmet;
        private final ConfigurationValue<Boolean> mReportImageDownloaderDownloadTimeToPmet;
        private final ConfigurationValue<Boolean> mReportImageDownloaderEventsToAloysius;
        private final ConfigurationValue<Boolean> mReportImageDownloaderEventsToPmet;
        private final ConfigurationValue<Boolean> mReportImageDownloaderEventsToQos;
        private final ConfigurationValue<Boolean> mReportImageDownloaderFailedDownloadToPmet;
        private final ConfigurationValue<Boolean> mReportImageDownloaderLatencyToPmet;
        private final ConfigurationValue<Boolean> mReportImageDownloaderThroughputToPmet;
        private final ConfigurationValue<Boolean> mReportImageDownloaderTotalDownloadToPmet;

        /* JADX INFO: Access modifiers changed from: private */
        public static ImageDownloaderReporterConfig getInstance() {
            return SingletonHolder.INSTANCE;
        }

        private static final class SingletonHolder {
            private static final ImageDownloaderReporterConfig INSTANCE = new ImageDownloaderReporterConfig();

            private SingletonHolder() {
            }
        }

        private ImageDownloaderReporterConfig() {
            this.mReportImageDownloaderEventsToQos = newBooleanConfigValue("playback_reportImageDownloaderEventsToQos", true);
            this.mReportImageDownloaderEventsToAloysius = newBooleanConfigValue("playback_reportImageDownloaderEventsToAloysius", true);
            this.mReportImageDownloaderEventsToPmet = newBooleanConfigValue("playback_reportImageDownloaderEventsToPmet", true);
            this.mReportImageDownloaderTotalDownloadToPmet = newBooleanConfigValue("playback_reportImageDownloaderTotalDownloadToPmet", true);
            this.mReportImageDownloaderFailedDownloadToPmet = newBooleanConfigValue("playback_reportImageDownloaderFailedDownloadToPmet", true);
            this.mReportImageDownloaderCancelledDownloadToPmet = newBooleanConfigValue("playback_reportImageDownloaderCancelledDownloadToPmet", true);
            this.mReportImageDownloaderThroughputToPmet = newBooleanConfigValue("playback_reportImageDownloaderThroughputToPmet", true);
            this.mReportImageDownloaderDownloadTimeToPmet = newBooleanConfigValue("playback_reportImageDownloaderDownloadTimeToPmet", true);
            this.mReportImageDownloaderLatencyToPmet = newBooleanConfigValue("playback_reportImageDownloaderLatencyToPmet", true);
        }
    }

    public void reportStarted(@Nonnull String str) {
        Preconditions.checkNotNull(str, "startParams");
        if (this.mReportToQos) {
            this.mReporter.reportMetric(QOSEventName.Information.toString(), "ImageDownloaderStarted", null, str, null);
        }
        if (this.mReportToAloysius) {
            this.mContentManagementEventBus.postEvent(new AloysiusDiagnosticEvent("ImageDownloaderStarted", str, AloysiusDiagnosticsState.Discrete));
        }
    }

    public void reportPrepared(@Nonnull TimeSpan timeSpan, @Nonnull ImageDownloadStrategy imageDownloadStrategy) {
        Preconditions.checkNotNull(timeSpan, "timeToPrepare");
        Preconditions.checkNotNull(imageDownloadStrategy, "imageDownloadStrategy");
        DLog.logf("ImageDownloader %s ready in %s milliseconds", imageDownloadStrategy.name(), Long.valueOf(timeSpan.getTotalMilliseconds()));
        if (this.mReportToQos) {
            this.mReporter.reportMetric(QOSEventName.Information.toString(), LiveTrickplayMetrics$ImageDownloaderTimerMetrics.TIME_TO_READY.toReportableString(), timeSpan, String.valueOf(timeSpan.getTotalMilliseconds()), null);
        }
        if (this.mReportToAloysius) {
            this.mContentManagementEventBus.postEvent(new AloysiusDiagnosticEvent(LiveTrickplayMetrics$ImageDownloaderTimerMetrics.TIME_TO_READY.toReportableString(), String.valueOf(timeSpan.getTotalMilliseconds()), AloysiusDiagnosticsState.Discrete));
        }
        if (this.mReportToPmet) {
            this.mPlaybackPmetMetricReporter.reportImageDownloaderTimerMetric(LiveTrickplayMetrics$ImageDownloaderTimerMetrics.TIME_TO_READY, imageDownloadStrategy.name(), timeSpan.getTotalMilliseconds());
        }
    }

    public void reportFinished(@Nonnull TimeSpan timeSpan, @Nonnull ImageDownloadStrategy imageDownloadStrategy) {
        Preconditions.checkNotNull(timeSpan, "timeToFinish");
        Preconditions.checkNotNull(imageDownloadStrategy, "imageDownloadStrategy");
        DLog.logf("ImageDownloader %s backward finished in %s milliseconds", imageDownloadStrategy.name(), Long.valueOf(timeSpan.getTotalMilliseconds()));
        if (this.mReportToQos) {
            this.mReporter.reportMetric(QOSEventName.Information.toString(), LiveTrickplayMetrics$ImageDownloaderTimerMetrics.TIME_TO_FINISH.toReportableString(), timeSpan, String.valueOf(timeSpan.getTotalMilliseconds()), null);
        }
        if (this.mReportToAloysius) {
            this.mContentManagementEventBus.postEvent(new AloysiusDiagnosticEvent(LiveTrickplayMetrics$ImageDownloaderTimerMetrics.TIME_TO_FINISH.toReportableString(), String.valueOf(timeSpan.getTotalMilliseconds()), AloysiusDiagnosticsState.Discrete));
        }
        if (this.mReportToPmet) {
            this.mPlaybackPmetMetricReporter.reportImageDownloaderTimerMetric(LiveTrickplayMetrics$ImageDownloaderTimerMetrics.TIME_TO_FINISH, imageDownloadStrategy.name(), timeSpan.getTotalMilliseconds());
        }
    }

    public void reportQualitySelected(long j, @Nonnull ImageDownloadStrategy imageDownloadStrategy) {
        Preconditions.checkNotNull(imageDownloadStrategy, "imageDownloadStrategy");
        DLog.logf("ImageDownloader %s selected imageWidth %s", imageDownloadStrategy.name(), Long.valueOf(j));
        if (this.mReportToQos) {
            this.mReporter.reportMetric(QOSEventName.Information.toString(), LiveTrickplayMetrics$LiveTrickplayTimerMetrics.IMAGE_WIDTH.toReportableString(), null, String.valueOf(j), null);
        }
        if (this.mReportToAloysius) {
            this.mContentManagementEventBus.postEvent(new AloysiusDiagnosticEvent(LiveTrickplayMetrics$LiveTrickplayTimerMetrics.IMAGE_WIDTH.toReportableString(), String.valueOf(j), AloysiusDiagnosticsState.Discrete));
        }
        if (this.mReportToPmet) {
            this.mPlaybackPmetMetricReporter.reportLiveTrickplayTimerMetric(LiveTrickplayMetrics$LiveTrickplayTimerMetrics.IMAGE_WIDTH, imageDownloadStrategy == ImageDownloadStrategy.MULTIPASS ? "LIVE_MULTIPASS" : "LIVE_SERIAL", j);
        }
    }

    public void reportDisposed(int i, int i2, int i3, int i4, long j, long j2, @Nonnull ImageDownloadStrategy imageDownloadStrategy) {
        Preconditions.checkNotNull(imageDownloadStrategy, "imageDownloadStrategy");
        String jsonString = new ImageDownloaderAggregates(i, i2, i3, i4, j, j2).toJsonString();
        DLog.logf("ImageDownloader %s disposed aggregate stats %s", imageDownloadStrategy.name(), jsonString);
        if (this.mReportToQos) {
            this.mReporter.reportMetric(QOSEventName.Information.toString(), LiveTrickplayMetrics$ImageDownloaderTimerMetrics.IMAGE_DOWNLOAD_SUMMARY.toReportableString(), null, jsonString, null);
        }
        if (this.mReportToAloysius) {
            this.mContentManagementEventBus.postEvent(new AloysiusDiagnosticEvent(LiveTrickplayMetrics$ImageDownloaderTimerMetrics.IMAGE_DOWNLOAD_SUMMARY.toReportableString(), jsonString, AloysiusDiagnosticsState.Discrete));
        }
        if (this.mReportToPmet) {
            if (this.mReportTotalDownloadToPmet) {
                this.mPlaybackPmetMetricReporter.reportImageDownloaderCounterMetric(LiveTrickplayMetrics$ImageDownloaderCounterMetrics.TOTAL_DOWNLOAD, imageDownloadStrategy.name(), i + i2 + i3);
            }
            if (this.mReportFailedDownloadToPmet) {
                this.mPlaybackPmetMetricReporter.reportImageDownloaderCounterMetric(LiveTrickplayMetrics$ImageDownloaderCounterMetrics.FAILED_DOWNLOAD, imageDownloadStrategy.name(), i2);
            }
            if (this.mReportCancelledDownloadToPmet) {
                this.mPlaybackPmetMetricReporter.reportImageDownloaderCounterMetric(LiveTrickplayMetrics$ImageDownloaderCounterMetrics.CANCELLED_DOWNLOAD, imageDownloadStrategy.name(), i3);
            }
            if (this.mReportThroughputToPmet) {
                this.mPlaybackPmetMetricReporter.reportImageDownloaderTimerMetric(LiveTrickplayMetrics$ImageDownloaderTimerMetrics.THROUGHPUT, imageDownloadStrategy.name(), i4);
            }
            if (this.mReportDownloadTimeToPmet) {
                this.mPlaybackPmetMetricReporter.reportImageDownloaderTimerMetric(LiveTrickplayMetrics$ImageDownloaderTimerMetrics.DOWNLOAD_TIME, imageDownloadStrategy.name(), j);
            }
            if (this.mReportLatencyToPmet) {
                this.mPlaybackPmetMetricReporter.reportImageDownloaderTimerMetric(LiveTrickplayMetrics$ImageDownloaderTimerMetrics.LATENCY, imageDownloadStrategy.name(), j2);
            }
        }
    }

    public void reportCancelled(@Nonnull LiveTrickplayCancellationCause liveTrickplayCancellationCause) {
        if (this.mReportToQos) {
            this.mReporter.reportMetric(QOSEventName.Information.toString(), "ImageDownloaderCancelled", null, liveTrickplayCancellationCause.name(), null);
        }
        if (this.mReportToAloysius) {
            this.mContentManagementEventBus.postEvent(new AloysiusDiagnosticEvent("ImageDownloaderCancelled", liveTrickplayCancellationCause.name(), AloysiusDiagnosticsState.Discrete));
        }
    }

    public void reportTrimmed(@Nonnull String str) {
        Preconditions.checkNotNull(str, "trimParams");
        if (this.mReportToQos) {
            this.mReporter.reportMetric(QOSEventName.Information.toString(), "ImageDownloaderTrimmed", null, str, null);
        }
        if (this.mReportToAloysius) {
            this.mContentManagementEventBus.postEvent(new AloysiusDiagnosticEvent("ImageDownloaderTrimmed", str, AloysiusDiagnosticsState.Discrete));
        }
    }

    public void reportError(@Nonnull String str) {
        Preconditions.checkNotNull(str, "errorNote");
        if (this.mReportToQos) {
            this.mReporter.reportMetric(QOSEventName.Information.toString(), "ImageDownloaderError", null, str, null);
        }
        if (this.mReportToAloysius) {
            this.mContentManagementEventBus.postEvent(new AloysiusDiagnosticEvent("ImageDownloaderError", str, AloysiusDiagnosticsState.Discrete));
        }
    }

    @Override // com.amazon.avod.content.smoothstream.ImageDownloader.ImageDownloaderListener
    public void onStatusUpdated(boolean z, @Nonnull TimeSpan timeSpan, @Nonnull ImageDownloadStrategy imageDownloadStrategy) {
        if (z) {
            reportPrepared((TimeSpan) Preconditions.checkNotNull(timeSpan, "timeToReady"), (ImageDownloadStrategy) Preconditions.checkNotNull(imageDownloadStrategy));
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ImageDownloaderAggregates {
        private int cancelledCount;
        private int downloadCount;
        private int downloadThroughputKbps;
        private int failedCount;
        private long medianDownloadLatencyMillis;
        private long medianDownloadTimeMillis;

        private ImageDownloaderAggregates(int i, int i2, int i3, int i4, long j, long j2) {
            this.downloadCount = i;
            this.failedCount = i2;
            this.cancelledCount = i3;
            this.downloadThroughputKbps = i4;
            this.medianDownloadTimeMillis = j;
            this.medianDownloadLatencyMillis = j2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Nonnull
        public String toJsonString() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            try {
                return objectMapper.writeValueAsString(this);
            } catch (JsonProcessingException e) {
                DLog.warnf("ImageDownloader AggregateMetrics toJsonString Exception: %s", e.getMessage());
                return toString();
            }
        }

        @Nonnull
        public String toString() {
            return MoreObjects.toStringHelper(this).add("cancelledCount", this.cancelledCount).add("downloadCount", this.downloadCount).add("downloadThroughputKbps", this.downloadThroughputKbps).add("failedCount", this.failedCount).add("medianDownloadLatencyMillis", this.medianDownloadLatencyMillis).add("medianDownloadTimeMillis", this.medianDownloadTimeMillis).toString();
        }
    }
}
