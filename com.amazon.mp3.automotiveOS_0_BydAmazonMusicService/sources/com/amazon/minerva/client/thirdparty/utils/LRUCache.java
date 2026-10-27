package com.amazon.minerva.client.thirdparty.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int CAPACITY;

    public LRUCache(int i) {
        super(i, 0.75f, true);
        this.CAPACITY = i;
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry entry) {
        return size() > this.CAPACITY;
    }

    public int getCapacity() {
        return this.CAPACITY;
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Objects.requireNonNull(obj, "Key cannot be null in get method.");
        if (containsKey(obj)) {
            return (V) super.get(obj);
        }
        return null;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        Objects.requireNonNull(k, "Key cannot be null in put method.");
        Objects.requireNonNull(v, "Value cannot be null in put method.");
        return (V) super.put(k, v);
    }
}
