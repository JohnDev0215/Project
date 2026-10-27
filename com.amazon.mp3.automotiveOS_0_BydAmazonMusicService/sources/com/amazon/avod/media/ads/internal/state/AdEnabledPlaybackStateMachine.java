package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.event.AdEventTransport;
import com.amazon.avod.event.PlaybackEventTransport;
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
import com.amazon.avod.media.ads.internal.AdPlanFactory;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachine;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.LiveAdTrackingManager;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.diagnostics.DiagnosticsOverlayToggler;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.framework.volume.VolumeManager;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoPresentationEventListener;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.pmet.PmetAcquisitionEventListener;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.state.PlayerStateType;
import com.amazon.avod.media.playback.state.trigger.ErrorTrigger;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggerType;
import com.amazon.avod.media.playback.state.trigger.PlayerTriggers;
import com.amazon.avod.media.playback.state.trigger.SeekTrigger;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.smoothstream.ServerInsertedManifestTimelineManager;
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
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public abstract class AdEnabledPlaybackStateMachine extends AdPlaybackStateMachine {
    protected final AdEnabledVideoPlayer mAdEnabledVideoPlayer;

    @Nonnull
    protected final AdEventTransport mAdEventTransport;
    protected final ExecutorService mAdExecutorService;
    protected final AdvertisingIdCollector mAdvertisingIdCollector;
    protected final AdBreakSelector mBreakSelector;
    protected final AdsConfig mConfig;
    protected final AdPlaybackStateMachineContext mContext;
    protected final DiagnosticsOverlayToggler mDiagnosticsToggler;
    protected final ContentManagementEventBus mEventBus;
    protected final ExecutorService mExecutor;
    private volatile boolean mIsAdPlayerPrepared;
    protected final String mOfferType;
    protected final AdPlanFactory mPlanFactory;
    protected final PlaybackEventTransport mPlaybackEventTransport;
    protected final PlaybackMediaEventReporters mPlaybackMediaEventReporters;
    protected final PmetAcquisitionEventListener mPmetAcquisitionEventListener;
    protected final VideoPresentation mPrimaryVideoPresentation;
    protected final ServerInsertedManifestTimelineManager mServerInsertedManifestTimelineManager;
    protected final boolean mShouldUnifyMediaEventReporters;
    protected final Object mStateChangeMutex;
    protected final TimelineMonitor mTimelineMonitor;
    protected final VolumeManager mVolumeManager;

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onTerminated() {
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void updateAdPlan(@Nonnull AdPlan adPlan) {
    }

    AdEnabledPlaybackStateMachine(AdPlaybackStateMachineContext adPlaybackStateMachineContext, TimelineMonitor timelineMonitor, ExecutorService executorService, AdPlanFactory adPlanFactory, AdBreakSelector adBreakSelector, VolumeManager volumeManager, @Nonnull AdvertisingIdCollector advertisingIdCollector, AdsConfig adsConfig, DiagnosticsOverlayToggler diagnosticsOverlayToggler, @Nonnull VideoPresentation videoPresentation, @Nonnull String str, @Nonnull AdEnabledVideoPlayer adEnabledVideoPlayer, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull AdEventTransport adEventTransport, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters, @Nonnull PmetAcquisitionEventListener pmetAcquisitionEventListener, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nullable ServerInsertedManifestTimelineManager serverInsertedManifestTimelineManager, boolean z) {
        super("AdEnabledPlayback");
        this.mStateChangeMutex = new Object();
        this.mIsAdPlayerPrepared = false;
        this.mContext = adPlaybackStateMachineContext;
        this.mTimelineMonitor = timelineMonitor;
        this.mExecutor = executorService;
        this.mPlanFactory = adPlanFactory;
        this.mBreakSelector = adBreakSelector;
        this.mVolumeManager = volumeManager;
        this.mAdvertisingIdCollector = (AdvertisingIdCollector) Preconditions.checkNotNull(advertisingIdCollector, "advertisingIdCollectorProvider");
        this.mConfig = adsConfig;
        this.mDiagnosticsToggler = diagnosticsOverlayToggler;
        this.mPrimaryVideoPresentation = (VideoPresentation) Preconditions.checkNotNull(videoPresentation, "primaryVideoPresentation");
        this.mOfferType = (String) Preconditions.checkNotNull(str, "offerType");
        this.mAdEnabledVideoPlayer = (AdEnabledVideoPlayer) Preconditions.checkNotNull(adEnabledVideoPlayer, "adEnabledVideoPlayer");
        this.mPlaybackEventTransport = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "playbackEventTransport");
        this.mAdEventTransport = (AdEventTransport) Preconditions.checkNotNull(adEventTransport, "adEventTransport");
        this.mAdExecutorService = createAdExecutor();
        this.mPlaybackMediaEventReporters = (PlaybackMediaEventReporters) Preconditions.checkNotNull(playbackMediaEventReporters, "playbackMediaEventReporters");
        this.mPmetAcquisitionEventListener = (PmetAcquisitionEventListener) Preconditions.checkNotNull(pmetAcquisitionEventListener, "pmetAcquisitionEventListener");
        this.mEventBus = (ContentManagementEventBus) Preconditions.checkNotNull(contentManagementEventBus, "contentManagementEventBus");
        this.mServerInsertedManifestTimelineManager = serverInsertedManifestTimelineManager;
        this.mShouldUnifyMediaEventReporters = z;
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void setPresentationEventListener(VideoPresentation videoPresentation, VideoPresentationEventListener videoPresentationEventListener) {
        this.mContext.getPresentationEventListener().setPresentation(videoPresentation);
        this.mContext.getPresentationEventListener().addConcreteListener(videoPresentationEventListener);
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
    public AdPlan getPlan() {
        return this.mContext.getPlan();
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public AdPlaybackStateMachineContext getContext() {
        return this.mContext;
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public void setRenderingSettings(VideoRenderingSettings videoRenderingSettings) {
        this.mContext.getAdTransitioner().initialize(videoRenderingSettings.getParentView());
        this.mContext.setRenderingSettings(videoRenderingSettings);
        this.mContext.notifyUserLaunchedPlayback();
    }

    protected ExecutorService createAdExecutor() {
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
        this.mPrimaryVideoPresentation.terminate(z);
        this.mContext.getUriProxy().cleanup();
        if (!this.mShouldUnifyMediaEventReporters) {
            this.mPlaybackMediaEventReporters.terminate();
        }
        this.mPmetAcquisitionEventListener.terminate();
        if (this.mConfig.shouldShutdownExecutor() && (executorService = this.mAdExecutorService) != null) {
            executorService.shutdown();
        }
        if (doTrigger(PlayerTriggers.SHUTDOWN)) {
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
        throw new UnsupportedOperationException("launchPlayback should not be called in the PLSM path.");
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onCompletion(VideoPresentation videoPresentation) {
        doTrigger(PlayerTriggers.COMPLETED);
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onError(VideoPresentation videoPresentation, MediaErrorCode mediaErrorCode) {
        AdManagerBasedAdClip adManagerBasedAdClipFindScheduledClip = findScheduledClip(videoPresentation);
        if (adManagerBasedAdClipFindScheduledClip != null) {
            doTrigger(new ClipErrorTrigger(adManagerBasedAdClipFindScheduledClip, AdError.PLAYBACK_ERROR, String.format(Locale.US, "Ad playback error %s occured", mediaErrorCode)));
        } else {
            doTrigger(new ErrorTrigger(mediaErrorCode));
        }
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onPrepared(VideoPresentation videoPresentation, PlaybackDataSource playbackDataSource) {
        if (videoPresentation == this.mPrimaryVideoPresentation) {
            this.mContext.setPrimaryPresentationPlaybackStatus(playbackDataSource);
        }
        AdManagerBasedAdClip adManagerBasedAdClipFindScheduledClip = findScheduledClip(videoPresentation);
        if (adManagerBasedAdClipFindScheduledClip != null) {
            adManagerBasedAdClipFindScheduledClip.onPrepared();
        }
        this.mIsAdPlayerPrepared = true;
        doTrigger(PlayerTriggers.PREPARED);
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onStarted(@Nonnull VideoPresentation videoPresentation, @Nonnull PlaybackDataSource playbackDataSource) {
        this.mContext.getPresentationEventListener().onStarted(this.mContext.getPrimaryPresentationPlaybackStatus());
    }

    private AdManagerBasedAdClip findScheduledClip(VideoPresentation videoPresentation) {
        Iterator<AdBreak> it = this.mContext.getPlan().getBreaks().iterator();
        while (it.hasNext()) {
            for (AdClip adClip : it.next().getClips()) {
                if (adClip.getPlayer() == videoPresentation.getPlayer()) {
                    DLog.logf("Found scheduled clip %s, isPrepared: %s", adClip, Boolean.valueOf(adClip.isPrepared()));
                    return (AdManagerBasedAdClip) CastUtils.castTo(adClip, AdManagerBasedAdClip.class);
                }
            }
        }
        return null;
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachine
    @Deprecated
    public void setRenderingSettingsOnPrimaryPlayer(@Nonnull VideoRenderingSettings videoRenderingSettings) {
        Preconditions.checkNotNull(videoRenderingSettings, "settings");
        this.mPrimaryVideoPresentation.setRenderingSettings(videoRenderingSettings);
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachine
    public void setCyclicReportingPaused(boolean z) {
        this.mPlaybackMediaEventReporters.setCyclicReportingPaused(z);
    }

    @Override // com.amazon.avod.media.ads.internal.AdPlaybackStateMachine
    @Nonnull
    @Deprecated
    public PlaybackEventReporter getPrimaryPlayerEventReporter() {
        PlaybackEventReporter reporter = this.mPrimaryVideoPresentation.getReporter();
        Preconditions.checkState(reporter != null, "Trying to use primary player event reporter before it's initialization.");
        return reporter;
    }

    @Override // com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager
    public boolean isAdPlayerPrepared() {
        return this.mIsAdPlayerPrepared;
    }
}
