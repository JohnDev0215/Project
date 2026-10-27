package org.apache.shiro.mgt;

import java.util.Collection;
import java.util.List;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.LifecycleUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class AuthorizingSecurityManager extends AuthenticatingSecurityManager {
    private Authorizer authorizer = new ModularRealmAuthorizer();

    public Authorizer getAuthorizer() {
        return this.authorizer;
    }

    public void setAuthorizer(Authorizer authorizer) {
        if (authorizer == null) {
            throw new IllegalArgumentException("Authorizer argument cannot be null.");
        }
        this.authorizer = authorizer;
    }

    @Override // org.apache.shiro.mgt.AuthenticatingSecurityManager, org.apache.shiro.mgt.RealmSecurityManager
    protected void afterRealmsSet() {
        super.afterRealmsSet();
        Authorizer authorizer = this.authorizer;
        if (authorizer instanceof ModularRealmAuthorizer) {
            ((ModularRealmAuthorizer) authorizer).setRealms(getRealms());
        }
    }

    @Override // org.apache.shiro.mgt.AuthenticatingSecurityManager, org.apache.shiro.mgt.RealmSecurityManager, org.apache.shiro.mgt.CachingSecurityManager, org.apache.shiro.util.Destroyable
    public void destroy() {
        LifecycleUtils.destroy(getAuthorizer());
        this.authorizer = null;
        super.destroy();
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean isPermitted(PrincipalCollection principalCollection, String str) {
        return this.authorizer.isPermitted(principalCollection, str);
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean isPermitted(PrincipalCollection principalCollection, Permission permission) {
        return this.authorizer.isPermitted(principalCollection, permission);
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean[] isPermitted(PrincipalCollection principalCollection, String... strArr) {
        return this.authorizer.isPermitted(principalCollection, strArr);
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean[] isPermitted(PrincipalCollection principalCollection, List<Permission> list) {
        return this.authorizer.isPermitted(principalCollection, list);
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean isPermittedAll(PrincipalCollection principalCollection, String... strArr) {
        return this.authorizer.isPermittedAll(principalCollection, strArr);
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean isPermittedAll(PrincipalCollection principalCollection, Collection<Permission> collection) {
        return this.authorizer.isPermittedAll(principalCollection, collection);
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkPermission(PrincipalCollection principalCollection, String str) throws AuthorizationException {
        this.authorizer.checkPermission(principalCollection, str);
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkPermission(PrincipalCollection principalCollection, Permission permission) throws AuthorizationException {
        this.authorizer.checkPermission(principalCollection, permission);
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkPermissions(PrincipalCollection principalCollection, String... strArr) throws AuthorizationException {
        this.authorizer.checkPermissions(principalCollection, strArr);
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkPermissions(PrincipalCollection principalCollection, Collection<Permission> collection) throws AuthorizationException {
        this.authorizer.checkPermissions(principalCollection, collection);
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean hasRole(PrincipalCollection principalCollection, String str) {
        return this.authorizer.hasRole(principalCollection, str);
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean[] hasRoles(PrincipalCollection principalCollection, List<String> list) {
        return this.authorizer.hasRoles(principalCollection, list);
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean hasAllRoles(PrincipalCollection principalCollection, Collection<String> collection) {
        return this.authorizer.hasAllRoles(principalCollection, collection);
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkRole(PrincipalCollection principalCollection, String str) throws AuthorizationException {
        this.authorizer.checkRole(principalCollection, str);
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkRoles(PrincipalCollection principalCollection, Collection<String> collection) throws AuthorizationException {
        this.authorizer.checkRoles(principalCollection, collection);
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkRoles(PrincipalCollection principalCollection, String... strArr) throws AuthorizationException {
        this.authorizer.checkRoles(principalCollection, strArr);
    }
}
