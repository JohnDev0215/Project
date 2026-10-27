package com.ecarx.xui.adaptapi.pms;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class Permission {
    public static IPermission create(Context context) {
        return new PermissionImp(context);
    }
}
