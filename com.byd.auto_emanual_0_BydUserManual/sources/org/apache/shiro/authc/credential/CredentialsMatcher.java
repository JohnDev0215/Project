package org.apache.shiro.authc.credential;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

/* JADX INFO: loaded from: classes.dex */
public interface CredentialsMatcher {
    boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo);
}
