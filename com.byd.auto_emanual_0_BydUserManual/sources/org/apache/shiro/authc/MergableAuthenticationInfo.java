package org.apache.shiro.authc;

/* JADX INFO: loaded from: classes.dex */
public interface MergableAuthenticationInfo extends AuthenticationInfo {
    void merge(AuthenticationInfo authenticationInfo);
}
