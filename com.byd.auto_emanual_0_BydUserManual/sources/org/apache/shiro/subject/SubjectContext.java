package org.apache.shiro.subject;

import java.io.Serializable;
import java.util.Map;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;

/* JADX INFO: loaded from: classes.dex */
public interface SubjectContext extends Map<String, Object> {
    AuthenticationInfo getAuthenticationInfo();

    AuthenticationToken getAuthenticationToken();

    String getHost();

    PrincipalCollection getPrincipals();

    SecurityManager getSecurityManager();

    Session getSession();

    Serializable getSessionId();

    Subject getSubject();

    boolean isAuthenticated();

    boolean isSessionCreationEnabled();

    boolean resolveAuthenticated();

    String resolveHost();

    PrincipalCollection resolvePrincipals();

    SecurityManager resolveSecurityManager();

    Session resolveSession();

    void setAuthenticated(boolean z);

    void setAuthenticationInfo(AuthenticationInfo authenticationInfo);

    void setAuthenticationToken(AuthenticationToken authenticationToken);

    void setHost(String str);

    void setPrincipals(PrincipalCollection principalCollection);

    void setSecurityManager(SecurityManager securityManager);

    void setSession(Session session);

    void setSessionCreationEnabled(boolean z);

    void setSessionId(Serializable serializable);

    void setSubject(Subject subject);
}
