package com.amazon.mp3.library.cache;

/* JADX INFO: loaded from: classes3.dex */
public interface CachePolicy<K, V> {
    void backgroundCache();

    void clear();

    boolean contains(String sourceId, K key);

    V get(String sourceId, K key);

    void put(String sourceId, K key, V value);

    void remove(String sourceId, K key);
}
