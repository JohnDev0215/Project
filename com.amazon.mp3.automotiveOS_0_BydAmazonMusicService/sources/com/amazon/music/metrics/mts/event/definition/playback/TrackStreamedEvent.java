package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;
import com.amazon.music.metrics.mts.event.types.TerminationReason;

/* JADX INFO: loaded from: classes4.dex */
public class TrackStreamedEvent extends PlaybackThresholdEvent {
    public TrackStreamedEvent(TrackPlaybackInfo trackPlaybackInfo, long j, SelectionSourceInfo selectionSourceInfo, boolean z, TerminationReason terminationReason, boolean z2) {
        super("trackStreamed", 5L, trackPlaybackInfo, j, selectionSourceInfo, terminationReason);
        addAttribute("isExplicitLanguageFilterOn", z);
        if (trackPlaybackInfo.getSelectionSourceType() != null) {
            addAttribute("selectionSourceType", trackPlaybackInfo.getSelectionSourceType());
        }
        if (trackPlaybackInfo.getEntityType() != null) {
            addAttribute(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, trackPlaybackInfo.getEntityType());
        }
        setIsOnDemandQueue(z2);
    }

    public TrackStreamedEvent(TrackPlaybackInfo trackPlaybackInfo, long j, SelectionSourceInfo selectionSourceInfo, boolean z, TerminationReason terminationReason, boolean z2, boolean z3) {
        this(trackPlaybackInfo, j, selectionSourceInfo, z, terminationReason, z2);
        setIsShufflePlay(z3);
    }

    protected TrackStreamedEvent(TrackPlaybackInfo trackPlaybackInfo, long j, SelectionSourceInfo selectionSourceInfo, boolean z, String str) {
        super(str, 5L, trackPlaybackInfo, j, selectionSourceInfo);
        addAttribute("isExplicitLanguageFilterOn", z);
        if (trackPlaybackInfo.getSelectionSourceType() != null) {
            addAttribute("selectionSourceType", trackPlaybackInfo.getSelectionSourceType());
        }
        if (trackPlaybackInfo.getEntityType() != null) {
            addAttribute(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, trackPlaybackInfo.getEntityType());
        }
    }
}
