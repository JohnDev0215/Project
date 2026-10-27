package com.amazon.mp3.library.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class SoftCachePolicy<K, V> implements CachePolicy<K, V> {
    private HashMap<String, HashMap<K, SoftReference<V>>> mSources = new HashMap<>();

    @Override // com.amazon.mp3.library.cache.CachePolicy
    public void backgroundCache() {
    }

    @Override // com.amazon.mp3.library.cache.CachePolicy
    public void clear() {
        this.mSources.clear();
    }

    @Override // com.amazon.mp3.library.cache.CachePolicy
    public boolean contains(String sourceId, K key) {
        return get(sourceId, key) != null;
    }

    @Override // com.amazon.mp3.library.cache.CachePolicy
    public V get(String sourceId, K key) {
        SoftReference<V> softReference;
        HashMap<K, SoftReference<V>> map = this.mSources.get(sourceId);
        if (map == null || (softReference = map.get(key)) == null) {
            return null;
        }
        V v = softReference.get();
        if (v != null) {
            return v;
        }
        map.remove(key);
        return v;
    }

    @Override // com.amazon.mp3.library.cache.CachePolicy
    public void put(String sourceId, K key, V value) {
        HashMap<K, SoftReference<V>> map = this.mSources.get(sourceId);
        if (map == null) {
            map = new HashMap<>();
            this.mSources.put(sourceId, map);
        }
        map.put(key, new SoftReference<>(value));
    }

    @Override // com.amazon.mp3.library.cache.CachePolicy
    public void remove(String sourceId, K key) {
        HashMap<K, SoftReference<V>> map = this.mSources.get(sourceId);
        if (map != null) {
            map.remove(key);
        }
    }
}
