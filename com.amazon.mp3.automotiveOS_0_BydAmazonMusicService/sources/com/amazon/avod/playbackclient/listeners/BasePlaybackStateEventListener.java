package com.amazon.avod.playbackclient.listeners;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackStateEventListener;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BasePlaybackStateEventListener implements PlaybackStateEventListener {
    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onPause(PlaybackEventContext playbackEventContext) {
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onRestartSuccess() {
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onResume(PlaybackEventContext playbackEventContext) {
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onSeekEnd(PlaybackEventContext playbackEventContext) {
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onSeekStart(TimeSpan timeSpan, PlaybackEventContext playbackEventContext) {
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onStart(PlaybackEventContext playbackEventContext) {
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onStop(PlaybackEventContext playbackEventContext) {
    }
}
