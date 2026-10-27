package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdClip;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum PrerollStatus {
    CACHED(true, "cached"),
    NOT_CACHED_REQUIRED(true, "not_cached_required"),
    NOT_CACHED_NOT_REQUIRED(false, "not_cached_not_required"),
    NOT_AVAILABLE(false, "not_available"),
    RESUME_POINT(false, "resume_point");

    private final boolean mIsPlayable;
    private final String mReportingName;

    PrerollStatus(boolean z, @Nonnull String str) {
        this.mIsPlayable = z;
        this.mReportingName = (String) Preconditions.checkNotNull(str, "reportingName");
    }

    public boolean isAvailableForPlayback() {
        return this.mIsPlayable;
    }

    @Nonnull
    public String getReportingName() {
        return this.mReportingName;
    }

    @Nonnull
    public static PrerollStatus getPrerollStatus(@Nonnull TimeSpan timeSpan, @Nullable AdBreak adBreak, @Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext) {
        Preconditions.checkNotNull(timeSpan, "primaryContentStartTime");
        Preconditions.checkNotNull(adPlaybackStateMachineContext, "context");
        if (!timeSpan.isZero()) {
            return RESUME_POINT;
        }
        if (adBreak == null || !adBreak.getStartTime().equals(TimeSpan.ZERO)) {
            return NOT_AVAILABLE;
        }
        List<AdClip> clips = adBreak.getClips();
        if (clips.isEmpty()) {
            DLog.logf("CheckPrerollState - Ad services are vending manifests with an AdBreak and no clip. This is probably a bug.");
            return NOT_AVAILABLE;
        }
        String offerType = adPlaybackStateMachineContext.getOfferType();
        Iterator<AdClip> it = clips.iterator();
        while (it.hasNext()) {
            if (!it.next().getCacheStatus().isFullyCached()) {
                if (adPlaybackStateMachineContext.getAdsConfig().shouldSkipAdIfNotReady(offerType)) {
                    return NOT_CACHED_NOT_REQUIRED;
                }
                return NOT_CACHED_REQUIRED;
            }
        }
        return CACHED;
    }
}
