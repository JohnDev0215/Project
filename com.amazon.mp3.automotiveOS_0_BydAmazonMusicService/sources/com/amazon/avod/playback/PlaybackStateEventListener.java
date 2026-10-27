package com.amazon.avod.playback;

import com.amazon.avod.media.TimeSpan;

/* JADX INFO: loaded from: classes2.dex */
public interface PlaybackStateEventListener {
    void onPause(PlaybackEventContext playbackEventContext);

    default void onRestartSuccess() {
    }

    void onResume(PlaybackEventContext playbackEventContext);

    void onSeekEnd(PlaybackEventContext playbackEventContext);

    void onSeekStart(TimeSpan timeSpan, PlaybackEventContext playbackEventContext);

    void onStart(PlaybackEventContext playbackEventContext);

    void onStop(PlaybackEventContext playbackEventContext);
}
