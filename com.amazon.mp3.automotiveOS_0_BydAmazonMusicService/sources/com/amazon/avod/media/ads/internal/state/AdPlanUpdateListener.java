package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.media.ads.AdPlan;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface AdPlanUpdateListener {
    void onPlanUpdated(@Nonnull AdPlan adPlan);

    void onTimelineUpdated();
}
