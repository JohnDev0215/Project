package com.amazon.mp3.library.cache.videostory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoPlaybackCache.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0018\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016R\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\"\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/library/cache/videostory/VideoPlaybackCache;", "", "", "key", "", "isExpired", "Lcom/amazon/mp3/library/cache/videostory/CachedData;", "data", "", "put", "get", "removeExpiredKeys", "", "maxCacheSize", "I", "", "videoPlaybackCache", "Ljava/util/Map;", "<init>", "(I)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoPlaybackCache {
    private final int maxCacheSize;
    private Map<String, CachedData> videoPlaybackCache = new LinkedHashMap();

    public VideoPlaybackCache(int i) {
        this.maxCacheSize = i;
    }

    public void put(CachedData data, String key) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(key, "key");
        if (this.videoPlaybackCache.size() >= this.maxCacheSize) {
            Map<String, CachedData> map = this.videoPlaybackCache;
            map.remove(CollectionsKt___CollectionsKt.first(map.keySet()));
        }
        this.videoPlaybackCache.put(key, data);
    }

    public CachedData get(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        CachedData cachedData = this.videoPlaybackCache.get(key);
        if (isExpired(key)) {
            this.videoPlaybackCache.remove(key);
        }
        return cachedData;
    }

    public void removeExpiredKeys() {
        Set<String> setKeySet = this.videoPlaybackCache.keySet();
        ArrayList arrayList = new ArrayList();
        for (String str : setKeySet) {
            if (isExpired(str)) {
                arrayList.add(str);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.videoPlaybackCache.remove((String) it.next());
        }
    }

    private final boolean isExpired(String key) {
        CachedData cachedData = this.videoPlaybackCache.get(key);
        return cachedData != null && cachedData.getExpiredTime().compareTo(new Date()) <= 0;
    }
}
