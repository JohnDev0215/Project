package org.apache.shiro.realm.ldap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.naming.AuthenticationException;
import javax.naming.NamingException;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class JndiLdapContextFactory implements LdapContextFactory {
    protected static final String DEFAULT_CONTEXT_FACTORY_CLASS_NAME = "com.sun.jndi.ldap.LdapCtxFactory";
    protected static final String DEFAULT_REFERRAL = "follow";
    protected static final String SIMPLE_AUTHENTICATION_MECHANISM_NAME = "simple";
    protected static final String SUN_CONNECTION_POOLING_PROPERTY = "com.sun.jndi.ldap.connect.pool";
    private static final Logger log = LoggerFactory.getLogger(JndiLdapContextFactory.class);
    private Map<String, Object> environment = new HashMap();
    private boolean poolingEnabled;
    private String systemPassword;
    private String systemUsername;

    public JndiLdapContextFactory() {
        setContextFactoryClassName(DEFAULT_CONTEXT_FACTORY_CLASS_NAME);
        setReferral(DEFAULT_REFERRAL);
        this.poolingEnabled = true;
    }

    public void setAuthenticationMechanism(String str) {
        setEnvironmentProperty("java.naming.security.authentication", str);
    }

    public String getAuthenticationMechanism() {
        return (String) getEnvironmentProperty("java.naming.security.authentication");
    }

    public void setContextFactoryClassName(String str) {
        setEnvironmentProperty("java.naming.factory.initial", str);
    }

    public String getContextFactoryClassName() {
        return (String) getEnvironmentProperty("java.naming.factory.initial");
    }

    public Map getEnvironment() {
        return this.environment;
    }

    public void setEnvironment(Map map) {
        this.environment = map;
    }

    private Object getEnvironmentProperty(String str) {
        return this.environment.get(str);
    }

    private void setEnvironmentProperty(String str, String str2) {
        if (StringUtils.hasText(str2)) {
            this.environment.put(str, str2);
        } else {
            this.environment.remove(str);
        }
    }

    public boolean isPoolingEnabled() {
        return this.poolingEnabled;
    }

    public void setPoolingEnabled(boolean z) {
        this.poolingEnabled = z;
    }

    public void setReferral(String str) {
        setEnvironmentProperty("java.naming.referral", str);
    }

    public String getReferral() {
        return (String) getEnvironmentProperty("java.naming.referral");
    }

    public void setUrl(String str) {
        setEnvironmentProperty("java.naming.provider.url", str);
    }

    public String getUrl() {
        return (String) getEnvironmentProperty("java.naming.provider.url");
    }

    public void setSystemPassword(String str) {
        this.systemPassword = str;
    }

    public String getSystemPassword() {
        return this.systemPassword;
    }

    public void setSystemUsername(String str) {
        this.systemUsername = str;
    }

    public String getSystemUsername() {
        return this.systemUsername;
    }

    @Override // org.apache.shiro.realm.ldap.LdapContextFactory
    public LdapContext getSystemLdapContext() throws NamingException {
        return getLdapContext((Object) getSystemUsername(), (Object) getSystemPassword());
    }

    @Override // org.apache.shiro.realm.ldap.LdapContextFactory
    @Deprecated
    public LdapContext getLdapContext(String str, String str2) throws NamingException {
        return getLdapContext((Object) str, (Object) str2);
    }

    protected boolean isPoolingConnections(Object obj) {
        return isPoolingEnabled() && obj != null && obj.equals(getSystemUsername());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.naming.AuthenticationException */
    @Override // org.apache.shiro.realm.ldap.LdapContextFactory
    public LdapContext getLdapContext(Object obj, Object obj2) throws IllegalStateException, NamingException, AuthenticationException {
        String url = getUrl();
        if (url == null) {
            throw new IllegalStateException("An LDAP URL must be specified of the form ldap://<hostname>:<port>");
        }
        Hashtable<String, Object> hashtable = new Hashtable<>(this.environment);
        if (getAuthenticationMechanism() == null && (obj != null || obj2 != null)) {
            hashtable.put("java.naming.security.authentication", SIMPLE_AUTHENTICATION_MECHANISM_NAME);
        }
        if (obj != null) {
            hashtable.put("java.naming.security.principal", obj);
        }
        if (obj2 != null) {
            hashtable.put("java.naming.security.credentials", obj2);
        }
        boolean zIsPoolingConnections = isPoolingConnections(obj);
        if (zIsPoolingConnections) {
            hashtable.put(SUN_CONNECTION_POOLING_PROPERTY, "true");
        }
        Logger logger = log;
        if (logger.isDebugEnabled()) {
            Object[] objArr = new Object[3];
            objArr[0] = url;
            objArr[1] = obj;
            objArr[2] = zIsPoolingConnections ? "enabled" : "disabled";
            logger.debug("Initializing LDAP context using URL [{}] and principal [{}] with pooling {}", objArr);
        }
        validateAuthenticationInfo(hashtable);
        return createLdapContext(hashtable);
    }

    protected LdapContext createLdapContext(Hashtable hashtable) throws NamingException {
        return new InitialLdapContext(hashtable, (Control[]) null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.naming.AuthenticationException */
    protected void validateAuthenticationInfo(Hashtable<String, Object> hashtable) throws AuthenticationException {
        if (SIMPLE_AUTHENTICATION_MECHANISM_NAME.equals(hashtable.get("java.naming.security.authentication")) && hashtable.get("java.naming.security.principal") != null && StringUtils.hasText(String.valueOf(hashtable.get("java.naming.security.principal")))) {
            Object obj = hashtable.get("java.naming.security.credentials");
            if (obj == null || (((obj instanceof byte[]) && ((byte[]) obj).length <= 0) || (((obj instanceof char[]) && ((char[]) obj).length <= 0) || (String.class.isInstance(obj) && !StringUtils.hasText(String.valueOf(obj)))))) {
                throw new AuthenticationException("LDAP Simple authentication requires both a principal and credentials.");
            }
        }
    }
}
