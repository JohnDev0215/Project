package com.netinsight.sye.syeClient.timeshift;

import com.netinsight.sye.syeClient.generated.enums.PlaybackType;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001R\u0016\u0010\u0005\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0016\u0010\t\u001a\u00020\u00068&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\r\u001a\u00020\n8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0011\u001a\u00020\u000e8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0013\u001a\u00020\u000e8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u0014"}, d2 = {"Lcom/netinsight/sye/syeClient/timeshift/ISyeTimelineInfo;", "", "", "getShowTimeline", "()Z", "showTimeline", "", "getThumbnailFillrate", "()D", "thumbnailFillrate", "Lcom/netinsight/sye/syeClient/generated/enums/PlaybackType;", "getPlaybackType", "()Lcom/netinsight/sye/syeClient/generated/enums/PlaybackType;", "playbackType", "", "getCurrentPosUtcMillis", "()J", "currentPosUtcMillis", "getLivePosUtcMillis", "livePosUtcMillis", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface ISyeTimelineInfo {
    long getCurrentPosUtcMillis();

    long getLivePosUtcMillis();

    PlaybackType getPlaybackType();

    boolean getShowTimeline();

    double getThumbnailFillrate();
}
