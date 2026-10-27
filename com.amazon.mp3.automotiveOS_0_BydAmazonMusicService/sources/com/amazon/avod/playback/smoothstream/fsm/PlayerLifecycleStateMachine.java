package com.amazon.avod.playback.smoothstream.fsm;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionFactory;
import com.amazon.avod.content.LiveWindowDuration;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.event.AdEventTransport;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.fsm.BlockingStateMachine;
import com.amazon.avod.fsm.StateBuilder;
import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.contentcache.VideoCacheManager;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.support.ConsumptionType;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.media.service.AVODContentUrlPolicyManager;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.LifecycleProfilerFactory;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.PlaybackRestartEvent;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.event.playback.AudioTrackChangeEvent;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.player.VideoPlaybackEngine;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.playback.session.ConsumptionIdProvider;
import com.amazon.avod.playback.session.PlaybackSession;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionFailureManager;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.playback.smoothstream.SmoothStreamingBitrateChangeAdapter;
import com.amazon.avod.playback.smoothstream.SmoothStreamingContentEventAdapter;
import com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector;
import com.amazon.avod.playback.smoothstream.fsm.PlayerLifecycleState;
import com.amazon.avod.playback.smoothstream.fsm.Triggers;
import com.amazon.avod.playback.subtitles.SubtitlesEngine;
import com.amazon.avod.pmet.WhisperCacheMetrics$CacheEviction;
import com.amazon.avod.qos.SmoothStreamingVideoPresentationEventReporter;
import com.amazon.avod.qos.metadata.EventSubtypes$Audio;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.userdownload.PVDownloadPlayerShim;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@SuppressFBWarnings(justification = "Findbugs don't want us to use synchronized with executor. But the implementation is correct, then we ignored.", value = {"JLM_JSR166_UTILCONCURRENT_MONITORENTER"})
@Deprecated
public class PlayerLifecycleStateMachine extends BlockingStateMachine<PlayerLifecycleState.Type, Triggers.Type> implements PlaybackSession {
    private final ExecutorService mExecutor;
    private final PlaybackSessionContext mPlaybackSessionContext;
    private final PlaybackSessionResources mPlaybackSessionResources;
    private File mStoragePath;
    private final long mTerminateTimeoutMillis;

    public PlayerLifecycleStateMachine(ExecutorService executorService, ContentSessionFactory contentSessionFactory, MediaProfiler mediaProfiler, VideoPlaybackEngineFactory videoPlaybackEngineFactory, PlaybackEventTransport playbackEventTransport, DiagnosticDataCollector diagnosticDataCollector, SmoothStreamingBitrateChangeAdapter smoothStreamingBitrateChangeAdapter, SmoothStreamingContentEventAdapter smoothStreamingContentEventAdapter, AVODContentUrlPolicyManager aVODContentUrlPolicyManager, VideoCacheManager videoCacheManager, DownloadService downloadService, SmoothStreamingVideoPresentationEventReporter smoothStreamingVideoPresentationEventReporter, PlaybackSupportEvaluator playbackSupportEvaluator, BaseDrmSystem baseDrmSystem, RendererSchemeController rendererSchemeController, SubtitlesEngine subtitlesEngine, @Nonnull LiveWindowDuration liveWindowDuration, @Nonnull VideoOptions videoOptions, @Nonnull PlaybackMediaEventReporters.Factory factory, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull TimelineMonitor timelineMonitor, @Nonnull AdEventTransport adEventTransport, @Nonnull PlaybackSessionFailureManager playbackSessionFailureManager, @Nonnull PlaybackConfig playbackConfig) {
        this(new PLSMBasedPlaybackSessionResources(executorService, contentSessionFactory, mediaProfiler, videoPlaybackEngineFactory, playbackEventTransport, diagnosticDataCollector, smoothStreamingBitrateChangeAdapter, smoothStreamingContentEventAdapter, aVODContentUrlPolicyManager, videoCacheManager, downloadService, smoothStreamingVideoPresentationEventReporter, playbackSupportEvaluator, baseDrmSystem, rendererSchemeController, subtitlesEngine, LifecycleProfilerFactory.createLifecycleProfiler(), liveWindowDuration, timelineMonitor, factory, contentManagementEventBus, adEventTransport, playbackSessionFailureManager, new ConsumptionIdProvider()), new PLSMBasedPlaybackSessionContext(videoOptions, rendererSchemeController), ExecutorBuilder.newBuilderFor(PlayerLifecycleStateMachine.class, new String[0]).withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build(), playbackConfig.getPlayerLifecycleStateMachineTerminateTimeout().getTotalMilliseconds());
    }

    PlayerLifecycleStateMachine(PlaybackSessionResources playbackSessionResources, PlaybackSessionContext playbackSessionContext, ExecutorService executorService, long j) {
        super("AIV.Player.PlayerLifecycleStateMachine");
        this.mPlaybackSessionResources = playbackSessionResources;
        this.mPlaybackSessionContext = playbackSessionContext;
        this.mExecutor = executorService;
        this.mTerminateTimeoutMillis = j;
        IdleState idleState = new IdleState(this, playbackSessionResources, playbackSessionContext);
        PreparingState preparingState = new PreparingState(this, playbackSessionResources, playbackSessionContext);
        ActiveState activeState = new ActiveState(this, playbackSessionResources, playbackSessionContext);
        TeardownState teardownState = new TeardownState(this, playbackSessionResources, playbackSessionContext);
        StateBuilder<PlayerLifecycleState.Type, Triggers.Type> stateBuilderRegisterTransition = setupState(idleState).registerTransition(Triggers.Type.PREPARE, preparingState);
        Triggers.Type type = Triggers.Type.RESTART;
        StateBuilder<PlayerLifecycleState.Type, Triggers.Type> stateBuilderRegisterTransition2 = stateBuilderRegisterTransition.registerTransition(type, teardownState);
        Triggers.Type type2 = Triggers.Type.TEARDOWN;
        stateBuilderRegisterTransition2.registerTransition(type2, teardownState);
        setupState(preparingState).registerTransition(Triggers.Type.ACTIVATE, activeState).registerTransition(type, teardownState).registerTransition(type2, teardownState);
        setupState(activeState).registerTransition(type, teardownState).registerTransition(type2, teardownState);
        setupState(teardownState).registerTransition(Triggers.Type.TEARDOWN_COMPLETE, idleState);
        start(idleState);
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    public void prepare(@Nonnull VideoSpecification videoSpecification, @Nullable File file, @Nullable String str, @Nullable AudioVideoUrls audioVideoUrls) {
        Preconditions.checkNotNull(videoSpecification, "spec");
        Preconditions.checkArgument(videoSpecification.getAudioTrackIds().size() <= 1, "Video specifications for playback need 0 or 1 audio track ids.");
        this.mStoragePath = file;
        doTriggerAsync(new Triggers.Prepare(videoSpecification, file, str, audioVideoUrls));
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    public void start(@Nonnull VideoRenderingSettings videoRenderingSettings) {
        doTriggerAsync(new Triggers.Activate(videoRenderingSettings));
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    public VideoPlaybackEngine getPlaybackEngine() {
        return this.mPlaybackSessionContext.getPlaybackEngine();
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    public void terminate(boolean z, @Nullable MediaException mediaException) {
        doTriggerAsync(new Triggers.TearDown(z));
        synchronized (this.mExecutor) {
            handleTerminationCause(mediaException);
            this.mExecutor.shutdown();
            try {
            } catch (InterruptedException e) {
                forceReleaseDrmSystemLock(String.format(Locale.US, "Interrupted while trying to terminate: %s", e.getMessage()));
            }
            if (!this.mExecutor.awaitTermination(this.mTerminateTimeoutMillis, TimeUnit.MILLISECONDS)) {
                forceReleaseDrmSystemLock(String.format(Locale.US, "Failed to terminate player in the allotted time: %s ms", Long.valueOf(this.mTerminateTimeoutMillis)));
            }
        }
    }

    private void handleTerminationCause(@Nullable MediaException mediaException) {
        if (mediaException == null) {
            return;
        }
        DLog.logf("Termination cause: %s", mediaException);
        this.mPlaybackSessionResources.getPlaybackSessionFailureManager().notifyMediaException(mediaException, this.mPlaybackSessionContext.getRendererSchemeType());
        if (this.mPlaybackSessionResources.getPlaybackConfig().getLicenseFailureErrorStrings().contains(mediaException.getErrorCode().getExternalCode().getName())) {
            VideoCacheManager cacheManager = this.mPlaybackSessionResources.getCacheManager();
            VideoSpecification videoSpec = this.mPlaybackSessionContext.getVideoSpec();
            if (videoSpec != null && cacheManager.queryFor(videoSpec) != null) {
                DLog.warnf("Triggering eviction of cached content due to missing or expired key in license");
                cacheManager.evict(videoSpec, WhisperCacheMetrics$CacheEviction.LICENSE_ERROR, "Triggering eviction of cached content due to missing or expired key in license");
            }
        }
        if (mediaException.getErrorCode().getExternalCode() == LicenseError.AIV_DRM_FRAMEWORK_BUSY.getExternalCode()) {
            forceReleaseDrmSystemLock(String.format(Locale.US, "Force released the drm lock because %s caused the session to terminate.", mediaException));
        }
    }

    private void forceReleaseDrmSystemLock(@Nonnull String str) {
        if (this.mPlaybackSessionResources.getPlaybackConfig().isDrmSystemBusyRecoveryEnabled()) {
            try {
                VideoSpecification videoSpec = this.mPlaybackSessionContext.getVideoSpec();
                ContentSession contentSession = this.mPlaybackSessionContext.getContentSession();
                if (videoSpec != null && contentSession != null && contentSession.isActive()) {
                    String encodedEncryptionHeader = contentSession.getEncodedEncryptionHeader();
                    DrmScheme drmScheme = this.mPlaybackSessionContext.getDrmScheme();
                    RendererSchemeType rendererSchemeType = this.mPlaybackSessionContext.getRendererSchemeType();
                    if (encodedEncryptionHeader == null) {
                        return;
                    }
                    this.mPlaybackSessionResources.getDrmSystem().shutdownPlayback(videoSpec.getTitleId(), encodedEncryptionHeader, this.mPlaybackSessionContext.getDownload() == null ? ConsumptionType.Streaming : ConsumptionType.Download, drmScheme, rendererSchemeType);
                    DLog.warnf(str);
                    this.mPlaybackSessionResources.getEventReporter().getPlaybackReporter().reportMetric(QOSEventName.Information.toString(), "forceReleaseDrm", null, str, null);
                }
            } catch (ContentException e) {
                DLog.warnf("Failed to release the drm system lock because of %s.", e);
            }
        }
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    @Nonnull
    public PlaybackSessionContext getContext() {
        return this.mPlaybackSessionContext;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    @Nonnull
    public PlaybackSessionResources getResources() {
        return this.mPlaybackSessionResources;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    public void restart(@Nonnull VideoSpecification videoSpecification, @Nonnull PlaybackRestartEvent playbackRestartEvent) {
        PlaybackMediaEventReporters mediaEventReportersMain;
        Preconditions.checkNotNull(videoSpecification, "spec");
        Preconditions.checkNotNull(playbackRestartEvent, "cause");
        Preconditions.checkArgument(videoSpecification.getAudioTrackIds().size() <= 1, "Video specifications for playback need 0 or 1 audio track ids.");
        if (EventSubtypes$Audio.PLAYBACK_AUDIO_TRACK_CHANGE_TYPES.contains(playbackRestartEvent.getTypeString())) {
            if (AloysiusConfig.getInstance().shouldUnifyMediaEventReporters()) {
                mediaEventReportersMain = this.mPlaybackSessionResources.getMediaEventReporters();
            } else {
                mediaEventReportersMain = this.mPlaybackSessionResources.getMediaEventReportersMain();
            }
            mediaEventReportersMain.getAloysiusInteractionReporter().reportAudioTrackChange();
            this.mPlaybackSessionResources.getPlaybackEventTransport().postEvent(new AudioTrackChangeEvent(TimeSpan.ZERO, playbackRestartEvent.getLanguageCode(), this.mPlaybackSessionContext.getContentSession().getConsumptionId(), videoSpecification.getPrimaryAudioTrackId()));
        }
        if (playbackRestartEvent.getType() == 14) {
            this.mPlaybackSessionResources.getPlaybackSessionFailureManager().executeRecoveryAction(new PlaybackException(PlaybackException.PlaybackError.AUTO_EVAL_CANARY_FAILURE, playbackRestartEvent.getMessage()), this.mPlaybackSessionContext.getRendererSchemeType());
        }
        if (playbackRestartEvent.getType() == 15) {
            this.mPlaybackSessionResources.getPlaybackSessionFailureManager().executeRecoveryAction(new PlaybackException(PlaybackException.PlaybackError.AUTO_EVAL_POOR_PLAYER_PERFORMANCE, playbackRestartEvent.getMessage()), this.mPlaybackSessionContext.getRendererSchemeType());
        }
        VideoRenderingSettings outputSettings = this.mPlaybackSessionContext.getOutputSettings();
        doTriggerAsync(new Triggers.Restart(videoSpecification, playbackRestartEvent));
        prepare(videoSpecification, this.mStoragePath, playbackRestartEvent.getContentUrlSetId(), playbackRestartEvent.getAudioVideoUrls());
        if (outputSettings != null) {
            start(outputSettings);
        } else {
            DLog.warnf("PLSM: Restart called before start");
        }
    }

    private void doTriggerAsync(@Nonnull final Trigger<Triggers.Type> trigger) {
        synchronized (this.mExecutor) {
            if (!this.mExecutor.isShutdown()) {
                this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.playback.smoothstream.fsm.PlayerLifecycleStateMachine.1DoTriggerAsyncRunnable
                    @Override // java.lang.Runnable
                    public void run() {
                        PlayerLifecycleStateMachine.this.doTrigger(trigger);
                    }
                });
            }
        }
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    @Nullable
    public ContentSession getContentSession() {
        return this.mPlaybackSessionContext.getContentSession();
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    @Nullable
    public VideoCacheManager getCacheManager() {
        return this.mPlaybackSessionResources.getCacheManager();
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    @Nullable
    public PlaybackDataSource getDataSource() {
        return this.mPlaybackSessionContext.getDataSource();
    }

    @Override // com.amazon.avod.playback.session.PlaybackSession
    @Nullable
    public PVDownloadPlayerShim getDownload() {
        return this.mPlaybackSessionContext.getDownload();
    }
}
