package org.apache.shiro.authc;

import org.apache.shiro.util.ByteSource;

/* JADX INFO: loaded from: classes.dex */
public interface SaltedAuthenticationInfo extends AuthenticationInfo {
    ByteSource getCredentialsSalt();
}
