package com.amazon.video.sdk.content;

import com.amazon.video.sdk.player.ContentConfig;
import com.amazon.video.sdk.stream.AudioStreamMatcher;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CacheContentConfig.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\u0004\b\u001a\u0010\u001bJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0010\u001a\u00020\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001c"}, d2 = {"Lcom/amazon/video/sdk/content/CacheContentConfigData;", "Lcom/amazon/video/sdk/content/CacheContentConfig;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/player/ContentConfig;", "contentConfig", "Lcom/amazon/video/sdk/player/ContentConfig;", "getContentConfig", "()Lcom/amazon/video/sdk/player/ContentConfig;", "Lcom/amazon/video/sdk/content/CacheConfig;", "cacheConfig", "Lcom/amazon/video/sdk/content/CacheConfig;", "getCacheConfig", "()Lcom/amazon/video/sdk/content/CacheConfig;", "", "Lcom/amazon/video/sdk/stream/AudioStreamMatcher;", "audioStreamMatchers", "Ljava/util/List;", "getAudioStreamMatchers", "()Ljava/util/List;", "<init>", "(Lcom/amazon/video/sdk/player/ContentConfig;Lcom/amazon/video/sdk/content/CacheConfig;Ljava/util/List;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class CacheContentConfigData implements CacheContentConfig {
    private final List<AudioStreamMatcher> audioStreamMatchers;
    private final CacheConfig cacheConfig;
    private final ContentConfig contentConfig;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CacheContentConfigData)) {
            return false;
        }
        CacheContentConfigData cacheContentConfigData = (CacheContentConfigData) other;
        return Intrinsics.areEqual(getContentConfig(), cacheContentConfigData.getContentConfig()) && Intrinsics.areEqual(getCacheConfig(), cacheContentConfigData.getCacheConfig()) && Intrinsics.areEqual(getAudioStreamMatchers(), cacheContentConfigData.getAudioStreamMatchers());
    }

    public int hashCode() {
        return (((getContentConfig().hashCode() * 31) + getCacheConfig().hashCode()) * 31) + getAudioStreamMatchers().hashCode();
    }

    public String toString() {
        return "CacheContentConfigData(contentConfig=" + getContentConfig() + ", cacheConfig=" + getCacheConfig() + ", audioStreamMatchers=" + getAudioStreamMatchers() + ')';
    }

    public CacheContentConfigData(ContentConfig contentConfig, CacheConfig cacheConfig, List<AudioStreamMatcher> audioStreamMatchers) {
        Intrinsics.checkNotNullParameter(contentConfig, "contentConfig");
        Intrinsics.checkNotNullParameter(cacheConfig, "cacheConfig");
        Intrinsics.checkNotNullParameter(audioStreamMatchers, "audioStreamMatchers");
        this.contentConfig = contentConfig;
        this.cacheConfig = cacheConfig;
        this.audioStreamMatchers = audioStreamMatchers;
    }

    @Override // com.amazon.video.sdk.content.CacheContentConfig
    public ContentConfig getContentConfig() {
        return this.contentConfig;
    }

    public CacheConfig getCacheConfig() {
        return this.cacheConfig;
    }

    @Override // com.amazon.video.sdk.content.CacheContentConfig
    public List<AudioStreamMatcher> getAudioStreamMatchers() {
        return this.audioStreamMatchers;
    }
}
