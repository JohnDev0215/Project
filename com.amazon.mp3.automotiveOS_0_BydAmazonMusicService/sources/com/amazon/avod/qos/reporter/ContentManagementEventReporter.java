package com.amazon.avod.qos.reporter;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.qahooks.PlaybackQAEvent;
import com.amazon.avod.qahooks.PlaybackQAMetric;
import com.amazon.avod.qahooks.QALog;
import com.amazon.avod.qos.QoSConfig;
import com.amazon.avod.qos.internal.DurationTimer;
import com.amazon.avod.qos.internal.metrics.QoSMetric;
import com.amazon.avod.qos.metadata.DeliveryType;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.qos.model.internal.AudioAdaptationSetSwitchContext;
import com.amazon.avod.qos.model.internal.StreamSwitchContext;
import com.amazon.avod.qos.reporter.internal.EventReporterBase;
import com.amazon.avod.qos.reporter.internal.QoSMetricEventSender;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ContentManagementEventReporter extends EventReporterBase {
    private final DurationTimer mDownloadTimer;
    private double mNextReportInterval;
    private long mReadyToWatchTime;
    private final String mServerConfigVersion;

    public ContentManagementEventReporter(@Nonnull QoSConfig qoSConfig, @Nonnull QoSMetricEventSender qoSMetricEventSender) {
        super(qoSMetricEventSender);
        this.mDownloadTimer = new DurationTimer();
        this.mReadyToWatchTime = 0L;
        this.mNextReportInterval = 25.0d;
        this.mServerConfigVersion = qoSConfig.getReportingConfigVersion();
    }

    public boolean isAudioTrackSwitching() {
        return getReporterContext().isAudioTrackSwitching();
    }

    public boolean isAudioAdaptationSetSwitching() {
        return getReporterContext().isAudioAdaptationSetSwitching();
    }

    public void handleAudioTrackSwitch(String str, String str2) {
        Preconditions.checkArgument(isAudioTrackSwitching());
        StreamSwitchContext streamSwitchContext = getReporterContext().getStreamSwitchContext();
        reportStreamSwitch(TimeSpan.now(), String.format(Locale.US, "SwitchStream from: %s, audiotrackid: %s, to: %s, audiotrackid: %s", streamSwitchContext.getLanguage(), streamSwitchContext.getOldAudioTrackId(), str, streamSwitchContext.getNewAudioTrackId()), str2);
        getReporterContext().finishAudioTrackSwitch();
    }

    public void handleAudioAdaptationSetSwitch(String str, int i) {
        Preconditions.checkArgument(isAudioAdaptationSetSwitching());
        AudioAdaptationSetSwitchContext audioAdaptationSetSwitchContext = getReporterContext().getAudioAdaptationSetSwitchContext();
        String cause = audioAdaptationSetSwitchContext.getCause();
        int bitrate = audioAdaptationSetSwitchContext.getBitrate();
        String adaptationSetName = audioAdaptationSetSwitchContext.getAdaptationSetName();
        String streamSelectionParams = audioAdaptationSetSwitchContext.getStreamSelectionParams();
        QALog.newQALog(PlaybackQAEvent.PLAYBACK_AUDIO_BITRATE_CHANGED).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.CAUSE, cause).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.OLD_QUALITY, bitrate).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.NEW_QUALITY, i).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.OLD_ADAPTATION_SET, adaptationSetName).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.NEW_ADAPTATION_SET, str).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.NOTE, streamSelectionParams).send();
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        metricsBuilder.eventSubtype(cause).fromBitrate(Integer.valueOf(bitrate)).fromAdaptationSet(adaptationSetName).toBitrate(Integer.valueOf(i)).toAdaptationSet(str).note(streamSelectionParams);
        reportMetric(QoSMetric.AudioBitrateChange, metricsBuilder);
        getReporterContext().finishAudioAdaptationSetSwitching();
    }

    public void reportCdnSwitch(String str, String str2, String str3, String str4, String str5) {
        reportMetric(QOSEventName.CDNSwitch.name(), null, TimeSpan.now(), str4, null);
        reportSession(TimeSpan.now());
        getReporterContext().setCdnInfo(str, str2, str3, str5);
    }

    public void reportCurrentBitrateKbps(long j) {
        getReporterContext().updateCurrentBitrateKbps(j);
    }

    public void reportSlowFragmentDownload(String str, long j) {
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        metricsBuilder.errorMessage(String.format(Locale.US, "Fragment download took %d ms", Long.valueOf(j))).errorSeverity(4).eventDuration(Long.valueOf(j)).eventSubtype("SlowFragmentDownload").url(str);
        reportMetric(QoSMetric.DownloadException, metricsBuilder);
    }

    public void onError(String str, String str2, int i, String str3, @Nullable String str4) {
        if (i <= 2) {
            getReporterContext().incrementErrorCount();
        }
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        metricsBuilder.errorMessage(str2).errorSeverity(Integer.valueOf(i)).eventSubtype(str).note(str3).url(str4);
        reportMetric(QoSMetric.DownloadException, metricsBuilder, str4 != null);
    }

    public void reportContentStoreEvent(@Nonnull String str) {
        Preconditions.checkNotNull(str, "contentStoreType");
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        metricsBuilder.eventSubtype("ContentStoreType").note(str);
        reportMetric(QoSMetric.Information, metricsBuilder);
    }

    public void reportDownloadStartMetrics(boolean z, TimeSpan timeSpan, String str) {
        if (getReporterContext().getDownloadStarted()) {
            return;
        }
        getReporterContext().setDownloadStarted(true);
        this.mDownloadTimer.setStartTime(timeSpan);
        reportMetric(QoSMetric.DownloadStart, getMetricsBuilder().eventSubtype(z ? "ResumeDownload" : "StartDownload").note(str));
    }

    public void reportDownloadSessionMetric(TimeSpan timeSpan, TimeSpan timeSpan2, String str) {
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        metricsBuilder.sessionEndTime(Long.valueOf(timeSpan2.getTotalMilliseconds())).sessionStartTime(Long.valueOf(this.mDownloadTimer.elapsedMillisUntil(TimeSpan.ZERO))).downloadParameters(this.mServerConfigVersion).note(str);
        if (timeSpan != null) {
            metricsBuilder.sessionStartTime(Long.valueOf(timeSpan.getTotalMilliseconds()));
        }
        long j = this.mReadyToWatchTime;
        if (j != 0) {
            metricsBuilder.playbackBufferFullTimeSec(Long.valueOf(j));
        }
        reportMetric(QoSMetric.DownloadSession, metricsBuilder);
    }

    public void reportDownloadProgress(int i) {
        setDownloadPercentage(i);
        if (i < this.mNextReportInterval || !isDeliveryTypeDownload()) {
            return;
        }
        this.mNextReportInterval += 25.0d;
        reportMetric(QoSMetric.AmountDownloaded, getMetricsBuilder());
    }

    public void reportStreamSwitch(TimeSpan timeSpan, String str, String str2) {
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        metricsBuilder.eventDuration(timeSpan != null ? Long.valueOf(timeSpan.getTotalMilliseconds()) : null).note(str).newUrlSetId(str2).sourceEventType(QOSEventName.MultiTrackAudio.name());
        reportMetric(QoSMetric.StreamSwitch, metricsBuilder);
    }

    public void reportWanUsage(int i, String str) {
        MetricsBuilder metricsBuilder = getMetricsBuilder();
        metricsBuilder.note(str).fromTimecode(Integer.valueOf(i));
        reportMetric(QoSMetric.StreamingBitrateChange, metricsBuilder);
    }

    @Override // com.amazon.avod.qos.reporter.internal.EventReporterBase, com.amazon.avod.qos.internal.LowMemoryReporter
    public void reportLowMemory() {
        onError("LowMemory", null, 4, null, null);
    }

    public void setDownloadPercentage(float f) {
        if (f < 0.0f || f > 100.0f) {
            DLog.warnf("Qos got an unexpected error percentage %s, ignoring", Float.valueOf(f));
        } else {
            getReporterContext().setDownloadPercentage(f);
        }
    }

    private boolean isDeliveryTypeDownload() {
        return getReporterContext().getDeliveryType() == DeliveryType.DOWNLOAD;
    }
}
