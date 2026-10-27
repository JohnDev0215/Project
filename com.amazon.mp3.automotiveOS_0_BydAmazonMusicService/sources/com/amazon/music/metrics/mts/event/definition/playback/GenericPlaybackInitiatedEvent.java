package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackEntityType;
import com.amazon.music.metrics.mts.event.types.PlaybackSourceType;
import com.amazon.music.metrics.mts.event.types.ResourceType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;

/* JADX INFO: loaded from: classes4.dex */
public class GenericPlaybackInitiatedEvent extends GenericPlaybackEvent {
    public GenericPlaybackInitiatedEvent(AudioVideoToggleMetricsInfo audioVideoToggleMetricsInfo, TrackPlaybackInfo trackPlaybackInfo, SelectionSourceInfo selectionSourceInfo, String str, boolean z, long j, boolean z2, boolean z3, String str2, ResourceType resourceType, PlaybackSourceType playbackSourceType, PlaybackEntityType playbackEntityType) {
        super("playbackInitiated", 1L, playbackSourceType, playbackEntityType, trackPlaybackInfo.getAsin());
        addAttribute("isVoiceInitiated", String.valueOf(z));
        addAttribute("isExplicitLanguageFilterOn", String.valueOf(z2));
        addAttribute("isShufflePlay", z3);
        addAttribute("initialPlaybackDelayMilliseconds", j);
        addAttribute("mediaPlayerName", str2);
        addAttribute("resourceType", resourceType.getMetricsValue());
        addAttribute(PageUriUtils.SOURCE_QUERY_PARAM, str);
        addAttribute("contentSubscriptionMode", trackPlaybackInfo.getContentSubscriptionMode());
        addAttribute("subContentSubscriptionMode", trackPlaybackInfo.getSubContentSubscriptionMode());
        addAttribute("selectionSourceType", selectionSourceInfo.getSelectionSourceType().getMetricsValue());
        addAttribute("selectionSourceId", selectionSourceInfo.getSelectionSourceId());
        addAttribute("selectionSourceSessionId", selectionSourceInfo.getSelectionSourceSessionId());
        addAttribute(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, trackPlaybackInfo.getEntityType());
    }
}
