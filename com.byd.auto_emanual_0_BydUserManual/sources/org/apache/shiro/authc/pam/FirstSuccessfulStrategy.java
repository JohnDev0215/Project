package org.apache.shiro.authc.pam;

import java.util.Collection;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;

/* JADX INFO: loaded from: classes.dex */
public class FirstSuccessfulStrategy extends AbstractAuthenticationStrategy {
    private boolean stopAfterFirstSuccess;

    @Override // org.apache.shiro.authc.pam.AbstractAuthenticationStrategy, org.apache.shiro.authc.pam.AuthenticationStrategy
    public AuthenticationInfo beforeAllAttempts(Collection<? extends Realm> collection, AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }

    public void setStopAfterFirstSuccess(boolean z) {
        this.stopAfterFirstSuccess = z;
    }

    public boolean getStopAfterFirstSuccess() {
        return this.stopAfterFirstSuccess;
    }

    @Override // org.apache.shiro.authc.pam.AbstractAuthenticationStrategy, org.apache.shiro.authc.pam.AuthenticationStrategy
    public AuthenticationInfo beforeAttempt(Realm realm, AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) throws AuthenticationException {
        if (!getStopAfterFirstSuccess() || authenticationInfo == null || isEmpty(authenticationInfo.getPrincipals())) {
            return authenticationInfo;
        }
        throw new ShortCircuitIterationException();
    }

    private static boolean isEmpty(PrincipalCollection principalCollection) {
        return principalCollection == null || principalCollection.isEmpty();
    }

    @Override // org.apache.shiro.authc.pam.AbstractAuthenticationStrategy
    protected AuthenticationInfo merge(AuthenticationInfo authenticationInfo, AuthenticationInfo authenticationInfo2) {
        return ((authenticationInfo2 == null || isEmpty(authenticationInfo2.getPrincipals())) && authenticationInfo != null) ? authenticationInfo : authenticationInfo2;
    }
}
