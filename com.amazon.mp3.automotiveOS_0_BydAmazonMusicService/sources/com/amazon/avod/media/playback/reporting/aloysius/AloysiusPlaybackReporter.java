package com.amazon.avod.media.playback.reporting.aloysius;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.content.event.ContentEventFragmentDownloaded;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.event.FragmentInformationHolder;
import com.amazon.avod.content.smoothstream.manifest.acquisition.events.NewContentDownloadedEvent;
import com.amazon.avod.event.AdEventTransport;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdClipVideoPlayerSetEvent;
import com.amazon.avod.media.ads.AdClipVideoPlayerUnsetEvent;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.events.MediaEventProducer;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackStateTracker;
import com.amazon.avod.media.playback.support.Buffer;
import com.amazon.avod.media.playback.support.PlayerStatistics;
import com.amazon.avod.media.playback.support.RendererPerformanceData;
import com.amazon.avod.media.service.AVODServiceConfig;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playback.event.playback.AdPlaybackCompletedEvent;
import com.amazon.avod.playback.event.playback.AdPlaybackLoadedEvent;
import com.amazon.avod.playback.event.playback.AdPlaybackPausedEvent;
import com.amazon.avod.playback.event.playback.AdPlaybackPlayingEvent;
import com.amazon.avod.playback.event.playback.AdPlaybackStartedEvent;
import com.amazon.avod.playback.event.playback.BufferStartEvent;
import com.amazon.avod.playback.event.playback.PauseEvent;
import com.amazon.avod.playback.event.playback.PlaybackLoadedEvent;
import com.amazon.avod.playback.event.playback.PlaybackStartEvent;
import com.amazon.avod.playback.event.playback.ResumeEvent;
import com.amazon.avod.playback.event.playback.SeekStartEvent;
import com.amazon.avod.playback.session.ConsumptionIdProvider;
import com.amazon.avod.playback.sye.events.SyePlaybackStartEvent;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.InitializationLatch;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusPlaybackReporter implements AloysiusPlaybackReporterInterface, MediaEventProducer {

    @Nullable
    private final AdEventTransport mAdEventTransport;
    private int mAdTotalDroppedFrames;
    protected AloysiusPlaybackStateTracker mAloysiusPlaybackStateTracker;
    private String mClientId;

    @Nonnull
    protected final ConsumptionIdProvider mConsumptionIdProvider;

    @Nullable
    private VideoPlayer mCurrentAdVideoPlayer;
    private final ContentManagementEventBus mEventBus;
    private final PlaybackEventTransport mEventTransport;

    @Nonnull
    private InitializationLatch mInitializationLatch;
    private final boolean mIsAdsAloysiusStateTransitionEnabled;
    private boolean mIsLive;

    @Nonnull
    protected final MediaEventQueue mMediaEventQueue;

    @Nullable
    private PlaybackExperienceController mPlaybackExperienceController;
    protected boolean mPlaybackStarted;
    private int mPrimaryTotalDroppedFrames;
    private final boolean mShouldReportPlayheadInLive;

    @Nullable
    private VideoPlayer mVideoPlayer;

    public AloysiusPlaybackReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nullable ContentManagementEventBus contentManagementEventBus, @Nullable PlaybackEventTransport playbackEventTransport, @Nullable AdEventTransport adEventTransport, @Nonnull ConsumptionIdProvider consumptionIdProvider, @Nonnull AloysiusPlaybackStateTracker aloysiusPlaybackStateTracker) {
        this(mediaEventQueue, contentManagementEventBus, playbackEventTransport, adEventTransport, consumptionIdProvider, false, aloysiusPlaybackStateTracker, AdsConfig.getInstance(), AVODServiceConfig.getInstance());
    }

    public AloysiusPlaybackReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nullable ContentManagementEventBus contentManagementEventBus, @Nullable PlaybackEventTransport playbackEventTransport, @Nullable AdEventTransport adEventTransport, @Nonnull ConsumptionIdProvider consumptionIdProvider, boolean z, @Nonnull AloysiusPlaybackStateTracker aloysiusPlaybackStateTracker, @Nonnull AdsConfig adsConfig, @Nonnull AVODServiceConfig aVODServiceConfig) {
        this.mInitializationLatch = new InitializationLatch(this);
        this.mPrimaryTotalDroppedFrames = 0;
        this.mAdTotalDroppedFrames = 0;
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
        this.mConsumptionIdProvider = (ConsumptionIdProvider) Preconditions.checkNotNull(consumptionIdProvider, "consumptionIdProvider");
        this.mEventBus = contentManagementEventBus;
        this.mEventTransport = playbackEventTransport;
        this.mAdEventTransport = adEventTransport;
        this.mPlaybackStarted = z;
        this.mAloysiusPlaybackStateTracker = (AloysiusPlaybackStateTracker) Preconditions.checkNotNull(aloysiusPlaybackStateTracker, "stateTracker");
        this.mIsAdsAloysiusStateTransitionEnabled = ((AdsConfig) Preconditions.checkNotNull(adsConfig, "adsConfig")).isAdsAloysiusStateTransitionEnabled();
        this.mShouldReportPlayheadInLive = ((AVODServiceConfig) Preconditions.checkNotNull(aVODServiceConfig)).shouldReportPlayheadInLive();
    }

    public void initializeVideoPlayback(@Nonnull VideoPlayer videoPlayer, @Nullable PlaybackExperienceController playbackExperienceController, boolean z) {
        Preconditions.checkState((playbackExperienceController == null && z) ? false : true, "playbackExperienceController must be nonnull for live streams");
        this.mInitializationLatch.start(30L, TimeUnit.SECONDS);
        this.mVideoPlayer = (VideoPlayer) Preconditions.checkNotNull(videoPlayer, "videoPlayer");
        this.mPlaybackExperienceController = playbackExperienceController;
        this.mIsLive = z;
        this.mInitializationLatch.complete();
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public synchronized void initialize() {
        ContentManagementEventBus contentManagementEventBus = this.mEventBus;
        if (contentManagementEventBus != null) {
            contentManagementEventBus.registerEventBusHandler(this);
        }
        PlaybackEventTransport playbackEventTransport = this.mEventTransport;
        if (playbackEventTransport != null) {
            playbackEventTransport.registerEventBusHandler(this);
        }
        AdEventTransport adEventTransport = this.mAdEventTransport;
        if (adEventTransport != null) {
            adEventTransport.registerEventBusHandler(this);
        }
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public synchronized void terminate() {
        ContentManagementEventBus contentManagementEventBus = this.mEventBus;
        if (contentManagementEventBus != null) {
            contentManagementEventBus.unregisterEventBusHandler(this);
        }
        PlaybackEventTransport playbackEventTransport = this.mEventTransport;
        if (playbackEventTransport != null) {
            playbackEventTransport.unregisterEventBusHandler(this);
        }
        AdEventTransport adEventTransport = this.mAdEventTransport;
        if (adEventTransport != null) {
            adEventTransport.unregisterEventBusHandler(this);
        }
    }

    @Override // com.amazon.avod.media.events.MediaEventProducer
    public synchronized void produce() {
        if (this.mInitializationLatch.isInitialized()) {
            if (this.mCurrentAdVideoPlayer != null && this.mAloysiusPlaybackStateTracker.getPlayerCurrentState() == AloysiusPlaybackStateTracker.PlaybackState.AdPlaying) {
                this.mMediaEventQueue.add(createMediaEvent(PlaybackMediaEvent.Action.Update, PlaybackMediaEvent.ContentContext.AdBreak, null));
            } else if (this.mPlaybackStarted && this.mConsumptionIdProvider.getConsumptionId() != null && this.mAloysiusPlaybackStateTracker.getPlayerCurrentState() == AloysiusPlaybackStateTracker.PlaybackState.MainPlaying) {
                this.mMediaEventQueue.add(createMediaEvent(PlaybackMediaEvent.Action.Update, PlaybackMediaEvent.ContentContext.Feature, null));
            }
        }
    }

    public void setClientId(@Nonnull String str) {
        this.mClientId = (String) Preconditions.checkNotNull(str, "clientId");
    }

    public void updateConsumptionId(@Nonnull String str) {
        this.mConsumptionIdProvider.setConsumptionId(str);
    }

    @Subscribe
    public synchronized void handleNewContentDownloadedEvent(NewContentDownloadedEvent newContentDownloadedEvent) {
        this.mInitializationLatch.checkInitialized();
        if (newContentDownloadedEvent.getContentUrl() != null) {
            this.mConsumptionIdProvider.setConsumptionId(newContentDownloadedEvent.getContentUrl().getSessionId());
        }
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporterInterface
    public synchronized void handleSeekStart(SeekStartEvent seekStartEvent) {
        this.mInitializationLatch.checkInitialized();
        AloysiusPlaybackStateTracker.PlaybackState playerCurrentState = this.mAloysiusPlaybackStateTracker.getPlayerCurrentState();
        this.mAloysiusPlaybackStateTracker.trigger(AloysiusPlaybackStateTracker.ActionType.MainPause);
        if (playerCurrentState == AloysiusPlaybackStateTracker.PlaybackState.MainPlaying) {
            this.mMediaEventQueue.add(createMediaEvent(PlaybackMediaEvent.Action.Stop, PlaybackMediaEvent.ContentContext.Feature, seekStartEvent.getEventTimeStamp()));
        }
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporterInterface
    public synchronized void handleAdPlaybackStartedEvent(AdPlaybackStartedEvent adPlaybackStartedEvent) {
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporterInterface
    public synchronized void handleResumeEvent(ResumeEvent resumeEvent) {
        this.mInitializationLatch.checkInitialized();
        DLog.devf("PlaybackEvent : ResumeEvent received");
        AloysiusPlaybackStateTracker.PlaybackState playerCurrentState = this.mAloysiusPlaybackStateTracker.getPlayerCurrentState();
        this.mAloysiusPlaybackStateTracker.trigger(AloysiusPlaybackStateTracker.ActionType.MainResume);
        if (playerCurrentState == AloysiusPlaybackStateTracker.PlaybackState.AdPlaying || playerCurrentState == AloysiusPlaybackStateTracker.PlaybackState.AdPaused) {
            this.mMediaEventQueue.add(createMediaEvent(PlaybackMediaEvent.Action.Context, PlaybackMediaEvent.ContentContext.Feature, resumeEvent.getEventTimeStamp()));
            DLog.devf("PlaybackEvent : ResumeEvent sending context switch to main from ad");
        }
        if (playerCurrentState != AloysiusPlaybackStateTracker.PlaybackState.MainPlaying) {
            this.mMediaEventQueue.add(createMediaEvent(PlaybackMediaEvent.Action.Start, PlaybackMediaEvent.ContentContext.Feature, resumeEvent.getEventTimeStamp()));
            DLog.devf("PlaybackEvent : ResumeEvent sending main player start event");
        }
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporterInterface
    public synchronized void handlePlaybackStartEvent(PlaybackStartEvent playbackStartEvent) {
        this.mInitializationLatch.checkInitialized();
        this.mPlaybackStarted = true;
        AloysiusPlaybackStateTracker.PlaybackState playerCurrentState = this.mAloysiusPlaybackStateTracker.getPlayerCurrentState();
        if (this.mAloysiusPlaybackStateTracker.trigger(AloysiusPlaybackStateTracker.ActionType.MainStart) == AloysiusPlaybackStateTracker.TransitioningTo.MainPlayer && (playerCurrentState == AloysiusPlaybackStateTracker.PlaybackState.AdPlaying || playerCurrentState == AloysiusPlaybackStateTracker.PlaybackState.AdPaused)) {
            this.mMediaEventQueue.add(createMediaEvent(PlaybackMediaEvent.Action.Context, PlaybackMediaEvent.ContentContext.Feature, playbackStartEvent.getEventTimeStamp()));
            DLog.devf("PlaybackEvent : sending switch event during switch");
        }
        if (playerCurrentState != AloysiusPlaybackStateTracker.PlaybackState.MainPlaying) {
            DLog.devf("PlaybackEvent : Start sending Main PlaybackStartEvent");
            this.mMediaEventQueue.add(createMediaEvent(PlaybackMediaEvent.Action.Start, PlaybackMediaEvent.ContentContext.Feature, playbackStartEvent.getEventTimeStamp()));
        }
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporterInterface
    public synchronized void handlePlaybackPlayingEvent(AdPlaybackPlayingEvent adPlaybackPlayingEvent) {
        this.mInitializationLatch.checkInitialized();
        AloysiusPlaybackStateTracker.PlaybackState playerCurrentState = this.mAloysiusPlaybackStateTracker.getPlayerCurrentState();
        if (this.mAloysiusPlaybackStateTracker.trigger(AloysiusPlaybackStateTracker.ActionType.AdBegin) == AloysiusPlaybackStateTracker.TransitioningTo.AdPlayer && (playerCurrentState == AloysiusPlaybackStateTracker.PlaybackState.MainPlaying || playerCurrentState == AloysiusPlaybackStateTracker.PlaybackState.MainPaused)) {
            this.mMediaEventQueue.add(createMediaEvent(PlaybackMediaEvent.Action.Context, PlaybackMediaEvent.ContentContext.AdBreak, null));
            DLog.devf("PlaybackEvent : sending context switch event for ad begin");
        }
        if (this.mIsAdsAloysiusStateTransitionEnabled && this.mAloysiusPlaybackStateTracker.getPlayerCurrentState() == AloysiusPlaybackStateTracker.PlaybackState.AdPaused) {
            this.mAloysiusPlaybackStateTracker.trigger(AloysiusPlaybackStateTracker.ActionType.AdResume);
        }
        this.mMediaEventQueue.add(createMediaEvent(PlaybackMediaEvent.Action.Start, PlaybackMediaEvent.ContentContext.AdBreak, null));
        DLog.devf("PlaybackEvent : Play sending Ads PlaybackStartEvent");
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporterInterface
    public synchronized void handlePlaybackPausedEvent(AdPlaybackPausedEvent adPlaybackPausedEvent) {
        this.mInitializationLatch.checkInitialized();
        AloysiusPlaybackStateTracker.PlaybackState playerCurrentState = this.mAloysiusPlaybackStateTracker.getPlayerCurrentState();
        if (!this.mIsAdsAloysiusStateTransitionEnabled || (this.mAloysiusPlaybackStateTracker.trigger(AloysiusPlaybackStateTracker.ActionType.AdPause) == AloysiusPlaybackStateTracker.TransitioningTo.AdPlayer && playerCurrentState == AloysiusPlaybackStateTracker.PlaybackState.AdPlaying)) {
            this.mMediaEventQueue.add(createMediaEvent(PlaybackMediaEvent.Action.Stop, PlaybackMediaEvent.ContentContext.AdBreak, null));
            DLog.devf("PlaybackEvent : Pause sending Ads PlaybackStopEvent");
        }
    }

    public synchronized void handlePlaybackCompletedEvent(AdPlaybackCompletedEvent adPlaybackCompletedEvent) {
        this.mInitializationLatch.checkInitialized();
        AloysiusPlaybackStateTracker.PlaybackState playerCurrentState = this.mAloysiusPlaybackStateTracker.getPlayerCurrentState();
        if (!this.mIsAdsAloysiusStateTransitionEnabled || (this.mAloysiusPlaybackStateTracker.trigger(AloysiusPlaybackStateTracker.ActionType.AdPause) == AloysiusPlaybackStateTracker.TransitioningTo.AdPlayer && playerCurrentState == AloysiusPlaybackStateTracker.PlaybackState.AdPlaying)) {
            this.mMediaEventQueue.add(createMediaEvent(PlaybackMediaEvent.Action.Stop, PlaybackMediaEvent.ContentContext.AdBreak, null));
            DLog.devf("PlaybackEvent : Complete sending Ads PlaybackStopEvent");
        }
    }

    public synchronized void reportPlaybackExit(@Nullable TimeSpan timeSpan) {
        this.mInitializationLatch.checkInitialized();
        this.mPlaybackStarted = false;
        AloysiusPlaybackStateTracker.PlaybackState playerCurrentState = this.mAloysiusPlaybackStateTracker.getPlayerCurrentState();
        this.mAloysiusPlaybackStateTracker.trigger(AloysiusPlaybackStateTracker.ActionType.MainStop);
        if (playerCurrentState != AloysiusPlaybackStateTracker.PlaybackState.Uninitialized) {
            this.mMediaEventQueue.add(createMediaEvent(PlaybackMediaEvent.Action.Exit, PlaybackMediaEvent.ContentContext.Feature, timeSpan, null, null));
            DLog.devf("PlaybackEvent : sending exit for Main");
        }
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporterInterface
    public synchronized void handlePauseEvent(PauseEvent pauseEvent) {
        this.mInitializationLatch.checkInitialized();
        if (this.mAloysiusPlaybackStateTracker.getPlayerCurrentState() == AloysiusPlaybackStateTracker.PlaybackState.MainPlaying) {
            this.mAloysiusPlaybackStateTracker.trigger(AloysiusPlaybackStateTracker.ActionType.MainPause);
            this.mMediaEventQueue.add(createMediaEvent(PlaybackMediaEvent.Action.Stop, PlaybackMediaEvent.ContentContext.Feature, pauseEvent.getEventTimeStamp()));
            DLog.devf("PlaybackEvent : PauseEvent sending stop for Main");
        }
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporterInterface
    public synchronized void handleBufferStartEvent(BufferStartEvent bufferStartEvent) {
        this.mInitializationLatch.checkInitialized();
        this.mAloysiusPlaybackStateTracker.trigger(AloysiusPlaybackStateTracker.ActionType.MainPause);
        this.mMediaEventQueue.add(createMediaEvent(PlaybackMediaEvent.Action.Stop, PlaybackMediaEvent.ContentContext.Feature, bufferStartEvent.getEventTimeStamp()));
        DLog.devf("PlaybackEvent : BufferStartEvent sending stop for Main");
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporterInterface
    public synchronized void handlePlaybackLoadedEvent(PlaybackLoadedEvent playbackLoadedEvent) {
        this.mInitializationLatch.checkInitialized();
        this.mConsumptionIdProvider.setConsumptionId(playbackLoadedEvent.getConsumptionId());
        PlaybackMediaEvent.Action action = PlaybackMediaEvent.Action.ReadyToWatch;
        ConsumptionIdProvider consumptionIdProvider = this.mConsumptionIdProvider;
        if (consumptionIdProvider.hasLoggedEventForId(consumptionIdProvider.getConsumptionId(), action.name())) {
            return;
        }
        this.mMediaEventQueue.add(createMediaEvent(action, PlaybackMediaEvent.ContentContext.Feature, playbackLoadedEvent.getEventTimeStamp()));
    }

    @Subscribe
    public synchronized void handleAdClipVideoPlayerSet(AdClipVideoPlayerSetEvent adClipVideoPlayerSetEvent) {
        this.mInitializationLatch.checkInitialized();
        this.mCurrentAdVideoPlayer = (VideoPlayer) adClipVideoPlayerSetEvent.getVideoPlayer();
    }

    @Subscribe
    public synchronized void handleAdClipVideoPlayerUnset(AdClipVideoPlayerUnsetEvent adClipVideoPlayerUnsetEvent) {
        this.mInitializationLatch.checkInitialized();
        if (this.mCurrentAdVideoPlayer == adClipVideoPlayerUnsetEvent.getVideoPlayer() && this.mCurrentAdVideoPlayer != null) {
            this.mCurrentAdVideoPlayer = null;
        }
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporterInterface
    public synchronized void handlePlaybackLoadedEvent(AdPlaybackLoadedEvent adPlaybackLoadedEvent) {
        this.mInitializationLatch.checkInitialized();
        PlaybackMediaEvent.Action action = PlaybackMediaEvent.Action.ReadyToWatch;
        ConsumptionIdProvider consumptionIdProvider = this.mConsumptionIdProvider;
        if (consumptionIdProvider.hasLoggedEventForId(consumptionIdProvider.getConsumptionId(), action.name())) {
            return;
        }
        this.mMediaEventQueue.add(createMediaEvent(action, PlaybackMediaEvent.ContentContext.AdBreak, null));
    }

    @Subscribe
    public synchronized void handleFragmentDownloadedEvent(ContentEventFragmentDownloaded contentEventFragmentDownloaded) {
        this.mInitializationLatch.checkInitialized();
        FragmentInformationHolder fragmentInformationHolder = contentEventFragmentDownloaded.getFragmentInformationHolder();
        if (fragmentInformationHolder.isVideo() || fragmentInformationHolder.isAudio()) {
            this.mConsumptionIdProvider.setConsumptionId(fragmentInformationHolder.getContentUrl().getSessionId());
        }
    }

    public synchronized void handleSyePlaybackStartEvent(SyePlaybackStartEvent syePlaybackStartEvent) {
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporterInterface
    public synchronized String getAppInstanceId() {
        return this.mMediaEventQueue.getAppInstanceId();
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporterInterface
    public synchronized void handlePlaybackStopEvent(@Nullable TimeSpan timeSpan) {
        this.mInitializationLatch.checkInitialized();
        AloysiusPlaybackStateTracker.PlaybackState playerCurrentState = this.mAloysiusPlaybackStateTracker.getPlayerCurrentState();
        if (playerCurrentState == AloysiusPlaybackStateTracker.PlaybackState.MainPlaying) {
            this.mAloysiusPlaybackStateTracker.trigger(AloysiusPlaybackStateTracker.ActionType.MainPause);
            this.mMediaEventQueue.add(createMediaEvent(PlaybackMediaEvent.Action.Stop, PlaybackMediaEvent.ContentContext.Feature, timeSpan));
            DLog.devf("PlaybackEvent : PlaybackStopEvent sending stop for Main");
        } else if (playerCurrentState == AloysiusPlaybackStateTracker.PlaybackState.AdPlaying) {
            this.mAloysiusPlaybackStateTracker.trigger(AloysiusPlaybackStateTracker.ActionType.AdPause);
            this.mMediaEventQueue.add(createMediaEvent(PlaybackMediaEvent.Action.Stop, PlaybackMediaEvent.ContentContext.AdBreak, timeSpan));
            DLog.devf("PlaybackEvent : PlaybackStopEvent sending stop for Ads");
        }
    }

    @VisibleForTesting
    protected PlaybackMediaEvent createMediaEvent(PlaybackMediaEvent.Action action, PlaybackMediaEvent.ContentContext contentContext, @Nullable TimeSpan timeSpan, @Nullable Long l, @Nullable Long l2) {
        PlayerStatistics playerStatistics;
        VideoPlayer videoPlayer;
        PlayerStatistics playerStatistics2;
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(this.mVideoPlayer, "mVideoPlayer");
        PlaybackMediaEvent.Builder builder = new PlaybackMediaEvent.Builder();
        DLog.devf("PlaybackMediaEvent Action: %s, Context: %s", action.name(), contentContext.name());
        if (this.mIsLive) {
            builder.livehead(l2);
        }
        ConsumptionIdProvider consumptionIdProvider = this.mConsumptionIdProvider;
        consumptionIdProvider.eventLoggedForId(consumptionIdProvider.getConsumptionId(), action.name());
        builder.action(action);
        builder.playhead(l);
        builder.consumptionId(this.mConsumptionIdProvider.getConsumptionId());
        builder.contentContext(contentContext);
        String str = this.mClientId;
        if (str != null) {
            builder.clientId(str);
        }
        if (PlaybackMediaEvent.ContentContext.AdBreak.equals(contentContext) && (videoPlayer = this.mCurrentAdVideoPlayer) != null) {
            try {
                playerStatistics2 = videoPlayer.getPlayerStatistics();
            } catch (IllegalStateException | NullPointerException e) {
                DLog.exceptionf(e, "Exception caught when creating Aloysius playback media event.", new Object[0]);
                playerStatistics2 = PlayerStatistics.EMPTY_PLAYER_STATS;
            }
            RendererPerformanceData rendererPerformanceData = playerStatistics2.getRendererPerformanceData();
            int totalRenderDropNum = rendererPerformanceData.getTotalRenderDropNum();
            int i = this.mAdTotalDroppedFrames;
            if (totalRenderDropNum < i) {
                builder.droppedFrames(Math.max(0, i));
            } else {
                builder.droppedFrames(Math.max(0, totalRenderDropNum - i));
            }
            this.mAdTotalDroppedFrames = Math.max(0, totalRenderDropNum);
            builder.averageFrameRate(rendererPerformanceData.getAverageFramesPerSecond());
            builder.frontBuffer(playerStatistics2.getFrontBuffers());
            builder.backBuffer(playerStatistics2.getBackBuffer());
            builder.rendererBuffer(rendererPerformanceData.getRendererBuffers());
        } else {
            try {
                playerStatistics = this.mVideoPlayer.getPlayerStatistics();
            } catch (IllegalStateException | NullPointerException e2) {
                DLog.exceptionf(e2, "Exception caught when creating Aloysius playback media event.", new Object[0]);
                playerStatistics = PlayerStatistics.EMPTY_PLAYER_STATS;
            }
            RendererPerformanceData rendererPerformanceData2 = playerStatistics.getRendererPerformanceData();
            int totalRenderDropNum2 = rendererPerformanceData2.getTotalRenderDropNum();
            builder.droppedFrames(Math.max(0, totalRenderDropNum2 - this.mPrimaryTotalDroppedFrames));
            this.mPrimaryTotalDroppedFrames = Math.max(0, totalRenderDropNum2);
            builder.averageFrameRate(rendererPerformanceData2.getAverageFramesPerSecond());
            builder.frontBuffer(playerStatistics.getFrontBuffers());
            builder.backBuffer(playerStatistics.getBackBuffer());
            builder.rendererBuffer(rendererPerformanceData2.getRendererBuffers());
        }
        return builder.build();
    }

    @VisibleForTesting
    protected PlaybackMediaEvent createMediaEvent(PlaybackMediaEvent.Action action, PlaybackMediaEvent.ContentContext contentContext, @Nullable TimeSpan timeSpan) {
        Long lValueOf;
        Long l;
        Long lValueOf2;
        this.mInitializationLatch.checkInitialized();
        DLog.devf("PlaybackMediaEvent Action: %s, Context: %s", action.name(), contentContext.name());
        if (this.mIsLive) {
            Preconditions.checkNotNull(this.mPlaybackExperienceController, "mPlaybackExperienceController");
            Long lValueOf3 = this.mShouldReportPlayheadInLive ? Long.valueOf(getPlayheadFromPlayerInMillis(contentContext)) : null;
            lValueOf2 = Long.valueOf(this.mPlaybackExperienceController.getLiveTimeWindowEndMillis());
            l = lValueOf3;
        } else {
            if (timeSpan != null) {
                lValueOf = Long.valueOf(timeSpan.getTotalMilliseconds());
            } else {
                lValueOf = Long.valueOf(getPlayheadFromPlayerInMillis(contentContext));
            }
            l = lValueOf;
            lValueOf2 = null;
        }
        return createMediaEvent(action, contentContext, timeSpan, l, lValueOf2);
    }

    private long getPlayheadFromPlayerInMillis(PlaybackMediaEvent.ContentContext contentContext) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(this.mVideoPlayer, "mVideoPlayer");
        if (AnonymousClass1.$SwitchMap$com$amazon$avod$media$playback$reporting$aloysius$AloysiusPlaybackReporter$PlaybackMediaEvent$ContentContext[contentContext.ordinal()] != 2) {
            if (this.mIsLive) {
                return this.mVideoPlayer.getCurrentPositionUTC();
            }
            return this.mVideoPlayer.getCurrentPosition();
        }
        VideoPlayer videoPlayer = this.mCurrentAdVideoPlayer;
        if (videoPlayer != null) {
            return videoPlayer.getCurrentPosition();
        }
        return 0L;
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$playback$reporting$aloysius$AloysiusPlaybackReporter$PlaybackMediaEvent$ContentContext;

        static {
            int[] iArr = new int[PlaybackMediaEvent.ContentContext.values().length];
            $SwitchMap$com$amazon$avod$media$playback$reporting$aloysius$AloysiusPlaybackReporter$PlaybackMediaEvent$ContentContext = iArr;
            try {
                iArr[PlaybackMediaEvent.ContentContext.Feature.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$reporting$aloysius$AloysiusPlaybackReporter$PlaybackMediaEvent$ContentContext[PlaybackMediaEvent.ContentContext.AdBreak.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @VisibleForTesting
    protected static class PlaybackMediaEvent implements MediaEvent {
        private final Action mAction;
        private final double mAverageFrameRate;
        private final Buffers mBuffers;
        private final String mClientId;
        private final String mConsumptionId;
        private final ContentContext mContentContext;
        private final int mDroppedFrames;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private final Long mLivehead;
        private final Long mPlayhead;

        enum Action {
            ReadyToWatch,
            Open,
            Start,
            Stop,
            Context,
            Update,
            Exit
        }

        enum ContentContext {
            Feature,
            AdBreak
        }

        /* synthetic */ PlaybackMediaEvent(Builder builder, AnonymousClass1 anonymousClass1) {
            this(builder);
        }

        private PlaybackMediaEvent(@Nonnull Builder builder) {
            this.mAction = ((Builder) Preconditions.checkNotNull(builder, "builder")).mAction;
            this.mPlayhead = builder.mPlayhead;
            this.mLivehead = builder.mLivehead;
            this.mConsumptionId = builder.mConsumptionId;
            this.mContentContext = builder.mContentContext;
            this.mBuffers = new Buffers(builder.mFrontBuffers, builder.mBackBuffers, builder.mRendererBuffers, null);
            this.mDroppedFrames = builder.mDroppedFrames;
            this.mAverageFrameRate = builder.mAverageFrameRate;
            this.mClientId = builder.mClientId;
        }

        @VisibleForTesting
        static class Buffers {
            private final List<Buffer> mBack;
            private final List<Buffer> mFront;
            private final List<Buffer> mRenderer;

            /* synthetic */ Buffers(List list, List list2, List list3, AnonymousClass1 anonymousClass1) {
                this(list, list2, list3);
            }

            private Buffers(@Nonnull List<Buffer> list, @Nonnull List<Buffer> list2, @Nonnull List<Buffer> list3) {
                this.mFront = (List) Preconditions.checkNotNull(list, "front");
                this.mBack = (List) Preconditions.checkNotNull(list2, "back");
                this.mRenderer = (List) Preconditions.checkNotNull(list3, "renderer");
            }

            @Nonnull
            public List<Buffer> getFront() {
                return this.mFront;
            }

            @Nullable
            public List<Buffer> getBack() {
                return this.mBack;
            }

            @Nonnull
            public List<Buffer> getRenderer() {
                return this.mRenderer;
            }
        }

        @Nullable
        public ContentContext getContentContext() {
            return this.mContentContext;
        }

        @Nonnull
        public Action getAction() {
            return this.mAction;
        }

        @Nullable
        public Long getPlayhead() {
            return this.mPlayhead;
        }

        @Nullable
        public Long getLivehead() {
            return this.mLivehead;
        }

        @Nullable
        public String getConsumptionId() {
            return this.mConsumptionId;
        }

        @Nonnull
        public Buffers getBuffers() {
            return this.mBuffers;
        }

        public int getDroppedFrames() {
            return this.mDroppedFrames;
        }

        public double getAverageFrameRate() {
            return this.mAverageFrameRate;
        }

        @Nullable
        public String getClientId() {
            return this.mClientId;
        }

        @Override // com.amazon.avod.media.events.model.MediaEvent
        public MediaEvent.MediaEventType getMediaEventType() {
            return MediaEvent.MediaEventType.Playback;
        }

        protected static class Builder {
            private Action mAction;
            private double mAverageFrameRate;
            private List<Buffer> mBackBuffers;
            private String mClientId;
            private String mConsumptionId;
            private ContentContext mContentContext;
            private int mDroppedFrames;
            private List<Buffer> mFrontBuffers;
            private Long mLivehead;
            private Long mPlayhead;
            private List<Buffer> mRendererBuffers;

            protected Builder() {
            }

            public Builder action(@Nonnull Action action) {
                this.mAction = (Action) Preconditions.checkNotNull(action, "action type");
                return this;
            }

            public Builder contentContext(@Nonnull ContentContext contentContext) {
                this.mContentContext = (ContentContext) Preconditions.checkNotNull(contentContext, "contentContext");
                return this;
            }

            public Builder playhead(@Nonnegative @Nullable Long l) {
                if (l != null) {
                    Preconditions.checkArgument(l.longValue() >= 0, "playhead must be non negative");
                }
                this.mPlayhead = l;
                return this;
            }

            public Builder livehead(@Nonnegative @Nullable Long l) {
                if (l != null) {
                    Preconditions.checkArgument(l.longValue() >= 0, "livehead must be non negative");
                }
                this.mLivehead = l;
                return this;
            }

            public Builder consumptionId(@Nullable String str) {
                this.mConsumptionId = str;
                return this;
            }

            protected Builder frontBuffer(@Nonnull List<Buffer> list) {
                this.mFrontBuffers = (List) Preconditions.checkNotNull(list, "front buffers");
                return this;
            }

            protected Builder backBuffer(@Nonnull List<Buffer> list) {
                this.mBackBuffers = (List) Preconditions.checkNotNull(list, "back buffers");
                return this;
            }

            protected Builder rendererBuffer(@Nonnull List<Buffer> list) {
                this.mRendererBuffers = (List) Preconditions.checkNotNull(list, "renderer buffers");
                return this;
            }

            public Builder droppedFrames(@Nonnegative int i) {
                Preconditions.checkArgument(i >= 0, "droppedFrames must be non negative");
                this.mDroppedFrames = i;
                return this;
            }

            public Builder averageFrameRate(@Nonnegative double d) {
                Preconditions.checkArgument(d >= 0.0d, "averageFrameRate must be non negative");
                this.mAverageFrameRate = d;
                return this;
            }

            public Builder clientId(@Nullable String str) {
                this.mClientId = str;
                return this;
            }

            public PlaybackMediaEvent build() {
                return new PlaybackMediaEvent(this, null);
            }
        }
    }
}
