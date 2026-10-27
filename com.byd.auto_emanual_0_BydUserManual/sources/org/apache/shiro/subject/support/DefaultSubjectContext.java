package org.apache.shiro.subject.support;

import java.io.Serializable;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.util.MapContext;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class DefaultSubjectContext extends MapContext implements SubjectContext {
    private static final String SECURITY_MANAGER = DefaultSubjectContext.class.getName() + ".SECURITY_MANAGER";
    private static final String SESSION_ID = DefaultSubjectContext.class.getName() + ".SESSION_ID";
    private static final String AUTHENTICATION_TOKEN = DefaultSubjectContext.class.getName() + ".AUTHENTICATION_TOKEN";
    private static final String AUTHENTICATION_INFO = DefaultSubjectContext.class.getName() + ".AUTHENTICATION_INFO";
    private static final String SUBJECT = DefaultSubjectContext.class.getName() + ".SUBJECT";
    private static final String PRINCIPALS = DefaultSubjectContext.class.getName() + ".PRINCIPALS";
    private static final String SESSION = DefaultSubjectContext.class.getName() + ".SESSION";
    private static final String AUTHENTICATED = DefaultSubjectContext.class.getName() + ".AUTHENTICATED";
    private static final String HOST = DefaultSubjectContext.class.getName() + ".HOST";
    public static final String SESSION_CREATION_ENABLED = DefaultSubjectContext.class.getName() + ".SESSION_CREATION_ENABLED";
    public static final String PRINCIPALS_SESSION_KEY = DefaultSubjectContext.class.getName() + "_PRINCIPALS_SESSION_KEY";
    public static final String AUTHENTICATED_SESSION_KEY = DefaultSubjectContext.class.getName() + "_AUTHENTICATED_SESSION_KEY";
    private static final transient Logger log = LoggerFactory.getLogger(DefaultSubjectContext.class);

    public DefaultSubjectContext() {
    }

    public DefaultSubjectContext(SubjectContext subjectContext) {
        super(subjectContext);
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public SecurityManager getSecurityManager() {
        return (SecurityManager) getTypedValue(SECURITY_MANAGER, SecurityManager.class);
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public void setSecurityManager(SecurityManager securityManager) {
        nullSafePut(SECURITY_MANAGER, securityManager);
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public SecurityManager resolveSecurityManager() {
        SecurityManager securityManager = getSecurityManager();
        if (securityManager != null) {
            return securityManager;
        }
        Logger logger = log;
        if (logger.isDebugEnabled()) {
            logger.debug("No SecurityManager available in subject context map.  Falling back to SecurityUtils.getSecurityManager() lookup.");
        }
        try {
            return SecurityUtils.getSecurityManager();
        } catch (UnavailableSecurityManagerException e) {
            if (!log.isDebugEnabled()) {
                return securityManager;
            }
            log.debug("No SecurityManager available via SecurityUtils.  Heuristics exhausted.", e);
            return securityManager;
        }
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public Serializable getSessionId() {
        return (Serializable) getTypedValue(SESSION_ID, Serializable.class);
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public void setSessionId(Serializable serializable) {
        nullSafePut(SESSION_ID, serializable);
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public Subject getSubject() {
        return (Subject) getTypedValue(SUBJECT, Subject.class);
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public void setSubject(Subject subject) {
        nullSafePut(SUBJECT, subject);
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public PrincipalCollection getPrincipals() {
        return (PrincipalCollection) getTypedValue(PRINCIPALS, PrincipalCollection.class);
    }

    private static boolean isEmpty(PrincipalCollection principalCollection) {
        return principalCollection == null || principalCollection.isEmpty();
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public void setPrincipals(PrincipalCollection principalCollection) {
        if (isEmpty(principalCollection)) {
            return;
        }
        put(PRINCIPALS, (Object) principalCollection);
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public PrincipalCollection resolvePrincipals() {
        Session sessionResolveSession;
        Subject subject;
        AuthenticationInfo authenticationInfo;
        PrincipalCollection principals = getPrincipals();
        if (isEmpty(principals) && (authenticationInfo = getAuthenticationInfo()) != null) {
            principals = authenticationInfo.getPrincipals();
        }
        if (isEmpty(principals) && (subject = getSubject()) != null) {
            principals = subject.getPrincipals();
        }
        return (!isEmpty(principals) || (sessionResolveSession = resolveSession()) == null) ? principals : (PrincipalCollection) sessionResolveSession.getAttribute(PRINCIPALS_SESSION_KEY);
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public Session getSession() {
        return (Session) getTypedValue(SESSION, Session.class);
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public void setSession(Session session) {
        nullSafePut(SESSION, session);
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public Session resolveSession() {
        Subject subject;
        Session session = getSession();
        return (session != null || (subject = getSubject()) == null) ? session : subject.getSession(false);
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public boolean isSessionCreationEnabled() {
        Boolean bool = (Boolean) getTypedValue(SESSION_CREATION_ENABLED, Boolean.class);
        return bool == null || bool.booleanValue();
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public void setSessionCreationEnabled(boolean z) {
        nullSafePut(SESSION_CREATION_ENABLED, Boolean.valueOf(z));
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public boolean isAuthenticated() {
        Boolean bool = (Boolean) getTypedValue(AUTHENTICATED, Boolean.class);
        return bool != null && bool.booleanValue();
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public void setAuthenticated(boolean z) {
        put(AUTHENTICATED, (Object) Boolean.valueOf(z));
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public boolean resolveAuthenticated() {
        Session sessionResolveSession;
        Boolean boolValueOf = (Boolean) getTypedValue(AUTHENTICATED, Boolean.class);
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(getAuthenticationInfo() != null);
        }
        if (!boolValueOf.booleanValue() && (sessionResolveSession = resolveSession()) != null) {
            Boolean bool = (Boolean) sessionResolveSession.getAttribute(AUTHENTICATED_SESSION_KEY);
            boolValueOf = Boolean.valueOf(bool != null && bool.booleanValue());
        }
        return boolValueOf.booleanValue();
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public AuthenticationInfo getAuthenticationInfo() {
        return (AuthenticationInfo) getTypedValue(AUTHENTICATION_INFO, AuthenticationInfo.class);
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public void setAuthenticationInfo(AuthenticationInfo authenticationInfo) {
        nullSafePut(AUTHENTICATION_INFO, authenticationInfo);
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public AuthenticationToken getAuthenticationToken() {
        return (AuthenticationToken) getTypedValue(AUTHENTICATION_TOKEN, AuthenticationToken.class);
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public void setAuthenticationToken(AuthenticationToken authenticationToken) {
        nullSafePut(AUTHENTICATION_TOKEN, authenticationToken);
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public String getHost() {
        return (String) getTypedValue(HOST, String.class);
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public void setHost(String str) {
        if (StringUtils.hasText(str)) {
            put(HOST, (Object) str);
        }
    }

    @Override // org.apache.shiro.subject.SubjectContext
    public String resolveHost() {
        Session sessionResolveSession;
        String host = getHost();
        if (host == null) {
            AuthenticationToken authenticationToken = getAuthenticationToken();
            if (authenticationToken instanceof HostAuthenticationToken) {
                host = ((HostAuthenticationToken) authenticationToken).getHost();
            }
        }
        return (host != null || (sessionResolveSession = resolveSession()) == null) ? host : sessionResolveSession.getHost();
    }
}
