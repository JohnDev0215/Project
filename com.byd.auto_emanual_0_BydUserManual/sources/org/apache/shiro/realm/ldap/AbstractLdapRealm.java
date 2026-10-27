package org.apache.shiro.realm.ldap;

import javax.naming.NamingException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractLdapRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(AbstractLdapRealm.class);
    protected String principalSuffix = null;
    protected String searchBase = null;
    protected String url = null;
    protected String systemUsername = null;
    protected String systemPassword = null;
    protected String searchFilter = "(&(objectClass=*)(userPrincipalName={0}))";
    private LdapContextFactory ldapContextFactory = null;

    protected abstract AuthenticationInfo queryForAuthenticationInfo(AuthenticationToken authenticationToken, LdapContextFactory ldapContextFactory) throws NamingException;

    protected abstract AuthorizationInfo queryForAuthorizationInfo(PrincipalCollection principalCollection, LdapContextFactory ldapContextFactory) throws NamingException;

    public void setPrincipalSuffix(String str) {
        this.principalSuffix = str;
    }

    public void setSearchBase(String str) {
        this.searchBase = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setSystemUsername(String str) {
        this.systemUsername = str;
    }

    public void setSystemPassword(String str) {
        this.systemPassword = str;
    }

    public void setLdapContextFactory(LdapContextFactory ldapContextFactory) {
        this.ldapContextFactory = ldapContextFactory;
    }

    public void setSearchFilter(String str) {
        this.searchFilter = str;
    }

    @Override // org.apache.shiro.realm.AuthorizingRealm, org.apache.shiro.realm.AuthenticatingRealm
    protected void onInit() {
        super.onInit();
        ensureContextFactory();
    }

    private LdapContextFactory ensureContextFactory() {
        if (this.ldapContextFactory == null) {
            Logger logger = log;
            if (logger.isDebugEnabled()) {
                logger.debug("No LdapContextFactory specified - creating a default instance.");
            }
            DefaultLdapContextFactory defaultLdapContextFactory = new DefaultLdapContextFactory();
            defaultLdapContextFactory.setPrincipalSuffix(this.principalSuffix);
            defaultLdapContextFactory.setSearchBase(this.searchBase);
            defaultLdapContextFactory.setUrl(this.url);
            defaultLdapContextFactory.setSystemUsername(this.systemUsername);
            defaultLdapContextFactory.setSystemPassword(this.systemPassword);
            this.ldapContextFactory = defaultLdapContextFactory;
        }
        return this.ldapContextFactory;
    }

    @Override // org.apache.shiro.realm.AuthenticatingRealm
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        try {
            return queryForAuthenticationInfo(authenticationToken, ensureContextFactory());
        } catch (javax.naming.AuthenticationException e) {
            throw new AuthenticationException("LDAP authentication failed.", e);
        } catch (NamingException e2) {
            throw new AuthenticationException("LDAP naming error while attempting to authenticate user.", e2);
        }
    }

    @Override // org.apache.shiro.realm.AuthorizingRealm
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        try {
            return queryForAuthorizationInfo(principalCollection, ensureContextFactory());
        } catch (NamingException e) {
            throw new AuthorizationException("LDAP naming error while attempting to retrieve authorization for user [" + principalCollection + "].", e);
        }
    }
}
