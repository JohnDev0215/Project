package com.amazon.avod.playback.event;

import com.amazon.avod.media.ads.AdPlan;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AdPlanFetchedEvent {
    private final AdPlan mAdPlan;

    public AdPlanFetchedEvent(@Nonnull AdPlan adPlan) {
        this.mAdPlan = (AdPlan) Preconditions.checkNotNull(adPlan, "adPlan");
    }

    public AdPlan getAdPlan() {
        return this.mAdPlan;
    }
}
