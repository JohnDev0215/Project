package com.dee.app.contacts.interfaces.models.data.enums;

/* JADX INFO: loaded from: classes5.dex */
public enum PermissionRequestInfo {
    READ_CONTACTS(1000, "contacts", new String[]{"android.permission.READ_CONTACTS"}, "permissions.contacts");

    public final String feature;
    public final String metric;
    public final String name;
    public final String[] permissions;
    public final int requestCode;

    PermissionRequestInfo(int i, String str, String[] strArr, String str2) {
        this(i, str, strArr, str2, null);
    }

    PermissionRequestInfo(int i, String str, String[] strArr, String str2, String str3) {
        this.name = str;
        this.requestCode = i;
        this.permissions = strArr;
        this.metric = str2;
        this.feature = str3;
    }

    public static PermissionRequestInfo byRequestCode(int i) {
        for (PermissionRequestInfo permissionRequestInfo : values()) {
            if (i == permissionRequestInfo.requestCode) {
                return permissionRequestInfo;
            }
        }
        return null;
    }

    public static PermissionRequestInfo byName(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Invalid permission name. The permission name is null.");
        }
        String lowerCase = str.toLowerCase();
        for (PermissionRequestInfo permissionRequestInfo : values()) {
            if (lowerCase.equals(permissionRequestInfo.name)) {
                return permissionRequestInfo;
            }
        }
        throw new IllegalArgumentException("Invalid permission name. No RequestInfo found for: " + lowerCase);
    }
}
