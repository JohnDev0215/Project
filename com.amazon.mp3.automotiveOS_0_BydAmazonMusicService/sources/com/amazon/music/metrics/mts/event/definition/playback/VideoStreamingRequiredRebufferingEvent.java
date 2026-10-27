package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.metrics.mts.event.types.BitRate;
import com.amazon.music.metrics.mts.event.types.CacheHitStatus;
import com.amazon.music.metrics.mts.event.types.MediaPlayerType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;

/* JADX INFO: loaded from: classes4.dex */
public class VideoStreamingRequiredRebufferingEvent extends StreamingRequiredRebufferingEvent {
    public VideoStreamingRequiredRebufferingEvent(TrackPlaybackInfo trackPlaybackInfo, MediaPlayerType mediaPlayerType, String str, long j, long j2, int i, int i2, int i3, CacheHitStatus cacheHitStatus, BitRate bitRate, SelectionSourceInfo selectionSourceInfo, long j3) {
        super(trackPlaybackInfo, mediaPlayerType, str, j, j2, i, i2, i3, cacheHitStatus, bitRate, selectionSourceInfo, j3, "videoStreamingRequiredReBuffering");
    }
}
