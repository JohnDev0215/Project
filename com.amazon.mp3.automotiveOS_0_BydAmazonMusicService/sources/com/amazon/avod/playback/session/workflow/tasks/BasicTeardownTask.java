package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.player.VideoPlaybackEngine;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.userdownload.PauseToken;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import com.google.common.eventbus.EventBus;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BasicTeardownTask extends SimpleTask {
    protected final PlaybackSessionContext mPlaybackSessionContext;
    protected final PlaybackSessionResources mPlaybackSessionResources;
    private final boolean mShouldRetainReusableComponents;
    protected final MediaException mTerminationCause;
    private final Ticker mTicker;

    protected abstract void executePostContentSessionTerminationTasks() throws MediaException;

    protected abstract void executePreContentSessionTerminationTasks() throws MediaException;

    public BasicTeardownTask(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSessionResources playbackSessionResources, @Nonnull PlaybackSessionContext playbackSessionContext, boolean z, @Nullable MediaException mediaException) {
        this(eventBus, exceptionCallback, playbackSessionResources, playbackSessionContext, z, Tickers.androidTicker(), mediaException);
    }

    BasicTeardownTask(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSessionResources playbackSessionResources, @Nonnull PlaybackSessionContext playbackSessionContext, boolean z, @Nonnull Ticker ticker, @Nullable MediaException mediaException) {
        super(eventBus, exceptionCallback);
        this.mPlaybackSessionResources = (PlaybackSessionResources) Preconditions.checkNotNull(playbackSessionResources, "playbackSessionResources");
        this.mPlaybackSessionContext = (PlaybackSessionContext) Preconditions.checkNotNull(playbackSessionContext, "playbackSessionContext");
        this.mShouldRetainReusableComponents = z;
        this.mTicker = (Ticker) Preconditions.checkNotNull(ticker, "ticker");
        this.mTerminationCause = mediaException;
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.BaseTask
    protected final void execute() throws MediaException {
        DLog.logf("Closing playback session.");
        Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(this.mTicker);
        executePreContentSessionTerminationTasks();
        executeContentSessionTerminationTasks();
        executePostContentSessionTerminationTasks();
        DLog.logf("Closed playback session in %s.", new TimeSpan(stopwatchCreateStarted.stop()));
    }

    protected void executeContentSessionTerminationTasks() throws MediaException {
        this.mPlaybackSessionResources.getContentEventAdapter().shutdown();
        VideoPlaybackEngine playbackEngine = this.mPlaybackSessionContext.getPlaybackEngine();
        if (playbackEngine != null) {
            VideoSpecification videoSpec = this.mPlaybackSessionContext.getVideoSpec();
            playbackEngine.close(this.mShouldRetainReusableComponents, videoSpec != null && videoSpec.isLiveStream());
        }
        ContentSession contentSession = this.mPlaybackSessionContext.getContentSession();
        if (contentSession != null) {
            contentSession.end(false);
        }
        this.mPlaybackSessionResources.getEventReporter().releaseAll();
        PauseToken downloadsPausedToken = this.mPlaybackSessionContext.getDownloadsPausedToken();
        if (downloadsPausedToken != null) {
            this.mPlaybackSessionResources.getDownloadManager().resume(downloadsPausedToken);
        }
        this.mPlaybackSessionResources.getDownloadService().restrictBackgroundDownloads(false);
    }
}
