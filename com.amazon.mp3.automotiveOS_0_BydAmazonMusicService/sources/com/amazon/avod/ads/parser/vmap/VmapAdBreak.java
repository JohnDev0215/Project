package com.amazon.avod.ads.parser.vmap;

import com.amazon.avod.ads.parser.vast.VastOffset;
import com.amazon.avod.ads.parser.vast.VastTimeSpan;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VmapAdBreak {
    private final VmapAdSource mAdSource;
    private final String mBreakId;
    private final VmapBreakType mBreakType;
    private final VastTimeSpan mRepeatAfter;
    private final VastOffset mTimeOffset;
    private final List<VmapTracking> mTrackingEvents;

    public VmapAdBreak(@Nonnull VastOffset vastOffset, @Nonnull VmapBreakType vmapBreakType, @Nullable String str, @Nullable VastTimeSpan vastTimeSpan, @Nullable VmapAdSource vmapAdSource, @Nullable List<VmapTracking> list) {
        LinkedList linkedListNewLinkedList = Lists.newLinkedList();
        this.mTrackingEvents = linkedListNewLinkedList;
        this.mTimeOffset = (VastOffset) Preconditions.checkNotNull(vastOffset, "mTimeOffset");
        this.mBreakType = (VmapBreakType) Preconditions.checkNotNull(vmapBreakType, "mBreakType");
        this.mBreakId = str;
        this.mRepeatAfter = vastTimeSpan;
        this.mAdSource = vmapAdSource;
        if (list != null) {
            linkedListNewLinkedList.addAll(list);
        }
    }

    @Nonnull
    public VastOffset getTimeOffset() {
        return this.mTimeOffset;
    }

    @Nullable
    public String getBreakId() {
        return this.mBreakId;
    }

    @Nullable
    public VastTimeSpan getRepeatAfter() {
        return this.mRepeatAfter;
    }

    @Nullable
    public VmapAdSource getAdSource() {
        return this.mAdSource;
    }

    @Nonnull
    public List<VmapTracking> getTrackingEvents() {
        return this.mTrackingEvents;
    }
}
