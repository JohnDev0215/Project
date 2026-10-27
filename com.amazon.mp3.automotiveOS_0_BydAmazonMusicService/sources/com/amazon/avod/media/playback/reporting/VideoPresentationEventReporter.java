package com.amazon.avod.media.playback.reporting;

import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.playback.PlaybackEventReporter;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface VideoPresentationEventReporter {
    @Nonnull
    PlaybackEventReporter getPlaybackReporter();

    String getUserWatchSessionId();

    void initialize(VideoPresentation videoPresentation, String str);
}
