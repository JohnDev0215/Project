package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.event.PrerollCompleteEvent;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ConsumePrerolls extends AsyncResponseBasedTask {
    private final PlaybackSessionContext mPlaybackSessionContext;
    private final PlaybackSessionResources mPlaybackSessionResources;
    private boolean mPrerollComplete;
    private final Object mSignalMutex;

    public ConsumePrerolls(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSessionResources playbackSessionResources, @Nonnull PlaybackSessionContext playbackSessionContext) {
        super(eventBus, exceptionCallback);
        this.mPrerollComplete = false;
        this.mSignalMutex = new Object();
        this.mPlaybackSessionResources = (PlaybackSessionResources) Preconditions.checkNotNull(playbackSessionResources, "playbackSessionResources");
        this.mPlaybackSessionContext = (PlaybackSessionContext) Preconditions.checkNotNull(playbackSessionContext, "playbackSessionContext");
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.BaseTask
    protected void execute() throws MediaException {
        synchronized (this.mSignalMutex) {
            if (this.mPrerollComplete) {
                return;
            }
            this.mPlaybackSessionResources.getPlaybackEventTransport().registerEventBusHandler(this);
            ((AdEnabledPlaybackManager) Preconditions.checkNotNull(this.mPlaybackSessionContext.getAdEnabledPlaybackManager(), "adEnabledPlaybackManager")).launchPlayback();
        }
    }

    @Subscribe
    public void prerollCompleteEvent(PrerollCompleteEvent prerollCompleteEvent) {
        synchronized (this.mSignalMutex) {
            if (this.mPrerollComplete) {
                return;
            }
            setSignalArrived();
            notifyTaskSuccess();
        }
    }

    @Override // com.amazon.avod.playback.session.workflow.tasks.AsyncResponseBasedTask
    public void executeAbort() {
        synchronized (this.mSignalMutex) {
            if (this.mPrerollComplete) {
                return;
            }
            setSignalArrived();
        }
    }

    private void setSignalArrived() {
        this.mPrerollComplete = true;
        this.mPlaybackSessionResources.getPlaybackEventTransport().unregisterEventBusHandler(this);
    }
}
