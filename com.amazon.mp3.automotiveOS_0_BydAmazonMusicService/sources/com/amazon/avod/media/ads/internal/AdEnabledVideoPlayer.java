package com.amazon.avod.media.ads.internal;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.media.playback.VideoPlayerProxy;
import com.amazon.avod.playback.TimelineManager;
import com.amazon.avod.playback.player.actions.SeekAction;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdEnabledVideoPlayer extends VideoPlayerProxy {
    private AdEnabledPlaybackManager mAdEnabledPlaybackManager;
    private boolean mIsSSAIEnabled;
    private VideoPlayer mPlayer;
    private TimelineManager mTimelineManager;

    @Deprecated
    public AdEnabledVideoPlayer() {
    }

    @Deprecated
    public void initialize(@Nonnull VideoPlayer videoPlayer, @Nonnull AdEnabledPlaybackManager adEnabledPlaybackManager, @Nullable TimelineManager timelineManager) {
        this.mPlayer = (VideoPlayer) Preconditions.checkNotNull(videoPlayer);
        this.mAdEnabledPlaybackManager = (AdEnabledPlaybackManager) Preconditions.checkNotNull(adEnabledPlaybackManager);
        this.mTimelineManager = timelineManager;
        this.mIsSSAIEnabled = AdsConfig.getInstance().isSSAIEnabled();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerProxy
    protected VideoPlayer getDelegate() {
        Preconditions.checkState(this.mPlayer != null, "Initialize must be called before the delegate can be retrieved.");
        return this.mPlayer;
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerProxy, com.amazon.avod.media.playback.VideoPlayer
    public void start() {
        Preconditions.checkState(this.mPlayer != null, "Initialize must be called before start.");
        this.mAdEnabledPlaybackManager.play();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerProxy, com.amazon.avod.media.playback.VideoPlayer
    public void pause() {
        Preconditions.checkState(this.mPlayer != null, "Initialize must be called before pause.");
        this.mAdEnabledPlaybackManager.pause();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerProxy, com.amazon.avod.media.playback.VideoPlayer
    public void seekTo(long j) {
        Preconditions.checkState(this.mPlayer != null, "Initialize must be called before seekTo.");
        this.mAdEnabledPlaybackManager.seekTo(TimeSpan.fromMilliseconds(j));
    }

    @Override // com.amazon.avod.media.playback.VideoPlayer
    public void seekToManifestPosition(long j, @Nonnull SeekAction.SeekCause seekCause) {
        this.mAdEnabledPlaybackManager.seekTo(TimeSpan.fromMilliseconds(j));
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerProxy, com.amazon.avod.media.playback.VideoPlayer
    public long getDuration() {
        TimelineManager timelineManager;
        Preconditions.checkState(this.mPlayer != null, "Initialize must be called before getDuration.");
        if (this.mIsSSAIEnabled && (timelineManager = this.mTimelineManager) != null) {
            return timelineManager.getPlaybackDuration().getTotalMilliseconds();
        }
        return this.mPlayer.getDuration();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerProxy, com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentPosition() {
        Preconditions.checkState(this.mPlayer != null, "Initialize must be called before getCurrentPosition.");
        if (this.mIsSSAIEnabled) {
            return this.mTimelineManager.getNormalisedRelativePosition(TimeSpan.fromMilliseconds(this.mPlayer.getCurrentPosition()).getTotalNanoSeconds()).getTotalMilliseconds();
        }
        return this.mPlayer.getCurrentPosition();
    }

    @Override // com.amazon.avod.media.playback.VideoPlayerProxy, com.amazon.avod.media.playback.VideoPlayer
    public long getCurrentAbsolutePosition() {
        return this.mPlayer.getCurrentAbsolutePosition();
    }
}
