package com.amazon.avod.playbackclient.subtitle.internal;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleFragment {
    private final long mEndTimeExclusive;
    private final long mStartTimeInclusive;
    private final SubtitleCollection mSubtitleCollection;

    public SubtitleFragment(@Nonnull SubtitleCollection subtitleCollection, long j, long j2) {
        this.mSubtitleCollection = (SubtitleCollection) Preconditions.checkNotNull(subtitleCollection, "subtitleCollection");
        this.mStartTimeInclusive = j;
        this.mEndTimeExclusive = j2;
    }

    public SubtitleCollection getSubtitleCollection() {
        return this.mSubtitleCollection;
    }

    public long getStartTimeInclusive() {
        return this.mStartTimeInclusive;
    }

    public boolean isFragmentAt(Long l) {
        return l.longValue() >= this.mStartTimeInclusive && l.longValue() < this.mEndTimeExclusive;
    }
}
