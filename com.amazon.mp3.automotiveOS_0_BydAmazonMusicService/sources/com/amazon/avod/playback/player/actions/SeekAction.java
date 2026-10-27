package com.amazon.avod.playback.player.actions;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.player.states.PlaybackState;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SeekAction extends Action {
    private PlaybackState mOutgoingState;
    private final SeekCause mSeekCause;
    private final TimeSpan mToTime;

    public enum SeekCause {
        MANUAL,
        LIVE_OUT_OF_WINDOW,
        LIVE_OUT_OF_WINDOW_BUFFER,
        STREAM_SEEK_OVER,
        AUDIO_TRACK_RECREATION,
        VIDEO_SURFACE_RECREATION,
        AD_SEEK_OVER,
        AD_CHAPTERING,
        AD_SKIP
    }

    public SeekAction(TimeSpan timeSpan, PlaybackState playbackState, @Nonnull SeekCause seekCause) {
        this.mToTime = timeSpan;
        this.mOutgoingState = playbackState;
        this.mSeekCause = (SeekCause) Preconditions.checkNotNull(seekCause, "seekCause");
    }

    public TimeSpan getToTime() {
        return this.mToTime;
    }

    public PlaybackState getOutgoingState() {
        return this.mOutgoingState;
    }

    public void setOutgoingState(PlaybackState playbackState) {
        this.mOutgoingState = playbackState;
    }

    @Override // com.amazon.avod.playback.player.actions.Action
    public ActionType getActionType() {
        return ActionType.Seek;
    }

    @Nonnull
    public SeekCause getSeekCause() {
        return this.mSeekCause;
    }
}
