package org.apache.shiro.authz.permission;

import org.apache.shiro.ShiroException;

/* JADX INFO: loaded from: classes.dex */
public class InvalidPermissionStringException extends ShiroException {
    private String permissionString;

    public InvalidPermissionStringException(String str, String str2) {
        super(str);
        this.permissionString = str2;
    }

    public String getPermissionString() {
        return this.permissionString;
    }
}
