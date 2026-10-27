package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.PlaybackEvent;
import com.amazon.avod.playback.subtitles.SubtitlesAggregator;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleEngineShutdownEvent extends PlaybackEvent {
    private final SubtitlesAggregator mSubtitlesAggregator;

    public SubtitleEngineShutdownEvent(@Nonnull TimeSpan timeSpan, @Nonnull SubtitlesAggregator subtitlesAggregator) {
        super(timeSpan);
        this.mSubtitlesAggregator = (SubtitlesAggregator) Preconditions.checkNotNull(subtitlesAggregator, "subtitlesAggregator");
    }

    @Nonnull
    public SubtitlesAggregator getSubtitlesAggregator() {
        return this.mSubtitlesAggregator;
    }
}
