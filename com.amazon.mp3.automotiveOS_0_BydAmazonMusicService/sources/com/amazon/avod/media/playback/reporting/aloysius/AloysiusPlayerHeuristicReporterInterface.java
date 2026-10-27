package com.amazon.avod.media.playback.reporting.aloysius;

import com.amazon.avod.playback.event.playback.PlaybackStartEvent;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface AloysiusPlayerHeuristicReporterInterface {
    void handlePlaybackStartEvent(@Nonnull PlaybackStartEvent playbackStartEvent);
}
