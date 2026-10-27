package org.apache.shiro.authc.pam;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.PrincipalCollection;

/* JADX INFO: loaded from: classes.dex */
public class AtLeastOneSuccessfulStrategy extends AbstractAuthenticationStrategy {
    private static boolean isEmpty(PrincipalCollection principalCollection) {
        return principalCollection == null || principalCollection.isEmpty();
    }

    @Override // org.apache.shiro.authc.pam.AbstractAuthenticationStrategy, org.apache.shiro.authc.pam.AuthenticationStrategy
    public AuthenticationInfo afterAllAttempts(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) throws AuthenticationException {
        if (authenticationInfo == null || isEmpty(authenticationInfo.getPrincipals())) {
            throw new AuthenticationException("Authentication token of type [" + authenticationToken.getClass() + "] could not be authenticated by any configured realms.  Please ensure that at least one realm can authenticate these tokens.");
        }
        return authenticationInfo;
    }
}
