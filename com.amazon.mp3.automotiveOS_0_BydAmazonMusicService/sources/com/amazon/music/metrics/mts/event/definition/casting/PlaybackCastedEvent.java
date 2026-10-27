package com.amazon.music.metrics.mts.event.definition.casting;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackCastedEvent extends CastingEvent {
    public PlaybackCastedEvent(String str, long j, long j2) {
        super("playbackCasted", 1L, str, j2);
        addAttribute("playbackCastedLatencyInMS", j);
    }
}
