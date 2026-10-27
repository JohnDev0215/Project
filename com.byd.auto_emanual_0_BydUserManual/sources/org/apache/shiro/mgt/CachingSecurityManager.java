package org.apache.shiro.mgt;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.CacheManagerAware;
import org.apache.shiro.event.EventBus;
import org.apache.shiro.event.EventBusAware;
import org.apache.shiro.event.support.DefaultEventBus;
import org.apache.shiro.util.Destroyable;
import org.apache.shiro.util.LifecycleUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class CachingSecurityManager implements SecurityManager, Destroyable, CacheManagerAware, EventBusAware {
    private CacheManager cacheManager;
    private EventBus eventBus;

    public CachingSecurityManager() {
        setEventBus(new DefaultEventBus());
    }

    public CacheManager getCacheManager() {
        return this.cacheManager;
    }

    @Override // org.apache.shiro.cache.CacheManagerAware
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
        afterCacheManagerSet();
    }

    protected void afterCacheManagerSet() {
        applyEventBusToCacheManager();
    }

    public EventBus getEventBus() {
        return this.eventBus;
    }

    @Override // org.apache.shiro.event.EventBusAware
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        afterEventBusSet();
    }

    protected void applyEventBusToCacheManager() {
        CacheManager cacheManager;
        EventBus eventBus = this.eventBus;
        if (eventBus == null || (cacheManager = this.cacheManager) == null || !(cacheManager instanceof EventBusAware)) {
            return;
        }
        ((EventBusAware) cacheManager).setEventBus(eventBus);
    }

    protected void afterEventBusSet() {
        applyEventBusToCacheManager();
    }

    public void destroy() {
        LifecycleUtils.destroy(getCacheManager());
        this.cacheManager = null;
        LifecycleUtils.destroy(getEventBus());
        this.eventBus = new DefaultEventBus();
    }
}
