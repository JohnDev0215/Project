package com.android.car.apps.common.util;

import android.app.PendingIntent;
import android.car.content.pm.CarPackageManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

/* JADX INFO: loaded from: classes5.dex */
public class CarPackageManagerUtils {
    private static final String TAG = "CarPackageManagerUtils";

    public static boolean isDistractionOptimized(CarPackageManager carPackageManager, PendingIntent pendingIntent) {
        if (carPackageManager != null) {
            return carPackageManager.isPendingIntentDistractionOptimized(pendingIntent);
        }
        return false;
    }

    public static boolean isDistractionOptimized(CarPackageManager carPackageManager, ActivityInfo activityInfo) {
        if (carPackageManager != null) {
            return carPackageManager.isActivityDistractionOptimized(activityInfo.packageName, activityInfo.name);
        }
        return false;
    }

    public static boolean isDistractionOptimized(CarPackageManager carPackageManager, PackageManager packageManager, Intent intent) {
        ResolveInfo info = packageManager.resolveActivity(intent, 65536);
        if (info != null) {
            return isDistractionOptimized(carPackageManager, info.activityInfo);
        }
        return false;
    }
}
