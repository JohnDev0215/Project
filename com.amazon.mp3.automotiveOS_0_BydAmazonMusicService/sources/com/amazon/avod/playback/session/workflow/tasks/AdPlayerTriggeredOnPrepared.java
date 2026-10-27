package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.internal.AdBreakSelector;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AdPlayerTriggeredOnPrepared extends SimpleTask {
    private final AdBreakSelector mBreakSelector;
    private final boolean mIsSSAIEnabled;
    private final PlaybackSessionContext mPlaybackSessionContext;
    private final PlaybackSessionResources mPlaybackSessionResources;

    public AdPlayerTriggeredOnPrepared(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSessionResources playbackSessionResources, @Nonnull PlaybackSessionContext playbackSessionContext) {
        this(eventBus, exceptionCallback, playbackSessionResources, playbackSessionContext, AdsConfig.getInstance().isSSAIEnabled());
    }

    public AdPlayerTriggeredOnPrepared(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSessionResources playbackSessionResources, @Nonnull PlaybackSessionContext playbackSessionContext, boolean z) {
        super(eventBus, exceptionCallback);
        this.mBreakSelector = new AdBreakSelector();
        this.mPlaybackSessionResources = (PlaybackSessionResources) Preconditions.checkNotNull(playbackSessionResources, "playbackSessionResources");
        this.mPlaybackSessionContext = (PlaybackSessionContext) Preconditions.checkNotNull(playbackSessionContext, "playbackSessionContext");
        this.mIsSSAIEnabled = z;
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.BaseTask
    protected void execute() throws MediaException {
        if (!hasPreloadedPreroll() || this.mIsSSAIEnabled) {
            return;
        }
        DLog.logf("AdPlayerTriggeredOnPrepared, pre-roll is present and ready - calling onPrepared");
        this.mPlaybackSessionResources.getVideoPlayerLifecyleEventHandler().onPrepared(this.mPlaybackSessionContext.getDataSource());
    }

    private boolean hasPreloadedPreroll() {
        TimeSpan startTime = ((VideoSpecification) Preconditions.checkNotNull(this.mPlaybackSessionContext.getVideoSpec(), "VideoSpec")).getStartTime();
        AdBreak adBreakSelectNextBreak = this.mBreakSelector.selectNextBreak(this.mPlaybackSessionContext.getAdPlan(), startTime);
        if (startTime.isZero() && adBreakSelectNextBreak != null && adBreakSelectNextBreak.getStartTime().equals(TimeSpan.ZERO)) {
            return !adBreakSelectNextBreak.getClips().isEmpty();
        }
        return false;
    }
}
