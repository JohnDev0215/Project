package org.apache.shiro.realm.ldap;

import javax.naming.NamingException;
import javax.naming.ldap.LdapContext;

/* JADX INFO: loaded from: classes.dex */
public interface LdapContextFactory {
    LdapContext getLdapContext(Object obj, Object obj2) throws NamingException;

    @Deprecated
    LdapContext getLdapContext(String str, String str2) throws NamingException;

    LdapContext getSystemLdapContext() throws NamingException;
}
