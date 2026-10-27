package org.apache.shiro.subject.support;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.ProxiedSession;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.DefaultSessionContext;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.subject.ExecutionException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class DelegatingSubject implements Subject {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected boolean authenticated;
    protected String host;
    protected PrincipalCollection principals;
    protected transient SecurityManager securityManager;
    protected Session session;
    protected boolean sessionCreationEnabled;
    private static final Logger log = LoggerFactory.getLogger(DelegatingSubject.class);
    private static final String RUN_AS_PRINCIPALS_SESSION_KEY = DelegatingSubject.class.getName() + ".RUN_AS_PRINCIPALS_SESSION_KEY";

    public DelegatingSubject(SecurityManager securityManager) {
        this(null, false, null, null, securityManager);
    }

    public DelegatingSubject(PrincipalCollection principalCollection, boolean z, String str, Session session, SecurityManager securityManager) {
        this(principalCollection, z, str, session, true, securityManager);
    }

    public DelegatingSubject(PrincipalCollection principalCollection, boolean z, String str, Session session, boolean z2, SecurityManager securityManager) {
        if (securityManager == null) {
            throw new IllegalArgumentException("SecurityManager argument cannot be null.");
        }
        this.securityManager = securityManager;
        this.principals = principalCollection;
        this.authenticated = z;
        this.host = str;
        if (session != null) {
            this.session = decorate(session);
        }
        this.sessionCreationEnabled = z2;
    }

    protected Session decorate(Session session) {
        if (session == null) {
            throw new IllegalArgumentException("session cannot be null");
        }
        return new StoppingAwareProxiedSession(session, this);
    }

    public SecurityManager getSecurityManager() {
        return this.securityManager;
    }

    private static boolean isEmpty(PrincipalCollection principalCollection) {
        return principalCollection == null || principalCollection.isEmpty();
    }

    protected boolean hasPrincipals() {
        return !isEmpty(getPrincipals());
    }

    public String getHost() {
        return this.host;
    }

    private Object getPrimaryPrincipal(PrincipalCollection principalCollection) {
        if (isEmpty(principalCollection)) {
            return null;
        }
        return principalCollection.getPrimaryPrincipal();
    }

    @Override // org.apache.shiro.subject.Subject
    public Object getPrincipal() {
        return getPrimaryPrincipal(getPrincipals());
    }

    @Override // org.apache.shiro.subject.Subject
    public PrincipalCollection getPrincipals() {
        List<PrincipalCollection> runAsPrincipalsStack = getRunAsPrincipalsStack();
        return CollectionUtils.isEmpty(runAsPrincipalsStack) ? this.principals : runAsPrincipalsStack.get(0);
    }

    @Override // org.apache.shiro.subject.Subject
    public boolean isPermitted(String str) {
        return hasPrincipals() && this.securityManager.isPermitted(getPrincipals(), str);
    }

    @Override // org.apache.shiro.subject.Subject
    public boolean isPermitted(Permission permission) {
        return hasPrincipals() && this.securityManager.isPermitted(getPrincipals(), permission);
    }

    @Override // org.apache.shiro.subject.Subject
    public boolean[] isPermitted(String... strArr) {
        if (hasPrincipals()) {
            return this.securityManager.isPermitted(getPrincipals(), strArr);
        }
        return new boolean[strArr.length];
    }

    @Override // org.apache.shiro.subject.Subject
    public boolean[] isPermitted(List<Permission> list) {
        if (hasPrincipals()) {
            return this.securityManager.isPermitted(getPrincipals(), list);
        }
        return new boolean[list.size()];
    }

    @Override // org.apache.shiro.subject.Subject
    public boolean isPermittedAll(String... strArr) {
        return hasPrincipals() && this.securityManager.isPermittedAll(getPrincipals(), strArr);
    }

    @Override // org.apache.shiro.subject.Subject
    public boolean isPermittedAll(Collection<Permission> collection) {
        return hasPrincipals() && this.securityManager.isPermittedAll(getPrincipals(), collection);
    }

    protected void assertAuthzCheckPossible() throws AuthorizationException {
        if (!hasPrincipals()) {
            throw new UnauthenticatedException("This subject is anonymous - it does not have any identifying principals and authorization operations require an identity to check against.  A Subject instance will acquire these identifying principals automatically after a successful login is performed be executing " + Subject.class.getName() + ".login(AuthenticationToken) or when 'Remember Me' functionality is enabled by the SecurityManager.  This exception can also occur when a previously logged-in Subject has logged out which makes it anonymous again.  Because an identity is currently not known due to any of these conditions, authorization is denied.");
        }
    }

    @Override // org.apache.shiro.subject.Subject
    public void checkPermission(String str) throws AuthorizationException {
        assertAuthzCheckPossible();
        this.securityManager.checkPermission(getPrincipals(), str);
    }

    @Override // org.apache.shiro.subject.Subject
    public void checkPermission(Permission permission) throws AuthorizationException {
        assertAuthzCheckPossible();
        this.securityManager.checkPermission(getPrincipals(), permission);
    }

    @Override // org.apache.shiro.subject.Subject
    public void checkPermissions(String... strArr) throws AuthorizationException {
        assertAuthzCheckPossible();
        this.securityManager.checkPermissions(getPrincipals(), strArr);
    }

    @Override // org.apache.shiro.subject.Subject
    public void checkPermissions(Collection<Permission> collection) throws AuthorizationException {
        assertAuthzCheckPossible();
        this.securityManager.checkPermissions(getPrincipals(), collection);
    }

    @Override // org.apache.shiro.subject.Subject
    public boolean hasRole(String str) {
        return hasPrincipals() && this.securityManager.hasRole(getPrincipals(), str);
    }

    @Override // org.apache.shiro.subject.Subject
    public boolean[] hasRoles(List<String> list) {
        if (hasPrincipals()) {
            return this.securityManager.hasRoles(getPrincipals(), list);
        }
        return new boolean[list.size()];
    }

    @Override // org.apache.shiro.subject.Subject
    public boolean hasAllRoles(Collection<String> collection) {
        return hasPrincipals() && this.securityManager.hasAllRoles(getPrincipals(), collection);
    }

    @Override // org.apache.shiro.subject.Subject
    public void checkRole(String str) throws AuthorizationException {
        assertAuthzCheckPossible();
        this.securityManager.checkRole(getPrincipals(), str);
    }

    @Override // org.apache.shiro.subject.Subject
    public void checkRoles(String... strArr) throws AuthorizationException {
        assertAuthzCheckPossible();
        this.securityManager.checkRoles(getPrincipals(), strArr);
    }

    @Override // org.apache.shiro.subject.Subject
    public void checkRoles(Collection<String> collection) throws AuthorizationException {
        assertAuthzCheckPossible();
        this.securityManager.checkRoles(getPrincipals(), collection);
    }

    @Override // org.apache.shiro.subject.Subject
    public void login(AuthenticationToken authenticationToken) throws AuthenticationException {
        PrincipalCollection principals;
        String host;
        clearRunAsIdentitiesInternal();
        Subject subjectLogin = this.securityManager.login(this, authenticationToken);
        if (subjectLogin instanceof DelegatingSubject) {
            DelegatingSubject delegatingSubject = (DelegatingSubject) subjectLogin;
            principals = delegatingSubject.principals;
            host = delegatingSubject.host;
        } else {
            principals = subjectLogin.getPrincipals();
            host = null;
        }
        if (principals == null || principals.isEmpty()) {
            throw new IllegalStateException("Principals returned from securityManager.login( token ) returned a null or empty value.  This value must be non null and populated with one or more elements.");
        }
        this.principals = principals;
        this.authenticated = true;
        if (authenticationToken instanceof HostAuthenticationToken) {
            host = ((HostAuthenticationToken) authenticationToken).getHost();
        }
        if (host != null) {
            this.host = host;
        }
        Session session = subjectLogin.getSession(false);
        if (session != null) {
            this.session = decorate(session);
        } else {
            this.session = null;
        }
    }

    @Override // org.apache.shiro.subject.Subject
    public boolean isAuthenticated() {
        return this.authenticated && hasPrincipals();
    }

    @Override // org.apache.shiro.subject.Subject
    public boolean isRemembered() {
        PrincipalCollection principals = getPrincipals();
        return (principals == null || principals.isEmpty() || isAuthenticated()) ? false : true;
    }

    protected boolean isSessionCreationEnabled() {
        return this.sessionCreationEnabled;
    }

    @Override // org.apache.shiro.subject.Subject
    public Session getSession() {
        return getSession(true);
    }

    @Override // org.apache.shiro.subject.Subject
    public Session getSession(boolean z) {
        Logger logger = log;
        if (logger.isTraceEnabled()) {
            StringBuilder sbAppend = new StringBuilder().append("attempting to get session; create = ").append(z).append("; session is null = ").append(this.session == null).append("; session has id = ");
            Session session = this.session;
            logger.trace(sbAppend.append((session == null || session.getId() == null) ? false : true).toString());
        }
        if (this.session == null && z) {
            if (!isSessionCreationEnabled()) {
                throw new DisabledSessionException("Session creation has been disabled for the current subject.  This exception indicates that there is either a programming error (using a session when it should never be used) or that Shiro's configuration needs to be adjusted to allow Sessions to be created for the current Subject.  See the " + DisabledSessionException.class.getName() + " JavaDoc for more.");
            }
            logger.trace("Starting session for host {}", getHost());
            this.session = decorate(this.securityManager.start(createSessionContext()));
        }
        return this.session;
    }

    protected SessionContext createSessionContext() {
        DefaultSessionContext defaultSessionContext = new DefaultSessionContext();
        if (StringUtils.hasText(this.host)) {
            defaultSessionContext.setHost(this.host);
        }
        return defaultSessionContext;
    }

    private void clearRunAsIdentitiesInternal() {
        try {
            clearRunAsIdentities();
        } catch (SessionException e) {
            log.debug("Encountered session exception trying to clear 'runAs' identities during logout.  This can generally safely be ignored.", e);
        }
    }

    @Override // org.apache.shiro.subject.Subject
    public void logout() {
        try {
            clearRunAsIdentitiesInternal();
            this.securityManager.logout(this);
        } finally {
            this.session = null;
            this.principals = null;
            this.authenticated = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sessionStopped() {
        this.session = null;
    }

    @Override // org.apache.shiro.subject.Subject
    public <V> V execute(Callable<V> callable) throws ExecutionException {
        try {
            return associateWith(callable).call();
        } catch (Throwable th) {
            throw new ExecutionException(th);
        }
    }

    @Override // org.apache.shiro.subject.Subject
    public void execute(Runnable runnable) {
        associateWith(runnable).run();
    }

    @Override // org.apache.shiro.subject.Subject
    public <V> Callable<V> associateWith(Callable<V> callable) {
        return new SubjectCallable(this, callable);
    }

    @Override // org.apache.shiro.subject.Subject
    public Runnable associateWith(Runnable runnable) {
        if (runnable instanceof Thread) {
            throw new UnsupportedOperationException("This implementation does not support Thread arguments because of JDK ThreadLocal inheritance mechanisms required by Shiro.  Instead, the method argument should be a non-Thread Runnable and the return value from this method can then be given to an ExecutorService or another Thread.");
        }
        return new SubjectRunnable(this, runnable);
    }

    private class StoppingAwareProxiedSession extends ProxiedSession {
        private final DelegatingSubject owner;

        private StoppingAwareProxiedSession(Session session, DelegatingSubject delegatingSubject) {
            super(session);
            this.owner = delegatingSubject;
        }

        @Override // org.apache.shiro.session.ProxiedSession, org.apache.shiro.session.Session
        public void stop() throws InvalidSessionException {
            super.stop();
            this.owner.sessionStopped();
        }
    }

    @Override // org.apache.shiro.subject.Subject
    public void runAs(PrincipalCollection principalCollection) {
        if (!hasPrincipals()) {
            throw new IllegalStateException("This subject does not yet have an identity.  Assuming the identity of another Subject is only allowed for Subjects with an existing identity.  Try logging this subject in first, or using the " + Subject.Builder.class.getName() + " to build ad hoc Subject instances with identities as necessary.");
        }
        pushIdentity(principalCollection);
    }

    @Override // org.apache.shiro.subject.Subject
    public boolean isRunAs() {
        return !CollectionUtils.isEmpty(getRunAsPrincipalsStack());
    }

    @Override // org.apache.shiro.subject.Subject
    public PrincipalCollection getPreviousPrincipals() {
        List<PrincipalCollection> runAsPrincipalsStack = getRunAsPrincipalsStack();
        int size = runAsPrincipalsStack != null ? runAsPrincipalsStack.size() : 0;
        if (size <= 0) {
            return null;
        }
        if (size == 1) {
            return this.principals;
        }
        return runAsPrincipalsStack.get(1);
    }

    @Override // org.apache.shiro.subject.Subject
    public PrincipalCollection releaseRunAs() {
        return popIdentity();
    }

    private List<PrincipalCollection> getRunAsPrincipalsStack() {
        Session session = getSession(false);
        if (session == null) {
            return null;
        }
        try {
            return (List) session.getAttribute(RUN_AS_PRINCIPALS_SESSION_KEY);
        } catch (SessionException e) {
            log.debug("Encountered session exception trying to get 'runAs' principal stack.  This can generally safely be ignored.", e);
            return null;
        }
    }

    private void clearRunAsIdentities() {
        Session session = getSession(false);
        if (session != null) {
            session.removeAttribute(RUN_AS_PRINCIPALS_SESSION_KEY);
        }
    }

    private void pushIdentity(PrincipalCollection principalCollection) throws NullPointerException {
        if (isEmpty(principalCollection)) {
            throw new NullPointerException("Specified Subject principals cannot be null or empty for 'run as' functionality.");
        }
        List<PrincipalCollection> runAsPrincipalsStack = getRunAsPrincipalsStack();
        if (runAsPrincipalsStack == null) {
            runAsPrincipalsStack = new CopyOnWriteArrayList<>();
        }
        runAsPrincipalsStack.add(0, principalCollection);
        getSession().setAttribute(RUN_AS_PRINCIPALS_SESSION_KEY, runAsPrincipalsStack);
    }

    private PrincipalCollection popIdentity() {
        List<PrincipalCollection> runAsPrincipalsStack = getRunAsPrincipalsStack();
        if (CollectionUtils.isEmpty(runAsPrincipalsStack)) {
            return null;
        }
        PrincipalCollection principalCollectionRemove = runAsPrincipalsStack.remove(0);
        if (!CollectionUtils.isEmpty(runAsPrincipalsStack)) {
            getSession().setAttribute(RUN_AS_PRINCIPALS_SESSION_KEY, runAsPrincipalsStack);
            return principalCollectionRemove;
        }
        clearRunAsIdentities();
        return principalCollectionRemove;
    }
}
