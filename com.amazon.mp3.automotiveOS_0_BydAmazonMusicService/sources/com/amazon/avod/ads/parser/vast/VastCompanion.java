package com.amazon.avod.ads.parser.vast;

import com.google.common.base.Preconditions;
import java.net.URI;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastCompanion {
    private final VastAdParameters mAdParameters;
    private final String mAdSlotId;
    private final String mAltText;
    private final String mApiFramework;
    private final Integer mAssetHeight;
    private final Integer mAssetWidth;
    private final URI mCompanionClickThrough;
    private final List<VastCompanionClickTracking> mCompanionClickTracking;
    private final Integer mExpandedHeight;
    private final Integer mExpandedWidth;
    private final int mHeight;
    private final String mId;
    private final VastResource mResource;
    private final List<VastTracking> mTrackingEvents;
    private final int mWidth;

    public VastCompanion(@Nullable String str, int i, int i2, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable Integer num4, @Nullable String str2, @Nullable String str3, @Nullable VastResource vastResource, @Nullable List<VastTracking> list, @Nullable URI uri, @Nullable List<VastCompanionClickTracking> list2, @Nullable String str4, @Nullable VastAdParameters vastAdParameters) {
        Preconditions.checkArgument(i >= 0);
        Preconditions.checkArgument(i2 >= 0);
        this.mId = str;
        this.mWidth = i;
        this.mHeight = i2;
        this.mAssetWidth = num;
        this.mAssetHeight = num2;
        this.mExpandedWidth = num3;
        this.mExpandedHeight = num4;
        this.mApiFramework = str2;
        this.mAdSlotId = str3;
        this.mResource = vastResource;
        this.mTrackingEvents = list;
        this.mCompanionClickThrough = uri;
        this.mCompanionClickTracking = list2;
        this.mAltText = str4;
        this.mAdParameters = vastAdParameters;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    @Nullable
    public VastResource getResource() {
        return this.mResource;
    }

    @Nullable
    public List<VastTracking> getTrackingEvents() {
        return this.mTrackingEvents;
    }
}
