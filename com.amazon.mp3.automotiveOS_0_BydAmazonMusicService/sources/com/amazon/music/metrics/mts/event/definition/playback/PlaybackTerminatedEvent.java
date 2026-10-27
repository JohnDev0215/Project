package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.EventTimeConverter;
import com.amazon.music.metrics.mts.event.exception.CirrusInvalidDataException;
import com.amazon.music.metrics.mts.event.types.MediaPlayerType;
import com.amazon.music.metrics.mts.event.types.PlaybackInitiationInfo;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.metrics.util.RangeValidator;

/* JADX INFO: loaded from: classes4.dex */
public abstract class PlaybackTerminatedEvent extends MTSEvent {
    protected PlaybackTerminatedEvent(String str, long j, TrackPlaybackInfo trackPlaybackInfo, TerminationReason terminationReason, SelectionSourceInfo selectionSourceInfo, MediaPlayerType mediaPlayerType, long j2, long j3, long j4, PlaybackInitiationInfo playbackInitiationInfo) throws CirrusInvalidDataException {
        super(str, j);
        addEventAttributes(trackPlaybackInfo, terminationReason, selectionSourceInfo, mediaPlayerType, j2, j3, j4, playbackInitiationInfo);
    }

    private void addEventAttributes(TrackPlaybackInfo trackPlaybackInfo, TerminationReason terminationReason, SelectionSourceInfo selectionSourceInfo, MediaPlayerType mediaPlayerType, long j, long j2, long j3, PlaybackInitiationInfo playbackInitiationInfo) throws CirrusInvalidDataException {
        PlaybackAttributesChecker.validatePlaybackDelay(j3);
        addAttribute("initialPlaybackDelayMilliseconds", j3);
        addAttribute("contentSubscriptionMode", trackPlaybackInfo.getContentSubscriptionMode());
        addAttribute("subContentSubscriptionMode", trackPlaybackInfo.getSubContentSubscriptionMode());
        addAttribute("terminationReason", terminationReason.getMetricsValue());
        addAttribute("selectionSourceType", selectionSourceInfo.getSelectionSourceType().getMetricsValue());
        addAttribute("selectionSourceId", selectionSourceInfo.getSelectionSourceId());
        addAttribute("pageType", playbackInitiationInfo.getPlaybackPageType().getMetricsValue());
        addAttribute("isShufflePlay", playbackInitiationInfo.getShufflePlayStatus());
        addAttribute("isDirectedPlay", playbackInitiationInfo.getDirectedPlayStatus().getMetricsValue());
        addAttribute("streamOrDRMTech", mediaPlayerType.getMetricsValue());
        addAttribute("asin", trackPlaybackInfo.getAsin());
        addAttribute("durationSeconds", getDurationSeconds(j, j2));
    }

    public void setTerminationReason(TerminationReason terminationReason) {
        if (terminationReason != null) {
            addAttribute("terminationReason", terminationReason.getMetricsValue());
        }
    }

    private long getDurationSeconds(long j, long j2) {
        return new EventTimeConverter().convertToRoundedSeconds(getDurationMilliseconds(j, j2));
    }

    private long getDurationMilliseconds(long j, long j2) {
        return new RangeValidator().withinRange(j, 0L, j2) ? j : j2;
    }

    protected void setIsOnDemandQueue(boolean z) {
        addAttribute("isOnDemandQueue", Boolean.toString(z));
    }
}
