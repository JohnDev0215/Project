package com.amazon.mp3.video.stories.metrics;

import kotlin.Metadata;

/* JADX INFO: compiled from: VideoStoryAutoPlayMetrics.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/video/stories/metrics/AutoPlayMetricsEvent;", "", "metricValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getMetricValue", "()Ljava/lang/String;", "EVENT_NAME_VIDEO_STORY_AUTOPLAY_IPDMS", "EVENT_NAME_VIDEO_STORY_AUTOPLAY_DURATION", "EVENT_NAME_VIDEO_STORY_AUTOPLAY_CONTENT_VIEW", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum AutoPlayMetricsEvent {
    EVENT_NAME_VIDEO_STORY_AUTOPLAY_IPDMS("videoStoryAutoPlayIPDMS"),
    EVENT_NAME_VIDEO_STORY_AUTOPLAY_DURATION("videoStoryAutoPlayDuration"),
    EVENT_NAME_VIDEO_STORY_AUTOPLAY_CONTENT_VIEW("videoStoryAutoPlayContentView");

    private final String metricValue;

    AutoPlayMetricsEvent(String str) {
        this.metricValue = str;
    }

    public final String getMetricValue() {
        return this.metricValue;
    }
}
