package com.amazon.avod.playback.smoothstream.fsm;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.event.playback.PlaybackPerformanceEvent;
import com.amazon.avod.playback.event.playback.PlaybackStartEvent;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.playback.smoothstream.fsm.PlayerLifecycleState;
import com.amazon.avod.playback.smoothstream.fsm.Triggers;
import com.amazon.avod.qos.SmoothStreamingVideoPresentationEventReporter;
import com.amazon.avod.userdownload.PVDownloadManagerPlayerShim;
import com.amazon.avod.userdownload.PVDownloadPlayerShim;
import com.amazon.avod.userdownload.UserDownloadState;
import com.amazon.avod.userdownload.reporting.MakeActiveCause;
import com.amazon.avod.userdownload.reporting.PauseCause;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class ActiveState extends PlayerLifecycleState {
    public ActiveState(PlayerLifecycleStateMachine playerLifecycleStateMachine, PlaybackSessionResources playbackSessionResources, PlaybackSessionContext playbackSessionContext) {
        super(playerLifecycleStateMachine, PlayerLifecycleState.Type.ACTIVE, playbackSessionResources, playbackSessionContext);
    }

    @Override // com.amazon.avod.playback.smoothstream.fsm.PlayerLifecycleState
    protected void doEnter(@Nonnull Trigger<Triggers.Type> trigger) {
        Preconditions.checkArgument(trigger instanceof Triggers.Activate, "trigger must be instance of Triggers.Activating");
        this.mPlaybackSessionContext.setOutputSettings(((Triggers.Activate) trigger).getSettings());
        PVDownloadManagerPlayerShim downloadManager = this.mPlaybackSessionResources.getDownloadManager();
        if (this.mPlaybackSessionResources.getPlaybackConfig().shouldPauseDownloadWhileStreaming()) {
            this.mPlaybackSessionContext.setDownloadsPausedToken(downloadManager.pause(PauseCause.PLAYBACK_STARTED_PAUSE));
        }
        boolean z = true;
        this.mPlaybackSessionResources.getDownloadService().restrictBackgroundDownloads(true);
        ContentSession contentSession = this.mPlaybackSessionContext.getContentSession();
        Preconditions.checkState(contentSession != null);
        if (contentSession.isRestarting()) {
            return;
        }
        if (contentSession.getContext().getSessionType() == ContentSessionType.PROGRESSIVE_PLAYBACK) {
            PVDownloadPlayerShim download = this.mPlaybackSessionContext.getDownload();
            if (download.getState() != UserDownloadState.QUEUED && download.getState() != UserDownloadState.QUEUEING && download.getState() != UserDownloadState.DOWNLOADING && download.getState() != UserDownloadState.PAUSED && download.getState() != UserDownloadState.WAITING) {
                z = false;
            }
            if (z) {
                downloadManager.makeActive(download, MakeActiveCause.PROGRESSIVE_PLAYBACK);
            }
            downloadManager.registerStreamingDownloadEventListener(download, contentSession);
        }
        contentSession.setPlaybackRestrictedToBufferedContent(false);
        this.mPlaybackSessionResources.getPlaybackEventTransport().registerEventBusHandler(this);
        this.mPlaybackSessionContext.getPlaybackEngine().launchPlayback(this.mPlaybackSessionContext.getOutputSettings(), this.mPlaybackSessionContext.getOfflineKeyId(), this.mPlaybackSessionContext.getDrmScheme(), this.mPlaybackSessionContext.getRendererSchemeType(), ContentUrl.isDashUrl(contentSession.getContext().getContentUrl()));
    }

    @Override // com.amazon.avod.playback.smoothstream.fsm.PlayerLifecycleState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void exit(@Nonnull Trigger<Triggers.Type> trigger) {
        Preconditions.checkNotNull(trigger, "exit() must be called with a valid trigger.");
        this.mPlaybackSessionResources.getPlaybackEventTransport().unregisterEventBusHandler(this);
    }

    @Subscribe
    public void onPlaybackPerformanceEvent(PlaybackPerformanceEvent playbackPerformanceEvent) {
        ContentSession contentSession = this.mPlaybackSessionContext.getContentSession();
        Preconditions.checkState(contentSession != null);
        if (contentSession.getContext() == null || contentSession.getContext().getEffectiveSessionType() != ContentSessionType.STREAMING) {
            return;
        }
        contentSession.setPlaybackPerformanceReport(playbackPerformanceEvent.getPlaybackPerformanceReport());
    }

    @Subscribe
    public void onPlaybackStartEvent(PlaybackStartEvent playbackStartEvent) {
        final ContentSession contentSession = this.mPlaybackSessionContext.getContentSession();
        final SmoothStreamingVideoPresentationEventReporter eventReporter = this.mPlaybackSessionResources.getEventReporter();
        final PlaybackEventReporter playbackReporter = eventReporter.getPlaybackReporter();
        PlaybackEventTransport playbackEventTransport = this.mPlaybackSessionResources.getPlaybackEventTransport();
        ContentSessionContext context = contentSession.getContext();
        AudioVideoUrls audioVideoUrls = context.getAudioVideoUrls();
        VideoSpecification specification = context.getSpecification();
        final VideoOptions videoOptions = this.mPlaybackSessionContext.getVideoOptions();
        final RendererSchemeType rendererSchemeType = this.mPlaybackSessionContext.getRendererSchemeType();
        final RendererScheme rendererScheme = this.mPlaybackSessionResources.getRendererSchemeController().getRendererScheme(rendererSchemeType, this.mPlaybackSessionContext.getDrmScheme());
        Preconditions.checkNotNull(contentSession, "session");
        Preconditions.checkNotNull(eventReporter, "videoPresentationEventReporter");
        Preconditions.checkNotNull(playbackReporter, "playbackEventReporter");
        Preconditions.checkNotNull(playbackEventTransport, "playbackEventTransport");
        Preconditions.checkNotNull(audioVideoUrls, "audioVideoUrls");
        Preconditions.checkNotNull(specification, "videoSpecification");
        this.mPlaybackSessionResources.getExecutor().execute(new Runnable() { // from class: com.amazon.avod.playback.smoothstream.fsm.ActiveState.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ActiveState.this.mPlaybackSessionResources.getContentUrlPolicyManager().validate(contentSession, videoOptions.getSessionContext(), eventReporter.getUserWatchSessionId(), playbackReporter, rendererSchemeType, rendererScheme);
                } catch (MediaException e) {
                    DLog.exceptionf(e, "Caught exception asynchronously validating playback rights for content %s!", ActiveState.this.mPlaybackSessionContext.getVideoSpec());
                    ActiveState.this.handleError(e);
                }
            }
        });
    }
}
