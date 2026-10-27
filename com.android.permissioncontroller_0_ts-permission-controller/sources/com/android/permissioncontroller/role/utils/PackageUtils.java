package com.android.permissioncontroller.role.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.UserHandle;

/* JADX INFO: loaded from: classes8.dex */
public final class PackageUtils {
    private PackageUtils() {
    }

    public static PackageInfo getPackageInfo(String packageName, int extraFlags, Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(packageName, 786432 | extraFlags);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static ApplicationInfo getApplicationInfo(String packageName, Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationInfo(packageName, 786432);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static ApplicationInfo getApplicationInfoAsUser(String packageName, UserHandle user, Context context) {
        return getApplicationInfo(packageName, UserUtils.getUserContext(context, user));
    }
}
