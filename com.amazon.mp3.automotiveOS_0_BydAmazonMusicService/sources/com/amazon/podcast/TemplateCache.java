package com.amazon.podcast;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public enum TemplateCache {
    INSTANCE;

    private static final String TAG = "TemplateCache";
    private static final Logger logger = LoggerFactory.getLogger(TAG);
    private final Map<String, TemplateCacheEntry> cache = new HashMap();

    TemplateCache() {
    }

    public TemplateCacheEntry read(final String ownerId) {
        return this.cache.get(ownerId);
    }

    public void write(final String ownerId, final TemplateCacheEntry entry) {
        this.cache.put(ownerId, entry);
    }

    public void keep(final Set<String> ownerId) {
        Set<String> setKeySet = this.cache.keySet();
        HashSet<String> hashSet = new HashSet();
        for (String str : setKeySet) {
            if (!ownerId.contains(str) && !this.cache.get(str).isAlertTemplate()) {
                hashSet.add(str);
            }
        }
        for (String str2 : hashSet) {
            TemplateCacheEntry templateCacheEntry = this.cache.get(str2);
            if (templateCacheEntry != null) {
                logger.debug("Removing template {}", templateCacheEntry.getTemplate().getClass().getSimpleName());
                this.cache.remove(str2);
            }
        }
    }

    public void delete(final String ownerId) {
        this.cache.remove(ownerId);
    }
}
