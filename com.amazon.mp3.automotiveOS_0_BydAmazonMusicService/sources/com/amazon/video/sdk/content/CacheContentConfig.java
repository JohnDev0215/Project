package com.amazon.video.sdk.content;

import com.amazon.video.sdk.player.ContentConfig;
import com.amazon.video.sdk.stream.AudioStreamMatcher;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: CacheContentConfig.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/amazon/video/sdk/content/CacheContentConfig;", "", "Lcom/amazon/video/sdk/player/ContentConfig;", "getContentConfig", "()Lcom/amazon/video/sdk/player/ContentConfig;", "contentConfig", "", "Lcom/amazon/video/sdk/stream/AudioStreamMatcher;", "getAudioStreamMatchers", "()Ljava/util/List;", "audioStreamMatchers", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public interface CacheContentConfig {
    List<AudioStreamMatcher> getAudioStreamMatchers();

    ContentConfig getContentConfig();
}
