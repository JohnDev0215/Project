package com.amazon.video.sdk.stream;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: StreamFeature.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/amazon/video/sdk/stream/StreamFeatureConfig;", "", "preferredAudioStreams", "", "Lcom/amazon/video/sdk/stream/AudioStreamMatcher;", "getPreferredAudioStreams", "()Ljava/util/List;", "preferredTimedTextStreams", "Lcom/amazon/video/sdk/stream/TimedTextStreamMatcher;", "getPreferredTimedTextStreams", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface StreamFeatureConfig {
    List<AudioStreamMatcher> getPreferredAudioStreams();

    List<TimedTextStreamMatcher> getPreferredTimedTextStreams();
}
