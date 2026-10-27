package org.apache.shiro.session.mgt;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.CacheManagerAware;
import org.apache.shiro.config.Ini;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class DefaultSessionManager extends AbstractValidatingSessionManager implements CacheManagerAware {
    private static final Logger log = LoggerFactory.getLogger(DefaultSessionManager.class);
    private CacheManager cacheManager;
    private boolean deleteInvalidSessions = true;
    private SessionFactory sessionFactory = new SimpleSessionFactory();
    protected SessionDAO sessionDAO = new MemorySessionDAO();

    public void setSessionDAO(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
        applyCacheManagerToSessionDAO();
    }

    public SessionDAO getSessionDAO() {
        return this.sessionDAO;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean isDeleteInvalidSessions() {
        return this.deleteInvalidSessions;
    }

    public void setDeleteInvalidSessions(boolean z) {
        this.deleteInvalidSessions = z;
    }

    @Override // org.apache.shiro.cache.CacheManagerAware
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
        applyCacheManagerToSessionDAO();
    }

    private void applyCacheManagerToSessionDAO() {
        SessionDAO sessionDAO;
        CacheManager cacheManager = this.cacheManager;
        if (cacheManager == null || (sessionDAO = this.sessionDAO) == null || !(sessionDAO instanceof CacheManagerAware)) {
            return;
        }
        ((CacheManagerAware) sessionDAO).setCacheManager(cacheManager);
    }

    @Override // org.apache.shiro.session.mgt.AbstractValidatingSessionManager
    protected Session doCreateSession(SessionContext sessionContext) {
        Session sessionNewSessionInstance = newSessionInstance(sessionContext);
        Logger logger = log;
        if (logger.isTraceEnabled()) {
            logger.trace("Creating session for host {}", sessionNewSessionInstance.getHost());
        }
        create(sessionNewSessionInstance);
        return sessionNewSessionInstance;
    }

    protected Session newSessionInstance(SessionContext sessionContext) {
        return getSessionFactory().createSession(sessionContext);
    }

    protected void create(Session session) {
        Logger logger = log;
        if (logger.isDebugEnabled()) {
            logger.debug("Creating new EIS record for new session instance [" + session + Ini.SECTION_SUFFIX);
        }
        this.sessionDAO.create(session);
    }

    @Override // org.apache.shiro.session.mgt.AbstractNativeSessionManager
    protected void onStop(Session session) {
        if (session instanceof SimpleSession) {
            SimpleSession simpleSession = (SimpleSession) session;
            simpleSession.setLastAccessTime(simpleSession.getStopTimestamp());
        }
        onChange(session);
    }

    @Override // org.apache.shiro.session.mgt.AbstractNativeSessionManager
    protected void afterStopped(Session session) {
        if (isDeleteInvalidSessions()) {
            delete(session);
        }
    }

    @Override // org.apache.shiro.session.mgt.AbstractValidatingSessionManager
    protected void onExpiration(Session session) {
        if (session instanceof SimpleSession) {
            ((SimpleSession) session).setExpired(true);
        }
        onChange(session);
    }

    @Override // org.apache.shiro.session.mgt.AbstractValidatingSessionManager
    protected void afterExpired(Session session) {
        if (isDeleteInvalidSessions()) {
            delete(session);
        }
    }

    @Override // org.apache.shiro.session.mgt.AbstractNativeSessionManager
    protected void onChange(Session session) {
        this.sessionDAO.update(session);
    }

    @Override // org.apache.shiro.session.mgt.AbstractValidatingSessionManager
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);
        if (sessionId == null) {
            log.debug("Unable to resolve session ID from SessionKey [{}].  Returning null to indicate a session could not be found.", sessionKey);
            return null;
        }
        Session sessionRetrieveSessionFromDataSource = retrieveSessionFromDataSource(sessionId);
        if (sessionRetrieveSessionFromDataSource != null) {
            return sessionRetrieveSessionFromDataSource;
        }
        throw new UnknownSessionException("Could not find session with ID [" + sessionId + Ini.SECTION_SUFFIX);
    }

    protected Serializable getSessionId(SessionKey sessionKey) {
        return sessionKey.getSessionId();
    }

    protected Session retrieveSessionFromDataSource(Serializable serializable) throws UnknownSessionException {
        return this.sessionDAO.readSession(serializable);
    }

    protected void delete(Session session) {
        this.sessionDAO.delete(session);
    }

    @Override // org.apache.shiro.session.mgt.AbstractValidatingSessionManager
    protected Collection<Session> getActiveSessions() {
        Collection<Session> activeSessions = this.sessionDAO.getActiveSessions();
        return activeSessions != null ? activeSessions : Collections.emptySet();
    }
}
