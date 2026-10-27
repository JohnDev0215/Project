package com.amazon.avod.media.ads.internal;

import com.amazon.atv.cuepoint.CuepointPlaylist;
import com.amazon.avod.media.ads.AdPlan;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AdPlanResult {

    @Nonnull
    private final AdPlan mAdPlan;

    @Nonnull
    private final Optional<CuepointPlaylist> mCuepointPlaylistOptional;

    public AdPlanResult(@Nonnull AdPlan adPlan, @Nonnull Optional<CuepointPlaylist> optional) {
        this.mAdPlan = (AdPlan) Preconditions.checkNotNull(adPlan, "adPlan");
        this.mCuepointPlaylistOptional = (Optional) Preconditions.checkNotNull(optional, "cuepointPlaylistOptional");
    }

    @Nonnull
    public AdPlan getAdPlan() {
        return this.mAdPlan;
    }

    @Nonnull
    public Optional<CuepointPlaylist> getCuepointPlaylistOptional() {
        return this.mCuepointPlaylistOptional;
    }
}
