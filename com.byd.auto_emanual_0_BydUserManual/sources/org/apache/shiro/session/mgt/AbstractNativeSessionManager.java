package org.apache.shiro.session.mgt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.config.Ini;
import org.apache.shiro.event.EventBus;
import org.apache.shiro.event.EventBusAware;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractNativeSessionManager extends AbstractSessionManager implements NativeSessionManager, EventBusAware {
    private static final Logger log = LoggerFactory.getLogger(AbstractSessionManager.class);
    private EventBus eventBus;
    private Collection<SessionListener> listeners = new ArrayList();

    protected void afterStopped(Session session) {
    }

    protected abstract Session createSession(SessionContext sessionContext) throws AuthorizationException;

    protected abstract Session doGetSession(SessionKey sessionKey) throws InvalidSessionException;

    protected void onChange(Session session) {
    }

    protected void onStart(Session session, SessionContext sessionContext) {
    }

    public void setSessionListeners(Collection<SessionListener> collection) {
        if (collection == null) {
            collection = new ArrayList<>();
        }
        this.listeners = collection;
    }

    public Collection<SessionListener> getSessionListeners() {
        return this.listeners;
    }

    protected EventBus getEventBus() {
        return this.eventBus;
    }

    @Override // org.apache.shiro.event.EventBusAware
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    protected void publishEvent(Object obj) {
        EventBus eventBus = this.eventBus;
        if (eventBus != null) {
            eventBus.publish(obj);
        }
    }

    @Override // org.apache.shiro.session.mgt.SessionManager
    public Session start(SessionContext sessionContext) {
        Session sessionCreateSession = createSession(sessionContext);
        applyGlobalSessionTimeout(sessionCreateSession);
        onStart(sessionCreateSession, sessionContext);
        notifyStart(sessionCreateSession);
        return createExposedSession(sessionCreateSession, sessionContext);
    }

    protected void applyGlobalSessionTimeout(Session session) {
        session.setTimeout(getGlobalSessionTimeout());
        onChange(session);
    }

    @Override // org.apache.shiro.session.mgt.SessionManager
    public Session getSession(SessionKey sessionKey) throws SessionException {
        Session sessionLookupSession = lookupSession(sessionKey);
        if (sessionLookupSession != null) {
            return createExposedSession(sessionLookupSession, sessionKey);
        }
        return null;
    }

    private Session lookupSession(SessionKey sessionKey) throws SessionException {
        Objects.requireNonNull(sessionKey, "SessionKey argument cannot be null.");
        return doGetSession(sessionKey);
    }

    private Session lookupRequiredSession(SessionKey sessionKey) throws SessionException {
        Session sessionLookupSession = lookupSession(sessionKey);
        if (sessionLookupSession != null) {
            return sessionLookupSession;
        }
        throw new UnknownSessionException("Unable to locate required Session instance based on SessionKey [" + sessionKey + "].");
    }

    protected Session createExposedSession(Session session, SessionContext sessionContext) {
        return new DelegatingSession(this, new DefaultSessionKey(session.getId()));
    }

    protected Session createExposedSession(Session session, SessionKey sessionKey) {
        return new DelegatingSession(this, new DefaultSessionKey(session.getId()));
    }

    protected Session beforeInvalidNotification(Session session) {
        return new ImmutableProxiedSession(session);
    }

    protected void notifyStart(Session session) {
        Iterator<SessionListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onStart(session);
        }
    }

    protected void notifyStop(Session session) {
        Session sessionBeforeInvalidNotification = beforeInvalidNotification(session);
        Iterator<SessionListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onStop(sessionBeforeInvalidNotification);
        }
    }

    protected void notifyExpiration(Session session) {
        Session sessionBeforeInvalidNotification = beforeInvalidNotification(session);
        Iterator<SessionListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onExpiration(sessionBeforeInvalidNotification);
        }
    }

    @Override // org.apache.shiro.session.mgt.NativeSessionManager
    public Date getStartTimestamp(SessionKey sessionKey) {
        return lookupRequiredSession(sessionKey).getStartTimestamp();
    }

    @Override // org.apache.shiro.session.mgt.NativeSessionManager
    public Date getLastAccessTime(SessionKey sessionKey) {
        return lookupRequiredSession(sessionKey).getLastAccessTime();
    }

    @Override // org.apache.shiro.session.mgt.NativeSessionManager
    public long getTimeout(SessionKey sessionKey) throws InvalidSessionException {
        return lookupRequiredSession(sessionKey).getTimeout();
    }

    @Override // org.apache.shiro.session.mgt.NativeSessionManager
    public void setTimeout(SessionKey sessionKey, long j) throws InvalidSessionException {
        Session sessionLookupRequiredSession = lookupRequiredSession(sessionKey);
        sessionLookupRequiredSession.setTimeout(j);
        onChange(sessionLookupRequiredSession);
    }

    @Override // org.apache.shiro.session.mgt.NativeSessionManager
    public void touch(SessionKey sessionKey) throws InvalidSessionException {
        Session sessionLookupRequiredSession = lookupRequiredSession(sessionKey);
        sessionLookupRequiredSession.touch();
        onChange(sessionLookupRequiredSession);
    }

    @Override // org.apache.shiro.session.mgt.NativeSessionManager
    public String getHost(SessionKey sessionKey) {
        return lookupRequiredSession(sessionKey).getHost();
    }

    @Override // org.apache.shiro.session.mgt.NativeSessionManager
    public Collection<Object> getAttributeKeys(SessionKey sessionKey) {
        Collection<Object> attributeKeys = lookupRequiredSession(sessionKey).getAttributeKeys();
        if (!CollectionUtils.isEmpty(attributeKeys)) {
            return Collections.unmodifiableCollection(attributeKeys);
        }
        return Collections.emptySet();
    }

    @Override // org.apache.shiro.session.mgt.NativeSessionManager
    public Object getAttribute(SessionKey sessionKey, Object obj) throws InvalidSessionException {
        return lookupRequiredSession(sessionKey).getAttribute(obj);
    }

    @Override // org.apache.shiro.session.mgt.NativeSessionManager
    public void setAttribute(SessionKey sessionKey, Object obj, Object obj2) throws InvalidSessionException {
        if (obj2 == null) {
            removeAttribute(sessionKey, obj);
            return;
        }
        Session sessionLookupRequiredSession = lookupRequiredSession(sessionKey);
        sessionLookupRequiredSession.setAttribute(obj, obj2);
        onChange(sessionLookupRequiredSession);
    }

    @Override // org.apache.shiro.session.mgt.NativeSessionManager
    public Object removeAttribute(SessionKey sessionKey, Object obj) throws InvalidSessionException {
        Session sessionLookupRequiredSession = lookupRequiredSession(sessionKey);
        Object objRemoveAttribute = sessionLookupRequiredSession.removeAttribute(obj);
        if (objRemoveAttribute != null) {
            onChange(sessionLookupRequiredSession);
        }
        return objRemoveAttribute;
    }

    @Override // org.apache.shiro.session.mgt.NativeSessionManager
    public boolean isValid(SessionKey sessionKey) {
        try {
            checkValid(sessionKey);
            return true;
        } catch (InvalidSessionException unused) {
            return false;
        }
    }

    @Override // org.apache.shiro.session.mgt.NativeSessionManager
    public void stop(SessionKey sessionKey) throws InvalidSessionException {
        Session sessionLookupRequiredSession = lookupRequiredSession(sessionKey);
        try {
            Logger logger = log;
            if (logger.isDebugEnabled()) {
                logger.debug("Stopping session with id [" + sessionLookupRequiredSession.getId() + Ini.SECTION_SUFFIX);
            }
            sessionLookupRequiredSession.stop();
            onStop(sessionLookupRequiredSession, sessionKey);
            notifyStop(sessionLookupRequiredSession);
        } finally {
            afterStopped(sessionLookupRequiredSession);
        }
    }

    protected void onStop(Session session, SessionKey sessionKey) {
        onStop(session);
    }

    protected void onStop(Session session) {
        onChange(session);
    }

    @Override // org.apache.shiro.session.mgt.NativeSessionManager
    public void checkValid(SessionKey sessionKey) throws InvalidSessionException {
        lookupRequiredSession(sessionKey);
    }
}
