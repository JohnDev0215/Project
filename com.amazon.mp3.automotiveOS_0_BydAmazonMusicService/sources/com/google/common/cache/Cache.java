package com.google.common.cache;

import java.util.concurrent.ConcurrentMap;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public interface Cache<K, V> {
    ConcurrentMap<K, V> asMap();

    void cleanUp();

    @CheckForNull
    V getIfPresent(Object obj);

    void invalidate(Object obj);

    void invalidateAll();

    void put(K k, V v);
}
