package org.apache.shiro.subject;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public interface Subject {
    Runnable associateWith(Runnable runnable);

    <V> Callable<V> associateWith(Callable<V> callable);

    void checkPermission(String str) throws AuthorizationException;

    void checkPermission(Permission permission) throws AuthorizationException;

    void checkPermissions(Collection<Permission> collection) throws AuthorizationException;

    void checkPermissions(String... strArr) throws AuthorizationException;

    void checkRole(String str) throws AuthorizationException;

    void checkRoles(Collection<String> collection) throws AuthorizationException;

    void checkRoles(String... strArr) throws AuthorizationException;

    <V> V execute(Callable<V> callable) throws ExecutionException;

    void execute(Runnable runnable);

    PrincipalCollection getPreviousPrincipals();

    Object getPrincipal();

    PrincipalCollection getPrincipals();

    Session getSession();

    Session getSession(boolean z);

    boolean hasAllRoles(Collection<String> collection);

    boolean hasRole(String str);

    boolean[] hasRoles(List<String> list);

    boolean isAuthenticated();

    boolean isPermitted(String str);

    boolean isPermitted(Permission permission);

    boolean[] isPermitted(List<Permission> list);

    boolean[] isPermitted(String... strArr);

    boolean isPermittedAll(Collection<Permission> collection);

    boolean isPermittedAll(String... strArr);

    boolean isRemembered();

    boolean isRunAs();

    void login(AuthenticationToken authenticationToken) throws AuthenticationException;

    void logout();

    PrincipalCollection releaseRunAs();

    void runAs(PrincipalCollection principalCollection) throws IllegalStateException, NullPointerException;

    public static class Builder {
        private final SecurityManager securityManager;
        private final SubjectContext subjectContext;

        public Builder() {
            this(SecurityUtils.getSecurityManager());
        }

        public Builder(SecurityManager securityManager) {
            Objects.requireNonNull(securityManager, "SecurityManager method argument cannot be null.");
            this.securityManager = securityManager;
            SubjectContext subjectContextNewSubjectContextInstance = newSubjectContextInstance();
            this.subjectContext = subjectContextNewSubjectContextInstance;
            if (subjectContextNewSubjectContextInstance == null) {
                throw new IllegalStateException("Subject instance returned from 'newSubjectContextInstance' cannot be null.");
            }
            subjectContextNewSubjectContextInstance.setSecurityManager(securityManager);
        }

        protected SubjectContext newSubjectContextInstance() {
            return new DefaultSubjectContext();
        }

        protected SubjectContext getSubjectContext() {
            return this.subjectContext;
        }

        public Builder sessionId(Serializable serializable) {
            if (serializable != null) {
                this.subjectContext.setSessionId(serializable);
            }
            return this;
        }

        public Builder host(String str) {
            if (StringUtils.hasText(str)) {
                this.subjectContext.setHost(str);
            }
            return this;
        }

        public Builder session(Session session) {
            if (session != null) {
                this.subjectContext.setSession(session);
            }
            return this;
        }

        public Builder principals(PrincipalCollection principalCollection) {
            if (principalCollection != null && !principalCollection.isEmpty()) {
                this.subjectContext.setPrincipals(principalCollection);
            }
            return this;
        }

        public Builder sessionCreationEnabled(boolean z) {
            this.subjectContext.setSessionCreationEnabled(z);
            return this;
        }

        public Builder authenticated(boolean z) {
            this.subjectContext.setAuthenticated(z);
            return this;
        }

        public Builder contextAttribute(String str, Object obj) {
            if (str == null) {
                throw new IllegalArgumentException("Subject context map key cannot be null.");
            }
            if (obj == null) {
                this.subjectContext.remove(str);
            } else {
                this.subjectContext.put(str, obj);
            }
            return this;
        }

        public Subject buildSubject() {
            return this.securityManager.createSubject(this.subjectContext);
        }
    }
}
