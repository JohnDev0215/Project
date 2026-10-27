package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.EventTimeConverter;
import com.amazon.music.metrics.mts.event.types.PlaybackEntityType;
import com.amazon.music.metrics.mts.event.types.PlaybackSourceType;
import com.amazon.music.metrics.util.RangeValidator;

/* JADX INFO: loaded from: classes4.dex */
public class GenericPlaybackEvent extends MTSEvent {
    public GenericPlaybackEvent(String str, long j, PlaybackSourceType playbackSourceType, PlaybackEntityType playbackEntityType, String str2) {
        super(str, j);
        addAttribute("playbackEntityType", playbackEntityType.getMetricsValue());
        addAttribute("playbackSourceType", playbackSourceType.getMetricsValue());
        addAttribute("asin", str2);
    }

    protected long getDurationSeconds(long j, long j2) {
        return new EventTimeConverter().convertToRoundedSeconds(getDurationMilliseconds(j, j2));
    }

    private long getDurationMilliseconds(long j, long j2) {
        return new RangeValidator().withinRange(j, 0L, j2) ? j : j2;
    }
}
