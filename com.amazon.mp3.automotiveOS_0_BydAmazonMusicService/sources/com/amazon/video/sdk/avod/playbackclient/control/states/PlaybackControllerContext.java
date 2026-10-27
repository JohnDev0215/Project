package com.amazon.video.sdk.avod.playbackclient.control.states;

import com.amazon.video.sdk.avod.playbackclient.control.PlaybackProgressEventListener;
import com.amazon.video.sdk.avod.playbackclient.control.PlaybackSpeedScrubResultListener;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class PlaybackControllerContext {
    private boolean mIsPlaying;
    private final String mName;
    private PlaybackSpeedScrubResultListener mPlaybackSpeedScrubResultListener;
    private PlaybackProgressEventListener mProgressEventListener;
    private long mThumbPosition;

    public PlaybackControllerContext() {
        this("Unnamed");
    }

    public PlaybackControllerContext(@Nonnull String str) {
        this.mIsPlaying = false;
        this.mThumbPosition = -1L;
        this.mName = (String) Preconditions.checkNotNull(str);
    }

    public void setPlaybackProgressEventListener(@Nullable PlaybackProgressEventListener playbackProgressEventListener) {
        this.mProgressEventListener = playbackProgressEventListener;
    }

    public void setPlaybackScrubEventListener(@Nullable PlaybackSpeedScrubResultListener playbackSpeedScrubResultListener) {
        this.mPlaybackSpeedScrubResultListener = playbackSpeedScrubResultListener;
    }

    public void setThumbPosition(long j) {
        this.mThumbPosition = j;
    }

    public void notifyProgressChanged() {
        PlaybackProgressEventListener playbackProgressEventListener = this.mProgressEventListener;
        if (playbackProgressEventListener != null) {
            playbackProgressEventListener.onProgressChanged();
        }
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.mName + ")";
    }
}
