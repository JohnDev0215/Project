package com.amazon.mp3.library.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class LruCachePolicy<K, V> implements CachePolicy<K, V> {
    private int mSize;
    private boolean mSoftCacheEnabled;
    private SoftCachePolicy<K, V> mSoftCache = new SoftCachePolicy<>();
    private HashMap<String, LruHashMap<K, V>> mSources = new HashMap<>();

    protected void onElementRemoved(String sourceId, K key, V value) {
    }

    public LruCachePolicy(int maxSize, boolean enableSoftCache) {
        this.mSize = 200;
        this.mSize = maxSize;
        this.mSoftCacheEnabled = enableSoftCache;
    }

    @Override // com.amazon.mp3.library.cache.CachePolicy
    public void clear() {
        this.mSources.clear();
        this.mSoftCache.clear();
    }

    @Override // com.amazon.mp3.library.cache.CachePolicy
    public boolean contains(String sourceId, K key) {
        LruHashMap<K, V> lruHashMap = this.mSources.get(sourceId);
        if (lruHashMap == null || !lruHashMap.containsKey(key)) {
            return this.mSoftCache.contains(sourceId, key);
        }
        return true;
    }

    @Override // com.amazon.mp3.library.cache.CachePolicy
    public V get(String sourceId, K key) {
        LruHashMap<K, V> lruHashMap = this.mSources.get(sourceId);
        V v = lruHashMap != null ? lruHashMap.get(key) : null;
        if (v == null && (v = this.mSoftCache.get(sourceId, key)) != null && lruHashMap != null) {
            synchronized (lruHashMap) {
                lruHashMap.put(key, v);
            }
        }
        return v == null ? this.mSoftCache.get(sourceId, key) : v;
    }

    @Override // com.amazon.mp3.library.cache.CachePolicy
    public void put(String sourceId, K key, V value) {
        LruHashMap<K, V> lruHashMap = this.mSources.get(sourceId);
        if (lruHashMap == null) {
            lruHashMap = new LruHashMap<>(sourceId, this);
            this.mSources.put(sourceId, lruHashMap);
        }
        synchronized (lruHashMap) {
            lruHashMap.put(key, value);
        }
    }

    @Override // com.amazon.mp3.library.cache.CachePolicy
    public void remove(String sourceId, K key) {
        LruHashMap<K, V> lruHashMap = this.mSources.get(sourceId);
        if (lruHashMap != null) {
            synchronized (lruHashMap) {
                lruHashMap.remove(key);
            }
        }
        this.mSoftCache.remove(sourceId, key);
    }

    @Override // com.amazon.mp3.library.cache.CachePolicy
    public void backgroundCache() {
        HashMap map;
        for (Map.Entry<String, LruHashMap<K, V>> entry : this.mSources.entrySet()) {
            String key = entry.getKey();
            LruHashMap<K, V> value = entry.getValue();
            synchronized (value) {
                map = new HashMap(value);
                value.clear();
            }
            if (this.mSoftCacheEnabled) {
                for (Map.Entry<K, V> entry2 : map.entrySet()) {
                    this.mSoftCache.put(key, entry2.getKey(), entry2.getValue());
                }
            }
        }
    }

    protected Iterator<V> iterator(String sourceId) {
        LruHashMap<K, V> lruHashMap = this.mSources.get(sourceId);
        if (lruHashMap == null) {
            return null;
        }
        return lruHashMap.values().iterator();
    }

    private static final class LruHashMap<K1, V1> extends LinkedHashMap<K1, V1> {
        private transient LruCachePolicy<K1, V1> mCachePolicy;
        private String mSourceId;

        public LruHashMap(String sourceId, LruCachePolicy<K1, V1> cachePolicy) {
            super(64, 0.75f, true);
            this.mSourceId = sourceId;
            this.mCachePolicy = cachePolicy;
        }

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<K1, V1> eldest) {
            if (size() < ((LruCachePolicy) this.mCachePolicy).mSize) {
                return false;
            }
            if (((LruCachePolicy) this.mCachePolicy).mSoftCacheEnabled) {
                ((LruCachePolicy) this.mCachePolicy).mSoftCache.put(this.mSourceId, eldest.getKey(), eldest.getValue());
            }
            this.mCachePolicy.onElementRemoved(this.mSourceId, eldest.getKey(), eldest.getValue());
            return true;
        }
    }
}
