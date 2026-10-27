package org.apache.shiro.authc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractAuthenticator implements Authenticator, LogoutAware {
    private static final Logger log = LoggerFactory.getLogger(AbstractAuthenticator.class);
    private Collection<AuthenticationListener> listeners = new ArrayList();

    protected abstract AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException;

    public void setAuthenticationListeners(Collection<AuthenticationListener> collection) {
        if (collection == null) {
            this.listeners = new ArrayList();
        } else {
            this.listeners = collection;
        }
    }

    public Collection<AuthenticationListener> getAuthenticationListeners() {
        return this.listeners;
    }

    protected void notifySuccess(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        Iterator<AuthenticationListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onSuccess(authenticationToken, authenticationInfo);
        }
    }

    protected void notifyFailure(AuthenticationToken authenticationToken, AuthenticationException authenticationException) {
        Iterator<AuthenticationListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onFailure(authenticationToken, authenticationException);
        }
    }

    protected void notifyLogout(PrincipalCollection principalCollection) {
        Iterator<AuthenticationListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onLogout(principalCollection);
        }
    }

    @Override // org.apache.shiro.authc.LogoutAware
    public void onLogout(PrincipalCollection principalCollection) {
        notifyLogout(principalCollection);
    }

    @Override // org.apache.shiro.authc.Authenticator
    public final AuthenticationInfo authenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken == null) {
            throw new IllegalArgumentException("Method argument (authentication token) cannot be null.");
        }
        Logger logger = log;
        logger.trace("Authentication attempt received for token [{}]", authenticationToken);
        try {
            AuthenticationInfo authenticationInfoDoAuthenticate = doAuthenticate(authenticationToken);
            if (authenticationInfoDoAuthenticate == null) {
                throw new AuthenticationException("No account information found for authentication token [" + authenticationToken + "] by this Authenticator instance.  Please check that it is configured correctly.");
            }
            logger.debug("Authentication successful for token [{}].  Returned account [{}]", authenticationToken, authenticationInfoDoAuthenticate);
            notifySuccess(authenticationToken, authenticationInfoDoAuthenticate);
            return authenticationInfoDoAuthenticate;
        } finally {
        }
    }
}
