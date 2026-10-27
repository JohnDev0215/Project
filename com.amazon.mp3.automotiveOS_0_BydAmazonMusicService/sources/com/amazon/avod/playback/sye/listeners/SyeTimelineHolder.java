package com.amazon.avod.playback.sye.listeners;

import com.amazon.avod.media.playback.internal.PlaybackListenerProxy;
import com.google.common.base.Preconditions;
import com.netinsight.sye.syeClient.generated.enums.PlaybackType;
import com.netinsight.sye.syeClient.playerListeners.ITimelineListener;
import com.netinsight.sye.syeClient.timeshift.ISyeTimelineInfo;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SyeTimelineHolder implements ITimelineListener, ISyeTimelineInfo {
    private final PlaybackListenerProxy mPlaybackListenerProxy;
    private long mSeekingPosUtcMillis = -1;
    private ISyeTimelineInfo mTimeline;

    public SyeTimelineHolder(@Nonnull PlaybackListenerProxy playbackListenerProxy) {
        this.mPlaybackListenerProxy = (PlaybackListenerProxy) Preconditions.checkNotNull(playbackListenerProxy, "playbackListenerProxy");
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.ITimelineListener
    public void onTimelineUpdate(@Nonnull ISyeTimelineInfo iSyeTimelineInfo) {
        this.mPlaybackListenerProxy.onTimeDataChange(iSyeTimelineInfo.getCurrentPosUtcMillis());
        this.mTimeline = iSyeTimelineInfo;
        this.mSeekingPosUtcMillis = -1L;
    }

    @Override // com.netinsight.sye.syeClient.timeshift.ISyeTimelineInfo
    public PlaybackType getPlaybackType() {
        ISyeTimelineInfo iSyeTimelineInfo = this.mTimeline;
        if (iSyeTimelineInfo == null) {
            return PlaybackType.Live;
        }
        return iSyeTimelineInfo.getPlaybackType();
    }

    @Override // com.netinsight.sye.syeClient.timeshift.ISyeTimelineInfo
    public long getCurrentPosUtcMillis() {
        long j = this.mSeekingPosUtcMillis;
        if (j != -1) {
            return j;
        }
        ISyeTimelineInfo iSyeTimelineInfo = this.mTimeline;
        if (iSyeTimelineInfo == null) {
            return 0L;
        }
        return iSyeTimelineInfo.getCurrentPosUtcMillis();
    }

    @Override // com.netinsight.sye.syeClient.timeshift.ISyeTimelineInfo
    public long getLivePosUtcMillis() {
        ISyeTimelineInfo iSyeTimelineInfo = this.mTimeline;
        if (iSyeTimelineInfo == null) {
            return 0L;
        }
        return iSyeTimelineInfo.getLivePosUtcMillis();
    }

    @Override // com.netinsight.sye.syeClient.timeshift.ISyeTimelineInfo
    public boolean getShowTimeline() {
        ISyeTimelineInfo iSyeTimelineInfo = this.mTimeline;
        if (iSyeTimelineInfo == null) {
            return false;
        }
        return iSyeTimelineInfo.getShowTimeline();
    }

    @Override // com.netinsight.sye.syeClient.timeshift.ISyeTimelineInfo
    public double getThumbnailFillrate() {
        ISyeTimelineInfo iSyeTimelineInfo = this.mTimeline;
        if (iSyeTimelineInfo == null) {
            return 0.0d;
        }
        return iSyeTimelineInfo.getThumbnailFillrate();
    }

    public void setSeekingPosUtcMillis(long j) {
        this.mSeekingPosUtcMillis = j;
    }
}
