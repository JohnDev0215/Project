package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;

/* JADX INFO: loaded from: classes4.dex */
public class GhostListeningInterruptedEvent extends MTSEvent {
    public GhostListeningInterruptedEvent(long j, long j2, SelectionSourceInfo selectionSourceInfo) {
        super("ghostListeningInterrupted", 1L);
        addEventAttributes(j, j2, selectionSourceInfo);
    }

    private void addEventAttributes(long j, long j2, SelectionSourceInfo selectionSourceInfo) {
        addAttribute("thresholdHitSeconds", j);
        addAttribute("secondsSinceLastActivity", j2);
        addAttribute("selectionSourceType", selectionSourceInfo.getSelectionSourceType().getMetricValue());
        addAttribute("selectionSourceId", selectionSourceInfo.getSelectionSourceId());
        addAttribute("selectionSourceSessionId", selectionSourceInfo.getSelectionSourceSessionId());
    }
}
