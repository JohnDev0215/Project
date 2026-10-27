package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.metrics.mts.event.EventTimeConverter;
import com.amazon.music.metrics.mts.event.types.PlaybackEntityType;
import com.amazon.music.metrics.mts.event.types.PlaybackSourceType;
import com.amazon.music.skyfire.models.TetheredMessageKey;

/* JADX INFO: loaded from: classes4.dex */
public class GenericPlaybackStateEvent extends GenericPlaybackEvent {
    public GenericPlaybackStateEvent(AudioVideoToggleMetricsInfo audioVideoToggleMetricsInfo, String str, boolean z, long j, PlaybackSourceType playbackSourceType, PlaybackEntityType playbackEntityType) {
        super(TetheredMessageKey.playbackState, 1L, playbackSourceType, playbackEntityType, str);
        addAttribute("isCCDisplayed", z);
        addAttribute("trackProgress", new EventTimeConverter().convertToRoundedSeconds(j));
    }
}
