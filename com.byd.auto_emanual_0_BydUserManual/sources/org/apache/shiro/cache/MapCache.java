package org.apache.shiro.cache;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class MapCache<K, V> implements Cache<K, V> {
    private final Map<K, V> map;
    private final String name;

    public MapCache(String str, Map<K, V> map) {
        if (str == null) {
            throw new IllegalArgumentException("Cache name cannot be null.");
        }
        if (map == null) {
            throw new IllegalArgumentException("Backing map cannot be null.");
        }
        this.name = str;
        this.map = map;
    }

    @Override // org.apache.shiro.cache.Cache
    public V get(K k) throws CacheException {
        return this.map.get(k);
    }

    @Override // org.apache.shiro.cache.Cache
    public V put(K k, V v) throws CacheException {
        return this.map.put(k, v);
    }

    @Override // org.apache.shiro.cache.Cache
    public V remove(K k) throws CacheException {
        return this.map.remove(k);
    }

    @Override // org.apache.shiro.cache.Cache
    public void clear() throws CacheException {
        this.map.clear();
    }

    @Override // org.apache.shiro.cache.Cache
    public int size() {
        return this.map.size();
    }

    @Override // org.apache.shiro.cache.Cache
    public Set<K> keys() {
        Set<K> setKeySet = this.map.keySet();
        if (!setKeySet.isEmpty()) {
            return Collections.unmodifiableSet(setKeySet);
        }
        return Collections.emptySet();
    }

    @Override // org.apache.shiro.cache.Cache
    public Collection<V> values() {
        Collection<V> collectionValues = this.map.values();
        if (!collectionValues.isEmpty()) {
            return Collections.unmodifiableCollection(collectionValues);
        }
        return Collections.emptyList();
    }

    public String toString() {
        return "MapCache '" + this.name + "' (" + this.map.size() + " entries)";
    }
}
