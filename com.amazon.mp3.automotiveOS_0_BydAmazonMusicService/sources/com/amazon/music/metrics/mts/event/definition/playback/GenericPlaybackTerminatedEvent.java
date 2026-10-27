package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.EventTimeConverter;
import com.amazon.music.metrics.mts.event.types.PlaybackEntityType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.PlaybackSourceType;
import com.amazon.music.metrics.mts.event.types.ResourceType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;
import com.amazon.music.metrics.mts.event.types.TerminationReason;

/* JADX INFO: loaded from: classes4.dex */
public class GenericPlaybackTerminatedEvent extends GenericPlaybackEvent {
    public GenericPlaybackTerminatedEvent(AudioVideoToggleMetricsInfo audioVideoToggleMetricsInfo, TrackPlaybackInfo trackPlaybackInfo, SelectionSourceInfo selectionSourceInfo, String str, boolean z, long j, long j2, PlaybackEntityType playbackEntityType, PlaybackSourceType playbackSourceType, String str2, TerminationReason terminationReason, ResourceType resourceType, PlaybackPageType playbackPageType, String str3, boolean z2, boolean z3, long j3) {
        super("playbackTerminated", 1L, playbackSourceType, playbackEntityType, trackPlaybackInfo.getAsin());
        addAttribute("isVoiceInitiated", z2);
        addAttribute("isExplicitLanguageFilterOn", z);
        addAttribute("isShufflePlay", z3);
        addAttribute("mediaPlayerName", str3);
        addAttribute("resourceType", resourceType.getMetricsValue());
        addAttribute("pageType", playbackPageType.getMetricsValue());
        addAttribute("terminationReason", terminationReason.getMetricsValue());
        addAttribute(PageUriUtils.SOURCE_QUERY_PARAM, str);
        addAttribute("contentSubscriptionMode", trackPlaybackInfo.getContentSubscriptionMode());
        addAttribute("subContentSubscriptionMode", trackPlaybackInfo.getSubContentSubscriptionMode());
        addAttribute("selectionSourceType", selectionSourceInfo.getSelectionSourceType().getMetricsValue());
        addAttribute("selectionSourceId", selectionSourceInfo.getSelectionSourceId());
        addAttribute("selectionSourceSessionId", selectionSourceInfo.getSelectionSourceSessionId());
        addAttribute(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, trackPlaybackInfo.getEntityType());
        addAttribute("trackProgress", new EventTimeConverter().convertToRoundedSeconds(j3));
        if (terminationReason == TerminationReason.ERROR) {
            addAttribute("errorMessage", str2);
        }
        addAttribute("durationSeconds", getDurationSeconds(j2, j));
    }
}
