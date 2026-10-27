package org.apache.http.auth;

import java.io.Serializable;
import java.security.Principal;
import org.apache.http.util.LangUtils;

/* JADX INFO: loaded from: classes7.dex */
public class NTCredentials implements Credentials, Serializable {
    private final String password;
    private final String workstation;

    @Override // org.apache.http.auth.Credentials
    public Principal getUserPrincipal() {
        return null;
    }

    public String getUserName() {
        throw null;
    }

    @Override // org.apache.http.auth.Credentials
    public String getPassword() {
        return this.password;
    }

    public String getDomain() {
        throw null;
    }

    public String getWorkstation() {
        return this.workstation;
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(17, (Object) null), this.workstation);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NTCredentials)) {
            return false;
        }
        NTCredentials nTCredentials = (NTCredentials) obj;
        nTCredentials.getClass();
        return LangUtils.equals((Object) null, (Object) null) && LangUtils.equals(this.workstation, nTCredentials.workstation);
    }

    public String toString() {
        return "[principal: " + ((Object) null) + "][workstation: " + this.workstation + "]";
    }
}
