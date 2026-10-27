package org.apache.shiro.realm.activedirectory;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.config.Ini;
import org.apache.shiro.realm.ldap.AbstractLdapRealm;
import org.apache.shiro.realm.ldap.LdapContextFactory;
import org.apache.shiro.realm.ldap.LdapUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class ActiveDirectoryRealm extends AbstractLdapRealm {
    private static final String ROLE_NAMES_DELIMETER = ",";
    private static final Logger log = LoggerFactory.getLogger(ActiveDirectoryRealm.class);
    private Map<String, String> groupRolesMap;

    public void setGroupRolesMap(Map<String, String> map) {
        this.groupRolesMap = map;
    }

    @Override // org.apache.shiro.realm.ldap.AbstractLdapRealm
    protected AuthenticationInfo queryForAuthenticationInfo(AuthenticationToken authenticationToken, LdapContextFactory ldapContextFactory) throws NamingException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        try {
            LdapUtils.closeContext(ldapContextFactory.getLdapContext(usernamePasswordToken.getUsername(), String.valueOf(usernamePasswordToken.getPassword())));
            return buildAuthenticationInfo(usernamePasswordToken.getUsername(), usernamePasswordToken.getPassword());
        } catch (Throwable th) {
            LdapUtils.closeContext(null);
            throw th;
        }
    }

    protected AuthenticationInfo buildAuthenticationInfo(String str, char[] cArr) {
        return new SimpleAuthenticationInfo(str, cArr, getName());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.naming.NamingException */
    @Override // org.apache.shiro.realm.ldap.AbstractLdapRealm
    protected AuthorizationInfo queryForAuthorizationInfo(PrincipalCollection principalCollection, LdapContextFactory ldapContextFactory) throws NamingException {
        String str = (String) getAvailablePrincipal(principalCollection);
        LdapContext systemLdapContext = ldapContextFactory.getSystemLdapContext();
        try {
            Set<String> roleNamesForUser = getRoleNamesForUser(str, systemLdapContext);
            LdapUtils.closeContext(systemLdapContext);
            return buildAuthorizationInfo(roleNamesForUser);
        } catch (Throwable th) {
            LdapUtils.closeContext(systemLdapContext);
            throw th;
        }
    }

    protected AuthorizationInfo buildAuthorizationInfo(Set<String> set) {
        return new SimpleAuthorizationInfo(set);
    }

    protected Set<String> getRoleNamesForUser(String str, LdapContext ldapContext) throws Throwable {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(2);
        NamingEnumeration namingEnumerationSearch = ldapContext.search(this.searchBase, this.searchFilter, new Object[]{(this.principalSuffix == null || str.toLowerCase(Locale.ROOT).endsWith(this.principalSuffix.toLowerCase(Locale.ROOT))) ? str : str + this.principalSuffix}, searchControls);
        while (namingEnumerationSearch.hasMoreElements()) {
            SearchResult searchResult = (SearchResult) namingEnumerationSearch.next();
            Logger logger = log;
            if (logger.isDebugEnabled()) {
                logger.debug("Retrieving group names for user [" + searchResult.getName() + Ini.SECTION_SUFFIX);
            }
            Attributes attributes = searchResult.getAttributes();
            if (attributes != null) {
                NamingEnumeration all = attributes.getAll();
                while (all.hasMore()) {
                    Attribute attribute = (Attribute) all.next();
                    if (attribute.getID().equals("memberOf")) {
                        Collection<String> allAttributeValues = LdapUtils.getAllAttributeValues(attribute);
                        Logger logger2 = log;
                        if (logger2.isDebugEnabled()) {
                            logger2.debug("Groups found for user [" + str + "]: " + allAttributeValues);
                        }
                        linkedHashSet.addAll(getRoleNamesForGroups(allAttributeValues));
                    }
                }
            }
        }
        return linkedHashSet;
    }

    protected Collection<String> getRoleNamesForGroups(Collection<String> collection) {
        HashSet hashSet = new HashSet(collection.size());
        if (this.groupRolesMap != null) {
            for (String str : collection) {
                String str2 = this.groupRolesMap.get(str);
                if (str2 != null) {
                    for (String str3 : str2.split(",")) {
                        Logger logger = log;
                        if (logger.isDebugEnabled()) {
                            logger.debug("User is member of group [" + str + "] so adding role [" + str3 + Ini.SECTION_SUFFIX);
                        }
                        hashSet.add(str3);
                    }
                }
            }
        }
        return hashSet;
    }
}
