package org.apache.shiro.authz;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class SimpleAuthorizationInfo implements AuthorizationInfo {
    protected Set<Permission> objectPermissions;
    protected Set<String> roles;
    protected Set<String> stringPermissions;

    public SimpleAuthorizationInfo() {
    }

    public SimpleAuthorizationInfo(Set<String> set) {
        this.roles = set;
    }

    @Override // org.apache.shiro.authz.AuthorizationInfo
    public Set<String> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<String> set) {
        this.roles = set;
    }

    public void addRole(String str) {
        if (this.roles == null) {
            this.roles = new HashSet();
        }
        this.roles.add(str);
    }

    public void addRoles(Collection<String> collection) {
        if (this.roles == null) {
            this.roles = new HashSet();
        }
        this.roles.addAll(collection);
    }

    @Override // org.apache.shiro.authz.AuthorizationInfo
    public Set<String> getStringPermissions() {
        return this.stringPermissions;
    }

    public void setStringPermissions(Set<String> set) {
        this.stringPermissions = set;
    }

    public void addStringPermission(String str) {
        if (this.stringPermissions == null) {
            this.stringPermissions = new HashSet();
        }
        this.stringPermissions.add(str);
    }

    public void addStringPermissions(Collection<String> collection) {
        if (this.stringPermissions == null) {
            this.stringPermissions = new HashSet();
        }
        this.stringPermissions.addAll(collection);
    }

    @Override // org.apache.shiro.authz.AuthorizationInfo
    public Set<Permission> getObjectPermissions() {
        return this.objectPermissions;
    }

    public void setObjectPermissions(Set<Permission> set) {
        this.objectPermissions = set;
    }

    public void addObjectPermission(Permission permission) {
        if (this.objectPermissions == null) {
            this.objectPermissions = new HashSet();
        }
        this.objectPermissions.add(permission);
    }

    public void addObjectPermissions(Collection<Permission> collection) {
        if (this.objectPermissions == null) {
            this.objectPermissions = new HashSet();
        }
        this.objectPermissions.addAll(collection);
    }
}
