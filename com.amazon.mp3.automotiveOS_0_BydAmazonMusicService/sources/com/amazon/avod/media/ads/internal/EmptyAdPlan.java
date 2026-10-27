package com.amazon.avod.media.ads.internal;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdPlan;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class EmptyAdPlan implements AdPlan {
    public static final EmptyAdPlan INSTANCE = new EmptyAdPlan();

    @Override // com.amazon.avod.media.ads.AdPlan
    public boolean hasPlayableAds() {
        return false;
    }

    @Override // com.amazon.avod.media.ads.AdPlan
    public boolean isDraper() {
        return false;
    }

    @Override // com.amazon.avod.media.ads.AdPlan
    public boolean showCountdownTimer() {
        return false;
    }

    private EmptyAdPlan() {
    }

    @Override // com.amazon.avod.media.ads.AdPlan
    @Nonnull
    public List<AdBreak> getBreaks() {
        return Collections.emptyList();
    }

    @Override // com.amazon.avod.media.ads.AdPlan
    @Nonnull
    public TimeSpan getDuration() {
        return TimeSpan.ZERO;
    }
}
