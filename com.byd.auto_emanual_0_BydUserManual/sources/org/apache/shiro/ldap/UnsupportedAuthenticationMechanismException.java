package org.apache.shiro.ldap;

import org.apache.shiro.dao.InvalidResourceUsageException;

/* JADX INFO: loaded from: classes.dex */
public class UnsupportedAuthenticationMechanismException extends InvalidResourceUsageException {
    public UnsupportedAuthenticationMechanismException(String str) {
        super(str);
    }

    public UnsupportedAuthenticationMechanismException(String str, Throwable th) {
        super(str, th);
    }
}
