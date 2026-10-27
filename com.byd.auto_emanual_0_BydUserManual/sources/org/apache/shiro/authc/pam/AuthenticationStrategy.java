package org.apache.shiro.authc.pam;

import java.util.Collection;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.Realm;

/* JADX INFO: loaded from: classes.dex */
public interface AuthenticationStrategy {
    AuthenticationInfo afterAllAttempts(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) throws AuthenticationException;

    AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo, AuthenticationInfo authenticationInfo2, Throwable th) throws AuthenticationException;

    AuthenticationInfo beforeAllAttempts(Collection<? extends Realm> collection, AuthenticationToken authenticationToken) throws AuthenticationException;

    AuthenticationInfo beforeAttempt(Realm realm, AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) throws AuthenticationException;
}
