package org.apache.shiro.realm.ldap;

import javax.naming.AuthenticationNotSupportedException;
import javax.naming.NamingException;
import javax.naming.ldap.LdapContext;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.ldap.UnsupportedAuthenticationMechanismException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class DefaultLdapRealm extends AuthorizingRealm {
    private static final String USERDN_SUBSTITUTION_TOKEN = "{0}";
    private static final Logger log = LoggerFactory.getLogger(DefaultLdapRealm.class);
    private LdapContextFactory contextFactory;
    private String userDnPrefix;
    private String userDnSuffix;

    protected AuthorizationInfo queryForAuthorizationInfo(PrincipalCollection principalCollection, LdapContextFactory ldapContextFactory) throws NamingException {
        return null;
    }

    public DefaultLdapRealm() {
        setCredentialsMatcher(new AllowAllCredentialsMatcher());
        setAuthenticationTokenClass(AuthenticationToken.class);
        this.contextFactory = new JndiLdapContextFactory();
    }

    protected String getUserDnPrefix() {
        return this.userDnPrefix;
    }

    protected String getUserDnSuffix() {
        return this.userDnSuffix;
    }

    public void setUserDnTemplate(String str) throws IllegalArgumentException {
        if (!StringUtils.hasText(str)) {
            throw new IllegalArgumentException("User DN template cannot be null or empty.");
        }
        int iIndexOf = str.indexOf(USERDN_SUBSTITUTION_TOKEN);
        if (iIndexOf < 0) {
            throw new IllegalArgumentException("User DN template must contain the '{0}' replacement token to understand where to insert the runtime authentication principal.");
        }
        String strSubstring = str.substring(0, iIndexOf);
        String strSubstring2 = str.substring(strSubstring.length() + 3);
        Logger logger = log;
        if (logger.isDebugEnabled()) {
            logger.debug("Determined user DN prefix [{}] and suffix [{}]", strSubstring, strSubstring2);
        }
        this.userDnPrefix = strSubstring;
        this.userDnSuffix = strSubstring2;
    }

    public String getUserDnTemplate() {
        return getUserDn(USERDN_SUBSTITUTION_TOKEN);
    }

    protected String getUserDn(String str) throws IllegalStateException, IllegalArgumentException {
        if (!StringUtils.hasText(str)) {
            throw new IllegalArgumentException("User principal cannot be null or empty for User DN construction.");
        }
        String userDnPrefix = getUserDnPrefix();
        String userDnSuffix = getUserDnSuffix();
        if (userDnPrefix == null && userDnSuffix == null) {
            log.debug("userDnTemplate property has not been configured, indicating the submitted AuthenticationToken's principal is the same as the User DN.  Returning the method argument as is.");
            return str;
        }
        int length = userDnPrefix != null ? userDnPrefix.length() : 0;
        int length2 = userDnSuffix != null ? userDnSuffix.length() : 0;
        StringBuilder sb = new StringBuilder(str.length() + length + length2);
        if (length > 0) {
            sb.append(userDnPrefix);
        }
        sb.append(str);
        if (length2 > 0) {
            sb.append(userDnSuffix);
        }
        return sb.toString();
    }

    public void setContextFactory(LdapContextFactory ldapContextFactory) {
        this.contextFactory = ldapContextFactory;
    }

    public LdapContextFactory getContextFactory() {
        return this.contextFactory;
    }

    @Override // org.apache.shiro.realm.AuthenticatingRealm
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        try {
            return queryForAuthenticationInfo(authenticationToken, getContextFactory());
        } catch (NamingException e) {
            throw new AuthenticationException("LDAP naming error while attempting to authenticate user.", e);
        } catch (AuthenticationNotSupportedException e2) {
            throw new UnsupportedAuthenticationMechanismException("Unsupported configured authentication mechanism", e2);
        } catch (javax.naming.AuthenticationException e3) {
            throw new AuthenticationException("LDAP authentication failed.", e3);
        }
    }

    @Override // org.apache.shiro.realm.AuthorizingRealm
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        try {
            return queryForAuthorizationInfo(principalCollection, getContextFactory());
        } catch (NamingException e) {
            throw new AuthorizationException("LDAP naming error while attempting to retrieve authorization for user [" + principalCollection + "].", e);
        }
    }

    protected Object getLdapPrincipal(AuthenticationToken authenticationToken) {
        Object principal = authenticationToken.getPrincipal();
        return principal instanceof String ? getUserDn((String) principal) : principal;
    }

    protected AuthenticationInfo queryForAuthenticationInfo(AuthenticationToken authenticationToken, LdapContextFactory ldapContextFactory) throws Throwable {
        LdapContext ldapContext;
        Object principal = authenticationToken.getPrincipal();
        Object credentials = authenticationToken.getCredentials();
        log.debug("Authenticating user '{}' through LDAP", principal);
        Object ldapPrincipal = getLdapPrincipal(authenticationToken);
        try {
            ldapContext = ldapContextFactory.getLdapContext(ldapPrincipal, credentials);
            try {
                AuthenticationInfo authenticationInfoCreateAuthenticationInfo = createAuthenticationInfo(authenticationToken, ldapPrincipal, credentials, ldapContext);
                LdapUtils.closeContext(ldapContext);
                return authenticationInfoCreateAuthenticationInfo;
            } catch (Throwable th) {
                th = th;
                LdapUtils.closeContext(ldapContext);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            ldapContext = null;
        }
    }

    protected AuthenticationInfo createAuthenticationInfo(AuthenticationToken authenticationToken, Object obj, Object obj2, LdapContext ldapContext) throws NamingException {
        return new SimpleAuthenticationInfo(authenticationToken.getPrincipal(), authenticationToken.getCredentials(), getName());
    }
}
