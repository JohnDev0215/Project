package org.apache.shiro.authc;

import org.apache.shiro.subject.PrincipalCollection;

/* JADX INFO: loaded from: classes.dex */
public interface AuthenticationListener {
    void onFailure(AuthenticationToken authenticationToken, AuthenticationException authenticationException);

    void onLogout(PrincipalCollection principalCollection);

    void onSuccess(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo);
}
