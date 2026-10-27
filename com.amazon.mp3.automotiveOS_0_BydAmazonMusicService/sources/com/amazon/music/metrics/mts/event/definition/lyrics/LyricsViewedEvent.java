package com.amazon.music.metrics.mts.event.definition.lyrics;

import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.types.LyricsViewSource;

/* JADX INFO: loaded from: classes4.dex */
abstract class LyricsViewedEvent extends MTSEvent {
    public LyricsViewedEvent(LyricsViewSource lyricsViewSource, String str, String str2, String str3, long j) {
        super("lyricsViewed", 2L);
        addEventAttributes(lyricsViewSource, str, str2, str3, j);
    }

    protected void addEventAttributes(LyricsViewSource lyricsViewSource, String str, String str2, String str3, long j) {
        addAttribute("asin", str);
        addAttribute("pageType", lyricsViewSource.getMetricValue());
        addAttribute("characterCount", j);
        addAttribute("contentSubscriptionMode", str2);
        if (str3 != null) {
            addAttribute("subContentSubscriptionMode", str3);
        }
    }
}
