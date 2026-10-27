package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class MainPlayerTriggeredOnPrepared extends SimpleTask {
    private final PlaybackSessionContext mPlaybackSessionContext;
    private final PlaybackSessionResources mPlaybackSessionResources;

    public MainPlayerTriggeredOnPrepared(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSessionResources playbackSessionResources, @Nonnull PlaybackSessionContext playbackSessionContext) {
        super(eventBus, exceptionCallback);
        this.mPlaybackSessionResources = (PlaybackSessionResources) Preconditions.checkNotNull(playbackSessionResources, "playbackSessionResources");
        this.mPlaybackSessionContext = (PlaybackSessionContext) Preconditions.checkNotNull(playbackSessionContext, "playbackSessionContext");
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.BaseTask
    protected void execute() throws MediaException {
        if (!this.mPlaybackSessionContext.getAdEnabledPlaybackManager().isAdPlayerPrepared()) {
            DLog.logf("MainPlayerTriggeredOnPrepared calling onPrepared");
            this.mPlaybackSessionResources.getVideoPlayerLifecyleEventHandler().onPrepared(this.mPlaybackSessionContext.getDataSource());
        } else {
            DLog.logf("MainPlayerTriggeredOnPrepared onPrepared already called from the ad path");
        }
    }
}
