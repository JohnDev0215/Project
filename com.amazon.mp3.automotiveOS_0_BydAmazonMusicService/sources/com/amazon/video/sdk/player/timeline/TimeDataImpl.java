package com.amazon.video.sdk.player.timeline;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TimeData.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0080\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u000f¢\u0006\u0004\b\u001b\u0010\u001cJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0010\u001a\u00020\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0015\u001a\u00020\u00148\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u0011\u001a\u0004\b\u001a\u0010\u0013¨\u0006\u001d"}, d2 = {"Lcom/amazon/video/sdk/player/timeline/TimeDataImpl;", "Lcom/amazon/video/sdk/player/timeline/TimeData;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/player/timeline/TimeUnit;", "unit", "Lcom/amazon/video/sdk/player/timeline/TimeUnit;", "getUnit", "()Lcom/amazon/video/sdk/player/timeline/TimeUnit;", "", "currentPosition", "J", "getCurrentPosition", "()J", "Lcom/amazon/video/sdk/player/timeline/PlayableRange;", "playableRange", "Lcom/amazon/video/sdk/player/timeline/PlayableRange;", "getPlayableRange", "()Lcom/amazon/video/sdk/player/timeline/PlayableRange;", "currentTimelineItemIndex", "getCurrentTimelineItemIndex", "<init>", "(Lcom/amazon/video/sdk/player/timeline/TimeUnit;JLcom/amazon/video/sdk/player/timeline/PlayableRange;J)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class TimeDataImpl implements TimeData {
    private final long currentPosition;
    private final long currentTimelineItemIndex;
    private final PlayableRange playableRange;
    private final TimeUnit unit;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TimeDataImpl)) {
            return false;
        }
        TimeDataImpl timeDataImpl = (TimeDataImpl) other;
        return getUnit() == timeDataImpl.getUnit() && getCurrentPosition() == timeDataImpl.getCurrentPosition() && Intrinsics.areEqual(getPlayableRange(), timeDataImpl.getPlayableRange()) && getCurrentTimelineItemIndex() == timeDataImpl.getCurrentTimelineItemIndex();
    }

    public int hashCode() {
        return (((((getUnit().hashCode() * 31) + Long.hashCode(getCurrentPosition())) * 31) + getPlayableRange().hashCode()) * 31) + Long.hashCode(getCurrentTimelineItemIndex());
    }

    public String toString() {
        return "TimeDataImpl(unit=" + getUnit() + ", currentPosition=" + getCurrentPosition() + ", playableRange=" + getPlayableRange() + ", currentTimelineItemIndex=" + getCurrentTimelineItemIndex() + ')';
    }

    public TimeDataImpl(TimeUnit unit, long j, PlayableRange playableRange, long j2) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(playableRange, "playableRange");
        this.unit = unit;
        this.currentPosition = j;
        this.playableRange = playableRange;
        this.currentTimelineItemIndex = j2;
    }

    public /* synthetic */ TimeDataImpl(TimeUnit timeUnit, long j, PlayableRange playableRange, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? TimeUnit.CONTENT_MILLIS : timeUnit, j, (i & 4) != 0 ? new PlayableRangeData(null, null, null, 7, null) : playableRange, (i & 8) != 0 ? 0L : j2);
    }

    @Override // com.amazon.video.sdk.player.timeline.TimeData
    public TimeUnit getUnit() {
        return this.unit;
    }

    @Override // com.amazon.video.sdk.player.timeline.TimeData
    public long getCurrentPosition() {
        return this.currentPosition;
    }

    @Override // com.amazon.video.sdk.player.timeline.TimeData
    public PlayableRange getPlayableRange() {
        return this.playableRange;
    }

    @Override // com.amazon.video.sdk.player.timeline.TimeData
    public long getCurrentTimelineItemIndex() {
        return this.currentTimelineItemIndex;
    }
}
