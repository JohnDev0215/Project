package com.amazon.video.sdk.content;

import com.amazon.avod.media.TimeSpan;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CacheConfig.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0010\u001a\u00020\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/video/sdk/content/CacheConfigData;", "Lcom/amazon/video/sdk/content/CacheConfig;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/content/CachePriority;", "cachePriority", "Lcom/amazon/video/sdk/content/CachePriority;", "getCachePriority", "()Lcom/amazon/video/sdk/content/CachePriority;", "Lcom/amazon/avod/media/TimeSpan;", "timeToLive", "Lcom/amazon/avod/media/TimeSpan;", "getTimeToLive", "()Lcom/amazon/avod/media/TimeSpan;", "<init>", "(Lcom/amazon/video/sdk/content/CachePriority;Lcom/amazon/avod/media/TimeSpan;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class CacheConfigData implements CacheConfig {
    private final CachePriority cachePriority;
    private final TimeSpan timeToLive;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CacheConfigData)) {
            return false;
        }
        CacheConfigData cacheConfigData = (CacheConfigData) other;
        return getCachePriority() == cacheConfigData.getCachePriority() && Intrinsics.areEqual(getTimeToLive(), cacheConfigData.getTimeToLive());
    }

    public int hashCode() {
        return (getCachePriority().hashCode() * 31) + getTimeToLive().hashCode();
    }

    public String toString() {
        return "CacheConfigData(cachePriority=" + getCachePriority() + ", timeToLive=" + getTimeToLive() + ')';
    }

    public CacheConfigData(CachePriority cachePriority, TimeSpan timeToLive) {
        Intrinsics.checkNotNullParameter(cachePriority, "cachePriority");
        Intrinsics.checkNotNullParameter(timeToLive, "timeToLive");
        this.cachePriority = cachePriority;
        this.timeToLive = timeToLive;
    }

    public CachePriority getCachePriority() {
        return this.cachePriority;
    }

    public TimeSpan getTimeToLive() {
        return this.timeToLive;
    }
}
