package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.service.AVODServiceConfig;
import com.amazon.avod.playback.event.playback.PlaybackPerformanceEvent;
import com.amazon.avod.playback.session.PlaybackSession;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class MonitorPerformanceEvents extends AsyncResponseBasedTask {
    private final AVODServiceConfig mAVODServiceConfig;
    private final PlaybackSessionContext mPlaybackSessionContext;
    private final PlaybackSessionResources mPlaybackSessionResources;

    public MonitorPerformanceEvents(@Nonnull PlaybackSession playbackSession, @Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback) {
        this(playbackSession, eventBus, AVODServiceConfig.getInstance(), exceptionCallback);
    }

    MonitorPerformanceEvents(@Nonnull PlaybackSession playbackSession, @Nonnull EventBus eventBus, @Nonnull AVODServiceConfig aVODServiceConfig, @Nonnull ExceptionCallback exceptionCallback) {
        super(eventBus, exceptionCallback);
        this.mPlaybackSessionResources = playbackSession.getResources();
        this.mPlaybackSessionContext = playbackSession.getContext();
        this.mAVODServiceConfig = aVODServiceConfig;
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.BaseTask
    public void execute() throws MediaException {
        ((PlaybackEventTransport) Preconditions.checkNotNull(this.mPlaybackSessionResources.getPlaybackEventTransport(), "playbackEventTransport")).registerEventBusHandler(this);
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

    @Override // com.amazon.avod.playback.session.workflow.tasks.AsyncResponseBasedTask
    public void executeAbort() {
        this.mPlaybackSessionResources.getPlaybackEventTransport().unregisterEventBusHandler(this);
    }
}
