package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.EventTimeConverter;
import com.amazon.music.metrics.mts.event.types.PlaybackEntityType;
import com.amazon.music.metrics.mts.event.types.PlaybackSourceType;
import com.amazon.music.metrics.mts.event.types.ResourceType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;

/* JADX INFO: loaded from: classes4.dex */
public class GenericPlaybackCompletedEvent extends GenericPlaybackEvent {
    public GenericPlaybackCompletedEvent(AudioVideoToggleMetricsInfo audioVideoToggleMetricsInfo, TrackPlaybackInfo trackPlaybackInfo, SelectionSourceInfo selectionSourceInfo, long j, long j2, String str, String str2, long j3, boolean z, boolean z2, boolean z3, ResourceType resourceType, PlaybackSourceType playbackSourceType, PlaybackEntityType playbackEntityType) {
        super("playbackCompleted", 1L, playbackSourceType, playbackEntityType, trackPlaybackInfo.getAsin());
        addAttribute("isVoiceInitiated", String.valueOf(z));
        addAttribute("isExplicitLanguageFilterOn", String.valueOf(z2));
        addAttribute("isShufflePlay", z3);
        addAttribute("resourceType", resourceType.getMetricsValue());
        addAttribute("mediaPlayerName", str2);
        addAttribute(PageUriUtils.SOURCE_QUERY_PARAM, str);
        addAttribute("contentSubscriptionMode", trackPlaybackInfo.getContentSubscriptionMode());
        addAttribute("subContentSubscriptionMode", trackPlaybackInfo.getSubContentSubscriptionMode());
        addAttribute("selectionSourceType", selectionSourceInfo.getSelectionSourceType().getMetricsValue());
        addAttribute("selectionSourceId", selectionSourceInfo.getSelectionSourceId());
        addAttribute("selectionSourceSessionId", selectionSourceInfo.getSelectionSourceSessionId());
        addAttribute("durationSeconds", getDurationSeconds(j, j2));
        addAttribute("trackProgress", new EventTimeConverter().convertToRoundedSeconds(j3));
    }
}
