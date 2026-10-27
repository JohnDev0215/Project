package com.amazon.music.media.playback.config;

import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.PlaybackProvider;

/* JADX INFO: loaded from: classes4.dex */
public interface PlaybackPersistenceProvider {
    void restore();

    void setPlaybackController(PlaybackController playbackController);

    void setPlaybackProvider(PlaybackProvider playbackProvider);
}
