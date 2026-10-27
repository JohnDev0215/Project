package com.amazon.video.sdk.player.timeline;

import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.playback.VideoPresentation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TimelineImpl.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0010¢\u0006\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/amazon/video/sdk/player/timeline/TimelineDelegate;", "", "()V", "updateTimeline", "Lcom/amazon/video/sdk/player/timeline/Timeline;", "videoPresentation", "Lcom/amazon/avod/media/playback/VideoPresentation;", "adPlan", "Lcom/amazon/avod/media/ads/AdPlan;", "updateTimeline$AmazonAndroidVideoPlayer_release", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class TimelineDelegate {
    public Timeline updateTimeline$AmazonAndroidVideoPlayer_release(VideoPresentation videoPresentation, AdPlan adPlan) {
        Intrinsics.checkNotNullParameter(adPlan, "adPlan");
        return TimelineImpl.INSTANCE.updateTimeline$AmazonAndroidVideoPlayer_release(videoPresentation, adPlan);
    }
}
