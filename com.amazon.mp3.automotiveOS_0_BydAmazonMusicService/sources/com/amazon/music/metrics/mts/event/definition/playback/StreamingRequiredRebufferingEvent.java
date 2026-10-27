package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.types.BitRate;
import com.amazon.music.metrics.mts.event.types.CacheHitStatus;
import com.amazon.music.metrics.mts.event.types.MediaPlayerType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;

/* JADX INFO: loaded from: classes4.dex */
public class StreamingRequiredRebufferingEvent extends MTSEvent {
    public StreamingRequiredRebufferingEvent(TrackPlaybackInfo trackPlaybackInfo, MediaPlayerType mediaPlayerType, String str, long j, long j2, int i, int i2, int i3, CacheHitStatus cacheHitStatus, BitRate bitRate, SelectionSourceInfo selectionSourceInfo, long j3) {
        super("streamingRequiredReBuffering", 2L);
        addEventAttributes(trackPlaybackInfo, mediaPlayerType, str, j, j2, i, i2, i3, cacheHitStatus, bitRate, selectionSourceInfo, j3);
    }

    protected StreamingRequiredRebufferingEvent(TrackPlaybackInfo trackPlaybackInfo, MediaPlayerType mediaPlayerType, String str, long j, long j2, int i, int i2, int i3, CacheHitStatus cacheHitStatus, BitRate bitRate, SelectionSourceInfo selectionSourceInfo, long j3, String str2) {
        super(str2, 2L);
        addEventAttributes(trackPlaybackInfo, mediaPlayerType, str, j, j2, i, i2, i3, cacheHitStatus, bitRate, selectionSourceInfo, j3);
    }

    private void addEventAttributes(TrackPlaybackInfo trackPlaybackInfo, MediaPlayerType mediaPlayerType, String str, long j, long j2, int i, int i2, int i3, CacheHitStatus cacheHitStatus, BitRate bitRate, SelectionSourceInfo selectionSourceInfo, long j3) {
        addAttribute("cdObjectId", trackPlaybackInfo.getCloudDriveObjectId());
        addAttribute("loadTimeMilliseconds", j);
        addAttribute("durationMilliseconds", j2);
        addAttribute("percentageCompleted", i);
        addAttribute("asin", trackPlaybackInfo.getAsin());
        addAttribute(PageUriUtils.SOURCE_QUERY_PARAM, str);
        addAttribute("streamOrDRMTech", mediaPlayerType.getMetricsValue());
        addAttribute("cacheHitStatus", cacheHitStatus.getMetricsValue());
        addAttribute("bitrates", bitRate.getMetricsValue());
        addAttribute("selectionSourceType", selectionSourceInfo.getSelectionSourceType().getMetricsValue());
        addAttribute("selectionSourceId", selectionSourceInfo.getSelectionSourceId());
        addAttribute("transferSpeedBPS", j3);
        addAttribute("contentSubscriptionMode", trackPlaybackInfo.getContentSubscriptionMode());
        addAttribute("subContentSubscriptionMode", trackPlaybackInfo.getSubContentSubscriptionMode());
        if (i3 != 0) {
            addAttribute("bytesExpected", i3);
            addAttribute("bytesReceived", i2);
        }
        if (trackPlaybackInfo.getSelectionSourceType() != null) {
            addAttribute("selectionSourceType", trackPlaybackInfo.getSelectionSourceType());
        }
        if (trackPlaybackInfo.getEntityType() != null) {
            addAttribute(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, trackPlaybackInfo.getEntityType());
        }
    }
}
