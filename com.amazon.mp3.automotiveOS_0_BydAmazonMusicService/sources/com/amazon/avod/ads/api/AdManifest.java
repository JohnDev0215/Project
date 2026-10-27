package com.amazon.avod.ads.api;

import com.amazon.avod.ads.http.AdHttpClient;
import com.amazon.avod.ads.parser.vast.VastDocument;
import com.amazon.avod.ads.parser.vmap.VmapAdBreak;
import com.amazon.avod.ads.parser.vmap.VmapAdSource;
import com.amazon.avod.ads.parser.vmap.VmapVastAdData;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AdManifest {
    private final List<AdBreakInfo> mAdBreaks;

    private AdManifest(List<AdBreakInfo> list) {
        this.mAdBreaks = list;
    }

    @Nonnull
    public static AdManifest createAdManifest(@Nonnull List<VmapAdBreak> list, @Nonnull AdHttpClient adHttpClient) {
        VmapVastAdData vastData;
        VastDocument vastDocument;
        Preconditions.checkNotNull(adHttpClient);
        Preconditions.checkNotNull(list);
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(list.size());
        for (VmapAdBreak vmapAdBreak : list) {
            VmapAdSource adSource = vmapAdBreak.getAdSource();
            if (adSource != null && (vastData = adSource.getVastData()) != null && (vastDocument = vastData.getVastDocument()) != null && !vastDocument.getAds().isEmpty()) {
                arrayListNewArrayListWithCapacity.add(new AdBreakInfo(vmapAdBreak, adHttpClient));
            }
        }
        return new AdManifest(arrayListNewArrayListWithCapacity);
    }

    @Nonnull
    public static AdManifest createEmtpyAdManifest() {
        return new AdManifest(Collections.emptyList());
    }

    @Nonnull
    public List<AdBreakInfo> getAdBreaks() {
        return this.mAdBreaks;
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("Ad Break Count", this.mAdBreaks.size()).toString();
    }
}
