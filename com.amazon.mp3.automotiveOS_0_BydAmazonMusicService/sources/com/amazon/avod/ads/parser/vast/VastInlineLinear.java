package com.amazon.avod.ads.parser.vast;

import com.google.common.base.Preconditions;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastInlineLinear extends VastWrapperLinear {
    private final VastAdParameters mAdParameters;
    private final VastTimeSpan mDuration;
    private final List<VastMediaFile> mMediaFiles;
    private final VastOffset mSkipOffset;

    public VastInlineLinear(@Nullable List<VastIcon> list, @Nullable List<VastTracking> list2, @Nullable VastVideoClicks vastVideoClicks, @Nullable VastOffset vastOffset, @Nonnull VastTimeSpan vastTimeSpan, @Nullable VastAdParameters vastAdParameters, @Nullable List<VastMediaFile> list3) {
        super(list, list2, vastVideoClicks);
        this.mSkipOffset = vastOffset;
        this.mDuration = (VastTimeSpan) Preconditions.checkNotNull(vastTimeSpan, "duration");
        this.mAdParameters = vastAdParameters;
        this.mMediaFiles = list3;
    }

    @Nullable
    public VastOffset getSkipOffset() {
        return this.mSkipOffset;
    }

    @Nonnull
    public VastTimeSpan getDuration() {
        return this.mDuration;
    }

    @Nullable
    public List<VastMediaFile> getMediaFiles() {
        return this.mMediaFiles;
    }
}
