package com.amazon.music.media.playback;

import com.amazon.music.media.playback.impl.IOnPlaybackCommandListener;

/* JADX INFO: loaded from: classes4.dex */
public abstract class OnPlaybackCommandListener implements IOnPlaybackCommandListener {
    @Override // com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onFastForward(PlaybackController playbackController) {
    }

    @Override // com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onNormalSpeed(PlaybackController playbackController) {
    }

    @Override // com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onPause(PlaybackController playbackController) {
    }

    @Override // com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onPlay(PlaybackController playbackController, long j) {
    }

    @Override // com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onResolveMaxConcurrency(PlaybackController playbackController, String str, boolean z, long j) {
    }

    @Override // com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onRestartMediaItem(PlaybackController playbackController) {
    }

    @Override // com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onRevertThumbsDown(PlaybackController playbackController) {
    }

    @Override // com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onRevertThumbsUp(PlaybackController playbackController) {
    }

    @Override // com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onRewind(PlaybackController playbackController) {
    }

    @Override // com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onSeek(PlaybackController playbackController, long j, int i) {
    }

    @Override // com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onSetPlaybackSpeed(PlaybackController playbackController, double d) {
    }

    @Override // com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onSetRepeatMode(PlaybackController playbackController, RepeatMode repeatMode) {
    }

    @Override // com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onSetShuffled(PlaybackController playbackController, boolean z) {
    }

    @Override // com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onSkip(PlaybackController playbackController, int i, ChangeReason changeReason) {
    }

    @Override // com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onSkipToIndex(PlaybackController playbackController, int i) {
    }

    @Override // com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onStop(PlaybackController playbackController, ChangeReason changeReason) {
    }

    @Override // com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onThumbsDown(PlaybackController playbackController) {
    }

    @Override // com.amazon.music.media.playback.impl.IOnPlaybackCommandListener
    public void onThumbsUp(PlaybackController playbackController) {
    }
}
