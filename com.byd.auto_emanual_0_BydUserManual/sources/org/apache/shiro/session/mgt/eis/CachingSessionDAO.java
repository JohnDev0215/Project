package org.apache.shiro.session.mgt.eis;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.CacheManagerAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;

/* JADX INFO: loaded from: classes.dex */
public abstract class CachingSessionDAO extends AbstractSessionDAO implements CacheManagerAware {
    public static final String ACTIVE_SESSION_CACHE_NAME = "shiro-activeSessionCache";
    private Cache<Serializable, Session> activeSessions;
    private String activeSessionsCacheName = ACTIVE_SESSION_CACHE_NAME;
    private CacheManager cacheManager;

    protected abstract void doDelete(Session session);

    protected abstract void doUpdate(Session session);

    @Override // org.apache.shiro.cache.CacheManagerAware
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public CacheManager getCacheManager() {
        return this.cacheManager;
    }

    public String getActiveSessionsCacheName() {
        return this.activeSessionsCacheName;
    }

    public void setActiveSessionsCacheName(String str) {
        this.activeSessionsCacheName = str;
    }

    public Cache<Serializable, Session> getActiveSessionsCache() {
        return this.activeSessions;
    }

    public void setActiveSessionsCache(Cache<Serializable, Session> cache) {
        this.activeSessions = cache;
    }

    private Cache<Serializable, Session> getActiveSessionsCacheLazy() {
        if (this.activeSessions == null) {
            this.activeSessions = createActiveSessionsCache();
        }
        return this.activeSessions;
    }

    protected Cache<Serializable, Session> createActiveSessionsCache() {
        CacheManager cacheManager = getCacheManager();
        if (cacheManager != null) {
            return cacheManager.getCache(getActiveSessionsCacheName());
        }
        return null;
    }

    @Override // org.apache.shiro.session.mgt.eis.AbstractSessionDAO, org.apache.shiro.session.mgt.eis.SessionDAO
    public Serializable create(Session session) {
        Serializable serializableCreate = super.create(session);
        cache(session, serializableCreate);
        return serializableCreate;
    }

    protected Session getCachedSession(Serializable serializable) {
        Cache<Serializable, Session> activeSessionsCacheLazy;
        if (serializable == null || (activeSessionsCacheLazy = getActiveSessionsCacheLazy()) == null) {
            return null;
        }
        return getCachedSession(serializable, activeSessionsCacheLazy);
    }

    protected Session getCachedSession(Serializable serializable, Cache<Serializable, Session> cache) {
        return cache.get(serializable);
    }

    protected void cache(Session session, Serializable serializable) {
        Cache<Serializable, Session> activeSessionsCacheLazy;
        if (session == null || serializable == null || (activeSessionsCacheLazy = getActiveSessionsCacheLazy()) == null) {
            return;
        }
        cache(session, serializable, activeSessionsCacheLazy);
    }

    protected void cache(Session session, Serializable serializable, Cache<Serializable, Session> cache) {
        cache.put(serializable, session);
    }

    @Override // org.apache.shiro.session.mgt.eis.AbstractSessionDAO, org.apache.shiro.session.mgt.eis.SessionDAO
    public Session readSession(Serializable serializable) throws UnknownSessionException {
        Session cachedSession = getCachedSession(serializable);
        return cachedSession == null ? super.readSession(serializable) : cachedSession;
    }

    @Override // org.apache.shiro.session.mgt.eis.SessionDAO
    public void update(Session session) throws UnknownSessionException {
        doUpdate(session);
        if (session instanceof ValidatingSession) {
            if (((ValidatingSession) session).isValid()) {
                cache(session, session.getId());
                return;
            } else {
                uncache(session);
                return;
            }
        }
        cache(session, session.getId());
    }

    @Override // org.apache.shiro.session.mgt.eis.SessionDAO
    public void delete(Session session) {
        uncache(session);
        doDelete(session);
    }

    protected void uncache(Session session) {
        Serializable id;
        Cache<Serializable, Session> activeSessionsCacheLazy;
        if (session == null || (id = session.getId()) == null || (activeSessionsCacheLazy = getActiveSessionsCacheLazy()) == null) {
            return;
        }
        activeSessionsCacheLazy.remove(id);
    }

    @Override // org.apache.shiro.session.mgt.eis.SessionDAO
    public Collection<Session> getActiveSessions() {
        Cache<Serializable, Session> activeSessionsCacheLazy = getActiveSessionsCacheLazy();
        if (activeSessionsCacheLazy != null) {
            return activeSessionsCacheLazy.values();
        }
        return Collections.emptySet();
    }
}
