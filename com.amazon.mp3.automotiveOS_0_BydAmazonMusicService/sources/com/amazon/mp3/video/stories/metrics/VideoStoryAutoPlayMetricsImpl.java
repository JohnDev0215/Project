package com.amazon.mp3.video.stories.metrics;

import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoStoryAutoPlayMetricsImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J \u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0016J\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\rH\u0016¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/video/stories/metrics/VideoStoryAutoPlayMetricsImpl;", "", "", "storyId", "", "storyIPDMS", "", "sendIPDMSMetrics", "durationValue", "", "numberOfAutoPlayLoops", "sendAutoPlayDurationMetrics", "viewId", "Lcom/amazon/mp3/video/stories/metrics/EntityRenderingType;", "renderingType", "sendVideoStoryContentViewMetrics", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoStoryAutoPlayMetricsImpl {
    public void sendIPDMSMetrics(String storyId, float storyIPDMS) {
        Intrinsics.checkNotNullParameter(storyId, "storyId");
        MetricsLogger.sendEvent(FlexEvent.builder(AutoPlayMetricsEvent.EVENT_NAME_VIDEO_STORY_AUTOPLAY_IPDMS.getMetricValue()).withFlexStr1(storyId).withFlexNum1(Float.valueOf(storyIPDMS)).build());
    }

    public void sendAutoPlayDurationMetrics(String storyId, float durationValue, int numberOfAutoPlayLoops) {
        Intrinsics.checkNotNullParameter(storyId, "storyId");
        MetricsLogger.sendEvent(FlexEvent.builder(AutoPlayMetricsEvent.EVENT_NAME_VIDEO_STORY_AUTOPLAY_DURATION.getMetricValue()).withFlexStr1(storyId).withFlexNum1(Float.valueOf(durationValue)).withFlexNum2(Float.valueOf(numberOfAutoPlayLoops)).build());
    }

    public void sendVideoStoryContentViewMetrics(String viewId, EntityRenderingType renderingType) {
        Intrinsics.checkNotNullParameter(viewId, "viewId");
        Intrinsics.checkNotNullParameter(renderingType, "renderingType");
        MetricsLogger.sendEvent(FlexEvent.builder(AutoPlayMetricsEvent.EVENT_NAME_VIDEO_STORY_AUTOPLAY_CONTENT_VIEW.getMetricValue()).withFlexStr1(viewId).withFlexStr2(renderingType.getMetricValue()).build());
    }
}
