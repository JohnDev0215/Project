package org.apache.shiro.cache;

import org.apache.shiro.util.SoftHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MemoryConstrainedCacheManager extends AbstractCacheManager {
    @Override // org.apache.shiro.cache.AbstractCacheManager
    protected Cache createCache(String str) {
        return new MapCache(str, new SoftHashMap());
    }
}
