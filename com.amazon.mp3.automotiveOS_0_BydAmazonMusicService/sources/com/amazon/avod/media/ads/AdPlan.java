package com.amazon.avod.media.ads;

import com.amazon.avod.media.TimeSpan;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface AdPlan {
    @Nonnull
    List<AdBreak> getBreaks();

    @Nonnull
    TimeSpan getDuration();

    boolean hasPlayableAds();

    boolean isDraper();

    boolean showCountdownTimer();
}
