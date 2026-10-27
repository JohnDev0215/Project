package com.amazon.music.media.playback.impl;

import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.RepeatMode;

/* JADX INFO: loaded from: classes4.dex */
public interface IOnPlaybackCommandListener {
    void onFastForward(PlaybackController playbackController);

    void onNormalSpeed(PlaybackController playbackController);

    void onPause(PlaybackController playbackController);

    void onPlay(PlaybackController playbackController, long j);

    void onResolveMaxConcurrency(PlaybackController playbackController, String str, boolean z, long j);

    void onRestartMediaItem(PlaybackController playbackController);

    void onRevertThumbsDown(PlaybackController playbackController);

    void onRevertThumbsUp(PlaybackController playbackController);

    void onRewind(PlaybackController playbackController);

    void onSeek(PlaybackController playbackController, long j, int i);

    void onSetPlaybackSpeed(PlaybackController playbackController, double d);

    void onSetRepeatMode(PlaybackController playbackController, RepeatMode repeatMode);

    void onSetShuffled(PlaybackController playbackController, boolean z);

    void onSkip(PlaybackController playbackController, int i, ChangeReason changeReason);

    void onSkipToIndex(PlaybackController playbackController, int i);

    void onStop(PlaybackController playbackController, ChangeReason changeReason);

    void onThumbsDown(PlaybackController playbackController);

    void onThumbsUp(PlaybackController playbackController);
}
