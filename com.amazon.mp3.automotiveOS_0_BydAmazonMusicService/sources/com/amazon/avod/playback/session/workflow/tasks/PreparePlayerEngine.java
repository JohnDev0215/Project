package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.playback.event.playback.PlaybackLoadedEvent;
import com.amazon.avod.playback.player.VideoPlaybackEngine;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.pmet.LifecycleProfilerMetrics;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PreparePlayerEngine extends AsyncResponseBasedTask {
    private Boolean mIsStateActive;
    private final ManifestCapturerInterface mManifestCapturer;
    private final PlaybackSessionContext mPlaybackSessionContext;
    private final PlaybackSessionResources mPlaybackSessionResources;
    private final Object mStateMutex;

    public PreparePlayerEngine(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSessionResources playbackSessionResources, @Nonnull PlaybackSessionContext playbackSessionContext, @Nonnull ManifestCapturerInterface manifestCapturerInterface) {
        super(eventBus, exceptionCallback);
        this.mIsStateActive = Boolean.FALSE;
        this.mStateMutex = new Object();
        this.mPlaybackSessionResources = (PlaybackSessionResources) Preconditions.checkNotNull(playbackSessionResources, "playbackSessionResources");
        this.mPlaybackSessionContext = (PlaybackSessionContext) Preconditions.checkNotNull(playbackSessionContext, "playbackSessionContext");
        this.mManifestCapturer = (ManifestCapturerInterface) Preconditions.checkNotNull(manifestCapturerInterface, "manifestCapturer");
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.BaseTask
    protected void execute() throws MediaException {
        PlaybackMediaEventReporters mediaEventReportersMain;
        synchronized (this.mStateMutex) {
            this.mIsStateActive = Boolean.TRUE;
            if (AloysiusConfig.getInstance().shouldUnifyMediaEventReporters()) {
                mediaEventReportersMain = this.mPlaybackSessionResources.getMediaEventReporters();
            } else {
                mediaEventReportersMain = this.mPlaybackSessionResources.getMediaEventReportersMain();
            }
            VideoPlaybackEngine videoPlaybackEngineCreateVideoPlaybackEngine = this.mPlaybackSessionResources.getVideoPlaybackEngineFactory().createVideoPlaybackEngine(this.mPlaybackSessionContext.getRendererSchemeType(), this.mPlaybackSessionContext.getDrmScheme(), mediaEventReportersMain.getPlaybackReporter(), mediaEventReportersMain.getAloysiusInteractionReporter(), mediaEventReportersMain.getAloysiusPlayerHeuristicReporter(), this.mManifestCapturer, mediaEventReportersMain.getAloysiusDeviceReporter(), this.mPlaybackSessionResources.getEventReporter().getPlaybackReporter());
            VideoSpecification videoSpecification = (VideoSpecification) Preconditions.checkNotNull(this.mPlaybackSessionContext.getVideoSpec(), "videoSpecification");
            ContentSession contentSession = (ContentSession) Preconditions.checkNotNull(this.mPlaybackSessionContext.getContentSession(), "contentSession");
            this.mPlaybackSessionContext.setPlaybackEngine(videoPlaybackEngineCreateVideoPlaybackEngine);
            videoPlaybackEngineCreateVideoPlaybackEngine.initialize(videoSpecification.getAudioFormat());
            videoPlaybackEngineCreateVideoPlaybackEngine.setSubtitlesEngine(this.mPlaybackSessionResources.getSubtitlesEngine());
            videoPlaybackEngineCreateVideoPlaybackEngine.setLifecycleProfiler(this.mPlaybackSessionResources.getLifecycleProfiler());
            this.mPlaybackSessionResources.getPlaybackEventTransport().registerEventBusHandler(this);
            videoPlaybackEngineCreateVideoPlaybackEngine.prepareContent(videoSpecification, contentSession);
            this.mPlaybackSessionResources.getLifecycleProfiler().end(LifecycleProfilerMetrics.DAG_PREPARE);
        }
    }

    @Subscribe
    public void onPreparedEvent(PlaybackLoadedEvent playbackLoadedEvent) {
        synchronized (this.mStateMutex) {
            if (this.mIsStateActive.booleanValue()) {
                this.mIsStateActive = Boolean.FALSE;
                this.mPlaybackSessionContext.notifyPrimaryPlayerPrepared();
                ContentSession contentSession = this.mPlaybackSessionContext.getContentSession();
                Preconditions.checkState(contentSession != null);
                contentSession.setPlaybackRestrictedToBufferedContent(true);
                this.mPlaybackSessionResources.getPlaybackEventTransport().unregisterEventBusHandler(this);
                notifyTaskSuccess();
            }
        }
    }

    @Override // com.amazon.avod.playback.session.workflow.tasks.AsyncResponseBasedTask
    public void executeAbort() {
        synchronized (this.mStateMutex) {
            if (this.mIsStateActive.booleanValue()) {
                this.mIsStateActive = Boolean.FALSE;
                this.mPlaybackSessionResources.getPlaybackEventTransport().unregisterEventBusHandler(this);
            }
        }
    }
}
