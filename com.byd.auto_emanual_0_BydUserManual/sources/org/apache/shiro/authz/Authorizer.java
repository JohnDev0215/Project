package org.apache.shiro.authz;

import java.util.Collection;
import java.util.List;
import org.apache.shiro.subject.PrincipalCollection;

/* JADX INFO: loaded from: classes.dex */
public interface Authorizer {
    void checkPermission(PrincipalCollection principalCollection, String str) throws AuthorizationException;

    void checkPermission(PrincipalCollection principalCollection, Permission permission) throws AuthorizationException;

    void checkPermissions(PrincipalCollection principalCollection, Collection<Permission> collection) throws AuthorizationException;

    void checkPermissions(PrincipalCollection principalCollection, String... strArr) throws AuthorizationException;

    void checkRole(PrincipalCollection principalCollection, String str) throws AuthorizationException;

    void checkRoles(PrincipalCollection principalCollection, Collection<String> collection) throws AuthorizationException;

    void checkRoles(PrincipalCollection principalCollection, String... strArr) throws AuthorizationException;

    boolean hasAllRoles(PrincipalCollection principalCollection, Collection<String> collection);

    boolean hasRole(PrincipalCollection principalCollection, String str);

    boolean[] hasRoles(PrincipalCollection principalCollection, List<String> list);

    boolean isPermitted(PrincipalCollection principalCollection, String str);

    boolean isPermitted(PrincipalCollection principalCollection, Permission permission);

    boolean[] isPermitted(PrincipalCollection principalCollection, List<Permission> list);

    boolean[] isPermitted(PrincipalCollection principalCollection, String... strArr);

    boolean isPermittedAll(PrincipalCollection principalCollection, Collection<Permission> collection);

    boolean isPermittedAll(PrincipalCollection principalCollection, String... strArr);
}
