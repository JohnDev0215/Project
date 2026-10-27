package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.metrics.mts.event.EventTimeConverter;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;

/* JADX INFO: loaded from: classes4.dex */
public class AdStreamedEvent extends TrackStreamedEvent {
    public AdStreamedEvent(TrackPlaybackInfo trackPlaybackInfo, long j, SelectionSourceInfo selectionSourceInfo, boolean z) {
        super(trackPlaybackInfo, j, selectionSourceInfo, z, "adStreamed");
        addAttribute("entityProgressSeconds", new EventTimeConverter().convertToRoundedSeconds(j));
        setSpecificEventVersion(1L);
    }
}
