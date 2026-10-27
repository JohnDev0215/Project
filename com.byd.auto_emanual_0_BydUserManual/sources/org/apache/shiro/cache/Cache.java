package org.apache.shiro.cache;

import java.util.Collection;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public interface Cache<K, V> {
    void clear() throws CacheException;

    V get(K k) throws CacheException;

    Set<K> keys();

    V put(K k, V v) throws CacheException;

    V remove(K k) throws CacheException;

    int size();

    Collection<V> values();
}
