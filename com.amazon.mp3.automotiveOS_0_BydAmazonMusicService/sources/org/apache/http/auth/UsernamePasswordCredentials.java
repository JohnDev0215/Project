package org.apache.http.auth;

import java.io.Serializable;
import java.security.Principal;
import org.apache.http.util.Args;
import org.apache.http.util.LangUtils;

/* JADX INFO: loaded from: classes7.dex */
public class UsernamePasswordCredentials implements Credentials, Serializable {
    private final String password;
    private final BasicUserPrincipal principal;

    @Deprecated
    public UsernamePasswordCredentials(String str) {
        Args.notNull(str, "Username:password string");
        int iIndexOf = str.indexOf(58);
        if (iIndexOf >= 0) {
            this.principal = new BasicUserPrincipal(str.substring(0, iIndexOf));
            this.password = str.substring(iIndexOf + 1);
        } else {
            this.principal = new BasicUserPrincipal(str);
            this.password = null;
        }
    }

    @Override // org.apache.http.auth.Credentials
    public Principal getUserPrincipal() {
        return this.principal;
    }

    @Override // org.apache.http.auth.Credentials
    public String getPassword() {
        return this.password;
    }

    public int hashCode() {
        return this.principal.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof UsernamePasswordCredentials) && LangUtils.equals(this.principal, ((UsernamePasswordCredentials) obj).principal);
    }

    public String toString() {
        return this.principal.toString();
    }
}
