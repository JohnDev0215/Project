package com.amazon.avod.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface TimelineManager {
    @Nonnull
    TimeSpan getNormalisedRelativePosition(@Nonnegative long j);

    @Nonnull
    TimeSpan getPlaybackDuration();

    void notifyAdPlanRetrievalComplete(@Nonnull AdPlan adPlan);

    void notifyAdPlanRetrievalComplete(@Nullable AdPlan adPlan, @Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext);
}
