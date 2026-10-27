package com.amazon.video.sdk.player.timeline;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: Timeline.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/video/sdk/player/timeline/Timeline;", "", ContextMappingConstants.ITEMS, "", "Lcom/amazon/video/sdk/player/timeline/TimelineItem;", "getItems", "()Ljava/util/List;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface Timeline {
    List<TimelineItem> getItems();
}
