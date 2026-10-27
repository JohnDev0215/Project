package com.amazon.avod.ads.parser.vast;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastInline implements VastAdData {
    private final VastAdSystem mAdSystem;
    private final String mAdTitle;
    private final String mAdvertiser;
    private final List<VastInlineCreative> mCreatives;
    private final String mDescription;
    private final List<URI> mErrors;
    private final List<VastExtension> mExtensions;
    private final List<VastImpression> mImpressions;
    private final VastPricing mPricing;
    private final List<URI> mSurvey;

    public VastInline(@Nonnull VastAdSystem vastAdSystem, @Nonnull String str, @Nonnull List<VastImpression> list, @Nonnull List<VastInlineCreative> list2, @Nullable String str2, @Nullable String str3, @Nullable VastPricing vastPricing, @Nullable List<URI> list3, @Nullable List<URI> list4, @Nullable List<VastExtension> list5) {
        this.mAdSystem = (VastAdSystem) Preconditions.checkNotNull(vastAdSystem, "adSystem");
        this.mAdTitle = (String) Preconditions.checkNotNull(str, "adTitle");
        this.mImpressions = (List) Preconditions.checkNotNull(list, "impressions");
        this.mCreatives = (List) Preconditions.checkNotNull(list2, "creatives");
        this.mDescription = str2;
        this.mAdvertiser = str3;
        this.mPricing = vastPricing;
        this.mSurvey = list3;
        this.mErrors = list4;
        this.mExtensions = list5;
    }

    @Override // com.amazon.avod.ads.parser.vast.VastAdData
    public VastAdSystem getAdSystem() {
        return this.mAdSystem;
    }

    @Nonnull
    public List<VastInlineCreative> getCreatives() {
        return this.mCreatives;
    }

    @Override // com.amazon.avod.ads.parser.vast.VastAdData
    public List<VastExtension> getExtensions() {
        return this.mExtensions;
    }

    @Override // com.amazon.avod.ads.parser.vast.VastAdData
    @Nullable
    public List<URI> getErrors() {
        return this.mErrors;
    }

    @Override // com.amazon.avod.ads.parser.vast.VastAdData
    @Nullable
    public List<URI> getImpressions() {
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(this.mImpressions.size());
        Iterator<VastImpression> it = this.mImpressions.iterator();
        while (it.hasNext()) {
            arrayListNewArrayListWithCapacity.add(it.next().getUri());
        }
        return arrayListNewArrayListWithCapacity;
    }
}
