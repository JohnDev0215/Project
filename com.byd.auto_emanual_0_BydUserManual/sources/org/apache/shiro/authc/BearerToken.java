package org.apache.shiro.authc;

/* JADX INFO: loaded from: classes.dex */
public class BearerToken implements HostAuthenticationToken {
    private String host;
    private final String token;

    public BearerToken(String str) {
        this(str, null);
    }

    public BearerToken(String str, String str2) {
        this.token = str;
        this.host = str2;
    }

    @Override // org.apache.shiro.authc.HostAuthenticationToken
    public String getHost() {
        return this.host;
    }

    @Override // org.apache.shiro.authc.AuthenticationToken
    public Object getPrincipal() {
        return this.token;
    }

    @Override // org.apache.shiro.authc.AuthenticationToken
    public Object getCredentials() {
        return this.token;
    }

    public String getToken() {
        return this.token;
    }
}
