package com.amazon.video.sdk.player.timeline;

import kotlin.Metadata;

/* JADX INFO: compiled from: TimeData.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/amazon/video/sdk/player/timeline/TimeData;", "", "currentPosition", "", "getCurrentPosition", "()J", "currentTimelineItemIndex", "getCurrentTimelineItemIndex", "playableRange", "Lcom/amazon/video/sdk/player/timeline/PlayableRange;", "getPlayableRange", "()Lcom/amazon/video/sdk/player/timeline/PlayableRange;", "unit", "Lcom/amazon/video/sdk/player/timeline/TimeUnit;", "getUnit", "()Lcom/amazon/video/sdk/player/timeline/TimeUnit;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface TimeData {
    long getCurrentPosition();

    long getCurrentTimelineItemIndex();

    PlayableRange getPlayableRange();

    TimeUnit getUnit();
}
