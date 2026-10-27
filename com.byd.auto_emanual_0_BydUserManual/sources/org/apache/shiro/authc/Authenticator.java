package org.apache.shiro.authc;

/* JADX INFO: loaded from: classes.dex */
public interface Authenticator {
    AuthenticationInfo authenticate(AuthenticationToken authenticationToken) throws AuthenticationException;
}
