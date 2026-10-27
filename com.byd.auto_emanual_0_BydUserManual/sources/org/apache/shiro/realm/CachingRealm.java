package org.apache.shiro.realm;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.CacheManagerAware;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.Nameable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public abstract class CachingRealm implements Realm, Nameable, CacheManagerAware, LogoutAware {
    private CacheManager cacheManager;
    private boolean cachingEnabled = true;
    private String name = getClass().getName() + "_" + INSTANCE_COUNT.getAndIncrement();
    private static final Logger log = LoggerFactory.getLogger(CachingRealm.class);
    private static final AtomicInteger INSTANCE_COUNT = new AtomicInteger();

    protected void afterCacheManagerSet() {
    }

    protected void doClearCache(PrincipalCollection principalCollection) {
    }

    public CacheManager getCacheManager() {
        return this.cacheManager;
    }

    @Override // org.apache.shiro.cache.CacheManagerAware
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
        afterCacheManagerSet();
    }

    public boolean isCachingEnabled() {
        return this.cachingEnabled;
    }

    public void setCachingEnabled(boolean z) {
        this.cachingEnabled = z;
    }

    @Override // org.apache.shiro.realm.Realm
    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    @Override // org.apache.shiro.authc.LogoutAware
    public void onLogout(PrincipalCollection principalCollection) {
        clearCache(principalCollection);
    }

    private static boolean isEmpty(PrincipalCollection principalCollection) {
        return principalCollection == null || principalCollection.isEmpty();
    }

    protected void clearCache(PrincipalCollection principalCollection) {
        if (isEmpty(principalCollection)) {
            return;
        }
        doClearCache(principalCollection);
        log.trace("Cleared cache entries for account with principals [{}]", principalCollection);
    }

    protected Object getAvailablePrincipal(PrincipalCollection principalCollection) {
        if (isEmpty(principalCollection)) {
            return null;
        }
        Collection collectionFromRealm = principalCollection.fromRealm(getName());
        if (!CollectionUtils.isEmpty(collectionFromRealm)) {
            return collectionFromRealm.iterator().next();
        }
        return principalCollection.getPrimaryPrincipal();
    }
}
