package org.apache.shiro.mgt;

import java.io.Serializable;
import java.util.Collection;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.config.Ini;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionContext;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class DefaultSecurityManager extends SessionsSecurityManager {
    private static final Logger log = LoggerFactory.getLogger(DefaultSecurityManager.class);
    protected RememberMeManager rememberMeManager;
    protected SubjectDAO subjectDAO;
    protected SubjectFactory subjectFactory;

    public DefaultSecurityManager() {
        this.subjectFactory = new DefaultSubjectFactory();
        this.subjectDAO = new DefaultSubjectDAO();
    }

    public DefaultSecurityManager(Realm realm) {
        this();
        setRealm(realm);
    }

    public DefaultSecurityManager(Collection<Realm> collection) {
        this();
        setRealms(collection);
    }

    public SubjectFactory getSubjectFactory() {
        return this.subjectFactory;
    }

    public void setSubjectFactory(SubjectFactory subjectFactory) {
        this.subjectFactory = subjectFactory;
    }

    public SubjectDAO getSubjectDAO() {
        return this.subjectDAO;
    }

    public void setSubjectDAO(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    public RememberMeManager getRememberMeManager() {
        return this.rememberMeManager;
    }

    public void setRememberMeManager(RememberMeManager rememberMeManager) {
        this.rememberMeManager = rememberMeManager;
    }

    protected SubjectContext createSubjectContext() {
        return new DefaultSubjectContext();
    }

    protected Subject createSubject(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo, Subject subject) {
        SubjectContext subjectContextCreateSubjectContext = createSubjectContext();
        subjectContextCreateSubjectContext.setAuthenticated(true);
        subjectContextCreateSubjectContext.setAuthenticationToken(authenticationToken);
        subjectContextCreateSubjectContext.setAuthenticationInfo(authenticationInfo);
        subjectContextCreateSubjectContext.setSecurityManager(this);
        if (subject != null) {
            subjectContextCreateSubjectContext.setSubject(subject);
        }
        return createSubject(subjectContextCreateSubjectContext);
    }

    @Deprecated
    protected void bind(Subject subject) {
        save(subject);
    }

    protected void rememberMeSuccessfulLogin(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo, Subject subject) {
        RememberMeManager rememberMeManager = getRememberMeManager();
        if (rememberMeManager != null) {
            try {
                rememberMeManager.onSuccessfulLogin(subject, authenticationToken, authenticationInfo);
                return;
            } catch (Exception e) {
                if (log.isWarnEnabled()) {
                    log.warn("Delegate RememberMeManager instance of type [" + rememberMeManager.getClass().getName() + "] threw an exception during onSuccessfulLogin.  RememberMe services will not be performed for account [" + authenticationInfo + "].", e);
                    return;
                }
                return;
            }
        }
        Logger logger = log;
        if (logger.isTraceEnabled()) {
            logger.trace("This " + getClass().getName() + " instance does not have a [" + RememberMeManager.class.getName() + "] instance configured.  RememberMe services will not be performed for account [" + authenticationInfo + "].");
        }
    }

    protected void rememberMeFailedLogin(AuthenticationToken authenticationToken, AuthenticationException authenticationException, Subject subject) {
        RememberMeManager rememberMeManager = getRememberMeManager();
        if (rememberMeManager != null) {
            try {
                rememberMeManager.onFailedLogin(subject, authenticationToken, authenticationException);
            } catch (Exception e) {
                if (log.isWarnEnabled()) {
                    log.warn("Delegate RememberMeManager instance of type [" + rememberMeManager.getClass().getName() + "] threw an exception during onFailedLogin for AuthenticationToken [" + authenticationToken + "].", e);
                }
            }
        }
    }

    protected void rememberMeLogout(Subject subject) {
        RememberMeManager rememberMeManager = getRememberMeManager();
        if (rememberMeManager != null) {
            try {
                rememberMeManager.onLogout(subject);
            } catch (Exception e) {
                if (log.isWarnEnabled()) {
                    log.warn("Delegate RememberMeManager instance of type [" + rememberMeManager.getClass().getName() + "] threw an exception during onLogout for subject with principals [" + (subject != null ? subject.getPrincipals() : null) + Ini.SECTION_SUFFIX, e);
                }
            }
        }
    }

    @Override // org.apache.shiro.mgt.SecurityManager
    public Subject login(Subject subject, AuthenticationToken authenticationToken) throws AuthenticationException {
        try {
            AuthenticationInfo authenticationInfoAuthenticate = authenticate(authenticationToken);
            Subject subjectCreateSubject = createSubject(authenticationToken, authenticationInfoAuthenticate, subject);
            onSuccessfulLogin(authenticationToken, authenticationInfoAuthenticate, subjectCreateSubject);
            return subjectCreateSubject;
        } catch (AuthenticationException e) {
            try {
                onFailedLogin(authenticationToken, e, subject);
            } catch (Exception e2) {
                if (log.isInfoEnabled()) {
                    log.info("onFailedLogin method threw an exception.  Logging and propagating original AuthenticationException.", e2);
                }
            }
            throw e;
        }
    }

    protected void onSuccessfulLogin(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo, Subject subject) {
        rememberMeSuccessfulLogin(authenticationToken, authenticationInfo, subject);
    }

    protected void onFailedLogin(AuthenticationToken authenticationToken, AuthenticationException authenticationException, Subject subject) {
        rememberMeFailedLogin(authenticationToken, authenticationException, subject);
    }

    protected void beforeLogout(Subject subject) {
        rememberMeLogout(subject);
    }

    protected SubjectContext copy(SubjectContext subjectContext) {
        return new DefaultSubjectContext(subjectContext);
    }

    @Override // org.apache.shiro.mgt.SecurityManager
    public Subject createSubject(SubjectContext subjectContext) {
        Subject subjectDoCreateSubject = doCreateSubject(resolvePrincipals(resolveSession(ensureSecurityManager(copy(subjectContext)))));
        save(subjectDoCreateSubject);
        return subjectDoCreateSubject;
    }

    protected Subject doCreateSubject(SubjectContext subjectContext) {
        return getSubjectFactory().createSubject(subjectContext);
    }

    protected void save(Subject subject) {
        this.subjectDAO.save(subject);
    }

    protected void delete(Subject subject) {
        this.subjectDAO.delete(subject);
    }

    protected SubjectContext ensureSecurityManager(SubjectContext subjectContext) {
        if (subjectContext.resolveSecurityManager() != null) {
            log.trace("Context already contains a SecurityManager instance.  Returning.");
            return subjectContext;
        }
        log.trace("No SecurityManager found in context.  Adding self reference.");
        subjectContext.setSecurityManager(this);
        return subjectContext;
    }

    protected SubjectContext resolveSession(SubjectContext subjectContext) {
        if (subjectContext.resolveSession() != null) {
            log.debug("Context already contains a session.  Returning.");
            return subjectContext;
        }
        try {
            Session sessionResolveContextSession = resolveContextSession(subjectContext);
            if (sessionResolveContextSession != null) {
                subjectContext.setSession(sessionResolveContextSession);
            }
        } catch (InvalidSessionException e) {
            log.debug("Resolved SubjectContext context session is invalid.  Ignoring and creating an anonymous (session-less) Subject instance.", e);
        }
        return subjectContext;
    }

    protected Session resolveContextSession(SubjectContext subjectContext) throws InvalidSessionException {
        SessionKey sessionKey = getSessionKey(subjectContext);
        if (sessionKey != null) {
            return getSession(sessionKey);
        }
        return null;
    }

    protected SessionKey getSessionKey(SubjectContext subjectContext) {
        Serializable sessionId = subjectContext.getSessionId();
        if (sessionId != null) {
            return new DefaultSessionKey(sessionId);
        }
        return null;
    }

    private static boolean isEmpty(PrincipalCollection principalCollection) {
        return principalCollection == null || principalCollection.isEmpty();
    }

    protected SubjectContext resolvePrincipals(SubjectContext subjectContext) {
        if (isEmpty(subjectContext.resolvePrincipals())) {
            Logger logger = log;
            logger.trace("No identity (PrincipalCollection) found in the context.  Looking for a remembered identity.");
            PrincipalCollection rememberedIdentity = getRememberedIdentity(subjectContext);
            if (!isEmpty(rememberedIdentity)) {
                logger.debug("Found remembered PrincipalCollection.  Adding to the context to be used for subject construction by the SubjectFactory.");
                subjectContext.setPrincipals(rememberedIdentity);
            } else {
                logger.trace("No remembered identity found.  Returning original context.");
            }
        }
        return subjectContext;
    }

    protected SessionContext createSessionContext(SubjectContext subjectContext) {
        DefaultSessionContext defaultSessionContext = new DefaultSessionContext();
        if (!CollectionUtils.isEmpty(subjectContext)) {
            defaultSessionContext.putAll(subjectContext);
        }
        Serializable sessionId = subjectContext.getSessionId();
        if (sessionId != null) {
            defaultSessionContext.setSessionId(sessionId);
        }
        String strResolveHost = subjectContext.resolveHost();
        if (strResolveHost != null) {
            defaultSessionContext.setHost(strResolveHost);
        }
        return defaultSessionContext;
    }

    @Override // org.apache.shiro.mgt.SecurityManager
    public void logout(Subject subject) {
        StringBuilder sb;
        if (subject == null) {
            throw new IllegalArgumentException("Subject method argument cannot be null.");
        }
        beforeLogout(subject);
        PrincipalCollection principals = subject.getPrincipals();
        if (principals != null && !principals.isEmpty()) {
            Logger logger = log;
            if (logger.isDebugEnabled()) {
                logger.debug("Logging out subject with primary principal {}", principals.getPrimaryPrincipal());
            }
            Authenticator authenticator = getAuthenticator();
            if (authenticator instanceof LogoutAware) {
                ((LogoutAware) authenticator).onLogout(principals);
            }
        }
        try {
            try {
                delete(subject);
                try {
                    stopSession(subject);
                } catch (Exception e) {
                    e = e;
                    if (log.isDebugEnabled()) {
                        sb = new StringBuilder();
                        log.debug(sb.append("Unable to cleanly stop Session for Subject [").append(subject.getPrincipal()).append("] Ignoring (logging out).").toString(), e);
                    }
                }
            } catch (Exception e2) {
                if (log.isDebugEnabled()) {
                    log.debug("Unable to cleanly unbind Subject.  Ignoring (logging out).", e2);
                }
                try {
                    stopSession(subject);
                } catch (Exception e3) {
                    e = e3;
                    if (log.isDebugEnabled()) {
                        sb = new StringBuilder();
                        log.debug(sb.append("Unable to cleanly stop Session for Subject [").append(subject.getPrincipal()).append("] Ignoring (logging out).").toString(), e);
                    }
                }
            }
        } catch (Throwable th) {
            try {
                stopSession(subject);
            } catch (Exception e4) {
                if (log.isDebugEnabled()) {
                    log.debug("Unable to cleanly stop Session for Subject [" + subject.getPrincipal() + "] Ignoring (logging out).", e4);
                }
            }
            throw th;
        }
    }

    protected void stopSession(Subject subject) {
        Session session = subject.getSession(false);
        if (session != null) {
            session.stop();
        }
    }

    @Deprecated
    protected void unbind(Subject subject) {
        delete(subject);
    }

    protected PrincipalCollection getRememberedIdentity(SubjectContext subjectContext) {
        RememberMeManager rememberMeManager = getRememberMeManager();
        if (rememberMeManager == null) {
            return null;
        }
        try {
            return rememberMeManager.getRememberedPrincipals(subjectContext);
        } catch (Exception e) {
            if (!log.isWarnEnabled()) {
                return null;
            }
            log.warn("Delegate RememberMeManager instance of type [" + rememberMeManager.getClass().getName() + "] threw an exception during getRememberedPrincipals().", e);
            return null;
        }
    }
}
