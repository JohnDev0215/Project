package com.amazon.video.sdk.content;

import com.amazon.video.sdk.Feature;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: LocalContentManager.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/video/sdk/content/LocalContentManager;", "Lcom/amazon/video/sdk/Feature;", "", "Lcom/amazon/video/sdk/content/CacheContentConfig;", "cacheContentConfigList", "", "cache", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public interface LocalContentManager extends Feature {
    void cache(List<? extends CacheContentConfig> cacheContentConfigList);
}
