package com.amazon.avod.media.ads.internal;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdBreakSelector {
    private final TimeSpan mAdSelectionTimeSpan;
    private final AdsConfig mAdsConfig;

    public AdBreakSelector() {
        this(AdsConfig.getInstance());
    }

    public AdBreakSelector(@Nonnull AdsConfig adsConfig) {
        this.mAdsConfig = (AdsConfig) Preconditions.checkNotNull(adsConfig, "adsConfig");
        this.mAdSelectionTimeSpan = adsConfig.getAdSelectionTimeSpan();
    }

    public AdBreak selectNextBreak(@Nonnull AdPlan adPlan, @Nonnull TimeSpan timeSpan) {
        Preconditions.checkNotNull(adPlan, "plan");
        Preconditions.checkNotNull(timeSpan, "primaryContentTime");
        for (AdBreak adBreak : adPlan.getBreaks()) {
            DLog.devf("checking the relevant ad for adBreakId %s, adtime %d, primarycontenttime %d", String.valueOf(adBreak.getId()), Long.valueOf(adBreak.getStartTime().getTotalMilliseconds()), Long.valueOf(timeSpan.getTotalMilliseconds()));
            if (isAdBreakEligible(adBreak, timeSpan)) {
                DLog.devf("found adbreak : %s", adBreak.getId());
                return adBreak;
            }
        }
        DLog.devf("found no ad break for primarycontenttime %d", Long.valueOf(timeSpan.getTotalMilliseconds()));
        return null;
    }

    private boolean isAdBreakEligible(@Nonnull AdBreak adBreak, @Nonnull TimeSpan timeSpan) {
        if (adBreak.isPlayed()) {
            return false;
        }
        if (this.mAdsConfig.isAdBreakDraper(adBreak) || !isWithInThresholdOfAdSelectionTimeSpan(adBreak.getStartTime(), timeSpan)) {
            return adBreak.getStartTime().greaterThanEquals(timeSpan);
        }
        DLog.devf("adBreak = %s is eligible adtime = %d, primarytime = %d", adBreak.getId(), Long.valueOf(adBreak.getStartTime().getTotalMilliseconds()), Long.valueOf(timeSpan.getTotalMilliseconds()));
        return true;
    }

    @Nullable
    public AdBreak selectPriorBreakIfUnPlayed(@Nonnull AdPlan adPlan, @Nonnull TimeSpan timeSpan, @Nonnull TimeSpan timeSpan2) {
        List<AdBreak> breaks = ((AdPlan) Preconditions.checkNotNull(adPlan, "AdPlan")).getBreaks();
        if (breaks.size() == 0) {
            return null;
        }
        Preconditions.checkNotNull(timeSpan, "seekTargetTime");
        Preconditions.checkNotNull(timeSpan2, "CurrentPrimaryContentTime");
        AdBreak priorBreakIfUnPlayed = getPriorBreakIfUnPlayed(adPlan, timeSpan);
        AdBreak priorBreakIfUnPlayed2 = getPriorBreakIfUnPlayed(adPlan, timeSpan2);
        if (priorBreakIfUnPlayed2 == null) {
            return priorBreakIfUnPlayed;
        }
        if (priorBreakIfUnPlayed == null || (!(this.mAdsConfig.shouldEnableDraperForWatchFromBeginning() && breaks.size() == 1 && timeSpan.isZero()) && String.valueOf(priorBreakIfUnPlayed.getId()).equalsIgnoreCase(String.valueOf(priorBreakIfUnPlayed2.getId())))) {
            return null;
        }
        return priorBreakIfUnPlayed;
    }

    @Nullable
    private AdBreak getPriorBreakIfUnPlayed(AdPlan adPlan, TimeSpan timeSpan) {
        for (AdBreak adBreak : Lists.reverse(adPlan.getBreaks())) {
            if (adBreak.getRelativeStartTime().lessThanEquals(timeSpan)) {
                if (adBreak.isPlayed()) {
                    return null;
                }
                return adBreak;
            }
        }
        return null;
    }

    private boolean isWithInThresholdOfAdSelectionTimeSpan(TimeSpan timeSpan, TimeSpan timeSpan2) {
        return Math.abs(timeSpan.getTotalMilliseconds() - timeSpan2.getTotalMilliseconds()) <= this.mAdSelectionTimeSpan.getTotalMilliseconds();
    }
}
