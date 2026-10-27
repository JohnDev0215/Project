package com.amazon.avod.playback.smoothstream.fsm;

import androidx.core.os.EnvironmentCompat;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.smoothstream.StreamSelections;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.playback.smoothstream.RestartBeginEvent;
import com.amazon.avod.playback.smoothstream.fsm.PlayerLifecycleState;
import com.amazon.avod.playback.smoothstream.fsm.Triggers;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.userdownload.PauseToken;
import com.amazon.avod.util.DLog;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class TeardownState extends PlayerLifecycleState {
    public TeardownState(PlayerLifecycleStateMachine playerLifecycleStateMachine, PlaybackSessionResources playbackSessionResources, PlaybackSessionContext playbackSessionContext) {
        super(playerLifecycleStateMachine, PlayerLifecycleState.Type.TEARDOWN, playbackSessionResources, playbackSessionContext);
    }

    @Override // com.amazon.avod.playback.smoothstream.fsm.PlayerLifecycleState
    protected void doEnter(@Nonnull Trigger<Triggers.Type> trigger) {
        Preconditions.checkNotNull(trigger, "exit() must be called with a valid trigger.");
        DLog.logf("Closing playback session.");
        Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(Tickers.androidTicker());
        Triggers.Type type = trigger.getType();
        Triggers.Type type2 = Triggers.Type.RESTART;
        if (type == type2) {
            ContentSession contentSession = this.mPlaybackSessionContext.getContentSession();
            Triggers.Restart restart = (Triggers.Restart) trigger;
            if (contentSession.isRestarting()) {
                this.mPlaybackSessionResources.getPlaybackEventTransport().postEvent(new RestartBeginEvent(this.mPlaybackSessionContext.getVideoSpec(), restart.newSpec, EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, 0, EnvironmentCompat.MEDIA_UNKNOWN, restart.mCause));
            } else {
                StreamSelections streamSelections = contentSession.getContext().getStreamSelections();
                StreamIndex audioStream = streamSelections.getAudioStream();
                this.mPlaybackSessionResources.getPlaybackEventTransport().postEvent(new RestartBeginEvent(this.mPlaybackSessionContext.getVideoSpec(), restart.newSpec, (String) MoreObjects.firstNonNull(audioStream.getLanguage(), EnvironmentCompat.MEDIA_UNKNOWN), audioStream.getFourCC(), streamSelections.getStartAudioQualityLevel().getBitrate(), streamSelections.getSelectionParams(), restart.mCause));
            }
        }
        if (trigger.getType() != type2) {
            this.mPlaybackSessionResources.getDataCollector().shutdown();
        }
        this.mPlaybackSessionResources.getContentEventAdapter().shutdown();
        if (this.mPlaybackSessionContext.getPlaybackEngine() != null) {
            this.mPlaybackSessionContext.getPlaybackEngine().close(trigger instanceof Triggers.TearDown ? ((Triggers.TearDown) trigger).shouldRetainReusableComponents() : false, this.mPlaybackSessionContext.getVideoSpec().isLiveStream());
        }
        ContentSession contentSession2 = this.mPlaybackSessionContext.getContentSession();
        if (contentSession2 != null) {
            contentSession2.end(trigger.getType() != type2);
        }
        this.mPlaybackSessionResources.getDrmSystem().shutdownDrmSession(this.mPlaybackSessionContext.getDrmScheme(), this.mPlaybackSessionContext.getRendererSchemeType());
        this.mPlaybackSessionResources.getEventReporter().releaseAll();
        PauseToken downloadsPausedToken = this.mPlaybackSessionContext.getDownloadsPausedToken();
        if (downloadsPausedToken != null) {
            this.mPlaybackSessionResources.getDownloadManager().resume(downloadsPausedToken);
        }
        this.mPlaybackSessionResources.getDownloadService().restrictBackgroundDownloads(false);
        if (trigger.getType() == Triggers.Type.TEARDOWN) {
            this.mPlaybackSessionResources.getPlaybackEventTransport().shutdown();
            if (this.mPlaybackSessionContext.getVideoSpec().isLiveStream()) {
                this.mPlaybackSessionResources.getCacheManager().getLiveCache().notifyPlaybackTerminated();
            }
            this.mPlaybackSessionContext.getTerminationLatch().countDown();
        }
        DLog.logf("Closed playback session in %s.", new TimeSpan(stopwatchCreateStarted.stop()));
        if (trigger.getType() == type2) {
            doTrigger(Triggers.TEARDOWN_COMPLETE_TRIGGER);
        }
    }
}
