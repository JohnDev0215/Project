package com.amazon.video.sdk.player.timeline;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import kotlin.Metadata;

/* JADX INFO: compiled from: TimelineItem.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004R\u0014\u0010\u000b\u001a\u00020\b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lcom/amazon/video/sdk/player/timeline/TimelineItem;", "", "", "getStartTime", "()Ljava/lang/Long;", "startTime", "getEndTime", "endTime", "Lcom/amazon/video/sdk/player/timeline/ContentInfo;", "getContentInfo", "()Lcom/amazon/video/sdk/player/timeline/ContentInfo;", ContextMappingConstants.CONTENT_INFO, "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public interface TimelineItem {
    ContentInfo getContentInfo();

    Long getEndTime();

    Long getStartTime();
}
