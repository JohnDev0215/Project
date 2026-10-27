package com.amazon.music.metrics.mts.event.definition.lyrics;

import com.amazon.music.metrics.mts.event.types.LyricsViewSource;
import com.amazon.music.metrics.mts.event.types.LyricsViewType;

/* JADX INFO: loaded from: classes4.dex */
public class LyricsViewedNowPlayingEvent extends LyricsViewedEvent {
    public LyricsViewedNowPlayingEvent(String str, String str2, String str3, long j, LyricsViewType lyricsViewType, long j2) {
        super(LyricsViewSource.NOW_PLAYING, str, str2, str3, j);
        addEventAttributes(lyricsViewType, j2);
    }

    private void addEventAttributes(LyricsViewType lyricsViewType, long j) {
        addAttribute("refTag", lyricsViewType.getMetricsValue());
        addAttribute("durationMilliSeconds", j);
    }
}
