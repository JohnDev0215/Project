package org.apache.shiro.authz.permission;

import org.apache.shiro.authz.Permission;

/* JADX INFO: loaded from: classes.dex */
public class WildcardPermissionResolver implements PermissionResolver {
    boolean caseSensitive;

    public WildcardPermissionResolver(boolean z) {
        this.caseSensitive = z;
    }

    public WildcardPermissionResolver() {
        this(false);
    }

    public void setCaseSensitive(boolean z) {
        this.caseSensitive = z;
    }

    public boolean isCaseSensitive() {
        return this.caseSensitive;
    }

    @Override // org.apache.shiro.authz.permission.PermissionResolver
    public Permission resolvePermission(String str) {
        return new WildcardPermission(str, this.caseSensitive);
    }
}
