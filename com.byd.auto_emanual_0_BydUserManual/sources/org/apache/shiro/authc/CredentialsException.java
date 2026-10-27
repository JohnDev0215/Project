package org.apache.shiro.authc;

/* JADX INFO: loaded from: classes.dex */
public class CredentialsException extends AuthenticationException {
    public CredentialsException() {
    }

    public CredentialsException(String str) {
        super(str);
    }

    public CredentialsException(Throwable th) {
        super(th);
    }

    public CredentialsException(String str, Throwable th) {
        super(str, th);
    }
}
