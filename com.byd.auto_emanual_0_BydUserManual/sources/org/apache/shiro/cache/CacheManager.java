package org.apache.shiro.cache;

/* JADX INFO: loaded from: classes.dex */
public interface CacheManager {
    <K, V> Cache<K, V> getCache(String str) throws CacheException;
}
