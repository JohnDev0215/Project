package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.metrics.mts.event.exception.CirrusInvalidDataException;
import com.amazon.music.metrics.mts.event.types.MediaPlayerType;
import com.amazon.music.metrics.mts.event.types.PlaybackInitiationInfo;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;
import com.amazon.music.metrics.mts.event.types.TerminationReason;

/* JADX INFO: loaded from: classes4.dex */
public class LocalPlaybackTerminatedEvent extends PlaybackTerminatedEvent {
    public LocalPlaybackTerminatedEvent(TrackPlaybackInfo trackPlaybackInfo, TerminationReason terminationReason, SelectionSourceInfo selectionSourceInfo, MediaPlayerType mediaPlayerType, long j, long j2, long j3, PlaybackInitiationInfo playbackInitiationInfo, boolean z, boolean z2) throws CirrusInvalidDataException {
        super("localPlaybackTerminated", 4L, trackPlaybackInfo, terminationReason, selectionSourceInfo, mediaPlayerType, j, j2, j3, playbackInitiationInfo);
        addAttribute("isExplicitLanguageFilterOn", z);
        setIsOnDemandQueue(z2);
    }
}
