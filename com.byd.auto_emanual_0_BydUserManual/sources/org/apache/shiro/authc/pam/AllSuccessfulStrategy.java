package org.apache.shiro.authc.pam;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class AllSuccessfulStrategy extends AbstractAuthenticationStrategy {
    private static final Logger log = LoggerFactory.getLogger(AllSuccessfulStrategy.class);

    @Override // org.apache.shiro.authc.pam.AbstractAuthenticationStrategy, org.apache.shiro.authc.pam.AuthenticationStrategy
    public AuthenticationInfo beforeAttempt(Realm realm, AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) throws AuthenticationException {
        if (realm.supports(authenticationToken)) {
            return authenticationInfo;
        }
        throw new UnsupportedTokenException("Realm [" + realm + "] of type [" + realm.getClass().getName() + "] does not support  the submitted AuthenticationToken [" + authenticationToken + "].  The [" + getClass().getName() + "] implementation requires all configured realm(s) to support and be able to process the submitted AuthenticationToken.");
    }

    @Override // org.apache.shiro.authc.pam.AbstractAuthenticationStrategy, org.apache.shiro.authc.pam.AuthenticationStrategy
    public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo, AuthenticationInfo authenticationInfo2, Throwable th) throws AuthenticationException {
        if (th != null) {
            if (th instanceof AuthenticationException) {
                throw ((AuthenticationException) th);
            }
            throw new AuthenticationException("Unable to acquire account data from realm [" + realm + "].  The [" + getClass().getName() + " implementation requires all configured realm(s) to operate successfully for a successful authentication.", th);
        }
        if (authenticationInfo == null) {
            throw new UnknownAccountException("Realm [" + realm + "] could not find any associated account data for the submitted AuthenticationToken [" + authenticationToken + "].  The [" + getClass().getName() + "] implementation requires all configured realm(s) to acquire valid account data for a submitted token during the log-in process.");
        }
        log.debug("Account successfully authenticated using realm [{}]", realm);
        merge(authenticationInfo, authenticationInfo2);
        return authenticationInfo2;
    }
}
