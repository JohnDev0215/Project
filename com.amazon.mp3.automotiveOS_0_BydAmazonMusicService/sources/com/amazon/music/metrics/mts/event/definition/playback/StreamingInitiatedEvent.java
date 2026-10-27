package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;

/* JADX INFO: loaded from: classes4.dex */
public class StreamingInitiatedEvent extends MTSEvent {
    public StreamingInitiatedEvent(TrackPlaybackInfo trackPlaybackInfo, SelectionSourceInfo selectionSourceInfo, String str, boolean z, boolean z2, boolean z3) {
        super("streamingInitiated", 3L);
        addEventAttributes(trackPlaybackInfo, selectionSourceInfo, str);
        addAttribute("isExplicitLanguageFilterOn", z);
        addAttribute("isOnDemandQueue", z2);
        addAttribute("isShufflePlay", z3);
    }

    protected StreamingInitiatedEvent(TrackPlaybackInfo trackPlaybackInfo, SelectionSourceInfo selectionSourceInfo, String str, boolean z, String str2) {
        super(str2, 3L);
        addEventAttributes(trackPlaybackInfo, selectionSourceInfo, str);
        addAttribute("isExplicitLanguageFilterOn", z);
    }

    private void addEventAttributes(TrackPlaybackInfo trackPlaybackInfo, SelectionSourceInfo selectionSourceInfo, String str) {
        addAttribute("cdObjectId", trackPlaybackInfo.getCloudDriveObjectId());
        addAttribute("asin", trackPlaybackInfo.getAsin());
        addAttribute(PageUriUtils.SOURCE_QUERY_PARAM, str);
        addAttribute("contentSubscriptionMode", trackPlaybackInfo.getContentSubscriptionMode());
        addAttribute("subContentSubscriptionMode", trackPlaybackInfo.getSubContentSubscriptionMode());
        addAttribute("selectionSourceType", selectionSourceInfo.getSelectionSourceType().getMetricsValue());
        addAttribute("selectionSourceId", selectionSourceInfo.getSelectionSourceId());
        if (trackPlaybackInfo.getEntityType() != null) {
            addAttribute(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, trackPlaybackInfo.getEntityType());
        }
    }
}
