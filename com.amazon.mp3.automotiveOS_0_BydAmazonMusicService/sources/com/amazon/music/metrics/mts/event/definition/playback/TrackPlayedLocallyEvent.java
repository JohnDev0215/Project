package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;
import com.amazon.music.metrics.mts.event.types.TerminationReason;

/* JADX INFO: loaded from: classes4.dex */
public class TrackPlayedLocallyEvent extends PlaybackThresholdEvent {
    public TrackPlayedLocallyEvent(TrackPlaybackInfo trackPlaybackInfo, long j, SelectionSourceInfo selectionSourceInfo, TerminationReason terminationReason, boolean z) {
        super("trackPlayedLocally", 4L, trackPlaybackInfo, j, selectionSourceInfo, terminationReason);
        setIsOnDemandQueue(z);
    }

    public TrackPlayedLocallyEvent(TrackPlaybackInfo trackPlaybackInfo, long j, SelectionSourceInfo selectionSourceInfo, TerminationReason terminationReason, boolean z, boolean z2) {
        this(trackPlaybackInfo, j, selectionSourceInfo, terminationReason, z);
        setIsShufflePlay(z2);
    }
}
