package org.apache.http.impl.auth;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.protocol.HttpContext;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.Oid;

/* JADX INFO: loaded from: classes7.dex */
public class KerberosScheme extends GGSSchemeBase {
    @Override // org.apache.http.auth.AuthScheme
    public String getRealm() {
        return null;
    }

    @Override // org.apache.http.auth.AuthScheme
    public String getSchemeName() {
        return "Kerberos";
    }

    @Override // org.apache.http.auth.AuthScheme
    public boolean isConnectionBased() {
        return true;
    }

    public KerberosScheme(boolean z, boolean z2) {
        super(z, z2);
    }

    public KerberosScheme() {
    }

    @Override // org.apache.http.impl.auth.GGSSchemeBase, org.apache.http.impl.auth.AuthSchemeBase, org.apache.http.auth.ContextAwareAuthScheme
    public Header authenticate(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) throws AuthenticationException {
        return super.authenticate(credentials, httpRequest, httpContext);
    }

    @Override // org.apache.http.impl.auth.GGSSchemeBase
    protected byte[] generateToken(byte[] bArr, String str) throws GSSException {
        return super.generateToken(bArr, str);
    }

    @Override // org.apache.http.impl.auth.GGSSchemeBase
    protected byte[] generateToken(byte[] bArr, String str, Credentials credentials) throws GSSException {
        return generateGSSToken(bArr, new Oid("1.2.840.113554.1.2.2"), str, credentials);
    }
}
