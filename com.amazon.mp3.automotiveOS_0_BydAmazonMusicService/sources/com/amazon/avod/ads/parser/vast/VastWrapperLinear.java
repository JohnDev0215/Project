package com.amazon.avod.ads.parser.vast;

import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastWrapperLinear {
    private final List<VastIcon> mIcons;
    private final List<VastTracking> mTrackingEvents;
    private final VastVideoClicks mVideoClicks;

    public VastWrapperLinear(@Nullable List<VastIcon> list, @Nullable List<VastTracking> list2, @Nullable VastVideoClicks vastVideoClicks) {
        this.mIcons = list;
        this.mTrackingEvents = list2;
        this.mVideoClicks = vastVideoClicks;
    }

    @Nullable
    public List<VastIcon> getIcons() {
        return this.mIcons;
    }

    @Nullable
    public List<VastTracking> getTrackingEvents() {
        return this.mTrackingEvents;
    }

    @Nullable
    public VastVideoClicks getVideoClicks() {
        return this.mVideoClicks;
    }
}
