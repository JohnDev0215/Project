package org.apache.shiro.cache;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.shiro.config.Ini;
import org.apache.shiro.util.Destroyable;
import org.apache.shiro.util.LifecycleUtils;
import org.apache.shiro.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractCacheManager implements CacheManager, Destroyable {
    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap();

    protected abstract Cache createCache(String str) throws CacheException;

    @Override // org.apache.shiro.cache.CacheManager
    public <K, V> Cache<K, V> getCache(String str) throws CacheException, IllegalArgumentException {
        if (!StringUtils.hasText(str)) {
            throw new IllegalArgumentException("Cache name cannot be null or empty.");
        }
        Cache<K, V> cache = this.caches.get(str);
        if (cache != null) {
            return cache;
        }
        Cache<K, V> cacheCreateCache = createCache(str);
        Cache<K, V> cachePutIfAbsent = this.caches.putIfAbsent(str, cacheCreateCache);
        return cachePutIfAbsent != null ? cachePutIfAbsent : cacheCreateCache;
    }

    @Override // org.apache.shiro.util.Destroyable
    public void destroy() throws Exception {
        while (!this.caches.isEmpty()) {
            Iterator<Cache> it = this.caches.values().iterator();
            while (it.hasNext()) {
                LifecycleUtils.destroy(it.next());
            }
            this.caches.clear();
        }
    }

    public String toString() {
        Collection<Cache> collectionValues = this.caches.values();
        StringBuilder sbAppend = new StringBuilder(getClass().getSimpleName()).append(" with ").append(this.caches.size()).append(" cache(s)): [");
        int i = 0;
        for (Cache cache : collectionValues) {
            if (i > 0) {
                sbAppend.append(", ");
            }
            sbAppend.append(cache.toString());
            i++;
        }
        sbAppend.append(Ini.SECTION_SUFFIX);
        return sbAppend.toString();
    }
}
