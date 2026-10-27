package com.amazon.avod.media.ads;

import com.amazon.avod.media.ads.internal.state.AdPlanUpdateListener;
import com.amazon.avod.media.playback.VideoPresentation;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface AdEnabledVideoPresentation extends VideoPresentation {
    boolean addAdPlanUpdateListener(@Nonnull AdPlanUpdateListener adPlanUpdateListener);

    @Nonnull
    AdPlan getAdPlan();

    void setAdEventListener(@Nullable AdEnabledVideoEventListener adEnabledVideoEventListener);
}
