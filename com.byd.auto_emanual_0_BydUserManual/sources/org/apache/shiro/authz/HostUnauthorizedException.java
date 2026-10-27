package org.apache.shiro.authz;

/* JADX INFO: loaded from: classes.dex */
public class HostUnauthorizedException extends UnauthorizedException {
    private String host;

    public HostUnauthorizedException() {
    }

    public HostUnauthorizedException(String str) {
        super(str);
    }

    public HostUnauthorizedException(Throwable th) {
        super(th);
    }

    public HostUnauthorizedException(String str, Throwable th) {
        super(str, th);
    }

    public String getHost() {
        return this.host;
    }

    public void setHostAddress(String str) {
        this.host = str;
    }
}
