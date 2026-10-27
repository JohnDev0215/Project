package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.PlaybackPerformanceReport;
import com.amazon.avod.playback.event.PlaybackEvent;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackPerformanceEvent extends PlaybackEvent {
    private final PlaybackPerformanceReport mPerformanceReport;

    public PlaybackPerformanceEvent(TimeSpan timeSpan, @Nullable PlaybackPerformanceReport playbackPerformanceReport) {
        super(timeSpan);
        this.mPerformanceReport = playbackPerformanceReport;
    }

    @Nullable
    public PlaybackPerformanceReport getPlaybackPerformanceReport() {
        return this.mPerformanceReport;
    }
}
