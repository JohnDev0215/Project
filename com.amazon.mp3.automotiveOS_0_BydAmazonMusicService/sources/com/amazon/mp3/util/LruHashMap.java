package com.amazon.mp3.util;

import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class LruHashMap<K1, V1> extends LinkedHashMap<K1, V1> {
    private int mMaxCacheSize;

    public LruHashMap(int maxCacheSize) {
        super(maxCacheSize + 1, 1.0f, true);
        this.mMaxCacheSize = maxCacheSize;
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry<K1, V1> eldest) {
        return size() >= this.mMaxCacheSize;
    }
}
