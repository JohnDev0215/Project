package org.apache.shiro.realm.ldap;

import java.util.Collection;
import java.util.HashSet;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.ldap.LdapContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public final class LdapUtils {
    private static final Logger log = LoggerFactory.getLogger(LdapUtils.class);

    public static void closeContext(LdapContext ldapContext) {
        if (ldapContext != null) {
            try {
                ldapContext.close();
            } catch (NamingException e) {
                log.error("Exception while closing LDAP context. ", e);
            }
        }
    }

    public static Collection<String> getAllAttributeValues(Attribute attribute) throws Throwable {
        NamingEnumeration all;
        HashSet hashSet = new HashSet();
        try {
            all = attribute.getAll();
            while (all.hasMore()) {
                try {
                    hashSet.add((String) all.next());
                } catch (Throwable th) {
                    th = th;
                    closeEnumeration(all);
                    throw th;
                }
            }
            closeEnumeration(all);
            return hashSet;
        } catch (Throwable th2) {
            th = th2;
            all = null;
        }
    }

    public static void closeEnumeration(NamingEnumeration namingEnumeration) {
        if (namingEnumeration != null) {
            try {
                namingEnumeration.close();
            } catch (NamingException e) {
                log.error("Exception while closing NamingEnumeration: ", e);
            }
        }
    }
}
