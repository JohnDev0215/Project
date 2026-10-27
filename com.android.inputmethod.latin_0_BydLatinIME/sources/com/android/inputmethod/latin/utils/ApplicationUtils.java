package com.android.inputmethod.latin.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public final class ApplicationUtils {
    private static final String TAG = "ApplicationUtils";

    private ApplicationUtils() {
    }

    public static int getActivityTitleResId(final Context context, final Class<? extends Activity> cls) {
        try {
            ActivityInfo activityInfo = context.getPackageManager().getActivityInfo(new ComponentName(context, cls), 0);
            if (activityInfo != null) {
                return activityInfo.labelRes;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Failed to get settings activity title res id.", e);
        }
        return 0;
    }

    public static String getVersionName(final Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Could not find version info.", e);
            return "";
        }
    }

    public static int getVersionCode(final Context context) {
        if (context == null) {
            return 0;
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Could not find version info.", e);
            return 0;
        }
    }
}
