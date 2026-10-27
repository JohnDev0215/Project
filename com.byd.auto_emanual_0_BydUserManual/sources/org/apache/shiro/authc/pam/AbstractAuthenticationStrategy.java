package org.apache.shiro.authc.pam;

import java.util.Collection;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.MergableAuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.Realm;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractAuthenticationStrategy implements AuthenticationStrategy {
    @Override // org.apache.shiro.authc.pam.AuthenticationStrategy
    public AuthenticationInfo afterAllAttempts(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) throws AuthenticationException {
        return authenticationInfo;
    }

    @Override // org.apache.shiro.authc.pam.AuthenticationStrategy
    public AuthenticationInfo beforeAttempt(Realm realm, AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) throws AuthenticationException {
        return authenticationInfo;
    }

    @Override // org.apache.shiro.authc.pam.AuthenticationStrategy
    public AuthenticationInfo beforeAllAttempts(Collection<? extends Realm> collection, AuthenticationToken authenticationToken) throws AuthenticationException {
        return new SimpleAuthenticationInfo();
    }

    @Override // org.apache.shiro.authc.pam.AuthenticationStrategy
    public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo, AuthenticationInfo authenticationInfo2, Throwable th) throws AuthenticationException {
        return authenticationInfo == null ? authenticationInfo2 : authenticationInfo2 == null ? authenticationInfo : merge(authenticationInfo, authenticationInfo2);
    }

    protected AuthenticationInfo merge(AuthenticationInfo authenticationInfo, AuthenticationInfo authenticationInfo2) {
        if (authenticationInfo2 instanceof MergableAuthenticationInfo) {
            ((MergableAuthenticationInfo) authenticationInfo2).merge(authenticationInfo);
            return authenticationInfo2;
        }
        throw new IllegalArgumentException("Attempt to merge authentication info from multiple realms, but aggregate AuthenticationInfo is not of type MergableAuthenticationInfo.");
    }
}
