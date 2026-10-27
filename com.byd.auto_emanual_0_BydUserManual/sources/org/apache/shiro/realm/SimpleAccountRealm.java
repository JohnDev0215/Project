package org.apache.shiro.realm;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleRole;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;

/* JADX INFO: loaded from: classes.dex */
public class SimpleAccountRealm extends AuthorizingRealm {
    protected final ReadWriteLock ROLES_LOCK;
    protected final ReadWriteLock USERS_LOCK;
    protected final Map<String, SimpleRole> roles;
    protected final Map<String, SimpleAccount> users;

    public SimpleAccountRealm() {
        this.users = new LinkedHashMap();
        this.roles = new LinkedHashMap();
        this.USERS_LOCK = new ReentrantReadWriteLock();
        this.ROLES_LOCK = new ReentrantReadWriteLock();
        setCachingEnabled(false);
    }

    public SimpleAccountRealm(String str) {
        this();
        setName(str);
    }

    protected SimpleAccount getUser(String str) {
        this.USERS_LOCK.readLock().lock();
        try {
            return this.users.get(str);
        } finally {
            this.USERS_LOCK.readLock().unlock();
        }
    }

    public boolean accountExists(String str) {
        return getUser(str) != null;
    }

    public void addAccount(String str, String str2) {
        addAccount(str, str2, (String[]) null);
    }

    public void addAccount(String str, String str2, String... strArr) {
        add(new SimpleAccount(str, str2, getName(), (Set<String>) CollectionUtils.asSet(strArr), (Set<Permission>) null));
    }

    protected String getUsername(SimpleAccount simpleAccount) {
        return getUsername(simpleAccount.getPrincipals());
    }

    protected String getUsername(PrincipalCollection principalCollection) {
        return getAvailablePrincipal(principalCollection).toString();
    }

    protected void add(SimpleAccount simpleAccount) {
        String username = getUsername(simpleAccount);
        this.USERS_LOCK.writeLock().lock();
        try {
            this.users.put(username, simpleAccount);
        } finally {
            this.USERS_LOCK.writeLock().unlock();
        }
    }

    protected SimpleRole getRole(String str) {
        this.ROLES_LOCK.readLock().lock();
        try {
            return this.roles.get(str);
        } finally {
            this.ROLES_LOCK.readLock().unlock();
        }
    }

    public boolean roleExists(String str) {
        return getRole(str) != null;
    }

    public void addRole(String str) {
        add(new SimpleRole(str));
    }

    protected void add(SimpleRole simpleRole) {
        this.ROLES_LOCK.writeLock().lock();
        try {
            this.roles.put(simpleRole.getName(), simpleRole);
        } finally {
            this.ROLES_LOCK.writeLock().unlock();
        }
    }

    protected static Set<String> toSet(String str, String str2) {
        if (str == null || str.trim().equals("")) {
            return null;
        }
        HashSet hashSet = new HashSet();
        for (String str3 : str.split(str2)) {
            String strTrim = str3.trim();
            if (strTrim.length() > 0) {
                hashSet.add(strTrim);
            }
        }
        return hashSet;
    }

    @Override // org.apache.shiro.realm.AuthenticatingRealm
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        SimpleAccount user = getUser(((UsernamePasswordToken) authenticationToken).getUsername());
        if (user != null) {
            if (user.isLocked()) {
                throw new LockedAccountException("Account [" + user + "] is locked.");
            }
            if (user.isCredentialsExpired()) {
                throw new ExpiredCredentialsException("The credentials for account [" + user + "] are expired");
            }
        }
        return user;
    }

    @Override // org.apache.shiro.realm.AuthorizingRealm
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = getUsername(principalCollection);
        this.USERS_LOCK.readLock().lock();
        try {
            return this.users.get(username);
        } finally {
            this.USERS_LOCK.readLock().unlock();
        }
    }
}
