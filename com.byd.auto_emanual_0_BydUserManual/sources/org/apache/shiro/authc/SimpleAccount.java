package org.apache.shiro.authc;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

/* JADX INFO: loaded from: classes.dex */
public class SimpleAccount implements Account, MergableAuthenticationInfo, SaltedAuthenticationInfo, Serializable {
    private SimpleAuthenticationInfo authcInfo;
    private SimpleAuthorizationInfo authzInfo;
    private boolean credentialsExpired;
    private boolean locked;

    public SimpleAccount() {
    }

    public SimpleAccount(Object obj, Object obj2, String str) {
        this(obj instanceof PrincipalCollection ? (PrincipalCollection) obj : new SimplePrincipalCollection(obj, str), obj2);
    }

    public SimpleAccount(Object obj, Object obj2, ByteSource byteSource, String str) {
        this(obj instanceof PrincipalCollection ? (PrincipalCollection) obj : new SimplePrincipalCollection(obj, str), obj2, byteSource);
    }

    public SimpleAccount(Collection collection, Object obj, String str) {
        this(new SimplePrincipalCollection(collection, str), obj);
    }

    public SimpleAccount(PrincipalCollection principalCollection, Object obj) {
        this.authcInfo = new SimpleAuthenticationInfo(principalCollection, obj);
        this.authzInfo = new SimpleAuthorizationInfo();
    }

    public SimpleAccount(PrincipalCollection principalCollection, Object obj, ByteSource byteSource) {
        this.authcInfo = new SimpleAuthenticationInfo(principalCollection, obj, byteSource);
        this.authzInfo = new SimpleAuthorizationInfo();
    }

    public SimpleAccount(PrincipalCollection principalCollection, Object obj, Set<String> set) {
        this.authcInfo = new SimpleAuthenticationInfo(principalCollection, obj);
        this.authzInfo = new SimpleAuthorizationInfo(set);
    }

    public SimpleAccount(Object obj, Object obj2, String str, Set<String> set, Set<Permission> set2) {
        this.authcInfo = new SimpleAuthenticationInfo(new SimplePrincipalCollection(obj, str), obj2);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(set);
        this.authzInfo = simpleAuthorizationInfo;
        simpleAuthorizationInfo.setObjectPermissions(set2);
    }

    public SimpleAccount(Collection collection, Object obj, String str, Set<String> set, Set<Permission> set2) {
        this.authcInfo = new SimpleAuthenticationInfo(new SimplePrincipalCollection(collection, str), obj);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(set);
        this.authzInfo = simpleAuthorizationInfo;
        simpleAuthorizationInfo.setObjectPermissions(set2);
    }

    public SimpleAccount(PrincipalCollection principalCollection, Object obj, Set<String> set, Set<Permission> set2) {
        this.authcInfo = new SimpleAuthenticationInfo(principalCollection, obj);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(set);
        this.authzInfo = simpleAuthorizationInfo;
        simpleAuthorizationInfo.setObjectPermissions(set2);
    }

    @Override // org.apache.shiro.authc.AuthenticationInfo
    public PrincipalCollection getPrincipals() {
        return this.authcInfo.getPrincipals();
    }

    public void setPrincipals(PrincipalCollection principalCollection) {
        this.authcInfo.setPrincipals(principalCollection);
    }

    @Override // org.apache.shiro.authc.AuthenticationInfo
    public Object getCredentials() {
        return this.authcInfo.getCredentials();
    }

    public void setCredentials(Object obj) {
        this.authcInfo.setCredentials(obj);
    }

    @Override // org.apache.shiro.authc.SaltedAuthenticationInfo
    public ByteSource getCredentialsSalt() {
        return this.authcInfo.getCredentialsSalt();
    }

    public void setCredentialsSalt(ByteSource byteSource) {
        this.authcInfo.setCredentialsSalt(byteSource);
    }

    @Override // org.apache.shiro.authz.AuthorizationInfo
    public Collection<String> getRoles() {
        return this.authzInfo.getRoles();
    }

    public void setRoles(Set<String> set) {
        this.authzInfo.setRoles(set);
    }

    public void addRole(String str) {
        this.authzInfo.addRole(str);
    }

    public void addRole(Collection<String> collection) {
        this.authzInfo.addRoles(collection);
    }

    @Override // org.apache.shiro.authz.AuthorizationInfo
    public Collection<String> getStringPermissions() {
        return this.authzInfo.getStringPermissions();
    }

    public void setStringPermissions(Set<String> set) {
        this.authzInfo.setStringPermissions(set);
    }

    public void addStringPermission(String str) {
        this.authzInfo.addStringPermission(str);
    }

    public void addStringPermissions(Collection<String> collection) {
        this.authzInfo.addStringPermissions(collection);
    }

    @Override // org.apache.shiro.authz.AuthorizationInfo
    public Collection<Permission> getObjectPermissions() {
        return this.authzInfo.getObjectPermissions();
    }

    public void setObjectPermissions(Set<Permission> set) {
        this.authzInfo.setObjectPermissions(set);
    }

    public void addObjectPermission(Permission permission) {
        this.authzInfo.addObjectPermission(permission);
    }

    public void addObjectPermissions(Collection<Permission> collection) {
        this.authzInfo.addObjectPermissions(collection);
    }

    public boolean isLocked() {
        return this.locked;
    }

    public void setLocked(boolean z) {
        this.locked = z;
    }

    public boolean isCredentialsExpired() {
        return this.credentialsExpired;
    }

    public void setCredentialsExpired(boolean z) {
        this.credentialsExpired = z;
    }

    @Override // org.apache.shiro.authc.MergableAuthenticationInfo
    public void merge(AuthenticationInfo authenticationInfo) {
        this.authcInfo.merge(authenticationInfo);
        if (authenticationInfo instanceof SimpleAccount) {
            SimpleAccount simpleAccount = (SimpleAccount) authenticationInfo;
            if (simpleAccount.isLocked()) {
                setLocked(true);
            }
            if (simpleAccount.isCredentialsExpired()) {
                setCredentialsExpired(true);
            }
        }
    }

    public int hashCode() {
        if (getPrincipals() != null) {
            return getPrincipals().hashCode();
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SimpleAccount)) {
            return false;
        }
        SimpleAccount simpleAccount = (SimpleAccount) obj;
        return getPrincipals() != null ? getPrincipals().equals(simpleAccount.getPrincipals()) : simpleAccount.getPrincipals() == null;
    }

    public String toString() {
        return getPrincipals() != null ? getPrincipals().toString() : "empty";
    }
}
