package com.amazon.avod.playback.player.states;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionState;
import com.amazon.avod.content.PlayerBindState;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.content.urlvending.FragmentRepresentation;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.PlaybackSessionProtocol;
import com.amazon.avod.playback.event.playback.PlaybackLoadedEvent;
import com.amazon.avod.playback.player.VideoPlaybackTimeline;
import com.amazon.avod.playback.player.actions.Action;
import com.amazon.avod.playback.player.actions.LoadAction;
import com.amazon.avod.playback.player.actions.SeekAction;
import com.amazon.avod.pmet.LifecycleProfilerMetrics;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Ticker;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class LoadingState extends BufferingBaseState {
    private boolean mIsFeedingSamples;
    private long mResumeTimeInNanoseconds;

    @Override // com.amazon.avod.playback.player.states.BufferingBaseState
    protected void sendBufferStatusEvents(float f) {
    }

    public LoadingState(PlaybackStateDependencies playbackStateDependencies, PlaybackStateContext playbackStateContext) {
        this(playbackStateDependencies, playbackStateContext, Tickers.androidTicker(), playbackStateDependencies.getConfig().getPlaybackStateMachineTickInterval());
    }

    LoadingState(PlaybackStateDependencies playbackStateDependencies, PlaybackStateContext playbackStateContext, Ticker ticker, @Nonnull TimeSpan timeSpan) {
        super(playbackStateDependencies, playbackStateContext, ticker, timeSpan);
        this.mResumeTimeInNanoseconds = 0L;
        this.mIsFeedingSamples = false;
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public PlaybackState getState() {
        return PlaybackState.Loading;
    }

    @Override // com.amazon.avod.playback.player.states.BufferingBaseState, com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onEnter(Action action) throws MediaException {
        TimeSpan startTime;
        super.onEnter(action);
        Preconditions.checkArgument(action instanceof LoadAction, "Can only accept LoadAction in LoadingState!");
        LoadAction loadAction = (LoadAction) action;
        ContentSession contentSession = loadAction.getContentSession();
        this.mPlaybackStateContext.setContentSession(contentSession);
        contentSession.notifyPlayerBindStateChange(PlayerBindState.ATTACHED_LOADING);
        VideoSpecification spec = loadAction.getSpec();
        this.mPlaybackStateContext.setSpecification(spec);
        if (spec.isLiveStream()) {
            startTime = resolveToTimeWindowBoundary(spec.getStartTime(), true);
        } else {
            startTime = spec.getStartTime();
        }
        PlaybackSessionProtocol protocol = contentSession.getProtocol();
        ContentSessionState state = contentSession.getContext().getState();
        MediaProfiler profiler = getProfiler();
        profiler.start("PlaybackSessionProtocol.start");
        TimeSpan timeSpanStart = protocol.start(startTime);
        this.mPlaybackStateContext.setProtocol(protocol);
        this.mPlaybackStateDependencies.getDataForwarder().initialize(protocol, state);
        this.mPlaybackStateContext.getSubtitlesEngine().initialize(this.mPlaybackStateDependencies.getEventTransport(), new VideoPlaybackTimeline(getRenderer()), protocol, contentSession.getContext().getStreamSelections());
        profiler.stop("PlaybackSessionProtocol.start");
        LifecycleProfiler lifecycleProfiler = this.mPlaybackStateContext.getLifecycleProfiler();
        LifecycleProfilerMetrics lifecycleProfilerMetrics = LifecycleProfilerMetrics.CONFIGURE_CODECS;
        lifecycleProfiler.start(lifecycleProfilerMetrics);
        profiler.start("VideoRenderer.configureCodecs");
        this.mPlaybackStateContext.setRendererStatusFlags(getRenderer().configureCodecs(protocol.getVideoConfig(), protocol.getAudioConfig(), protocol.isEncryptedContent(), contentSession.getContext().getContentBufferLookAheadSize()) | this.mPlaybackStateContext.getRendererStatusFlags());
        this.mPlaybackStateContext.getLifecycleProfiler().end(lifecycleProfilerMetrics);
        profiler.stop("VideoRenderer.configureCodecs");
        updateResumeTime(timeSpanStart);
        checkForManifestSupport();
    }

    @Override // com.amazon.avod.playback.player.states.BufferingBaseState, com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onExit() throws MediaException {
        super.onExit();
        tryReportLoadingComplete();
    }

    @Override // com.amazon.avod.playback.player.states.BufferingBaseState
    protected boolean isContentReadyToWatch() {
        return canStartFromTimeInNanos(this.mResumeTimeInNanoseconds);
    }

    @Override // com.amazon.avod.playback.player.states.BufferingBaseState
    protected PlaybackState handleSeekAction(SeekAction seekAction, PlaybackState playbackState) throws MediaException {
        TimeSpan toTime;
        if (this.mPlaybackStateContext.getSpecification().isLiveStream()) {
            toTime = resolveToTimeWindowBoundary(seekAction.getToTime(), false);
        } else {
            toTime = seekAction.getToTime();
        }
        TimeSpan timeSpanSeekTo = getPlaybackSessionProtocol().seekTo(toTime, false);
        getRenderer().flush();
        getSubtitlesEngine().flush(false);
        updateResumeTime(timeSpanSeekTo);
        return null;
    }

    @Override // com.amazon.avod.playback.player.states.BufferingBaseState
    protected void handleReadyToWatch() {
        super.handleReadyToWatch();
        tryReportLoadingComplete();
        waitForNextAction();
    }

    private void updateResumeTime(TimeSpan timeSpan) {
        this.mResumeTimeInNanoseconds = timeSpan.getTotalNanoSeconds();
        getTimeline().setObservedTimeInNanos(this.mResumeTimeInNanoseconds);
        getPlaybackSessionProtocol().setPlayPositionInNanos(this.mResumeTimeInNanoseconds);
        if (this.mIsFeedingSamples) {
            return;
        }
        this.mPlaybackStateContext.getLifecycleProfiler().start(LifecycleProfilerMetrics.QUEUEING_SAMPLES);
        getProfiler().start("QueueingSamples");
        this.mIsFeedingSamples = true;
    }

    private void tryReportLoadingComplete() {
        if (this.mIsFeedingSamples) {
            this.mPlaybackStateContext.getLifecycleProfiler().end(LifecycleProfilerMetrics.QUEUEING_SAMPLES);
            getProfiler().stop("QueueingSamples");
            PlaybackLoadedEvent playbackLoadedEvent = new PlaybackLoadedEvent(new TimeSpan(getPlaybackTimeInNanoseconds()), this.mPlaybackStateContext.getContentSession().getConsumptionId());
            postEvent(playbackLoadedEvent);
            getPlaybackEventReporter().handlePlaybackLoadedEvent(playbackLoadedEvent);
            this.mIsFeedingSamples = false;
        }
    }

    private void checkForManifestSupport() throws ContentException {
        AudioVideoUrls audioVideoUrls = this.mPlaybackStateContext.getContentSession().getAudioVideoUrls();
        if (audioVideoUrls == null || audioVideoUrls.getContentUrls().isEmpty()) {
            return;
        }
        ContentUrl contentUrl = audioVideoUrls.getContentUrls().get(0);
        if (!FragmentRepresentation.SeparateFile.equals(contentUrl.getFragmentRepresentation()) || ContentUrl.isDashUrl(contentUrl.getUrl())) {
            return;
        }
        DLog.errorf("Players are not given a dash manifest for either live/JAB");
        throw new ContentException(ContentException.ContentError.MALFORMED_MANIFEST, String.format(Locale.US, "Players are not given a dash manifest for either live/JAB : asin %s", this.mPlaybackStateContext.getSpecification().getTitleId()));
    }
}
