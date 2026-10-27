package com.amazon.avod.qos;

import android.content.Context;
import androidx.core.os.EnvironmentCompat;
import com.amazon.avod.content.smoothstream.StreamSelections;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.DataRate;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.contentcache.CacheRecord;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.error.MediaInternalErrorCode;
import com.amazon.avod.media.playback.support.RendererPerformanceData;
import com.amazon.avod.media.playback.support.VideoRenderer;
import com.amazon.avod.playback.LiveLanguageConfig;
import com.amazon.avod.playback.PlaybackPerformanceReport;
import com.amazon.avod.playback.PlaybackRestartEvent;
import com.amazon.avod.playback.event.AudioQualityChangeEvent;
import com.amazon.avod.playback.event.CacheStatusEvent;
import com.amazon.avod.playback.event.PlaybackLaunchedEvent;
import com.amazon.avod.playback.event.PlaybackRightsValidatedEvent;
import com.amazon.avod.playback.event.VideoQualityChangeEvent;
import com.amazon.avod.playback.event.playback.BufferStartEvent;
import com.amazon.avod.playback.event.playback.BufferStopEvent;
import com.amazon.avod.playback.event.playback.LiveManifestRefreshEvent;
import com.amazon.avod.playback.event.playback.LowFpsEvent;
import com.amazon.avod.playback.event.playback.PauseEvent;
import com.amazon.avod.playback.event.playback.PlaybackErrorEvent;
import com.amazon.avod.playback.event.playback.PlaybackPerformanceEvent;
import com.amazon.avod.playback.event.playback.PlaybackStartEvent;
import com.amazon.avod.playback.event.playback.PlaybackStopEvent;
import com.amazon.avod.playback.event.playback.ResumeEvent;
import com.amazon.avod.playback.event.playback.SeekEndEvent;
import com.amazon.avod.playback.event.playback.SeekStartEvent;
import com.amazon.avod.playback.event.playback.StatusEvent;
import com.amazon.avod.playback.event.playback.SubtitleEngineShutdownEvent;
import com.amazon.avod.playback.smoothstream.RestartBeginEvent;
import com.amazon.avod.qos.ErrorReportingConfiguration;
import com.amazon.avod.qos.internal.HdmiReceiver;
import com.amazon.avod.qos.internal.LowMemoryReceiver;
import com.amazon.avod.qos.listeners.QosEventListenerBase;
import com.amazon.avod.qos.metadata.DeliveryType;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.qos.reporter.PlaybackEventReporter;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackEventListener extends QosEventListenerBase {
    private volatile PlaybackErrorEvent mCachedPlaybackErrorEvent;
    private final ErrorReportingConfiguration mErrorConfig;
    private final ErrorReportingConfiguration.ErrorReportingConfig mErrorReportingConfig;
    private final ExecutorService mExecutor;
    private final HdmiReceiver mHdmiReceiver;
    private volatile boolean mIsLiveKeyRotationRestartPending;
    private final LowMemoryReceiver mLowMemReceiver;
    private PlaybackEventReporter mReporter;

    public PlaybackEventListener(ExecutorService executorService, Context context) {
        this(new ErrorReportingConfiguration(), executorService, new HdmiReceiver(context), new LowMemoryReceiver(context), ErrorReportingConfiguration.ErrorReportingConfig.getInstance());
    }

    PlaybackEventListener(ErrorReportingConfiguration errorReportingConfiguration, ExecutorService executorService, HdmiReceiver hdmiReceiver, LowMemoryReceiver lowMemoryReceiver, @Nonnull ErrorReportingConfiguration.ErrorReportingConfig errorReportingConfig) {
        this.mIsLiveKeyRotationRestartPending = false;
        this.mErrorConfig = errorReportingConfiguration;
        this.mExecutor = executorService;
        this.mHdmiReceiver = hdmiReceiver;
        this.mLowMemReceiver = lowMemoryReceiver;
        this.mErrorReportingConfig = (ErrorReportingConfiguration.ErrorReportingConfig) Preconditions.checkNotNull(errorReportingConfig, "errorReportingConfig");
    }

    public void bind(PlaybackEventTransport playbackEventTransport, PlaybackEventReporter playbackEventReporter) {
        this.mReporter = playbackEventReporter;
        super.bind(playbackEventTransport);
        this.mHdmiReceiver.register(this.mReporter);
        this.mLowMemReceiver.register(this.mReporter);
    }

    @Override // com.amazon.avod.qos.listeners.QosEventListenerBase
    public void release() {
        this.mLowMemReceiver.unregister();
        this.mHdmiReceiver.unregister();
        super.release();
    }

    public void setDeliveryType(@Nonnull DeliveryType deliveryType) {
        this.mReporter.setDeliveryType((DeliveryType) Preconditions.checkNotNull(deliveryType));
    }

    public void setAudioLanguage(@Nullable String str) {
        this.mReporter.setAudioLanguage(str);
    }

    @Subscribe
    public void playbackRightsValidatedEvent(PlaybackRightsValidatedEvent playbackRightsValidatedEvent) {
        this.mReporter.onPlaybackAuthorityDetermined(playbackRightsValidatedEvent.getPlaybackAuthorityId());
    }

    @Subscribe
    public void playbackLaunchedEvent(PlaybackLaunchedEvent playbackLaunchedEvent) {
        this.mReporter.onMediaPlayerStart(playbackLaunchedEvent.getEventTimeStamp(), playbackLaunchedEvent.getEpochTimeStamp());
        this.mReporter.reportMetric(QOSEventName.WhisperCache.name(), playbackLaunchedEvent.wasPreparedAtLaunch() ? "PreinitHit" : "PreinitMiss", null, null, null);
        this.mReporter.reportMetric(QOSEventName.Information.name(), "SurfaceHandlingMode", null, playbackLaunchedEvent.getSurfaceHandlingMode().name(), null);
    }

    @Subscribe
    public void startBufferEvent(BufferStartEvent bufferStartEvent) {
        if (!this.mIsLiveKeyRotationRestartPending) {
            this.mReporter.reportBufferStart(bufferStartEvent.getEventTimeStamp(), bufferStartEvent.getEpochTimeStamp(), bufferStartEvent.getBufferingAnalysis());
        } else {
            DLog.logf("BufferStartEvent qos reporting is skipped due to live keyrotation restarting pending");
        }
    }

    @Subscribe
    public void stopBufferEvent(BufferStopEvent bufferStopEvent) {
        if (!this.mIsLiveKeyRotationRestartPending) {
            this.mReporter.reportBufferEnd(bufferStopEvent.getEventTimeStamp(), bufferStopEvent.getEpochTimeStamp());
        } else {
            DLog.logf("BufferStopEvent qos reporting is skipped due to live keyrotation restarting pending");
        }
    }

    @Subscribe
    public synchronized void startSeekEvent(SeekStartEvent seekStartEvent) {
        this.mReporter.onPlay(seekStartEvent.getEventTimeStamp(), seekStartEvent.getEpochTimeStamp());
        this.mReporter.reportSeekStart(seekStartEvent.getEventTimeStamp(), seekStartEvent.getEpochTimeStamp());
    }

    @Subscribe
    public synchronized void endSeekEvent(SeekEndEvent seekEndEvent) {
        this.mReporter.reportSeekEnd(seekEndEvent.getTargetSeekTime(), seekEndEvent.getEpochTimeStamp(), seekEndEvent.getSeekCause());
    }

    @Subscribe
    public void startPlaybackEvent(final PlaybackStartEvent playbackStartEvent) {
        this.mIsLiveKeyRotationRestartPending = false;
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.qos.PlaybackEventListener.1
            @Override // java.lang.Runnable
            public void run() {
                long totalSeconds = playbackStartEvent.getEventTimeStamp().getTotalSeconds();
                StreamSelections streamSelections = playbackStartEvent.getStreamSelections();
                PlaybackEventListener.this.mReporter.onInitialPlaybackIndex(totalSeconds);
                PlaybackEventListener.this.mReporter.onPlaybackStart(playbackStartEvent.getEventTimeStamp(), playbackStartEvent.getEpochTimeStamp(), null, playbackStartEvent.getDeviceCapability(), playbackStartEvent.getRendererInfo(), playbackStartEvent.getHeuristicsSettingsId(), playbackStartEvent.getAudioAdaptationSetSwitchContext(), streamSelections.getVideoStream().getFourCC(), streamSelections.getAudioStream().getFourCC(), playbackStartEvent.getContentStoreType(), playbackStartEvent.isLiveStream());
                PlaybackEventListener.this.mReporter.reportRendererStatus(VideoRenderer.RendererStatusFlags.getReportingString(playbackStartEvent.getRendererStatusFlags()));
            }
        });
    }

    @Subscribe
    public void restartPlaybackEvent(PlaybackRestartEvent playbackRestartEvent) {
        if (playbackRestartEvent.getType() == 3) {
            this.mIsLiveKeyRotationRestartPending = true;
        }
    }

    @Subscribe
    public void stopPlaybackEvent(PlaybackStopEvent playbackStopEvent) {
        TimeSpan epochTimeStamp = playbackStopEvent.getEpochTimeStamp();
        this.mReporter.onPlaybackStop(epochTimeStamp, playbackStopEvent.getRendererScheme(), playbackStopEvent.getDrmScheme(), playbackStopEvent.getStreamMetaData());
        this.mReporter.reportSession(epochTimeStamp);
    }

    @Subscribe
    public void statusEvent(StatusEvent statusEvent) {
        this.mReporter.onPlaybackIndex(statusEvent.getEventTimeStamp().getTotalSeconds());
        RendererPerformanceData rendererPerformanceData = statusEvent.getRendererPerformanceData();
        if (rendererPerformanceData != null) {
            this.mReporter.onPerformanceDataReported(rendererPerformanceData.getRenderDropNum(), rendererPerformanceData.getTotalRenderDropNum(), rendererPerformanceData.getAverageFramesPerSecond());
        }
        TimeSpan epochTimeStamp = statusEvent.getEpochTimeStamp();
        this.mReporter.onPlaybackUpdate(epochTimeStamp);
        this.mReporter.onPeriodicPlaybackSession(epochTimeStamp);
    }

    @Subscribe
    public void pauseEvent(PauseEvent pauseEvent) {
        this.mReporter.onPause(pauseEvent.getEventTimeStamp(), pauseEvent.getEpochTimeStamp());
    }

    @Subscribe
    public void resumeEvent(ResumeEvent resumeEvent) {
        this.mReporter.onPlay(resumeEvent.getEventTimeStamp(), resumeEvent.getEpochTimeStamp());
    }

    @Subscribe
    public void lowFpsEvent(LowFpsEvent lowFpsEvent) {
        Locale locale = Locale.US;
        this.mReporter.onLowFps(lowFpsEvent.getEpochTimeStamp(), String.format(locale, "Rendered time: %sms, Real time: %sms", Long.valueOf(lowFpsEvent.getRenderedDuration().getTotalMilliseconds()), Long.valueOf(lowFpsEvent.getRealtimeDuration().getTotalMilliseconds())), String.format(locale, "Low FPS for %s consecutive seconds", Integer.valueOf(lowFpsEvent.getConsecutiveCount())));
    }

    @Subscribe
    public void playbackPerformanceEvent(PlaybackPerformanceEvent playbackPerformanceEvent) {
        PlaybackPerformanceReport playbackPerformanceReport = playbackPerformanceEvent.getPlaybackPerformanceReport();
        if (playbackPerformanceReport == null || playbackPerformanceReport.getSeverity() == PlaybackPerformanceReport.PlaybackPerformanceSeverity.NONE) {
            return;
        }
        this.mReporter.onLowPerformanceReported(playbackPerformanceReport, playbackPerformanceEvent.getEventTimeStamp());
    }

    @Subscribe
    public void errorEvent(PlaybackErrorEvent playbackErrorEvent) {
        String str;
        MediaException playbackException = playbackErrorEvent.getPlaybackException();
        String string = playbackException.toString();
        int severityFor = this.mErrorConfig.getSeverityFor(playbackException, playbackErrorEvent.isRetriable(), playbackErrorEvent.getHttpErrorCode());
        if (this.mErrorReportingConfig.shouldDedupeFatalErrorEvents() && severityFor <= 2 && playbackErrorEvent.equals(this.mCachedPlaybackErrorEvent)) {
            return;
        }
        this.mCachedPlaybackErrorEvent = playbackErrorEvent;
        PlaybackEventReporter playbackEventReporter = this.mReporter;
        MediaInternalErrorCode errorCode = playbackException.getErrorCode();
        TimeSpan epochTimeStamp = playbackErrorEvent.getEpochTimeStamp();
        String note = playbackErrorEvent.getNote();
        if (playbackErrorEvent.getPlaybackException() != null) {
            str = "" + playbackErrorEvent.getPlaybackException().getUrl();
        } else {
            str = null;
        }
        playbackEventReporter.onPlaybackError(errorCode, epochTimeStamp, severityFor, string, note, str);
    }

    @Subscribe
    public void videoQualityChangeEvent(VideoQualityChangeEvent videoQualityChangeEvent) {
        this.mReporter.onBitrateChange(new DataRate(videoQualityChangeEvent.getBitrate(), DataUnit.BITS));
    }

    @Subscribe
    public void audioQualityChangeEvent(AudioQualityChangeEvent audioQualityChangeEvent) {
        this.mReporter.setIsDolbyDigitalPlusStream(audioQualityChangeEvent.getAudioFormat() == AudioFormat.AC_3_5_1);
    }

    @Subscribe
    public void cacheStatusEvent(CacheStatusEvent cacheStatusEvent) {
        String str;
        StringBuilder sb = new StringBuilder();
        TimeSpan startTime = cacheStatusEvent.getVideoSpec().getStartTime();
        CacheRecord record = cacheStatusEvent.getRecord();
        if (record != null) {
            sb.append(record);
            sb.append("|");
            if (record.beginsAtTimestamp(startTime)) {
                int i = AnonymousClass2.$SwitchMap$com$amazon$avod$media$contentcache$CacheRecord$RecordState[record.getCacheState().ordinal()];
                str = i != 1 ? i != 2 ? "SuggestedButNotCached" : "PartiallyCached" : "FullyCached";
            } else {
                str = "CachedAtOtherStartPoint";
            }
        } else {
            str = "CacheMiss";
        }
        sb.append("Requested start time = ");
        sb.append(startTime);
        this.mReporter.reportMetric(QOSEventName.WhisperCache.name(), str, null, sb.toString(), null);
    }

    /* JADX INFO: renamed from: com.amazon.avod.qos.PlaybackEventListener$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$contentcache$CacheRecord$RecordState;

        static {
            int[] iArr = new int[CacheRecord.RecordState.values().length];
            $SwitchMap$com$amazon$avod$media$contentcache$CacheRecord$RecordState = iArr;
            try {
                iArr[CacheRecord.RecordState.FULLY_CACHED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$contentcache$CacheRecord$RecordState[CacheRecord.RecordState.IN_PROGRESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Subscribe
    public void onRestartBeginEvent(RestartBeginEvent restartBeginEvent) {
        int type = restartBeginEvent.getCause().getType();
        if (type != 0 && type != 11) {
            if (type == 4 || type == 5) {
                this.mReporter.handleAudioAdaptationSetSwitchStart(restartBeginEvent.getAudioAdaptationSetName(), restartBeginEvent.getAudioAdaptationSetBitrate(), restartBeginEvent.getCause().getTypeString(), restartBeginEvent.getStreamSelectionParams());
                return;
            }
            return;
        }
        String languageCode = getLanguageCode(restartBeginEvent.getOldLanguage());
        String languageCode2 = getLanguageCode(restartBeginEvent.getNewLanguage());
        boolean zIsEmpty = restartBeginEvent.getOldSpec().getAudioTrackIds().isEmpty();
        String str = EnvironmentCompat.MEDIA_UNKNOWN;
        String str2 = zIsEmpty ? EnvironmentCompat.MEDIA_UNKNOWN : restartBeginEvent.getOldSpec().getAudioTrackIds().get(0);
        if (!restartBeginEvent.getNewSpec().getAudioTrackIds().isEmpty()) {
            str = restartBeginEvent.getNewSpec().getAudioTrackIds().get(0);
        }
        this.mReporter.handleAudioTrackSwitchStart(languageCode, languageCode2, str2, str);
    }

    private String getLanguageCode(String str) {
        String str2 = LiveLanguageConfig.getInstance().getLanguageCodeMapping().get(str);
        return str2 == null ? EnvironmentCompat.MEDIA_UNKNOWN : str2;
    }

    @Subscribe
    public void onSubtitleEngineShutdownEvent(SubtitleEngineShutdownEvent subtitleEngineShutdownEvent) {
        this.mReporter.reportSubtitlesAggregate(subtitleEngineShutdownEvent.getSubtitlesAggregator().getQosSummary());
    }

    @Subscribe
    public void onLiveManifestRefreshEvent(LiveManifestRefreshEvent liveManifestRefreshEvent) {
        this.mReporter.reportLiveManifestRefresh(liveManifestRefreshEvent.getTargetTime(), liveManifestRefreshEvent.getEpochTimeStamp(), liveManifestRefreshEvent.getManifestRefreshDuration(), String.format(Locale.US, "SeekCause: %s | Seek in cache region: %s", liveManifestRefreshEvent.getSeekAction().getSeekCause().name().toString(), Boolean.valueOf(liveManifestRefreshEvent.isInCachedRegion())));
    }
}
