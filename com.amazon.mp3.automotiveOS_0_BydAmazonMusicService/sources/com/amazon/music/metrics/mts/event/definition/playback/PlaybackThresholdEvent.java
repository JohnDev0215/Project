package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.EventTimeConverter;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.metrics.util.StringUtil;

/* JADX INFO: loaded from: classes4.dex */
abstract class PlaybackThresholdEvent extends MTSEvent {
    protected PlaybackThresholdEvent(String str, long j, TrackPlaybackInfo trackPlaybackInfo, long j2, SelectionSourceInfo selectionSourceInfo, TerminationReason terminationReason) {
        super(str, j);
        addEventAttributes(trackPlaybackInfo, j2, selectionSourceInfo, terminationReason);
    }

    protected PlaybackThresholdEvent(String str, long j, TrackPlaybackInfo trackPlaybackInfo, long j2, SelectionSourceInfo selectionSourceInfo) {
        this(str, j, trackPlaybackInfo, j2, selectionSourceInfo, null);
    }

    private void addEventAttributes(TrackPlaybackInfo trackPlaybackInfo, long j, SelectionSourceInfo selectionSourceInfo, TerminationReason terminationReason) {
        String asin = trackPlaybackInfo.getAsin();
        String cloudDriveObjectId = trackPlaybackInfo.getCloudDriveObjectId();
        if (StringUtil.isNullOrWhiteSpace(asin) && StringUtil.isNullOrWhiteSpace(cloudDriveObjectId)) {
            asin = "0";
            cloudDriveObjectId = "0";
        }
        addAttribute("asin", asin);
        addAttribute("cdObjectId", cloudDriveObjectId);
        addAttribute("contentSubscriptionMode", trackPlaybackInfo.getContentSubscriptionMode());
        addAttribute("subContentSubscriptionMode", trackPlaybackInfo.getSubContentSubscriptionMode());
        addAttribute("trackProgress", new EventTimeConverter().convertToRoundedSeconds(j));
        if (selectionSourceInfo != null) {
            if (selectionSourceInfo.getSelectionSourceType() != null) {
                addAttribute("selectionSourceType", selectionSourceInfo.getSelectionSourceType().getMetricsValue());
            }
            if (selectionSourceInfo.getSelectionSourceId() != null) {
                addAttribute("selectionSourceId", selectionSourceInfo.getSelectionSourceId());
            }
        }
        if (terminationReason != null) {
            addAttribute("terminationReason", terminationReason.getMetricsValue());
        }
    }

    protected void setIsOnDemandQueue(boolean z) {
        addAttribute("isOnDemandQueue", Boolean.toString(z));
    }

    protected void setIsShufflePlay(boolean z) {
        addAttribute("isShufflePlay", Boolean.toString(z));
    }
}
