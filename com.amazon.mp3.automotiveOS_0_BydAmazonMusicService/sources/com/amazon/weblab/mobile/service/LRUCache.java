package com.amazon.weblab.mobile.service;

import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int cacheSize;

    public LRUCache(int i, int i2) {
        super(i, 0.75f, true);
        this.cacheSize = i2;
    }

    public LRUCache(int i) {
        this(32, i);
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
        return size() >= this.cacheSize;
    }
}
