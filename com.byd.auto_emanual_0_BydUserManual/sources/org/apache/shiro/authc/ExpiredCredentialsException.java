package org.apache.shiro.authc;

/* JADX INFO: loaded from: classes.dex */
public class ExpiredCredentialsException extends CredentialsException {
    public ExpiredCredentialsException() {
    }

    public ExpiredCredentialsException(String str) {
        super(str);
    }

    public ExpiredCredentialsException(Throwable th) {
        super(th);
    }

    public ExpiredCredentialsException(String str, Throwable th) {
        super(str, th);
    }
}
