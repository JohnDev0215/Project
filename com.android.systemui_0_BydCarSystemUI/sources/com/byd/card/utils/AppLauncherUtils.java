package com.byd.card.utils;

import android.app.Activity;
import android.car.CarNotConnectedException;
import android.car.content.pm.CarPackageManager;
import android.car.media.CarMediaManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
class AppLauncherUtils {
    static final int APP_TYPE_LAUNCHABLES = 1;
    static final int APP_TYPE_MEDIA_SERVICES = 2;
    private static final String TAG = "AppLauncherUtils";

    @Retention(RetentionPolicy.SOURCE)
    @interface AppTypes {
    }

    private AppLauncherUtils() {
    }

    static class LauncherAppsInfo {
        LauncherAppsInfo() {
        }
    }

    static ComponentName getMediaSource(PackageManager packageManager, String packageName) {
        Intent intent = new Intent();
        intent.setPackage(packageName);
        intent.setAction("android.media.browse.MediaBrowserService");
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 64);
        if (listQueryIntentServices.isEmpty()) {
            return null;
        }
        String str = listQueryIntentServices.get(0).serviceInfo.name;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new ComponentName(packageName, str);
    }

    public static boolean shouldAddToLaunchables(ComponentName componentName, Set<String> blackList, Set<String> customMediaComponents, int appTypesToShow, int componentAppType) {
        if (blackList.contains(componentName.getPackageName())) {
            return false;
        }
        if (componentAppType != 1) {
            if (componentAppType == 2) {
                if (customMediaComponents.contains(componentName.flattenToString()) && (appTypesToShow & 1) != 0) {
                    return false;
                }
            } else {
                Log.e(TAG, "Invalid componentAppType : " + componentAppType);
                return false;
            }
        }
        return true;
    }

    private static void selectMediaSourceAndFinish(Context context, ComponentName componentName, CarMediaManager carMediaManager) {
        try {
            carMediaManager.setMediaSource(componentName, 1);
            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
        } catch (CarNotConnectedException e) {
            Log.e(TAG, "Car not connected", e);
        }
    }

    static boolean isActivityDistractionOptimized(CarPackageManager carPackageManager, String packageName, String activityName) {
        if (carPackageManager == null) {
            return false;
        }
        try {
            return carPackageManager.isActivityDistractionOptimized(packageName, activityName);
        } catch (CarNotConnectedException e) {
            Log.e(TAG, "Car not connected when getting DO info", e);
            return false;
        }
    }
}
