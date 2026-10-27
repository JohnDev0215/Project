package com.android.permissioncontroller.role.model;

import android.content.Context;
import android.util.ArrayMap;

/* JADX INFO: loaded from: classes8.dex */
public class Roles {
    private static final Object sLock = new Object();
    private static ArrayMap<String, Role> sRoles;

    private Roles() {
    }

    public static ArrayMap<String, Role> get(Context context) {
        ArrayMap<String, Role> arrayMap;
        synchronized (sLock) {
            if (sRoles == null) {
                sRoles = new RoleParser(context).parse();
            }
            arrayMap = sRoles;
        }
        return arrayMap;
    }
}
