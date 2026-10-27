package com.amazon.video.sdk.player.timeline;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TimelineItem.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0080\b\u0018\u00002\u00020\u0001BI\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u001f\u0010 J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R\u001a\u0010\u0017\u001a\u00020\u00168\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u000f8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u0011\u001a\u0004\b\u001c\u0010\u0013R\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u000f8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u0011\u001a\u0004\b\u001e\u0010\u0013¨\u0006!"}, d2 = {"Lcom/amazon/video/sdk/player/timeline/TimelineItemData;", "Lcom/amazon/video/sdk/player/timeline/TimelineItem;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/player/timeline/TimeUnit;", "timeUnit", "Lcom/amazon/video/sdk/player/timeline/TimeUnit;", "getTimeUnit", "()Lcom/amazon/video/sdk/player/timeline/TimeUnit;", "", "startTime", "Ljava/lang/Long;", "getStartTime", "()Ljava/lang/Long;", "endTime", "getEndTime", "Lcom/amazon/video/sdk/player/timeline/ContentInfo;", ContextMappingConstants.CONTENT_INFO, "Lcom/amazon/video/sdk/player/timeline/ContentInfo;", "getContentInfo", "()Lcom/amazon/video/sdk/player/timeline/ContentInfo;", "absoluteStartTime", "getAbsoluteStartTime$AmazonAndroidVideoPlayer_release", "absoluteEndTime", "getAbsoluteEndTime$AmazonAndroidVideoPlayer_release", "<init>", "(Lcom/amazon/video/sdk/player/timeline/TimeUnit;Ljava/lang/Long;Ljava/lang/Long;Lcom/amazon/video/sdk/player/timeline/ContentInfo;Ljava/lang/Long;Ljava/lang/Long;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class TimelineItemData implements TimelineItem {
    private final Long absoluteEndTime;
    private final Long absoluteStartTime;
    private final ContentInfo contentInfo;
    private final Long endTime;
    private final Long startTime;
    private final TimeUnit timeUnit;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TimelineItemData)) {
            return false;
        }
        TimelineItemData timelineItemData = (TimelineItemData) other;
        return getTimeUnit() == timelineItemData.getTimeUnit() && Intrinsics.areEqual(getStartTime(), timelineItemData.getStartTime()) && Intrinsics.areEqual(getEndTime(), timelineItemData.getEndTime()) && Intrinsics.areEqual(getContentInfo(), timelineItemData.getContentInfo()) && Intrinsics.areEqual(this.absoluteStartTime, timelineItemData.absoluteStartTime) && Intrinsics.areEqual(this.absoluteEndTime, timelineItemData.absoluteEndTime);
    }

    public int hashCode() {
        int iHashCode = ((((((getTimeUnit().hashCode() * 31) + (getStartTime() == null ? 0 : getStartTime().hashCode())) * 31) + (getEndTime() == null ? 0 : getEndTime().hashCode())) * 31) + getContentInfo().hashCode()) * 31;
        Long l = this.absoluteStartTime;
        int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
        Long l2 = this.absoluteEndTime;
        return iHashCode2 + (l2 != null ? l2.hashCode() : 0);
    }

    public String toString() {
        return "TimelineItemData(timeUnit=" + getTimeUnit() + ", startTime=" + getStartTime() + ", endTime=" + getEndTime() + ", contentInfo=" + getContentInfo() + ", absoluteStartTime=" + this.absoluteStartTime + ", absoluteEndTime=" + this.absoluteEndTime + ')';
    }

    public TimelineItemData(TimeUnit timeUnit, Long l, Long l2, ContentInfo contentInfo, Long l3, Long l4) {
        Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
        Intrinsics.checkNotNullParameter(contentInfo, "contentInfo");
        this.timeUnit = timeUnit;
        this.startTime = l;
        this.endTime = l2;
        this.contentInfo = contentInfo;
        this.absoluteStartTime = l3;
        this.absoluteEndTime = l4;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TimelineItemData(TimeUnit timeUnit, Long l, Long l2, ContentInfo contentInfo, Long l3, Long l4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        TimeUnit timeUnit2 = (i & 1) != 0 ? TimeUnit.CONTENT_MILLIS : timeUnit;
        Long l5 = (i & 2) != 0 ? null : l;
        Long l6 = (i & 4) != 0 ? null : l2;
        this(timeUnit2, l5, l6, contentInfo, (i & 16) != 0 ? l5 : l3, (i & 32) != 0 ? l6 : l4);
    }

    public TimeUnit getTimeUnit() {
        return this.timeUnit;
    }

    @Override // com.amazon.video.sdk.player.timeline.TimelineItem
    public Long getStartTime() {
        return this.startTime;
    }

    @Override // com.amazon.video.sdk.player.timeline.TimelineItem
    public Long getEndTime() {
        return this.endTime;
    }

    @Override // com.amazon.video.sdk.player.timeline.TimelineItem
    public ContentInfo getContentInfo() {
        return this.contentInfo;
    }

    /* JADX INFO: renamed from: getAbsoluteStartTime$AmazonAndroidVideoPlayer_release, reason: from getter */
    public final Long getAbsoluteStartTime() {
        return this.absoluteStartTime;
    }

    /* JADX INFO: renamed from: getAbsoluteEndTime$AmazonAndroidVideoPlayer_release, reason: from getter */
    public final Long getAbsoluteEndTime() {
        return this.absoluteEndTime;
    }
}
