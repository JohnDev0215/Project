package com.amazon.avod.media.ads.internal.adplaybackstatemachine;

import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.event.AdEventTransport;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.fsm.SimpleTrigger;
import com.amazon.avod.fsm.State;
import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdClip;
import com.amazon.avod.media.ads.AdEnabledVideoEventListener;
import com.amazon.avod.media.ads.AdError;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.ads.internal.AdBreakSelector;
import com.amazon.avod.media.ads.internal.AdEnabledVideoPlayer;
import com.amazon.avod.media.ads.internal.AdManagerBasedAdClip;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachine;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.LiveAdTrackingManager;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.ads.internal.state.AdEnabledPlayerTriggerType;
import com.amazon.avod.media.ads.internal.state.AdPlanUpdateListener;
import com.amazon.avod.media.ads.internal.state.ClipErrorTrigger;
import com.amazon.avod.media.diagnostics.DiagnosticsOverlayToggler;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.framework.volume.VolumeManager;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoPlayerLifecyleEventHandler;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoPresentationEventListener;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.pmet.PmetAcquisitionEventListener;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusAdIdentifierReporter;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.state.PlayerStateType;
import com.amazon.avod.media.playback.state.trigger.ErrorTrigger;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggers;
import com.amazon.avod.media.playback.state.trigger.SeekTrigger;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AdEnabledPlaybackStateMachine extends AdPlaybackStateMachine {
    protected AdEnabledVideoPlayer mAdEnabledVideoPlayer;

    @Nonnull
    protected final AdEventTransport mAdEventTransport;
    protected final ExecutorService mAdExecutor;
    protected final AdvertisingIdCollector mAdvertisingIdCollector;
    protected final AloysiusAdIdentifierReporter mAloysiusAdIdentifierReporter;
    protected final AdBreakSelector mBreakSelector;
    protected final AdsConfig mConfig;
    protected final AdPlaybackStateMachineContext mContext;
    protected final DiagnosticsOverlayToggler mDiagnosticsToggler;
    protected final ContentManagementEventBus mEventBus;
    protected boolean mIsAdPlayerPrepared;
    protected volatile boolean mOnStartedInvoked;
    protected final PlaybackEventTransport mPlaybackEventTransport;
    protected final PlaybackMediaEventReporters mPlaybackMediaEventReporters;
    protected final PmetAcquisitionEventListener mPmetAcquisitionEventListener;
    protected final boolean mShouldUnifyMediaEventReporters;
    protected final Object mStateChangeMutex;
    protected final TimelineMonitor mTimelineMonitor;

    @Nonnull
    protected final VideoPlayerLifecyleEventHandler mVideoPlayerLifecycleEventHandler;
    protected final VolumeManager mVolumeManager;

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onTerminated() {
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void updateAdPlan(@Nonnull AdPlan adPlan) {
    }

    public AdEnabledPlaybackStateMachine(@Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull TimelineMonitor timelineMonitor, @Nonnull AdBreakSelector adBreakSelector, @Nonnull VolumeManager volumeManager, @Nonnull AdsConfig adsConfig, @Nonnull DiagnosticsOverlayToggler diagnosticsOverlayToggler, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters, @Nonnull AdEventTransport adEventTransport, @Nonnull VideoPlayerLifecyleEventHandler videoPlayerLifecyleEventHandler, @Nonnull AdEnabledVideoPlayer adEnabledVideoPlayer, @Nonnull PmetAcquisitionEventListener pmetAcquisitionEventListener, @Nonnull ContentManagementEventBus contentManagementEventBus, Boolean bool) {
        super("AdEnabledPlayback");
        this.mStateChangeMutex = new Object();
        this.mOnStartedInvoked = false;
        this.mIsAdPlayerPrepared = false;
        this.mAdvertisingIdCollector = (AdvertisingIdCollector) Preconditions.checkNotNull(advertisingIdCollector, "advertisingIdCollector");
        this.mContext = (AdPlaybackStateMachineContext) Preconditions.checkNotNull(adPlaybackStateMachineContext, "context");
        this.mTimelineMonitor = (TimelineMonitor) Preconditions.checkNotNull(timelineMonitor, "timelineMonitor");
        this.mBreakSelector = (AdBreakSelector) Preconditions.checkNotNull(adBreakSelector, "breakSelector");
        this.mVolumeManager = (VolumeManager) Preconditions.checkNotNull(volumeManager, "volumeManager");
        this.mConfig = (AdsConfig) Preconditions.checkNotNull(adsConfig, "config");
        this.mDiagnosticsToggler = (DiagnosticsOverlayToggler) Preconditions.checkNotNull(diagnosticsOverlayToggler, "diagnosticsToggler");
        this.mPlaybackEventTransport = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "playbackEventTransport");
        PlaybackMediaEventReporters playbackMediaEventReporters2 = (PlaybackMediaEventReporters) Preconditions.checkNotNull(playbackMediaEventReporters, "playbackMediaEventReporters");
        this.mPlaybackMediaEventReporters = playbackMediaEventReporters2;
        this.mAloysiusAdIdentifierReporter = playbackMediaEventReporters2.getAloysiusAdIdentifierReporter();
        this.mAdEventTransport = (AdEventTransport) Preconditions.checkNotNull(adEventTransport, "adEventTransport");
        this.mVideoPlayerLifecycleEventHandler = (VideoPlayerLifecyleEventHandler) Preconditions.checkNotNull(videoPlayerLifecyleEventHandler, "videoPlayerLifecyleEventHandler");
        this.mAdEnabledVideoPlayer = (AdEnabledVideoPlayer) Preconditions.checkNotNull(adEnabledVideoPlayer, "adEnabledVideoPlayer");
        this.mAdExecutor = createAdExecutor();
        this.mPmetAcquisitionEventListener = (PmetAcquisitionEventListener) Preconditions.checkNotNull(pmetAcquisitionEventListener, "pmetAcquisitionEventListener");
        this.mEventBus = (ContentManagementEventBus) Preconditions.checkNotNull(contentManagementEventBus, "contentManagementEventBus");
        this.mShouldUnifyMediaEventReporters = bool.booleanValue();
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    @Deprecated
    public void setPresentationEventListener(VideoPresentation videoPresentation, VideoPresentationEventListener videoPresentationEventListener) {
        throw new UnsupportedOperationException("setPresentationEventListener not implemented in DA");
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void setAdEventListener(AdEnabledVideoEventListener adEnabledVideoEventListener) {
        this.mContext.setListener(adEnabledVideoEventListener);
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public boolean addAdPlanUpdateListener(@Nonnull AdPlanUpdateListener adPlanUpdateListener) {
        Preconditions.checkNotNull(adPlanUpdateListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        return this.mContext.addAdPlanUpdateListener(adPlanUpdateListener);
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    @Nonnull
    @Deprecated
    public AdPlan getPlan() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    @Nonnull
    public AdPlaybackStateMachineContext getContext() {
        return this.mContext;
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void setRenderingSettings(VideoRenderingSettings videoRenderingSettings) {
        this.mContext.getAdTransitioner().initialize(videoRenderingSettings.getParentView());
        this.mContext.setRenderingSettings(videoRenderingSettings);
    }

    private ExecutorService createAdExecutor() {
        return ScheduledExecutorBuilder.newBuilderFor(LiveAdTrackingManager.class, new String[0]).withFixedThreadPoolSize(AdsConfig.getInstance().getBeaconThreadCount()).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).withRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy()).build();
    }

    @Override // com.amazon.avod.fsm.BlockingStateMachine, com.amazon.avod.fsm.StateMachine
    public boolean doTrigger(Trigger<PlayerTriggerType> trigger) {
        boolean zDoTrigger;
        synchronized (this.mStateChangeMutex) {
            State<PlayerStateType, PlayerTriggerType> currentState = getCurrentState();
            zDoTrigger = super.doTrigger(trigger);
            if (zDoTrigger) {
                DLog.logf("Trigger %s caused transition from %s to %s.", trigger, currentState, getCurrentState());
            } else {
                DLog.logf("Trigger %s was ignored based on the current state (%s).", trigger, currentState);
            }
        }
        return zDoTrigger;
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void shutdown(boolean z) {
        ExecutorService executorService;
        this.mContext.getUriProxy().cleanup();
        boolean zDoTrigger = doTrigger(PlayerTriggers.SHUTDOWN);
        if (!this.mShouldUnifyMediaEventReporters) {
            this.mPlaybackMediaEventReporters.terminate();
        }
        this.mPmetAcquisitionEventListener.terminate();
        if (this.mConfig.shouldShutdownExecutor() && (executorService = this.mAdExecutor) != null) {
            executorService.shutdown();
        }
        if (zDoTrigger) {
            super.shutdown();
        }
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void play() {
        doTrigger(PlayerTriggers.PLAY);
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void pause() {
        doTrigger(PlayerTriggers.PAUSE);
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void seekTo(TimeSpan timeSpan) {
        doTrigger(new SeekTrigger(timeSpan));
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void launchPlayback() {
        doTrigger(new SimpleTrigger(AdEnabledPlayerTriggerType.PLAY_PRE_ROLLS));
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onCompletion(VideoPresentation videoPresentation) {
        doTrigger(PlayerTriggers.COMPLETED);
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onError(VideoPresentation videoPresentation, MediaErrorCode mediaErrorCode) {
        AdManagerBasedAdClip adManagerBasedAdClipFindScheduledClip = findScheduledClip(videoPresentation.getPlayer());
        if (adManagerBasedAdClipFindScheduledClip != null) {
            doTrigger(new ClipErrorTrigger(adManagerBasedAdClipFindScheduledClip, AdError.PLAYBACK_ERROR, String.format(Locale.US, "Ad playback error %s occured", mediaErrorCode)));
        } else {
            doTrigger(new ErrorTrigger(mediaErrorCode));
        }
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onPrepared(VideoPresentation videoPresentation, PlaybackDataSource playbackDataSource) {
        AdManagerBasedAdClip adManagerBasedAdClipFindScheduledClip = findScheduledClip(videoPresentation.getPlayer());
        if (adManagerBasedAdClipFindScheduledClip != null) {
            adManagerBasedAdClipFindScheduledClip.onPrepared();
        }
        this.mIsAdPlayerPrepared = true;
        doTrigger(PlayerTriggers.PREPARED);
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onStarted(@Nonnull VideoPresentation videoPresentation, @Nonnull PlaybackDataSource playbackDataSource) {
        if (this.mOnStartedInvoked) {
            return;
        }
        this.mOnStartedInvoked = true;
        this.mVideoPlayerLifecycleEventHandler.onStarted((PlaybackDataSource) Preconditions.checkNotNull(playbackDataSource, "playbackDataSource"));
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachine
    @Nonnull
    public PlaybackEventReporter getPrimaryPlayerEventReporter() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachine
    public void setRenderingSettingsOnPrimaryPlayer(@Nonnull VideoRenderingSettings videoRenderingSettings) {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachine
    public void setCyclicReportingPaused(boolean z) {
        this.mPlaybackMediaEventReporters.setCyclicReportingPaused(z);
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public boolean isAdPlayerPrepared() {
        return this.mIsAdPlayerPrepared;
    }

    private AdManagerBasedAdClip findScheduledClip(VideoPlayer videoPlayer) {
        Iterator<AdBreak> it = this.mContext.getPlan().getBreaks().iterator();
        while (it.hasNext()) {
            for (AdClip adClip : it.next().getClips()) {
                if (adClip.getPlayer() == videoPlayer) {
                    return (AdManagerBasedAdClip) CastUtils.castTo(adClip, AdManagerBasedAdClip.class);
                }
            }
        }
        return null;
    }
}
