package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.event.playback.PlaybackStartEvent;
import com.amazon.avod.playback.player.VideoPlaybackEngine;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.qos.SmoothStreamingVideoPresentationEventReporter;
import com.amazon.avod.userdownload.PVDownloadManagerPlayerShim;
import com.amazon.avod.userdownload.PVDownloadPlayerShim;
import com.amazon.avod.userdownload.UserDownloadState;
import com.amazon.avod.userdownload.reporting.MakeActiveCause;
import com.amazon.avod.userdownload.reporting.PauseCause;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class StartPlayback extends AsyncResponseBasedTask {
    private final PlaybackSessionContext mPlaybackSessionContext;
    private final PlaybackSessionResources mPlaybackSessionResources;

    public StartPlayback(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSessionResources playbackSessionResources, @Nonnull PlaybackSessionContext playbackSessionContext) {
        super(eventBus, exceptionCallback);
        this.mPlaybackSessionResources = (PlaybackSessionResources) Preconditions.checkNotNull(playbackSessionResources, "playbackSessionResources");
        this.mPlaybackSessionContext = (PlaybackSessionContext) Preconditions.checkNotNull(playbackSessionContext, "playbackSessionContext");
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.BaseTask
    protected void execute() throws MediaException {
        PVDownloadManagerPlayerShim downloadManager = this.mPlaybackSessionResources.getDownloadManager();
        if (this.mPlaybackSessionResources.getPlaybackConfig().shouldPauseDownloadWhileStreaming()) {
            this.mPlaybackSessionContext.setDownloadsPausedToken(downloadManager.pause(PauseCause.PLAYBACK_STARTED_PAUSE));
        }
        boolean z = true;
        this.mPlaybackSessionResources.getDownloadService().restrictBackgroundDownloads(true);
        ContentSession contentSession = (ContentSession) Preconditions.checkNotNull(this.mPlaybackSessionContext.getContentSession(), "session");
        if (contentSession.getContext().getSessionType() == ContentSessionType.PROGRESSIVE_PLAYBACK) {
            PVDownloadPlayerShim pVDownloadPlayerShim = (PVDownloadPlayerShim) Preconditions.checkNotNull(this.mPlaybackSessionContext.getDownload(), "userDownload");
            if (pVDownloadPlayerShim.getState() != UserDownloadState.QUEUED && pVDownloadPlayerShim.getState() != UserDownloadState.QUEUEING && pVDownloadPlayerShim.getState() != UserDownloadState.DOWNLOADING && pVDownloadPlayerShim.getState() != UserDownloadState.PAUSED && pVDownloadPlayerShim.getState() != UserDownloadState.WAITING) {
                z = false;
            }
            if (z) {
                downloadManager.makeActive(pVDownloadPlayerShim, MakeActiveCause.PROGRESSIVE_PLAYBACK);
            }
            downloadManager.registerStreamingDownloadEventListener(pVDownloadPlayerShim, contentSession);
        }
        contentSession.setPlaybackRestrictedToBufferedContent(false);
        this.mPlaybackSessionResources.getPlaybackEventTransport().registerEventBusHandler(this);
        ((VideoPlaybackEngine) Preconditions.checkNotNull(this.mPlaybackSessionContext.getPlaybackEngine(), "videoPlaybackEngine")).launchPlayback((VideoRenderingSettings) Preconditions.checkNotNull(this.mPlaybackSessionContext.getOutputSettings(), "videoRenderingSettings"), this.mPlaybackSessionContext.getOfflineKeyId(), this.mPlaybackSessionContext.getDrmScheme(), this.mPlaybackSessionContext.getRendererSchemeType(), this.mPlaybackSessionContext.isDashSession());
    }

    @Subscribe
    public void onPlaybackStartEvent(PlaybackStartEvent playbackStartEvent) {
        final ContentSession contentSession = this.mPlaybackSessionContext.getContentSession();
        final Map<String, String> sessionContext = this.mPlaybackSessionContext.getVideoOptions().getSessionContext();
        SmoothStreamingVideoPresentationEventReporter eventReporter = this.mPlaybackSessionResources.getEventReporter();
        final PlaybackEventReporter playbackReporter = eventReporter.getPlaybackReporter();
        final String userWatchSessionId = eventReporter.getUserWatchSessionId();
        ExecutorService executor = this.mPlaybackSessionResources.getExecutor();
        final RendererSchemeType rendererSchemeType = this.mPlaybackSessionContext.getRendererSchemeType();
        final RendererScheme rendererScheme = this.mPlaybackSessionResources.getRendererSchemeController().getRendererScheme(rendererSchemeType, this.mPlaybackSessionContext.getDrmScheme());
        executor.execute(new Runnable() { // from class: com.amazon.avod.playback.session.workflow.tasks.StartPlayback.1
            @Override // java.lang.Runnable
            public void run() {
                StartPlayback.this.validatePlaybackRights(contentSession, sessionContext, userWatchSessionId, playbackReporter, rendererSchemeType, rendererScheme);
            }
        });
    }

    @Override // com.amazon.avod.playback.session.workflow.tasks.AsyncResponseBasedTask
    public void executeAbort() {
        this.mPlaybackSessionResources.getPlaybackEventTransport().unregisterEventBusHandler(this);
    }

    void validatePlaybackRights(@Nonnull ContentSession contentSession, @Nonnull Map<String, String> map, @Nonnull String str, @Nonnull PlaybackEventReporter playbackEventReporter, @Nullable RendererSchemeType rendererSchemeType, @Nonnull RendererScheme rendererScheme) {
        try {
            try {
                this.mPlaybackSessionResources.getContentUrlPolicyManager().validate(contentSession, map, str, playbackEventReporter, rendererSchemeType, rendererScheme);
                notifyTaskSuccess();
            } catch (MediaException e) {
                DLog.exceptionf(e, "Caught exception asynchronously validating playback rights for content %s!", this.mPlaybackSessionContext.getVideoSpec());
                notifyFailure(e);
            }
        } finally {
            this.mPlaybackSessionResources.getPlaybackEventTransport().unregisterEventBusHandler(this);
        }
    }
}
