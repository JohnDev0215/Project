package org.apache.shiro.session.mgt;

import java.util.Collection;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.config.Ini;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.util.Destroyable;
import org.apache.shiro.util.LifecycleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractValidatingSessionManager extends AbstractNativeSessionManager implements ValidatingSessionManager, Destroyable {
    public static final long DEFAULT_SESSION_VALIDATION_INTERVAL = 3600000;
    private static final Logger log = LoggerFactory.getLogger(AbstractValidatingSessionManager.class);
    protected SessionValidationScheduler sessionValidationScheduler;
    protected boolean sessionValidationSchedulerEnabled = true;
    protected long sessionValidationInterval = DEFAULT_SESSION_VALIDATION_INTERVAL;

    protected void afterExpired(Session session) {
    }

    protected void afterSessionValidationEnabled() {
    }

    protected void beforeSessionValidationDisabled() {
    }

    protected abstract Session doCreateSession(SessionContext sessionContext) throws AuthorizationException;

    protected abstract Collection<Session> getActiveSessions();

    protected abstract Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException;

    public boolean isSessionValidationSchedulerEnabled() {
        return this.sessionValidationSchedulerEnabled;
    }

    public void setSessionValidationSchedulerEnabled(boolean z) {
        this.sessionValidationSchedulerEnabled = z;
    }

    public void setSessionValidationScheduler(SessionValidationScheduler sessionValidationScheduler) {
        this.sessionValidationScheduler = sessionValidationScheduler;
    }

    public SessionValidationScheduler getSessionValidationScheduler() {
        return this.sessionValidationScheduler;
    }

    private void enableSessionValidationIfNecessary() {
        SessionValidationScheduler sessionValidationScheduler = getSessionValidationScheduler();
        if (isSessionValidationSchedulerEnabled()) {
            if (sessionValidationScheduler == null || !sessionValidationScheduler.isEnabled()) {
                enableSessionValidation();
            }
        }
    }

    public void setSessionValidationInterval(long j) {
        this.sessionValidationInterval = j;
    }

    public long getSessionValidationInterval() {
        return this.sessionValidationInterval;
    }

    @Override // org.apache.shiro.session.mgt.AbstractNativeSessionManager
    protected final Session doGetSession(SessionKey sessionKey) throws InvalidSessionException {
        enableSessionValidationIfNecessary();
        log.trace("Attempting to retrieve session with key {}", sessionKey);
        Session sessionRetrieveSession = retrieveSession(sessionKey);
        if (sessionRetrieveSession != null) {
            validate(sessionRetrieveSession, sessionKey);
        }
        return sessionRetrieveSession;
    }

    @Override // org.apache.shiro.session.mgt.AbstractNativeSessionManager
    protected Session createSession(SessionContext sessionContext) throws AuthorizationException {
        enableSessionValidationIfNecessary();
        return doCreateSession(sessionContext);
    }

    protected void validate(Session session, SessionKey sessionKey) throws InvalidSessionException {
        try {
            doValidate(session);
        } catch (ExpiredSessionException e) {
            onExpiration(session, e, sessionKey);
            throw e;
        } catch (InvalidSessionException e2) {
            onInvalidation(session, e2, sessionKey);
            throw e2;
        }
    }

    protected void onExpiration(Session session, ExpiredSessionException expiredSessionException, SessionKey sessionKey) {
        log.trace("Session with id [{}] has expired.", session.getId());
        try {
            onExpiration(session);
            notifyExpiration(session);
        } finally {
            afterExpired(session);
        }
    }

    protected void onExpiration(Session session) {
        onChange(session);
    }

    protected void onInvalidation(Session session, InvalidSessionException invalidSessionException, SessionKey sessionKey) {
        if (invalidSessionException instanceof ExpiredSessionException) {
            onExpiration(session, (ExpiredSessionException) invalidSessionException, sessionKey);
            return;
        }
        log.trace("Session with id [{}] is invalid.", session.getId());
        try {
            onStop(session);
            notifyStop(session);
        } finally {
            afterStopped(session);
        }
    }

    protected void doValidate(Session session) throws InvalidSessionException {
        if (session instanceof ValidatingSession) {
            ((ValidatingSession) session).validate();
            return;
        }
        throw new IllegalStateException("The " + getClass().getName() + " implementation only supports validating Session implementations of the " + ValidatingSession.class.getName() + " interface.  Please either implement this interface in your session implementation or override the " + AbstractValidatingSessionManager.class.getName() + ".doValidate(Session) method to perform validation.");
    }

    protected long getTimeout(Session session) {
        return session.getTimeout();
    }

    protected SessionValidationScheduler createSessionValidationScheduler() {
        Logger logger = log;
        if (logger.isDebugEnabled()) {
            logger.debug("No sessionValidationScheduler set.  Attempting to create default instance.");
        }
        ExecutorServiceSessionValidationScheduler executorServiceSessionValidationScheduler = new ExecutorServiceSessionValidationScheduler(this);
        executorServiceSessionValidationScheduler.setInterval(getSessionValidationInterval());
        if (logger.isTraceEnabled()) {
            logger.trace("Created default SessionValidationScheduler instance of type [" + executorServiceSessionValidationScheduler.getClass().getName() + "].");
        }
        return executorServiceSessionValidationScheduler;
    }

    protected synchronized void enableSessionValidation() {
        SessionValidationScheduler sessionValidationScheduler = getSessionValidationScheduler();
        if (sessionValidationScheduler == null) {
            sessionValidationScheduler = createSessionValidationScheduler();
            setSessionValidationScheduler(sessionValidationScheduler);
        }
        if (!sessionValidationScheduler.isEnabled()) {
            Logger logger = log;
            if (logger.isInfoEnabled()) {
                logger.info("Enabling session validation scheduler...");
            }
            sessionValidationScheduler.enableSessionValidation();
            afterSessionValidationEnabled();
        }
    }

    protected synchronized void disableSessionValidation() {
        beforeSessionValidationDisabled();
        SessionValidationScheduler sessionValidationScheduler = getSessionValidationScheduler();
        if (sessionValidationScheduler != null) {
            try {
                sessionValidationScheduler.disableSessionValidation();
                Logger logger = log;
                if (logger.isInfoEnabled()) {
                    logger.info("Disabled session validation scheduler.");
                }
            } catch (Exception e) {
                Logger logger2 = log;
                if (logger2.isDebugEnabled()) {
                    logger2.debug("Unable to disable SessionValidationScheduler.  Ignoring (shutting down)...", e);
                }
            }
            LifecycleUtils.destroy(sessionValidationScheduler);
            setSessionValidationScheduler(null);
        }
    }

    @Override // org.apache.shiro.util.Destroyable
    public void destroy() {
        disableSessionValidation();
    }

    @Override // org.apache.shiro.session.mgt.ValidatingSessionManager
    public void validateSessions() {
        String str;
        Logger logger = log;
        if (logger.isInfoEnabled()) {
            logger.info("Validating all active sessions...");
        }
        int i = 0;
        Collection<Session> activeSessions = getActiveSessions();
        if (activeSessions != null && !activeSessions.isEmpty()) {
            for (Session session : activeSessions) {
                try {
                    validate(session, new DefaultSessionKey(session.getId()));
                } catch (InvalidSessionException e) {
                    Logger logger2 = log;
                    if (logger2.isDebugEnabled()) {
                        logger2.debug("Invalidated session with id [" + session.getId() + Ini.SECTION_SUFFIX + (e instanceof ExpiredSessionException ? " (expired)" : " (stopped)"));
                    }
                    i++;
                }
            }
        }
        Logger logger3 = log;
        if (logger3.isInfoEnabled()) {
            if (i > 0) {
                str = "Finished session validation.  [" + i + "] sessions were stopped.";
            } else {
                str = "Finished session validation.  No sessions were stopped.";
            }
            logger3.info(str);
        }
    }
}
