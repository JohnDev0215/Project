package com.android.car.developeroptions.applications;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class AppStoreUtil {
    private static Intent resolveIntent(Context context, Intent intent) {
        ResolveInfo resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveInfoResolveActivity == null) {
            return null;
        }
        Intent intent2 = new Intent(intent.getAction());
        ActivityInfo activityInfo = resolveInfoResolveActivity.activityInfo;
        return intent2.setClassName(activityInfo.packageName, activityInfo.name);
    }

    public static String getInstallerPackageName(Context context, String str) {
        try {
            return context.getPackageManager().getInstallerPackageName(str);
        } catch (IllegalArgumentException e) {
            Log.e("AppStoreUtil", "Exception while retrieving the package installer of " + str, e);
            return null;
        }
    }

    public static Intent getAppStoreLink(Context context, String str, String str2) {
        Intent intentResolveIntent = resolveIntent(context, new Intent("android.intent.action.SHOW_APP_INFO").setPackage(str));
        if (intentResolveIntent == null) {
            return null;
        }
        intentResolveIntent.putExtra("android.intent.extra.PACKAGE_NAME", str2);
        return intentResolveIntent;
    }

    public static Intent getAppStoreLink(Context context, String str) {
        return getAppStoreLink(context, getInstallerPackageName(context, str), str);
    }
}
