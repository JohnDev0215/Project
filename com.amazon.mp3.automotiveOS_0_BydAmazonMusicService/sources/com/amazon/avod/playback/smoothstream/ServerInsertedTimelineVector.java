package com.amazon.avod.playback.smoothstream;

import com.amazon.avod.media.TimeSpan;
import com.google.common.collect.Range;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ServerInsertedTimelineVector {
    private final ServerInsertedTimelineVectorType mTimelineItemType;
    private final Range<Long> mVectorAbsoluteRange;
    private final Range<Long> mVectorRelativeRange;

    public ServerInsertedTimelineVector(@Nonnull Range<Long> range, @Nonnull Range<Long> range2, @Nonnull ServerInsertedTimelineVectorType serverInsertedTimelineVectorType) {
        this.mVectorAbsoluteRange = range;
        this.mVectorRelativeRange = range2;
        this.mTimelineItemType = serverInsertedTimelineVectorType;
    }

    public Range<Long> getVectorAbsoluteRange() {
        return this.mVectorAbsoluteRange;
    }

    public Range<Long> getVectorRelativeRange() {
        return this.mVectorRelativeRange;
    }

    public ServerInsertedTimelineVectorType getTimelineItemType() {
        return this.mTimelineItemType;
    }

    public String toString() {
        return "ServerInsertedTimelineVector{, mVectorAbsoluteRange= {Start: " + new TimeSpan(((Long) this.mVectorAbsoluteRange.lowerEndpoint()).longValue()) + ", End: " + new TimeSpan(((Long) this.mVectorAbsoluteRange.upperEndpoint()).longValue()) + "}, mVectorRelativeRange= {Start: " + new TimeSpan(((Long) this.mVectorRelativeRange.lowerEndpoint()).longValue()) + ", End: " + new TimeSpan(((Long) this.mVectorRelativeRange.upperEndpoint()).longValue()) + "}, mTimelineItemType=" + this.mTimelineItemType + '}';
    }
}
