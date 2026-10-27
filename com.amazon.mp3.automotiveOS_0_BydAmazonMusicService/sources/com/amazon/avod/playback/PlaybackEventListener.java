package com.amazon.avod.playback;

import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.playback.PlaybackDataSource;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface PlaybackEventListener {
    void onCompletion();

    void onError(MediaErrorCode mediaErrorCode);

    void onPrepared(PlaybackDataSource playbackDataSource);

    void onStarted(@Nonnull PlaybackDataSource playbackDataSource);

    void onTerminated();
}
