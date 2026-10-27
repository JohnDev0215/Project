package com.amazon.video.sdk.stream;

import com.amazon.video.sdk.Feature;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: StreamFeature.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&R\u0016\u0010\t\u001a\u0004\u0018\u00010\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\"\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/amazon/video/sdk/stream/StreamFeature;", "", "Lcom/amazon/video/sdk/stream/TimedTextConfig;", "timedTextConfig", "", "setTimedTextConfig", "Lcom/amazon/video/sdk/stream/TimedTextStreamGroup;", "getTimedTextStreams", "()Lcom/amazon/video/sdk/stream/TimedTextStreamGroup;", "timedTextStreams", "", "Lcom/amazon/video/sdk/stream/AudioStreamMatcher;", "getAudioStreamPreferences", "()Ljava/util/List;", "setAudioStreamPreferences", "(Ljava/util/List;)V", "audioStreamPreferences", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public interface StreamFeature extends Feature {
    List<AudioStreamMatcher> getAudioStreamPreferences();

    TimedTextStreamGroup getTimedTextStreams();

    void setTimedTextConfig(TimedTextConfig timedTextConfig);
}
