package com.amazon.avod.playback.session.workflow;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionFactory;
import com.amazon.avod.content.LiveWindowDuration;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.event.AdEventTransport;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.internal.AdPlanFactory;
import com.amazon.avod.media.ads.internal.AdUriProxy;
import com.amazon.avod.media.contentcache.VideoCacheManager;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoPlayerLifecyleEventHandler;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.media.service.AVODContentUrlPolicyManager;
import com.amazon.avod.playback.LifecycleProfilerFactory;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.PlaybackRestartEvent;
import com.amazon.avod.playback.event.PlaybackLaunchedEvent;
import com.amazon.avod.playback.event.playback.AudioTrackChangeEvent;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.player.VideoPlaybackEngine;
import com.amazon.avod.playback.session.ConsumptionIdProvider;
import com.amazon.avod.playback.session.PlaybackSession;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionFailureManager;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.playback.smoothstream.SmoothStreamingBitrateChangeAdapter;
import com.amazon.avod.playback.smoothstream.SmoothStreamingContentEventAdapter;
import com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector;
import com.amazon.avod.playback.smoothstream.fsm.VideoPlaybackEngineFactory;
import com.amazon.avod.playback.subtitles.SubtitlesEngine;
import com.amazon.avod.qos.SmoothStreamingVideoPresentationEventReporter;
import com.amazon.avod.qos.metadata.EventSubtypes$Audio;
import com.amazon.avod.userdownload.PVDownloadPlayerShim;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class TaskSchedulerBasedPlaybackSession implements PlaybackSession {
    private final PlaybackSessionContext mContext;
    private final AtomicBoolean mIsTerminated;
    private final PlayerLifecycleWorkflow mPlayerLifecycleWorkflow;
    private final PlaybackSessionResources mResources;
    private final long mTerminateTimeoutMillis;

    public TaskSchedulerBasedPlaybackSession(@Nonnull PlayerLifecycleWorkflow playerLifecycleWorkflow, @Nonnull ExecutorService executorService, @Nonnull ContentSessionFactory contentSessionFactory, @Nonnull MediaProfiler mediaProfiler, @Nonnull VideoPlaybackEngineFactory videoPlaybackEngineFactory, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull DiagnosticDataCollector diagnosticDataCollector, @Nonnull SmoothStreamingBitrateChangeAdapter smoothStreamingBitrateChangeAdapter, @Nonnull SmoothStreamingContentEventAdapter smoothStreamingContentEventAdapter, @Nonnull AVODContentUrlPolicyManager aVODContentUrlPolicyManager, @Nonnull VideoCacheManager videoCacheManager, @Nonnull DownloadService downloadService, @Nonnull SmoothStreamingVideoPresentationEventReporter smoothStreamingVideoPresentationEventReporter, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nonnull BaseDrmSystem baseDrmSystem, @Nonnull RendererSchemeController rendererSchemeController, @Nonnull SubtitlesEngine subtitlesEngine, @Nonnull LiveWindowDuration liveWindowDuration, @Nonnull AdUriProxy adUriProxy, @Nonnull AdPlanFactory adPlanFactory, @Nonnull VideoPlayerLifecyleEventHandler videoPlayerLifecyleEventHandler, @Nonnull VideoOptions videoOptions, @Nonnull TimelineMonitor timelineMonitor, @Nonnull PlaybackMediaEventReporters.Factory factory, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull AdEventTransport adEventTransport, @Nonnull PlaybackSessionFailureManager playbackSessionFailureManager, @Nonnull PlaybackConfig playbackConfig) {
        this(playerLifecycleWorkflow, new DAGBasedPlaybackSessionContext(videoOptions, rendererSchemeController), new DAGBasedPlaybackSessionResources(executorService, contentSessionFactory, mediaProfiler, videoPlaybackEngineFactory, playbackEventTransport, diagnosticDataCollector, smoothStreamingBitrateChangeAdapter, smoothStreamingContentEventAdapter, aVODContentUrlPolicyManager, videoCacheManager, downloadService, smoothStreamingVideoPresentationEventReporter, playbackSupportEvaluator, baseDrmSystem, rendererSchemeController, subtitlesEngine, LifecycleProfilerFactory.createLifecycleProfiler(), liveWindowDuration, adUriProxy, adPlanFactory, videoPlayerLifecyleEventHandler, timelineMonitor, factory, contentManagementEventBus, adEventTransport, playbackSessionFailureManager, new ConsumptionIdProvider()), playbackConfig.getPlayerLifecycleStateMachineTerminateTimeout().getTotalMilliseconds());
    }

    TaskSchedulerBasedPlaybackSession(@Nonnull PlayerLifecycleWorkflow playerLifecycleWorkflow, @Nonnull PlaybackSessionContext playbackSessionContext, @Nonnull PlaybackSessionResources playbackSessionResources, long j) {
        this.mIsTerminated = new AtomicBoolean(false);
        this.mPlayerLifecycleWorkflow = (PlayerLifecycleWorkflow) Preconditions.checkNotNull(playerLifecycleWorkflow, "playerLifecycleWorkflow");
        this.mContext = (PlaybackSessionContext) Preconditions.checkNotNull(playbackSessionContext, "playbackSessionContext");
        this.mResources = (PlaybackSessionResources) Preconditions.checkNotNull(playbackSessionResources, "playbackSessionResources");
        this.mTerminateTimeoutMillis = j;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    public void prepare(@Nonnull VideoSpecification videoSpecification, @Nullable File file, @Nullable String str, @Nullable AudioVideoUrls audioVideoUrls) {
        if (this.mIsTerminated.get()) {
            DLog.warnf("Ignoring prepare call after Terminate for %s", this);
            return;
        }
        if (audioVideoUrls != null) {
            this.mContext.setAudioVideoUrls(audioVideoUrls);
        }
        this.mContext.setVideoSpec((VideoSpecification) Preconditions.checkNotNull(videoSpecification, "videoSpecification"));
        this.mContext.setStoragePath(file);
        this.mPlayerLifecycleWorkflow.preparePlaybackSession(this);
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    public void start(@Nonnull VideoRenderingSettings videoRenderingSettings) {
        if (this.mIsTerminated.get()) {
            DLog.warnf("Ignoring start call after Terminate for %s", this);
            return;
        }
        Preconditions.checkNotNull(videoRenderingSettings, "settings");
        VideoSpecification videoSpecification = (VideoSpecification) Preconditions.checkNotNull(this.mContext.getVideoSpec(), "videoSpecification");
        this.mContext.setOutputSettings(videoRenderingSettings);
        this.mContext.notifyPlaybackStarted();
        this.mResources.getPlaybackEventTransport().postEvent(new PlaybackLaunchedEvent(videoSpecification.getStartTime(), this.mContext.isPrimaryPlayerPrepared(), videoRenderingSettings.getSurfaceHandlingMode()));
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    public void restart(@Nonnull VideoSpecification videoSpecification, @Nonnull PlaybackRestartEvent playbackRestartEvent) {
        PlaybackMediaEventReporters mediaEventReportersMain;
        if (this.mIsTerminated.get()) {
            DLog.warnf("Ignoring restart call after Terminate for %s", this);
            return;
        }
        if (EventSubtypes$Audio.PLAYBACK_AUDIO_TRACK_CHANGE_TYPES.contains(playbackRestartEvent.getTypeString())) {
            if (AloysiusConfig.getInstance().shouldUnifyMediaEventReporters()) {
                mediaEventReportersMain = this.mResources.getMediaEventReporters();
            } else {
                mediaEventReportersMain = this.mResources.getMediaEventReportersMain();
            }
            mediaEventReportersMain.getAloysiusInteractionReporter().reportAudioTrackChange();
            this.mResources.getPlaybackEventTransport().postEvent(new AudioTrackChangeEvent(TimeSpan.ZERO, playbackRestartEvent.getLanguageCode(), getContentSession().getConsumptionId(), videoSpecification.getPrimaryAudioTrackId()));
        }
        if (playbackRestartEvent.getType() == 14) {
            this.mResources.getPlaybackSessionFailureManager().executeRecoveryAction(new PlaybackException(PlaybackException.PlaybackError.AUTO_EVAL_CANARY_FAILURE, playbackRestartEvent.getMessage()), this.mContext.getRendererSchemeType());
        }
        if (playbackRestartEvent.getType() == 15) {
            this.mResources.getPlaybackSessionFailureManager().executeRecoveryAction(new PlaybackException(PlaybackException.PlaybackError.AUTO_EVAL_POOR_PLAYER_PERFORMANCE, playbackRestartEvent.getMessage()), this.mContext.getRendererSchemeType());
        }
        this.mContext.setVideoSpec((VideoSpecification) Preconditions.checkNotNull(videoSpecification, "videoSpecification"));
        this.mPlayerLifecycleWorkflow.restartPlaybackSession(this, playbackRestartEvent);
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    public void terminate(boolean z, @Nullable MediaException mediaException) {
        if (!this.mIsTerminated.compareAndSet(false, true)) {
            DLog.warnf("Ignoring rogue terminate call for %s", this);
        } else {
            this.mPlayerLifecycleWorkflow.terminate(this, z, mediaException);
        }
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    @Nonnull
    public PlaybackSessionContext getContext() {
        return this.mContext;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    @Nonnull
    public PlaybackSessionResources getResources() {
        return this.mResources;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    @Nullable
    public VideoPlaybackEngine getPlaybackEngine() {
        return this.mContext.getPlaybackEngine();
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    @Nullable
    public PlaybackDataSource getDataSource() {
        return this.mContext.getDataSource();
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    @Nullable
    public ContentSession getContentSession() {
        return this.mContext.getContentSession();
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    @Nullable
    public VideoCacheManager getCacheManager() {
        return this.mResources.getCacheManager();
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    @Nullable
    public PVDownloadPlayerShim getDownload() {
        return this.mContext.getDownload();
    }
}
