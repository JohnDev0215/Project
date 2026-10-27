package com.sun.xml.bind.v2.schemagen;

import java.lang.Comparable;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes3.dex */
final class MultiMap<K extends Comparable<K>, V> extends TreeMap<K, V> {
    private final V many;

    public MultiMap(V v) {
        this.many = v;
    }

    @Override // java.util.TreeMap, java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        V v2 = (V) super.put(k, (Object) v);
        if (v2 != null && !v2.equals(v)) {
            super.put(k, (Object) this.many);
        }
        return v2;
    }

    @Override // java.util.TreeMap, java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }
}
