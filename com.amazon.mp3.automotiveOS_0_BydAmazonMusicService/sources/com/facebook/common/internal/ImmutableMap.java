package com.facebook.common.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class ImmutableMap<K, V> extends HashMap<K, V> {
    private ImmutableMap(Map<? extends K, ? extends V> map) {
        super(map);
    }

    public static <K, V> Map<K, V> of(K k1, V v1) {
        HashMap map = new HashMap(1);
        map.put(k1, v1);
        return Collections.unmodifiableMap(map);
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
        HashMap map = new HashMap(2);
        map.put(k1, v1);
        map.put(k2, v2);
        return Collections.unmodifiableMap(map);
    }

    public static <K, V> ImmutableMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        return new ImmutableMap<>(map);
    }
}
