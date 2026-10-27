package org.apache.shiro.authz.permission;

import java.util.Collection;
import org.apache.shiro.authz.Permission;

/* JADX INFO: loaded from: classes.dex */
public interface RolePermissionResolver {
    Collection<Permission> resolvePermissionsInRole(String str);
}
