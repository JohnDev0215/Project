package org.apache.shiro.mgt;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.cache.CacheManagerAware;
import org.apache.shiro.event.EventBus;
import org.apache.shiro.event.EventBusAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.util.LifecycleUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class SessionsSecurityManager extends AuthorizingSecurityManager {
    private SessionManager sessionManager = new DefaultSessionManager();

    public SessionsSecurityManager() {
        applyCacheManagerToSessionManager();
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        afterSessionManagerSet();
    }

    protected void afterSessionManagerSet() {
        applyCacheManagerToSessionManager();
        applyEventBusToSessionManager();
    }

    public SessionManager getSessionManager() {
        return this.sessionManager;
    }

    @Override // org.apache.shiro.mgt.RealmSecurityManager, org.apache.shiro.mgt.CachingSecurityManager
    protected void afterCacheManagerSet() {
        super.afterCacheManagerSet();
        applyCacheManagerToSessionManager();
    }

    @Override // org.apache.shiro.mgt.RealmSecurityManager, org.apache.shiro.mgt.CachingSecurityManager
    protected void afterEventBusSet() {
        super.afterEventBusSet();
        applyEventBusToSessionManager();
    }

    protected void applyCacheManagerToSessionManager() {
        SessionManager sessionManager = this.sessionManager;
        if (sessionManager instanceof CacheManagerAware) {
            ((CacheManagerAware) sessionManager).setCacheManager(getCacheManager());
        }
    }

    protected void applyEventBusToSessionManager() {
        EventBus eventBus = getEventBus();
        if (eventBus != null) {
            SessionManager sessionManager = this.sessionManager;
            if (sessionManager instanceof EventBusAware) {
                ((EventBusAware) sessionManager).setEventBus(eventBus);
            }
        }
    }

    @Override // org.apache.shiro.session.mgt.SessionManager
    public Session start(SessionContext sessionContext) throws AuthorizationException {
        SessionManager sessionManager = this.sessionManager;
        if (sessionManager == null) {
            throw new IllegalStateException("Session manager is not available or has been destroyed");
        }
        return sessionManager.start(sessionContext);
    }

    @Override // org.apache.shiro.session.mgt.SessionManager
    public Session getSession(SessionKey sessionKey) throws SessionException {
        return this.sessionManager.getSession(sessionKey);
    }

    @Override // org.apache.shiro.mgt.AuthorizingSecurityManager, org.apache.shiro.mgt.AuthenticatingSecurityManager, org.apache.shiro.mgt.RealmSecurityManager, org.apache.shiro.mgt.CachingSecurityManager, org.apache.shiro.util.Destroyable
    public void destroy() {
        LifecycleUtils.destroy(getSessionManager());
        this.sessionManager = null;
        super.destroy();
    }
}
