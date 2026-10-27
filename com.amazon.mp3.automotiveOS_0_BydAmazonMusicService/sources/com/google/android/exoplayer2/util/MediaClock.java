package com.google.android.exoplayer2.util;

import com.google.android.exoplayer2.PlaybackParameters;

/* JADX INFO: loaded from: classes6.dex */
public interface MediaClock {
    PlaybackParameters getPlaybackParameters();

    long getPositionUs();

    PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters);
}
