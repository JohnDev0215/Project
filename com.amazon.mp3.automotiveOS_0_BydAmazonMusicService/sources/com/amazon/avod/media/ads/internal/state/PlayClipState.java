package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.ads.api.AdSkipInfo;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.DataRate;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.ads.AdError;
import com.amazon.avod.media.ads.internal.AdManagerBasedAdClip;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.config.PlaybackZoomConfig;
import com.amazon.avod.media.framework.volume.VolumeChangeListener;
import com.amazon.avod.media.framework.volume.VolumeManager;
import com.amazon.avod.media.playback.VideoAttributes;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.PlaybackStateEventListener;
import com.amazon.avod.playback.PlaybackZoomLevel;
import com.amazon.avod.playback.event.playback.TimeDataChangeEvent;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.qos.metrics.MetricsBuilder;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlayClipState extends AdEnabledPlaybackState {
    private final AdsConfig mAdsConfig;
    private final AdBreakBufferEventListener mBufferEventListener;
    private ScheduledExecutorService mExecutor;
    boolean mHasEnterExecuted;
    private final boolean mIsSdkPlayer;
    private final PlaybackEventTransport mPlaybackEventTransport;
    private final PlaybackZoomLevel mPlaybackZoomLevel;
    final PlaybackStateEventListener mStateEventListener;
    private final TimeSpan mStatusEventIntervalMillis;
    private final TimelineMonitor mTimelineMonitor;
    final VolumeChangeListener mVolumeChangeListener;
    private final VolumeManager mVolumeManager;

    public PlayClipState(@Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull AdsConfig adsConfig, @Nonnull TimelineMonitor timelineMonitor, @Nonnull VolumeManager volumeManager, @Nonnull PlaybackEventTransport playbackEventTransport) {
        this(adPlaybackStateMachineContext, adsConfig, timelineMonitor, volumeManager, new AdBreakBufferEventListener(adPlaybackStateMachineContext, AdsConfig.getInstance(), adPlaybackStateMachineContext.getStateMachine()), PlaybackZoomConfig.getInstance().getPlaybackZoomLevel(), playbackEventTransport, Executors.newSingleThreadScheduledExecutor(), PlaybackConfig.INSTANCE.getPlaybackStatusEventIntervalMillis(), MediaSystemSharedDependencies.getInstance().isSDKPlayer());
    }

    PlayClipState(@Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull AdsConfig adsConfig, @Nonnull TimelineMonitor timelineMonitor, @Nonnull VolumeManager volumeManager, @Nonnull AdBreakBufferEventListener adBreakBufferEventListener, @Nonnull PlaybackZoomLevel playbackZoomLevel, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull ScheduledExecutorService scheduledExecutorService, @Nonnull TimeSpan timeSpan, boolean z) {
        super(AdEnabledPlayerStateType.PLAYING_AD, adPlaybackStateMachineContext);
        this.mVolumeChangeListener = new VolumeChangeListener() { // from class: com.amazon.avod.media.ads.internal.state.PlayClipState.1
            @Override // com.amazon.avod.media.framework.volume.VolumeChangeListener
            public void onVolumeChange(int i, int i2) {
                AdManagerBasedAdClip currentAdClip = PlayClipState.this.getContext().getCurrentAdClip();
                if (currentAdClip == null) {
                    return;
                }
                if (i == PlayClipState.this.mVolumeManager.getMinimumVolume() && i2 != PlayClipState.this.mVolumeManager.getMinimumVolume()) {
                    currentAdClip.sendUnmuteEvent(PlayClipState.this.getContext().getOfferType());
                }
                if (i == PlayClipState.this.mVolumeManager.getMinimumVolume() || i2 != PlayClipState.this.mVolumeManager.getMinimumVolume()) {
                    return;
                }
                currentAdClip.sendMuteEvent(PlayClipState.this.getContext().getOfferType());
            }
        };
        this.mStateEventListener = new PlaybackStateEventListener() { // from class: com.amazon.avod.media.ads.internal.state.PlayClipState.2
            @Override // com.amazon.avod.playback.PlaybackStateEventListener
            public void onSeekEnd(PlaybackEventContext playbackEventContext) {
            }

            @Override // com.amazon.avod.playback.PlaybackStateEventListener
            public void onSeekStart(TimeSpan timeSpan2, PlaybackEventContext playbackEventContext) {
            }

            @Override // com.amazon.avod.playback.PlaybackStateEventListener
            public void onStart(PlaybackEventContext playbackEventContext) {
            }

            @Override // com.amazon.avod.playback.PlaybackStateEventListener
            public void onStop(PlaybackEventContext playbackEventContext) {
            }

            @Override // com.amazon.avod.playback.PlaybackStateEventListener
            public void onResume(PlaybackEventContext playbackEventContext) {
                AdManagerBasedAdClip currentAdClip = PlayClipState.this.getContext().getCurrentAdClip();
                if (currentAdClip == null) {
                    return;
                }
                currentAdClip.sendResumeEvent(PlayClipState.this.getContext().getOfferType());
            }

            @Override // com.amazon.avod.playback.PlaybackStateEventListener
            public void onPause(PlaybackEventContext playbackEventContext) {
                AdManagerBasedAdClip currentAdClip = PlayClipState.this.getContext().getCurrentAdClip();
                if (currentAdClip == null) {
                    return;
                }
                currentAdClip.sendPauseEvent(PlayClipState.this.getContext().getOfferType());
            }
        };
        this.mTimelineMonitor = (TimelineMonitor) Preconditions.checkNotNull(timelineMonitor, "timelineMonitor");
        this.mVolumeManager = (VolumeManager) Preconditions.checkNotNull(volumeManager, "volumeManager");
        this.mBufferEventListener = (AdBreakBufferEventListener) Preconditions.checkNotNull(adBreakBufferEventListener, "bufferEventListener");
        this.mAdsConfig = (AdsConfig) Preconditions.checkNotNull(adsConfig, "adsConfig");
        this.mPlaybackZoomLevel = (PlaybackZoomLevel) Preconditions.checkNotNull(playbackZoomLevel, "playbackZoomLevel");
        this.mPlaybackEventTransport = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "playbackEventTransport");
        this.mExecutor = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "executor");
        this.mStatusEventIntervalMillis = (TimeSpan) Preconditions.checkNotNull(timeSpan, "playbackStatusEventIntervalMillis");
        this.mIsSdkPlayer = z;
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<PlayerTriggerType> trigger) {
        AdManagerBasedAdClip currentAdClip = getContext().getCurrentAdClip();
        Preconditions.checkNotNull(currentAdClip);
        if (!currentAdClip.isPrepared()) {
            DLog.warnf("Skipping ad due to entering PlayClipState while clip is not yet prepared");
            doTrigger(new ClipErrorTrigger(currentAdClip, AdError.FILE_NOT_AVAILABLE, "Entered PlayClipState with the clip not prepared"));
            return;
        }
        currentAdClip.sendStartEvent(getContext().getOfferType(), getConsumptionId());
        currentAdClip.scheduleTimelineEvents(this.mTimelineMonitor);
        if (this.mVolumeManager.getCurrentVolume() == 0) {
            currentAdClip.sendMuteEvent(getContext().getOfferType());
        }
        this.mVolumeManager.startListening(this.mVolumeChangeListener);
        this.mBufferEventListener.onStartingClip();
        VideoPlayer player = currentAdClip.getPresentation().getPlayer();
        player.addListener(this.mStateEventListener);
        player.addListener(this.mBufferEventListener);
        player.start();
        reportAdClipQualityToQos();
        if (player.getPlaybackExperienceController() != null) {
            if (currentAdClip.getSelectedMediaFile().isScalable() == null || !this.mAdsConfig.getHonorAdScalableProperty() || (this.mAdsConfig.getHonorAdScalableProperty() && currentAdClip.getSelectedMediaFile().isScalable().booleanValue())) {
                player.getPlaybackExperienceController().setZoomLevel(this.mPlaybackZoomLevel);
            }
            if (!this.mIsSdkPlayer) {
                player.getPlaybackExperienceController().scaleVolume(this.mAdsConfig.getAdVolumeScaleFactor());
            }
        }
        if (this.mStatusEventIntervalMillis.getTotalMilliseconds() > 0) {
            if (this.mExecutor.isShutdown()) {
                this.mExecutor = Executors.newSingleThreadScheduledExecutor();
            }
            scheduleTimeDataChangeEvent();
        }
        this.mHasEnterExecuted = true;
    }

    private void scheduleTimeDataChangeEvent() {
        final AtomicLong atomicLong = new AtomicLong(-1L);
        this.mExecutor.scheduleWithFixedDelay(new Runnable() { // from class: com.amazon.avod.media.ads.internal.state.PlayClipState$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$scheduleTimeDataChangeEvent$0(atomicLong);
            }
        }, 0L, this.mStatusEventIntervalMillis.getTotalMilliseconds(), TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scheduleTimeDataChangeEvent$0(AtomicLong atomicLong) {
        long currentPosition = getContext().getCurrentAdClip().getPlayer().getCurrentPosition();
        if (currentPosition != atomicLong.get()) {
            this.mPlaybackEventTransport.postEvent(new TimeDataChangeEvent(currentPosition));
            atomicLong.set(currentPosition);
        }
    }

    @Override // com.amazon.avod.media.ads.internal.state.AdEnabledPlaybackState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void exit(Trigger<PlayerTriggerType> trigger) {
        if (trigger.getType() == AdEnabledPlayerTriggerType.SKIP_CURRENT_AD_CLIP) {
            getContext().getCurrentAdClip().sendSkipClipEvent(getContext().getOfferType());
        }
        if (this.mHasEnterExecuted) {
            this.mVolumeManager.stopListening();
            this.mBufferEventListener.onEndingClip();
            this.mExecutor.shutdown();
        }
    }

    void reportAdClipQualityToQos() {
        AdManagerBasedAdClip currentAdClip = getContext().getCurrentAdClip();
        VideoPresentation presentation = currentAdClip.getPresentation();
        VideoAttributes attributes = presentation.getSpecification().getAttributes();
        VideoResolution resolution = attributes.getResolution();
        DataRate bitrate = attributes.getBitrate();
        PlaybackEventReporter reporter = presentation.getReporter();
        String string = currentAdClip.getSelectedMediaFile().toString();
        String str = getContext().getPlan().isDraper() ? "Draper" : "Non-Draper";
        AdSkipInfo adSkipInfo = currentAdClip.getAdSkipInfo();
        String str2 = String.format(Locale.US, "Skippable: %b, AdType: %s, MediaFile: %s", Boolean.valueOf(adSkipInfo != null && adSkipInfo.getMinAdLength() <= currentAdClip.getDuration().getTotalMilliseconds()), str, string);
        DLog.logf(str2);
        reporter.reportMetric(new MetricsBuilder().eventType(QOSEventName.Information.toString()).eventSubtype("AdClipPlaybackInfo").clientWidth(Integer.valueOf(resolution.getWidth())).clientHeight(Integer.valueOf(resolution.getHeight())).adBitrate((int) bitrate.getBitsPerSecond()).note(str2));
    }
}
