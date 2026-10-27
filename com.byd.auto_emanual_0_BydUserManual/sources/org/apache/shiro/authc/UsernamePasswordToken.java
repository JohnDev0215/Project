package org.apache.shiro.authc;

/* JADX INFO: loaded from: classes.dex */
public class UsernamePasswordToken implements HostAuthenticationToken, RememberMeAuthenticationToken {
    private String host;
    private char[] password;
    private boolean rememberMe;
    private String username;

    public UsernamePasswordToken() {
        this.rememberMe = false;
    }

    public UsernamePasswordToken(String str, char[] cArr) {
        this(str, cArr, false, (String) null);
    }

    public UsernamePasswordToken(String str, String str2) {
        this(str, str2 != null ? str2.toCharArray() : null, false, (String) null);
    }

    public UsernamePasswordToken(String str, char[] cArr, String str2) {
        this(str, cArr, false, str2);
    }

    public UsernamePasswordToken(String str, String str2, String str3) {
        this(str, str2 != null ? str2.toCharArray() : null, false, str3);
    }

    public UsernamePasswordToken(String str, char[] cArr, boolean z) {
        this(str, cArr, z, (String) null);
    }

    public UsernamePasswordToken(String str, String str2, boolean z) {
        this(str, str2 != null ? str2.toCharArray() : null, z, (String) null);
    }

    public UsernamePasswordToken(String str, char[] cArr, boolean z, String str2) {
        this.rememberMe = false;
        this.username = str;
        this.password = cArr;
        this.rememberMe = z;
        this.host = str2;
    }

    public UsernamePasswordToken(String str, String str2, boolean z, String str3) {
        this(str, str2 != null ? str2.toCharArray() : null, z, str3);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public char[] getPassword() {
        return this.password;
    }

    public void setPassword(char[] cArr) {
        this.password = cArr;
    }

    @Override // org.apache.shiro.authc.AuthenticationToken
    public Object getPrincipal() {
        return getUsername();
    }

    @Override // org.apache.shiro.authc.AuthenticationToken
    public Object getCredentials() {
        return getPassword();
    }

    @Override // org.apache.shiro.authc.HostAuthenticationToken
    public String getHost() {
        return this.host;
    }

    public void setHost(String str) {
        this.host = str;
    }

    @Override // org.apache.shiro.authc.RememberMeAuthenticationToken
    public boolean isRememberMe() {
        return this.rememberMe;
    }

    public void setRememberMe(boolean z) {
        this.rememberMe = z;
    }

    public void clear() {
        this.username = null;
        this.host = null;
        this.rememberMe = false;
        if (this.password == null) {
            return;
        }
        int i = 0;
        while (true) {
            char[] cArr = this.password;
            if (i < cArr.length) {
                cArr[i] = 0;
                i++;
            } else {
                this.password = null;
                return;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(" - ");
        sb.append(this.username);
        sb.append(", rememberMe=").append(this.rememberMe);
        if (this.host != null) {
            sb.append(" (").append(this.host).append(")");
        }
        return sb.toString();
    }
}
