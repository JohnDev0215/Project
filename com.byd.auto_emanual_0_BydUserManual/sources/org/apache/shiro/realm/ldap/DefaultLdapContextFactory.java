package org.apache.shiro.realm.ldap;

import java.util.Hashtable;
import java.util.Map;
import javax.naming.AuthenticationException;
import javax.naming.NamingException;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import org.apache.shiro.config.Ini;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class DefaultLdapContextFactory implements LdapContextFactory {
    private static final String SIMPLE_AUTHENTICATION_MECHANISM_NAME = "simple";
    protected static final String SUN_CONNECTION_POOLING_PROPERTY = "com.sun.jndi.ldap.connect.pool";
    private static final Logger log = LoggerFactory.getLogger(DefaultLdapContextFactory.class);
    private Map<String, String> additionalEnvironment;
    protected String authentication = SIMPLE_AUTHENTICATION_MECHANISM_NAME;
    protected String principalSuffix = null;
    protected String searchBase = null;
    protected String contextFactoryClassName = "com.sun.jndi.ldap.LdapCtxFactory";
    protected String url = null;
    protected String referral = "follow";
    protected String systemUsername = null;
    protected String systemPassword = null;
    private boolean usePooling = true;

    public void setAuthentication(String str) {
        this.authentication = str;
    }

    public void setPrincipalSuffix(String str) {
        this.principalSuffix = str;
    }

    @Deprecated
    public void setSearchBase(String str) {
        this.searchBase = str;
    }

    public void setContextFactoryClassName(String str) {
        this.contextFactoryClassName = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setReferral(String str) {
        this.referral = str;
    }

    public void setSystemUsername(String str) {
        this.systemUsername = str;
    }

    public void setSystemPassword(String str) {
        this.systemPassword = str;
    }

    public void setUsePooling(boolean z) {
        this.usePooling = z;
    }

    public void setAdditionalEnvironment(Map<String, String> map) {
        this.additionalEnvironment = map;
    }

    @Override // org.apache.shiro.realm.ldap.LdapContextFactory
    public LdapContext getSystemLdapContext() throws NamingException {
        return getLdapContext(this.systemUsername, this.systemPassword);
    }

    @Override // org.apache.shiro.realm.ldap.LdapContextFactory
    @Deprecated
    public LdapContext getLdapContext(String str, String str2) throws NamingException {
        if (str != null && this.principalSuffix != null) {
            str = str + this.principalSuffix;
        }
        return getLdapContext((Object) str, (Object) str2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.naming.AuthenticationException */
    @Override // org.apache.shiro.realm.ldap.LdapContextFactory
    public LdapContext getLdapContext(Object obj, Object obj2) throws NamingException, AuthenticationException {
        if (this.url == null) {
            throw new IllegalStateException("An LDAP URL must be specified of the form ldap://<hostname>:<port>");
        }
        Hashtable<String, Object> hashtable = new Hashtable<>();
        hashtable.put("java.naming.security.authentication", this.authentication);
        if (obj != null) {
            hashtable.put("java.naming.security.principal", obj);
        }
        if (obj2 != null) {
            hashtable.put("java.naming.security.credentials", obj2);
        }
        hashtable.put("java.naming.factory.initial", this.contextFactoryClassName);
        hashtable.put("java.naming.provider.url", this.url);
        hashtable.put("java.naming.referral", this.referral);
        if (this.usePooling && obj != null && obj.equals(this.systemUsername)) {
            hashtable.put(SUN_CONNECTION_POOLING_PROPERTY, "true");
        }
        Map<String, String> map = this.additionalEnvironment;
        if (map != null) {
            hashtable.putAll(map);
        }
        Logger logger = log;
        if (logger.isDebugEnabled()) {
            logger.debug("Initializing LDAP context using URL [" + this.url + "] and username [" + this.systemUsername + "] with pooling [" + (this.usePooling ? "enabled" : "disabled") + Ini.SECTION_SUFFIX);
        }
        validateAuthenticationInfo(hashtable);
        return createLdapContext(hashtable);
    }

    protected LdapContext createLdapContext(Hashtable hashtable) throws NamingException {
        return new InitialLdapContext(hashtable, (Control[]) null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.naming.AuthenticationException */
    private void validateAuthenticationInfo(Hashtable<String, Object> hashtable) throws AuthenticationException {
        if (SIMPLE_AUTHENTICATION_MECHANISM_NAME.equals(hashtable.get("java.naming.security.authentication")) && hashtable.get("java.naming.security.principal") != null && StringUtils.hasText(String.valueOf(hashtable.get("java.naming.security.principal")))) {
            Object obj = hashtable.get("java.naming.security.credentials");
            if (obj == null || (((obj instanceof byte[]) && ((byte[]) obj).length <= 0) || (((obj instanceof char[]) && ((char[]) obj).length <= 0) || (String.class.isInstance(obj) && !StringUtils.hasText(String.valueOf(obj)))))) {
                throw new AuthenticationException("LDAP Simple authentication requires both a principal and credentials.");
            }
        }
    }
}
