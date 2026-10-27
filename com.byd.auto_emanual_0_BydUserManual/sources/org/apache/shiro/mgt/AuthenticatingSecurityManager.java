package org.apache.shiro.mgt;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.util.LifecycleUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class AuthenticatingSecurityManager extends RealmSecurityManager {
    private Authenticator authenticator = new ModularRealmAuthenticator();

    public Authenticator getAuthenticator() {
        return this.authenticator;
    }

    public void setAuthenticator(Authenticator authenticator) throws IllegalArgumentException {
        if (authenticator == null) {
            throw new IllegalArgumentException("Authenticator argument cannot be null.");
        }
        this.authenticator = authenticator;
    }

    @Override // org.apache.shiro.mgt.RealmSecurityManager
    protected void afterRealmsSet() {
        super.afterRealmsSet();
        Authenticator authenticator = this.authenticator;
        if (authenticator instanceof ModularRealmAuthenticator) {
            ((ModularRealmAuthenticator) authenticator).setRealms(getRealms());
        }
    }

    @Override // org.apache.shiro.authc.Authenticator
    public AuthenticationInfo authenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        return this.authenticator.authenticate(authenticationToken);
    }

    @Override // org.apache.shiro.mgt.RealmSecurityManager, org.apache.shiro.mgt.CachingSecurityManager, org.apache.shiro.util.Destroyable
    public void destroy() {
        LifecycleUtils.destroy(getAuthenticator());
        this.authenticator = null;
        super.destroy();
    }
}
