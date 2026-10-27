package org.apache.shiro.authz.permission;

import java.io.Serializable;
import org.apache.shiro.authz.Permission;

/* JADX INFO: loaded from: classes.dex */
public class AllPermission implements Permission, Serializable {
    @Override // org.apache.shiro.authz.Permission
    public boolean implies(Permission permission) {
        return true;
    }
}
