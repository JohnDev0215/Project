package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;

/* JADX INFO: loaded from: classes4.dex */
public class AdStreamingInitiatedEvent extends StreamingInitiatedEvent {
    public AdStreamingInitiatedEvent(TrackPlaybackInfo trackPlaybackInfo, SelectionSourceInfo selectionSourceInfo, String str, boolean z, long j, String str2) {
        super(trackPlaybackInfo, selectionSourceInfo, str, z, "adStreamingInitiated");
        addAttribute("initialPlaybackDelayMilliseconds", j);
        addAttribute("initiationReason", str2);
        setSpecificEventVersion(1L);
    }
}
