package org.apache.shiro.authc.credential;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

/* JADX INFO: loaded from: classes.dex */
public class AllowAllCredentialsMatcher implements CredentialsMatcher {
    @Override // org.apache.shiro.authc.credential.CredentialsMatcher
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        return true;
    }
}
