package org.apache.shiro.mgt;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.CacheManagerAware;
import org.apache.shiro.event.EventBus;
import org.apache.shiro.event.EventBusAware;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.LifecycleUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class RealmSecurityManager extends CachingSecurityManager {
    private Collection<Realm> realms;

    public void setRealm(Realm realm) {
        if (realm == null) {
            throw new IllegalArgumentException("Realm argument cannot be null");
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(realm);
        setRealms(arrayList);
    }

    public void setRealms(Collection<Realm> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Realms collection argument cannot be null.");
        }
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Realms collection argument cannot be empty.");
        }
        this.realms = collection;
        afterRealmsSet();
    }

    protected void afterRealmsSet() {
        applyCacheManagerToRealms();
        applyEventBusToRealms();
    }

    public Collection<Realm> getRealms() {
        return this.realms;
    }

    protected void applyCacheManagerToRealms() {
        CacheManager cacheManager = getCacheManager();
        Collection<Realm> realms = getRealms();
        if (cacheManager == null || realms == null || realms.isEmpty()) {
            return;
        }
        for (Realm realm : realms) {
            if (realm instanceof CacheManagerAware) {
                ((CacheManagerAware) realm).setCacheManager(cacheManager);
            }
        }
    }

    protected void applyEventBusToRealms() {
        EventBus eventBus = getEventBus();
        Collection<Realm> realms = getRealms();
        if (eventBus == null || realms == null || realms.isEmpty()) {
            return;
        }
        for (Realm realm : realms) {
            if (realm instanceof EventBusAware) {
                ((EventBusAware) realm).setEventBus(eventBus);
            }
        }
    }

    @Override // org.apache.shiro.mgt.CachingSecurityManager
    protected void afterCacheManagerSet() {
        super.afterCacheManagerSet();
        applyCacheManagerToRealms();
    }

    @Override // org.apache.shiro.mgt.CachingSecurityManager
    protected void afterEventBusSet() {
        super.afterEventBusSet();
        applyEventBusToRealms();
    }

    @Override // org.apache.shiro.mgt.CachingSecurityManager, org.apache.shiro.util.Destroyable
    public void destroy() {
        LifecycleUtils.destroy((Collection) getRealms());
        this.realms = null;
        super.destroy();
    }
}
