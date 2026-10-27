package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.metrics.mts.event.EventTimeConverter;
import com.amazon.music.metrics.mts.event.types.PlaybackEntityType;
import com.amazon.music.metrics.mts.event.types.PlaybackSourceType;
import com.amazon.music.metrics.mts.event.types.ResumeReason;

/* JADX INFO: loaded from: classes4.dex */
public class GenericPlaybackResumedEvent extends GenericPlaybackEvent {
    public GenericPlaybackResumedEvent(AudioVideoToggleMetricsInfo audioVideoToggleMetricsInfo, TrackPlaybackInfo trackPlaybackInfo, ResumeReason resumeReason, long j, long j2, PlaybackSourceType playbackSourceType, PlaybackEntityType playbackEntityType) {
        super("playbackResumed", 1L, playbackSourceType, playbackEntityType, trackPlaybackInfo.getAsin());
        addAttribute("resumeReason", resumeReason.getMetricsValue());
        addAttribute("initialPlaybackDelayMilliseconds", j);
        addAttribute("trackProgress", new EventTimeConverter().convertToRoundedSeconds(j2));
    }
}
