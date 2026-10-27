package org.apache.shiro.authc.pam;

import java.util.Collection;
import org.apache.shiro.authc.AbstractAuthenticator;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class ModularRealmAuthenticator extends AbstractAuthenticator {
    private static final Logger log = LoggerFactory.getLogger(ModularRealmAuthenticator.class);
    private AuthenticationStrategy authenticationStrategy = new AtLeastOneSuccessfulStrategy();
    private Collection<Realm> realms;

    public void setRealms(Collection<Realm> collection) {
        this.realms = collection;
    }

    protected Collection<Realm> getRealms() {
        return this.realms;
    }

    public AuthenticationStrategy getAuthenticationStrategy() {
        return this.authenticationStrategy;
    }

    public void setAuthenticationStrategy(AuthenticationStrategy authenticationStrategy) {
        this.authenticationStrategy = authenticationStrategy;
    }

    protected void assertRealmsConfigured() throws IllegalStateException {
        if (CollectionUtils.isEmpty(getRealms())) {
            throw new IllegalStateException("Configuration error:  No realms have been configured!  One or more realms must be present to execute an authentication attempt.");
        }
    }

    protected AuthenticationInfo doSingleRealmAuthentication(Realm realm, AuthenticationToken authenticationToken) {
        if (!realm.supports(authenticationToken)) {
            throw new UnsupportedTokenException("Realm [" + realm + "] does not support authentication token [" + authenticationToken + "].  Please ensure that the appropriate Realm implementation is configured correctly or that the realm accepts AuthenticationTokens of this type.");
        }
        AuthenticationInfo authenticationInfo = realm.getAuthenticationInfo(authenticationToken);
        if (authenticationInfo != null) {
            return authenticationInfo;
        }
        throw new UnknownAccountException("Realm [" + realm + "] was unable to find account data for the submitted AuthenticationToken [" + authenticationToken + "].");
    }

    protected AuthenticationInfo doMultiRealmAuthentication(Collection<Realm> collection, AuthenticationToken authenticationToken) {
        AuthenticationInfo authenticationInfo;
        Throwable th;
        AuthenticationStrategy authenticationStrategy = getAuthenticationStrategy();
        AuthenticationInfo authenticationInfoBeforeAllAttempts = authenticationStrategy.beforeAllAttempts(collection, authenticationToken);
        Logger logger = log;
        if (logger.isTraceEnabled()) {
            logger.trace("Iterating through {} realms for PAM authentication", Integer.valueOf(collection.size()));
        }
        for (Realm realm : collection) {
            try {
                AuthenticationInfo authenticationInfoBeforeAttempt = authenticationStrategy.beforeAttempt(realm, authenticationToken, authenticationInfoBeforeAllAttempts);
                if (realm.supports(authenticationToken)) {
                    log.trace("Attempting to authenticate token [{}] using realm [{}]", authenticationToken, realm);
                    try {
                        th = null;
                        authenticationInfo = realm.getAuthenticationInfo(authenticationToken);
                    } catch (Throwable th2) {
                        if (log.isDebugEnabled()) {
                            log.debug("Realm [" + realm + "] threw an exception during a multi-realm authentication attempt:", th2);
                        }
                        authenticationInfo = null;
                        th = th2;
                    }
                    authenticationInfoBeforeAllAttempts = authenticationStrategy.afterAttempt(realm, authenticationToken, authenticationInfo, authenticationInfoBeforeAttempt, th);
                } else {
                    log.debug("Realm [{}] does not support token {}.  Skipping realm.", realm, authenticationToken);
                    authenticationInfoBeforeAllAttempts = authenticationInfoBeforeAttempt;
                }
            } catch (ShortCircuitIterationException unused) {
            }
        }
        return authenticationStrategy.afterAllAttempts(authenticationToken, authenticationInfoBeforeAllAttempts);
    }

    @Override // org.apache.shiro.authc.AbstractAuthenticator
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        assertRealmsConfigured();
        Collection<Realm> realms = getRealms();
        if (realms.size() == 1) {
            return doSingleRealmAuthentication(realms.iterator().next(), authenticationToken);
        }
        return doMultiRealmAuthentication(realms, authenticationToken);
    }

    @Override // org.apache.shiro.authc.AbstractAuthenticator, org.apache.shiro.authc.LogoutAware
    public void onLogout(PrincipalCollection principalCollection) {
        super.onLogout(principalCollection);
        Collection<Realm> realms = getRealms();
        if (CollectionUtils.isEmpty(realms)) {
            return;
        }
        for (Realm realm : realms) {
            if (realm instanceof LogoutAware) {
                ((LogoutAware) realm).onLogout(principalCollection);
            }
        }
    }
}
