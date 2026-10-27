package com.amazon.avod.playback.sye.trickplay;

import android.app.Activity;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.sye.SyeDomainVendingMachine;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.netinsight.sye.syeClient.ISyePlayer;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SyeTrickplayManager implements TrickplayManager {
    private final Activity mActivity;
    private final TimeSpan mDurationSafetyThreshold;
    private final TimeSpan mMaxCacheDuration;
    private final int mMinImageFidelityThresholdMillis;
    private final SyeDomainVendingMachine mSyeDomainVendingMachine;
    private final ISyePlayer mSyePlayer;

    public SyeTrickplayManager(@Nonnull Activity activity, @Nonnull ISyePlayer iSyePlayer, @Nonnull SyeDomainVendingMachine syeDomainVendingMachine) {
        this(activity, iSyePlayer, syeDomainVendingMachine, (int) SyeTrickplayConfig.getInstance().getMinImageFidelityThreshold().getTotalMilliseconds(), SyeTrickplayConfig.getInstance().getDurationSafetyThreshold(), SyeTrickplayConfig.getInstance().getMaxDuration());
    }

    SyeTrickplayManager(@Nonnull Activity activity, @Nonnull ISyePlayer iSyePlayer, @Nonnull SyeDomainVendingMachine syeDomainVendingMachine, int i, @Nonnull TimeSpan timeSpan, @Nonnull TimeSpan timeSpan2) {
        this.mActivity = (Activity) Preconditions.checkNotNull(activity, "playbackActivity");
        this.mSyePlayer = (ISyePlayer) Preconditions.checkNotNull(iSyePlayer, "syePlayer");
        this.mSyeDomainVendingMachine = (SyeDomainVendingMachine) Preconditions.checkNotNull(syeDomainVendingMachine, "syeDomainVendingMachine");
        this.mMinImageFidelityThresholdMillis = i;
        this.mDurationSafetyThreshold = (TimeSpan) Preconditions.checkNotNull(timeSpan, "durationSafetyThreshold");
        this.mMaxCacheDuration = (TimeSpan) Preconditions.checkNotNull(timeSpan2, "maxCacheDuration");
    }

    @Override // com.amazon.avod.playback.sye.trickplay.TrickplayManager
    public void startCaching() {
        int dvrDurationMillis = this.mSyeDomainVendingMachine.currentSyeDomain().getDvrDurationMillis();
        long startUtcMillis = this.mSyeDomainVendingMachine.currentSyeDomain().getStartUtcMillis();
        long durationMillis = this.mSyeDomainVendingMachine.currentSyeDomain().getDurationMillis();
        if (dvrDurationMillis != 0) {
            long jMin = Math.min(this.mMaxCacheDuration.getTotalMilliseconds(), durationMillis + this.mDurationSafetyThreshold.getTotalMilliseconds());
            DLog.logf("SyeTrickplayManager: Caching thumbnails with duration %d milliseconds", Long.valueOf(jMin));
            this.mSyePlayer.cacheThumbnailsForInterval(startUtcMillis, jMin + startUtcMillis);
            return;
        }
        DLog.logf("SyeTrickplayManager: Not caching thumbnailis due to invalid DVR window");
    }
}
