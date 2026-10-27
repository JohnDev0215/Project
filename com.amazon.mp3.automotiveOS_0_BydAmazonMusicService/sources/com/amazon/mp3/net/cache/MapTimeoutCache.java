package com.amazon.mp3.net.cache;

import com.amazon.mp3.util.LruHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class MapTimeoutCache<K, V> extends TimeoutCache<K, V> {
    private final Map<K, CacheData<V>> mMap;

    public MapTimeoutCache(long cacheTimeout) {
        super(cacheTimeout);
        this.mMap = new LruHashMap(128);
    }

    @Override // com.amazon.mp3.net.cache.TimeoutCache
    protected synchronized void insert(K id, CacheData<V> data) {
        this.mMap.put(id, data);
    }

    @Override // com.amazon.mp3.net.cache.TimeoutCache
    protected synchronized CacheData<V> getCacheData(K id) {
        return this.mMap.get(id);
    }

    @Override // com.amazon.mp3.net.cache.TimeoutCache
    protected synchronized void remove(K id) {
        this.mMap.remove(id);
    }
}
