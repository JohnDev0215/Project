package com.amazon.avod.media.playback;

import com.amazon.avod.media.error.MediaErrorCode;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface VideoPresentationEventListener {
    void onCompletion(VideoPresentation videoPresentation);

    void onError(VideoPresentation videoPresentation, MediaErrorCode mediaErrorCode);

    void onPrepared(VideoPresentation videoPresentation, PlaybackDataSource playbackDataSource);

    void onStarted(@Nonnull VideoPresentation videoPresentation, @Nonnull PlaybackDataSource playbackDataSource);

    void onTerminated();
}
