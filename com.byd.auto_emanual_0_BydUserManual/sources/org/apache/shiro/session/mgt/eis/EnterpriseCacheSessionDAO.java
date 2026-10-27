package org.apache.shiro.session.mgt.eis;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.MapCache;
import org.apache.shiro.session.Session;

/* JADX INFO: loaded from: classes.dex */
public class EnterpriseCacheSessionDAO extends CachingSessionDAO {
    @Override // org.apache.shiro.session.mgt.eis.CachingSessionDAO
    protected void doDelete(Session session) {
    }

    @Override // org.apache.shiro.session.mgt.eis.AbstractSessionDAO
    protected Session doReadSession(Serializable serializable) {
        return null;
    }

    @Override // org.apache.shiro.session.mgt.eis.CachingSessionDAO
    protected void doUpdate(Session session) {
    }

    public EnterpriseCacheSessionDAO() {
        setCacheManager(new AbstractCacheManager() { // from class: org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO.1
            @Override // org.apache.shiro.cache.AbstractCacheManager
            protected Cache<Serializable, Session> createCache(String str) throws CacheException {
                return new MapCache(str, new ConcurrentHashMap());
            }
        });
    }

    @Override // org.apache.shiro.session.mgt.eis.AbstractSessionDAO
    protected Serializable doCreate(Session session) {
        Serializable serializableGenerateSessionId = generateSessionId(session);
        assignSessionId(session, serializableGenerateSessionId);
        return serializableGenerateSessionId;
    }
}
