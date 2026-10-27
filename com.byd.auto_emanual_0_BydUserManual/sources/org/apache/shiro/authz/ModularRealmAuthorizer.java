package org.apache.shiro.authz;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.PermissionResolverAware;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.RolePermissionResolverAware;
import org.apache.shiro.config.Ini;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;

/* JADX INFO: loaded from: classes.dex */
public class ModularRealmAuthorizer implements Authorizer, PermissionResolverAware, RolePermissionResolverAware {
    protected PermissionResolver permissionResolver;
    protected Collection<Realm> realms;
    protected RolePermissionResolver rolePermissionResolver;

    public ModularRealmAuthorizer() {
    }

    public ModularRealmAuthorizer(Collection<Realm> collection) {
        setRealms(collection);
    }

    public Collection<Realm> getRealms() {
        return this.realms;
    }

    public void setRealms(Collection<Realm> collection) {
        this.realms = collection;
        applyPermissionResolverToRealms();
        applyRolePermissionResolverToRealms();
    }

    public PermissionResolver getPermissionResolver() {
        return this.permissionResolver;
    }

    @Override // org.apache.shiro.authz.permission.PermissionResolverAware
    public void setPermissionResolver(PermissionResolver permissionResolver) {
        this.permissionResolver = permissionResolver;
        applyPermissionResolverToRealms();
    }

    protected void applyPermissionResolverToRealms() {
        PermissionResolver permissionResolver = getPermissionResolver();
        Collection<Realm> realms = getRealms();
        if (permissionResolver == null || realms == null || realms.isEmpty()) {
            return;
        }
        for (Realm realm : realms) {
            if (realm instanceof PermissionResolverAware) {
                ((PermissionResolverAware) realm).setPermissionResolver(permissionResolver);
            }
        }
    }

    public RolePermissionResolver getRolePermissionResolver() {
        return this.rolePermissionResolver;
    }

    @Override // org.apache.shiro.authz.permission.RolePermissionResolverAware
    public void setRolePermissionResolver(RolePermissionResolver rolePermissionResolver) {
        this.rolePermissionResolver = rolePermissionResolver;
        applyRolePermissionResolverToRealms();
    }

    protected void applyRolePermissionResolverToRealms() {
        RolePermissionResolver rolePermissionResolver = getRolePermissionResolver();
        Collection<Realm> realms = getRealms();
        if (rolePermissionResolver == null || realms == null || realms.isEmpty()) {
            return;
        }
        for (Realm realm : realms) {
            if (realm instanceof RolePermissionResolverAware) {
                ((RolePermissionResolverAware) realm).setRolePermissionResolver(rolePermissionResolver);
            }
        }
    }

    protected void assertRealmsConfigured() throws IllegalStateException {
        Collection<Realm> realms = getRealms();
        if (realms == null || realms.isEmpty()) {
            throw new IllegalStateException("Configuration error:  No realms have been configured!  One or more realms must be present to execute an authorization operation.");
        }
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean isPermitted(PrincipalCollection principalCollection, String str) {
        assertRealmsConfigured();
        for (Realm realm : getRealms()) {
            if ((realm instanceof Authorizer) && ((Authorizer) realm).isPermitted(principalCollection, str)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean isPermitted(PrincipalCollection principalCollection, Permission permission) {
        assertRealmsConfigured();
        for (Realm realm : getRealms()) {
            if ((realm instanceof Authorizer) && ((Authorizer) realm).isPermitted(principalCollection, permission)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean[] isPermitted(PrincipalCollection principalCollection, String... strArr) {
        assertRealmsConfigured();
        if (strArr == null || strArr.length <= 0) {
            return new boolean[0];
        }
        boolean[] zArr = new boolean[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            zArr[i] = isPermitted(principalCollection, strArr[i]);
        }
        return zArr;
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean[] isPermitted(PrincipalCollection principalCollection, List<Permission> list) {
        assertRealmsConfigured();
        int i = 0;
        if (list == null || list.isEmpty()) {
            return new boolean[0];
        }
        boolean[] zArr = new boolean[list.size()];
        Iterator<Permission> it = list.iterator();
        while (it.hasNext()) {
            zArr[i] = isPermitted(principalCollection, it.next());
            i++;
        }
        return zArr;
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean isPermittedAll(PrincipalCollection principalCollection, String... strArr) {
        assertRealmsConfigured();
        if (strArr == null || strArr.length <= 0) {
            return true;
        }
        for (String str : strArr) {
            if (!isPermitted(principalCollection, str)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean isPermittedAll(PrincipalCollection principalCollection, Collection<Permission> collection) {
        assertRealmsConfigured();
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        Iterator<Permission> it = collection.iterator();
        while (it.hasNext()) {
            if (!isPermitted(principalCollection, it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkPermission(PrincipalCollection principalCollection, String str) throws AuthorizationException {
        assertRealmsConfigured();
        if (!isPermitted(principalCollection, str)) {
            throw new UnauthorizedException("Subject does not have permission [" + str + Ini.SECTION_SUFFIX);
        }
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkPermission(PrincipalCollection principalCollection, Permission permission) throws AuthorizationException {
        assertRealmsConfigured();
        if (!isPermitted(principalCollection, permission)) {
            throw new UnauthorizedException("Subject does not have permission [" + permission + Ini.SECTION_SUFFIX);
        }
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkPermissions(PrincipalCollection principalCollection, String... strArr) throws AuthorizationException {
        assertRealmsConfigured();
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        for (String str : strArr) {
            checkPermission(principalCollection, str);
        }
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkPermissions(PrincipalCollection principalCollection, Collection<Permission> collection) throws AuthorizationException {
        assertRealmsConfigured();
        if (collection != null) {
            Iterator<Permission> it = collection.iterator();
            while (it.hasNext()) {
                checkPermission(principalCollection, it.next());
            }
        }
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean hasRole(PrincipalCollection principalCollection, String str) {
        assertRealmsConfigured();
        for (Realm realm : getRealms()) {
            if ((realm instanceof Authorizer) && ((Authorizer) realm).hasRole(principalCollection, str)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean[] hasRoles(PrincipalCollection principalCollection, List<String> list) {
        assertRealmsConfigured();
        int i = 0;
        if (list == null || list.isEmpty()) {
            return new boolean[0];
        }
        boolean[] zArr = new boolean[list.size()];
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            zArr[i] = hasRole(principalCollection, it.next());
            i++;
        }
        return zArr;
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean hasAllRoles(PrincipalCollection principalCollection, Collection<String> collection) {
        assertRealmsConfigured();
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            if (!hasRole(principalCollection, it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkRole(PrincipalCollection principalCollection, String str) throws AuthorizationException {
        assertRealmsConfigured();
        if (!hasRole(principalCollection, str)) {
            throw new UnauthorizedException("Subject does not have role [" + str + Ini.SECTION_SUFFIX);
        }
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkRoles(PrincipalCollection principalCollection, Collection<String> collection) throws AuthorizationException {
        if (collection == null || collection.isEmpty()) {
            return;
        }
        checkRoles(principalCollection, (String[]) collection.toArray(new String[collection.size()]));
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkRoles(PrincipalCollection principalCollection, String... strArr) throws AuthorizationException {
        assertRealmsConfigured();
        if (strArr != null) {
            for (String str : strArr) {
                checkRole(principalCollection, str);
            }
        }
    }
}
