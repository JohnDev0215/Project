package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.metrics.mts.event.exception.CirrusInvalidDataException;
import com.amazon.music.metrics.mts.event.types.BitRate;
import com.amazon.music.metrics.mts.event.types.CacheHitStatus;
import com.amazon.music.metrics.mts.event.types.MediaPlayerType;
import com.amazon.music.metrics.mts.event.types.PlaybackInitiationInfo;
import com.amazon.music.metrics.mts.event.types.ResourceType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;
import com.amazon.music.metrics.mts.event.types.TerminationReason;

/* JADX INFO: loaded from: classes4.dex */
public class AdStreamingTerminatedEvent extends StreamingTerminatedEvent {
    public AdStreamingTerminatedEvent(TrackPlaybackInfo trackPlaybackInfo, TerminationReason terminationReason, SelectionSourceInfo selectionSourceInfo, MediaPlayerType mediaPlayerType, long j, long j2, long j3, PlaybackInitiationInfo playbackInitiationInfo, long j4, ResourceType resourceType, long j5, CacheHitStatus cacheHitStatus, String str, BitRate bitRate, boolean z) throws CirrusInvalidDataException {
        super(trackPlaybackInfo, terminationReason, selectionSourceInfo, mediaPlayerType, j, j2, j3, playbackInitiationInfo, j4, resourceType, j5, cacheHitStatus, str, bitRate, z, "adStreamingTerminated");
        setSpecificEventVersion(1L);
    }
}
