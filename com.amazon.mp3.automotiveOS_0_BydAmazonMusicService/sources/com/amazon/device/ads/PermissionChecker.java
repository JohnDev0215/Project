package com.amazon.device.ads;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
class PermissionChecker {
    PermissionChecker() {
    }

    public boolean hasPermission(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public boolean hasInternetPermission(Context context) {
        return hasPermission(context, "android.permission.INTERNET");
    }
}
