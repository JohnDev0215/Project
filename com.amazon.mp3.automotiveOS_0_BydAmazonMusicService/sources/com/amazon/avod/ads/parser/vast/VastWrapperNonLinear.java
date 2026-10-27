package com.amazon.avod.ads.parser.vast;

import com.google.common.base.Preconditions;
import java.net.URI;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastWrapperNonLinear {
    protected final String mApiFramework;
    protected final Integer mExpandedHeight;
    protected final Integer mExpandedWidth;
    protected final Integer mHeight;
    protected final String mId;
    protected final Boolean mMaintainAspectRatio;
    protected final VastTimeSpan mMinSuggestedDuration;
    protected final List<URI> mNonLinearClickTracking;
    protected final Boolean mScalable;
    protected final List<VastTracking> mTrackingEvents;
    protected final Integer mWidth;

    public VastWrapperNonLinear(@Nullable String str, @Nonnull Integer num, @Nonnull Integer num2, @Nullable Integer num3, @Nullable Integer num4, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable VastTimeSpan vastTimeSpan, @Nullable String str2, @Nullable List<URI> list, @Nullable List<VastTracking> list2) {
        this.mId = str;
        this.mWidth = (Integer) Preconditions.checkNotNull(num, "width");
        this.mHeight = (Integer) Preconditions.checkNotNull(num2, "height");
        this.mExpandedWidth = num3;
        this.mExpandedHeight = num4;
        this.mScalable = bool;
        this.mMaintainAspectRatio = bool2;
        this.mMinSuggestedDuration = vastTimeSpan;
        this.mApiFramework = str2;
        this.mNonLinearClickTracking = list;
        this.mTrackingEvents = list2;
    }
}
