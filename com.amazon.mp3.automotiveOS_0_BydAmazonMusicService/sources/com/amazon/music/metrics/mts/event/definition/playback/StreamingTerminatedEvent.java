package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.exception.CirrusInvalidDataException;
import com.amazon.music.metrics.mts.event.types.BitRate;
import com.amazon.music.metrics.mts.event.types.CacheHitStatus;
import com.amazon.music.metrics.mts.event.types.MediaPlayerType;
import com.amazon.music.metrics.mts.event.types.PlaybackInitiationInfo;
import com.amazon.music.metrics.mts.event.types.ResourceType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.metrics.util.RangeValidator;

/* JADX INFO: loaded from: classes4.dex */
public class StreamingTerminatedEvent extends PlaybackTerminatedEvent {
    public StreamingTerminatedEvent(TrackPlaybackInfo trackPlaybackInfo, TerminationReason terminationReason, SelectionSourceInfo selectionSourceInfo, MediaPlayerType mediaPlayerType, long j, long j2, long j3, PlaybackInitiationInfo playbackInitiationInfo, long j4, ResourceType resourceType, long j5, CacheHitStatus cacheHitStatus, String str, BitRate bitRate, boolean z, boolean z2) throws CirrusInvalidDataException {
        super("streamingTerminated", 5L, trackPlaybackInfo, terminationReason, selectionSourceInfo, mediaPlayerType, j, j2, j3, playbackInitiationInfo);
        addEventAttributes(trackPlaybackInfo, selectionSourceInfo, j4, resourceType, j5, cacheHitStatus, str, bitRate, z);
        setIsOnDemandQueue(z2);
    }

    protected StreamingTerminatedEvent(TrackPlaybackInfo trackPlaybackInfo, TerminationReason terminationReason, SelectionSourceInfo selectionSourceInfo, MediaPlayerType mediaPlayerType, long j, long j2, long j3, PlaybackInitiationInfo playbackInitiationInfo, long j4, ResourceType resourceType, long j5, CacheHitStatus cacheHitStatus, String str, BitRate bitRate, boolean z, String str2) throws CirrusInvalidDataException {
        super(str2, 5L, trackPlaybackInfo, terminationReason, selectionSourceInfo, mediaPlayerType, j, j2, j3, playbackInitiationInfo);
        addEventAttributes(trackPlaybackInfo, selectionSourceInfo, j4, resourceType, j5, cacheHitStatus, str, bitRate, z);
    }

    public void setCdnType(String str) {
        if (str != null) {
            addAttribute("cdnType", str);
        }
    }

    private void addEventAttributes(TrackPlaybackInfo trackPlaybackInfo, SelectionSourceInfo selectionSourceInfo, long j, ResourceType resourceType, long j2, CacheHitStatus cacheHitStatus, String str, BitRate bitRate, boolean z) throws CirrusInvalidDataException {
        validateTransferSpeed(j2);
        addAttribute("transferSpeedBPS", j2);
        addAttribute("cdObjectId", trackPlaybackInfo.getCloudDriveObjectId());
        addAttribute("resourceType", resourceType);
        addAttribute("rebufferCount", j);
        addAttribute("selectionSourceSessionId", selectionSourceInfo.getSelectionSourceSessionId());
        addAttribute(PageUriUtils.SOURCE_QUERY_PARAM, str);
        addAttribute("cacheHitStatus", cacheHitStatus.getMetricsValue());
        addAttribute("bitrates", bitRate.getMetricsValue());
        addAttribute("isExplicitLanguageFilterOn", z);
        if (trackPlaybackInfo.getSelectionSourceType() != null) {
            addAttribute("selectionSourceType", trackPlaybackInfo.getSelectionSourceType());
        }
        if (trackPlaybackInfo.getEntityType() != null) {
            addAttribute(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, trackPlaybackInfo.getEntityType());
        }
    }

    private void validateTransferSpeed(long j) throws CirrusInvalidDataException {
        if (!isTransferSpeedValid(j)) {
            throw new CirrusInvalidDataException(getTransferSpeedErrorString(j));
        }
    }

    private String getTransferSpeedErrorString(long j) {
        return String.format("playbackTerminated dataRateBps is outside of valid range: %d", Long.valueOf(j));
    }

    private boolean isTransferSpeedValid(long j) {
        return new RangeValidator().withinRange(j, 0L, 100000000L);
    }
}
