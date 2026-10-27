package org.apache.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

/* JADX INFO: loaded from: classes.dex */
public interface Realm {
    AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException;

    String getName();

    boolean supports(AuthenticationToken authenticationToken);
}
