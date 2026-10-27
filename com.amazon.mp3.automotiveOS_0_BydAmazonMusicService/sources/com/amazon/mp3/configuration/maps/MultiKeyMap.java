package com.amazon.mp3.configuration.maps;

import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class MultiKeyMap<K, V, W> {
    HashMap<K, HashMap<V, W>> mMap = new HashMap<>();

    protected MultiKeyMap(K... keys) {
        for (K k : keys) {
            this.mMap.put(k, new HashMap<>());
        }
    }

    protected W put(K keyName, V key, W value) {
        HashMap<V, W> map = this.mMap.get(keyName);
        if (map != null) {
            return map.put(key, value);
        }
        return null;
    }

    protected void put(K keyName, V[] keys, W value) {
        HashMap<V, W> map = this.mMap.get(keyName);
        if (map != null) {
            for (V v : keys) {
                map.put(v, value);
            }
        }
    }

    protected W get(K keyName, V key) {
        HashMap<V, W> map = this.mMap.get(keyName);
        if (map != null) {
            return map.get(key);
        }
        return null;
    }
}
