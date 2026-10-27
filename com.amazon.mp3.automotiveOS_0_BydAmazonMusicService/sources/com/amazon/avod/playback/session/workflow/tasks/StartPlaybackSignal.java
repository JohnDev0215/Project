package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.event.PlaybackLaunchedEvent;
import com.amazon.avod.playback.session.PlaybackSession;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class StartPlaybackSignal extends AsyncResponseBasedTask {
    private final PlaybackSessionContext mPlaybackSessionContext;
    private final PlaybackSessionResources mPlaybackSessionResources;
    private final Object mSignalMutex;
    private boolean mStartPlaybackSignalArrived;

    public StartPlaybackSignal(@Nonnull PlaybackSession playbackSession, @Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback) {
        super(eventBus, exceptionCallback);
        this.mStartPlaybackSignalArrived = false;
        this.mSignalMutex = new Object();
        this.mPlaybackSessionResources = playbackSession.getResources();
        this.mPlaybackSessionContext = playbackSession.getContext();
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.BaseTask
    protected void execute() throws MediaException {
        synchronized (this.mSignalMutex) {
            if (this.mStartPlaybackSignalArrived || this.mPlaybackSessionContext.isPlaybackStarted()) {
                notifyTaskSuccess();
            } else {
                this.mPlaybackSessionResources.getPlaybackEventTransport().registerEventBusHandler(this);
            }
        }
    }

    @Subscribe
    public void playbackLaunchedEvent(PlaybackLaunchedEvent playbackLaunchedEvent) {
        synchronized (this.mSignalMutex) {
            if (this.mStartPlaybackSignalArrived) {
                return;
            }
            setSignalArrived();
            notifyTaskSuccess();
        }
    }

    @Override // com.amazon.avod.playback.session.workflow.tasks.AsyncResponseBasedTask
    public void executeAbort() {
        synchronized (this.mSignalMutex) {
            if (this.mStartPlaybackSignalArrived) {
                return;
            }
            setSignalArrived();
        }
    }

    private void setSignalArrived() {
        this.mStartPlaybackSignalArrived = true;
        this.mPlaybackSessionResources.getPlaybackEventTransport().unregisterEventBusHandler(this);
    }
}
